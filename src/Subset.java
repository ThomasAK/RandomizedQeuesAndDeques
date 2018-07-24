import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        System.out.println("");
        String str[] = StdIn.readString().split(" ");
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (String aStr : str) {
            randomizedQueue.enqueue(aStr);
        }
        for (String arg : args) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
