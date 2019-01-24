package Samsung;

import java.util.ArrayList;
import java.util.Scanner;

public class Gear {

	static int[][] command;
	static ArrayList<int[]> list; // arraylist안에 배열을 저장
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		list = new ArrayList<>(); // 선언
		
		for(int i=0; i<4; i++){ 
			int[] array = new int[8]; // 톱니바퀴의 상태를 저장할 배열 선언
		
			String str = sc.next(); // 한줄씩 톱니바퀴 상태 입력
			
			for(int j=0; j<8; j++){
				array[j] = Integer.parseInt(str.charAt(j)+""); // 문자열을 하나씩 읽고 int형으로 변환한다음에 배열에 저장
			}
			list.add(array); // 해당되는 배열을 list에 추가
		}
		
		int n = sc.nextInt(); // 회전 수
		command = new int[n][2]; // 회전시킬 방법을 배열로 선언 -> 톱니바퀴의 번호, 방향
		
		for(int i=0; i<n; i++){
			for(int j=0; j<2; j++){
				command[i][j] = sc.nextInt(); // 차례대로 입력
			}
		}
		solution();
	}

	private static void solution() {
		for(int i=0; i<command.length;i++){
			rotate(command[i][0]-1,command[i][1]);
			
			boolean leftFlag = true; // 왼쪽 톱니바퀴 체크 변수
			int target = command[i][0]-1;
			int direction = command[i][1];
			
			while (leftFlag) {
				try {
					if(list.get(target)[6+direction] != list.get(--target)[2]){ // target의 6번째 값과 target의 왼쪽에 있는 톱니바퀴의 2번째 index가 같은지 확인
						direction = direction * -1; // 회전 반대방향으로 바꿔준다.
						rotate(target, direction); // 왼쪽 톱니바퀴를 회전 반대방향으로 rotate
					} else { // 같으면 회전 x
						leftFlag = false;
					}
				} catch (Exception e) {
					leftFlag = false;
				}
			}
			
			boolean rightFlag = true;
			target = command[i][0]-1;
			direction = command[i][1];
			while(rightFlag){
				try {			
					if(list.get(target)[2+direction] != list.get(++target)[6]) {
						direction = direction * -1;
						rotate(target, direction);
					}else{
						rightFlag = false;
					}
				} catch (Exception e) {
					rightFlag = false;
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<list.size(); i++){
			ans += list.get(i)[0] * (Math.pow(2, i));
		}
		
		System.out.println(ans);
		
	}

	private static void rotate(int target, int direction) {
		if(direction == 1) {
			//시계방향
			int temp = list.get(target)[7];
			for(int i=7; i > 0; i--){
				list.get(target)[i] = list.get(target)[i-1];
			}
			list.get(target)[0] = temp;
			
		} else{
			//시계 반대방향
			int temp = list.get(target)[0];
			for(int i=0; i < 7; i++){
				list.get(target)[i] = list.get(target)[i+1];
			}
			list.get(target)[7] = temp;
		}
	}
}