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
        int tracker = 0;
        int counter;
        Integer reader = BinaryStdIn.readInt(8);
        while (!BinaryStdIn.isEmpty()) {
            if (BinaryStdIn.readInt(1) == BinaryStdIn.readInt(2)) {
                tracker++;
            }
            if (tracker == 255) {
                BinaryStdOut.write(tracker, 8);
                BinaryStdOut.write(0, 8);
                tracker = 0;
            if (BinaryStdIn.readInt(1) != BinaryStdIn.readInt(2)) {
                    BinaryStdOut.write(tracker, 8);
                }
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits from standard
        } input, decodes it,
     * and writes the results to standard output.
     */
    public static void expand() {
        // TODO: complete expand()
        while (!BinaryStdIn.isEmpty()) {
            Integer run = BinaryStdIn.readInt(8);
            if (BinaryStdIn.readInt() == BinaryStdIn.readInt(2)) {
                BinaryStdOut.write(BinaryStdIn.readInt(64), 8);
            }


        }
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