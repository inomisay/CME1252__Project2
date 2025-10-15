import enigma.console.Console;

public class Status {
	
	// attributes
    private Console console;
    public Player backpack = this.getBackpack();
	private Player teleport;
	private Player score;
	private Game time;
	private static Stack temp;
	
	public Status(Console console, Player backpack, Player teleport, Player score, Game time) {
	    this.console = console;	  
	    this.backpack = backpack;
	    this.teleport = teleport;
	    this.score = score;
	    this.time = time;
	}
	
	public void showStatus() {
		
		temp = new Stack(8);
		
		console.getTextWindow().setCursorPosition(64, 6);
	  	console.getTextWindow().output("Input");
	  	console.getTextWindow().setCursorPosition(64, 7);
	  	console.getTextWindow().output("<<<<<<<<<<<<<<<");
	  	console.getTextWindow().setCursorPosition(64, 8);
	  	// input in game class
	  	console.getTextWindow().setCursorPosition(64, 9);
	  	console.getTextWindow().output("<<<<<<<<<<<<<<<");
	  	
	  	// backpack print part
        int y = 12;
	  	for(int i = 0; i < 8; i++) {
            console.getTextWindow().setCursorPosition(64, y);
            if(!Player.backpack.isEmpty() && y+Player.backpack.size()>=20) {
            	console.getTextWindow().output("     | " + Player.backpack.peek()+ " |");
            	temp.push(Player.backpack.pop());
            } else
            	console.getTextWindow().output("     | " + "" + "  |");
            y++;
	  	}
	  	while(!temp.isEmpty())
        	Player.backpack.push(temp.pop());
	  	
	    console.getTextWindow().setCursorPosition(64, 20);
  	  	console.getTextWindow().output("	 +---+");
	    console.getTextWindow().setCursorPosition(64, 21);
  	  	console.getTextWindow().output("	Backpack");
	    console.getTextWindow().setCursorPosition(64, 25);
  	  	console.getTextWindow().output("Teleport	: " + teleport.getTeleport());
  	  	console.getTextWindow().setCursorPosition(64, 27);
  	  	console.getTextWindow().output("Score	   : " + score.getScore());
  	  	console.getTextWindow().setCursorPosition(64, 29);
	  	console.getTextWindow().output("Time		: " + (int)time.getTime());
	}
	
	// getters
	public Player getBackpack() {
		return backpack;
	}
	public Player getTeleport() {
		return teleport;
	}
	public Player getScore() {
		return score;
	}
    public Game getTime() {
        return time;
    }
}
