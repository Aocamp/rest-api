package com.api.dao;

import com.api.model.BaseModel;

import java.util.List;

public interface BaseDao<T extends BaseModel> {
    List<T> getAll();

    T getById(Long id);

    void delete(Long id);
}
