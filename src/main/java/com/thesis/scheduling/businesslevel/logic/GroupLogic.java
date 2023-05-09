package com.thesis.scheduling.businesslevel.logic;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.modellevel.entity.Group;
import com.thesis.scheduling.modellevel.mapper.GroupMapper;
import com.thesis.scheduling.modellevel.model.M_For_Selection_Response;
import com.thesis.scheduling.modellevel.service.GroupService;

@Service
public class GroupLogic {

    private final GroupService groupService;
    private final GroupMapper mapper;

    public GroupLogic(GroupService groupService, GroupMapper mapper) {
        this.groupService = groupService;
        this.mapper = mapper;
    }

    // GET
    public Iterable<M_For_Selection_Response> showAllTeacherForPlan() {
        return mapper.toMShowForSelection(groupService.findAll());
    }

}
