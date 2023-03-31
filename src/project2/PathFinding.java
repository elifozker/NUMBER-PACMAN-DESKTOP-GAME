package project2;

import java.util.Random;

public class PathFinding {
	private static MppE[][] mp;
	PathFinding() {
	}
	public MppE[] redNumsRnd() {
		mp= new MppE[23][55];
		MppE[] redNums = new MppE[60];
		int z = 0;
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 55; j++) {
				String value = maze.getMap1(i, j);
				MppE mpe = new MppE(value, j, i, true);
				mp[i][j] = mpe;
				if (mpe.getValue().equals("7") || mpe.getValue().equals("9")
						|| mpe.getValue().equals("8")) {
					redNums[z] = mp[i][j];
					z++;
				}
			}
		}
		return redNums;
		
		
	}

	public static MppE[][] PFinding(MppE[]redNums){
		
		// MppE[] path = new MppE[1265];
		// MppE[] visited = new MppE[1265];
		
		MppE[][] allPath = new MppE[60][1265];
		//int c = 0;
		//int d = 0;
		int z = 0;
		int e = 0;
		boolean isCount = false;
	
		for (int j = 0; j < redNums.length; j++) {
			MppE[] path = new MppE[1265];
			MppE[] visited = new MppE[1265];
		
			randomN human =FindingHumanNumCoord();
			if (redNums[j] != null && redNums[j].isCheck()) {
				int c = 0;
				int d = 0;
				path[c] = redNums[j];
				visited[d] = redNums[j];
				redNums[j].setCheck(false);
				c++;
				d++;

				int count = 0;
				int rnd = -1;
				
				if (path[0] != null) {
					while (!(path[c - 1].getValue().equals("H"))) {
						if (count < 200) {
							
							int Cx = path[c - 1].getX();
							int Cy = path[c - 1].getY();
							int hx = human.getX();
							int hy = human.getY();
							isCount = false;
							
							if(hx > redNums[j].getX() && hy < redNums[j].getY()){//kuzeydoğu
								rnd = new Random().nextInt(2)+1;
								
							}
							else if(hx < redNums[j].getX() && hy < redNums[j].getY()) {// kuzeybatı
											
							 	boolean rndboolean =  new Random().nextBoolean();
							 	if(rndboolean == true)
							 		rnd = 0;
							 	else if(rndboolean == false)
							 		rnd = 2;
							}
							else if(hx>redNums[j].getX() && hy >redNums[j].getY()) {// güneydoğu // 1 // 3
								boolean rndboolean =  new Random().nextBoolean();
							 	if(rndboolean == true)
							 		rnd = 1;
							 	else if(rndboolean == false)
							 		rnd = 3;
							}
							else if(hx<redNums[j].getX() && hy>redNums[j].getY() ) {//güney batı
								boolean rndboolean =  new Random().nextBoolean();
							 	if(rndboolean == true)
							 		rnd = 0;
							 	else if(rndboolean == false)
							 		rnd = 3;
							}
							else if(hx ==  redNums[j].getX() && hy < redNums[j].getY()) {// kuzey
								rnd = 2;
							}
							else if(hx ==  redNums[j].getX() && hy > redNums[j].getY()) {//güney
								rnd = 3;
							}
							
							else if( hy == redNums[j].getY() && hx<redNums[j].getX() ) {// batı
								rnd = 0;
							}
							else if( hy == redNums[j].getY() && hx>redNums[j].getX()) { // doğu
								rnd = 1;
							}
							
							if (rnd == 0) { // left
								if (Cx > 0
										&& (mp[Cy][Cx - 1].getValue().equals(" ")
												|| mp[Cy][Cx - 1].getValue().equals("H"))
										&& PathFinding.isVisited(visited, Cx - 1, Cy) == false) {
									visited[d] = mp[Cy][Cx - 1];
									path[c] = mp[Cy][Cx - 1];
									c++;
									d++;
									count = 0;
									isCount = true;
								}
								
								
							}
							
							if (rnd == 1) { // right
								if (Cx < 23
										&& (mp[Cy][Cx + 1].getValue().equals(" ")
												|| mp[Cy][Cx + 1].getValue().equals("H"))
										&& PathFinding.isVisited(visited, Cx + 1, Cy) == false) {
									visited[d] = mp[Cy][Cx + 1];
									path[c] = mp[Cy][Cx + 1];
									c++;
									d++;
									count = 0;
									isCount = true;
								}
							
							}
							if (rnd == 2) { // up
								if (Cy > 0
										&& (mp[Cy - 1][Cx].getValue().equals(" ")
												|| mp[Cy - 1][Cx].getValue().equals("H"))
										&& PathFinding.isVisited(visited, Cx, Cy - 1) == false) {
									visited[d] = mp[Cy - 1][Cx];
									path[c] = mp[Cy - 1][Cx];
									c++;
									d++;
									count = 0;
									isCount = true;
								}
								
							
							}
							if (rnd == 3) { // down
								if (Cy < 23
										&& (mp[Cy + 1][Cx].getValue().equals(" ")
												|| mp[Cy + 1][Cx].getValue().equals("H"))
										&& PathFinding.isVisited(visited, Cx, Cy + 1) == false) {
									visited[d] = mp[Cy + 1][Cx];
									path[c] = mp[Cy + 1][Cx];
									c++;
									d++;
									count = 0;
									isCount = true;
								}
														
							}
							if(isCount == false) {
								rnd = new Random().nextInt(4);
							}
							
							
							
							/*if(c>20) {
								j--;
								break;
							}*/
							count++;
						} else {
							path[c - 1] = null;
							c--;
							count = 0;
							if (c == 0) {
								break;
							}
						}
					}
				}
				if (path[0] != null) {
					allPath[e] = path;
					e++;
				}
			}
		}
		return allPath;
	}
	public static void RedMove(MppE[][] allPath, int mNumber) {
		for(int i=0;i<60;i++) {
    		if(allPath[i]!=null) {
    			if(allPath[i][mNumber]!=null&&!(allPath[i][mNumber].getValue().equals("H"))) {
    				Game.cn.getTextWindow().setCursorPosition(allPath[i][mNumber-1].getX(), allPath[i][mNumber-1].getY());
    				Game.cn.setTextAttributes(Game.white);
    				System.out.println(" ");
    				maze.setMap2(" ",allPath[i][mNumber-1].getX(), allPath[i][mNumber-1].getY());
    				Game.cn.getTextWindow().setCursorPosition(allPath[i][mNumber].getX(), allPath[i][mNumber].getY());
    				Game.cn.setTextAttributes(Game.red);
    		        System.out.println(allPath[i][0].getValue());
    		        maze.setMap2(allPath[i][0].getValue(),allPath[i][mNumber].getX(), allPath[i][mNumber].getY());
    			}
    		}
    		}
    	}
	public static MppE[][] updatePath(MppE[][] allPath,int x, int y){
		boolean isRed=false;
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 55; j++) {
				if (maze.getMap()[i][j].equals("7") || maze.getMap()[i][j].equals("9")
						|| maze.getMap()[i][j].equals("8")) {
                   isRed=true;
				}
			}
		}
		for(int i=0;i<60;i++) {
			if(isRed) {
				for(int j=0;j<1265;j++) {
					if(allPath[i][j]==null) {
						MppE mpe = new MppE(" ",x,y,true);
						allPath[i][j]= mpe;
						break;
					}
				}
			}
		}
		return allPath;
	} 
	
    public static void PathDrawing(MppE[][] allPath) {
 
    	for(int i=0;i<60;i++) {
    		if(allPath[i]!=null) {
    		for(int j=1;j<allPath[i].length;j++) {
    			if(allPath[i][j]!=null&&!(allPath[i][j].getValue().equals("H"))) {
    				Game.cn.getTextWindow().setCursorPosition(allPath[i][j].getX(), allPath[i][j].getY());
    				Game.cn.setTextAttributes(Game.white);
    				System.out.println(".");
    			}
    		}
    		}
    	}
    }
	public static boolean isVisited(MppE[] visited, int x, int y) {
		boolean isV = false;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] != null && visited[i].getX() == x && visited[i].getY() == y) {
				isV = true;
			}
		}
		return isV;
	}
	public static randomN FindingHumanNumCoord() {
		int hy = 0;
		int hx = 0;
		boolean flag = true;
		for (int i = 0; i < 23; i++) {
			for (int k = 0; k < 55; k++) {
				if(maze.getMap()[i][k].equals("H")) {
					hy = i;
					hx = k;
					flag = false;
					
				}
			}
			if(!flag)
				break;
		}
		randomN human = new randomN(hx,hy,"H");
		return human;
	
	}

}
