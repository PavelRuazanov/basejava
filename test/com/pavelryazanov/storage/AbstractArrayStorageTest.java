package com.pavelryazanov.storage;

import com.pavelryazanov.exception.ExistStorageException;
import com.pavelryazanov.exception.NotExistStorageException;
import com.pavelryazanov.exception.StorageException;
import com.pavelryazanov.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private final Storage storage;
    Resume r1;
    Resume r2;
    Resume r3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        r1 = new Resume(UUID_1);
        r2 = new Resume(UUID_2);
        r3 = new Resume(UUID_3);
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("samUUid");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid_9");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get("uuid_9"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        storage.save(r2);
    }

    @Test(expected = StorageException.class)
    public void storageOverflowTest() {
        try {
            for (int i = 4; i <= ArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("uuidEnd"));

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get("uuid_1"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid_2");
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test
    public void update() {
        Resume resume = storage.get("uuid_1");
        storage.update(resume);
        Assert.assertEquals(r1, storage.get("uuid_1"));
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid_4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid_4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes.length, storage.size());
        Assert.assertEquals(r1, resumes[0]);
        Assert.assertEquals(r2, resumes[1]);
        Assert.assertEquals(r3, resumes[2]);
    }
}