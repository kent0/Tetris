import java.util.Random;
import java.lang.String;

public class Tetromino {
    final static int[][][] TETROMINOS = {
    {{0}},
    
    {{0,0,0,0},
     {0,0,0,0},
     {1,1,1,1},
     {0,0,0,0}},
     
     {{2,2},
      {2,2}},
     
     {{0,3,0},
      {3,3,3},
      {0,0,0}},
     
     {{4,0,0},
      {4,4,4},
      {0,0,0}},
     
     {{0,0,5},
      {5,5,5},
      {0,0,0}},

     {{0,6,6},
      {6,6,0},
      {0,0,0}},
     
     {{7,7,0},
      {0,7,7},
      {0,0,0}}
};

    private int[][] shape;
    private int[] position;

    public Tetromino() {
	this.shape = generator();
	this.position = new int[2];
    }

    public int[][] getShape() {
	int[][] shapeCopy = new int[this.shape.length][this.shape[0].length];
	for (int i = 0; i < shapeCopy.length; i++) {
	    for (int j = 0; j < shapeCopy[i].length; j++) {
		shapeCopy[i][j] = this.shape[i][j];
	    }
	}
	return shapeCopy;
    }

    private void setShape(int[][] shape) {
	this.shape = shape;
    }

    public int[] getPosition() {
	return this.position;
    }

    public void setPosition(int[] position) {
	if (position.length == 2) {
	    this.position = position;
	}
    }

    private int[][] generator() {
	Random randomGenerator = new Random();
	int randomNumber = randomGenerator.nextInt(7) + 1;
	
        return TETROMINOS[randomNumber];
    }

    public void translate(int[] translation) {
	if (translation.length == 2) {
	    this.setPosition(new int[]{this.getPosition()[0]+translation[0],this.getPosition()[1]+translation[1]});
	}
    }
	
    public void rotate(boolean clockwise) {
	if (clockwise) {
	    int size = this.getShape().length;
	    int[][] newShape = new int[size][size];
	    
	    for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
		    newShape[i][j] = this.getShape()[size - 1 - j][i];
		}
	    }
	    
	    this.setShape(newShape);
	} else {
	    for (int i = 0; i < 3; i++) {
		this.rotate(true);
	    }
	}
	
    }

    public static void test() {
	String testString = "";
	
	for (int i = 0; i < 8; i++) {
	    int[][] testShape = TETROMINOS[i];
	    testString += "Tetromino " + i + ": \n";
	    
	    for (int[] row : testShape) {
		for (int element : row) {
		    if (element == 0) {
			testString += "  ";
		    } else {
			testString += "[]";
		    }
		}
		
		testString += "\n";
	    }
	    
	    testString += "\n";
	}
	
	System.out.println(testString);
    }

    public int[][] configuration() {
	int[][] configuration = new int[4][2];
	int count = 0;
	
	for (int i = 0; i < this.shape.length; i++) {
	    for (int j = 0; j < this.shape[i].length; j++) {
		if (this.getShape()[i][j] != 0) {
		    configuration[count][0] = i;
		    configuration[count++][1] = j;
		}
	    }
	}
	
	return configuration;
    }

    public int type() {
	return this.getShape()[this.configuration()[0][0]][this.configuration()[0][1]];
    }

    public String toString() {
	String description = "";
	
	for (int[] row : this.getShape()) {
	    for (int element : row) {
		if (element == 0) {
		    description += "  ";
		} else {
		    description += "[]";
		}
	    }
	    
	    description += "\n";
	}

	description += "(" + this.getPosition()[0] + ", " + this.getPosition()[1] + ")\n";
	
	return description;
    }
}
