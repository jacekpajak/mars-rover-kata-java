package org.mars.rover.kata.entrydata;

import java.util.Iterator;

public interface InputProcessor {
    CommandSet processInput(Iterator<String> stringIterator);
}
