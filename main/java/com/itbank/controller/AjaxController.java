package com.itbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itbank.service.MemberService;

@RestController		// 비동기 통신을 처리하는 컨트롤러
					// 내부 모든 메서드는 @ResponseBody를 적용받는다
public class AjaxController {
	
	@Autowired private MemberService memberService; 
	
	@GetMapping(value="/ajax/dupIdCheck", produces="application/json; charset=utf-8")
	@ResponseBody	
					// 작성하지 않아도, @RestController 내부에서는 자동 적용
					// 일반 컨트롤러와 달리, forward 혹은 redirect하지 않는다
					// 문자열, primitive type, JSON 형식의 객체 데이터를 반환한다
	public String dupIdCheck(String userid) {
		int row = memberService.dupIdCheck(userid);
		return row + "";
	}

}
