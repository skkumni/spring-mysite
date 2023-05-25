package com.itbank.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itbank.model.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> selectList(HashMap<String, Object> param);

	int insert(BoardDTO dto);

	@Select("select max(idx) from board")
	int selectMaxIdx();

	BoardDTO selectOne(int idx);

//	@Select("select count(*) from board")
	int selectBoardCount(String search);

	int delete(HashMap<String, Object> param);

	int update(BoardDTO dto);

	@Update("update board set viewCount = viewCount + 1 where idx = #{idx}")
	void updateViewCount(int idx);

}





