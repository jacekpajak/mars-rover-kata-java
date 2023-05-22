package org.mars.rover.kata.entrydata;

import java.util.Iterator;
import java.util.Scanner;

public interface InputProcessorInterface {
    CommandSet processInput(Scanner inputProvider);
}
