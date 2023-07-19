/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_1_2023_s1;


public class LinkedList <E extends Comparable>{
    public int size = 0;
    private Node head;
    
    public void addHead(E data)
    {
        head.data = data;
    }
    
    public Node getHead()
    {
        return head;
    }
    
    public void add(E data)
    {
        Node newNode = new Node();
        newNode.data = data;
        add(head, newNode);
        size++;
    }
    
    private void add(Node currentNode, Node node)
    {
        if(currentNode == null)
        {
            head = node;
        }
        else if(currentNode.next == null)
        {
            currentNode.next = node;
        }
        else
        {
            add(currentNode.next, node);
        }
    }
    
    public void printLinkedList()
    {
        printLinkedList(head);
    }
    
    private void printLinkedList(Node node)
    {
        if(node != null)
        {
            System.out.println(node.data);
            printLinkedList(node.next);
        }
    }
    
    public boolean contains(Node node)
    {
        return contains(head, node);
    }
    
    private boolean contains(Node currentNode, Node node)
    {
        if(currentNode == null)
        {
            return false;
        }
        else if(currentNode.equals(node))
        {
            return true;
        }
        else
        {
            return contains(currentNode.next, node);
        }
    }
    
    public void remove(Node node)
    {
        removeFromBody(head, node);
        size--;
    }
    
    public void remove(int position)
    {
        if(position >= 0 && position < size && getNode(position) != null)
        {
            removeByIndex(head, position);
            size--;
        }
    }
    
    private void removeByIndex(Node currentNode, int position)
    {
        if(currentNode != null)
        {
            if(position == 0 && head.next == null)
            {
                head = null;
            }
            else if(position == 0 && head.next != null)
            {
                head = head.next;
            }
            else if(position == 1 && currentNode.next != null)
            {
                currentNode.next = currentNode.next.next;
            }
            else
            {
                removeByIndex(currentNode.next, position-1);
            }
        }
    }
    
    private void removeFromBody(Node currentNode, Node node)
    {
        if(currentNode != null && node != null)
        {
            if(head.equals(node) && head.next == null)
            {
                head = null;
            }
            else if(head.equals(node) && head.next != null)
            {
                head = head.next;
            }
            else if(currentNode.next.equals(node))
            {
                currentNode.next = currentNode.next.next;
            }
            else
            {
                removeFromBody(currentNode.next, node);
            }
        }
    }
    
    public Node removeFromHead()
    {
        Node removedNode = getNode(0);
        remove(0);
        return removedNode;
    }
    
    public Node removeFromTail()
    {
        Node removedNode = getNode(size-1);
        remove(size-1);
        return removedNode;
    }
    
//    private Node removeFromTail(Node node)
//    {
//
//    }
    
    public void addInOrder(E data)
    {
        Node newNode = new Node();
        newNode.data = data;
        addInOrder(head, newNode);
        size++;
    }
    
    private void addInOrder(Node currentNode, Node newNode)
    {
        if(head == null || head.compareTo(newNode) >= 0)
        {
            newNode.next = head;
            head = newNode;
        }
        else if(currentNode.next == null || currentNode.next.compareTo(newNode) >= 0)
        {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        else
        {
            addInOrder(currentNode.next, newNode);
        }
    }
    
    public Node getNode(int index)
    {
        if(index >= 0 && index < size)
        {
            return getNode(index, head);
        }
        else
        {
            return null;
        }
    }
    
    private Node getNode(int index, Node currentNode)
    {
        if(index == 0)
        {
            return currentNode;
        }
        else
        {
            return getNode(index-1, currentNode.next);
        }
    }
    
    public E getData(int index)
    {
        return (E)getNode(index).data;
    }
    
//    private E getData(int index, Node head)
//    {
//
//    }    
}
