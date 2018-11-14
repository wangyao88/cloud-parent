package com.sxkl.project.cloudnote.base.service;

import com.sxkl.project.cloudnote.base.dao.BaseDao;
import com.sxkl.project.cloudnote.common.OperationResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<Entity,ID> {

    OperationResult save(Entity entity);

    OperationResult deleteById(ID id);

    OperationResult update(Entity entity);

    Entity findById(ID id);

    List<Entity> findAll();

    Page<Entity> findPage(int page, int size);

    BaseDao<Entity,ID> getBaseDao();
}
