package com.example.piskvorky;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//deklarace promìnných
	private GameBoard board = null;
	private int moveCount = 0, x = 0, y = 0, turn = 0;
	private String cross = "X", circle = "O";
	private boolean end = false;
	
	//inicializace plochy
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//nastaveni layaoutu a schovani action baru
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		
		//vytvoreni boardu
		board = new GameBoard();
	}

	//pokud je zmacknuto tlacitko Dalsi kolo tak se obnovi plocha a vymazou hodnoty v bunkach
	public void resetClick(View v) {
		clear();
	}
	
	//rozhodovani na kterou bunku bylo kliknuto
	public void cellClick(View v) {
		//vytahnuti id na kterou bunku bylo kliknuto a pretipovani na TextView
		TextView cell = (TextView) findViewById(v.getId());
		//do content ulozeni textu bunky
		String content = (String) cell.getText();
		if (content == "" && !end) {
			//prirazeni lokace na jakou bunku bylo kliknuto pomoci promennych x, y
			switch (cell.getId()) {
				case R.id.cell11:
					x = 0; y = 0; break;
				case R.id.cell12:
					x = 0; y = 1; break;
				case R.id.cell13:
					x = 0; y = 2; break;
				case R.id.cell21:
					x = 1; y = 0; break;
				case R.id.cell22:
					x = 1; y = 1; break;
				case R.id.cell23:
					x = 1; y = 2; break;
				case R.id.cell31:
					x = 2; y = 0; break;
				case R.id.cell32:
					x = 2; y = 1; break;
				case R.id.cell33:
					x = 2; y = 2; break;
			}
			
			if (turn == 0 ){
			//umisteni znacky hrace na specifickou polohu
				board.placeMark(x, y, cross);
				cell.setText(cross);
				turn = 1;
				moveCount++;
				end = checkEnd(cross);
			} else{
				board.placeMark(x, y, circle);
				cell.setText(circle);
				turn = 0;
				moveCount++;
				end = checkEnd(circle);
			}
		}
	}
	
	//Kontrola jestli hrac ktery provedl posledni krok neukoncil hru
	private boolean checkEnd(String player) {
		//vitez
		if (board.isWinner())
		{
			announce(true, player);
			return true;
		}
		//remiza
		else if (moveCount >= 9)
		{
			announce(false, player);
			return true;
		}
		//Pokud neni vyherce ani remiza tak pokracuje
		return false;
	}

	//oznameni kdo vyhral nebo jestli je remiza
	private void announce(boolean endState, String player) {
		if (endState == true)
			player = player + " vyhrál!";
		else
			player = "Remíza!";
		
		//vypsani vysledku
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, player, Toast.LENGTH_LONG);
		toast.show();
	}
	
	//vycisteni boardu
	private void clear() {
		//ziskani idListu vsech bunek
		int[] idList = { R.id.cell11, R.id.cell12, R.id.cell13, R.id.cell21,
				R.id.cell22, R.id.cell23, R.id.cell31, R.id.cell32, R.id.cell33 };
		TextView cell;
		//ve vsech bunkach vymazu text
		for (int item : idList) {
			cell = (TextView) findViewById(item);
			cell.setText("");
		}
		//reset hry a vymazani boardu
		end = false;
		moveCount = 0;
		board.clear();
	}
}