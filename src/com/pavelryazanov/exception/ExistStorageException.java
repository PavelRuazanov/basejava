package com.pavelryazanov.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist in storage", uuid);
    }
}
