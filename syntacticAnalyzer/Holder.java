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
public class Holder {
	public String type;
	public Object value;
	public String name;
	
	public Holder(String t,Object v, String n){
		type=t;
		value=v;
		name=n;
	}
}
