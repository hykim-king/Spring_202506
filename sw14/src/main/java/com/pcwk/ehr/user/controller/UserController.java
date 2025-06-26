package com.pcwk.ehr.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.cmn.PcwkString;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;
import com.pcwk.ehr.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger log = LogManager.getLogger(getClass());

	@Autowired
	private UserService userService;

	public UserController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ UserController()                                        │");
		log.debug("└─────────────────────────────────────────────────────────┘");

	}

	@GetMapping("/doSaveView.do")
	public String doSaveView() {
		String viewName = "user/user_mng";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSaveView()*            │");
		log.debug("└───────────────────────────┘");

		return viewName;
	}
	
	
	@GetMapping(value="/doRetrieve.do")
	public String doRetrieve(Model model, SearchDTO inVO ) throws SQLException {
		String viewName = "user/user_list";
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doRetrieve()*            │");
		log.debug("└───────────────────────────┘");		
		
		//pageNo==0 ->1
		//pageSize==0 ->10
		//페이지 번호
		int pageNo=PcwkString.nvlZero(inVO.getPageNo(), 1);
		//페이지 사이즈
		int pageSize=PcwkString.nvlZero(inVO.getPageSize(), 10);

		//검색구분
		String searchDiv = PcwkString.nullToEmpty(inVO.getSearchDiv());
		//검색어
		String searchWord = PcwkString.nullToEmpty(inVO.getSearchWord());
		
		
		inVO.setPageNo(pageNo);
		inVO.setPageSize(pageSize);
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);
		
		log.debug("inVO:{}",inVO);
		
		List<UserDTO> list = userService.doRetrieve(inVO);
		
		model.addAttribute("list", list);

		return viewName;
	}	
	
	
	@GetMapping(value="/doSelectOne.do")
	public String doSelectOne(Model model, @RequestParam("userId") String userId ) throws SQLException {
		// 동기 통신
		// 회원단건 조회
		// db단건 조회
		// 화면/model
		
		String viewName = "user/user_mng";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSelectOne()*           │");
		log.debug("└───────────────────────────┘");		
		
		String userIdIn = PcwkString.nullToEmpty(userId);
		log.debug("userIdIn:{}",userIdIn);
		
		UserDTO inVO=new UserDTO();
		inVO.setUserId(userIdIn);
		log.debug("inVO:{}",inVO);
		
		UserDTO outVO = userService.doSelectOne(inVO);
		
		model.addAttribute("userDTO", outVO);
		
		return viewName;
	}

	//doUpdate
	@PostMapping(value = "/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(UserDTO param, Model model) throws SQLException {
		// 비동기 통신
		// 회원등록
		// db입력
		// 성공실패: JSON

		log.debug("┌───────────────────────────┐");
		log.debug("│ *doUpdate()*                │");
		log.debug("└───────────────────────────┘");
		// http://localhost:8080/ehr/user/doSave.do?userId=pcwk01
		// String userId = req.getParameter("userId");
		String jsonString = "";
		UserDTO inVO = param;

		log.debug("inVO:{}", inVO);
		
		int flag = userService.doUpdate(inVO);
		
		String message = "";
		if (1 == flag) {
			message = inVO.getUserId() + "님이 수정 되었습니다.";
		} else {
			message = inVO.getUserId() + "님이 수정 실패 했습니다.";
		}

		MessageDTO messageDTO = new MessageDTO(flag, message);

		jsonString = new Gson().toJson(messageDTO);
		log.debug("jsonString:{}", jsonString);


		return jsonString;
		
	}
	
	
	
	
	
	
	@PostMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(name = "userId",required = true,defaultValue = "guest") String userId ) {
		String jsonString = "";
		// 비동기 통신
		// 회원등록
		// db입력
		// 성공실패: JSON

		log.debug("┌───────────────────────────┐");
		log.debug("│ *doDelete()*              │");
		log.debug("└───────────────────────────┘");
		
		log.debug("userId: {}",userId);
		UserDTO  inVO=new UserDTO();
        inVO.setUserId(userId);				
		
        log.debug("inVO: {}",inVO);
		
        //서비스 호출
        int flag = userService.doDelete(inVO);
        
        String message = "";
        if(1 == flag) {
        	message = userId +"님이 삭제 되었습니다.";
        }else {
        	message = userId +"님이 삭제 실패 했습니다.";
        }
        
        MessageDTO  messageDTO=new MessageDTO(flag, message);
        
        jsonString = new Gson().toJson(messageDTO);
        log.debug("jsonString:{}",jsonString);
		
		return jsonString;
	}
	
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, Model model) throws SQLException {
		// 비동기 통신
		// 회원등록
		// db입력
		// 성공실패: JSON

		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");
		// http://localhost:8080/ehr/user/doSave.do?userId=pcwk01
		// String userId = req.getParameter("userId");
		String jsonString = "";
		UserDTO inVO = new UserDTO();

		String userId = PcwkString.nullToEmpty(req.getParameter("userId"));
		String name = PcwkString.nullToEmpty(req.getParameter("name"));
		String password = PcwkString.nullToEmpty(req.getParameter("password"));

		String login = PcwkString.nullToEmpty(req.getParameter("login"));
		String recommend = PcwkString.nullToEmpty(req.getParameter("recommend"));
		String grade = PcwkString.nullToEmpty(req.getParameter("grade"));
		String email = PcwkString.nullToEmpty(req.getParameter("email"));

		log.debug("userId:" + userId);
		log.debug("name:" + name);
		log.debug("password:" + password);
		log.debug("login:" + login);
		log.debug("recommend:" + recommend);
		log.debug("grade:" + grade);
		log.debug("email:" + email);

		inVO.setUserId(userId);
		inVO.setName(name);
		inVO.setPassword(password);
		inVO.setLogin(Integer.parseInt(login));
		inVO.setRecommend(Integer.parseInt(recommend));
		int level = Integer.parseInt(grade);

		inVO.setGrade(Level.valueOf(level));
		inVO.setEmail(email);

		log.debug("inVO:{}", inVO);

		int flag = userService.doSave(inVO);

		String message = "";
		if (1 == flag) {
			message = name + "님이 등록 되었습니다.";
		} else {
			message = name + "님 등록 실패 했습니다.";
		}

		MessageDTO messageDTO = new MessageDTO(flag, message);

		jsonString = new Gson().toJson(messageDTO);
		log.debug("jsonString:{}", jsonString);

		/// WEB-INF/views/+viewName+.jsp -> /WEB-INF/views/user/user_mng.jsp
		return jsonString;
	}

}
