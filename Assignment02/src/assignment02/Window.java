/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * The {@code Window} class simulates the 2d Rubiks cube game
 * 
 *
 * @author robert marton AY3VBL
 */

public class Window extends MyFrame {
    private int size;
    private Model model;
    private GMenu menu;
    private JButton[][] buttons; 
    private int turnCount;
    
    public Window(int size, GMenu menu){
        this.size = size;
        this.menu = menu;
        turnCount = 0;
        menu.getWindowList().add(this);
        
        JPanel topButtons = new JPanel();
        topButtons.setLayout(new GridLayout(1, size));
        for (int i = 0; i < size; i++){
            int index = i;
            JButton button = new JButton();
            button.setText("UP");
            button.addActionListener(e -> columnUp(index));
            topButtons.add(button);
        }
        
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(size, 1));
        for (int i = 0; i < size; i++){
            int index = i;
            JButton button = new JButton();
            button.setText("LEFT");
            button.addActionListener(e -> rowLeft(index));
            leftButtons.add(button);
        }
        
        JPanel rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayout(size, 1));
        for (int i = 0; i < size; i++){
            int index = i;
            JButton button = new JButton();
            button.setText("RIGHT");
            button.addActionListener(e -> rowRight(index));
            rightButtons.add(button);
        }
        
        JPanel downButtons = new JPanel();
        downButtons.setLayout(new GridLayout(1, size));
        for (int i = 0; i < size; i++){
            int index = i;
            JButton button = new JButton();
            button.setText("DOWN");
            button.addActionListener(e -> columnDown(index));
            downButtons.add(button);
        }
        
        
        model = new Model(size);
        model.shuffleColors();
        
        buttons = new JButton[size][size];
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                addButton(mainPanel, i , j);
            }
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topButtons, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(leftButtons, BorderLayout.WEST);
        getContentPane().add(rightButtons, BorderLayout.EAST);
        getContentPane().add(downButtons, BorderLayout.SOUTH);
        
        
    }
    
    /**
     * Adds static buttons to the main panel
     * 
     * @param panel
     * @param i
     * @param j 
     */
    private void addButton(JPanel panel, int i, int j){
        JButton button = new JButton();
        buttons[i][j] = button;
        Colors color = model.getColors(i, j);  
        
        JPanel colorPanel = new JPanel();
        
        if (color != null) { 
            switch (color) {
                case R:
                    colorPanel.setBackground(Color.RED);
                    break;
                case B:
                    colorPanel.setBackground(Color.BLUE);
                    break;
                case G:
                    colorPanel.setBackground(Color.GREEN);
                    break;
                case Y:
                    colorPanel.setBackground(Color.YELLOW);
                    break;
                case O:
                    colorPanel.setBackground(Color.ORANGE);
                    break;
                case P:
                    colorPanel.setBackground(Color.MAGENTA);  
                    break;
                default:
                    break;
            }
        }
        button.add(colorPanel);
        panel.add(button);
    }
    
    /**
     * Shifts column up by one
     * 
     * @param col 
     */
    private void columnUp(int col){
        turnCount++;
        model.shiftColumnUp(col);
        
        //Visual
        JButton firstButton = buttons[0][col];
        for (int i = 0; i < size - 1; i++){
            buttons[i][col] = buttons[i + 1][col];
        }
        buttons[size - 1][col] = firstButton;
        
        
        reAnimate();
        
        
        if (model.isComplete()){
            showMessage("You've completed the game in " + turnCount + " steps");
            reLoad();
        }
    }
    
    /**
     * Shifts column down by one
     * 
     * @param col 
     */
    private void columnDown(int col){
        turnCount++;
        model.shiftColumnDown(col);
        
        //Visual
        JButton lastButton = buttons[size - 1][col];
        for (int i = size - 1; i > 0; i--){
            buttons[i][col] = buttons[i - 1][col];
        }
        buttons[0][col] = lastButton;
        
        reAnimate();
        
        if (model.isComplete()){
            showMessage("You've completed the game in " + turnCount + " steps");
            reLoad();
        }
    }
    
    /**
     * Shifts row right by one
     * 
     * @param row 
     */
    private void rowRight(int row){
        turnCount++;
        model.shiftRowRight(row);
        
        //Visual
        JButton lastButton = buttons[row][size - 1];
        for (int j = size - 1; j > 0; j--){
            buttons[row][j] = buttons[row][j - 1];
        }
        buttons[row][0] = lastButton;
        
        
        reAnimate();
        
        if (model.isComplete()){
            showMessage("You've completed the game in " + turnCount + " steps");
            reLoad();
        }
    }
    
    
    /**
     * Shifts row left by one
     * 
     * @param row 
     */
    private void rowLeft(int row){
        turnCount++;
        model.shiftRowLeft(row);
        
        //Visual
        JButton firstButton = buttons[row][0];
        for (int j = 0; j < size - 1; j++){
            buttons[row][j] = buttons[row][j + 1];
        }
        buttons[row][size - 1] = firstButton;
        reAnimate();
        
        if (model.isComplete()){
            showMessage("You've completed the game in " + turnCount + " steps");
            reLoad();
        }
    }
    
    
    /**
     * Updates the static buttons on the main panel
     * 
     */
    private void reAnimate(){
        JPanel mainPanel = (JPanel) getContentPane().getComponent(1);
        mainPanel.removeAll();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                mainPanel.add(buttons[i][j]);
            }
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    /**
     * Winning message
     * 
     * @param message 
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Sucess!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * function which reloads the game
     * 
     */
    private void reLoad(){
        turnCount = 0;
        model.shuffleColors();
        
        JPanel mainPanel = (JPanel) getContentPane().getComponent(1);
        mainPanel.removeAll();
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                addButton(mainPanel, i , j);
            }
        }
        
    }
    
    /**
     * exits the program and removes the window from the window list
     */
    @Override
    protected void exiting(){
        super.exiting();
        menu.getWindowList().remove(this);
    }
}
