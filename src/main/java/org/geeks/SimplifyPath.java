package org.geeks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * path = "/../", => "/"
 * path = "/home//foo/", => "/home/foo"
 */
public class SimplifyPath {


    public static void main(String[] args) {
        System.out.println(SimplifyPath.simplify("/../"));
    }

    public static String simplify(String path) {
        Deque<String> stack = new LinkedList<>();

        String[] split = path.split("/");

        for (String subpath : split) {
            if (subpath.trim().isEmpty())
                continue;

            if (subpath.equals(".")) {
                // skip
            } else if (subpath.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else  {
                stack.push(subpath);
            }
        }

        Deque<String> destack = new LinkedList<>();
        while (!stack.isEmpty())
            destack.push(stack.pop());

        String simplified = destack.stream().collect(Collectors.joining("/"));
        return "/" + simplified;
    }
}
