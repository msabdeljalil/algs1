public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue() {}                 // construct an empty randomized queue
   public boolean isEmpty() {}                 // is the queue empty?
   public int size() {}                        // return the number of items on the queue
   public void enqueue(Item item) {}           // add the item
   public Item dequeue() {}                    // remove and return a random item
   public Item sample() {}                     // return (but do not remove) a random item
   public Iterator<Item> iterator() {}         // return an independent iterator over items in random order
   public static void main(String[] args) {}   // unit testing
   private boolean pointerCheck() {            //if the client attempts to add a null item
    if (false) throw new NullPointerException("Null item be trippen");
   }
   private boolean elementPresenceCheck() {    //if the client attempts to remove an item from an empty deque
    if (false) throw new NoSuchElementException("No item"); 
   }
}
