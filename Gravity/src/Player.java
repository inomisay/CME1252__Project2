import enigma.console.Console;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Player {
	
	// attributes
    private Console console;
	public TextMouseListener tmlis; 
	public KeyListener klis; 
    
    // ------ Standard variables for mouse and keyboard ------
 	public int mousepr;          // mouse pressed?
 	public int mousex, mousey;   // mouse text coordination
 	public int keypr;   // key pressed?
 	public int rkey;    // key   (for press/release)
 	
    // coordination
    private int x;
    private int y;
    
    private boolean Alive = true;

    public static Stack backpack;
	private int teleport;
	private static int score;
	
	private char[][] map;
	
    private Random rnd = new Random();
    // ----------------------------------------------------
	// constructor
	public Player(Console console, char[][] map) {
		
		// ------ Standard code for mouse and keyboard ------ Do not change
		tmlis=new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {}
	         public void mousePressed(TextMouseEvent arg0) {
	            if(mousepr==0) {
	               mousepr=1;
	               mousex=arg0.getX();
	               mousey=arg0.getY();
	            }
	         }
	         public void mouseReleased(TextMouseEvent arg0) {}
	      };
	      console.getTextWindow().addTextMouseListener(tmlis);
	    
	      klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      console.getTextWindow().addKeyListener(klis);
	    // ----------------------------------------------------
	    this.console = console;
	    this.map = map;

	    backpack = new Stack(9);
		teleport = 3;
		score = 0;
		
		// Player's position
		while(true) {
			x = rnd.nextInt(54) + 1;
			y = rnd.nextInt(24) + 1;
			if(map[y][x] == ' ') {
				map[y][x] = 'P'; 
				break; 
			}
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
    public boolean getAlive() {
    	return Alive;
    }
	public Stack getBackpack() {
		return backpack;
	}
	public int getTeleport() {
	    return teleport;
	}
	public int getScore() {
	    return score;
	}
    // ----------------------------------------------------
	// setters
	public void setX(int x) {
	    this.x = x;
	}
	public void setY(int y) {
	    this.y = y;
	}
	public void setBackpack(Stack backpack) {
		Player.backpack = backpack;
	}
	public void setTeleport(int teleport) {
	    this.teleport = teleport;
	}
	public void setScore(int score) {
	    Player.score = score;
	}
    // ----------------------------------------------------
    public void playerMovement() throws InterruptedException {
    	if (keypr == 1) { // if keyboard button pressed
            int newx = x, newy = y;
            if (rkey == KeyEvent.VK_LEFT && x > 1 && map[y][x - 1] != '#' && map[y][x - 1] != 'O') {
                newx--;
                collect(y, x - 1);
                map[y][x - 1] = 'P';
                map[y][x] = ' ';
            }
            if (rkey == KeyEvent.VK_RIGHT && x < 58 && map[y][x + 1] != '#' && map[y][x + 1] != 'O') {
                newx++;
                collect(y, x + 1);
                map[y][x + 1] = 'P';
                map[y][x] = ' ';
            }
            if (rkey == KeyEvent.VK_UP && y > 1 && map[y - 1][x] != '#' && map[y - 1][x] != 'O') {
                newy--;
                collect(y - 1, x);
                map[y - 1][x] = 'P';
                map[y][x] = ' ';
            }
            if (rkey == KeyEvent.VK_DOWN && y < 28 && map[y + 1][x] != '#' && map[y + 1][x] != 'O') {
                if (map[y - 1][x] == 'O')
                {
                    Alive = false;
                }

                newy++;
                collect(y + 1, x);
                map[y + 1][x] = 'P';
                map[y][x] = ' ';
            }

            // push boulder
            if (rkey == KeyEvent.VK_LEFT && x > 1 && map[y][x - 1] == 'O' && map[y][x - 2] == ' ') {
                map[y][x - 2] = 'O';
                newx--;
                map[y][x - 1] = 'P';
                map[y][x] = ' ';
            }
            if (rkey == KeyEvent.VK_RIGHT && x < 58 && map[y][x + 1] == 'O' && map[y][x + 2] == ' ') {
                map[y][x + 2] = 'O';
                newx++;
                map[y][x + 1] = 'P';
                map[y][x] = ' ';
            }
            // check if the new position is within bounds and not a wall
            if (newx >= 1 && newx <= 54 && newy >= 1 && newy <= 23 && map[newy][newx] != '#') {
                // Move player to new position
                x = newx;
                y = newy;
            }
            if (rkey == KeyEvent.VK_SPACE) {
                if (teleport > 0) { // check if player has any teleport left
                    teleport--;
                    int prevX = x;
                    int prevY = y;
                    while (true) {
                        x = rnd.nextInt(54) + 1;
                        y = rnd.nextInt(24) + 1;
                        if (map[y][x] == ' ' || map[y][x] == ':') {
                            map[y][x] = 'P';
                            break;
                        }
                    }
                    map[prevY][prevX] = ' ';
                }
            }
            keypr = 0; // last action
        }
    }
    // ----------------------------------------------------
    public void collect(int y, int x) {
        if (map[y][x] == '1') {
            if (backpack.isFull())
                backpack.pop();
            if (!backpack.isEmpty()) {
                if ((int) backpack.peek() == 1) {
                    backpack.pop();
                    //teleport++;
                    score += 10;
                } else
                    backpack.push(1);
            } else
                backpack.push(1);

            map[y][x] = ' ';

        } else if (map[y][x] == '2') {
            if (backpack.isFull())
                backpack.pop();
            if (!backpack.isEmpty()) {
                if ((int) backpack.peek() == 2) {
                    backpack.pop();
                    score += 40;
                    //teleport++;
                } else
                    backpack.push(2);
            } else
                backpack.push(2);

            map[y][x] = ' ';

        } else if (map[y][x] == '3') {
            if (backpack.isFull())
                backpack.pop();
            if (!backpack.isEmpty()) {
                if ((int) backpack.peek() == 3) {
                    backpack.pop();
                    teleport++;
                    score += 90;
                } else
                    backpack.push(3);
            } else
                backpack.push(3);

            //teleport++;
            map[y][x] = ' ';

        }
    }

}