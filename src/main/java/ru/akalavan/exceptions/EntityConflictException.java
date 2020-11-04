package ru.akalavan.exceptions;

// Исключение при конфликте с сущ. данными
public class EntityConflictException extends BaseException {
    public EntityConflictException(String message) {
        super(message);
    }
}
