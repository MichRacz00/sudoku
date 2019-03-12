package sudoqu;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author michael
 */
public class Board
{
    public static void main(String[] args)
    {
        
        int[][] board = generate();
        print(board);
    }
    
    public static int[][] generate()
    {
        int[][] output = new int[9][9];
        int[][][] registry = generateRegistry();
        
        
        int k = 0;
        while (k != 9)
        {
            int l = 0;
            
            
            while (l != 9)
            {
                if (arrayIsEmpty(registry[k][l]))
                {
                    l -= 1;
                    registry = addToRegistry(registry, k, l, output[k][l]);
                }
                else
                {
                    int random = getRandom(registry[k][l]);
                    registry = removeFromRegistry (registry, k, l, random);
                    output[k][l] = random;
                    l += 1;
                }
            }
            
            
            print (output);
            System.out.println ("=================");
            
            k += 1;
        }
        
        return output;
    }
    
    public static Boolean arrayIsEmpty(int[] array)
    {
        int zeros = 0;
        for (int i = 0; i != array.length; i += 1)
        {
            if (array[i] == 0) zeros += 1;
        }
        
        return zeros == array.length;
    }
    
    public static int getRandom(int[] array)
    {
        Random r = new Random();
        int index = r.nextInt(array.length);
        
        if (array[index] == 0) return getRandom(array);
        
        return array[index];
    }
    
    public static int[][][] removeFromRegistry (int[][][] reg, int y, int x, int number)
    {
        for (int i = 0; i != 9; i += 1) reg[y][i][number - 1] = 0;
        for (int i = 0; i != 9; i += 1) reg[i][x][number - 1] = 0;
        
        x = getSquareLocation(x);
        y = getSquareLocation(y);
        
        for (int i = x; i != x + 3; i += 1)
        {
            for (int j = y; j != y + 3; j += 1)
            {
                reg[j][i][number - 1] = 0;
            }
        }
        
        return reg;
    }
    
    public static int[][][] addToRegistry (int[][][] reg, int y, int x, int number)
    {
        for (int i = 0; i != 9; i += 1) reg[y][i][number - 1] = number;
        for (int i = 0; i != 9; i += 1) reg[i][x][number - 1] = number;
        
        x = getSquareLocation(x);
        y = getSquareLocation(y);
        
        for (int i = x; i != x + 3; i += 1)
        {
            for (int j = y; j != y + 3; j += 1)
            {
                reg[j][i][number - 1] = number;
            }
        }
        
        return reg;
    }
    
    public static int getSquareLocation(int i)
    {
        if (i > 2)
        {
            if (i > 5)
            {
                i = 6;
            }
            else
            {
                i = 3;
            }
        }
        else
        {
            i = 0;
        }
        
        return i;
    }
    
    public static int[][][] generateRegistry()
    {
        int[][][] output = new int[9][9][9];
        for (int k = 0; k != output.length; k += 1)
        {
            for (int l = 0; l != output[k].length; l += 1)
            {
                for (int m = 0; m != output[k][l].length; m += 1) output[k][l][m] = m + 1;
            }
        }
        return output;
    }
    
    public static void print(int[][] data)
    {
        for (int i = 0; i != data.length; i += 1)
        {
            for (int j = 0; j != data[i].length; j += 1) System.out.print (data[i][j]+" ");
            System.out.println ();
        }
    }
    
    //temporary
    
    public static void printRegistry(int[][][] reg)
    {
        for (int k = 0; k != reg.length; k += 1)
        {
            for (int l = 0; l != reg[k].length; l += 1)
            {
                System.out.println (Arrays.toString(reg[k][l]));
            }
            System.out.println (k + "---------------------------");
        }
    }
}
