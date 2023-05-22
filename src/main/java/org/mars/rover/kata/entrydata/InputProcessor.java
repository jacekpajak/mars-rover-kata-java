package org.mars.rover.kata.entrydata;

import java.util.Iterator;
import java.util.Scanner;

public interface InputProcessor {
    CommandSet processInput(Scanner inputProvider);
}
