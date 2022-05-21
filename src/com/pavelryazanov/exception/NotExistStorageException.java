package com.pavelryazanov.exception;

public class NotExistStorageException extends StorageException{

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist in storage", uuid);
    }
}
