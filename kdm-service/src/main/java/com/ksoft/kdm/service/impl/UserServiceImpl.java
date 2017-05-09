package com.ksoft.kdm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import com.ksoft.kdm.dto.*;
import com.ksoft.kdm.entity.UserEntity;
import com.ksoft.kdm.enums.UserSexEnum;
import com.ksoft.kdm.mapper.UserMapper;
import com.ksoft.kdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */

//@Service(version = "1.0.0", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public ResponseDto<List<UserDto>> getAll(){
        //Example e=new Example(UserEntity.class);
        //e.setOrderByClause("sss");
        //System.out.println("s");
        //userMapper.selectByExample(e);
        List<UserEntity> users=userMapper.selectAll();
        return ResponseDtoFactory.toSuccess("",toDtos(users));
    }

    //@Override
    public void saveUser() {
        UserEntity u=new UserEntity();
        u.setUserName("test");
        u.setPassWord("ttt");
        userMapper.insert(u);
    }


    private List<UserDto> toDtos(List<UserEntity> users){
        List<UserDto> dtos = new ArrayList<>();
        if(null != users && users.size() > 0)
        for(UserEntity user : users){
            if(user != null){
                UserDto dto = new UserDto(user.getId(), user.getUserName(),user.getPassWord(),user.getUserSex());
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public ResponseDto<UserSearchDto> getPage(UserSearchDto searchDto) {
        Example e=new Example(UserEntity.class);
        e.setOrderByClause("id");
        e.createCriteria().andEqualTo("userName",searchDto.getUserName());
        PageRowBounds rowBounds = new PageRowBounds(searchDto.getOffset(), searchDto.getPageSize());
        List<UserEntity> list = userMapper.selectByExampleAndRowBounds(e,rowBounds);
        searchDto.setList(toDtos(list));
        /*UserEntity userEntity = userMapper.selectByPrimaryKey(4l);
        userEntity.setUserSex(UserSexEnum.WOMAN);
        userMapper.updateByPrimaryKey(userEntity);*/
        return ResponseDtoFactory.toSuccess("",searchDto);
    }

    public List<UserEntity> getAll2(UserEntity userInfo){
        PageRowBounds rowBounds = new PageRowBounds(0, 10);
        List<UserEntity> list = userMapper.selectByRowBounds(userInfo,rowBounds);
        //userInfo.setTotal(rowBounds.getTotal());
        return list;
    }
}
