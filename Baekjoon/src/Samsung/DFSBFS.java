package Samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class DFSBFS{
  static int N,M,V;
  static int visit[], graph[][];
 
  static void DFS(int x) { // x가 시작점
    visit[x]=1; // 시작점은 1로 설정
    System.out.print(x+" "); // 방문했으니깐 출력
    for(int i=1; i<=N; i++) {
      if(graph[x][i]==1 && visit[i]==0) { // 연결되어있는지와 방문한적이 없다면 탐색 시작
        DFS(i);
      }
    }
  }
  
  static void BFS() { // bfs는 큐를 이용
    Queue<Integer> q = new LinkedList<Integer>();
    visit[V] = 1;
    q.add(V);
    while(!q.isEmpty()) {
      int x = q.peek(); //Queue에서 front부분의 값을 x에 저장
      q.poll(); //Queue에서 front부분의 값을 제거하고 반환
      System.out.print(x+" ");
      for(int i=1; i<=N; i++) {
        if(graph[x][i]==1 && visit[i]==0) {
          visit[i]=1;
          q.add(i);
        }
      }
    }
  }
 
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt(); // 정점의 개수
    M = sc.nextInt(); // 간선의 개수
    V = sc.nextInt(); // 시작할 정점의 번호
     
    graph = new int[N+1][N+1]; // 1부터 N까지이므로 배열의 크기는 N+1
    for(int i=1; i<=M; i++) { // 간선을 이어줄 정점을 입력
      int x = sc.nextInt();
      int y = sc.nextInt();
      graph[x][y] = graph[y][x] = 1; // 무방향성 그래프이므로 양쪽둘다 1로 저장
    }
 
    visit = new int[N+1]; // 방문할 정점 선언
    DFS(V);
    System.out.println();
    visit = new int[N+1];
    BFS();
     
  }
   
}