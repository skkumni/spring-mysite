package com.itbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.exception.WrongAccessException;
import com.itbank.model.BoardDTO;
import com.itbank.model.MemberDTO;
import com.itbank.model.Paging;
import com.itbank.model.ReplyDTO;
import com.itbank.service.BoardService;
import com.itbank.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired private BoardService boardService;
	@Autowired private ReplyService replyService;

	@GetMapping("/list")
	public String list() {
		return "redirect:/board/list/1";
	}
	
	@GetMapping("/list/{page}")
	public ModelAndView list(@PathVariable("page") int page, String search) {
		// 검색어가 있을 경우와 검색어가 없을 경우의 게시글 전체 개수가 달라진다
		
		ModelAndView mav = new ModelAndView("board/list");
		int boardCount = boardService.getBoardCount(search);
		Paging paging = new Paging(page, boardCount);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("paging", paging);
		param.put("search", search);	// 검색어를 SQL에 반영할 수 있도록 추가한다
		
		List<BoardDTO> list = boardService.getBoardList(param);
		mav.addObject("list", list);
		mav.addObject("paging", paging);
		return mav;
	}
	
	@GetMapping("/view/{idx}")
	public ModelAndView view(@PathVariable("idx") int idx) {
		ModelAndView mav = new ModelAndView("board/view");
		BoardDTO dto = boardService.getBoard(idx);
		
		// 댓글을 불러와서 같이 추가하기
		List<ReplyDTO> replyList = replyService.selectList(idx);
		mav.addObject("replyList", replyList);
		
		mav.addObject("dto", dto);
		return mav;
	}
	
	@PostMapping("/replyWrite/{idx}")
	public String replyWrite(@PathVariable("idx") int board_idx, 
							 @SessionAttribute("login") MemberDTO login,
							 ReplyDTO dto) {
		
		dto.setBoard_idx(board_idx);
		dto.setWriter(login.getNickname());
		
		System.out.println("작성자 : " + dto.getWriter());
		System.out.println("게시글 번호 : " + dto.getBoard_idx());
		System.out.println("댓글 내용 : " + dto.getContent());
		System.out.println("parent_idx : " + dto.getParent_idx());
		System.out.println("reply_depth : " + dto.getReply_depth());
		
		int row = replyService.write(dto);
		System.out.println(row != 0 ? "댓글 작성 성공" : "댓글 작성 실패");
		
		return "redirect:/board/view/" + board_idx;
	}
	
	@GetMapping("/write")
	public void write() {}
	
	@PostMapping("/write")
	public String write(BoardDTO dto) throws IllegalStateException, IOException {
		int row = boardService.write(dto);
		int idx = boardService.getMaxIdx();
		System.out.println(row != 0 ? "성공" : "실패");
		
		return row != 0 ? "redirect:/board/view/" + idx : 
						  "redirect:/board/list";
	}
	
	
	@GetMapping("/delete/{idx}")
	public String delete(@PathVariable("idx") int idx, 
						 @SessionAttribute("login") MemberDTO login) throws WrongAccessException {
//		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("idx", idx);
		param.put("writer", login.getNickname());
		
		int row = boardService.delete(param);
		System.out.println(row != 0 ? "성공" : "실패");
		
		if(row == 0) {
			throw new WrongAccessException("본인의 글만 삭제할 수 있습니다");
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/modify/{idx}")
	public ModelAndView modify(@PathVariable("idx")int idx, 
							   @SessionAttribute("login") MemberDTO login) 
				throws WrongAccessException {
		
		ModelAndView mav = new ModelAndView("board/modify");
		BoardDTO dto = boardService.getBoard(idx);
		
		// 로그인된 사용자가 없거나, 게시글 작성자와 현재 로그인 사용자가 일치하지 않는다면
		// *** 실제로는, 로그인 인터셉터의 preHandle()에 의해서 로그인 객체가 없다면 컨트롤러에 도달할 수 없다 ***
		// *** or 연산자의 경우, A or B 라면, A가 참일 경우 전체가 참이므로, B를 체크하지 않는다
		if(dto == null || dto.getWriter().equals(login.getNickname()) == false) {
			throw new WrongAccessException("본인의 글만 수정할 수 있습니다");
		}
		mav.addObject("dto", dto);
		
		return mav;
	}
	
	@PostMapping("/modify/{idx}")
	public String modify(BoardDTO dto) throws IllegalStateException, IOException {
		int row = boardService.modify(dto);
		
		if(row != 0) {
			return "redirect:/board/view/" + dto.getIdx();
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/replyDelete/{idx}")
	public String replyDelete(@PathVariable("idx") int idx,
							  @RequestHeader("referer") String referer) {
		
		// 현재 요청 이전 단계에 있던 주소 (참조자)
		System.out.println("referer : " + referer);
		System.out.println("idx : " + idx);
		
		int row = replyService.deleteReply(idx);
		System.out.println(row != 0 ? "댓글 삭제 성공" : "댓글 삭제 실패");
		
		return "redirect:" + referer;
	}
	
}












