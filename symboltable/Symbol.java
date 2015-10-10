/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler_spring_2015.symboltable;

/**
 *
 * @author Billy
 */
public class Symbol {
	
	private String name;
	private String type;
	private String pName;
	private Object value;
	private int id;
	private int size;
	
	public Symbol(String name, String type, int id){ //Constructor
		this.name=name;
		this.type=type;
		this.id=id;
	}
	
	public void setName(String n){ //Name of Symbol
		name=n;
	}
	
	public String getName(){ //Return name
		return name;
	}
	
	public void setType(String t){ //Set Type of Symbol
		type=t;
	}
	
	public String getType(){ //return type
		return type;
	}
	
	public void setPName(String p){ //Set Name of Symbol
		pName=p;
	}
	
	public String getPName(){ //Return name of symbol
		return pName;
	}
	
	public void setValue(Object v){ //Set value of Symbol
		value=v;
	}
	
	public Object getValue(){ //Return value of symbol
		return value;
	}
	
	public void setID(int i){ //Set ID
		id=i;
	}
	
	public int getID(){ //Return ID
		return id;
	}
	
	public void setSize(int s){ //set size of Symbol***
		size=s;
	}
	
	public int getSize(){ //Return size
		return size;
	}
	
	public String toString(){
		String bam=id +"  =  "+ name;
		return bam;
	}
}
