package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
