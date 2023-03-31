package project2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import enigma.core.Enigma;

public class Input {
	private static  int[] InputArray = {1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,6,7,8,9};	
	private  Queue queue;
	private  boolean numcontrol123;
	private  boolean numcontrol456;
	private  boolean numcontrol789;
	randomN randNumbers;
	
	Input(){
		queue = new Queue(9000000);
		numcontrol123 = false;
		numcontrol456 = false;
		numcontrol789 = false;		
	}
	
	
	public void CreatingQueue() {
		for (int i = 0; i < 100000; i++) {
			int rnd = new Random().nextInt(InputArray.length);		
			   int num = InputArray[rnd];
			   queue.enqueue(num);
		}
		 
	}
	public void First25() {	
		int count = 0;
		while(count < 25) {
			 int indexX= new Random().nextInt(maze.getLenght0());
			 int indexY= new Random().nextInt(maze.getLenght1());			 		 
			 if(maze.getMap()[indexX][indexY].equals(" ")) {
				 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
				 First25Color(indexX,indexY); 
				 count++;}
		}
		
	}
	
	public void First25Color(int indexX,int indexY) {//ilk 25 için
		String[][] map = maze.getMap();
		numColorControl();
		 if(numcontrol123 == true) {			
			 setMapAndTxt(map,indexX,indexY);
			 Green();
		 }
		 else if(numcontrol456 == true ) {			
			 setMapAndTxt(map,indexX,indexY);
			 Yellow();
		 }
		 else if(numcontrol789 == true ) {			 
			 setMapAndTxt(map,indexX,indexY);
			 Red();			
		 }
	}
	
	public void numColorControl() {
		 if((int)queue.peek() == 1|| (int)queue.peek() == 2 || (int)queue.peek()== 3 ) {			
			 numcontrol123 = true;
			 numcontrol456 = false;
			 numcontrol789 = false;
		 }
		 else if((int)queue.peek()== 4  || (int)queue.peek() == 5|| (int)queue.peek() == 6 ) {	
			 numcontrol123 = false;
			 numcontrol456 = true;
			 numcontrol789 = false;
			
		 }
		 else if((int)queue.peek() == 7  || (int)queue.peek()== 8  || (int)queue.peek()== 9 ) {	
			 numcontrol123 = false;
			 numcontrol456 = false;
			 numcontrol789 = true;
		 }
	}
	public void Red() {
		 Game.cn.setTextAttributes(Game.red);
		 System.out.println(queue.dequeue());		
	}
	public void Yellow() {
		 Game.cn.setTextAttributes(Game.yellow);
		 System.out.println(queue.dequeue());
	}
	public void Green() {
		 Game.cn.setTextAttributes(Game.green);
		 System.out.println(queue.dequeue());
	}
	public void setMapAndTxt(String[][] map ,int indexX,int indexY) {
		 map[indexX][indexY] =  String.valueOf(queue.peek());	
		 maze.setMap(map);
	}
	public  void PrintingInput() {
	 
	   for (int i = 0; i < 10; i++) {			  
		   Game.cn.getTextWindow().setCursorPosition(57+i,2);
		   Game.cn.setTextAttributes(Game.white);	
		   System.out.println(queue.peek());
		   queue.enqueue(queue.dequeue());		   
 }
	   for (int i = 0; i < queue.size()-10; i++) {
		queue.enqueue(queue.dequeue());
	     }	    	
	} 
	 
	 
	
	 public void SettleInput() throws InterruptedException {
		 String[][] map = maze.getMap();
		 numColorControl();
		 while(true) {
			 int indexX= new Random().nextInt(maze.getLenght0());
			 int indexY= new Random().nextInt(maze.getLenght1());
			 if(!(maze.getMap()[indexX][indexY].equals("#"))) {
				 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
				 if( numcontrol123 == true) {							 
					 setMapAndTxt(map,indexX, indexY);
					 Green();
					 PrintingInput();					
					 break;
				 }
				 else if(numcontrol456 == true) {				 					
					 setMapAndTxt(map,indexX, indexY);
					 Yellow();
					 PrintingInput();
					 //randommoving(indexX,indexY);
					 break;
				 }
				 else if(numcontrol789 == true) {					 					
					 setMapAndTxt(map,indexX, indexY);
					 Red();
					 PrintingInput();	
					 break;
				 }
				
			 }
			 
		 }			 
	 }
	 public randomN[] randm() {
		 int a =0;
		 randomN[] randNumbers = new randomN[13000];
		 String[][] Map = maze.getMap();
	        for (int i = 0; i < maze.getLenght0(); i++) {

	            for (int j = 0; j < maze.getLenght1(); j++) {
                         if(Map[i][j].equals("4")||Map[i][j].equals("5")||Map[i][j].equals("6")) {
                        	 randomN rndN = new randomN(j,i,Map[i][j]);
                        	 randNumbers[a]= rndN;
                        	 a++;
                         }
	            }
	        }
	       return randNumbers;
	    }
	 
	 public void randMove(randomN[] rn) {
		 String[][] Map = maze.getMap();
		 int b=0;
		boolean isMove = false;
               while(rn[b]!=null) {
            	   int indexY=rn[b].getX();
            	   int indexX = rn[b].getY();
            	   String num = rn[b].getNumber();
            	   isMove=false;
            	   int c =0;
            	   while(isMove==false) {
            	   int a =new Random().nextInt(4);
  				 if(a == 0) {
  					 if(indexY<23&&maze.getMap()[indexX][indexY+1].equals(" ") ) {//right
  						 
  						 rightRandom(Map,indexX,indexY,num);
  						 isMove=true;
  						
  						
  						 
  					 }	
  					 else if(indexY<23&&maze.getMap()[indexX][indexY+1].equals("H") && Integer.parseInt(num) > Game.humannum) {
  						 
  						 rightRandom(Map,indexX,indexY,num);
 						 isMove=true;
 						 Game.cn.getTextWindow().setCursorPosition(20,11);
 						 Game.cn.setTextAttributes(Game.red);
   						 System.out.println("GAME OVER");
 						 Game.gamecontrol = false;
 						 break;
 						 
  						 
  					 }
  				 }
  				 else if(a == 1) {									 
  					   if(indexY>0&&maze.getMap()[indexX][indexY-1].equals(" ")) {//left
  							
  						 leftRandom(Map,indexX,indexY,num);
  						 isMove=true;
  					 }	
  					   else if(indexY>0&&maze.getMap()[indexX][indexY-1].equals("H") &&  Integer.parseInt(num) > Game.humannum) {
  						 leftRandom(Map,indexX,indexY,num);
  						 isMove=true;
  						 Game.cn.getTextWindow().setCursorPosition(20,11);
  						 Game.cn.setTextAttributes(Game.red);
   						 System.out.println("GAME OVER");
  						 Game.gamecontrol = false;
  						 break;
  						 
  					   }
  				}
  				 else if(a == 2) {
  					 if(indexX<23&&maze.getMap()[indexX+1][indexY].equals(" ")) {//down					
  						 downRandom(Map,indexX,indexY,num);
  						 isMove=true;
  					 }
  					 else if(indexX<23&&maze.getMap()[indexX+1][indexY].equals("H")  && Integer.parseInt(num) > Game.humannum) {
  						 downRandom(Map,indexX,indexY,num);
  						 isMove=true;
  						 Game.cn.getTextWindow().setCursorPosition(20,11);
  						 Game.cn.setTextAttributes(Game.red);
   						 System.out.println("GAME OVER");
  						 Game.gamecontrol = false;
  						 break;
  					 }
  				}
  				 else if(a==3) {
  					 if(indexX>0&&Map[indexX-1][indexY].equals(" ")) {//up
  							
  						 upRandom(Map,indexX,indexY,num);
  						 isMove=true;
  						
  					 }
  					 else if(indexX>0&&Map[indexX-1][indexY].equals("H") && Integer.parseInt(num) > Game.humannum) {
  						 upRandom(Map,indexX,indexY,num);
  						 isMove=true;
  						 Game.gamecontrol = false;
  						 Game.cn.getTextWindow().setCursorPosition(20,11);
  						 Game.cn.setTextAttributes(Game.red);
  						 System.out.println("GAME OVER");
  						 break;
  						 
  						 
  					 }
  				 }
  				 if(c>200) {
  					 isMove=true;
  				 }
  				 c++;
               }
            	   isMove=false;
  				b++;
               }		 
	 }
	 public void rightRandom(String[][] Map,int indexX,int indexY,String num) {
		     Map[indexX][indexY] = " ";
			 Map[indexX][indexY+1] = num;
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
			 System.out.println(" ");
			 maze.setMap(Map);						
			 Game.cn.getTextWindow().setCursorPosition(indexY+1,indexX);
			 Game.cn.setTextAttributes(Game.yellow);
			 System.out.println(num);
		 
		 
		 
	 }
	 public void leftRandom(String[][] Map,int indexX,int indexY,String num) {
		 Map[indexX][indexY] = " ";
			 Map[indexX][indexY-1] = num;
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
			 System.out.println(" ");
			 maze.setMap(Map); 						
			 Game.cn.getTextWindow().setCursorPosition(indexY-1,indexX);
			 Game.cn.setTextAttributes(Game.yellow);
			 System.out.println(num);
	 }
	 public void downRandom(String[][] Map,int indexX,int indexY,String num) {
		     Map[indexX][indexY] = " ";
			 Map[indexX+1][indexY] = num;
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
			 System.out.println(" ");
			 maze.setMap(Map);
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX+1);
			 Game.cn.setTextAttributes(Game.yellow);
			 System.out.println(num); 
		 
		 
	 }
	 public void upRandom(String[][] Map,int indexX,int indexY,String num) {
		     Map[indexX][indexY] = " ";
			 Map[indexX-1][indexY] = num;
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX);
			 System.out.println(" ");
			 maze.setMap(Map);						
			 Game.cn.getTextWindow().setCursorPosition(indexY,indexX-1);
			 Game.cn.setTextAttributes(Game.yellow);
			 System.out.println(num);
		 
		 
	 }
}

