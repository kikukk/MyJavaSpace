package ex6;

public class J_Grid15 {
    private int[][] m_board;
    private J_Grid15(){
        m_board = new int[3][3];
    }

    //杈撳嚭妫嬬洏鐨勬牸绾胯
    private void mb_outputGridRowBoard(){
        int i;
        System.out.print("+");
        for(i = 0;i < 5;i++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    //杈撳嚭妫嬬洏鐨勬暟鎹锛堢i琛岋紝i鍙兘涓�0/1/2锛�
    private void mb_outputGridRowBoard(int i){
        int j;
        for(j = 0;j<m_board[i].length;j++){
            System.out.print("|"+m_board[i][j]);
        }
        System.out.println("|");
    }

    //杈撳嚭妫嬬洏
    private void mb_outputGrid(){
        int i;
        mb_outputGridRowBoard();
        for(i = 0;i < m_board.length;i++){
            mb_outputGridRowBoard(i);
            mb_outputGridRowBoard();
        }
    }

    //鍒濆鍖栨暟鎹�
    private void mb_dataInit(){
        int i,j,k;
        for(i = 0,k = 1;i < m_board.length;i++){
            for(j = 0;j < m_board[i].length;j++,k++){
                m_board[i][j] = k;
            }
        }
    }

    //鏁版嵁缁撴潫妫�娴�
    //杩斿洖鍊艰鏄庯細褰撴暟鎹负鏈�鍚庝竴涓暟鎹椂锛岃繑鍥瀟rue;鍚﹀垯锛岃繑鍥瀎alse
    private boolean mb_dataEnd(){
        int i,j,k;
        for(i = 0,k = 9;i < m_board.length;i++){
            for(j = 0;j < m_board[i].length;j++,k--){
                if(m_board[i][j] !=k){
                    return false;
                }
            }
        }
        return true;
    }

    //鍙栦笅涓�涓暟鎹�
    private void mb_dataNext(){
        int i,j;
        for(i = m_board.length-1;i>=0;i--){
            for(j = m_board[i].length-1;j>=0;j--){
                if(m_board[i][j] == 9){
                    m_board[i][j] = 1;
                }
                else{
                    m_board[i][j]++;
                    return;
                }
            }
        }
    }

    //鏁版嵁妫�娴嬶細鍒ゆ柇鏁版嵁涓槸鍚﹀惈鏈夌浉鍚岀殑鏁板瓧
    //褰撴暟鎹腑瀛樺湪鐩稿悓鏁板瓧鏃讹紝杩斿洖false;鍚﹀垯锛岃繑鍥瀟rue
    private boolean mb_dataCheckDifferent(){
        int i,j;
        int[] digit = new int[10];
        for(i = 0;i < m_board.length;i++){
            for(j = 0;j < m_board[i].length;j++){
                digit[m_board[i][j]] = 1;
            }
        }
        for(i = 1,j = 0;i < digit.length;i++){
            j+=digit[i];
        }
        return j == 9;
    }

    //鏁版嵁妫�娴嬶細鍚勮鍜屾槸鍚︿负15
    //褰撳悇琛屽拰鍧囦负15鏃讹紝杩斿洖true;鍚﹀垯锛岃繑鍥瀎alse
    private boolean mb_dataCheckSumRow(){
        int i,j,k;
        for(i = 0;i < m_board.length;i++){
            for(j = 0,k = 0;j < m_board[i].length;j++){
                k+=m_board[i][j];
            }
            if(k!=15){
                return false;
            }
        }
        return true;
    }

    //鏁版嵁妫�娴嬶細鍚勫垪鍜屾槸鍚︿负15
    //褰撳悇鍒楀拰鍧囦负15鏃讹紝杩斿洖true;鍚﹀垯锛岃繑鍥瀎alse
    private boolean mb_dataCheckSumColumn(){
        int i,j,k;
        for(i = 0;i < m_board.length;i++){
            for(j = 0,k = 0;j < m_board.length;j++){
                k+=m_board[j][i];
            }
            if(k!=15){
                return false;
            }
        }
        return true;
    }

    private boolean mb_dataCheck(){
        if(!mb_dataCheckDifferent()){
            return false;
        }
        if(!mb_dataCheckSumRow()){
            return false;
        }
        if(!mb_dataCheckSumColumn()){
            return false;
        }
        //妫�娴嬪瑙掔嚎涔嬪拰鏄惁涓�15
        if(m_board[0][0]+m_board[1][1]+m_board[2][2]!=15){
            return false;
        }
        //妫�娴嬪瑙掔嚎涔嬪拰鏄惁涓�15
        return m_board[0][2] + m_board[1][1] + m_board[2][0] == 15;
    }

    //姹傝В骞惰緭鍑烘鐩橀棶棰�
    private void mb_arrange(){
        int n = 1;
        for(mb_dataInit();!mb_dataEnd();mb_dataNext()){
            if(mb_dataCheck()){
                System.out.println("绗�"+n+"涓粨鏋滐細");
                n++;
                mb_outputGrid();
            }
        }
    }

    public static void main(String[] args) {
        J_Grid15 a = new J_Grid15();
        a.mb_arrange();
    }




}
