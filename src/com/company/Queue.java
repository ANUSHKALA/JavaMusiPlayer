package com.company;

public class Queue {
    static int SIZE = 20;
    static String[] items = new String[SIZE];
    static int front, rear;

    Queue() {
        front = -1;
        rear = -1;
    }

    // check if the queue is full
    static boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        return false;
    }

    // check if the queue is empty
    static boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    // insert elements to the queue
    static void enQueue(String element) {

        // if queue is full
        if (isFull()) {
            System.out.println("Queue is full");
        }
        else {
            if (front == -1) {
                // mark front denote first element of queue
                front = 0;
            }

            rear++;
            // insert element at the rear
            items[rear] = element;
            System.out.println("Insert " + element);
        }
    }

    // delete element from the queue
    static String deQueue() {
        String element;

        // if queue is empty
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return (null);
        }
        else {
            // remove element from the front of queue
            element = items[front];

            // if the queue has only one element
            if (front >= rear) {
                front = -1;
                rear = -1;
            }
            else {
                // mark next element as the front
                front++;
            }
            System.out.println( element + " Deleted");
            return (element);
        }
    }

    // display element of the queue
    static void display() {
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        }
        else {
            // display the front of the queue
            System.out.println("\nFront index-> " + front);

            // display element of the queue
            System.out.println("Items -> ");
            for (i = front; i <= rear; i++)
                System.out.print(items[i] + "  ");

            // display the rear of the queue
            System.out.println("\nRear index-> " + rear);
        }
    }

    public static void main(String[] args) {

        // create an object of Queue class
        Queue q = new Queue();

        // try to delete element from the queue
        // currently queue is empty
        // so deletion is not possible
        q.deQueue();

        // insert elements to the queue
//        for(int i = 1; i < 6; i ++) {
//            q.enQueue(i);
//        }

        // 6th element can't be added to queue because queue is full
        q.enQueue("a");
        q.enQueue("b");
        q.enQueue("c");
        q.enQueue("d");

        q.display();

        q.enQueue("e");

        // deQueue removes element entered first i.e. 1
        q.deQueue();

        // Now we have just 4 elements
        q.display();

    }
}