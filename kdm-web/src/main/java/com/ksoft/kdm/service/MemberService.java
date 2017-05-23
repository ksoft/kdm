package com.ksoft.kdm.service;

import com.ksoft.kdm.common.dto.CasualInfo;
import com.ksoft.kdm.common.dto.MemberInfo;
import com.ksoft.kdm.dto.ShowDzLogDto;

import javax.servlet.http.HttpServletRequest;

/**
 * MemberService
 * 会员信息
 * @author HZH
 * @date 2017/4/25
 */
public interface MemberService {

    public boolean validateTicket(HttpServletRequest request);

    public MemberInfo getPrincipal();

    public CasualInfo getCasualInfo(HttpServletRequest request);

    public ShowDzLogDto findUser(Long id, String firstName, String lastName);
}
