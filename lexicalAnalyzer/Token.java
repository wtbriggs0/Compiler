/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.lexicalAnalyzer;

import compiler_spring_2015.syntacticAnalyzer.ReservedWordTree;

/**
 *
 * @author Billy
 */
public class Token {
	public String parsingName;
	public int ParInt;//Numerical Representation
	public String type;//Reserved Word, Identifier, or Constant
	public String idName;
	public int numValue;//Special case for 'num'
	public char cValue;//Special case for 'c'
	public String stringliteralValue;//Special case for 'Stringliteral'
	public int idSTnum;//This is the numerical representation of the symbol table number for an identifier
	public ReservedWordTree rwt;
	
	/**
	 * Constuctor for num, identifier, and all other reserved words except c and stringliteral
	 * @param in
	 */
	public Token(String in){
		rwt=new ReservedWordTree();
		if(in=="$end"){
			parsingName="$end";
			ParInt=0;
			type="Reserved Word";
			return;
		}
		else if(isInteger(in)){
			parsingName="num";
			numValue=Integer.parseInt(in);
			type="Constant";
		}
		else if(rwt.search(in)==-1){
			idName=in;
			parsingName="identifier";
			type="Identifier";
		}
		else{
			parsingName=in;
			type="Reserved Word";
		}
		ParInt=rwt.search(parsingName);
	}
	
	/**
	 * Constructor 
	 * @param in
	 * @param b
	 */
	public Token(String in, boolean b){
		rwt=new ReservedWordTree();
		if(b==true){
			if(in.length()==1){
				parsingName="c";
				cValue=in.charAt(0);
			}
			else{
				parsingName="stringliteral";
				stringliteralValue=in;
			}
			type="Constant";
			ParInt=rwt.search(parsingName);
		}
	}
	
	public boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}

