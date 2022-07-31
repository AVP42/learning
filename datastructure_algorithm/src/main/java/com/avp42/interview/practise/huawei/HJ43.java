package com.avp42.interview.practise.huawei;

/**
 * HJ43 迷宫问题
 */
import java.util.*;
public class HJ43 {

    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1,0}, {0, -1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt(), n = in.nextInt();
            int[][] matrix = new int[m][n];
            for(int i = 0;i < m; i++){
                for(int j =  0; j < n; j++){
                    matrix[i][j] = in.nextInt();
                }
            }
//            dfs(matrix);
//            bfs(matrix);
        }
    }


    /** bfs*/
    // 通过parent记录路径
    static class Node{
        int[] val;
        Node parent;
        Node(int[] val){
            this.val  = val;
        }
    }

    private static void bfs(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(new int[]{0, 0}));
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                for (int[] direction : directions) {
                    int r = cur.val[0] + direction[0];
                    int c = cur.val[1] + direction[1];
                    if(r < 0 || c < 0 || r >= m || c >= n) continue;
                    if(visited[r][c]) continue;
                    if(matrix[r][c] == 1) continue;
                    Node newNode = new Node(new int[]{r, c});
                    newNode.parent = cur;
                    if(r == m - 1 && c == n -1) {
                        handleResult(newNode);
                        return;
                    }
                    visited[r][c] = true;
                    q.offer(newNode);
                }
            }
        }
    }

    private static void handleResult(Node node) {
        List<String> ret = new ArrayList<>();
        while(node != null){
            ret.add("(" + node.val[0] + "," + node.val[1] + ")");
            node = node.parent;
        }
        for (int i = ret.size() - 1; i >= 0; i--) {
            System.out.println(ret.get(i));
        }
    }


    /** dfs */
    static class Holder<T>{
        T val;
    }
    private static void dfs(int[][] matrix) {
        List<int[]> buffer = new ArrayList<>();
        Holder<List<int[]>> holder = new Holder<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        buffer.add(new int[]{0, 0});
        visited[0][0] = true;
        helper(matrix, 0, 0, buffer,visited, holder);
        print(holder.val);
    }


    static int maxLen = Integer.MAX_VALUE;
    private static void helper(int[][] matrix, int row, int column, List<int[]> buffer, boolean[][] visited, Holder<List<int[]>> holder) {
        if(row == matrix.length - 1 && column == matrix[0].length - 1){
            if(maxLen > buffer.size()){
//                System.out.println(buffer.size());
               holder.val = new ArrayList<>(buffer);
            }
            return;
        }
        for(int[] direction: directions){
            int r = row + direction[0];
            int c = column + direction[1];
            if(r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length){
                continue;
            }
            if(matrix[r][c] == 1) continue;
            if(visited[r][c]) continue;
            buffer.add(new int[]{r, c});
            visited[r][c] = true;
            helper(matrix, r, c, buffer, visited, holder);
            visited[r][c] = false;
            buffer.remove(buffer.size() - 1);
        }
    }

    private static void print(List<int[]> buffer) {
        for(int[] arr: buffer){
            System.out.println("(" + arr[0] + "," + arr[1] + ")");
        }
    }
}
