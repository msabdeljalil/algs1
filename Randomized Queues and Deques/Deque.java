public class Deque<Item> implements Iterable<Item> {
  private Node first = null;
  private Node last = null;
  private int size = 0;

  public Deque() {}
  
  private class Node() {
    Item item;
    Node next;
    Node prev;
  }
   
  public boolean isEmpty() {
    return (size == 0);
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    Node oldFirst = first
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    first.prev = null;
    if (isEmpty()) last = first;
    size++;
  }

  public void addLast(Item item) {
    Node oldLast = last
    last = new Node();
    last.item = item;
    last.next = null;
    last.prev = oldLast;
    if (isEmpty()) first = last;
    size++;
  }

  public Item removeFirst() {
    Item oldFirstItem = first.item;
    first = first.next;
    first.prev = null;
    size--;
    if (isEmpty()) last = first; // null
    return oldFirstItem;
  }

  public Item removeLast() {
    Node oldLastItem = last.item;
    last = last.prev;
    last.next = null;
    size--;
    if (isEmpty()) first = last; // null
    return oldLastItem;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator() {
    private Node current = first;

    public boolean hasNext() {
      return current != null
    }

    public Item next() {
        Item item = current.iterm;
        current = current.next;
        return item;
    }

    public void remove() {
        throw new UnsupportedOperationException("We don't support 'Remove'")
    }
  }

  public static void main(String[] args) {}
}


