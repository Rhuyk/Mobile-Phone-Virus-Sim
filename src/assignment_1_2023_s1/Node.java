/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;


public class Node <E extends Comparable> {
    public E data;
    public Node next;  
    
    public boolean equals(Node node)
    {
        if(this.compareTo(node) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int compareTo(Node node)
    {
        return this.data.compareTo(node.data);
    }
}
