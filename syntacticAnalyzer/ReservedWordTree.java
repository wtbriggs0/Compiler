/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

import compiler_spring_2015.syntacticAnalyzer.ReservedWordNode;

/**
 *
 * @author Billy
 */
public class ReservedWordTree {
	public ReservedWordNode root;
	
	public ReservedWordTree(){
		root=new ReservedWordNode("BEGIN",18);//
		root.left=new ReservedWordNode(":=",10);
		root.right=new ReservedWordNode("FALSE",26);
		root.left.left=new ReservedWordNode("*",6); 
		root.left.right=new ReservedWordNode("<>",14);
		root.right.left=new ReservedWordNode("DBMS_OUTPUT.PUT",22);
		root.right.right=new ReservedWordNode("TRUE",34);
		root.left.left.left=new ReservedWordNode("(",4); 
		root.left.left.right=new ReservedWordNode("-",8); 
		root.left.right.left=new ReservedWordNode("<",12); 
		root.left.right.right=new ReservedWordNode(">",16); 
		root.right.left.left=new ReservedWordNode("CHAR",20); 
		root.right.left.right=new ReservedWordNode("DECLARE",24); 
		root.right.right.left=new ReservedWordNode("NULL",30);
		root.right.right.right=new ReservedWordNode("c",38);
		root.left.left.left.left=new ReservedWordNode("&",2); 
		root.left.left.left.right=new ReservedWordNode(")",5);
		root.left.left.right.left=new ReservedWordNode("+",7); 
		root.left.left.right.right=new ReservedWordNode("/",9);
		root.left.right.left.left=new ReservedWordNode(";",11); 
		root.left.right.left.right=new ReservedWordNode("<=",13);
		root.left.right.right.left=new ReservedWordNode("==",15); 
		root.left.right.right.right=new ReservedWordNode(">=",17); 
		root.right.left.left.left=new ReservedWordNode("BOOLEAN",19); 
		root.right.left.left.right=new ReservedWordNode("DBMS_OUTPUT.NEW_LINE",21); 
		root.right.left.right.left=new ReservedWordNode("DBMS_OUTPUT.PUT_LINE",23); 
		root.right.left.right.right=new ReservedWordNode("END",25); 
		root.right.right.left.left=new ReservedWordNode("LOOP",28); 
		root.right.right.left.right=new ReservedWordNode("POSITIVE",32); 
		root.right.right.right.left=new ReservedWordNode("WHILE",36); 
		root.right.right.right.right=new ReservedWordNode("num",40);
		root.left.left.left.left.left=new ReservedWordNode("%",1); 
		root.left.left.left.left.right=new ReservedWordNode("\'",3);
		root.right.right.left.left.left=new ReservedWordNode("IF",27);
		root.right.right.left.left.right=new ReservedWordNode("NOT",29); 
		root.right.right.left.right.left=new ReservedWordNode("NUMBER",31);
		root.right.right.left.right.right=new ReservedWordNode("THEN",33); 
		root.right.right.right.left.left=new ReservedWordNode("VARCHAR2",35); 
		root.right.right.right.left.right=new ReservedWordNode("\\",37);
		root.right.right.right.right.left=new ReservedWordNode("identifier",39); 
		root.right.right.right.right.right=new ReservedWordNode("stringliteral",41); 
	}
	
	/**
     * Prints InOrder Traversal of Tree
     */
    public void inorderPrint()
    {
        System.out.println("Inorder:");
        inorder(root);
        System.out.println();
    }
    /**
     * inorderTraversal method
     * @param rt
     */
    private void inorder(ReservedWordNode rt)
    {
        if(rt==null)return;
        inorder(rt.left);
        System.out.print("\t"+rt.name);
        inorder(rt.right);
    }
	
	public int search(String target){
		ReservedWordNode temp=searchh(root, target);
			if(temp==null)
				return -1;
		return temp.getNumber();
	}
	
	public ReservedWordNode searchh(ReservedWordNode rt, String target){
		if(rt==null)return null;
		if(target.compareTo(rt.getName())<0)//target precedes root name
			return searchh(rt.left,target);
		if(target.compareTo(rt.getName())>0)//target succeeds root name
			return searchh(rt.right,target);
		return rt;
	}
}

