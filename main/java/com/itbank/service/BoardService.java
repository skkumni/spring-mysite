package com.itbank.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itbank.model.BoardDTO;
import com.itbank.repository.BoardDAO;

@Service
public class BoardService {

	@Autowired private BoardDAO dao;
	
	private final String saveDirectory = "E:\\upload_board";
	
	public BoardService() {
		File dir = new File(saveDirectory);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
	}
	
	public List<BoardDTO> getBoardList(HashMap<String, Object> param) {
		return dao.selectList(param);
	}
	
	// UUID (Universaly Unique IDentifier, 범용 고유 식별자)
	public int write(BoardDTO dto) throws IllegalStateException, IOException {
		MultipartFile file = dto.getUploadFile();
		
		if(file.getSize() != 0) {
			// 1) 원본 파일의 확장자를 확인하여 저장한다
			String originalFileName = file.getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			String fileName = UUID.randomUUID().toString() + ext;
			fileName = fileName.replaceAll("-", "");
			
			// 2) 새로운 파일이름으로 saveDirectory에 저장한다 (확장자는 그대로 유지된다)
			File dest = new File(saveDirectory, fileName);
			file.transferTo(dest);
			
			// 3) 새로운 파일이름을 dto에 세팅한 후 dao에게 넘겨준다
			dto.setFileName(fileName);
		}
		
		return dao.insert(dto);
	}

	public int getMaxIdx() {
		return dao.selectMaxIdx();
	}

	// @Transactional, transactionManager, spring-tx, aop
	public BoardDTO getBoard(int idx) {
		dao.updateViewCount(idx);
		return dao.selectOne(idx);
	}

	public int getBoardCount(String search) {
		return dao.selectBoardCount(search);
	}

	public int delete(HashMap<String, Object> param) {
		return dao.delete(param);
	}

	public int modify(BoardDTO dto) throws IOException, IllegalStateException {
		MultipartFile file = dto.getUploadFile();
		
		if(file.getSize() != 0) {	// 새로운 파일이 들어왔으면 파일을 업로드한다 (추가와 동일한 코드)
			String originalFileName = file.getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			String fileName = UUID.randomUUID().toString() + ext;
			fileName = fileName.replaceAll("-", "");
			File dest = new File(saveDirectory, fileName);
			file.transferTo(dest);
			dto.setFileName(fileName);
		}
		return dao.update(dto);
	}

}





