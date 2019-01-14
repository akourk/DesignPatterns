// In computer programming, the proxy pattern is a software design pattern.
// A proxy, in its most general form, is a class functioning as an
// interface to something else. The proxy could interface to anything:
// a network connection, a large object in memory, a file, or some other
// resource that is expensive or impossible to duplicate. In short, a
// proxy is a wrapper or agent object that is being called by the client to
// access the real serving object behind the scenes. Use of the proxy can
// simply be forwarding to the real object, or can provide additional logic.
// In the proxy, extra functionality can be provided, for example caching
// when operations on the real object are resource intensive, or checking
// preconditions before operations on the real object are invoked. For the
// client, usage of a proxy object is similar to using the real object,
// because both implements the same interface.

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        final Image image1 = new ProxyImage("HiRes_10MB_Photo1");
        final Image image2 = new ProxyImage("HiRes_10MB_Photo2");

        image1.displayImage();  // loading necessary
        image1.displayImage();  // loading unnecessary
        image2.displayImage();  // loading necessary
        image2.displayImage();  // loading unnecessary
        image2.displayImage();  // loading unnecessary

    }
}

interface Image {
    public void displayImage();
}

// On System A
class RealImage implements Image {

    private String filename;
    // Constructor
    // @param filename

    public RealImage(final String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    // Loads the image from the disk

    private void loadImageFromDisk() {
        System.out.println("Loading " + filename);
    }

    // Displays the image

    public void displayImage() {
        System.out.println("Displaying " + filename);
    }
}

// On System B
class ProxyImage implements Image {

    private RealImage image;
    private String filename;
    // Constructor
    // @param filename

    public ProxyImage(final String filename) {
        this.filename = filename;
    }

    // Diplays the image

    public void displayImage() {
        if (image == null) {
            image = new RealImage(filename);
        }
        image.displayImage();
    }
}




