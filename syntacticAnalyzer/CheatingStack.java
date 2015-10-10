/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.syntacticAnalyzer;

/**
 *
 * @author Billy
 * Cheating Stack to look further down the Stack
 */
public class CheatingStack {
   private SLListNode top;
  
   public CheatingStack() //Constructor
   {
       top=null;
   }
   /**
    * Clear the stack
    */
   public void clear() //clear
   {
       top=null;
   }
   
   public boolean isEmpty()
   {
       return top==null;
   }
  
   public Object pop() //Pop off Stack
   {
       if(top==null)
           return null;
       Object temp = top.data;
       top=top.next;
       return temp;
   }
  
   public void push(Object element) //Add to Stack
   {
       top=new SLListNode(element,top);
   }
   
   public Object top() //Return whats on Top of the Stack
   {
       if(top==null)
           return null;
       return top.data;
   }
   
  /**
   * This allows us to look down the Stack
   * @param num
   * @return  data
   */
   public Object peekAtLowerElement(int num)
   {
	   SLListNode temp=top;
	   while(temp.next!=null && num-->0){
		   temp=temp.next;
	   }
	   return temp.data;
   }
   
   /**
    * @return A string of the stack
    * 
    */
   public String toString()
   {
       String str = "Stack: \n [";
       SLListNode temp = top;
       while(temp!=null){
           str+=","+temp.data;
           temp=temp.next;
       }
       str+="]";
       return str.replaceFirst(",", "");
   }
}