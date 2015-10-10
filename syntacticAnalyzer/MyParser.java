/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;


import compiler_spring_2015.codegenerator.CodeGenerator;
import compiler_spring_2015.symboltable.SymbolTable;
import compiler_spring_2015.lexicalAnalyzer.LexicalAnalyzer;

import compiler_spring_2015.lexicalAnalyzer.Token;

/**
 *
 * @author Billy
 */
public class MyParser {
	private SymbolTable st;
	private LexicalAnalyzer lex;
	private Grammar g;
	private CheatingStack stack;
	private ManuallyBuiltParseTable p;
	private CodeGenerator cg;
	private CheatingStack IFstack;
	private CheatingStack WHILEstack;
	private boolean firstLOOP;
	private Caster caster;
	private final String OUTPUT_PATH="C:\\Users\\Billy\\Documents\\CompSci\\COSC\\Compiler\\ICE\\CompilerOutput.jam";
	
	public MyParser(String path) {
		lex = new LexicalAnalyzer(path);
		st=lex.getSymbolTable();
		g=new Grammar();
		stack=new CheatingStack();
		p=new ManuallyBuiltParseTable();
		cg=new CodeGenerator();
		IFstack=new CheatingStack();
		WHILEstack= new CheatingStack();
		firstLOOP=false;
		caster=new Caster();
	}
	
	/**
	 * Will parse the Project, and generate Code
	 * @return Any Error Messages the Project runs into
	 */
	public boolean parse(){
		int currTokenNum;
		int currState=0;
		boolean isDecStage=false;
		stack.push(new ParseNode(new Token("$end"),st));
              
		stack.push(currState);
		Token currToken=lex.getNextToken();
		currTokenNum=currToken.ParInt;
		while(!stack.isEmpty()){
			currState=(Integer)stack.top();
			int temp=p.pTable[currState][currTokenNum];
                        System.out.println("currState: " + currState);
                        System.out.println("currToken Num: " + currTokenNum);
                        System.out.println("current Token Name: " + currToken.parsingName);
                        System.out.println("---------------------------- 2");
			if(temp>0 &&temp<9999){//SHIFT
				if(currToken!=null){
					stack.push(new ParseNode(currToken,st));
				}
				stack.push(temp);
				if(temp!=6){

					if(currToken.parsingName.equals("THEN")){
						IFstack.push(cg.currentLine);
						cg.gen(cg.nextGen(), "JEQ", "#"+0, null, null);
					}
					if(currToken.parsingName.equals("WHILE")){
						WHILEstack.push(cg.currentLine);
						firstLOOP=true;
					}
					if(currToken.parsingName.equals("LOOP")){
						if(firstLOOP){
							firstLOOP=false;
							WHILEstack.push(cg.currentLine);
							cg.gen(cg.nextGen(), "JEQ", "#"+0, null, null);
						}
					}
					currToken=lex.getNextToken();
					currTokenNum=currToken.ParInt;
					if(currToken.parsingName.equals("DECLARE")){
						isDecStage=true;
					}
					if(currToken.parsingName.equals("BEGIN")){
						isDecStage=false;
					}
					continue;
				}
				temp=10000;
			}
			if(temp<0){//REDUCE
				CheatingStack codeStack=new CheatingStack();
				temp=Math.abs(temp);
				for(int i=1;i<g.grammarTable[temp][0];++i){
					Integer state = (Integer)stack.pop();
					System.out.println("state turns to: " + state);
                                        ParseNode currPN= (ParseNode)stack.pop();
                                        System.out.println("currPN turns to: " + currPN.name);
					codeStack.push(currPN);
					int value=currPN.pNum;
                                        
                                        
                                        
                                        System.out.println("Current ParseNode Name: " + currPN.name);
                                        System.out.println(" Current ParseNode Number: " + currPN.pNum);
                                        System.out.println("------------------");
                                        
                                        
					System.out.print("what we have:" + g.grammarTable[temp][i]+" : what it needs to be:  "+value+ "~~~");
                                       
                                        System.out.println();
                                        System.out.println(" PRODUCTION: " + temp + "POSITION: " + i);
                                        System.out.println();
					if(g.grammarTable[temp][i]!=value){//Match
                                                
						System.err.println("ERROR. Reduction failure at Production: "+temp+" Position: "+i);
						return false;
					}
				}
				
				if(temp>=24||temp==4||temp==6||temp==7||temp==9||temp==10||temp==11||temp==12||temp==13||temp==15||temp==16){//isDecStage==false){
					Holder holder=codeGeneration(temp,codeStack);
					stack.push(new ParseNode(g.grammarTable[temp][g.grammarTable[temp].length-1], holder.type ,holder.value,holder.name));
				}
				else{
					stack.push(new ParseNode(g.grammarTable[temp][g.grammarTable[temp].length-1], "LHS" ,null,"LHS"));
				}
				stack.push(p.pTable[(Integer)stack.peekAtLowerElement(1)][(Integer)((ParseNode)stack.top()).pNum]);
				continue;
			}
			if(temp==10000){//Accept
				cg.printCode();
                                System.out.println("Hits the Accept");
				cg.writeToFile(OUTPUT_PATH);
                                System.out.println("File Written.");
				return true;
			}
			if(temp==9999){//Error
				System.err.println("Hit An Error in Parsing (9999"); 
			
				return false;
			}
		}
		return false;
	}

	/**
	 * This method is for generating code
	 * @param productionNum The productionNum being reduced
	 * @param stack The RHS of the productionNum (top is left most element of the RHS) 
	 * @return The value being passed up to the new RHS ParseNode
	 */
	public Holder codeGeneration(int productionNum, CheatingStack cStack){
		String opOne, opTwo;
		switch(productionNum){
			case 4: //Declaration final step
				st.get(((ParseNode)cStack.top()).name).setType(((ParseNode)cStack.peekAtLowerElement(1)).type);
				st.get(((ParseNode)cStack.top()).name).setValue(((ParseNode)cStack.peekAtLowerElement(1)).value);
				if(((ParseNode)cStack.peekAtLowerElement(1)).type.equals("CHAR"))
					opOne="#"+(int)String.valueOf(((ParseNode)cStack.peekAtLowerElement(1)).value).charAt(0);
				else if(isInteger(((ParseNode)cStack.peekAtLowerElement(1)).name))
					opOne="#"+((ParseNode)cStack.peekAtLowerElement(1)).value;
				else if(((ParseNode)cStack.peekAtLowerElement(1)).type.equals("BOOLEAN"))
					opOne="#"+((ParseNode)cStack.peekAtLowerElement(1)).value;
				else
					opOne=String.valueOf(((ParseNode)cStack.peekAtLowerElement(1)).value);
				if(!(opOne.equals("null")||opOne.equals("#null"))){
					cg.gen(cg.nextGen(), "STO", opOne, null, ((ParseNode)cStack.top()).name);
				}
				return new Holder(((ParseNode)cStack.peekAtLowerElement(1)).type,((ParseNode)cStack.peekAtLowerElement(1)).value,((ParseNode)cStack.top()).name);
			case 6: //Declaration step 2
				return new Holder(((ParseNode)cStack.top()).name,((ParseNode)cStack.peekAtLowerElement(1)).value,((ParseNode)cStack.peekAtLowerElement(1)).name);
			case 7: //Declaration step 1
				return new Holder(((ParseNode)cStack.peekAtLowerElement(1)).type,((ParseNode)cStack.peekAtLowerElement(1)).value,((ParseNode)cStack.peekAtLowerElement(1)).name);
			case 9: //characters
			case 10: //numbers
			case 11: //BOOLEAN
			case 12: //CHAR
			case 13: //VARCHAR2
			case 15: //NUMBER
			case 16: //POSITIVE
				return new Holder(((ParseNode)cStack.top()).name,((ParseNode)cStack.top()).name,((ParseNode)cStack.top()).name);
			
			case 24: //PutLine
				if(((ParseNode)cStack.peekAtLowerElement(2)).type.equals("CHAR")||((ParseNode)cStack.peekAtLowerElement(2)).type.equals("VARCHAR2")){
					cg.gen(cg.nextGen(), "SYS", "#-"+2, ((ParseNode)cStack.peekAtLowerElement(2)).name, null);
				}
				else{
					cg.gen(cg.nextGen(), "SYS", "#-"+1, ((ParseNode)cStack.peekAtLowerElement(2)).name, null);
				}
					cg.gen(cg.nextGen(), "SYS", "#"+0, null, null);
				break;
			case 25: //Put
				if(((ParseNode)cStack.peekAtLowerElement(2)).type.equals("CHAR")||((ParseNode)cStack.peekAtLowerElement(2)).type.equals("VARCHAR2")){
					cg.gen(cg.nextGen(), "SYS", "#-"+2, ((ParseNode)cStack.peekAtLowerElement(2)).name, null);
				}
				else{
					cg.gen(cg.nextGen(), "SYS", "#-"+1, ((ParseNode)cStack.peekAtLowerElement(2)).name, null);
				}
				break;
			case 26: //NewLine
				cg.gen(cg.nextGen(), "SYS", "#"+0, null, null);
				break;
			case 27: //Read in
				cg.gen(cg.nextGen(), "SYS", "#"+1, null, String.valueOf(((ParseNode)cStack.peekAtLowerElement(1)).name));
				break;
 			case 28: // IF
				int tempAdd=(Integer)IFstack.pop();
				if(!((ParseNode)cStack.peekAtLowerElement(2)).type.equals("BOOLEAN")){
					System.err.println("ERROR. BOOLEAN expected instead of "+((ParseNode)cStack.peekAtLowerElement(2)).type+" in IF statement");
					System.exit(0);
				}
				cg.backPatch(tempAdd, cg.currentLine);
				cg.replaceOperandTwo(tempAdd, String.valueOf(((ParseNode)cStack.peekAtLowerElement(2)).value));
				
				break;
			case 29: // WHILE
				int tempAddOne=(Integer)WHILEstack.pop();
				if(!((ParseNode)cStack.peekAtLowerElement(1)).type.equals("BOOLEAN")){
					System.err.println("ERROR. BOOLEAN expected instead of "+((ParseNode)cStack.peekAtLowerElement(1)).type+" in WHILE statement");
					System.exit(0);
				}
				cg.backPatch(tempAddOne, cg.currentLine+1);
				cg.replaceOperandTwo(tempAddOne, String.valueOf(((ParseNode)cStack.peekAtLowerElement(1)).value));
				cg.gen(cg.nextGen(), "JMP", null, null, "#"+String.valueOf(WHILEstack.pop()));
				break;
			case 30: //identifier assignment
				if(!((ParseNode)cStack.top()).type.equals(((ParseNode)cStack.peekAtLowerElement(2)).type)){
					System.err.println("ERROR. Type Mismatch in assignment. Identifier type: "+((ParseNode)cStack.top()).type+ ", Expression type: "+((ParseNode)cStack.peekAtLowerElement(2)).type);
					System.exit(0);
				}
				if(((ParseNode)cStack.peekAtLowerElement(2)).type.equals("CHAR"))
					opOne="#"+(int)String.valueOf(((ParseNode)cStack.peekAtLowerElement(2)).value).charAt(0);
				else if(isInteger(((ParseNode)cStack.peekAtLowerElement(2)).name))
					opOne="#"+((ParseNode)cStack.peekAtLowerElement(2)).name;
				else if(((ParseNode)cStack.peekAtLowerElement(2)).type.equals("BOOLEAN")){
					if(((ParseNode)cStack.peekAtLowerElement(2)).name.equals("TRUE"))
						opOne="#"+1;
					else if(((ParseNode)cStack.peekAtLowerElement(2)).name.equals("FALSE"))
						opOne="#"+0;
					else
						opOne=((ParseNode)cStack.peekAtLowerElement(2)).name;
				}
					
				else
					opOne=((ParseNode)cStack.peekAtLowerElement(2)).name;
				cg.gen(cg.nextGen(), "STO", opOne, null,((ParseNode)cStack.top()).name);
				//cg.printLine(cg.currentLine-1);
				break;
			case 32: //String
			case 33: //character
				return new Holder(((ParseNode)cStack.peekAtLowerElement(1)).type,((ParseNode)cStack.peekAtLowerElement(1)).value,((ParseNode)cStack.peekAtLowerElement(1)).name);
			case 34: //Casting
				if(!caster.areCompatible(((ParseNode)cStack.top()).type, ((ParseNode)cStack.peekAtLowerElement(2)).type)){
					System.err.println("ERROR. "+ ((ParseNode)cStack.peekAtLowerElement(2)).type+" Cannot be cast to type: "+((ParseNode)cStack.top()).type);
					System.exit(0);
				}
				return new Holder(((ParseNode)cStack.top()).type,((ParseNode)cStack.peekAtLowerElement(2)).value,((ParseNode)cStack.peekAtLowerElement(2)).name);
			case 37: //RELOP
				String relop;
				if(!((ParseNode)cStack.top()).type.equals(((ParseNode)cStack.peekAtLowerElement(2)).type)){
					System.err.println("ERROR. Type Mismatch in comparison. Left-side type: "+((ParseNode)cStack.top()).type+ ", Right-side type: "+((ParseNode)cStack.peekAtLowerElement(2)).type);
					System.exit(0);
				}
				if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals(">"))
					relop="JGT";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("<"))
					relop="JLT";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals(">="))
					relop="JGE";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("<="))
					relop="JLE";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("=="))
					relop="JEQ";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("<>"))
					relop="JNE";
				else
					relop="SOMETHING WENT WRONG!";
				if(isInteger(((ParseNode)cStack.top()).name))
					opOne="#"+((ParseNode)cStack.top()).name;
				else
					opOne=((ParseNode)cStack.top()).name;
				if(isInteger(((ParseNode)cStack.peekAtLowerElement(2)).name))
					opTwo="#"+((ParseNode)cStack.peekAtLowerElement(2)).name;
				else
					opTwo=((ParseNode)cStack.peekAtLowerElement(2)).name;
				cg.gen(cg.nextGen(), relop, opOne, opTwo, "#"+(cg.currentLine+2));
				String relTemp=cg.newTemp();
				cg.gen(cg.nextGen(), "STO","#"+0, null, relTemp);
				cg.gen(cg.nextGen(), "JMP", null, null, "#"+(cg.currentLine+1));
				cg.gen(cg.nextGen(), "STO","#"+1, null, relTemp);
				//break;
				return new Holder("BOOLEAN",relTemp,relTemp);
			case 39: //ADDOP
				String addop;
				if(!((ParseNode)cStack.top()).type.equals(((ParseNode)cStack.peekAtLowerElement(2)).type)){
					System.err.println("ERROR. Type Mismatch in addition operation(+,-). Left-side type: "+((ParseNode)cStack.top()).type+ ", Right-side type: "+((ParseNode)cStack.peekAtLowerElement(2)).type);
					System.exit(0);
				}
				if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("+"))
					addop="ADD";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("-"))
					addop="SUB";
				else
					addop="SOMETHING WENT WRONG!";
				if(isInteger(((ParseNode)cStack.top()).name))
					opOne="#"+((ParseNode)cStack.top()).name;
				else
					opOne=((ParseNode)cStack.top()).name;
				if(isInteger(((ParseNode)cStack.peekAtLowerElement(2)).name))
					opTwo="#"+((ParseNode)cStack.peekAtLowerElement(2)).name;
				else
					opTwo=((ParseNode)cStack.peekAtLowerElement(2)).name;
				String temp=cg.newTemp();
				cg.gen(cg.nextGen(), addop, opOne, opTwo, temp);
				
				return new Holder(((ParseNode)cStack.top()).type,temp,temp);
			case 41: //MULOP
				String mulop;
	
				if(!((ParseNode)cStack.top()).type.equals(((ParseNode)cStack.peekAtLowerElement(2)).type)){
					System.err.println("ERROR. Type Mismatch in multiplication operation(*,/,%). Left-side type: "+((ParseNode)cStack.top()).type+ ", Right-side type: "+((ParseNode)cStack.peekAtLowerElement(2)).type);
					System.exit(0);
				}
				if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("*"))
					mulop="MUL";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("/"))
					mulop="DIV";
				else if(((ParseNode)cStack.peekAtLowerElement(1)).name.equals("%"))
					mulop="MOD";
				else
					mulop="SOMETHING WENT WRONG!";
				if(isInteger(((ParseNode)cStack.top()).name))
					opOne="#"+((ParseNode)cStack.top()).name;
				else
					opOne=((ParseNode)cStack.top()).name;
				if(isInteger(((ParseNode)cStack.peekAtLowerElement(2)).name))
					opTwo="#"+((ParseNode)cStack.peekAtLowerElement(2)).name;
				else
					opTwo=((ParseNode)cStack.peekAtLowerElement(2)).name;
				String temp1=cg.newTemp();
				cg.gen(cg.nextGen(), mulop, opOne, opTwo, temp1);
				
				return new Holder(((ParseNode)cStack.top()).type,temp1,temp1);
			case 44:
				return new Holder("BOOLEAN",1,"TRUE");
			case 45:
				return new Holder("BOOLEAN",0,"FALSE");
			case 46:
				return new Holder("BOOLEAN",-1,"NULL");
			case 47: //NOT
				if(!((ParseNode)cStack.peekAtLowerElement(1)).type.equals("BOOLEAN")){
					System.err.println("ERROR. BOOLEAN expected instead of "+((ParseNode)cStack.peekAtLowerElement(1)).type+" in NOT statement");
					System.exit(0);
				}
				String temp2 = cg.newTemp();
				cg.gen(cg.nextGen(), "NOT",((ParseNode)cStack.peekAtLowerElement(1)).name, null, temp2);
				
				return new Holder("BOOLEAN",temp2,temp2);
		}
		return new Holder(((ParseNode)cStack.top()).type,((ParseNode)cStack.top()).value, ((ParseNode)cStack.top()).name);
	}
	
	/**
	 * @param s String being tested
	 * @return If it is an integer Value
	 */
	public boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}

