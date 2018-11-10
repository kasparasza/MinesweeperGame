package elements;
import java.util.*;

//!!! 
// dar pagalvok, ar is tiesu reikalinga si klase 
public class AdjacentCells
{
	private List<Cell> adjacentCells;
	
	public AdjacentCells(){
		this.adjacentCells = new ArrayList<>();
	}
	
	public void addCell(Cell cell){
		this.adjacentCells.add(cell);
	}
	
	public List<Cell> getListOfAdjacentCells(){
		return this.adjacentCells;
	}
	
	public int countMinedCells(){
		int result = 0;
		for(Cell c: this.adjacentCells){
			if(c.getIsMined()){
				result++;
			}
		}
		return result;
	}

	// idetas laikinam naudojimui
	@Override
	public String toString()
	{
		String result = "";
		for(Cell c: this.adjacentCells){
			result += c.getIsMined();
			result += "|";
		}
		return result;
	}
	
	
}
