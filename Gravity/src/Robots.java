import enigma.console.Console;

import java.util.Random;

public class Robots {
	
	// attributes
    private Console console;
    
    // coordination
    private int x;
    private int y;

	private char[][] map;
	
    private Random rnd = new Random();
	// ----------------------------------------------------
    public Robots(int num, char[][] map) {
    	
        this.map = map;

     // Robot's position
        while(num == 1) {
			x = rnd.nextInt(54) + 1;
			y = rnd.nextInt(24) + 1;
			if(map[y][x] == ' ') {
				map[y][x] = 'X';
				break; 
			}
		}
		if (num == 2){
			map[getY()][getX()]= ' ';
		}
    }
    // ----------------------------------------------------
    // getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    // ----------------------------------------------------
	// setters
	public void setX(int x) {
	    this.x = x;
	}
	public void setY(int y) {
	    this.y = y;
	}
	// ----------------------------------------------------
	public void robotMovement() throws InterruptedException {
		
		if(map[y][x] == 'X'){
			int counter = 0; // count the number of invalid moves

			while(counter < 4) { // try 4 times to move in different directions
				// Generate a random direction
				int direction = rnd.nextInt(4);

				// Calculate the new position based on the chosen direction
				int newX = x;
				int newY = y;
				if(direction == 0) { // Move up
					newY = y - 1;
				} else if(direction == 1) { // Move right
					newX = x + 1;
				} else if(direction == 2) { // Move down
					newY = y + 1;
				} else { // Move left
					newX = x - 1;
				}

				// Check if the new position is valid and move the robot if it is
				if(newX >= 0 && newX < map[0].length && newY >= 0 && newY < map.length && map[newY][newX] == ' ' || map[newY][newX] == 'P' || map[newY][newX] == '1'||map[newY][newX] == '2'||map[newY][newX] == '3' ) {
					map[y][x] = ' ';
					x = newX;
					y = newY;
					map[y][x] = 'X';
					return; // exit the robotMovement() method
				} else {
					counter++;
				}
			}
			// If all moves are invalid, do nothing so the program will continued and wont stop working

		}

    }

}

