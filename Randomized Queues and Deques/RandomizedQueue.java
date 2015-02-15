/*************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue < input.txt
 *
 *  A generic stack and que combined, implemented using a linked list. Each stack
 *  element is of type Item.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Node first = null;
  private int size = 0;

  public RandomizedQueue() { }
  
  private class Node {
    public Item item;
    public Node next;
    public Node prev;
  }
   
  public boolean isEmpty() {
    return (size == 0);
  }

  public int size() {
    return size;
  }

  /**
  * return (but do not remove) a random item
  */
  public Item sample() {
    checkEmptyStack();
    int rand = StdRandom.uniform(1, size + 1);
    Item current = null;

    Iterator<Item> iterator = this.iterator();
    for (int i = 0; i < rand; i++) {
      if (iterator.hasNext()) current = iterator.next();
    }
    return current;
  }

  public void enqueue(Item item) {
    checkNullItem(item);

    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;

    if (!isEmpty()) oldFirst.prev = first;;
    size++;
  }

  public Item dequeue() {
    checkEmptyStack();

    int rand = StdRandom.uniform(1, size + 1);
    Node current = first;

    for (int i = 1; i < rand; i++) {
      if (current.next != null ) current = current.next;
    }

    if (current.prev != null) current.prev.next = current.next;
    else { first = current.next; }
    if (current.next != null) current.next.prev = current.prev;    
    size--;

    return current.item;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    
    public boolean hasNext()  { return current != null;                     }
    public void remove()      { throw new UnsupportedOperationException();  }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<String> deck = new RandomizedQueue<String>();
    deck.enqueue("F");
    deck.enqueue("E");
    deck.enqueue("D");
    deck.enqueue("C");
    deck.enqueue("B");
    deck.enqueue("A");
    // StdOut.println("Sample: "+deck.sample());
    StdOut.println("Deque "+deck.dequeue());
    StdOut.println("Deque "+deck.dequeue());
    StdOut.println("Deque "+deck.dequeue());
    StdOut.println("Deque "+deck.dequeue());
    StdOut.println("Deque "+deck.dequeue());
    StdOut.println("Deque "+deck.dequeue());

    StdOut.println("("+deck.size()+" left on stack)");
 }

 private void checkNullItem(Item item) {
  if (item == null) throw new NullPointerException("That's a null item!");
 }

  private void checkEmptyStack() {
  if (size < 1) throw new NoSuchElementException("The deque is empty!");
 }

}
