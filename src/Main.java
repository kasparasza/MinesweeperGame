import java.util.*;
import elements.*;
import staticValues.*;
import userInterface.*;

public class Main
{
	// TOLIAU:
	//3) Pradek kurti UI logika
	//4) Langeliu atvertimas: visos grupes -> tolesnes grupes, jei pries tai atversta tuscia lastele
	// 4.1) sukuriaU ALTERNATYVIA KLASE adjacentCellsMap ir perdariau 2 metodus MineField
	// nesuprantu, kodel gaunu stackOverflow
	//6) Ar klases neatlieka per daug f-ju (pvz. MineField ?)
	//7) gal galima lauka atspausdinti su spalvomis?
	public static void main(String[] args){		
		UserInterface gameUi = new UserInterface();
		//gameUi.startGame();   // <---- ar galima kitaip, kad is main metodo butu call i start, o ne atvirksciai
	
	//[h][w]
	/*
		MineField myMineField = new MineField(15, 15, 0.25);
		System.out.println(myMineField);
		System.out.println("Cells to open: " + myMineField.countCellsRemainingForOpening());
		System.out.println("Cells with value: " + myMineField.countCellsByType(CellValue.ONE));
		*/
	}
	
	/*
	Integer[][] myTable = new Integer[5][3];
	for(int i = 0; i < 5; i++){
		for(int j = 0; j < 3; j++){
			myTable[i][j] = 0;
			System.out.print(myTable[i][j]);
		}
		System.out.println("\n");
	}
	*/
	
}
