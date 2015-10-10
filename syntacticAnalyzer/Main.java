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
public class Main {

	private static MyParser parser;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
            
		
                parser = new MyParser("C:\\Users\\Billy\\Desktop\\Sample Program 6.txt");
		boolean b=parser.parse();

	}

}


