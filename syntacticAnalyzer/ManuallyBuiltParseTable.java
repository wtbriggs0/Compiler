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

public class ManuallyBuiltParseTable {

	public int[][] pTable;
	
	public ManuallyBuiltParseTable(){
		pTable=new int[112][123];
		SetTable();
		
                // STATE 0
		fillReductionRow(0,-3);
		pTable[0][24]=1;
		pTable[0][100]=2;
		pTable[0][101]=3;
		//SSTATE 1
		fillReductionRow(1,-5);
		pTable[1][39]=4;
		pTable[1][102]=5;
		//SSTATE2
		pTable[2][0]=6;
		//SSTATE3
		pTable[3][18]=7;
		pTable[3][109]=8;
		//STATE 4
		pTable[4][19]=9;
		pTable[4][20]=10;
		pTable[4][31]=11;
		pTable[4][32]=12;
		pTable[4][35]=13;
		pTable[4][103]=14;
		pTable[4][105]=15;
		pTable[4][106]=16;
		pTable[4][108]=17;
		//STATE 5
		fillReductionRow(5,-2);
		//STATE6
		fillReductionRow(6,10000);
		//STATE 7
		pTable[7][2]=18;
		pTable[7][18]=7;
		pTable[7][21]=19;
		pTable[7][22]=20;
		pTable[7][23]=21;
		pTable[7][27]=22;
		pTable[7][30]=23;
		pTable[7][36]=24;
		pTable[7][39]=25;
		pTable[7][109]=26;
		pTable[7][110]=27;
		pTable[7][111]=28;
		pTable[7][112]=29;
		pTable[7][113]=30;
		//STATE 8
		pTable[8][37]=31;
		//STATE 9
		fillReductionRow(9,-11);
		//STATE 10
		fillReductionRow(10,-12);
		//STATE 11
		pTable[11][4]=32;
		pTable[11][107]=33;
		//STATE 12
		pTable[12][4]=32;
		pTable[12][107]=34;
		//STATE 13
		pTable[13][4]=32;
		pTable[13][107]=35;
		//STATE 14
		pTable[14][11]=36;
		//STATE 15
		fillReductionRow(15,-8);
		pTable[15][10]=37;
		pTable[15][104]=38;
		//STATE 16
		fillReductionRow(16,-9);
		//STATE 17
		fillReductionRow(17,-10);
		//SSTATE 18
		pTable[18][39]=39;
		//STATE 19
		pTable[19][11]=40;
		//STATE 20
		pTable[20][4]=41;
		//STATE 21
		pTable[21][4]=42;
		//STATE 22
		pTable[22][18]=43;
		//STATE 23
		pTable[23][11]=44;
		//STATE 24
		pTable[24][26]=45;
		pTable[24][29]=46;
		pTable[24][30]=47;
		pTable[24][34]=48;
		pTable[24][39]=49;
		pTable[24][40]=50;
		pTable[24][116]=51;
		pTable[24][117]=52;
		pTable[24][118]=53;
		pTable[24][119]=54;
		//STATE 25
		pTable[25][10]=55;
		//STATE 26
		fillReductionRow(26,-23);
		//STATE 27
		pTable[27][25]=56;
		//28
		fillReductionRow(28,-19);
		pTable[28][2]=18;
		pTable[28][18]=7;
		pTable[28][21]=19;
		pTable[28][22]=20;
		pTable[28][23]=21;
		pTable[28][27]=22;
		pTable[28][36]=24;
		pTable[28][39]=25;
		pTable[28][109]=26;
		pTable[28][112]=57;
		pTable[28][113]=30;
		//STATE 29
		fillReductionRow(29,-20);
		//STATE 30
		pTable[30][11]=58;
		//STATE 31
		fillReductionRow(31,-1);
		//STATE 32
		pTable[32][40]=59;
		//STATE 33
		fillReductionRow(33,-15);
		//STATE 34
		fillReductionRow(34,-16);
		//STATE 35
		fillReductionRow(35,-13);
		//STATE 36
		fillReductionRow(36,-5);
		pTable[36][39]=4;
		pTable[36][102]=60;
		//STATE 37
		pTable[37][3]=61;
		pTable[37][19]=9;
		pTable[37][20]=10;
		pTable[37][26]=45;
		pTable[37][29]=46;
		pTable[37][30]=47;
		pTable[37][31]=11;
		pTable[37][32]=12;
		pTable[37][34]=48;
		pTable[37][35]=13;
		pTable[37][39]=49;
		pTable[37][40]=50;
		pTable[37][105]=62;
		pTable[37][106]=16;
		pTable[37][108]=17;
		pTable[37][114]=63;
		pTable[37][115]=64;
		pTable[37][116]=65;
		pTable[37][117]=52;
		pTable[37][118]=53;
		pTable[37][119]=54;
		//STATE 38
		fillReductionRow(38,-6);
		//STATE 39
		pTable[39][11]=66;
		//STATE 40
		fillReductionRow(40,-26);
		//STATE 41
		pTable[41][39]=67;
		//STATE 42
		pTable[42][39]=68;
		//STATE 43
		pTable[43][26]=45;
		pTable[43][29]=46;
		pTable[43][30]=47;
		pTable[43][34]=48;
		pTable[43][39]=49;
		pTable[43][40]=50;
		pTable[43][116]=69;
		pTable[43][117]=52;
		pTable[43][118]=53;
		pTable[43][119]=54;
		//STATE 44
		fillReductionRow(44,-18);
		//STATE 45
		fillReductionRow(45,-45);
		//STATE 46
		pTable[46][26]=45;
		pTable[46][29]=46;
		pTable[46][30]=47;
		pTable[46][34]=48;
		pTable[46][39]=49;
		pTable[46][40]=50;
		pTable[46][119]=70;
		//STATE 47
		fillReductionRow(47,-46);
		//STATE 48
		fillReductionRow(48,-44);
		//STATE 49
		fillReductionRow(49,-42);
		//STATE 50
		fillReductionRow(50,-43);
		//STATE 51
		pTable[51][28]=71;
		//STATE 52
		fillReductionRow(52,-36);
		pTable[52][7]=72;
		pTable[52][8]=73;
		pTable[52][12]=74;
		pTable[52][13]=75;
		pTable[52][14]=76;
		pTable[52][15]=77;
		pTable[52][16]=78;
		pTable[52][17]=79;
		pTable[52][120]=80;
		pTable[52][121]=81;
		//STATE 53
		fillReductionRow(53,-38);
		pTable[53][1]=82;
		pTable[53][6]=83;
		pTable[53][9]=84;
		pTable[53][122]=85;
		//STATE 54
		fillReductionRow(54,-40);
		//STATE 55
		pTable[55][3]=61;
		pTable[55][19]=9;
		pTable[55][20]=10;
		pTable[55][26]=45;
		pTable[55][29]=46;
		pTable[55][30]=47;
		pTable[55][31]=11;
		pTable[55][32]=12;
		pTable[55][34]=48;
		pTable[55][35]=13;
		pTable[55][39]=49;
		pTable[55][40]=50;
		pTable[55][105]=62;
		pTable[55][106]=16;
		pTable[55][108]=17;
		pTable[55][114]=86;
		pTable[55][115]=64;
		pTable[55][116]=65;
		pTable[55][117]=52;
		pTable[55][118]=53;
		pTable[55][119]=54;
		//STATE 56
		pTable[56][11]=87;
		//STATE 57
		fillReductionRow(57,-21);
		//STATE 58
		fillReductionRow(58,-22);
		//STATE 59
		pTable[59][5]=88;
		//STATE 60
		fillReductionRow(60,-4);
		//STATE 61
		pTable[61][38]=89;
		pTable[61][41]=90;
		//STATE 62
		fillReductionRow(62,-35);
		//STATE 63
		fillReductionRow(63,-7);
		//STATE 64
		pTable[64][4]=91;
		//STATE 65
		fillReductionRow(65,-31);
		//STATE 66
		fillReductionRow(66,-27);
		//STATE 67
		pTable[67][5]=92;
		//STATE 68
		pTable[68][5]=93;
		//STATE 69
		pTable[69][33]=94;
		//STATE 70
		fillReductionRow(70,-47);
		//STATE 71
		pTable[71][2]=18;
		pTable[71][18]=7;
		pTable[71][21]=19;
		pTable[71][22]=20;
		pTable[71][23]=21;
		pTable[71][27]=22;
		pTable[71][36]=24;
		pTable[71][39]=25;
		pTable[71][109]=26;
		pTable[71][112]=95;
		pTable[71][113]=30;
		//STATE 72
		fillReductionRow(72,-54);
		//STATE 73
		fillReductionRow(73,-55);
		//STATE 74
		fillReductionRow(74,-52);
		//STATE 75
		fillReductionRow(75,-51);
		//STATE 76
		fillReductionRow(76,-53);
		//STATE 77
		fillReductionRow(77,-50);
		//STATE 78
		fillReductionRow(78,-48);
		//STATE 79
		fillReductionRow(79,-49);
		//STATE 80
		pTable[80][26]=45;
		pTable[80][29]=46;
		pTable[80][30]=47;
		pTable[80][34]=48;
		pTable[80][39]=49;
		pTable[80][40]=50;
		pTable[80][117]=96;
		pTable[80][118]=53;
		pTable[80][119]=54;
		//STATE 81
		pTable[81][26]=45;
		pTable[81][29]=46;
		pTable[81][30]=47;
		pTable[81][34]=48;
		pTable[81][39]=49;
		pTable[81][40]=50;
		pTable[81][118]=97;
		pTable[81][119]=54;
		//STATE 82
		fillReductionRow(82,-58);
		//STATE 83
		fillReductionRow(83,-56);
		//STATE 84
		fillReductionRow(84,-57);
		//STATE 85
		pTable[85][26]=45;
		pTable[85][29]=46;
		pTable[85][30]=47;
		pTable[85][34]=48;
		pTable[85][39]=49;
		pTable[85][40]=50;
		pTable[85][119]=98;
		//STATE 86
		fillReductionRow(86,-30);
		//STATE 87
		fillReductionRow(87,-17);
		//STATE 88
		fillReductionRow(88,-14);
		//STATE 89
		pTable[89][3]=99;
		//STATE 90
		pTable[90][3]=100;
		//STATE 91
		pTable[91][26]=45;
		pTable[91][29]=46;
		pTable[91][30]=47;
		pTable[91][34]=48;
		pTable[91][39]=49;
		pTable[91][40]=50;
		pTable[91][116]=101;
		pTable[91][117]=52;
		pTable[91][118]=53;
		pTable[91][119]=54;
		//STATE 92
		pTable[92][11]=102;
		//STATE 93
		pTable[93][11]=103;
		//STATE 94
		pTable[94][2]=18;
		pTable[94][18]=7;
		pTable[94][21]=19;
		pTable[94][22]=20;
		pTable[94][23]=21;
		pTable[94][27]=22;
		pTable[94][36]=24;
		pTable[94][39]=25;
		pTable[94][109]=26;
		pTable[94][112]=104;
		pTable[94][113]=30;
		//STATE 95
		pTable[95][25]=105;
		//STATE 96
		fillReductionRow(96,-37);
		pTable[96][7]=72;
		pTable[96][8]=73;
		pTable[96][121]=81;
		//STATE 97
		fillReductionRow(97,-39);
		pTable[97][1]=82;
		pTable[97][6]=83;
		pTable[97][9]=84;
		pTable[97][122]=85;
		//STATE 98
		fillReductionRow(98,-41);
		//STATE 99
		fillReductionRow(99,-33);
		//STATE 100
		fillReductionRow(100,-32);
		//STATE 101
		pTable[101][5]=106;
		//STATE 102
		fillReductionRow(102,-25);
		//STATE 103
		fillReductionRow(103,-24);
		//STATE 104
		pTable[104][25]=107;
		//STATE 105
		pTable[105][28]=108;
		//STATE 106
		fillReductionRow(106,-34);
		//STATE 107
		pTable[107][27]=109;
		//STATE 108
		pTable[108][11]=110;
		//STATE 109
		pTable[109][11]=111;
		//STATE 110
		fillReductionRow(110,-29);
		//STATE 111
		fillReductionRow(111,-28);
	}
	
	/**
	 * Fills entire table with error values (9999)
	 */
	public void SetTable(){
		for(int i=0;i<pTable.length;++i){
			for(int j=0;j<pTable[i].length;++j){
				pTable[i][j]=9999;//Error
			}
		}
	}
	
	/**
	 * Fills an entire action row with a particular value. Used specifically when filling
	 * rows for reductions.
	 * @param row The row being filled
	 * @param value The value -(production rule #) being inserted
	 */
	public void fillReductionRow(int row, int value){
		for(int i=0;i<=41;++i){
			pTable[row][i]=value;
		}
	}
}