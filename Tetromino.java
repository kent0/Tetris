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

    int[][] shape;
    int[] position;

    public Tetromino() {
	this.shape = generator();
	this.position = new int[2];
    }

    public int[][] getShape() {
	return this.shape;
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
	
    public void rotateClockwise() {
	int size = this.shape.length;
	int[][] newShape = new int[size][size];
	
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		newShape[i][j] = this.shape[size - 1 - j][i];
	    }
	}

	this.shape = newShape;
    }

    public void rotateCounterClockwise() {
	for (int i = 0; i < 3; i++) {
	    this.rotateClockwise();
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

    public String toString() {
	String description = "";
	
	for (int[] row : this.shape) {
	    for (int element : row) {
		if (element == 0) {
		    description += "  ";
		} else {
		    description += "[]";
		}
	    }
	    description += "\n";
	}
	
	return description;
    }
}
