package com.pavelryazanov.storage;

import com.pavelryazanov.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void fillDeleteElement(int index) {
        int numMove = size - index - 1;
        if (numMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMove);
        }
    }

    @Override
    protected void insertElementArrays(Resume r, int index) {
//        http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
