package com.pcwk.ehr.user.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController implements PLog {

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
	
	
	
	@PostMapping("/doSave.do")
	public String doSave(@RequestParam String userId, @RequestParam String name, Model model) throws SQLException {
		String viewName = "user/user_mng";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");
		// http://localhost:8080/ehr/user/doSave.do?userId=pcwk01
		// String userId = req.getParameter("userId");
		log.debug("userId:" + userId);
		log.debug("name:" + name);

		// 화면(view)로 데이터 전달
		model.addAttribute("userId", userId);
		model.addAttribute("name", name);

		/// WEB-INF/views/+viewName+.jsp -> /WEB-INF/views/user/user_mng.jsp
		return viewName;
	}

}
