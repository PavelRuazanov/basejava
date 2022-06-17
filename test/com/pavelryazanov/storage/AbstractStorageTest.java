package com.pavelryazanov.storage;

import com.pavelryazanov.exception.ExistStorageException;
import com.pavelryazanov.exception.NotExistStorageException;
import com.pavelryazanov.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";

    private static final String FULL_NAME_1 = "Pavel Ryazanov";
    private static final String FULL_NAME_2 = "Tatiana Bardina";
    private static final String FULL_NAME_3 = "Anna Karenina";
    private static final String FULL_NAME_4 = "Boris Ahmatov";
    protected final Storage storage;
    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
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
        storage.save(R2);
    }


    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(R1, storage.get("uuid_1"));
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
        Assert.assertEquals(R1, storage.get("uuid_1"));
    }

    @Test
    public void save() {
        storage.save(R4);
        Assert.assertEquals(R4, storage.get("uuid_4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(resumes.size(), storage.size());
        Assert.assertEquals(resumes, Arrays.asList(R1, R2,R3));

    }

}