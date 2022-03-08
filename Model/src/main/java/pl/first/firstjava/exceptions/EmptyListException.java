package pl.first.firstjava.exceptions;

import java.util.NoSuchElementException;

public class EmptyListException extends NoSuchElementException {

    public EmptyListException(String s) {
        super(s);
    }
}
