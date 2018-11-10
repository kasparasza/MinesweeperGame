package elements;
import java.util.*;

//!!! 
// bandomoji klase - jei pavyks, ja bus pakeista adjacent cells
// manau int[] reiktu pakeisti i atskira klase - koordinates
// galbut reikia tureti du objektus: List<Cell> ir Map<>
public class AdjacentCellsMap
{
	private List<Cell> adjacentCellsList;
	private Map<Cell, int[]> adjacentCellsMap;

	public AdjacentCellsMap(){
		this.adjacentCellsList = new ArrayList<>();
		this.adjacentCellsMap = new HashMap<>();
	}

	public void addCell(Cell cell, int[] coordinates){
		this.adjacentCellsList.add(cell);
		this.adjacentCellsMap.put(cell, coordinates);
	}

	public List<Cell> getAdjacentCells(){
		return this.adjacentCellsList;
	}
	
	public int[] getCellCoordinates(Cell cell){
		return this.adjacentCellsMap.get(cell);
	}

	public int countMinedCells(){
		int result = 0;
		for(Cell c: this.adjacentCellsList){
			if(c.getIsMined()){
				result++;
			}
		}
		return result;
	}


}
