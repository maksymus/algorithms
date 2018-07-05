package org.util;

import java.io.*;

public class BinaryIn implements Closeable {
    private BufferedInputStream in;
    private int buffer;
    private int n;

    public BinaryIn(InputStream inputStream) {
        in = new BufferedInputStream(inputStream);
        fillBuffer();
    }

    public boolean readBit() {
        if (n == 0)
            fillBuffer();

        if (n < 0)
            throw new IllegalStateException("end of stream");

        return ((buffer >>> --n) & 1) == 1;
    }

    public char readChar() {
        if (n == 0)
            fillBuffer();

        if (n < 0)
            throw new IllegalStateException("end of stream");

        if (n == 8) {
            int ch = buffer;
            fillBuffer();
            return (char) ch;
        }

        int ch = 0;
        for (int i = 7; i >= 0; i--) {
            ch |= (readBit() ? 1 : 0) << i;
        }

        return (char) ch;
    }

    public int readInt() {
        int x = 0;

        x |= readChar() << 24;
        x |= readChar() << 16;
        x |= readChar() << 8;
        x |= readChar() << 0;

        return x;
    }

    public boolean empty() {
        return n == -1;
    }

    @Override
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillBuffer() {
        try {
            buffer = in.read();
            n = buffer != -1 ? 8 : -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // x = 0111 1000
    public static void main(String[] args) throws FileNotFoundException {
        try (BinaryIn binaryIn = new BinaryIn(new FileInputStream(new File("/tmp/data.txt")))) {
//       try (BinaryOut binaryIn = new BinaryOut(System.out)) {
            System.out.println(binaryIn.readInt());
        }

    }
}
