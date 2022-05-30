package com.pavelryazanov.storage;

import com.pavelryazanov.exception.StorageException;
import com.pavelryazanov.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverflowTest() {
        try {
            for (int i = 4; i <= ArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i, "fullName" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("uuidEnd", "fullNameEnd"));

    }
}