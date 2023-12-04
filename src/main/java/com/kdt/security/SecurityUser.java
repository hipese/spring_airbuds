package com.kdt.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.kdt.domain.entity.Member;

public class SecurityUser extends User {

	//따로 요구하는 것을 만들고 싶으면 작성해서 setter, getter를 만든다.
	private String name;
	public SecurityUser(Member m) {
		super(m.getId(), 
				"{noop}"+m.getPassword(),
				m.getEnabled(),
				true,true,true,
				AuthorityUtils.createAuthorityList(m.getRole()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
