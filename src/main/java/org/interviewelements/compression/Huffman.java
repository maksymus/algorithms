package org.interviewelements.compression;

import org.util.BinaryIn;
import org.util.BinaryOut;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Huffman compression algorithm. <a href="https://docs.google.com/viewer?url=http://algs4.cs.princeton.edu/lectures/55DataCompression.pdf">55DataCompression.pdf</a>
 * <br/>
 * Dynamic model: use a custom prefix-free code for each message.<br/>
 * Compression:
 * <ul>
 * <li>Read message.
 * <li>Built best prefix-free code for message.
 * <li>Write prefix-free code (as a trie) to file.
 * <li>Compress message using prefix-free code.
 * </ul>
 * Expansion:
 * <ul>
 * <li>Read prefix-free code (as a trie) from file.
 * <li>Read compressed message and expand using trie.
 * </ul>
 */
public class Huffman {

    private static final String text = "In computer science and information theory, "
            + "Huffman coding is an entropy encoding algorithm used for lossless data compression. "
            + "The term refers to the use of a variable-length code table for encoding a source symbol "
            + "(such as a character in a file) where the variable-length code table has been derived in a "
            + "particular way based on the estimated probability of occurrence for each possible value of "
            + "the source symbol. It was developed by David A. Huffman while he was a Ph.D. student at MIT, "
            + "and published in the 1952 paper \"A Method for the Construction of Minimum-Redundancy Codes\".";

//    private static final String text = "hello";

    /** Prefix tree node */
    private static abstract class Node {
        protected int weight;
        public Node(int weight) {
            this.weight = weight;
        }
    }

    /** External leaf */
    private static class LeafNode extends Node {
        private char ch;

        public LeafNode(int weight, char ch) {
            super(weight);
            this.ch = ch;
        }

        @Override
        public String toString() {
            return String.valueOf(ch) + ":" + weight;
        }
    }

    /** Internal node */
    private static class InternalNode extends Node {
        private Node left;
        private Node right;

        public InternalNode(int weight, Node left, Node right) {
            super(weight);
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Compress string.
     * @param str string to compress
     * @param outputStream output to store compressed data.
     */
    public void compress(String str, OutputStream outputStream) {
        Map<Character, String> codes = new HashMap<>();

        Node root = buildTree(str);
        buildCode(root, codes, "");

        try (BinaryOut binaryOut = new BinaryOut(outputStream)) {
            writeTree(binaryOut, root, codes);
            binaryOut.write(str.length());
            for (char ch : str.toCharArray()) {
                String s = codes.get(ch);
                s.chars().forEach(c -> binaryOut.write(c == '1' ? true : false));
            }
        }
    }

    /**
     * Decompress huffman archive.
     * @param inputStream input with compressed data.
     * @return decompressed string.
     */
    public String decompress(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();

        try (BinaryIn binaryIn = new BinaryIn(inputStream)) {
            Node root = readTree(binaryIn);
            int length = binaryIn.readInt();

            for (int i = 0 ; i < length; i++) {
                Node x = root;
                while (!(x instanceof LeafNode)) {
                    boolean bit = binaryIn.readBit();
                    x = bit ? ((InternalNode) x).right : ((InternalNode) x).left;
                }

                builder.append(((LeafNode) x).ch);
            }
        }

        return builder.toString();
    }

    private void writeTree(BinaryOut binaryOut, Node node, Map<Character, String> codes) {
        if (node == null)
            return;

        if (node instanceof InternalNode) {
            InternalNode internal = (InternalNode) node;
            binaryOut.write(false);
            writeTree(binaryOut, internal.left, codes);
            writeTree(binaryOut, internal.right, codes);
        } else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode) node;
            binaryOut.write(true);
            binaryOut.write(leaf.ch);
        }
    }

    private Node readTree(BinaryIn binaryIn) {
        boolean isLeaf = binaryIn.readBit();

        if (isLeaf) {
            char ch = binaryIn.readChar();
            return new LeafNode(0, ch);
        } else {
            Node left = readTree(binaryIn);
            Node right = readTree(binaryIn);
            return new InternalNode(0, left, right);
        }
    }

    private void buildCode(Node node, Map<Character, String> codes, String s) {
        if (node == null)
            return;

        if (node instanceof InternalNode) {
            InternalNode internal = (InternalNode) node;
            buildCode(internal.left, codes, s + '0');
            buildCode(internal.right, codes, s + '1');
        } else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode) node;
            codes.put(leaf.ch, s);
        }
    }

    /**
     * Returns root.
     */
    private Node buildTree(String str) {
        Map<Character, Integer> counts = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(100,
                (Node o1, Node o2) -> o1.weight - o2.weight);

        str.chars().forEach(c ->
            counts.put((char) c, counts.getOrDefault((char) c, 0) + 1));

        priorityQueue.addAll(counts.keySet().stream()
                .map(c -> new LeafNode(counts.get(c), c))
                .collect(Collectors.toList()));

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node internal = new InternalNode(left.weight + right.weight, left, right);
            priorityQueue.add(internal);
        }

        return priorityQueue.poll();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Huffman main = new Huffman();

        String compressedFile = "/tmp/compress.huf";

        main.compress(text, new FileOutputStream(compressedFile));
        String decompressed = main.decompress(new FileInputStream(compressedFile));

        System.out.println(decompressed);
    }

}
