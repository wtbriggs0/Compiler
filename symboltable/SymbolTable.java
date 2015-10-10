/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_spring_2015.symboltable;

import compiler_spring_2015.symboltable.Symbol;

/**
 * This symbol table will insert, get, Check to see:
 * isDeclared, and printStatements, ect.
 * @author Billy
 */
public class SymbolTable {

    private Symbol[] table;
    private int size;
    public int counter;

    public SymbolTable(int initCapacity) {
        table = new Symbol[initCapacity];
        size = initCapacity;
        counter = 0;
    }

    public int put(Symbol s) {
        if (counter < size) {
            table[counter] = s;
            return counter++;
        } else {
            return -1;
        }
    }

    public Symbol get(String key) {
        int i = 0;
        while (i < counter) {
            if (key.equals(table[i].getName())) {
                return table[i];
            } else {
                i++;
            }
        }
        return null;
    }

    public Symbol get(int number) {
        number -= 1000;
        if (number < size && number >= 0) {
            return table[number];
        } else {
            return null;
        }
    }

    public boolean isDeclared(String temp) {
        for (int i = 0; i <= counter; ++i) {
            if (table[i] != null) {
                if (table[i].getName().equals(temp)) {
                    return true;
                }

            }
        }
        return false;

    }
    
    public void printTable(){
        System.out.println("Table ~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.println();
        for(int i=0;i<counter;++i){
            System.out.println(table[i]);
        }
        System.out.println("End Table~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

}
