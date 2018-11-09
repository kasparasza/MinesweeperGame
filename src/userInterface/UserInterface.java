package userInterface;
import elements.*;
import java.util.*;
import staticValues.*;

public class UserInterface
{
	private MineField field;
	private Scanner reader;
	private int height;
	private int width;
	
	public UserInterface(){
		this.reader = new Scanner(System.in);
		startGame();
	}
	
	// padaryti, kad useris pas sukurtu minu lauka
	//veliau tb reikia padaryti rekursine f-ja
	//panaudoti while
	
	public void startGame(){
		System.out.println("Welcome to Minesweeper!");
		System.out.println("Type \"O\", if you want to choose size and difficulty for the game,");
		System.out.println("or press \"Enter\" to quick-start.");
		System.out.println("");
		String input = reader.nextLine();
		System.out.println("");
		if(input.toLowerCase().equals("o")){
			System.out.println("Settings: ");
			
			// get user input
			int[] dimensions = readDimensions();
			double mineChance = Double.parseDouble(	readDifficulty().getValue());
			
			this.height = dimensions[0];
			this.width = dimensions[1];
			this.field = new MineField(this.height, this.width, mineChance);
		} else {
			this.height = Integer.parseInt(MineFieldSettings.PRESET_HEIGHT.getValue());
			this.width = Integer.parseInt(MineFieldSettings.PRESET_WIDTH.getValue());
			double mineChance = Double.parseDouble(MineFieldSettings.PRESET_DIFFICULTY.getValue());
			this.field = new MineField(this.height, this.width, mineChance);
		}
		
		System.out.println(this.field);
		//!!! TOLESNIO METODO KVIETIMAS
		makeAMove();
	}
	
	public void makeAMove(){
	 //!!!
		if(this.field.performActionOnCell(readUserInput())){
			// kol kas cia padarome nesibaigianti loop,
			// veliau reiks patikros, ar dar yra ka veikti
			System.out.println(this.field);
			makeAMove();
		} else {
			System.out.println(this.field);
			System.out.println("");
			System.out.println("You have hit a mine..");
			System.out.println("GAME OVER");			
		}

	}
	
	private int[] readDimensions(){
		int minBound = Integer.parseInt(MineFieldSettings.MIN_HEIGHT.getValue());
		int maxBound = Integer.parseInt(MineFieldSettings.MAX_HEIGHT.getValue());
		String formatedMessage = String.format("they should be between %d and %d.", minBound, maxBound);
		
		System.out.println("Set height and width of the minefield,");
		System.out.println(formatedMessage);
		System.out.println("press \"Enter\" after each.");
		
		String hInput = reader.nextLine();
		String wInput = reader.nextLine();
		
		// validation of input
		if(hInput.isEmpty() || wInput.isEmpty() || !hInput.matches("[0-9]+") ||	!wInput.matches("[0-9]+")){
			readDimensions();
		}				
		
		int hDimension = Integer.parseInt(hInput);
		int wDimension = Integer.parseInt(wInput);
		if(hDimension < minBound || hDimension > maxBound ||
		wDimension < minBound || wDimension > maxBound){
			readDimensions();
		}
		
		return new int[]{hDimension, wDimension};		
	}
	
	private MineFieldSettings readDifficulty(){
		System.out.println("Choose difficulty level:");
		System.out.println("\"1\" - easy,");
		System.out.println("\"2\" - medium,");
		System.out.println("\"3\" - hard,");
		System.out.println("\"4\" - extra hard.");
		System.out.println("");
		String input = "";
		
		//validation of input
		while(input.isEmpty() || !input.matches("[1-4]")){
			input = reader.nextLine();
		}
		
		switch(Integer.parseInt(input)){
			case 1:
				return MineFieldSettings.EASY_DIFFICULTY;
			case 2:
				return MineFieldSettings.MEDIUM_DIFFICULTY;
			case 3:
				return MineFieldSettings.HARD_DIFFICULTY;
			case 4:
				return MineFieldSettings.EXTRA_HARD_DIFFICULTY;
			default:
				return MineFieldSettings.MEDIUM_DIFFICULTY;
		}
	}
	
	private String[] readUserInput(){
		System.out.println("Cell to choose: X & Y coordinates");
		String wCoord = reader.nextLine();
		String hCoord = reader.nextLine();
		System.out.println("Action: (O - open, F - flag, ? - mark as uncertain)");
		String action = reader.nextLine();
		String[] input = new String[]{hCoord, wCoord, action};
		if(!validateInput(input)){
			System.out.println("Invalid input, please repeat.");
			readUserInput();
		}
		return input;
	}
	
	private boolean validateInput(String[] input){
		// check if not empty
		for(int i = 0; i < 3; i++){
			if(input[i].isEmpty()){
				return false;
			}
		}
		// check if integers were entered
		for(int i = 0; i < 2; i++){
			if(!input[i].matches("[0-9]+")){
				return false;
			}
		}	
		
		// check if integers are within bounds
		if(Integer.parseInt(input[0]) > this.width){
			return false;
		}
		if(Integer.parseInt(input[1]) > this.height){
			return false;
		}
		
		// check for valid action command
		if(!input[2].toLowerCase().matches("[o|f|?]")){
			return false;
		}
		return true;
	}
	
}
