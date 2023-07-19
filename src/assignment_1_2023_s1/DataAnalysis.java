/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;


public class DataAnalysis <E extends Comparable>{
    private Queue <E> queue = new Queue();
    private Stack <E> stack = new Stack();
    private E[] data;
    
    public DataAnalysis(E[] data)
    {
        this.data = data;
        for(int i = 0; i < data.length; i++)
        {
            queue.enqueue(data[i]);
            stack.push(data[i]);
        }
    }
    
    public boolean bracketEvaluator()
    {
        Stack<Character> bracketStack = new Stack();
        
        for (E element : data) {
            String str = element.toString();
            for(int i = 0; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if(ch == '(' || ch == '{' || ch == '[' || ch == '<')
                {
                    bracketStack.push(ch);
                }
                else if(ch == ')' || ch == '}' || ch == '}' || ch == '>')
                {
                    if (bracketStack.getSize() == 0) {
                        return false;
                    }
                    
                    char top = bracketStack.pop();
                    if ((top == '(' && ch != ')') || (top == '{' && ch != '}') || (top == '[' && ch != ']') || (top == '<' && ch != '>')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isSymmetrical()
    {
        boolean symmetry = true;
        for(int i = 0; i < data.length; i++)
        {
            if(!queue.dequeue().equals(stack.pop()))
            {
                symmetry = false;
            }
        }
        return symmetry;
    }
}
