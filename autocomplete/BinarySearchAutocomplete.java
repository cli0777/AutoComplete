package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        elements = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        //add all elements
        elements.addAll(terms);
        //sort it in ascending order? alphabetical
        Collections.sort(elements, null);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) { //keysWithPrefix
        //matching terms appear next to each other in orted arraylist - 
        //thus need to find first index that matches 
        List<CharSequence> result = new ArrayList<>();

        //if prefix is null or length 0
        if (prefix == null || prefix.length() == 0) {
            return result;
        } 

        //if find matching prefix, add it to result and then iterate until end
        int ind = Collections.binarySearch(elements, prefix, null);
        if (ind < 0) {
            ind = -(ind + 1);
        }

        for (int i = ind; i < elements.size(); i++) {
            if (Autocomplete.isPrefixOf(prefix, elements.get(i))) {
                result.add(elements.get(i));
            } else {
                return result;
            }
        }
        return result;
    }
}
