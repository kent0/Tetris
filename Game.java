import java.lang.String;

public class Game {
    long startTime;
    int[][] well = new int[20][10];
    Tetromino currentPiece = new Tetromino();

    public Game() {
	this.startTime = System.currentTimeMillis();

	for (int i = 0; i < well.length; i++) {
	    for (int j = 0; j < well[0].length; j++) {
		this.well[i][j] = 0;
	    }
	}
    }

    public String toString() {
	String description = "";
	description += "Well: \n";
	    
	for (int[] row : well) {
	    description += "||";
	    for (int element : row) {
		if (element == 0) {
		    description += "· ";
		} else {
		    description += "[]";
		}
	    }
	    description += "||\n";
	}

	description += "||";
	
	for (int i = 0; i < 20; i++) {
	    description += "-";
	}

	description += "||\n";

	return description;
    }
}
