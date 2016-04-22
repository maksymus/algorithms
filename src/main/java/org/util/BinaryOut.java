package org.util;

import java.io.*;

public class BinaryOut implements Closeable {
    /* writing to this stream */
    private BufferedOutputStream out;

    /* byte-length buffer */
    private int buffer;

    /* number of bits in buffer */
    private int n;

    public BinaryOut(OutputStream stream) {
        out = new BufferedOutputStream(stream);
    }

    public void write(boolean bit) {
        buffer <<= 1;
        buffer |= bit ? 1 : 0;
        n++;

        if (n == 8)
            clear();
    }

    public void write(byte x) {
        if (n == 0) {
            try {
                out.write(x);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            write(((x >>> (8 - i - 1)) & 1) == 1);
        }
    }

    public void write(int x) {
        write((byte) ((x >>> 24) & 0xff));
        write((byte) ((x >>> 16) & 0xff));
        write((byte) ((x >>> 8) & 0xff));
        write((byte) ((x >>> 0) & 0xff));
    }

    public void write(char c) {
        write((byte) c);
    }

    public void write(String str) {
        str.chars().forEach(c -> write((byte) c));
    }

    private void clear() {
        if (n == 0)
            return;

        if (n > 0)
            buffer <<= (8 - n);

        try {
            out.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        n = 0;
        buffer = 0;
    }

    public void flush() {
        clear();
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        flush();
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BinaryOut binaryOut = new BinaryOut(new FileOutputStream(new File("/tmp/data.txt")))) {
//       try (BinaryOut binaryOut = new BinaryOut(System.out)) {
            binaryOut.write(1432);
        }
    }
}
