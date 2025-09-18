/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment02;

import java.util.Random;
/**
 * The {@code Model} class defines the rubiks cube
 * 
 * @author robert
 */
public class Model {
    private int size;
    private Colors color;
    private Colors[][] table;
    
    public Model(int size) {
        this.size = size;
        
        table = new Colors[size][size];
        if (size == 2){
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    if (i == 0){
                        table[i][j] = Colors.B;
                    } else {
                        table[i][j] = Colors.R;
                    }
                }
            }
        }
        
        if (size == 4){
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    switch (i) {
                        case 0:
                            table[i][j] = Colors.B;
                            break;
                        case 1:
                            table[i][j] = Colors.R;
                            break;
                        case 2:
                            table[i][j] = Colors.G;
                            break;
                        case 3:
                            table[i][j] = Colors.Y;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
        if (size == 6){
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    switch (i) {
                        case 0:
                            table[i][j] = Colors.B;
                            break;
                        case 1:
                            table[i][j] = Colors.R;
                            break;
                        case 2:
                            table[i][j] = Colors.G;
                            break;
                        case 3:
                            table[i][j] = Colors.Y;
                            break;
                        case 4: 
                            table[i][j] = Colors.O;                           
                            break;
                        case 5: 
                            table[i][j] = Colors.P;                            
                            break;
                        default:
                            break;
                    }
                }
            }
        }  
    }
    
    /**
     * Shuffles the colors on the table
     */
    
    public void shuffleColors() {
        Random random = new Random();
        int total = size * size;
        
        Colors[] flatten = new Colors[total];
        int index = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                flatten[index++] = table[i][j];
            }
        }
        
        for (int i = total - 1; i > 0; i--){
            int j = random.nextInt(i + 1);
            Colors temp = flatten[i];
            flatten[i] = flatten[j];
            flatten[j] = temp;
        }
        
        index = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                table[i][j] = flatten[index++];
            }
        }
    }
    
    /**
     * shifts row to the right by one
     * @param row 
     */
    
    public void shiftRowRight(int row){
        Colors lastElement = table[row][size - 1];
        for (int j = size - 1; j > 0; j--){
            table[row][j] = table[row][j - 1];
        }
        table[row][0] = lastElement;
    }
    
    /**
     * shifts row to the left by one
     * @param row 
     */
    
    public void shiftRowLeft(int row){
        Colors firstElement = table[row][0];
        for (int j = 0; j < size - 1; j++){
            table[row][j] = table[row][j + 1];
        }
        table[row][size - 1] = firstElement; 
    }
    
    /**
     * shifts column up by one
     * @param col 
     */
    
    public void shiftColumnUp(int col){
        Colors firstElement = table[0][col];
        for(int i = 0; i < size - 1; i++){
            table[i][col] = table[i + 1][col];
        }
        table[size - 1][col] = firstElement;
    }
    
    /**
     * shifts column down by one
     * @param col 
     */
    
    public void shiftColumnDown(int col){
        Colors lastElement = table[size - 1][col];
        for(int i = size - 1; i > 0; i--){
            table[i][col] = table[i - 1][col];
        }
        table[0][col] = lastElement;
    } 
    
    /**
     * checks if the rubiks cube is complete (each row or column have the same color)
     * @return 
     */
    
    public boolean isComplete(){
        boolean isRowComplete = true;
        for (int i = 0; i < size; i++){
            Colors firstColor = table[i][0];
            for (int j = 1; j < size; j++){
                if (table[i][j] != firstColor){
                    isRowComplete = false;
                    break;
                }
            }
        }
        if (!isRowComplete){
            boolean isColumnComplete = true;
            for (int j = 0; j < size; j++) {
                Colors firstColor = table[0][j];
                for (int i = 1; i < size; i++){
                    if (table[i][j] != firstColor){
                        isColumnComplete = false;
                        break;
                    }
                }
            }
            return isColumnComplete;
        } else { return true;}
    }
    
    public Colors getColors(int i, int j){
        return table[i][j];
    }
}
