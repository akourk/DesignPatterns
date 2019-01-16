// In object-oriented programming, the iterator pattern is a
// design pattern in which an iterator is used to traverse
// a container and access the container's elements. The
// iterator pattern decouples algorithms from containers;
// in some cases, algorithms are necessarily container-
// specific and thus cannot be decoupled.

// For example, the hypothetical algorithm SearchForElement
// can be implemented generally using a specified type of
// iterator rather than implementing it as a container-
// specific algorithm. This allows SearchForElement to be
// used on any container that supports the required type
// of iterator.


// Java has the Iterator interface.

// As of Java 5, objects implementing the Iterable interface,
// which returns an Iterator from its only method, can be
// traversed using Java's foreach loop syntax. The Collection
// interface from the Java collections framework extends
// Iterable.

// Example of class RedHead implementing the Iterable interface:

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

class RedHead implements Iterable<RedHead> {
    private Set<RedHead> redHeads = new HashSet<RedHead>();

    public void add(final RedHead redHead) {
        redHeads.add(redHead);
    }
    @Override
    public Iterator<RedHead> iterator() {
        return redHeads.iterator();
    }
}

// Only one class is necessary to properly utilize Iterable
// in Java. Subclass Weasley is used only to aid the example

class Weasley extends RedHead {
    private String name;

    public Weasley(final String name) {
        this.name = name;
    }
    public String toString() {
        return this.name + " Weasley";
    }
}


// Main to demonstrate the use of class RedHead:

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        RedHead redHead = new RedHead();

        redHead.add(new Weasley("Arthur"));
        redHead.add(new Weasley("Molly"));
        redHead.add(new Weasley("Bill"));
        redHead.add(new Weasley("Charlie"));
        redHead.add(new Weasley("Percy"));
        redHead.add(new Weasley("Fred"));
        redHead.add(new Weasley("George"));
        redHead.add(new Weasley("Ron"));
        redHead.add(new Weasley("Ginny"));

        for (RedHead rh : redHead) {
            System.out.println(rh);
        }
    }
}
