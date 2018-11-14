package com.sxkl.project.cloudnote.base.service;

import com.sxkl.project.cloudnote.common.OperationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public abstract class BaseServiceImpl<Entity,ID> implements BaseService<Entity,ID>  {

    @Override
    public OperationResult save(Entity entity) {
        try {
            getBaseDao().save(entity);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildDefaultFail();
        }
    }

    @Override
    public OperationResult deleteById(ID id) {
        try {
            getBaseDao().deleteById(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildDefaultFail();
        }
    }

    @Override
    public OperationResult update(Entity entity) {
        return null;
    }

    @Override
    public Entity findById(ID id) {
        return getBaseDao().findById(id).orElse(null);
    }

    @Override
    public List<Entity> findAll() {
        return getBaseDao().findAll();
    }

    @Override
    public Page<Entity> findPage(int page, int size) {
        return getBaseDao().findAll(PageRequest.of(page,size));
    }
}
