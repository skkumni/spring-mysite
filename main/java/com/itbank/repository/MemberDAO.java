package com.itbank.repository;

import org.apache.ibatis.annotations.Select;

import com.itbank.model.MemberDTO;

public interface MemberDAO {

	MemberDTO login(MemberDTO dto);

	int join(MemberDTO dto);

	@Select("select count(*) from member where userid = #{userid}")
	int selectCount(String userid);

}
