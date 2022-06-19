package com.pavelryazanov.storage;

import com.pavelryazanov.exception.StorageException;
import com.pavelryazanov.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream oS) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(oS)) {
            oos.writeObject(r);
        }

    }

    @Override
    protected Resume doRead(InputStream iS) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(iS)){
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
throw new StorageException("Error read resume", null, e);
        }
    }
}
