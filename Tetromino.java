public class Tetromino {
    int[][] shape;
    int[][] position;
    int config;

    Tetromino(int shape) {
	this.generator(shape);
	this.config = 0;
    }

    void generator(int shape) {
	
	switch (shape) {
	    
	case 1: this.shape = {
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{1,1,1,1}};
	    
	case 2: this.shape = {
		{2,2},
		{2,2}};
	    
	case 3: this.shape = {
		{0,3,0},
		{3,3,3},
		{0,0,0}};
	    
	case 4: this.shape = {
		{4,0,0},
		{4,4,4},
		{0,0,0}};
	    
	case 5: this.shape = {
		{0,0,5},
		{5,5,5},
		{0,0,0}};
	    
	case 6: this.shape = {
		{0,6,6},
		{6,6,0},
		{0,0,0}};
	    
	case 7: this.shape = {
		{7,7,0},
		{0,7,7},
		{0,0,0}};
	    
	case default: this.shape = {
		{0}};
	    
    }

    void rotateClockwise() {
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
	    this.shape.rotateClockwise();
	}
    }
}
