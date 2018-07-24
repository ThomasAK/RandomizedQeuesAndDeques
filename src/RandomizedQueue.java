import edu.princeton.cs.algs4.ResizingArrayQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private ResizingArrayQueue<Item> random;

    public RandomizedQueue() {
        random = new ResizingArrayQueue<>();
    }

    public boolean isEmpty() {
        return random.isEmpty();
    }

    public int size() {
        return random.size();
    }

    public void enqueue(Item item) {
        try {
            if (item == null) {
                System.out.println("Unexpected: The value was null or invalid");
            } else {
                random.enqueue(item);
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public Item dequeue() {
        try {
            if (!isEmpty()) {
                random = randomize(random);
                return random.dequeue();
            } else {
                System.out.println("No elements exist in Deque");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return null;
    }

    public Item sample() {
        try {
            if (!isEmpty()) {
                random = randomize(random);
                return random.peek();
            } else {
                System.out.println("No elements exist in Deque");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        random = randomize(random);
        return random.iterator();
    }

    private ResizingArrayQueue<Item> randomize(ResizingArrayQueue<Item> in) {
        ResizingArrayQueue<Item> first = new ResizingArrayQueue<>();
        ResizingArrayQueue<Item> second = new ResizingArrayQueue<>();
        ResizingArrayQueue<Item> third = new ResizingArrayQueue<>();
        int s = in.size() - 1;
        if (!in.isEmpty()) {
            for (int i = 0; i < s; i++) {
                int n = (int) (Math.random() * 3);
                if (n == 0 && !in.isEmpty()) {
                    first.enqueue(in.dequeue());
                }
                if (n == 1 && !in.isEmpty()) {
                    second.enqueue(in.dequeue());
                }
                if (n == 2 && !in.isEmpty()) {
                    third.enqueue(in.dequeue());
                }
            }
            for (int i = 0; i < s; i++) {
                if (!third.isEmpty()) {
                    in.enqueue(third.dequeue());
                } else if (!first.isEmpty()) {
                    in.enqueue(first.dequeue());
                } else if (!second.isEmpty()) {
                    in.enqueue(second.dequeue());
                }
            }
        }
        return in;
    }


    public static void main(String[] args) {

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("Waffles");
        randomizedQueue.enqueue("Test");
        randomizedQueue.enqueue("Random");
        randomizedQueue.enqueue("Bananas");
        randomizedQueue.enqueue("Tacos");
        System.out.println("Random Queue Size;" + randomizedQueue.size());
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println(randomizedQueue.sample());
        System.out.println();

        randomizedQueue.dequeue();
        System.out.println("Random queue empty: " + randomizedQueue.isEmpty());
        System.out.println("Random Queue Size: " + randomizedQueue.size());
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        System.out.println();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        System.out.println("Random queue empty: " + randomizedQueue.isEmpty());
        System.out.println("Random Queue Size: " + randomizedQueue.size());
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        System.out.println();
        System.out.println("Random queue empty: " + randomizedQueue.isEmpty());
        System.out.println("Random Queue Size: " + randomizedQueue.size());

    }
}
