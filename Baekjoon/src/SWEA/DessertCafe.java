package SWEA;

import java.util.Scanner;

public class DessertCafe{
	static Scanner sc = new Scanner(System.in);
	static int N; // 맵 사이즈를 저장한 변수
	static int[][] map = new int[20][20]; // 맵 정보를 저장한 배열
	static boolean[] isVisit = new boolean[101]; // 방문 여부를 체크용 배열
	static int result; //가장 긴 경로 길이를 저장하는 변수
	
	// isVisit 배열을 모두 false로 초기화하는 함수
	static void visitClear() {
		for(int i = 0; i < 101; i++) {
			isVisit[i] = false;
		}
	}
	
	// 처음 시작시 호출 할 함수
	static void init( ) {
		visitClear();
		result = -1;
	}
	
	static void solve() {
		
		// i, j 좌표부터 시작한다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				// 사각형의 변의 길이는 각각 a, b이다.
				for(int a = 1; a < N; a++) {
					for(int b = 1; b < N; b++) {
						
						// result : 지금까지 계산한 값들 중에 가장 많이 디저트를 먹을 수 있는 결과 값을 저장하고 있다.
						// 꼭짓점이 존재 할 수 있는지 범위를 확인하고, 지금 구할 둘레가 이전에 구했던 가장 큰 둘레보다 작으면 탐색하지 않는다.
						
						if(j+a <= N-1 // 오른쪽 범위
							&& i+a+b <= N-1 // 아래쪽 범위 
							&& j-b >= 0 // 왼쪽 범위
							&& (a+b) * 2 > result) // 이전에 구했던 값보다 크다면 
							{
								visitClear();
								boolean isAble = true;
								int curi = i, curj = j;
								
								// (우측 하단), (좌측 하단), (좌측 상단), (우측 상단)순으로 탐색
								
								// (우측 하단) 확인
								for(int n = 0; n < a; n++) {
									curi++;
									curj++;
									
									// 먹지 않은 디저트라면 먹었다고 체크한다.
									if(!isVisit[map[curi][curj]]) {
										isVisit[map[curi][curj]] = true;
									} else { // 먹지 않은 디저르를 또 먹게 되는 경우 불가능 하다고 체크한다.
										isAble = false;
										break;
									}
								}
								
								// 불가능한 경우는 더 이상 볼필요가 없으므로 다음 경우로 간다.
								if (!isAble) {
									continue;
								}
								
								// (좌측 하단) 확인
								for(int n = 0; n < b; n++) {
									curi++;
									curj--;
									
									if(!isVisit[map[curi][curj]]) {
										isVisit[map[curi][curj]] = true;
									} else {
										isAble = false;
										break;
									}
								}
								
								if(!isAble) {
									continue;
								}
								
								// (좌측 상단) 확인
								for(int n = 0; n < a; n++) {
									curi--;
									curj--;
									
									if(!isVisit[map[curi][curj]]) {
										isVisit[map[curi][curj]] = true;
									} else {
										isAble = false;
										break;
									}
								}
								
								if(!isAble) {
									continue;
								}
								
								// (우측 상단) 확인
								for(int n = 0; n < b; n++) {
									curi--;
									curj++;
									
									if(!isVisit[map[curi][curj]]) {
										isVisit[map[curi][curj]] = true;
									} else {
										isAble = false;
										break;
									}
								}
								
								if(!isAble) {
									continue;
								}
								
								// 앞에서 모든 경우를 확인했기 때문에 가장 많이 먹을 수 있는 경우로 저장한다.
								result = 2 * (a +b);
							}
						}
					}
				}
			}
		}
	
	public static void main(String[] args) throws Exception{
		
		int test_case;
		test_case = sc.nextInt();
		
		for(int t = 1; t <= test_case; t++) {
			
			// 시작 시 초기화
			init();
			
			N = sc.nextInt();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 답을 내는 부분
			solve();
			
			// 결과값 출력
			System.out.println("#" + t + " " + result);
		}
		
	}
}