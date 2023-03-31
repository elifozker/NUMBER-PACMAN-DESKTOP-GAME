package project2;




public class backpack {
	
	public Stack left_b = new Stack(8);
	public Stack right_b = new Stack(8);
	
	public static int Score;
	backpack(){
      Score = 0;
	}
	public void left_to_right() {
		if(left_b.isEmpty()==false)
		right_b.push(left_b.pop());
	}
	public void right_to_left() {
		if(right_b.isEmpty()==false)
		left_b.push(right_b.pop());
	}
	public boolean isMatched() {
		 
		if(left_b.isEmpty()==false&&right_b.isEmpty()==false && left_b.size()==right_b.size()&&left_b.peek()==right_b.peek()) 
			return true;		
		else return false;
	}
	
	public void pushAte() {
		if(left_b.isFull()) {
			left_b.pop();
			left_b.push(Game.ate);
		}
		else {
			left_b.push(Game.ate);
		}
		
	}
	public static int Scoring() {
		
		if(Game.matched== 1 || Game.matched == 2 || Game.matched == 3) 
			Score += Game.matched * 1;		
		else if(Game.matched == 4 ||Game.matched == 5 || Game.matched == 6)
			Score += Game.matched * 5;
		else 
			Score += Game.matched * 25;
		return Score;
	}
	public void printScore() {
		Game.cn.getTextWindow().setCursorPosition(65, 20);
		System.out.println(Scoring());
	}
	
	public void print_left_b() {
		for(int i =9;i<17;i++) {
			Game.cn.getTextWindow().setCursorPosition(59,i);
			System.out.println(" ");
		}
		int y = 16;		
		Stack temp_s = new Stack(8);		
		while(left_b.isEmpty()==false) {
			Game.cn.getTextWindow().setCursorPosition(59,y-left_b.size());
			System.out.print(left_b.peek());
			temp_s.push(left_b.pop());
		}
		while(temp_s.isEmpty()==false)
		left_b.push(temp_s.pop());	
	}
	public void print_right_b() {
		for(int i =9;i<17;i++) {
			Game.cn.getTextWindow().setCursorPosition(65,i);
			System.out.println(" ");
		}
		int y = 16;
		Stack temp_s = new Stack(8);
		while(right_b.isEmpty()==false) {
			Game.cn.getTextWindow().setCursorPosition(65,y-right_b.size());
			System.out.print(right_b.peek());
			temp_s.push(right_b.pop());		
		}
		while(temp_s.isEmpty()==false)
		right_b.push(temp_s.pop());
	}
}
