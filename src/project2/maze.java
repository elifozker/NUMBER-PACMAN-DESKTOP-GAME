package project2;


import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import enigma.console.TextAttributes;
import enigma.core.Enigma;


public class maze {
	

	private static String Map[][] ;
	private static int lenght0;
	private static int lenght1;
	
	    maze(){
	    	maze.lenght0 = 23;
	    	maze.lenght1 = 55; 
	    	Map = new String[lenght0][lenght1];
	    }
	    
	    public static String[][] getMap() {
			return Map;
		}
	    public static String getMap1(int y,int x){
	    	return Map[y][x];
	    }
		public static  void setMap(String[][] map) {
			Map = map;
		}
		public static void setMap2(String map,int x, int y) {
			Map[y][x] = map;
		}
		public static int getLenght0() {
			return lenght0;
		}
		public static void setLenght0(int lenght0) {
			maze.lenght0 = lenght0;
		}
		public static int getLenght1() {
			return lenght1;
		}
		public static void setLenght1(int lenght1) {
			maze.lenght1 = lenght1;
		}
		 public  void  Menu() {
				
				System.out.println("███╗░░░███╗░█████╗░███████╗███████╗  ░██████╗░░█████╗░███╗░░░███╗███████╗\n"
						+ "████╗░████║██╔══██╗╚════██║██╔════╝  ██╔════╝░██╔══██╗████╗░████║██╔════╝\n"
						+ "██╔████╔██║███████║░░███╔═╝█████╗░░  ██║░░██╗░███████║██╔████╔██║█████╗░░\n"
						+ "██║╚██╔╝██║██╔══██║██╔══╝░░██╔══╝░░  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░\n"
						+ "██║░╚═╝░██║██║░░██║███████╗███████╗  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗\n"
						+ "╚═╝░░░░░╚═╝╚═╝░░╚═╝╚══════╝╚══════╝  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝");
				
				Game.cn.getTextWindow().setCursorPosition(25,10);			
				System.out.println("Welcome to the Maze Game!");
				Game.cn.setTextAttributes(Game.red);				
				System.out.print("Play to Press"  + " P" + "\nEnter:");
				Game.cn.setTextAttributes(Game.white);		
	   	     	Scanner scanner = new Scanner(System.in);		
				String input = scanner.next();
				
				
			}
		
			public void clearScreen() {
				
				Game.cn.getTextWindow().setCursorPosition(0,0);		
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
				Game.cn.getTextWindow().setCursorPosition(25,10);		
				System.out.println("                                                                         ");
				System.out.println("                                                                         ");
			
			} 
       
		
	    
     public void print_maze() throws FileNotFoundException {
    	 FileInputStream fis = new FileInputStream(
					"/Users/elifozker/Desktop/Documents/mazegame.txt");    	
    	 Scanner scan = new Scanner(fis);   	
    	 while(scan.hasNextLine()) {    	   		
    	 for (int i = 0; i < maze.lenght0; i++) {
    		 String str = scan.nextLine();
    		 for (int j = 0; j < maze.lenght1; j++) {
 				Map[i][j] = String.valueOf(str.charAt(j));
 			}
    		 System.out.println(str);			
		}    	
    	 }
    	 Shapes();   	
     }
     public void updateMap() {
    	 for(int i=0;i<23;i++) {
    		 for(int j=0;j<55;j++) {
    			 if(Map[i][j].equals(" ")) {
    				 Game.cn.getTextWindow().setCursorPosition(j, i);
    				 System.out.println(" ");
    			 }
    		 }
    	 }
     }

	 public  void Shapes() {
			Game.cn.getTextWindow().setCursorPosition(57,0);
			System.out.println("Input");
			Game.cn.getTextWindow().setCursorPosition(57,1);
			System.out.println("<<<<<<<<<<");
			Game.cn.getTextWindow().setCursorPosition(57,3);
			System.out.println("<<<<<<<<<<");	
			Game.cn.getTextWindow().setCursorPosition(57,7);
			System.out.println("Backpacks");
			for (int i = 8; i <= 15; i++) {
				Game.cn.getTextWindow().setCursorPosition(57,i);
				System.out.println("|   | |   |");
			}			
			Game.cn.getTextWindow().setCursorPosition(57,16);
			System.out.println("+---+ +---+");
			Game.cn.getTextWindow().setCursorPosition(57,17);
			System.out.println("Left  Right");		
			Game.cn.getTextWindow().setCursorPosition(57,20);
			System.out.println("Score: ");
			Game.cn.getTextWindow().setCursorPosition(57,23);
			System.out.println("Time : ");
			
			
		}
	
	

	
	
     
}

