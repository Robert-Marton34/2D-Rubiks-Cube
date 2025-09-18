/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment02;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * The {@code GMenu} class is the menu window that opens the game based on the options
 * 
 * @author robert marton AY3VBL
 */
public class GMenu extends MyFrame{
    private List<Window> gwindows = new ArrayList<>();
    
    public GMenu(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton easy = new JButton();
        JButton medium = new JButton();
        JButton hard = new JButton();
        
        easy.setText("Easy");
        medium.setText("Medium");
        hard.setText("Hard");
        
        easy.addActionListener(getActionListener(2));
        medium.addActionListener(getActionListener(4));
        hard.addActionListener(getActionListener(6));
        
        buttonPanel.add(easy);
        buttonPanel.add(medium);
        buttonPanel.add(hard);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }
    
    /**
     * Action listener to open window
     * 
     * @param size
    */
    private ActionListener getActionListener(int size){
        return new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                Window window = new Window(size, GMenu.this);
                window.setVisible(true);
                gwindows.add(window);
            }
        };
    }
    
    public List<Window> getWindowList(){
        return gwindows;
    }
    
    /**
     * terminates the program
     */
    @Override
    protected void exiting(){
        System.exit(0);
    }
}
