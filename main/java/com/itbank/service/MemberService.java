package com.itbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.model.MemberDTO;
import com.itbank.repository.MemberDAO;

@Service
public class MemberService {

	@Autowired private MemberDAO dao;

	public MemberDTO login(MemberDTO dto) {
		return dao.login(dto);
	}

	public int join(MemberDTO dto) {
		return dao.join(dto);
	}

	public int dupIdCheck(String userid) {
		return dao.selectCount(userid);
	}
}
