package com.github.demonh3x.tictactoe.game;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LiteralSet {
    public static <T> Set<T> of(T... elems){
        final HashSet<T> set = new HashSet<>();
        Collections.addAll(set, elems);
        return set;
    }
}
