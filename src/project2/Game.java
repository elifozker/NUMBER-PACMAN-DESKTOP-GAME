package project2;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {
	public static enigma.console.Console cn = Enigma.getConsole("Maze Game",75,30,15);
	
	public TextMouseListener tmlis;
	static TextAttributes red = new TextAttributes(Color.RED, Color.BLACK);
	static TextAttributes yellow = new TextAttributes(Color.YELLOW, Color.BLACK);
	static TextAttributes green = new TextAttributes(Color.GREEN, Color.BLACK);
	static TextAttributes white = new TextAttributes(Color.WHITE, Color.BLACK);
	static TextAttributes blue = new TextAttributes(Color.BLUE, Color.BLACK);
	public KeyListener klis;
	public int keypr;
	public int rkey;
	public int time;
	public static String human;
	public static int ate;
	public static int matched;
	public static boolean gamecontrol = true;
	public static int humannum = 5;


	Game() throws Exception {

		maze maze = new maze();
		Input input = new Input();
		PathFinding pf = new PathFinding();
       // maze.Menu();
		//maze.clearScreen();
		Game.cn.getTextWindow().setCursorPosition(0,0);		
		maze.print_maze();
		input.CreatingQueue();
		//input.First25();
		input.PrintingInput();
		input.SettleInput();
		backpack bp = new backpack();
		ate = 0;
		matched = 0;
		
		input.randm();
		pf.redNumsRnd();

		human = "H";
		
		time = 1;
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);

		int px = 7, py = 3;
		int timecount = 0;
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		boolean atecontrol = false;
		cn.getTextWindow().setCursorPosition(px, py);
		cn.setTextAttributes(blue);
		project2.maze.setMap2(human, px, py);
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 55; j++) {
				if (project2.maze.getMap()[i][j].equals(human)) {
					System.out.println(humannum);
				}
			}
		}
		MppE[][] allPath = PathFinding.PFinding(pf.redNumsRnd());
		PathFinding.PathDrawing(allPath);
		int mCount = 1;
		while (gamecontrol) {    //main game loop
			PathFinding.PathDrawing(allPath);
			PathFinding.RedMove(allPath, mCount);
			gamecontrol=false;
			for(int i=0;i<23;i++) {
				for(int j=0;j<55;j++) {
					if(project2.maze.getMap()[i][j].equals("H")) {
						gamecontrol=true;
					}
				}
			}
			if(gamecontrol==false) {
				GameOver(px,py);
			}
			mCount++;
			if (mCount == 11) {
				maze.updateMap();
				allPath = PathFinding.PFinding(pf.redNumsRnd());
				PathFinding.PathDrawing(allPath);
				mCount = 1;
			}
			cn.setTextAttributes(white);
			bp.print_left_b();
			bp.print_right_b();
			bp.printScore();
			matched = 0;
			input.randMove(input.randm());
			String[][] map = project2.maze.getMap();
			cn.getTextWindow().setCursorPosition(65, 23);
			cn.setTextAttributes(white);
			System.out.println(time);
			if (humannum == 9) {
				humannum = 1;
				project2.maze.setMap(map);
				timecount = time;

			}
			if (humannum == 1 && time - timecount < 8) {
				keypr = 0;
			}

			if (keypr == 1) { // if keyboard button pressed

				if (humannum == 1)
					humannum += 1;
				if (rkey == KeyEvent.VK_Q) {
					bp.left_to_right();
				}
				if (rkey == KeyEvent.VK_W) {
					bp.right_to_left();
				}
				if (rkey == KeyEvent.VK_LEFT && px > 1 && !(map[py][px - 1].equals("#"))) {
					map[py][px] = " ";
					if (!(map[py][px - 1].equals(" "))) {
						if (Integer.valueOf(map[py][px - 1]) <= humannum) {
							humannum += 1;
							ate = Integer.valueOf(map[py][px - 1]);
							bp.pushAte();
							atecontrol = true;

						} else if (Integer.valueOf(map[py][px - 1]) > humannum) {
							GameOver(px, py);
							break;
						}
					}

					px--;
					left = true;
					map[py][px] = human;
					atecontrol = false;
					project2.maze.setMap(map);

				}
				if (rkey == KeyEvent.VK_RIGHT && px < 55 && !(map[py][px + 1].equals("#"))) {
					map[py][px] = " ";
					if (!(map[py][px + 1].equals(" "))) {
						if (Integer.valueOf(map[py][px + 1]) <= humannum) {
							humannum += 1;
							ate = Integer.valueOf(map[py][px + 1]);
							bp.pushAte();
							atecontrol = true;
						} else if (Integer.valueOf(map[py][px + 1]) > humannum) {
							GameOver(px, py);
							break;
						}

					}
					px++;
					right = true;
					map[py][px] = human;
					atecontrol = false;
					project2.maze.setMap(map);
				}
				if (rkey == KeyEvent.VK_UP && py > 1 && !(map[py - 1][px].equals("#"))) {
					map[py][px] = " ";
					if (!(map[py - 1][px].equals(" "))) {
						if (Integer.valueOf(map[py - 1][px]) <= humannum) {
							humannum += 1;
							ate = Integer.valueOf(map[py - 1][px]);
							bp.pushAte();
							atecontrol = true;
						} else if (Integer.valueOf(map[py - 1][px]) > humannum) {
							GameOver(px, py);
							break;
						}

					}
					py--;
					up = true;
					map[py][px] = human;
					atecontrol = false;
					project2.maze.setMap(map);

				}
				if (rkey == KeyEvent.VK_DOWN && py < 22 && !(map[py + 1][px].equals("#"))) {
					map[py][px] = " ";
					if (!(map[py + 1][px].equals(" "))) {
						if (Integer.valueOf(map[py + 1][px]) <= humannum) {
							humannum += 1;
							ate = Integer.valueOf(map[py + 1][px]);
							bp.pushAte();
							atecontrol = true;
						} else if (Integer.valueOf(map[py + 1][px]) > humannum) {
							GameOver(px, py);
							break;
						}

					}
					py++;
					down = true;
					map[py][px] = human;
					atecontrol = false;
					project2.maze.setMap(map);

				}
				char rckey = (char) rkey;
				cn.setTextAttributes(blue);
				if (left == true && rckey == '%') {
					cn.getTextWindow().output(px + 1, py, ' ');
					project2.maze.setMap2(" ", px + 1, py);
					project2.maze.setMap2(human, px, py);
					allPath = PathFinding.updatePath(allPath, px + 1, py);
					left = false;
				}
				if (right == true && rckey == '\'') {
					cn.getTextWindow().output(px - 1, py, ' ');
					project2.maze.setMap2(" ", px - 1, py);
					project2.maze.setMap2(human, px, py);
					allPath = PathFinding.updatePath(allPath, px - 1, py);
					right = false;
				}
				if (up == true && rckey == '&') {
					cn.getTextWindow().output(px, py + 1, ' ');
					project2.maze.setMap2(" ", px, py + 1);
					project2.maze.setMap2(human, px, py);
					allPath = PathFinding.updatePath(allPath, px, py + 1);
					up = false;
				}
				if (down == true && rckey == '(') {
					cn.getTextWindow().output(px, py - 1, ' ');
					project2.maze.setMap2(" ", px, py - 1);
					project2.maze.setMap2(human, px, py);
					allPath = PathFinding.updatePath(allPath, px, py - 1);
					// cn.getTextWindow().setCursorPosition(px, py);
					// System.out.println(humannum);
					down = false;
				}

				keypr = 0;
			}
			project2.maze.setMap(map);
			for (int i = 0; i < 23; i++) {
				for (int j = 0; j < 55; j++) {
					if (project2.maze.getMap()[i][j].equals(human)) {
						cn.setTextAttributes(blue);
						cn.getTextWindow().setCursorPosition(j, i);
						System.out.println(humannum);
					}
				}
			}
			if (bp.isMatched()) {
				matched = (int) bp.left_b.pop();
				bp.right_b.pop();
			}
			if (time % 5 == 0) {
				input.SettleInput();
			}

			time++;
			Thread.sleep(500);

		}

	}

	public void GameOver(int px, int py) {
		Game.cn.getTextWindow().setCursorPosition(px, py);
		System.out.println(" ");
		gamecontrol = false;
		Game.cn.getTextWindow().setCursorPosition(20, 11);
		Game.cn.setTextAttributes(Game.red);
		System.out.println("GAME OVER");

	}
	

}
