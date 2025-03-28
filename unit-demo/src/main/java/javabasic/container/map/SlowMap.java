package javabasic.container.map;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lee
 * @since 2020-09-24
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();

        while (ki.hasNext()) {
            set.add(new MapEntry<>(ki.next(), vi.next()));
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            oldValue = get(key);
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        if (!keys.contains(key)) {
            return null;
        }
        return values.get(keys.indexOf(key));
    }

    public static void main(String[] args) {
        SlowMap<String, String> m = new SlowMap<>();
    }
}

class MapEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode() ^ (value == null ? 0 : value.hashCode()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MapEntry)) return false;
        MapEntry me = (MapEntry) obj;
        return (key == null ? me.key == null : key.equals(me.getKey()))
                && (value == null ? me.value == null : value.equals(me.getValue()));
    }
}
