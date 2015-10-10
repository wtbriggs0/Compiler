/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

import compiler_spring_2015.symboltable.SymbolTable;
import compiler_spring_2015.lexicalAnalyzer.Token;

/**
 *
 * @author Billy
 */
public class ParseNode {
	public int pNum;
	public Object value;
	public String type;
	public String name;
	public ReservedWordTree rwt;
	
	/**
	 * Constructor used to manually build a parse node
	 * (Used during the parsing when value, type, name, and paring num are passed up to LHS of productions)
	 * @param num The parsing num
	 * @param t The type
	 * @param v The value
	 * @param n The name
	 */
	public ParseNode(int num, String t, Object v, String n){
		pNum=num;
		type=t;
		value=v;
		name=n;
        }
	
	/**
	 * Constructor that builds a parse node based on a Token value. 
	 * This constructor contains all the logic to convert the Token into a Parse Node.
	 * @param t Token being converted
	 * @param st Symbol table used for token conversion
	 */
	public ParseNode(Token t, SymbolTable st){
		pNum=t.ParInt;
		if(t.type.equals("Identifier")){
			if(st.isDeclared(t.idName)){
				value=st.get(t.idName).getValue();
				type=st.get(t.idName).getType();
				name=t.idName;
			}
		}
		else if(t.type.equals("Constant")){
			if(t.parsingName.equals("num")){
				value=t.numValue;
				type="NUMBER";
				name=String.valueOf(value);
			}
			else if(t.parsingName.equals("c")){
				value=t.cValue;
				type="CHAR";
				name=String.valueOf(value);
			}
			else if(t.parsingName.equals("stringliteral")){
				value=t.stringliteralValue;
				type="VARCHAR2";
				name=String.valueOf(value);
			}
		}
		else if(t.type.equals("Reserved Word")){
			//Do Nothing?
			name=t.parsingName;
		}
	}
//	
//	public void hold(Object o){
//		value=o;
//	}
}
