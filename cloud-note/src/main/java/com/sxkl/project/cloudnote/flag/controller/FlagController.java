package com.sxkl.project.cloudnote.flag.controller;

import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.flag.entity.Flag;
import com.sxkl.project.cloudnote.flag.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlagController {

    @Autowired
    private FlagService flagService;

    @PostMapping("/flagSave")
    public OperationResult save(@RequestBody Flag flag) {
        return flagService.save(flag);
    }

    @PostMapping("/flagDeleteById")
    public OperationResult deleteById(String flagId) {
        return flagService.deleteById(flagId);
    }

    @PostMapping("/flagForceDeleteById")
    public OperationResult forceDeleteById(String flagId) {
        return flagService.forceDeleteById(flagId);
    }

    @PostMapping("/flagUpdate")
    public OperationResult update(@RequestBody Flag flag) {
        return flagService.update(flag);
    }

    @GetMapping("/flagFindById")
    public Flag findById(String flagId) {
        return flagService.findById(flagId);
    }

    @GetMapping("/flagFindAll")
    public List<Flag> findAll(String userId) {
        return flagService.findAll(userId);
    }

    @GetMapping("/flagFindPage")
    public Page<Flag> findPage(Flag flag, int page, int size) {
        return flagService.findPage(flag,page,size);
    }
}
