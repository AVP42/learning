package com.avp42.datastructure._5_priority_queue_and_heap;


/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-27 22:20
 * @since： v 3.1.0
 */
class Sudoku {
    // X-每行的备忘录，使用位来记录，比如X[0] = 000010000，表示第0行已经存在5这个数字
    // Y-每列的备忘录，使用位来记录, 比如Y[0] = 000010000, 表示第0列已经存在5这个数字
    // Z-每个3x3 宫格内的备忘录，总共也是9个宫格
    int[] X = new int[9],Y = new int[9] ,Z = new int[9];

    public void solveSudoku(char[][] board) {
        // 思路：递归求解
        // 技巧：通过位存储0-9是否出现过，通过左移操作来设置相应的位，通过异或运算实现相同的方法写入和抹除操作，通过全局坐标代替x,y双坐标。
        // 步骤：
        // 1.初始化
        initSudoku(board);
        // 2.递归求解
        _solve(board,0,0);

    }


    void setMemorandum(int num , int x, int y){
        X[x] ^= (1 << num);
        Y[y] ^= (1 << num);
        // 根据x, y 计算z 坐在宫格块的行为 x/3,所在宫格块的列为 y/3，所以z = (x/3) * 3 + (y/3)
        Z[(x/3) * 3 + (y/3)] ^= (1 << num);
    }

    void initSudoku(char[][] board){
        for(int i = 0;i < 9; i++){
            for(int j = 0; j < 9; j ++){
                if(board[i][j] == '.') continue;
                setMemorandum(board[i][j] - '0', i, j);
            }
        }
    }

    boolean check(int num,int x, int y){
//        if((X[x] & (1 << num)) == (1 << num)) return false;
//        if((Y[y] & (1 << num)) == (1 << num)) return false;
//        if((Z[(x/3)*3 + y/3] & (1 << num)) == (1 << num)) return false;
        if((X[x] >> num & 1) == 1) return false;
        if((Y[y] >> num & 1) == 1) return false;
        if((Z[(x/3)*3 + y/3] >> num & 1) == 1) return false;
        return true;
    }

    int next_x(int x, int y){
        if(y == 8) return x + 1;
        return x;
    }

    int next_y(int y){
        if(y == 8) return 0;
        return y + 1;
    }

    boolean _solve(char[][] board, int x, int y){
        if(x == 9) return true;
        if(board[x][y] != '.') return _solve(board,next_x(x, y),next_y(y)) ;
        for(int k = 1;k <= 9;k++){
            if(!check(k,x,y)) continue;
            // 设置当前位置对应的k数字出现过
            setMemorandum(k,x,y);
            // 将数值的字符串转化为char
            board[x][y] = (char) (k + '0');
            // 不能直接返回，还有其他的要尝试
            if(_solve(board,next_x(x,y),next_y(y))) return true;
            // 到这一步，说明有走不下去的情况，这里需要回退，当前位置的数字k不适合。
            board[x][y] = '.';
            setMemorandum(k,x,y);
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new Sudoku().solveSudoku(board);
    }
}
