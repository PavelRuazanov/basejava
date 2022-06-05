package com.pavelryazanov.storage;

import com.pavelryazanov.exception.ExistStorageException;
import com.pavelryazanov.exception.NotExistStorageException;
import com.pavelryazanov.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AbstractStorageTest {
    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";

    private static final String FULL_NAME_1 = "Pavel Ryazanov";
    private static final String FULL_NAME_2 = "Tatiana Bardina";
    private static final String FULL_NAME_3 = "Anna Karenina";
    private static final String FULL_NAME_4 = "Boris Ahmatov";
    protected final Storage storage;
    Resume r1;
    Resume r2;
    Resume r3;
    Resume r4;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        r1 = new Resume(UUID_1, FULL_NAME_1);
        r2 = new Resume(UUID_2, FULL_NAME_2);
        r3 = new Resume(UUID_3, FULL_NAME_3);
        r4 = new Resume(UUID_4, FULL_NAME_4);
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
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid_4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(resumes.size(), storage.size());
        Assert.assertEquals(resumes, Arrays.asList(r3, r1, r2));

    }

}