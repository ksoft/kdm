package com.ksoft.kdm.service;

import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.UserDto;
import com.ksoft.kdm.dto.UserSearchDto;

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
