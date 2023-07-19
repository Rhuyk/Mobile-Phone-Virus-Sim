/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;

import java.awt.Color;
import javax.swing.JFrame;


public class MobilePhoneVirusSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Mobile Phone Virus Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        
        frame.getContentPane().add(panel);
        frame.setSize(1000, 650);
        frame.setVisible(true);
        panel.setBackground(Color.BLACK);
    }
    
}
