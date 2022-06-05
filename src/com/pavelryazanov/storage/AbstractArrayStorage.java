package com.pavelryazanov.storage;


import com.pavelryazanov.exception.StorageException;
import com.pavelryazanov.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume doGet(Integer index) {
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void doDelete(Integer index) {
        fillDeleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    public void doSave(Resume r, Integer index) {
        if (size + 1 > storage.length) throw new StorageException("Массив заполнен", r.getUuid());
        insertElementArrays(r, index);
        size++;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    public List<Resume> doCopyAll() {
        Resume[] result = Arrays.copyOfRange(storage, 0, size);
        return Arrays.asList(result);
    }

    protected abstract void fillDeleteElement(int index);

    protected abstract void insertElementArrays(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);
}
