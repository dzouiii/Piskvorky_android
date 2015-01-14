package com.example.piskvorky;

public class GameBoard {
	//deklarace
	private String[][] theBoard = new String[3][3];

	//inicializace boardu s prazdnymi retezci
		GameBoard() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					theBoard[i][j] = "";
				}
			}
		}
		
		//reset
		public void clear() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					theBoard[i][j] = "";
				}
			}
		}
				
		//kontrola jestli v boardu uz znak neni a pripadne ulozeni znaku
		public void placeMark(int x, int y, String mark) {
		if (theBoard[x][y] == "") theBoard[x][y] = mark;

		}
		
		//Rozhodovani o vitezstvi
		public boolean isWinner() {
		//diagonaly
		if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] != "")

		return true;

		if (theBoard[2][0] == theBoard[1][1] && theBoard[2][0] == theBoard[0][2] && theBoard[2][0] != "")

		return true;

		for (int i = 0; i < 3; i++) {

		//radky
		if (theBoard[i][0] == theBoard[i][1] && theBoard[i][1] == theBoard[i][2] && theBoard[i][0] != "")

		return true;

		//sloupce
		if (theBoard[0][i] == theBoard[1][i] && theBoard[1][i] == theBoard[2][i] && theBoard[0][i] != "")

		return true;

		}

		return false;
		}
}
