package com.gf.musics.web.dao;

import com.gf.musics.web.model.PermissionAssignment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "permissionAssignmentMapper")
public interface PermissionAssignmentMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(PermissionAssignment record);

    int insertSelective(PermissionAssignment record);

    PermissionAssignment selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(PermissionAssignment record);

    int updateByPrimaryKey(PermissionAssignment record);

    List<PermissionAssignment> selectByPage(Map map);
}