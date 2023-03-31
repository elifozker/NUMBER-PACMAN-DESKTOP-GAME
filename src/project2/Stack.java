package project2;



public class Stack {
	private int top;
	private Object[] elements;
	public Stack(int capacity) {
		elements = new Object[capacity];
		top = -1;
	}
	void push(Object data) {
		if(isFull()) {
			System.out.println("Stack overflow");
		}
		else {
			top++;
			elements[top] = data;
		}
	}
	Object pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}
		else {
			Object data = elements[top];
			top--;
			return data;
		}
	}
	Object peek() {
		if(isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}
		else {
			return elements[top];
		}
	}
	public boolean isEmpty() {
		return (top == - 1);
	}
	public boolean isFull() {
		return (top == elements.length - 1);
	}
	public int size() {
		return (top + 1);
	}	
}

