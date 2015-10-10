/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Billy
 */
public class ParseTableBuilder {
	
	public String[][] stringTable;
	public int[][] intTable;
	
	public ArrayList<String> strTerm=new ArrayList<String>();
	public ArrayList<Integer> intTerm=new ArrayList<Integer>();
	
	public ArrayList<String> strNonTerm=new ArrayList<String>();
	public ArrayList<Integer> intNonTerm=new ArrayList<Integer>();
	
	public ArrayList<Integer> stateList=new ArrayList<Integer>();
	
	public int[][] pTable;
	
	public ParseTableBuilder(){
		pTable=new int[10000][100];
		initializeTable(pTable);
	}
	
	/**
	 * Builds a parse table, saving the resulting table to the field pTable
	 * @param filePath The path of the .output file produced by bison
	 */
	public int[][] buildTable(String filePath){
		try{
            File f=new File(filePath);
            FileInputStream fstream = new FileInputStream(f);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while((strLine=br.readLine())!=null){
                if(strLine.isEmpty())
                    continue;
                //Extract Terminals
                if(strLine.trim().startsWith("Terminals")){
                	strLine=br.readLine();
                	while(!strLine.trim().startsWith("Nonterminals")){
                		if(!strLine.trim().startsWith("error")&&!strLine.isEmpty()){
                    		strTerm.add(strLine.split("\\s+")[0]);
                    		System.out.println("Added: " + strLine.split("\\s+")[0] + " to list of String Terminals");//W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W
                		}
                		strLine=br.readLine();
                	}
                }
                //Extract NonTerminals
                if(strLine.trim().startsWith("Nonterminals")){
                	strLine=br.readLine();
                	while(!strLine.trim().startsWith("state")){
                		if(!strLine.trim().startsWith("$accept")&&!strLine.isEmpty()&&!strLine.trim().startsWith("on")){
                			strNonTerm.add(strLine.split("\\s+")[0]);
                			System.out.println("Added: " + strLine.split("\\s+")[0] + " to list of String NonTerminals");//W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W
                		}
                		strLine=br.readLine();
                	}
                }
                //Extract State info
                if(strLine.trim().startsWith("state")){
	            	do{	
	                	if(strLine.trim().startsWith("state")){
		                	int currState=Integer.parseInt(strLine.split("\\s+")[1]);
		                	stateList.add(currState);
		                	System.out.println("Added: " + currState + " to list of States");//W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W-W
		                	//while(!(strLine=br.readLine()).trim().startsWith("state")){
		                	while((strLine=br.readLine())!=null){
		                		if(strLine.trim().startsWith("state")){
		                			break;
		                		}
		                		if(strLine.contains(":"))
		                			continue;
		                		if(strLine.isEmpty())
		                			continue;
		                		//int value=Integer.parseInt(strLine.split[0])
		                		//if(value<100) means this is a terminal
			                	//if(!(strLine.split("\\s+")[0].isEmpty())){
		                		//Add shifts/reductions/accepts to parse table
		                			if(Character.isLowerCase(strLine.trim().toCharArray()[0])|| strLine.trim().startsWith("$")){
			                			if(strLine.contains("shift")){
				                			int nextState=Integer.parseInt(strLine.substring(strLine.indexOf("state")+6).trim());
				                			pTable[currState][terminalToInteger(strLine.split("\\s+")[1])]=nextState;
			                			}
			                			if(strLine.contains("reduce")){
			                				int production=Integer.parseInt(strLine.substring(strLine.indexOf("rule")+5,strLine.indexOf("rule")+6).trim());
			                				if(strLine.trim().startsWith("$default")){
			                					for(int k=0;k<(strTerm.size());++k){
			                		                pTable[currState][k]=-production;
			                		            }
			                					continue;
			                				}
			                				pTable[currState][terminalToInteger(strLine.split("\\s+")[1])]=-production;
			                			}
			                			if(strLine.contains("accept")){
			                				if(strLine.trim().startsWith("$default")){
			                					for(int k=0;k<(strTerm.size());++k){
			                		                pTable[currState][k]=0;
			                		            }
			                					continue;
			                				}
			                				pTable[currState][terminalToInteger(strLine.split("\\s+")[1])]=0;
			                			}
			                		}
			                	//}
		                		//if(value>=100) means this is a nonterminal
		                		if(Character.isUpperCase(strLine.trim().toCharArray()[0])){
//		                			System.out.println("TERM5:" +strLine.split("\\s+")[5]);
		                			pTable[currState][nonTerminalToInteger(strLine.split("\\s+")[1])]=Integer.parseInt(strLine.split("\\s+")[5]);
		                		}
		                	}
	                	}
	                }while((strLine)!=null);
            	}
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		int[][] temp = new int[stateList.size()][strTerm.size()+strNonTerm.size()];
		for(int y=0;y<temp.length;++y){
			for(int z=0;z<temp[y].length;++z){
				temp[y][z]=pTable[y][z];
			}
		}
		pTable=temp;
		return temp;
	}
	
	/**
	 * Changes a string terminal into the integer index used for the pTable
	 * @param term The String terminal to be converted
	 * @return the integer index
	 */
	public int terminalToInteger(String term){
		for(int i=0;i<strTerm.size();++i){
			if(strTerm.get(i).equals(term))
				return i;
		}
		System.out.println("Error with: "+term);
		return -1;
	}
	
	/**
	 * Changes a string nonterminal into the integer index used for the pTable
	 * @param nonterm The String nonterminal to be converted
	 * @return the integer index
	 */
	public int nonTerminalToInteger(String nonterm){
		for(int i=0;i<strNonTerm.size();++i){
			if(strNonTerm.get(i).equals(nonterm))
				return i + strTerm.size();
		}
		System.out.println("Error with: "+nonterm);
		return -1;
	}
	
	/**
	 * Initializes the table to have 999(error) in all memory locations. That way, 
	 * the program doesn't have to add them later
	 * @param tab Table to be initialized
	 */
	public void initializeTable(int[][] tab){
		for(int i=0;i<tab.length;++i){
			for(int j=0;j<tab[i].length;++j){
				tab[i][j]=999;
			}
		}
	}
	
	/**
	 * Prints textual representation of the Parse table pTable. States=rows, Action/Goto=Columns
	 */
	public void printTable(){
        System.out.print("\t");
        for(int i=0;i<strTerm.size();++i){
            System.out.print(strTerm.get(i)+"\t");
        }
        for(int i=0;i<strNonTerm.size();++i){
            System.out.print(strNonTerm.get(i)+"\t");
        }
        System.out.println();
        for(int j=0;j<stateList.size();++j){
            System.out.print(stateList.get(j)+"\t");
            for(int k=0;k<(strTerm.size()+strNonTerm.size());++k){
                System.out.print(pTable[j][k]+"\t");
            }
            System.out.println();
        }
    }
}
