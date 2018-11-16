package com.sxkl.project.cloudnote.flag.service;

import com.sxkl.project.cloudnote.article.service.ArticeService;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.flag.dao.FlagDao;
import com.sxkl.project.cloudnote.flag.entity.Flag;
import com.sxkl.project.cloudnote.job.JobManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlagServiceImpl implements FlagService {

    @Autowired
    private FlagDao flagDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ArticeService articeService;

    @Override
    public OperationResult save(Flag flag) {
        try {
            boolean exists = flagDao.exists(Example.of(new Flag(flag.getName())));
            if(exists) {
                return OperationResult.buildFail("标签已存在");
            }
            flagDao.save(flag);
            return OperationResult.buildSuccess("标签保存成功");
        } catch (Exception e) {
            return OperationResult.buildFail("标签保存失败");
        }
    }

    @Override
    public OperationResult deleteById(String id) {
        try {
            long count = articeService.countByFlagId(id);
            if(count > 0) {
                return OperationResult.buildFail("标签尚关联"+count+"篇文章");
            }
            flagDao.deleteById(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("标签删除失败");
        }
    }

    @Override
    public OperationResult forceDeleteById(String id) {
        try {
            flagDao.deleteById(id);
            JobManager.startUpdateArticleByDeleteFlagJob(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("标签删除失败");
        }
    }

    @Override
    public OperationResult update(Flag flag) {
        try {
            Query query = new Query(Criteria.where("id").is(flag.getId()));
            Update update = new Update();
            update.set("name", flag.getName());
            mongoTemplate.updateFirst(query,update,Flag.class);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本更新失败");
        }
    }

    @Override
    public Flag findById(String id) {
        return flagDao.findById(id).orElse(new Flag());
    }

    @Override
    public List<Flag> findAll(String userId) {
        Flag flag = new Flag();
        flag.setUserId(userId);
        return flagDao.findAll(Example.of(flag));
    }

    @Override
    public Page<Flag> findPage(Flag flag, int page, int size) {
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.skip(page*size).limit(size);
        if(!StringUtils.isEmpty(flag.getUserId())) {
            query.addCriteria(Criteria.where("userId").is(flag.getUserId()));
        }
        if(!StringUtils.isEmpty(flag.getName())) {
            query.addCriteria(Criteria.where("name").alike(Example.of(flag.getName())));
        }
        long count = mongoTemplate.count(query, Flag.class);
        List<Flag> flags = mongoTemplate.find(query, Flag.class);
        return new PageImpl<Flag>(flags,PageRequest.of(page,size),count);
    }

    @Override
    public Flag findWholeTree(String userId) {
        List<Flag> all = findAll(userId);
        Flag root = Flag.getRootFlag();
        configurateTree(root,all);
        return root;
    }

    @Override
    public Flag findSubTree(String userId, String parentId) {
        List<Flag> all = findAll(userId);
        Flag root = findById(parentId);
        configurateTree(root,all);
        return root;
    }

    public void configurateTree(Flag root, List<Flag> all) {
        List<Flag> collect = all.stream().filter(flag -> flag.getParentId().equals(root.getId())).collect(Collectors.toList());
        if(collect.isEmpty()) {
            return;
        }
        root.getChildren().addAll(collect);
        collect.forEach(flag -> {
            configurateTree(flag,all);
        });
    }
}
