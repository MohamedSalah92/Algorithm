package Samsung;

import java.util.ArrayList;
import java.util.Scanner;

class Pair {
    int x; 
    int y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ChickenDelivery {
    static int n; // 도시의 크기 
    static int m; // 치킨집의 최대 개수
    static int[][] inputAry; // 도시의 정보를 2차원 배열로 표현
    static ArrayList<Pair> storeList; // 치킨집
    static ArrayList<Pair> personList; // 집
    static int answer = Integer.MAX_VALUE; // 도시의 치킨 거리의 최솟값
    
    public static void main(String[] args) { // main 함수
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 입력
        m = sc.nextInt(); // 입력
        inputAry = new int[n][n]; // 배열 선언 및 초기화
        storeList = new ArrayList<Pair>(); // 객체 생성
        personList = new ArrayList<Pair>(); // 객체 생성
        ArrayList<Pair> answerList = new ArrayList<Pair>();
	    
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                inputAry[i][j] = sc.nextInt();
                if(inputAry[i][j] == 2) { // 2일 경우 치킨집
                    storeList.add(new Pair(i,j));
                } else if(inputAry[i][j] == 1) { // 1일 경우 집
                    personList.add(new Pair(i,j));
                }
            }
        }
        boolean[] check = new boolean[storeList.size()];
	    
        dfs(0,0, answerList, check);
        System.out.println(answer);
    }
    
    static void dfs(int start, int depth, ArrayList<Pair> answerList, boolean[] check) {
        if(depth == m) { // 치킨집의 최대 개수일 경우 stop
            int sum = calAnswer(answerList);
            answer = Math.min(answer, sum);
            return; // return을 넣어줘도 되고 안넣어줘도 된다. 함수를 정의할 때 void를 선언했기 때문이다.
        }
        for(int i = start; i < storeList.size(); i++) {
            if(check[i])
                continue;
            
            check[i] = true;
            answerList.add(storeList.get(i));
            dfs(i+1, depth+1, answerList, check);
            answerList.remove(answerList.size()-1);
            check[i] = false;
        }
    }
    
	static int calAnswer(ArrayList<Pair> answerList) {
        int sum = 0;
        for(int i = 0; i < personList.size(); i++) {
            Pair p1 = personList.get(i);
            int len = Integer.MAX_VALUE;
            for(int j = 0; j < answerList.size(); j++) {
                Pair p2 = answerList.get(j);
                int temp = Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
                len = Math.min(len, temp);
            }
            sum += len;
        }
        return sum;
    }
}