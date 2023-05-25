package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.model.ReplyDTO;
import com.itbank.repository.ReplyDAO;

@Service
public class ReplyService {

	@Autowired private ReplyDAO dao;
	
	public List<ReplyDTO> selectList(int idx) {
		List<ReplyDTO> list = dao.selectList(idx);
		list.forEach(reply -> {
			if(reply.getReply_depth() < 0) {
				reply.setContent("삭제된 댓글입니다");
			}
		});
		return list;
	}

	public int write(ReplyDTO dto) {
		return dao.insert(dto);
	}

	public int deleteReply(int idx) {
		return dao.delete(idx);
	}

}
