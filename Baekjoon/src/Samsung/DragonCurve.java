package Samsung;
import java.util.ArrayList;
import java.util.Scanner;
 
public class DragonCurve {
 
    static int[][] map = new int[101][101];
    //배열 방향정보 : 0 (→), 1 (↑), 2 (←), 3(↓), 4(대각선아래)
    //static int[] goX = {0,-1,0,1,1};
    //static int[] goY = {1,0,-1,0,1};
    
    static int[] goX = {1, 0, -1, 0, 1};
    static int[] goY = {0, -1, 0, 1, 1};
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        int curveNum = sc.nextInt(); // 드래곤 커브의 수
         
        int[][] curveInfoArray = new int[curveNum][4]; // 드래곤 커브의 정보를 저장할 배열
        
        for(int i = 0; i < curveNum; i++){ // for문을 통해 시작점 / 시작방향 / 세대 입력
            for(int j = 0; j < 4; j++){
                curveInfoArray[i][j] = sc.nextInt();
            }
        }
        solutions(curveInfoArray);
    }
     
    static void solutions(int[][] curveInfoArray) {
     
        for(int i = 0; i < curveInfoArray.length; i++){
            ArrayList<Integer> direction = new ArrayList<>();
            direction.add(curveInfoArray[i][2]); // 방향을 저장한다.
            for(int j = 0; j < curveInfoArray[i][3]; j++){ // 입력한 세대만큼 반복
            	
                //이동해야할 방향을 설정한다.
                int size = direction.size();
                for(int k = size-1; k >= 0; k--) {
                    int n = direction.get(k);
                    if(n == 3) {
                        direction.add(0);
                    } else {
                        direction.add(n+1);
                    }
                }
            } 
            //시작지점부터 방향설정에 따라 그린다.
            drawing(curveInfoArray[i][0], curveInfoArray[i][1], direction);
        }
            //4개  꼭지점이 모두 선택된 사각형을 탐색
            countRec();
    }
     
    static void countRec() {
        int result = 0;
        //100인 이유 마지막 행과 열은 애초에 사각형을 이룰 수 없기 떄문에 검사하지 않는다.
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                // 0(→) 3 (↓) 4 (대각선 아래)
                if(map[i][j] == 1 && map[i+goX[0]][j+goY[0]] == 1 && map[i+goX[3]][j+goY[3]] == 1 && map[i+goX[4]][j+goY[4]] == 1){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
 
    static void drawing(int x, int y, ArrayList<Integer> direction) {
            map[x][y] = 1;
            for(int i = 0; i < direction.size(); i++){
                x = x + goX[direction.get(i)];
                y = y + goY[direction.get(i)];
                map[x][y] = 1;
        }
    }
}