/******************************************************************************
 *  Compilation:  javac BitmapCompressor.java
 *  Execution:    java BitmapCompressor - < input.bin   (compress)
 *  Execution:    java BitmapCompressor + < input.bin   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   q32x48.bin
 *                q64x96.bin
 *                mystery.bin
 *
 *  Compress or expand binary input from standard input.
 *
 *  % java DumpBinary 0 < mystery.bin
 *  8000 bits
 *
 *  % java BitmapCompressor - < mystery.bin | java DumpBinary 0
 *  1240 bits
 ******************************************************************************/

/**
 *  The {@code BitmapCompressor} class provides static methods for compressing
 *  and expanding a binary bitmap input.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 *  @author David Lutch
 */
public class BitmapCompressor {

    /**
     * Reads a sequence of bits from standard input, compresses them,
     * and wr ites the results to standard output.
     */
    public static void compress() {
        // TODO: complete compress()
        int tracker = 1;
        int oldBit = BinaryStdIn.readInt(1);

        while (!BinaryStdIn.isEmpty()) {
            int currentBit = BinaryStdIn.readInt(1);
            if (currentBit == oldBit) {
                tracker++;
                if (tracker == 256) {
                    BinaryStdOut.write(tracker - 1, 8);
                    BinaryStdOut.write(0, 8);
                    tracker = 1;
                }
            }
            if (currentBit != oldBit) {
                BinaryStdOut.write(tracker, 8);
                oldBit = currentBit;
                tracker = 1;
            }
        }
        BinaryStdOut.write(tracker, 8);
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits from standard
        } input, decodes it,
     * and writes the results to standard output.
     */// TODO: complete expand()
    public static void expand() {
        boolean Zero = true;

        while (!BinaryStdIn.isEmpty()) {
            int run = BinaryStdIn.readInt(8);
            for (int i = 1; i <= run; i++) {
                if (Zero) {
                    BinaryStdOut.write(0, 1);
                }
                else {
                    BinaryStdOut.write(1, 1);
                    }
                }
            Zero = !Zero;
            }
        // Flips for the next run
        BinaryStdOut.close();
    }

    /**
     * When executed at the command-line, run {@code compress()} if the command-line
     * argument is "-" and {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}