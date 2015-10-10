/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.codegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Billy
 */
public class CodeGenerator {
	public int currentLine;
	public int tempNum;
	public String[][] iCode;
	
	public CodeGenerator(){
		currentLine=0;
		iCode=new String[500][5];
	}
	
	/**
	 * Creates a 2D array, that inserts the line of Code into
	 * @param line line number being written to
	 * @param operator 
	 * @param op1 The 1st operand
	 * @param op2 The 2nd operand
	 * @param op3 The 3rd operand
	 */
	public void gen(int line,String operator, String op1, String op2, String op3){
		iCode[line][0]=String.valueOf(line);
		iCode[line][1]=operator;
		iCode[line][2]=op1;
		iCode[line][3]=op2;
		iCode[line][4]=op3;
		
	}
	
	/**
	 * Returns the current line incremented
	 * 
	 */
	public int nextGen(){
		return currentLine++;
	}
	
	public String newTemp(){
		return "T"+tempNum++;
	}
	
	/**
	 * Prints the single line of code
	 * @param 
	 */
	public void printLine(int r){
		System.out.print(iCode[r][0]);
		System.out.print(" ");
		System.out.print(iCode[r][1]);
		System.out.print(" ");
		System.out.print(iCode[r][2]);
		System.out.print(",");
		System.out.print(iCode[r][3]);
		System.out.print(",");
		System.out.print(iCode[r][4]);
		System.out.println();
	}
	
	/**
	 * BackPatch
	 * @param codeLine 
	 * @param jumpLocation The value which is being inserted into the code
	 */
	public void backPatch(int codeLine, int jumpLocation){
		iCode[codeLine][4]="#"+jumpLocation;
	}
	
	/**
	 * Replaces 2nd operand
	 * @param codeLine
	 * @param operand 
	 */
	public void replaceOperandTwo(int codeLine, String operand){
		if(isInteger(operand)){
			operand= "#"+Integer.parseInt(operand);
		}
		iCode[codeLine][3]=operand;
	}
	
	/**
	 * Prints the code
	 */
	public void printCode(){
		for(int i=0;i<currentLine;++i){
			printLine(i);
		}
	}
	
	/**
	 * @param s String being tested
	 * @return Whether or not the string being tested is an integer value
	 */
	public boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
        /**
         * Writes to File, given the outputPath
         * @param filePath 
         */
	public void writeToFile(String filePath){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
			prepareForMice();
			for(int i=0;i<currentLine;++i){
				bw.write(iCode[i][0]+" "+iCode[i][1]+" "+iCode[i][2]+","+iCode[i][3]+","+iCode[i][4]);
				bw.newLine();
			}
			bw.write(nextGen()+" HLT ,,");
			bw.flush();
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
         * Create file in a way that Mice can use it.
         */
	public void prepareForMice(){
		for(int i=0;i<currentLine;++i){
			for(int j=0;j<iCode[i].length;++j){
				if(iCode[i][j]==null){
					iCode[i][j]="";
				}
			}
		}
	}
}
