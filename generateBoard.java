package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Michael Raczkiewicz
 */
public class generateBoard
{
    static int[][] board = newBoardTemplate();
    
    public static void main(String[] args)
    {
        generateBoard();
        printBoard();
    }
    
    public static Boolean generateBoard()
    {
        for (int i = 0; i != 9; i += 1)
        {
            if (!generateRow(i))
            {
                board = newBoardTemplate();
                return generateBoard();
            }
        }
        return true;
    }
    
    public static void printBoard()
    {
        for (int i = 0; i != 9; i += 1)
        {
            System.out.println (Arrays.toString(board[i]));
        }
    }
    
    private static int[][] newBoardTemplate()
    {
        int[][] output = new int[9][9];
        return output;
    }
    
    private static Boolean generateRow(int row)
    {
        Random r = new Random();   
        int i = 0;
        while (i != 9)
        {
            int random = r.nextInt(9) + 1;
            ArrayList<Integer> registry = checkRegistry(row, i);
            if (!registry.contains(random))
            {
                board[row][i] = random;
                i += 1;
            }
            
            if (registry.size() == 9)
            {
                return false;
            }
        }
        return true;
    }
    
    private static ArrayList<Integer> checkRegistry(int x, int y)
    {
        ArrayList<Integer> avaliable = new ArrayList<>();
        for (int i = 0; i != 9; i += 1)
        {
            if (!avaliable.contains(board[x][i]) && board[x][i] != 0) avaliable.add(board[x][i]);
            if (!avaliable.contains(board[i][y]) && board[i][y] != 0) avaliable.add(board[i][y]);
        }
        
        for (int i = 0; i != 3; i += 1)
        {
            x = x/3 * 3;
            y = y/3 * 3;
            for (int j = 0; j != 3; j += 1)
            {
                if (!avaliable.contains(board[x + i][y +j]) && board[x + i][y + j] != 0) avaliable.add(board[x + i][y +j]);
            }
        }
        return avaliable;
    }
}
