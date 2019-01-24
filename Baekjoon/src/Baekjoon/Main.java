package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Queue<Integer> qu = new LinkedList<>();

		Scanner sc = new Scanner(System.in);
		int a = 0;
		
		int t = sc.nextInt();
		
		for(int i = 0; i < t; i++) {
			String st = sc.next();
			
			if(st.equals("push")) {
				a = sc.nextInt();
				qu.add(a);
			} else if(st.equals("pop")) {
				if(qu.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(qu.poll());
				}
			} else if(st.equals("size")) {
				System.out.println(qu.size());
			} else if(st.equals("empty")) {
				if(qu.isEmpty()) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
			} else if(st.equals("front")) {
				if(qu.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(qu.peek());
					}
				}
			else {
				if(qu.isEmpty()) {
					System.out.println("-1");
					} 
				else {
					System.out.println(a);
				}
			}
		}
	}
}