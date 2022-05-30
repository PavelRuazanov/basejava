package com.pavelryazanov.storage;

import com.pavelryazanov.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage{

    private final Map<String, Resume> map = new HashMap<>();


    @Override
    protected Object getSearchKey(String uuid) {

        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
map.replace((String) searchKey,r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
map.put((String) searchKey,r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    public void clear() {
        map.clear();

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }
}
