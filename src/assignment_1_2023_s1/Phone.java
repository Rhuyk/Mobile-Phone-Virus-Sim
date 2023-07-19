/**
 * Question:
 * Which object(s) have you chosen for the synchronize? Why?
 * 
 * Answer:
 * I have chosen repairshop object for the synchronize,
 * because there can only exist one repairshop in the program
 * so that it can be shared by each phone one by one.
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1_2023_s1;

import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rh200
 */
public class Phone implements Runnable {
    int phoneNumber;
    int x;
    int y;
    int vx;
    int vy;
    int lifeSpan;
    int counter = 0;
    int width = 1000;
    int height = 650;
    boolean isInfected = false;
    boolean isRepairing = false;
    RepairShop repairshop;
    Random rand = new Random();
    
    public Phone (int phoneNumber, RepairShop repairshop)
    {
        this.phoneNumber = phoneNumber;
        this.lifeSpan = 500;
        this.x = rand.nextInt(1000);
        this.y = rand.nextInt(650);
        this.vx = rand.nextInt(9)-4;
        this.vy = rand.nextInt(9)-4;
        this.repairshop = repairshop;
    }
    
    public void run()
    {
        while(this.lifeSpan > 0)
        {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(this.isInfected)
            {
                this.lifeSpan--;
                synchronized(this.repairshop)
                {
                    if(this.repairshop.empty)
                    {
                        this.repairshop.empty = false;
                        this.isRepairing = true;
                    }
                }
                if(this.isRepairing)
                {
                    if(repairPhone())
                    {
                        phoneRepairing();
                    }
                }
                else
                {
                    move();
                }
            }
            else
            {
                move();
            }
        }
    }
    
    public void setRange(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    private void move()
    {
        if(this.x < 0 || this.x > this.width)
        {
            this.vx *= -1;
        }
        if(this.y < 0 || this.y > this.height)
        {
            this.vy *= -1;
        }
        
        this.x += vx;
        this.y += vy;
        changeDirection();
    }
    
    private void changeDirection()
    {
        this.counter++;
        if(this.counter == 50)
        {
            this.counter = 0;
            this.vx = rand.nextInt(9)-4;
            this.vy = rand.nextInt(9)-4;
        }
    }
    
    private boolean repairPhone()
    {
        if(this.x < this.repairshop.x)
        {
            this.vx = 3;
        }
        else if(this.x > this.repairshop.x)
        {
            this.vx = -3;
        }
        
        if(this.y < this.repairshop.y)
        {
            this.vy = 3;
        }
        else if(this.y > this.repairshop.y)
        {
            this.vy = -3;
        }
        this.x += vx;
        this.y += vy;
        return (abs(this.x - this.repairshop.x)<10 && abs(this.y - this.repairshop.y)<10);
    }
    
    private void phoneRepairing()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.lifeSpan = 500;
        this.isInfected = false;
        this.isRepairing = false;
        this.repairshop.empty = true;
    }
}
