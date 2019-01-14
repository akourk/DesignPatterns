// In object-oriented programming, the decorator pattern is a design
// that allows behavior to be added to an individual object, dynamically,
// without affecting the behavior of other objects from the same class.
// The decorator pattern is often useful for adhering to the Single
// Responsibility Principle, as it allows functionality to be divided
// between classes with unique areas of concern. The decorator pattern is
// structurally nearly identical to the chain of responsibility pattern,
// the difference being that in a chain of responsibility, exactly one of
// the classes handles the request, while for the decorator, all classes
// handle the request.

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        // create a decorated window with horizontal and vertical scrollbars
        Window decoratedWindow = new HorizontalScrollBarDecorator(new VerticalScrollBarDecorator(new SimpleWindow()));

        // print the window's description
        System.out.println(decoratedWindow.getDescription());
    }
}

// the following example illustrates the use of decorators using the
// window/scrolling scenario.

// the Window interface class
interface Window {
    void draw();    // draws the window
    String getDescription();    // returns a description of the window
}

// implementation of a simple window without any scrollbars
class SimpleWindow implements Window {
    @Override
    public void draw() {

    }

    @Override
    public String getDescription() {
        return "simple window";
    }
}


// the following classes contain the decorators for all Window
// classes, including the decorator classes themselves.

// abstract decorator class - note that it implements Window
abstract class WindowDecorator implements Window {
    protected Window windowToBeDecorated; // the wndow being decorated

    public WindowDecorator (Window windowToBeDecorated) {
        this.windowToBeDecorated = windowToBeDecorated;
    }
    @Override
    public void draw() {
        windowToBeDecorated.draw(); // Delegation
    }
    @Override
    public String getDescription() {
        return windowToBeDecorated.getDescription(); // Delegation
    }
}


// the first concrete decorator which adds vertical scrollbar functionality
class VerticalScrollBarDecorator extends WindowDecorator {
    public VerticalScrollBarDecorator (Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public void draw() {
        super.draw();
        drawVerticalScrollBar();
    }

    private void drawVerticalScrollBar() {
        // draw the vertical scrollbar
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including vertical scrollbars";
    }
}

// the second concrete decorator which adds horizontal scrollbar functionality
class HorizontalScrollBarDecorator extends WindowDecorator {
    public HorizontalScrollBarDecorator (Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public void draw() {
        super.draw();
        drawHorizontalScrollBar();
    }

    private void drawHorizontalScrollBar() {
        // draw the horizontal scrollbar
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including horizontal scrollbars";
    }
}

