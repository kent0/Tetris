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

	for (int i = 1; i < this.tetrominos.length - 1; i++) {
	    this.tetrominos[i] = new Tetromino();
	}
	
	this.tetrominos[this.tetrominos.length - 1] = Tetromino.empty();
	this.nextPiece();

	this.line = this.score = 0;
	this.gameIsOver = this.holdSwitch = false;
    }

    public int[][] getWell() {
	return this.well;
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
	return this.line / 10;
    }

    public boolean getGameIsOver() {
	return this.gameIsOver;
    }

    private int[][] target() {
	int[][] target = new int[4][2];
	for (int i = 0; i < 4; i++) {
	    target[i] = new int[]{tetrominos[0].getPosition()[0] +
			     tetrominos[0].configuration()[i][0],
			     tetrominos[0].getPosition()[1] +
			     tetrominos[0].configuration()[i][1]};
	}
	
	return target;
    }

    private boolean wellConflict() {
	for (int i = 0; i < 4; i++) {
	    if (target()[i][0] < 0 || target()[i][0] > 21 ||
		target()[i][1] < 0 || target()[i][1] > 9 ||
		well[target()[i][0]][target()[i][1]] > 0) {
		return true;
	    }
	}

	return false;
    }

    private void placeTile(int color) {
	for (int i = 0; i < 4; i++) {
	    if (target()[i][0] >= 0 && target()[i][0] < 22 &&
		target()[i][1] >= 0 && target()[i][1] < 10) {
		well[target()[i][0]][target()[i][1]] = color;
	    } 
	}
    }

    private void placeShadow(boolean yes) {
	int tempScore = score;
	int[] tempPosition = tetrominos[0].getPosition();
	drop();
	placeTile(yes ? -1 : 0);
	tetrominos[0].setPosition(tempPosition);
	score = tempScore;
    }       

    private boolean move(int[] translation, int rotation) {
	if (!(rotation == 0)) {
	    tetrominos[0].rotate(rotation == 1);
	    
	    if (wellConflict()) {
		if (tetrominos[0].type() == 1 &&
		    !move(new int[]{0,1},0) &&
		    !move(new int[]{0,2},0) &&
		    !move(new int[]{0,-1},0) &&
		    !move(new int[]{0,-2},0)) {
		    return false;
		} else {
		    if (!move(new int[]{0,1},0) &&
			!move(new int[]{0,-1},0)) {
			tetrominos[0].rotate(rotation == -1);

			return false;
		    }
		}
	    }
	} else {
	    if (translation.length == 2) {
		tetrominos[0].translate(translation);
		if (wellConflict()) {
		    tetrominos[0].translate(new int[]{-translation[0],
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
	placeTile(tetrominos[0].type());
	return didMove;
    }

    private boolean drop() {
	boolean didMove = false;
	
	while (move(new int[]{1,0},0)) {
	    score += 2;
	    didMove = true;
	}
	
	return didMove;
    }

    public void up() {
	placeTile(0);
	drop();
	placeTile(tetrominos[0].type());
	tick();
    }

    public boolean down() {
	if (cleanMovePlace(new int[]{1,0},0)) {
	    score += 1;
	    return true;
	}
	
	return false;
    }

    public void right() {
	cleanMovePlace(new int[]{0,1},0);
    }

    public void left() {
	cleanMovePlace(new int[]{0,-1},0);
    }

    public void clockTurn() {
	cleanMovePlace(new int[]{0,0},1);
    }

    public void counterTurn() {
	cleanMovePlace(new int[]{0,0},-1);
    }

    public void hold() {
	if (!holdSwitch) {
	    placeTile(0);
	    placeShadow(false);
	    Tetromino temp = tetrominos[0];
	    temp.resetShape();
	    
	    if (tetrominos[tetrominos.length-1].type() == 0) {
		nextPiece();
		tetrominos[tetrominos.length-1] = temp;
	    } else {
		tetrominos[0] = tetrominos[tetrominos.length-1];
		tetrominos[0].setPosition(startPosition(tetrominos[0]));
		tetrominos[tetrominos.length-1] = temp;
	    }
	    
	    placeShadow(true);
	    placeTile(tetrominos[0].type());
	    holdSwitch = true;
	}
    }

    private void nextPiece() {
	for (int i = 0; i < tetrominos.length - 2; i++) {
	    tetrominos[i] = tetrominos[i+1];
	}

	tetrominos[tetrominos.length - 2] = new Tetromino();	
	tetrominos[0].setPosition(startPosition(tetrominos[0]));

	if (wellConflict()) {
	    gameIsOver = true;
	} else {
	    placeShadow(true);
	    placeTile(tetrominos[0].type());
	}

	holdSwitch = false;
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
		if (well[i][j] == 0) {
		    continue search;
		}
	    }
	    
	    for (int j = i - 1; j > 0; j--) {
		for (int k = 0; k < 10; k++) {
		    well[j+1][k] = well[j][k];
		}
	    }

	    removedLines++;
	}

	switch (removedLines) {

	case 1:
	    score += 100 * (level() + 1);
	    break;
	    
	case 2:
	    score += 300 * (level() + 1);
	    break;

	case 3:
	    score += 500 * (level() + 1);
	    break;

	case 4:
	    score += 800 * (level() + 1);
	    break;

	default:
	    break;
	}

	line += removedLines;
    }

    public void tick() {
	if (!gameIsOver) {
	    if (!cleanMovePlace(new int[]{1,0},0)) {
		clean();
		nextPiece();
	    }
	} else {
	    for (int i = 0; i < 5; i++) {
		tetrominos[i] = Tetromino.empty();
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
	    description += tetrominos[i];
	}

	return description;
    }
}
