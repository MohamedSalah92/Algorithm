package Samsung;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
 
public class LadderManipulation {
 
    static ArrayList<Point> installable = new ArrayList<>();
    static int[][] orignalLadder;
    public static void main(String[] args) {
        //1. point자료형으로 가로(시작점,끝점)을 그린다.
        //2. 사용자가 임의로 그릴 수 있는 가로 공간을 따로 분별한다.
        //3. 조합을 적용하여 nCr (r=0,r=1,r=2,r=3)인 경우를 구한다.(r>3 부터 -1)
         
        Scanner sc = new Scanner(System.in);
     
        int h = sc.nextInt(); // 세로선의 갯수
        int r = sc.nextInt(); // 가로선의 갯수
        int w = sc.nextInt(); // 세로선마다 가로선을 놓을 수 있는 위치의 갯수
         
        orignalLadder = new int[w][h]; // 처음 생성된 사다리
     
        for(int i=0; i<r; i++){ // 가로선 그리기
            int y = sc.nextInt(); // y번 점선 위치에서 가로선 연결
            int x = sc.nextInt(); // x번 세로번과 x+1번 세로선
            orignalLadder[y-1][x-1] = 1;
            orignalLadder[y-1][x] = 2;
        }
     
        //2. 사용자가 그릴 수 있는 사다리 공간을 저장한다.
        for(int i=0; i<orignalLadder.length; i++) {
            for(int j=0; j<orignalLadder[0].length; j++) {
                // 끝점은 필요없음.무조건 -> 해당방향으로 설치할거임
                if(orignalLadder[i][j] == 0 && j !=orignalLadder[0].length-1) {
                    installable.add(new Point(i, j));
                }
            }
        }
        
        int n = installable.size(); // 그릴 수 있는 공간의 크기
        int[] arr = new int[n];
        int[] combArr = new int[n];
        for(int i=0; i<n; i++) arr[i] = i;
         
        for(int i=0; i<=r; i++){
            doCombination(combArr, n, i, 0, 0, arr);
        }
         
        //불가능
        System.out.println(-1);
 
    }
 
    private static void doCombination(int[] combArr, int n, int r, int index, int target, int[] arr) { 
        // r ==0 이란 것은 뽑을 원소를 다 뽑았다는 뜻.
        if(r == 0){
            int[][] copyArray = new int[orignalLadder.length][orignalLadder[0].length];
            arrayCopy(orignalLadder, copyArray);
             
            for(int i=0; i<index; i++){
                //3보다 크면..-1로 종료
                if(index > 3) {
                    System.out.println(-1);
                    System.exit(0);
                }
                int x = installable.get(arr[combArr[i]]).x; //i
                int y = installable.get(arr[combArr[i]]).y; //j

                // -> 이 방향으로만 설치 따라서, 오른쪽 유무 확인
                if(copyArray[x][y]==0 && copyArray[x][y+1]==0){
                    copyArray[x][y] = 1;
                    copyArray[x][y+1] = 2;
                }else{
                    return;
                }
             
            }
 
            //한개의 조합에 대해 사다리 설치 완료
            boolean flag = followLadder(copyArray);
            if(flag){
                System.out.println(index);
                System.exit(0);
            }
             
        } else if (target == n){ 
            return;
        } else {
            combArr[index] = target;
            doCombination(combArr, n, r-1, index+1, target+1, arr);
            doCombination(combArr, n, r, index, target+1, arr); 
        }
         
    }
 
     
    private static boolean followLadder(int[][] copyArray) {
          for(int i=0; i<copyArray[0].length; i++){
                int indexX = 0;
                int indexY = i;
                boolean flag = true;
                while(flag){
                     
                    //System.out.println("P : "+indexX+" "+indexY);
                    if(indexX == copyArray.length) break;       
                    int v = copyArray[indexX][indexY];
                    indexX++;
                    switch (v) {
                    case 1:
                        indexY++;   
                        break;
                    case 2:
                        indexY--;
                        break;
                    }
                }
                 
                if(i != indexY){
                    return false;
                }           
                 
            }
          return true;
         
    }
 
    private static void arrayCopy(int[][] array, int[][] copyarray){
        for(int i=0; i<array.length; i++){
            copyarray[i] = array[i].clone();
        }
    }
     
     
 
}