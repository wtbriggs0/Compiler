/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.lexicalAnalyzer;

import compiler_spring_2015.syntacticAnalyzer.MyParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Billy
 */
public class JavaLexicalAnalyzer {
	
	private static MyParser fyle=new MyParser("C:\\Users\\Billy\\Desktop\\Sample Program 6.txt");

	
	/**
	 * Read the input file, separate the lexemes, and hash the symbols
	 * @param filePath 
	 *
	 */
	public static ArrayList<String> readFile(String filePath) throws FileNotFoundException, IOException{
        File OutputPath=new File(filePath);
		FileInputStream fstream = new FileInputStream(OutputPath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("LexicalAnalyzerOutput.txt"));
        String strLine;
        char[] charLine;
        ArrayList<char[]> tempCharList = new ArrayList<char[]>();
        int lineCtr=0;
        while((strLine=br.readLine())!=null){
        	if(strLine.isEmpty()){
                lineCtr++;
        		continue;
        	}
        	//add char array (line) to array list of char arrays
        	charLine=strLine.toCharArray();
            tempCharList.add(charLine);
            lineCtr++;
        }
        ArrayList<String> xyz = buffer(removeComments(tempCharList));
	    if(xyz!=null){	
			for(String s:xyz){
	    		bw.write("$" + s + "$");
	    		bw.newLine();
	    	}
		}
        in.close();
        bw.flush();
        bw.close();
        return xyz;
	}
	
	/**
	 * Converts file into an array List, with comments taken out.
	 * @param tempCharList 
	 * 
	 */
	public static char[] removeComments(ArrayList<char[]> tempCharList){
		ArrayList<Character> noCommentsCharList = new ArrayList<Character>();
		boolean isComment=false;
		for(int i=0;i<tempCharList.size();++i){
			for(int j=0;j<tempCharList.get(i).length;++j){
				if(tempCharList.get(i)[j]=='/' && (j+1)<tempCharList.get(i).length){
					if(tempCharList.get(i)[j+1]=='/'){
						break;
						
					}
					if(tempCharList.get(i)[j+1]=='*'){
						isComment=true;
						continue;
					}
				}
				if((j+1)<tempCharList.get(i).length&&tempCharList.get(i)[j]=='*'&&tempCharList.get(i)[j+1]=='/'){
					isComment=false;
					j++;
					continue;
				}
				if(isComment==false){
					noCommentsCharList.add(tempCharList.get(i)[j]);
				}
			}
		}
		char[] charArray = new char[noCommentsCharList.size()];
		for(int i=0;i<noCommentsCharList.size();i++){
			charArray[i]=noCommentsCharList.get(i);
		}
		return charArray;
	}
	
	/**
	 * Character Array to ArrayList of Strings
	 * @param line
	 * @return
	 */
	public static ArrayList<String> buffer(char[] line){
		ArrayList<String> stringArray = new ArrayList<String>();
		int i=0;
		while(i<line.length){
    		int current=i;
			int next=current+1;
			if(Character.isWhitespace(line[current])){
				i++;
				continue;
			}
			if(!Character.isJavaIdentifierPart(line[current])){
				stringArray.add(String.valueOf(line[current]));
				i++;
				continue;
			}
			if(Character.isJavaIdentifierPart(line[current])){
				char[] identifier=new char[line.length];
				int temp=0;
				identifier[temp++]=line[current];
				if(next<line.length){
					while(Character.isJavaIdentifierPart(line[next])||line[next]=='.'||line[next]=='*'){
						identifier[temp++]=line[next++];
						if(next>=identifier.length)
							break;
					}
				}
				stringArray.add(String.valueOf(identifier).trim());
				i=next;
			}
    	}
		return stringArray;
	}
}

    

