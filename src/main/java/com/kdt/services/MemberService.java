package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Member;
import com.kdt.repositories.MemberRepository;
import com.kdt.security.SecurityUser;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository mRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member m= mRepo.findById(username).get();
		SecurityUser su =new SecurityUser(m);
		su.setName(m.getName());
		return su;
	}
	
}
