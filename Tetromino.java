import java.util.Random;

public class Tetromino {
    final int[][][] TETROMINOS = {
	{{0}},
	
	{{0,0,0,0},
	 {0,0,0,0},
	 {0,0,0,0},
	 {1,1,1,1}},
	
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
    int[][] position;
    int config;

    public Tetromino() {
	this.shape = generator();
	this.config = 0;
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

    void rotateCounterClockwise() {
	for (int i = 0; i < 3; i++) {
	    this.rotateClockwise();
	}
    }
}
