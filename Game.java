import java.lang.String;

public class Game {
    private int[][] well;
    private Tetromino currentPiece;
    private boolean gameIsOver;

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
	int[][] wellCopy = new int[22][10];

	for (int i = 0; i < wellCopy.length; i++) {
	    for (int j = 0; j < wellCopy[0].length; j++) {
		wellCopy[i][j] = this.well[i][j];
	    }
	}
	
	return wellCopy;
    }

    public void setWell(int[][] well) {
	if (well.length != this.well.length) {
	    return;
	}

	for (int i = 0; i < well.length; i++) {
	    if (well[i].length != this.well[i].length) {
		return;
	    }
	}

	this.well = well;
    }

    private boolean wellConflict() {
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

    private void placeTile(int color) {
	for (int i = 0; i < 4; i++) {
	    int[] target = {this.currentPiece.getPosition()[0]+this.currentPiece.configuration()[i][0],this.currentPiece.getPosition()[1]+this.currentPiece.configuration()[i][1]};
	    if (target[0] >= 0 && target[0] < 22 && target[1] >= 0 && target[1] < 10) {
		this.well[target[0]][target[1]] = color;
	    } 
	}
    }

    private void placeShadow(boolean yes) {
	int[] position = this.currentPiece.getPosition();
	this.drop();
	this.placeTile(yes ? -1 : 0);
	this.currentPiece.setPosition(position);
    }       

    private boolean move(int[] translation, int rotation) {
	if (!(rotation == 0)) {
	    this.placeTile(0);
	    //this.placeShadow(false);
	    this.currentPiece.rotate(rotation == 1);
	    if (this.wellConflict()) {
		if (!this.move(new int[]{0,1},0) && !this.move(new int[]{0,-1},0)) {
		    this.currentPiece.rotate(rotation == -1);
		    //this.placeShadow(true);
		    this.placeTile(this.currentPiece.type());
		    return false;
		}
	    }
	} else {
	    if (translation.length == 2) {
		this.placeTile(0);
		this.currentPiece.translate(translation);
		if (this.wellConflict()) {
		    this.currentPiece.translate(new int[]{-translation[0], -translation[1]});
		    //this.placeShadow(true);
		    this.placeTile(this.currentPiece.type());
		    return false;
		}
	    }
	}
	//this.placeShadow(true);
	this.placeTile(this.currentPiece.type());
	return true;
    }

    private boolean drop() {
	boolean didMove = false;
	while (this.move(new int[]{1,0},0)) {
	    didMove = true;
	}
	return didMove;
    }

    public boolean up() {
	boolean didMove = this.drop();
	this.tick();
	return didMove;
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

    public boolean clockTurn() {
	return this.move(new int[]{0,0},1);
    }

    public boolean counterTurn() {
	return this.move(new int[]{0,0},-1);
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

	if (this.wellConflict()) {
	    this.gameIsOver = true;
	} else {
	    this.placeTile(this.currentPiece.type());
	}
    }

    private void clean() {
	search:
	for (int i = 2; i < 22; i++) {
	    for (int j = 0; j < 10; j++) {
		if (this.well[i][j] == 0) {
		    continue search;
		}
	    }
	    
	    for (int j = i - 1; j > 0; j--) {
		for (int k = 0; k < 10; k++) {
		    this.well[j+1][k] = this.well[j][k];
		}
	    }						    
	}
    }

    public void tick() {
	if (!this.gameIsOver) {
	    if (!this.down()) {
		this.clean();
		this.nextPiece();
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
