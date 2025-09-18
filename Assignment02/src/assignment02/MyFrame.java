/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment02;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * The {@code MyFrame} class defines the size and exit on each window
 * 
 * @author robert marton AY3VBL
 */
public class MyFrame extends JFrame {
    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent e){
                exiting();
            }
        });
        
        
        
        this.setTitle("2D Rubik's Cube");
        this.setSize(600, 600);
    }
    
    
    /**
     * exits the program
     */
    protected void exiting(){
        this.dispose();
    }
}
