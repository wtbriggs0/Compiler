/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.codegenerator;

/**
 *
 * @author Billy
 */
public class Code {
	String operator,op1,op2,op3;
	
	
	public Code(String a, String b, String c, String d){
		operator=a;op1=b;op2=c;op3=d;
	}
	
	@Override
	public String toString(){
		return operator + " " + op1 + "," + op2 + "," + op3;
	}
}

