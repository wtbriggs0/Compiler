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

public class Grammar {
	public int[][] grammarTable;
	
        /**
         * The first element  [___][0] just states how long each line is
         */
	public Grammar(){
		grammarTable=new int[][]{
				{3,0,100,10000},//0 Blank production
				{4,37,109,101,100},//1
				{3,102,24,101},//2
				{1,101},//3
				{5,102,11,103,39,102},//4
				{1,102},//5
				{3,104,105,103},//6
				{3,114,10,104},//7
				{1,104},//8
				{2,106,105},//9
				{2,108,105},//10
				{2,19,105},//11
				{2,20,106},//12
				{3,107,35,106},//13
				{4,5,40,4,107},//14
				{3,107,31,108},//15
				{3,107,32,108},//16
				{5,11,25,110,18,109},//17
				{3,11,30,110},//18
				{2,111,110},//19
				{2,112,111},//20
				{3,112,111,111},//21
				{3,11,113,112},//22
				{2,109,112},//23
				{6,11,5,39,4,23,112},//24
				{6,11,5,39,4,22,112},//25
				{3,11,21,112},//26
				{4,11,39,2,112},//27
				{9,11,27,25,112,33,116,18,27,112},//28
				{8,11,28,25,112,28,116,36,112},//29
				{4,114,10,39,113},//30
				{2,116,114},//31
				{4,3,41,3,114},//32
				{4,3,38,3,114},//33
				{5,5,116,4,115,114},//34
				{2,105,115},//35
				{2,117,116},//36
				{4,117,120,117,116},//37
				{2,118,117},//38
				{4,118,121,117,117},//39
				{2,119,118},//40
				{4,119,122,118,118},//41
				{2,39,119},//42
				{2,40,119},//43
				{2,34,119},//44
				{2,26,119},//45
				{2,30,119},//46
				{3,119,29,119},//47
				{2,16,120},//48
				{2,17,120},//49
				{2,15,120},//50
				{2,13,120},//51
				{2,12,120},//52
				{2,14,120},//53
				{2,7,121},//54
				{2,8,121},//55
				{2,6,122},//56
				{2,9,122},//57
				{2,1,122},//58
		};
	}
}
