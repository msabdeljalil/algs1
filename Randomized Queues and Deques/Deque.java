/*************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque < input.txt
 *
 *  A generic stack and que combined, implemented using a linked list. Each stack
 *  element is of type Item.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first = null;
  private Node last = null;
  private int size = 0;

  public Deque() { }
  
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

  public void addFirst(Item item) {
    checkNullItem(item);

    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;

    if (isEmpty()) last = first;
    else {oldFirst.prev = first;}
    size++;
  }

  public void addLast(Item item) {
    checkNullItem(item);

    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.prev = oldLast;

    if (isEmpty()) first = last;
    else {oldLast.next = last;}
    size++;
  }

  public Item removeFirst() {
    checkEmptyStack();

    Node oldFirst = first;
    first = first.next;
    size--;

    if (isEmpty()) last = first;
    else {first.prev = null;}
    return oldFirst.item;
  }

  public Item removeLast() {
    checkEmptyStack();

    Node oldLast = last;
    last = last.prev;
    size--;

    if (isEmpty()) first = last;
    else {last.next = null;}
    return oldLast.item;
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
    Deque<String> deck = new Deque<String>();
    deck.addFirst("the");
    deck.addFirst("is");
    deck.addFirst("This");
    deck.addLast("END");

    StdOut.println(deck.removeFirst());
    StdOut.println(deck.removeFirst());
    StdOut.println(deck.removeFirst());
    StdOut.println(deck.removeLast());
    StdOut.println(deck.removeLast());

    StdOut.println("(" + deck.size() + " left on stack)");
 }

 private void checkNullItem(Item item) {
  if (item == null) throw new NullPointerException("That's a null item!");
 }

  private void checkEmptyStack() {
  if (size < 1) throw new NoSuchElementException("The deque is empty!");
 }

}
