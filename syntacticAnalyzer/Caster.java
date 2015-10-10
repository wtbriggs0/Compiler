/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

/**
 *
 * @author Billy
 */
public class Caster {
	boolean castTable[][]=new boolean[5][5];
	
	public Caster(){
		castTable[0][0]=true;//boolean to boolean
		
		castTable[1][1]=true;//CHAR to CHAR
		castTable[1][2]=true;//CHAR to VARCHAR2
		castTable[1][3]=true;//CHAR to NUM
		
		castTable[2][1]=true;//VARCHAR2 to VARCHAR2
		
		castTable[3][1]=true;//NUM to CHAR
		castTable[3][2]=true;//NUM to VARCHAR2
		castTable[3][3]=true;//NUM to NUM
		
		castTable[4][1]=true;//POSITIVE to CHAR
		castTable[4][3]=true;//POSITIVE to NUMBER
		castTable[4][4]=true;//POSITIVE to POSITIVE
	}
	
	public boolean areCompatible(String temp1,String temp2){
		return castTable[typeToInt(temp2)][typeToInt(temp1)];
	}
	
	public int typeToInt(String type){
		if(type.equals("BOOLEAN")){
			return 0;
		}
		else if(type.equals("CHAR")){
			return 1;
		}
		else if(type.equals("VARCHAR2")){
			return 2;
		}
		else if(type.equals("NUMBER")){
			return 3;
		}
		else if(type.equals("POSITIVE")){
			return 4;
		}
		else{
			return -1;
		}
	}
}

