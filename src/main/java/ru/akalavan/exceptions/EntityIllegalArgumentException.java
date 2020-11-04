package ru.akalavan.exceptions;

//Исключение при вызыве с некорректными параметрами
public class EntityIllegalArgumentException extends BaseException {
    public EntityIllegalArgumentException(String message) {
        super(message);
    }
}
