import java.lang.String;

public class Game {
    int[][] well;
    Tetromino currentPiece;

    public Game() {
	currentPiece = new Tetromino();
	well = new int[22][10];
	
	for (int i = 0; i < well.length; i++) {
	    for (int j = 0; j < well[0].length; j++) {
		this.well[i][j] = 0;
	    }
	}

	int[] startPosition = new int[2];
	
	switch (currentPiece.getShape().length) {
	    
	case 2:
	    startPosition[0] = 1;
	    startPosition[1] = 9;
	    break;
	    
	case 3:
	    startPosition[0] = 1;
	    startPosition[1] = 8;
	    break;

	case 4:
	    startPosition[0] = 0;
	    startPosition[1] = 8;
	    break;

	default:
	    break;
	    
	}

	currentPiece.setPosition(startPosition);
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
