package com.sxkl.project.cloudnote.flag.service;


import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.flag.entity.Flag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlagService {

    OperationResult save(Flag flag);

    OperationResult deleteById(String id);

    OperationResult forceDeleteById(String id);

    OperationResult update(Flag flag);

    Flag findById(String id);

    List<Flag> findAll(String userId);

    Page<Flag> findPage(Flag flag, int page, int size);

    Flag findWholeTree(String userId);

    Flag findSubTree(String userId,String parentId);

}
