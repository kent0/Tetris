import java.lang.String;

public class Game {
    int[][] well;
    Tetromino currentPiece;
    boolean gameIsOver;

    public Game() {
	this.newPiece();
	this.gameIsOver = false;
	this.well = new int[22][10];
	
	for (int i = 0; i < well.length; i++) {
	    for (int j = 0; j < well[0].length; j++) {
		this.well[i][j] = 0;
	    }
	}
    }

    public int[][] getWell() {
	return this.well;
    }

    public boolean checkConflict() {
	
	for (int i = 0; i < 4; i++) {
	    int[] target = new int[2];
	    target[0] = this.currentPiece.getPosition()[0] + this.currentPiece.configuration()[i][0];
	    target[1] = this.currentPiece.getPosition()[1] + this.currentPiece.configuration()[i][1];
	    if (target[0] < 0 || target[0] > 21 || target[1] < 0 || target[1] > 9 || well[target[0]][target[1]] != 0) {
		return false;
	    }
	}

	return true;
    }

    private void newPiece() {
	this.currentPiece = new Tetromino();

	int[] startPosition = {0,0};
	
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
