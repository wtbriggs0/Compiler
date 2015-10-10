/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

/**
 * This will create a node that will hold the information that will be used
 * in the Reserved word tree
 * @author Billy
 */
public class ReservedWordNode {
    public String name;
    private int num;
    public ReservedWordNode left;
    public ReservedWordNode right;
    
    public ReservedWordNode(String name, int num){
        this.name = name;
        this.num = num;
    }
    
    
    
    public String getName(){
        return name;
    }
    
    public int getNumber(){
        return num;
    }
    
    public String toString(){
        return name + " : " + num;
        
    }
    
}
