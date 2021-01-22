import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;
    public LinkedList() {
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        Node<T> temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public boolean add(T data) {
        Node<T> node = tail;
        Node<T> newNode = new Node<>(node, data, null);
        tail = newNode;
        if (node == null)
            head = newNode;
        else
            node.next = newNode;
        size++;
        return false;
    }

    public boolean contains(Object o) {
        Node<T> node = head;
        while (node != null) {
            if ((node.data) == o) {
                return true;
            }
            node = node.next;
        }
        return false;
    }



    public int indexOf(Object o) {
        int index = 0;
        Node<T> node = head;
        while (node != null) {
            if ((node.data) == o) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = 0;
        Node<T> node = tail;
        while (node != null) {
            if ((node.data) == o) {
                return index;
            }
            node = node.prev;
            index--;
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + "Size:" + size);
        }
        Node<T> node = head;
        while (node != null) {
            if (indexOf(node.data) == index) {
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + "Size:" + size);
        }
        Node<T> node = head;
        while (node != null) {
            if (indexOf(node.data) == index) {
                node.data = element;
            }
            node = node.next;
        }
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = head; x != null; x = x.next)
            result[i++] = x.data;
        return result;
    }

    public void clear() {
        Node<T> node = head;
        while (node != null) {
            Node<T> next = node.next;
            node.data = null;
            node.next = null;
            node.prev= null;
            node = next;
        }
        head = tail = null;
        size = 0;
    }

    public T middle() {
        if (size % 2 == 0) {
            return null;
        }
        return get(size / 2);
    }

    public boolean isCyclic() {
        Node<T> fast = head;
        Node<T> slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public void pairWiseSwap() {
        Node<T> temp = head;
        while (temp != null && temp.next != null) {
            T k = temp.data;
            temp.data = temp.next.data;
            temp.next.data = k;
            temp = temp.next.next;
        }
    }

    public boolean remove(T item) {
        int index = this.indexOf(item);
        System.out.println(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
        return true;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void addFirst(T o) {
        final Node<T> f = head;
        final Node<T> newNode = new Node<>(null, o, f);
        head = newNode;
        if (f == null)
            tail = newNode;
        else
            f.prev = newNode;
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + "Size:" + size);
        } else {
            Node<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            curr.next = new Node<>(curr.prev, element, curr.next);
            size++;
        }
    }

    public void addLast(T o) {
        add(size,o);
    }

    public boolean offerFirst(T o) {
        addFirst(o);
        return true;
    }

    public boolean offerLast(T o) {
        addLast(o);
        return true;
    }

    public T peekFirst() {
        final Node<T> f = head;
        return (f == null) ? null : f.data;
    }

    public T peekLast() {
        final Node<T> l = tail;
        return (l == null) ? null : l.data;
    }

    public T element() {
        final Node<T> node = head;
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.data;
    }

    public void remove() {
        head = head.next;
        size--;
    }

    public void removeFirst() {
        remove();
    }

    public void removeLast() {
        tail = tail.prev;
        size--;
    }

    public Object getFirst()
    {
        return head.data;
    }

    public Object getLast() {
        return tail.data;
    }

    public Object pollFirst() {
        Node<T> node = head;
        removeFirst();
        return node.data;
    }

    public Object pollLast() {
        Node<T> node = tail;
        removeLast();
        return node.data;
    }

    public boolean offer(T item) {
        addLast(item);
        return true;
    }

    public Object poll() {
        return pollFirst();
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public void push(T item) {
        addFirst(item);
    }

    public Object pop()
    {
        return pollFirst();
    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public LinkedList<T> sublist(int from, int to) {
        if (from > to || from < 0 || to > size) {
            throw new IndexOutOfBoundsException("From: " + from + " To: " + to + "Size:" + size);
        }
        for (int i = 0; i < from; i++) {
            remove(get(i));
        }
        for (int i = from; i < to; i++) {
            remove(get(i));
        }
        return this;
    }


}

class Test {
    public static void main(String[] args) {
        System.out.println("start time");
        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate1 = now1.format(myFormatObj);
        System.out.println(formattedDate1);
        LinkedList<String> linkedList = new LinkedList<>();
        boolean isEmpty0 = linkedList.isEmpty();
        System.out.println("isEmpty0 " + isEmpty0);
        int size0 = linkedList.size();
        System.out.println("size0 " + size0);
        linkedList.add("1");
        boolean isEmpty1 = linkedList.isEmpty();
        System.out.println("isEmpty1 " + isEmpty1);
        int size1 = linkedList.size();
        System.out.println("size1 " + size1);
        System.out.println(linkedList.add("2"));
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add("9");
        linkedList.add("10");
        System.out.println("list");
        for (String s : linkedList
        ) {
            System.out.println(s);
        }
        boolean contains = linkedList.contains("1");
        System.out.println("contains " + contains);
        int index = linkedList.indexOf("5");
        System.out.println("index " + index);
        int lastIndex = linkedList.lastIndexOf("10");
        System.out.println("index " + lastIndex);
        String get = linkedList.get(0);
        System.out.println("get " + get);
        linkedList.set(0, "a");
        String get1 = linkedList.get(0);
        System.out.println("get1 " + get1);
        linkedList.set(0, "1");
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        System.out.println("integerList");
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.add(4);
        integerLinkedList.add(5);
        for (Integer integer : integerLinkedList) {
            System.out.println(integer);
        }
        integerLinkedList.clear();
        System.out.println("clear");
        for (Integer integer : integerLinkedList) {
            System.out.println(integer);
        }
        String middle0 = linkedList.middle();
        System.out.println("middle0 " + middle0);
        linkedList.add("11");
        String middle1 = linkedList.middle();
        System.out.println("middle1 " + middle1);
        Object[] objects = linkedList.toArray();
        for (Object o : objects
        ) {
            System.out.print(o + " ");
        }
        System.out.println();
        System.out.println("MA");
        System.out.println(Arrays.toString(linkedList.toArray()));
        boolean remove = linkedList.remove("11");
        System.out.println("remove " + remove);
        System.out.println("stringList");
        for (String string : linkedList) {
            System.out.println(string);
        }
        linkedList.add(0, "11");
        System.out.println("new list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        linkedList.addFirst("12");
        System.out.println("new list ---------");
        for (String string : linkedList) {
            System.out.println(string);
        }
        linkedList.addLast("13");
        System.out.println("new list ");
        for (String string : linkedList) {
            System.out.println(string);
        }
        boolean offerFirst = linkedList.offerFirst("14");
        System.out.println("offerFirst " + offerFirst);
        System.out.println("new list ------------ ");
        for (String string : linkedList) {
            System.out.println(string);
        }
        System.out.println("reverse list ");
        linkedList.reverse();
        for (String string : linkedList) {
            System.out.println(string);
        }
        System.out.println("original list ");
        linkedList.reverse();
        for (String string : linkedList) {
            System.out.println(string);
        }
        boolean isCyclic = linkedList.isCyclic();
        System.out.println(" isCyclic " + isCyclic);
        linkedList.pairWiseSwap();
        System.out.println("pairWiseSwap");
        for (String string : linkedList) {
            System.out.println(string);
        }
        boolean offerLast = linkedList.offerLast("15");
        System.out.println(" offerLast " + offerLast);
        System.out.println("new list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        String peekFirst = linkedList.peekFirst();
        System.out.println("peekFirst " + peekFirst);
        String peekLast = linkedList.peekLast();
        System.out.println("peekLast " + peekLast);
        String element = linkedList.element();
        System.out.println("element " + element);
        linkedList.remove();
        System.out.println("removed list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        linkedList.removeFirst();
        System.out.println("removed list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        linkedList.removeLast();
        System.out.println("removed list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        String getFirst = (String) linkedList.getFirst();
        String getLast = (String) linkedList.getLast();
        System.out.println("getFirst " + getFirst);
        System.out.println("getLast " + getLast);
        String pollFirst = (String) linkedList.pollFirst();
        System.out.println("pollFirst " + pollFirst);
        String pollLast = (String) linkedList.pollLast();
        System.out.println("pollLast " + pollLast);
        boolean offer = linkedList.offer("16");
        System.out.println("offer " + offer);
        System.out.println("list");
        for (String string : linkedList) {
            System.out.println(string);
        }
        String poll = (String) linkedList.poll();
        System.out.println("poll " + poll);
        System.out.println("poll");
        for (String string : linkedList) {
            System.out.println(string);
        }
        String peek = (String) linkedList.peek();
        System.out.println("peek " + peek);
        System.out.println("push");
        linkedList.push("17");
        for (String string : linkedList) {
            System.out.println(string);
        }
        String pop = (String) linkedList.pop();
        System.out.println("pop " + pop);
        System.out.println("end time");
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = now2.format(myFormatObj2);
        System.out.println(formattedDate2);
        System.out.println("hello from");
        for (String s : linkedList
        ) {
            System.out.println(s);
        }
        LinkedList<String> sublist = linkedList.sublist(1, 5);
        System.out.println("hello");
        for (String s : sublist) {
            System.out.println(s);
        }

    }
}