# AutoComplete
This project implements an Autocomplete feature: a data structure that suggests valid search results as the user types a prefix. It is commonly used in applications such as map search, text editors, and search engines.

In this implementation, multiple approaches are explored to understand how different data structures impact performance and efficiency. The project also demonstrates how autocomplete concepts can extend beyond text, such as enabling fast DNA subsequence searches.

## Overview

Autocomplete is designed to return all terms that match a given prefix. For example, given the prefix "Sea", an autocomplete system might return "Seattle", "Seaside", and "Searchlight".

## Project Purpose

The project explores three data structures for implementing autocomplete:

Sequential search using unsorted arrays

Binary search using sorted arrays

Ternary search trees

By comparing these implementations, the project illustrates trade-offs in efficiency, complexity, and memory usage.
Sequential search is simple but slow. Binary search benefits from sorting and indexing. Ternary search trees, though more complex, provide fast lookups with flexible prefix handling.

The goal is to understand how these data structures impact runtime complexity and practical performance, especially when scaling to large datasets.

## Autocomplete Interface

All implementations share a common interface, Autocomplete, which defines methods such as:

addAll(Collection<? extends CharSequence> terms)
Adds all terms to the data structure.

allMatches(CharSequence prefix)
Returns a list of all terms that start with the given prefix.

Example Usage

Given the terms:

[alpha, delta, do, cats, dodgy, pilot, dog]


Calling allMatches("do") returns:

[do, dodgy, dog]


These results may appear in any order depending on the underlying data structure.

## Implementations
### TreeSetAutocomplete

The reference implementation, TreeSetAutocomplete, uses a TreeSet (a balanced red-black tree) to store terms in sorted order.
It takes advantage of Java’s NavigableSet interface, which allows efficient range queries using methods like ceiling and tailSet.

Complexity: O(log n) for insertion and lookup

Strengths: Sorted storage and efficient prefix scanning

Limitations: Tree-based operations require extra memory overhead

### SequentialSearchAutocomplete

SequentialSearchAutocomplete stores terms in an ArrayList with no guaranteed order.
When performing a search, it scans the entire list, checking each term to see if it matches the prefix.

Complexity: O(n) for search and insertion

Strengths: Simple to implement and update dynamically

Limitations: Inefficient for large datasets

This approach demonstrates a baseline for performance comparison with more optimized structures.

### BinarySearchAutocomplete

BinarySearchAutocomplete stores terms in a sorted ArrayList.
After each insertion, the list is re-sorted to maintain order.
When searching, the method uses Collections.binarySearch to find the first matching term, then iterates forward to collect all additional matches.

Complexity:

Insertion: O(n log n) (due to sorting)

Search: O(log n + k) (where k is the number of matches)

Strengths: Fast prefix matching in ordered datasets

Limitations: Re-sorting on every insertion can be costly

### TernarySearchTreeAutocomplete

TernarySearchTreeAutocomplete implements a ternary search tree (TST), where each node represents a character and links to up to three children. This structure allows efficient prefix-based searches without storing entire words at each node.

Complexity: O(k + log n) for search (where k is prefix length)

Strengths: Space-efficient for string-heavy datasets

Limitations: Recursion depth may cause stack overflow for very long inputs
