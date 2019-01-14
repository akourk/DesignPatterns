// the facade pattern is a software-design pattern commonly used
// with object-oriented programming. Analogous to a facade in
// architecture, a facade is an object that serves as a front-
// facing interface masking more complex underlying or structural
// code. a facade can:
//      -improve the readability and usability of a software library
// by masking interaction with more complex components behind a single
// (and often simplified) API
//      -provide a context-specific interface to more generic functionality
// (complete with context-specific input validation)
//      -serve as a launching point for a broader refactor of a monolithic
// or tightly-coupled systems in favor of more loosely-coupled code


// complex parts:

class CPU {
    public void freeze() {}
    public void jump(long position) {}
    public void execute() {}
}

class HardDrive {
    public byte[] read(long lba, int size) {}
}

class Memory {
    public void load(long position, byte[] data) {}
}

// Facade:

class ComputerFacade {
    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
        processor.jump(BOOT_ADDRESS);
        processor.execute();
    }
}

// Client:

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}