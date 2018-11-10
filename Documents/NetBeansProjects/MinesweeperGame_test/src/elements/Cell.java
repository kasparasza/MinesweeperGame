package elements;
import staticValues.*;

public class Cell
{
	private boolean isOpen;
	private boolean isMined;
	private CellValue valueForDisplay;
	private CellValue valueActual;
	private String borderCellValue;
	
	//!!! truksta:
	// 1) setter ir getter metodu
	// 2) gal border cell ir paprasta cel turi buti atskirose klasese
	
	public Cell(boolean isOpen, boolean isMined){
		this.isOpen = isOpen;
		this.isMined = isMined;
		
		if(this.isMined){
			setActualValue(CellValue.MINED);
		} else {
			setActualValue(CellValue.EMPTY); //???? siti cia neturi buti, bet be jo kol kas nullPointerE
		}
		
		//???
		updateDisplayedValue();
		
	}
	
	public Cell(String value){
		this.borderCellValue = value;
	}
	
	public void setBorderCellValue(String newValue){
		this.borderCellValue = newValue;
	}
	
	public boolean getIsMined(){
		return this.isMined;
	}
	
	// neaisku, ar reikalingas
	public boolean getIsOpen(){
		return this.isOpen;
	}
	
	public CellValue getValueDisplayed(){
		return this.valueForDisplay;
	}
	
	public CellValue getValueActual(){
		return this.valueActual;
	}
	
	public void setValueDisplayed(CellValue value){
		this.valueForDisplay = value;
	}
	
	public void openCell(){
		this.isOpen = true;
		updateDisplayedValue();
	}
	
	//!!! ar tikrai visais atvejais tinka "CLOSED"?
	private void updateDisplayedValue(){
		if(this.isOpen){
			this.valueForDisplay = this.valueActual;
		} else {
			this.valueForDisplay = CellValue.CLOSED;
		}
	}
	
	//!!! cia gal biski kreiva, kad verte nustato kita klase
	public void setActualValue(CellValue value){
		this.valueActual = value;
		updateDisplayedValue();
	}
	
	public void setActualValue(int value){
		switch (value){
			case 0:
				this.valueActual = CellValue.EMPTY;
				break;
			case 1:
				this.valueActual = CellValue.ONE;
				break;
			case 2:
				this.valueActual = CellValue.TWO;
				break;
			case 3:
				this.valueActual = CellValue.THREE;
				break;
			case 4:
				this.valueActual = CellValue.FOUR;
				break;
			case 5:
				this.valueActual = CellValue.FIVE;
				break;
			case 6:
				this.valueActual = CellValue.SIX;
				break;
			case 7:
				this.valueActual = CellValue.SEVEN;
				break;
			case 8:
				this.valueActual = CellValue.EIGHT;
				break;
			default:
				this.valueActual = CellValue.EMPTY;
		}
		updateDisplayedValue();
	}
	
	@Override
	public String toString()
	{
		if(this.borderCellValue == null){
			return " " + this.valueForDisplay.getValue();
		} else {
			return this.borderCellValue;	
		}
	}
	
	
	
}
