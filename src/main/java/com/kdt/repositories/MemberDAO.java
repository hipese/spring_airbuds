package com.kdt.repositories;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession db;
	
	public void updateId(String id, String newID) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("newID", newID);
		db.update("Member.updateID", param);
	}
}
