package com.some_domain.www.cache;

import java.util.Optional;

public interface Cache {

    boolean set(int key, int value);

    Integer get(int key);

    int size();

    boolean isEmpty();

    void clear();
}
