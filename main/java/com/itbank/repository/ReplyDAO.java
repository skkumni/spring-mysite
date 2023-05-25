package com.itbank.repository;

import java.util.List;

import com.itbank.model.ReplyDTO;

public interface ReplyDAO {

	List<ReplyDTO> selectList(int idx);

	int insert(ReplyDTO dto);

	int delete(int idx);

}
