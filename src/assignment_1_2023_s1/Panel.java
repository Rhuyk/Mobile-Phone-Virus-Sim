/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Panel extends JPanel implements KeyListener, ComponentListener {
    int width;
    int height;
    int totalPhones = 1;
    int infectedPhone = 0;
    int repairingPhone = 0;
    int infectionRange = 20;
    RepairShop repairshop = new RepairShop(485, 280);
    ArrayList<Phone> phones = new ArrayList<Phone>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    Random rand = new Random();
    
    Image image_repairshop;
    
    public Panel()
    {       
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);
        image_repairshop = new ImageIcon("repairshop_image.png").getImage();
        
        phones.add(new Phone(totalPhones-1, repairshop));
        threads.add(new Thread(phones.get(totalPhones-1)));
        this.threads.get(totalPhones-1).start();
    }
    
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        this.infectedPhone = 0;
        this.repairingPhone = 0;
        
        g.setColor(Color.WHITE);
        g.drawLine(460, 258, 520, 258);
        g.setColor(Color.WHITE);
        g.drawLine(460, 323, 520, 323);
        g.setColor(Color.WHITE);
        g.drawLine(460, 323, 460, 258);
        g.setColor(Color.WHITE);
        g.drawLine(520, 323, 520, 258);
        
        g.setColor(Color.WHITE);
        g.drawString("Repair", 472, 269);
        g.setColor(Color.WHITE);
        g.drawString("Shop", 475, 320);
        g.drawImage(image_repairshop, 470, 270, 40, 40, Color.WHITE, this);
        
        for(int i = 0; i < totalPhones; i++)
        {
            Phone aPhone = phones.get(i);
            if(aPhone.lifeSpan > 0)
            {
                g.setColor(Color.WHITE);
                g.fillRect(phones.get(i).x-2, phones.get(i).y-2, 14, 14);
                if(aPhone.isRepairing)
                {
                    g.setColor(Color.GREEN);
                    this.repairingPhone++;
                }
                else if(aPhone.isInfected)
                {
                    for(int j = 0; j < totalPhones; j++)
                    {
                        Phone bPhone = phones.get(j);
                        if(distance(aPhone, bPhone) < infectionRange)
                        {
                            bPhone.isInfected = true;
                        }
                    }
                    g.setColor(Color.RED);
                    this.infectedPhone++;
                }
                else
                {
                    g.setColor(Color.blue);
                }
                g.fillRect(phones.get(i).x, phones.get(i).y, 10, 10);
            }
            else
            {
                if(aPhone.isRepairing)
                {
                    this.repairshop.empty = true;
                }
                phones.remove(i);
                this.totalPhones--;
            }
        }
        g.setColor(Color.blue);
        g.drawString("Total Mobile Phones: "+this.totalPhones, 260, 20);
        g.setColor(Color.RED);
        g.drawString("Total Infected Phones: "+this.infectedPhone, 425, 20);
        g.setColor(Color.GREEN);
        g.drawString("Total Going to Repair: "+this.repairingPhone, 593, 20);
        repaint();
    }
    
    private double distance(Phone p1, Phone p2)
    {
        double value = Math.pow((p1.x - p2.x), 2.0D) + Math.pow((p1.y - p2.y), 2.0D);
        return Math.sqrt(value);
    }

    @Override
    public void keyTyped(KeyEvent ke) {        
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 38)
        {
            this.phones.add(new Phone(this.totalPhones, repairshop));
            this.threads.add(new Thread(this.phones.get(this.totalPhones)));
            this.threads.get(this.threads.size()-1).start();
            this.totalPhones++;
        }
        if(ke.getKeyCode() == 86)
        {
            int index = rand.nextInt(this.totalPhones);
            this.phones.get(index).isInfected = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }    

    @Override
    public void componentResized(ComponentEvent ce) {
        this.width = getWidth();
        this.height = getHeight();
        for(int i = 0; i < this.totalPhones; i++)
        {
            Phone aPhone = phones.get(i);
            aPhone.width = this.width;
            aPhone.height = this.height;
        }
    }

    @Override
    public void componentMoved(ComponentEvent ce) {

    }

    @Override
    public void componentShown(ComponentEvent ce) {

    }

    @Override
    public void componentHidden(ComponentEvent ce) {

    }
}
