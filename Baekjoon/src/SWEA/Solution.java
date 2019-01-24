package SWEA;

import java.util.Scanner;

class info {
	public info() {
	}
	public info(info n) {
		h = n.h;
		w = n.w;
		height = n.height;
		usedk = n.usedk;
		len = n.len;
	}
	int h, w; // 맵상의 위치;
	int height; // (h,w)의 지점의 높이
	int usedk; // 공사를 진행한적이 있다면(1) 아니면 (0)
	int len; // 현재까지의 등산로 길이
}

public class Solution {

	static int N, K; // N : 맵의 사이즈, K : 최대 공사 가능 길이
	static int map[][] = new int[10][10]; // N*N 맵의 정보를 저장할 배열
	static int visit[][] = new int[10][10]; // 방문을 했었는지를 체크할 배열
	static int heighest; // 맵에서 가장 높은 봉우리의 높이
	static int ans; // 정답을 저장하는 변수
	static final int dir[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // 상, 하, 좌, 우  이동을 위한 배열
	
	static void sol(info cur) {
		// 길이가 더 길어졌을 때만 정답으로 지정
		ans = ans > cur.len ? ans : cur.len;
		
		// dir 배열을 이용해 (상, 하, 좌, 우) 순으로 탐색 진행
		for(int d = 0; d < 4; d++) {
			// 현재 위치(curH, curW)에 이동 방향을 더해 다음 방문할 위치와 높이 구하기
			info nxt = new info(cur);
			nxt.h = cur.h;
			nxt.h += dir[d][0];
			nxt.w += dir[d][1];
			
			// 주어진 맵의 사이즈의 범위를 벗어날 경우 탐색을 이어갈 수 없음
			if (nxt.h < 0 || nxt.h >= N || nxt.w < 0 || nxt.w >= N) {
				continue;
			}
			
			nxt.height = map[nxt.h][nxt.w];
			
			// 이미 방문을 한 경우 탐색을 이어갈 수 없음
			if(visit[nxt.h][nxt.w] != 0) {
				continue;
			}
			
			// 방문 예정인 위치의 높이가 현재의 높이보다 작은 경우
			if(nxt.height < cur.height)  {
				// 방문 표시
				visit[nxt.h][nxt.w] = 1;
				// 등산로 길이를 1 증가
				nxt.len++;
				// nxt를 기점으로 다음 탐색을 위해 함수 호출
				sol(nxt);
				// 다시 방문 표시를 해제
				visit[nxt.h][nxt.w] = 0;
				
			} else { // 다음에 갈 곳의 높이가 현재의 높이보다 크거나 같은 경우
				
				// 현재까지 공사가 이루어지지 않았고 다음 높이에 대해 K만큼 공사를 했을 때 현재보다 작을 경우에만 가능
				if(cur.usedk == 0 && nxt.height - K < cur.height) {
					visit[nxt.h][nxt.w] = 1;
					nxt.len++;
					// 공사했음을 표시
					nxt.usedk = 1;
					// 최대한 긴 등산로를 만들고 싶으므로 K만큼의 높이를 다 깎을 필요 없이
					// 현재 높이보다 1만 작게 깎아야 가장 긴 등산로를 만들 수 있다.
					nxt.height = cur.height - 1;
					sol(nxt);
					visit[nxt.h][nxt.w] = 0;
				}
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			// 초기화
			heighest = ans = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					// 맵에서 가장 높은 봉우리 찾기
					heighest = heighest > map[i][j] ? heighest : map[i][j];
				}
			} 
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 가장 높은 봉우리일 경우에만 탐색을 시작
					if(heighest == map[i][j]) {
						// 방문을 표시
						visit[i][j] = 1;
						
						info cur = new info();
						// 위치(i,j)
						cur.h = i;
						cur.w = j;
						// 높이
						cur.height = map[i][j];
						//공사 진행 하지 않음(0)
						cur.usedk = 0;
						// 현재 등산로 길이(1)
						cur.len = 1;
						// cur를 기점으로 탐색하기 위해 함수 호출
						sol(cur);
						//다시 방문 표시 해제
						visit[i][j] = 0;
					}
				}
			}
			// 결과값 출력
			System.out.println("#" + t + " " + ans);
		}
	}
}
