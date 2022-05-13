package com.web.pos.dao;

import com.web.pos.entity.UserInfo;

public interface UserInfoInterfacce {
	UserInfo getUserInfo(String id, String pwd);
	
	int insertUserInfo(String id, String pwd, String name);

	int deleteUserInfo(String pwd);
}
