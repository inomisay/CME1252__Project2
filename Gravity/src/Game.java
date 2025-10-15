import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.Color;
import java.util.Random;

public class Game {
	
	// Attributes
	public static char[][] map = new char[25][55];
	private Player player;
	private int time;
	private Status status;
	private CircularQueue input = new CircularQueue(150);
	
	private Random rnd = new Random();

	public enigma.console.Console cn = Enigma.getConsole("Gravity", 100, 40, 15); 
	public enigma.console.TextWindow cnt = cn.getTextWindow();
	// ----------------------------------------------------
	public Game() throws Exception {   // --- Constructor
		//Game
	  	cn.getTextWindow().setCursorPosition(7, 2);
		cn.getTextWindow().output("			Welcome to the Gravity Game");
	  	cn.getTextWindow().setCursorPosition(7, 3);
		cn.getTextWindow().output("			---------------------------");
		// ----------------------------------------------------
	  	cn.getTextWindow().setCursorPosition(6, 6);
	  	
		// initialization
		for(int y = 0; y < 25; y++) {    // walls
		    for(int x = 0; x < 55; x++) {
		        map[y][x]=' ';
		        if(x == 0 || x == 54 || y == 0 || y == 24) {
		            map[y][x] = '#';
		        }
		        if(y == 8 && x < 50) {
		            map[y][x] ='#';
		        }
		        if(y == 16 && x > 4) {
		            map[y][x] ='#';
		        }
		    }
		}       

		for(int y = 1; y < 24; y++) {    // earth
		    for(int x = 1; x < 54; x++) {
		        if(map[y][x] == ' ') {
		            map[y][x] = ':';
		        }
		    }
		}

		for (int i = 0; i < 200; i++) {   // boulder
		    while (true) {
		        int x = rnd.nextInt(54) + 1; 
		        int y = rnd.nextInt(24) + 1;
		        if (map[y][x] == ':') { 
		            map[y][x] = 'O';
		            break;
		        }
		    }
		}

		for(int i = 0; i < 30; i++) {   // 1,2,3
		    while(true) {
		        int x = rnd.nextInt(54) + 1;
		        int y = rnd.nextInt(24) + 1;
		        int e = rnd.nextInt(3) + 1;
		        if(map[y][x] == ':') { 
		            map[y][x] = (char)(e + 48); 
		            break; 
		        }
		    }
		}

		for (int i = 0; i < 200; i++) {   // empty
		    while (true) {
		        int x = rnd.nextInt(54) + 1; 
		        int y = rnd.nextInt(24) + 1; 
		        if (map[y][x] == ':') { 
                    cnt.output(x + 6, y + 6, ' ');
		            map[y][x] = ' ';
		            break;
		        }
		    }
		}
		// ----------------------------------------------------
		player = new Player(cn, map); // player
		
		int numberofrobots =7;
		Robots[] robots = new Robots[100];
		int[][] robots1 = new int[100][100];
		
		for(int i = 0; i < numberofrobots; i++) { // robots
            robots[i] = new Robots(1, map);
			robots1[i][0] = i;
			robots1[i][1]= robots[i].getX();
			robots1[i][2]= robots[i].getY();
        }

		status = new Status(cn, player, player, player, this); // status 		
		
		// input
		for (int i = 0; i < 15; i++) {
			int inp = rnd.nextInt(40) + 1;
			if (inp <= 6) {
				input.enqueue('1');
			}
			else if (inp > 6 && inp <= 11) {
				input.enqueue('2');
			}
			else if (inp > 11 && inp <= 15) {
				input.enqueue('3');
			}
			else if (inp > 15 && inp <= 16) {
				input.enqueue('X');
			}
			else if (inp > 16 && inp <= 26) {
				input.enqueue('O');
			}
			else if (inp > 26 && inp <= 35) {
				input.enqueue(':');
			}
			else if (inp > 35 && inp <= 40) {
				input.enqueue('e');
			}
		}	
		
		Boolean gameBegin = true;
		Boolean isAlive = true;
		time = 0;
		// ----------------------------------------------------
		while(isAlive) {
			
			//---INPUT---
			if(time % 12 == 0)
			{
				boolean isdone = false;
				boolean generate = false;
				boolean delete = false;

				while(isdone == false)
				{
					int x = rnd.nextInt(54) + 1;
					int y = rnd.nextInt(24) + 1;

					if((char)input.peek() != ':' || (char)input.peek() != ' ' && (char)input.peek() != 'O')
					{
						while(map[y][x] == ' ' || map[y][x] == ':')
						{
							if((char)input.peek()=='e'){
								input.dequeue();
								map[y][x] = ' ';
								generate = true;
								break;
							}
							else if((char)input.peek()!='X'){
								map[y][x] = (char)input.dequeue();
								generate = true;
								break;
							} else if ((char)input.peek()=='X') {
								numberofrobots++;
								robots[numberofrobots] = new Robots(1, map);
								generate = true;
								break;


							}

						}

					}
					else if((char)input.peek() == 'O')
					{
						while(map[y][x] == ' ' || map[y][x] == ':')
						{
							map[y][x] = (char)input.dequeue();
							generate = true;
							delete = true;
							break;
						}


						if(delete == true)
						{
							x = rnd.nextInt(54) + 1;
							y = rnd.nextInt(24) + 1;

							while(map[y][x] == 'O')
							{
								map[y][x] = ':';
								break;
							}
							delete = false;
						}

					}
					else if((char)input.peek() == ':')
					{
						while(map[y][x] == ' ')
						{
							map[y][x] = (char)input.dequeue();
							generate = true;
							break;
						}
						x = rnd.nextInt(54) + 1;
						y = rnd.nextInt(24) + 1;
					}
					else if((char)input.peek() == ' ')
					{
						while(map[y][x] == ':')
						{
							map[y][x] = (char)input.dequeue();
							generate = true;
							break;
						}

					}

					if(generate == true)
					{
						int inp = rnd.nextInt(40) + 1;

						if (inp <= 6) {
							input.enqueue('1');
						}
						else if (inp > 6 && inp <= 11) {
							input.enqueue('2');
						}
						else if (inp > 11 && inp <= 15) {
							input.enqueue('3');
						}
						else if (inp > 15 && inp <= 16) {
							input.enqueue('X');
						}
						else if (inp > 16 && inp <= 26) {
							input.enqueue('O');
						}
						else if (inp > 26 && inp <= 35) {
							input.enqueue(':');
						}
						else if (inp > 35 && inp <= 40) {
							input.enqueue('e');
						}
					}
					break;
				}
				cnt.setCursorPosition(64, 8);
				for(int i = 0; i<15;i++)
				{
					System.out.print(input.peek());
					input.enqueue(input.dequeue());
				}
			}
			
			//---FALL---
			for (int i = 0; i < 55; i++)
			{
				for (int j = 0; j < 25; j++)
				{
					if(map[j][i] == 'O' && map[j+1][i] != '#' && map[j+1][i] != ':')
					{
						if(map[j+1][i] == ' ' || map[j+1][i] == '1' || map[j+1][i] == '2' || map[j+1][i] == '3')
						{
							map[j+1][i] = 'O';
							map[j][i] = ' ';
							break;
						}
						else if(map[j+1][i] == 'O')
						{
							int a = rnd.nextInt(2);

							if(a == 1)
							{
								if(map[j+1][i+1] == ' ' || map[j+1][i+1] == '1' || map[j+1][i+1] == '2' || map[j+1][i+1] == '3')
								{
									map[j][i] = ' ';
									map[j+1][i+1] = 'O';
									break;
								}
							}
							if(a == 0)
							{
								if(map[j+1][i-1] == ' ' || map[j+1][i-1] == '1' || map[j+1][i-1] == '2' || map[j+1][i-1] == '3')
								{
									map[j][i] = ' ';
									map[j+1][i-1] = 'O';
									break;
								}
							}

						}

						else if(map[j+1][i] == 'X')
						{
							map[j+1][i] = 'O';
							map[j][i] = ' ';
							player.setScore(player.getScore()+ 900);
							for(int g =0; g< numberofrobots;g++){
								if(robots1[g][1] == i&&robots1[g][2]==j+1){
									robots[g]= new Robots(2,map);
									cnt.setCursorPosition(60, 60);
								}
							}
							break;
						}
					}
				}
			}

			if(player.getAlive()== false)
			{
				isAlive = false;
				cnt.setCursorPosition(7, 32);
				System.out.print("GAME OVER!");
			}
			
			status.showStatus();
			
			player.playerMovement();
			for(int i = 0; i < numberofrobots; i++) {
				if(robots[i]!=null){
					robots[i].robotMovement();
				}
            }

			// map
			for(int i = 0; i < 25; i++){
			    for(int j = 0; j < 55; j++){
			        cnt.setCursorPosition(j+6, i+6);
			        char c = map[i][j];
			        if(c == ':') { // earth
			            cnt.output(c, new TextAttributes(Color.GREEN.darker()));
			        } else if(c == 'O') { // boulder
			            cnt.output(c, new TextAttributes(Color.GRAY));
			        } else if(c >= '1' && c <= '3') { // 1, 2, 3
			            cnt.output(c, new TextAttributes(Color.PINK));
			        }else if(c == 'P') { // player
				            cnt.output(c, new TextAttributes(Color.YELLOW));
			        }else if(c == 'X') { // robot
			            cnt.output(c, new TextAttributes(Color.RED));
			        } else {
			            System.out.print(c);
			        }
			    }
			}
			
			//Press Enter to start the game
			if(gameBegin) {
				cn.getTextWindow().setCursorPosition(23, 4);
				cnt.output("PRESS ENTER TO START");		
				System.in.read();
				cn.getTextWindow().setCursorPosition(23, 4);
				cnt.output("                             ");
				gameBegin = false;
			}
			
			// Game Over...
			for(int i = 0; i < numberofrobots; i++) {
				if(robots[i] != null && map[robots[i].getY()][robots[i].getX()] == 'X' && map[player.getY()][player.getX()] == map[robots[i].getY()][robots[i].getX()]) {
					isAlive = false;
			        cnt.setCursorPosition(player.getX() + 6, player.getY() + 6);
					cnt.output('?', new TextAttributes(Color.RED));
			        cnt.setCursorPosition(7, 32);
					System.out.print("GAME OVER!");
				}
            }
			
			Thread.sleep(250);
			time += 1;
		}
	}

	// getters
	public int getTime() {
	    return time;
	}
	
	// setters
	public void setTime(int time) {
	    this.time = time;
	}
}
