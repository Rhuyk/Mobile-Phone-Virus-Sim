/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;


public class Stack <E extends Comparable>{
    private LinkedList<E> stack;
    
    public Stack()
    {
        stack = new LinkedList();
    }
    
    public void push(E data)
    {
        stack.add(data);
    }
    
    public E pop()
    {
        return (E)stack.removeFromTail().data;
    }
    
    public void printStack()
    {
        stack.printLinkedList();
    }
    
    public int getSize()
    {
        return stack.size;
    }
    
    public static void main(String[] args) {
        String word = "students.".toLowerCase();
        Stack<Character> stack = new Stack();
        for(int i = 0; i < word.length(); i++)
        {
            stack.push(word.charAt(i));
        }
        
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
