import java.lang.String;

public class Game {
    int[][] well;
    Tetromino currentPiece;
    boolean gameIsOver;

    public Game() {
	this.gameIsOver = false;
	this.well = new int[22][10];
	
	for (int i = 0; i < well.length; i++) {
	    for (int j = 0; j < well[0].length; j++) {
		this.well[i][j] = 0;
	    }
	}

	this.nextPiece();
    }

    public int[][] getWell() {
	return this.well;
    }

    public boolean wellConflict() {
	
	for (int i = 0; i < 4; i++) {
	    int[] target = new int[2];
	    target[0] = this.currentPiece.getPosition()[0] + this.currentPiece.configuration()[i][0];
	    target[1] = this.currentPiece.getPosition()[1] + this.currentPiece.configuration()[i][1];
	    if (target[0] < 0 || target[0] > 21 || target[1] < 0 || target[1] > 9 || well[target[0]][target[1]] != 0) {
		return true;
	    }
	}

	return false;
    }

    public void placePiece(boolean yes) {
	for (int i = 0; i < 4; i++) {
	    this.well[this.currentPiece.getPosition()[0]+this.currentPiece.configuration()[i][0]][this.currentPiece.getPosition()[1]+this.currentPiece.configuration()[i][1]] = yes ? 1 : 0;
	}
    }

    // movements
    public boolean move(int[] translation, int rotation) {
	if (!(rotation == 0)) {
	    this.placePiece(false);
	    this.currentPiece.rotate(rotation == 1);
	    if (this.wellConflict()) {
		if (!this.move(new int[]{0,1},0) && !this.move(new int[]{0,-1},0)) {
		    this.currentPiece.rotate(rotation == -1);
		    this.placePiece(true);
		    return false;
		}
	    }
	} else {
	    if (translation.length == 2) {
		this.placePiece(false);
		this.currentPiece.translate(translation);
		if (this.wellConflict()) {
		    this.currentPiece.translate(new int[]{-translation[0], -translation[1]});
		    this.placePiece(true);
		    return false;
		}
	    }
	}
	this.placePiece(true);
	return true;
    }

    public boolean up() {
	return this.move(new int[]{-1,0},0);
    }

    public boolean down() {
	return this.move(new int[]{1,0},0);
    }

    public boolean right() {
	return this.move(new int[]{0,1},0);
    }

    public boolean left() {
	return this.move(new int[]{0,-1},0);
    }

    private void nextPiece() {
	this.currentPiece = new Tetromino();

	int[] startPosition = {0,0};
	
	switch (currentPiece.getShape().length) {
	    
	case 2:
	    startPosition[0] = 1;
	    startPosition[1] = 4;
	    break;
	    
	case 3:
	    startPosition[0] = 1;
	    startPosition[1] = 3;
	    break;

	case 4:
	    startPosition[0] = 0;
	    startPosition[1] = 3;
	    break;

	default:
	    break;   
	}

	currentPiece.setPosition(startPosition);

	this.gameIsOver = this.wellConflict() ? true : false;
	this.placePiece(!this.gameIsOver);
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
