package com.pavelryazanov.storage;

import com.pavelryazanov.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElementArrays(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeleteElement(int index) {
        storage[index] = storage[size - 1];
    }
}