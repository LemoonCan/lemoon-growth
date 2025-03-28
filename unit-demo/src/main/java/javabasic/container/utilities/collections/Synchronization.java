package javabasic.container.utilities.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author lee
 * @since 2020-11-03
 */
public class Synchronization {
    public static void main(String[] args) {
        Collection<String> c = Collections.synchronizedCollection(new ArrayList<>());
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Set<String> s = Collections.synchronizedSet(new HashSet<>());
        Set<String> ss = Collections.synchronizedSortedSet(new TreeSet<>());
        Map<String,String> m = Collections.synchronizedMap(new HashMap<>(4));
        Map<String,String> sm = Collections.synchronizedSortedMap(new TreeMap<>());
    }
}
