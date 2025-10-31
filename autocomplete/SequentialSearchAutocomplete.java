package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sequential search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class SequentialSearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public SequentialSearchAutocomplete() {
        elements = new ArrayList<>();
    }

    //add items in any order with the given collection 'terms'
    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence each : terms) {
            elements.add(each);
        }
    }

    //iterate through elements and check if prefix matches
    //return list created of matching
    //call isPrefixOf
    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        //if prefix is null or length 0
        if (prefix == null || prefix.length() == 0) {
            return result;
        } 

        //iterate thru terms
        for (CharSequence each : elements) {
            if (Autocomplete.isPrefixOf(prefix, each)) {
                result.add(each);
            } 
        }
        return result;
    }
}
