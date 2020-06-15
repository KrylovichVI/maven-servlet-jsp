package com.krylovichVI.dao.converters;

public interface AbstractConverter<E, D> {
    D toDto(E e);
    E toEntity(D d);
}
