package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence each : terms) {
            //call .put(); to add each term to the root
            overallRoot = put(overallRoot, each.toString(), 0);
        }
    }

    //starter 
    public void put(String term) {
        overallRoot = put(overallRoot, term, 0);

    }

    //put - based on prev node, chooses the direction of the node, +
    //mark the end of the word 
    //recursive method
    private Node put(Node root, String term, int index) {
        if (index >= term.length()) {
            return root;
        }
        //get char at index
        char curr = term.charAt(index);

        //if root is null, create one with first char
        if (root == null) {
            root = new Node(curr);
        }
        //if not null
        char nodeC = root.data;

        //compare: if curr < nodeC; >;recurse for both
        if (curr < nodeC) {
            root.left = put(root.left, term, index);
        } else if (curr > nodeC) {
            root.right = put(root.right, term, index);
        } else {
            if (index == term.length() - 1) {
                // end of word
                root.isTerm = true;
            } else {
                // continue in the middle
                root.mid = put(root.mid, term, index + 1);
            }
        }
        return root;
    }

    //create list with all matches for the prefix
    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        //
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        //get the node where the prefix ends
        Node curr = get(overallRoot, prefix.toString(), 0);

        if (curr == null) {
            return result;
        }

        if (curr.isTerm) {
            result.add(prefix.toString());
        }

        collect(curr.mid, prefix.toString(), result);
    
        return result;
    }

    //collect
    private void collect (Node root, String prefix, List<CharSequence> results) {
        if (root != null) {
            //recurisve left subtree
            collect(root.left, prefix, results);
            //if node isterm
            if (root.isTerm) {
                results.add(prefix + root.data);
            }

            //middle
            collect(root.mid, prefix + root.data, results);

            //right
            collect(root.right, prefix, results);
        }
    }

    //get method: finds prefix node, for which later will explore
    //possible continuations with 'collect'
    private Node get(Node root, String term, int index) {
        if (root == null) {
            return null;
        }
        //get current char
        char curr = term.charAt(index);

        if (curr < root.data) {
            return get(root.left, term, index);
        } else if (curr > root.data) {
            return get(root.right, term, index);
        } else {
            //find char match
            if (index == term.length() - 1) {
                return root;
            } else {
                return get(root.mid, term, index + 1);
            }
        }
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
