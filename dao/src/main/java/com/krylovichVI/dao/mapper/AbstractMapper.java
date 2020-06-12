package com.krylovichVI.dao.mapper;

public interface AbstractMapper<E, D> {
    D toDto(E e);
    E toEntity(D d);
}
