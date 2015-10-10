package compiler_spring_2015.lexicalAnalyzer;

import compiler_spring_2015.syntacticAnalyzer.ReservedWordTree;
import compiler_spring_2015.symboltable.Symbol;
import compiler_spring_2015.symboltable.SymbolTable;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Lexical Analyzer will read the file
 *
 * @author Billy
 */
public class LexicalAnalyzer {

    ArrayList<String> sStream = new ArrayList<String>();
    ArrayList<Token> tStream = new ArrayList<Token>();
    SymbolTable symTable = new SymbolTable(8998);
    ReservedWordTree rwt = new ReservedWordTree();
    ArrayList<String> idList = new ArrayList<String>();
    int counter = 0;

    public LexicalAnalyzer(String filePath) {
        sStream = readFile(filePath);
        buildSymbolTable();
        tStream = tokenize(sStream);
      
    }

    /**
     * This will read in the source file, Separate the Lexemes, and Hash the
     * Symbols
     *
     * @param filePath (Path of File)
     * @return
     */
    public ArrayList<String> readFile(String filePath) {
        ArrayList<String> temp8 = new ArrayList<String>();

        try {
            File fyle = new File(filePath);
            FileInputStream fstream = new FileInputStream(fyle);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            char[] charArray;

            ArrayList<char[]> tempCharList = new ArrayList<char[]>();

            int lineCtr = 0;
            while ((strLine = br.readLine()) != null) {
                lineCtr++;
                if (strLine.isEmpty()) {
                    continue;
                } else {
                    charArray = strLine.toCharArray();
                }
                tempCharList.add(charArray);
            }
            temp8 = buffer(removeComments(tempCharList));
        } catch (Exception e) {
            System.err.println("ERROR:  " + e);
           
        }
        return temp8;

    }

    
    
    public char[] removeComments(ArrayList<char[]> tempCharList) {
        ArrayList<Character> charListNoComments = new ArrayList<Character>();
        boolean isComment = false;

        for (int i = 0; i < tempCharList.size(); ++i) {
            for (int j = 0; j < tempCharList.get(i).length; ++j) {
                if (tempCharList.get(i)[j] == '/' && (j + 1) < tempCharList.get(i).length) {
                    if (tempCharList.get(i)[j + 1] == '*') {
                        isComment = true;
                        continue;
                    }
                }
                if ((j + 1) < tempCharList.get(i).length && tempCharList.get(i)[j] == '*' && tempCharList.get(i)[j + 1] == '/') {
                    isComment = false;
                    j++;
                    continue;
                }
                if (tempCharList.get(i)[j] == '-' && (j + 1) < tempCharList.get(i).length) {
                    if (tempCharList.get(i)[j + 1] == '-') {
                        break;
                    }
                }
                if (isComment == false) {
                    charListNoComments.add(tempCharList.get(i)[j]);
                }
            }
        }
        char[] charArray = new char[charListNoComments.size()];
        for (int i = 0; i < charListNoComments.size(); i++) {
            charArray[i] = charListNoComments.get(i);
        }
        return charArray;
    }

    
    /**
	 * Turns one line in a text file from a character array into an ArrayList 
	 * of Strings.
	 * @param line
	 * @return
	 */
	public ArrayList<String> buffer(char[] line){
		ArrayList<String> sArray = new ArrayList<String>();
		int i=0;
		while(i<line.length){
    		int currentLine=i;
			int next=currentLine+1;
			if(Character.isWhitespace(line[currentLine])){
				i++;
				continue;
			}
			if(line[currentLine]=='\''){
				sArray.add(String.valueOf(line[currentLine]));
				if(line[currentLine+1]==' '&& currentLine+1<line.length){
					int j=1;
					String temp="";
					while(line[currentLine+j]!='\''&& currentLine+j<line.length){
						temp+=line[currentLine+j++];
						i++;
					}
					sArray.add(temp);
				}
				i++;
				continue;
			}
			if(line[currentLine]==':'&& currentLine+1<line.length){
				String temp=":";
				if(line[currentLine+1]=='='){
					temp+="=";
					sArray.add(temp);
					i++;i++;
					continue;
				}
			}
			if(line[currentLine]=='<'&& currentLine+1<line.length){
				String temp="<";
				if(line[currentLine+1]=='='){
					temp+="=";
					sArray.add(temp);
					i++;i++;
					continue;
				}
				else if(line[currentLine+1]=='>'){
					temp+=">";
					sArray.add(temp);
					i++;i++;
					continue;
				}
				sArray.add(temp);
				++i;
				continue;
			}
			if(line[currentLine]=='>'&& currentLine+1<line.length){
				String temp=">";
				if(line[currentLine+1]=='='){
					temp+="=";
					sArray.add(temp);
					i++;i++;
					continue;
				}
				sArray.add(temp);
				++i;
				continue;
			}
			if(line[currentLine]=='='&& currentLine+1<line.length){
				String temp="=";
				if(line[currentLine+1]=='='){
					temp+="=";
					sArray.add(temp);
					i++;i++;
					continue;
				}
				sArray.add(temp);
				++i;
				continue;
			}
			if(!Character.isJavaIdentifierPart(line[currentLine])){// && line[currentLine]!='\''){
				sArray.add(String.valueOf(line[currentLine]));
				i++;
				continue;
			}
			if(Character.isJavaIdentifierPart(line[currentLine])){//||line[currentLine]=='\''){
				char[] identifier=new char[line.length];
				int temp=0;
				identifier[temp++]=line[currentLine];
				if(next<line.length){
					while(Character.isJavaIdentifierPart(line[next])||line[next]=='.'){//||line[next]=='*'){
						identifier[temp++]=line[next++];
						if(next>=identifier.length)
							break;
					}
				}
				if(isInteger(String.valueOf(identifier).trim())){
					sArray.add(String.valueOf(identifier).trim());
					i=next;
					continue;
				}
				sArray.add(String.valueOf(identifier).trim());
				i=next;
			}
    	}

		return sArray;
	}
	
	/**
	 * @return The next token in the token stream
	 */
	public Token getNextToken(){
		
		if(streamIsEmpty()){
			return null;
		}
		return tStream.get(counter++);
	}
	
	/**
	 * Uses the string stream of strings in between DECLARE and BEGIN and builds the symbol table
	 * from those values
	 */
	public void buildSymbolTable(){
		for(int i=0;i<sStream.size(); ++i){
			if(sStream.get(i).equals("BEGIN")){
				break;
			}
			if(isInteger(sStream.get(i))){
				continue;
			}
			if(sStream.get(i).equals("\'")){
				i++;
				while(!sStream.get(i).equals("\'")){
					i++;
				}
				continue;
			}
			if(rwt.search(sStream.get(i))==-1){//Check to see if this is an identifier?
				if(!idList.contains(sStream.get(i))){	
                                        System.out.println("--" + sStream.get(i));
					idList.add(sStream.get(i));
				}
				else{
					System.err.println("Whoops! The Identifier ' "+sStream.get(i)+" ' has already been declared. (Lex analyzer)");
					System.exit(0);
				}
				Symbol sym=new Symbol(sStream.get(i), sStream.get(i+1), symTable.counter+1000);
				symTable.put(sym);
				if(sStream.get(i+1).equals("VARCHAR2") ||sStream.get(i+1).equals("NUMBER") ||sStream.get(i+1).equals("POSITIVE")){
					sym.setSize(Integer.parseInt(sStream.get(i+3)));
					if(sStream.get(i+5).equals(":=")){
						int limit=Integer.parseInt(sStream.get(i+3));
						int size;
						if(sStream.get(i+1).equals("VARCHAR2")){
							size=sStream.get(i+7).length();
						}
						else{
							size=sStream.get(i+6).length();
						}
						if(size>limit){
							System.err.println("Whoops #2.  "+sStream.get(i)+" Size need to be less than: "+limit);
							System.exit(0);
						}
					}
				}
				i++;continue;
			}
		}
	}
	
	/**
	 * Take stream of Strings, and Tokenize them
	 * @param sal The stream of strings
	 * @return The stream of tokens
	 */
	public ArrayList<Token> tokenize(ArrayList<String> sal){
		ArrayList<Token> newAList=new ArrayList<Token>();
		for(int i=0;i<sStream.size();++i){
			if(sStream.get(i).equals("\'")){
				newAList.add(new Token(sStream.get(i)));//add ' to list
				++i;
				newAList.add(new Token(sStream.get(i),true));//string/char constant?
				++i;
				newAList.add(new Token(sStream.get(i)));//add '  to list
				continue;
			}
			newAList.add(new Token(sStream.get(i)));//insert reserved words, identifiers, num constants
		}
		newAList.add(new Token("$end")); //make sure to add $end so code can compile
		tStream=newAList;
		checkIfAllDeclared(); //Declared Checking
		return newAList;
	}
	
	/**
	 * DoubleChecks Declared Tokens
	 * @return Error if Found.
	 */
	private boolean checkIfAllDeclared(){
		for(Token t:tStream){
			if(t.type=="Identifier"){
				if(symTable.isDeclared(t.idName)){
					t.idSTnum=symTable.get(t.idName).getID();
					continue;
				}
				if(!symTable.isDeclared(t.idName)){
					System.err.println("Whoops #3. Identifier: "+ t.idName + "Is Undeclared. (Lex Analyzer)");
					System.exit(0);
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * @return Is the Stream Empty?
	 */
	public boolean streamIsEmpty(){

		return counter>=tStream.size();
	}
	/**
         * Is Integer?
         * @param s
         * @return 
         */
	public boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public void printStringStream(){
		for(String s: sStream){
			System.out.println("$"+s+"$");
		}
	}
	
	/**
	 * Prints the stream of tokens. 
	 */
	public void printTokenStream(){
		for(Token t: tStream){
			System.out.println("~"+t.parsingName+"~");
		}
	}
	
	/**
	 * @return The symbol table that was built in this Lexical Analyzer
	 */
	public SymbolTable getSymbolTable(){
		return symTable;
	}
}


