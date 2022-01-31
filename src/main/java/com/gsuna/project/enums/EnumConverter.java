package com.gsuna.project.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public abstract class EnumConverter<T extends Enum<T> & BaseEnum<E>, E> implements AttributeConverter<T, E> {
    private final Class<T> clazz ;

    public EnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
    	if(dbData==null) return null;
        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.getValue().equals(dbData)) {
                return e;
            }
        }

        throw new UnsupportedOperationException();
    }
}
