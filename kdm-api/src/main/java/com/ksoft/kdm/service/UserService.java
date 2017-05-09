package com.zcckj.storeshow.service;

import com.zcckj.storeshow.dto.ResponseDto;
import com.zcckj.storeshow.dto.UserDto;
import com.zcckj.storeshow.dto.UserSearchDto;

import java.util.List;

/**
 * UserService
 *
 * @author HZH
 * @date 2017/4/11
 */
public interface UserService {
    ResponseDto<List<UserDto>> getAll();
    void saveUser();
    ResponseDto<UserSearchDto> getPage(UserSearchDto userDto);
}
