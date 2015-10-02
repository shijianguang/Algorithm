/**
 * Problem link: https://leetcode.com/problems/peeking-iterator
 *
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 *
 * Hint:
 *
 * Think of "looking ahead". You want to cache the next element.Show More Hint 
 *
 */
public class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer top;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if(iterator.hasNext()) {
            top = iterator.next();
        } else {
            top = null;
        }
    }

    public Integer peek() {
        return top;
    }

    @Override
    public Integer next() {
        Integer result = top;
        if(iterator.hasNext()) {
            top = iterator.next();
        } else {
            top = null;
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        if(top != null) {
            return true;
        } else if(iterator.hasNext()) {
            return true;
        } else {
            return false;
        }
    }
}
