
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Box<Item> firstBox = new Box<>(), lastBox = new Box<>();
    private int size;

    public Deque() {
        firstBox.next = lastBox;
        lastBox.prev = firstBox;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        try {
            if (item == null) {
                System.out.println("Unexpected: The value was null or invalid");
            } else {
                Box<Item> newBox = new Box<>(item);
                Box<Item> next = firstBox.next;
                firstBox.next = newBox;
                newBox.next = next;
                newBox.prev = firstBox;
                next.prev = newBox;
                size++;
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void addLast(Item item) {
        try {
            if (item == null) {
                System.out.println("Unexpected: The value was null or invalid");
            } else {
                Box<Item> newBox = new Box<>(item);
                Box<Item> prev = lastBox.prev;
                lastBox.prev = newBox;
                newBox.prev = prev;
                newBox.next = lastBox;
                prev.next = newBox;
                size++;
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public Item removeFirst() {
        try {
            if (!isEmpty()) {
                Box<Item> next = firstBox.next;
                next.next.prev = firstBox;
                firstBox.next = next.next;
                size--;
                return (Item) next;
            } else {
                System.out.println("No elements exist in Deque");
            }
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
        return null;
    }

    public Item removeLast() {
        try{
        if (!isEmpty()) {
            Box<Item> prev = lastBox.prev;
            prev.prev.next = lastBox;
            lastBox.prev = prev.prev;
            size--;
            return (Item) prev;
        }else {
            System.out.println("No elements exist in Deque");
        }
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Iterator<Item> iterator() {

        return new DequeIterator<>(firstBox);
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Box<Item> nextBox;

        private DequeIterator(Box<Item> nextBox) {
            this.nextBox = nextBox;
        }

        @Override
        public boolean hasNext() {
            return nextBox.next != null && nextBox.next != lastBox;
        }

        @Override
        public void remove() { throw new UnsupportedOperationException();  }

        @Override
        public Item next() {
            try {
                if (!isEmpty()) {
                    nextBox = nextBox.next;
                    return nextBox.item;
                }else {
                    System.out.println("No elements exist in Deque");
                }
            }
            catch (NoSuchElementException e){
                System.out.println(e);
            }
            return null;
        }
    }

    private class Box<Item> {
        private Item item;
        private Box<Item> next;
        private Box<Item> prev;

        public Box(Item item) {
            this.item = item;
        }

        public Box() {
        }
    }

    public static void main(String[] args) {
        Deque<String> deques = new Deque<>();
        deques.addFirst("String");
        deques.addFirst("RobertSucks");
        deques.addFirst("Sucks");
        deques.addFirst("Deque of Deques");
        deques.addFirst("Deque");
        deques.addFirst("Waffles");
        deques.addFirst("foo");
        deques.addLast("Last");
        for (String s : deques) {
            System.out.println(s);
        }
        deques.removeFirst();
        deques.removeLast();
        System.out.println();
        System.out.println("Deque is empty: " + deques.isEmpty());
        System.out.println("Deque Size: " + deques.size);
        for (String s : deques) {
            System.out.println(s);
        }
        deques.removeFirst();
        deques.removeLast();
        System.out.println();
        System.out.println("Deque is empty: " + deques.isEmpty());
        System.out.println("Deque Size: " + deques.size);
        for (String s : deques) {
            System.out.println(s);
        }
        deques.removeFirst();
        deques.removeLast();
        System.out.println();
        System.out.println("Deque is empty: " + deques.isEmpty());
        System.out.println("Deque Size: " + deques.size);
        for (String s : deques) {
            System.out.println(s);
        }
        deques.removeFirst();
        deques.removeLast();
        System.out.println();
        System.out.println("Deque is empty: " + deques.isEmpty());
        System.out.println("Deque Size: " + deques.size);
    }
}
