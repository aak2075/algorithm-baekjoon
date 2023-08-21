import java.util.regex.*;
import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        String[][] all = new String[m][n];
        
        for(int i = 0; i < board.length; i++) {
            all[i] = board[i].split("");
        }
        
        boolean found = true;
        boolean[][] checked = new boolean[m][n];
        while(found) {
            found = false;
            for(int k = 0; k < m; k++) {
                Arrays.fill(checked[k], false);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(checkRecursive(all, i, j, checked, all[i][j])) {
                        found = true;
                        
                    };
                }
            }
            pang(all, checked);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    down(all, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(all[i][j].equals("0")) {
                    answer++;
                }
            }
        }
        
        return answer;    
    }
    
    private boolean checkRecursive(String[][] all, int y, int x, boolean[][] checked, String init) {
        if (x < 0 || x > all[0].length - 1 || y < 0 || y > all.length - 1) {
            return false;
        }
        
        if(!init.equals(all[y][x]) || all[y][x].equals("0")) {
            return false;
        }
        
        if (square(all, y, x, checked)) {   
            if (x < all[0].length - 1 && !checked[y][x+1]) {
                checkRecursive(all, y, x + 1, checked, init);
            }
            if (y < all.length - 1 && !checked[y+1][x]) {
                checkRecursive(all, y + 1, x, checked, init);    
            }
            if (x < all[0].length - 1 && !checked[y+1][x+1]) {
                checkRecursive(all, y + 1, x + 1, checked, init);
            }
            if (x > 0 && y < all.length - 1 && !checked[y + 1][x - 1] && checked[y + 1][x]) {
                checkRecursive(all, y + 1, x - 1, checked, init);
            }
            
            return true;
        }
        
        return false;
    }
    
    private boolean square(String[][] all, int y, int x, boolean[][] checked) {
        if (x >= all[0].length - 1 || y >= all.length - 1) {
            return false;
        }
        String current = all[y][x];
        
        if (current.equals(all[y][x+1]) && current.equals(all[y+1][x]) && current.equals(all[y+1][x+1])) {
            checked[y][x] = true;
            checked[y][x+1] = true;
            checked[y+1][x] = true;
            checked[y+1][x+1] = true;
            
            return true;
        }
        
        return false;
    }
    
    private void down(String[][]all, int i, int j) {
        if (i >= all.length - 1) {
            return;
        }
        if (!all[i][j].equals("0")) {
            down(all, i + 1, j);
            if (all[i + 1][j].equals("0")) {                
                all[i + 1][j] = all[i][j];
                all[i][j] = "0";
            }
        }
    }
    
    private void pang(String[][] all, boolean[][] checked) {
        for (int i = 0; i < checked.length; i++) {
            for(int j = 0; j < checked[0].length; j++) {
                if(checked[i][j]) {
                    all[i][j] = "0";
                }
            }
        }
    }
}
