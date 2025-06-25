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
import com.pcwk.ehr.user.domain.UserDTO;
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
	public String doSave(UserDTO param, Model model) throws SQLException {
		String viewName = "user/user_mng";
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSave()*                │");
		log.debug("└───────────────────────────┘");
		// http://localhost:8080/ehr/user/doSave.do?userId=pcwk01
		// String userId = req.getParameter("userId");
		log.debug("param:" + param);

		//int flag =userService.doSave(param);

		/// WEB-INF/views/+viewName+.jsp -> /WEB-INF/views/user/user_mng.jsp
		return viewName;
	}

}
