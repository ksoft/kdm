package com.ksoft.kdm.mapper;


import com.ksoft.kdm.common.BaseMapper;
import com.ksoft.kdm.entity.UserEntity;

/**
 * UserMapper
 *
 * @author HZH
 * @date 2017/3/31
 */
//@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /*@Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);*/


    /*@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity user);*/
}
