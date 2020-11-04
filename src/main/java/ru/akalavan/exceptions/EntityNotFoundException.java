package ru.akalavan.exceptions;

import org.springframework.util.Assert;

//Исключение сущность не найдена
public class EntityNotFoundException extends BaseException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String type, Object id) {
        this(formatString(type, id));
    }

    private static String formatString(String type, Object id) {
        Assert.hasText(type, "тип не может быть пустым");
        Assert.notNull(id, "id объекта не может быть null");
        Assert.hasText(id.toString(), "id не может быть пустым");
        return String.format("%s с ключом %s не найден", type, id);
    }
}
