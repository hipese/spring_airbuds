package com.kdt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Account;
import com.kdt.repositories.AccountRepository;
import com.kdt.security.SecurityUser;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accRepo;
	
}
