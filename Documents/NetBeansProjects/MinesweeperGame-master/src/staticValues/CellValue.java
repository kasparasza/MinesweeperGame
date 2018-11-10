package staticValues;

// !!! Kol kas nenaudoju MINED reiksmes / ji painiojasi su FLAGGED

public enum CellValue {
    CLOSED("X"),
    FLAGGED("*"),
    UNCERTAIN("?"),
    MINED("$"),
    EXPLODED("@"),
    EMPTY(" "),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private String value;

    private CellValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
