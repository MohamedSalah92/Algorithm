package Samsung;

import java.util.LinkedList;
import java.util.Scanner;

class Node {
	int value;
	int col;
	int row;
	
	public Node(int v, int c, int r) {
		value = v;
		col = c;
		row = r;
	}
}

public class WatchCctv {
	
	static LinkedList<Node> cctv = new LinkedList<>();
	static int min; // 사각지대 최솟값
	static int col; // 사무실의 열
	static int row; // 사무실의 행
	static int[][] map; // 사무실의 저장할 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		col = sc.nextInt(); // 사무실의 세로 크기 입력
		row = sc.nextInt(); // 사무실의 가로 크기 입력
		min = col * row; // 최솟값을 행 * 열로 지정
		map = new int[col][row]; // 세로와 가로의 크기를 배열 크기로 저장
		boolean[][] chk_map = new boolean[col][row]; // 사각지대를 체크할 배열을 선언
		
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < row; j++) {
				map[i][j] = sc.nextInt(); // 사무실 정보 입력
				if(map[i][j] > 0 && map[i][j] < 6) { // cctv일 경우 linkedlist에 추가
					cctv.add(new Node(map[i][j], i, j)); // 차례대로 cctv의 종류, 행, 열로 저장
				}
				if(map[i][j] == 6) { // 벽일 경우
					chk_map[i][j] = true; // 체크 배열에서 true로 저장
				}
			}
		}
		
		search(0, chk_map);
		System.out.println(min);
	}
	
	public static void search(int start, boolean[][] arr) {
		
		boolean[][] resArr = new boolean[col][row];
		
		if(start >= cctv.size()) {
			int count = count(arr); // 빈칸과 cctv의 개수
			if(min > count) {
				min = count;
			}
			return;
		}
		
		Node n = cctv.get(start); // 첫번째부터 탐색 시작
	
		switch(n.value) { // 해당되는 cctv의 종류
		
		case 1: // cctv의 종류가 1번일 때
			for(int i = 0; i < 4; i++) { // 1번이 회전할 수 있는 경우의 수
				for(int z = 0; z < col; z++) {
					for(int j = 0; j < row; j++) {
						resArr[z][j] = arr[z][j]; // 벽 -> true / 빈 칸, cctv -> false을 저장한 배열을 복사
					}
				}
				
				detect(n, i, resArr);
				search(start+1, resArr);
			}
			break;
			
		case 2: // cctv의 종류가 2번일 때
			for(int i = 0; i < 2; i++) {
				for(int z = 0; z < col; z++) {
					for(int j = 0; j <row; j++) {
						resArr[z][j] = arr[z][j];
					}
				}
				
				i *= 2;
				detect(n, i, resArr);
				detect(n, i+1, resArr);
				search(start+1, resArr);
			}
			break;
			
		case 3:
			for(int i = 0; i < 4; i++) { // 1번이 회전할 수 있는 경우의 수
				for(int z = 0; z < col; z++) {
					for(int j = 0; j < row; j++) {
						resArr[z][j] = arr[z][j]; // 벽 -> true / 빈 칸, cctv -> false을 저장한 배열을 복사
					}
				}
				
				detect(n, (i) % 2, resArr);
				if(i < 2) {
					detect(n, 2, resArr);
				} else {
					detect(n, 3, resArr);
				}
				search(start+1, resArr);
			}
			break;
		
		case 4:
			for(int i = 0; i < 4; i++) { // 1번이 회전할 수 있는 경우의 수
				for(int z = 0; z < col; z++) {
					for(int j = 0; j < row; j++) {
						resArr[z][j] = arr[z][j]; // 벽 -> true / 빈 칸, cctv -> false을 저장한 배열을 복사
					}
				}
				
				for (int j = 0; j < 4; j++) {
					if (j != i) {
						detect(n, j, resArr);
					}
				}
				search(start + 1, resArr);
			}

			break;

		case 5:
			for (int z = 0; z < col; z++) {
				for (int j = 0; j < row; j++) {
					resArr[z][j] = arr[z][j];
				}
			}
			
			for (int i = 0; i < 4; i++) {
				detect(n, i, resArr);
			}
			
			search(start + 1, resArr);
			resArr = arr.clone();

			break;
		}
		return;
	}
	
	public static int count(boolean[][] arr) { // 벽이 아닌 cctv와 빈칸이 몇 개있는지 알려주는 메소드
		int res = 0; // count할 변수
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == false) { // 사각지대일 경우
					res++;
				}
			}
		}
		return res;
	}
	
	public static boolean[][] detect(Node node, int dir, boolean[][] arr) {
		
		switch(dir) { // 방향에 따른 사각지대 찾기
		
		case 0: // 회전 0번
			for(int i = node.col; i >= 0; i--) { // i는 노드의 열 //
				if(map[i][node.row] == 6) { // 벽을 만날 경우 break
					break;
				}
				arr[i][node.row] = true; // true -> 사각지대가 아니다. (cctv, 빈 칸)
			}
			break;
			
		case 1: // 회전 1번
			for(int i = node.col; i < col; i++) {
				if(map[i][node.row] == 6) {
					break;
				}
				arr[i][node.row] = true;
			}
			break;
			
		case 2: // 회전 2번
			for(int i = node.row; i >= 0; i--) {
				if(map[node.col][i] == 6) {
					break;
				}
				arr[node.col][i] = true;
			}
			break;
			
		case 3: // 회전 3번
			for(int i = node.row; i < row; i++) {
				if(map[node.col][i] == 6) {
					break;
				}
				arr[node.col][i] = true;
			}
			break;
		}
		return arr;
	}

}