package com.company;

public class Queue
{
    private int maxSize;
    private String[] queueArray;
    private int front;
    private int rear;
    private int currentSize;
    private static int queue[];

    public Queue(int size)
    {
        this.maxSize = size;
        this.queueArray = new String[size];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void insert(String item)
    {

        if(isQueueFull())
        {
            System.out.println("Queue is full!");
            return;
        }
        if(rear == maxSize - 1)
        {
            rear = -1;
        }

        queueArray[++rear] = item;
        currentSize++;
        System.out.println("Item added to queue: " + item);
    }

    public String delete()
    {

        if(isQueueEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }

        String temp = queueArray[front++];
        if(front == maxSize)
        {
            front = 0;
        }
        currentSize--;
        return temp;
    }

    public String peek()
    {
        return queueArray[front];
    }

    public boolean isQueueFull()
    {
        return (maxSize == currentSize);
    }

    public boolean isQueueEmpty()
    {
        return (currentSize == 0);
    }

    public void queueDisplay()
    {
        int i;
        if (front == rear) {
            System.out.printf("Queue is Empty\n");
            return;
        }

        // traverse front to rear and print elements
        for (i = front; i < rear; i++) {
            System.out.printf(" %d , ", queue[i]);
        }
        return;
    }

    public static void main(String[] args)
    {
//        Queue queue = new Queue(10);
//        queue.insert(2);
//        queue.insert(3);
//        System.out.println("Item deleted from queue: " + queue.delete());
//        System.out.println("Item deleted from queue: " + queue.delete());
//        queue.insert(5);
//        System.out.println("Item deleted from queue: " + queue.delete());
    }
}