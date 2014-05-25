import java.lang.String;

public class Game {
    private int[][] well;
    private Tetromino[] tetrominos;
    private int line, score;
    private boolean gameIsOver, holdSwitch;

    public Game() {
	this.well = new int[22][10];
	
	for (int i = 0; i < well.length; i++) {
	    for (int j = 0; j < well[0].length; j++) {
		this.well[i][j] = 0;
	    }
	}

	this.tetrominos = new Tetromino[5];

	for (int i = 1; i < this.tetrominos.length-1; i++) {
	    this.tetrominos[i] = new Tetromino();
	}
	
	this.tetrominos[this.tetrominos.length-1] = Tetromino.empty();
	this.nextPiece();

	this.line = this.score = 0;
	this.gameIsOver = this.holdSwitch = false;
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

    public Tetromino[] getTetrominos() {
	return this.tetrominos;
    }

    public int getLine() {
	return this.line;
    }

    public int getScore() {
	return this.score;
    }

    public int level() {
	return line / 10;
    }

    public boolean gameIsOver() {
	return this.gameIsOver;
    }

    private boolean wellConflict() {
	for (int i = 0; i < 4; i++) {
	    int[] target = new int[2];
	    target[0] = this.tetrominos[0].getPosition()[0] +
		this.tetrominos[0].configuration()[i][0];
	    target[1] = this.tetrominos[0].getPosition()[1] +
		this.tetrominos[0].configuration()[i][1];
	    if (target[0] < 0 || target[0] > 21 || target[1] < 0 ||
		target[1] > 9 || well[target[0]][target[1]] > 0) {
		System.out.println("Conflict @ (" + target[0] + ", " +
				   target[1] + ")");
		return true;
	    }
	}

	return false;
    }

    private void placeTile(int color) {
	for (int i = 0; i < 4; i++) {
	    int[] target = {this.tetrominos[0].getPosition()[0] +
			    this.tetrominos[0].configuration()[i][0],
			    this.tetrominos[0].getPosition()[1] +
			    this.tetrominos[0].configuration()[i][1]};
	    
	    if (target[0] >= 0 && target[0] < 22 && target[1] >= 0 &&
		target[1] < 10) {
		this.well[target[0]][target[1]] = color;
	    } 
	}
    }

    private void placeShadow(boolean yes) {
	int tempScore = score;
	int[] position = this.tetrominos[0].getPosition();
	this.drop();
	this.placeTile(yes ? -1 : 0);
	this.tetrominos[0].setPosition(position);
	score = tempScore;
    }       

    private boolean move(int[] translation, int rotation) {
	if (!(rotation == 0)) {
	    this.tetrominos[0].rotate(rotation == 1);
	    
	    if (this.wellConflict()) {
		if (this.tetrominos[0].type() == 4 &&
		    !this.move(new int[]{0,1},0) &&
		    !this.move(new int[]{0,2},0) &&
		    !this.move(new int[]{0,-1},0) &&
		    !this.move(new int[]{0,-2},0)) {
		    return false;
		} else {
		    if (!this.move(new int[]{0,1},0) &&
			!this.move(new int[]{0,-1},0)) {
			this.tetrominos[0].rotate(rotation == -1);
			return false;
		    }
		}
	    }
	} else {
	    if (translation.length == 2) {
		this.tetrominos[0].translate(translation);
		if (this.wellConflict()) {
		    System.out.println(this.tetrominos[0]);
		    this.tetrominos[0].translate(new int[]{-translation[0],
							   -translation[1]});
		    return false;
		}
	    }
	}
	return true;
    }

    private boolean cleanMovePlace(int[] translation, int rotation) {
	boolean didMove;
	placeTile(0);
	placeShadow(false);
	didMove = move(translation, rotation);
	placeShadow(true);
	placeTile(this.tetrominos[0].type());
	return didMove;
    }

    private boolean drop() {
	boolean didMove = false;
	while (this.move(new int[]{1,0},0)) {
	    this.score += 2;
	    didMove = true;
	}
	return didMove;
    }

    public void up() {
	placeTile(0);
	this.drop();
	placeTile(this.tetrominos[0].type());
	this.tick();
    }

    public boolean down() {
	if (this.cleanMovePlace(new int[]{1,0},0)) {
	    this.score += 1;
	    return true;
	} else {
	    return false;
	}
    }

    public void right() {
	this.cleanMovePlace(new int[]{0,1},0);
    }

    public void left() {
	this.cleanMovePlace(new int[]{0,-1},0);
    }

    public void clockTurn() {
	this.cleanMovePlace(new int[]{0,0},1);
    }

    public void counterTurn() {
	this.cleanMovePlace(new int[]{0,0},-1);
    }

    public void hold() {
	if (!this.holdSwitch) {
	    this.placeTile(0);
	    this.placeShadow(false);
	    Tetromino temp = this.tetrominos[0];
	    temp.resetShape();
	    
	    if (this.tetrominos[this.tetrominos.length-1].type() == 0) {
		this.nextPiece();
		this.tetrominos[this.tetrominos.length-1] = temp;
	    } else {
		this.tetrominos[0] =
		    this.tetrominos[this.tetrominos.length-1];
		this.tetrominos[0].resetShape();
		this.tetrominos[0].setPosition(this.startPosition(tetrominos[0]));
		this.tetrominos[this.tetrominos.length-1] = temp;
	    }
	    
	    this.placeShadow(true);
	    this.placeTile(this.tetrominos[0].type());
	    this.holdSwitch = true;
	}
    }

    private void nextPiece() {
	for (int i = 0; i < this.tetrominos.length - 2; i++) {
	    this.tetrominos[i] = this.tetrominos[i+1];
	}

	this.tetrominos[this.tetrominos.length - 2] = new Tetromino();	
	this.tetrominos[0].setPosition(this.startPosition(tetrominos[0]));

	if (this.wellConflict()) {
	    this.gameIsOver = true;
	} else {
	    this.placeShadow(true);
	    this.placeTile(this.tetrominos[0].type());
	}

	this.holdSwitch = false;
    }

    private int[] startPosition(Tetromino tetromino) {
	int[] startPosition = {0,0};
	
	switch (tetrominos[0].getShape().length) {
	    
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

	return startPosition;
    }

    private void clean() {
	int removedLines = 0;
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

	    removedLines++;
	}

	switch (removedLines) {

	case 1:
	    this.score += 100 * (this.level() + 1);
	    break;
	    
	case 2:
	    this.score += 300 * (this.level() + 1);
	    break;

	case 3:
	    this.score += 500 * (this.level() + 1);
	    break;

	case 4:
	    this.score += 800 * (this.level() + 1);
	    break;

	default:
	    break;
	}

	this.line += removedLines;
    }

    public void tick() {
	if (!this.gameIsOver) {
	    if (!this.cleanMovePlace(new int[]{1,0},0)) {
		this.clean();
		this.nextPiece();
	    }
	} else {
	    for (int i = 0; i < 5; i++) {
		this.tetrominos[i] = Tetromino.empty();
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

	for (int i = 0; i < 5; i++) {
	    description += this.tetrominos[i];
	}

	return description;
    }
}
