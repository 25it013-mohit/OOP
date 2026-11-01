import java.util.Arrays;

class GenericStack<T> {
    private Object[] elements;
    private int top;
    private int capacity;

    public GenericStack(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        top = -1;
    }

    public void push(T value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }

        elements[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }

        T value = (T) elements[top];
        elements[top--] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Nothing to peek.");
            return null;
        }

        return (T) elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        System.out.print("Stack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {
        GenericStack<Integer> intStack = new GenericStack<>(5);

        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        System.out.println("Integer Stack:");
        intStack.display();
        System.out.println("Popped: " + intStack.pop());
        System.out.println("Top element: " + intStack.peek());

        System.out.println();

        GenericStack<String> stringStack = new GenericStack<>(5);

        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++");

        System.out.println("String Stack:");
        stringStack.display();
        System.out.println("Popped: " + stringStack.pop());
        System.out.println("Top element: " + stringStack.peek());

        System.out.println();

        GenericStack<Integer> emptyStack = new GenericStack<>(3);
        System.out.println("Empty Stack Test:");
        emptyStack.pop();
        emptyStack.peek();
    }
}