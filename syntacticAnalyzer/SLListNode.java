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
public class SLListNode {
   public Object data;
   public SLListNode next;
   
   public SLListNode(Object d, SLListNode n)
   {
       data = d;
       next = n;
   }
}

    

