package ru.akalavan.exceptions;

import org.springframework.util.Assert;

// Исключение при попытки удаления сущности, на которые есть ссылки у других сущностей
public class EntityHasDetailsException extends BaseException {
    public EntityHasDetailsException(String message) {
        super(message);
    }

    public EntityHasDetailsException(String type, Object id) {
        this(formatString(type, id));
    }

    private static String formatString(String type, Object id) {
        Assert.hasText(type, "тип не может быть пустым");
        Assert.notNull(id, "id объекта не может быть null");
        Assert.hasText(id.toString(), "id не может быть пустым");
        return String.format("%s ссылается на удаляемый объект с id %s", type, id);
    }
}
