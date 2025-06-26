package com.pcwk.ehr;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageDTO;
import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class UserControllerTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 웹브라우저 대역 객체
	MockMvc mockMvc;

	UserDTO userDTO01;

	@Autowired
	UserMapper userMapper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		userDTO01 = new UserDTO("pcwk01", "이상무01", "4321", "사용안함", 0, 0, Level.BASIC, "jamesol@paran.com");

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Test
	void doRetrieve() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doRetrieve()              │");
		log.debug("└───────────────────────────┘");
		
		//1. 전체삭제
		//2. 502건 입력
		//3. 조회
		
		//1.
		userMapper.deleteAll();
		
		//2.
		userMapper.saveAll();
		assertEquals(502, userMapper.getCount());
		
		//3.url호출, method:get, param
		MockHttpServletRequestBuilder  requestBuilder
		= MockMvcRequestBuilders.get("/user/doRetrieve.do")
		.param("pageNo", "0")
		.param("pageSize", "0")
		.param("searchDiv", "")
		.param("searchWord", "");
		
		
		//3.1
		ResultActions resultActions= mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());		
		
		
		//3.2 Model 데이터 조회
		MvcResult  mvcResult=resultActions.andDo(print()).andReturn();
				
		//3.3
		Map<String, Object> model=mvcResult.getModelAndView().getModel();
		List<UserDTO> list=(List<UserDTO>) model.get("list");
		
		for(UserDTO dto   :list) {
			log.debug(dto);
		}
		
		//3.4
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		assertEquals("user/user_list", viewName);
	}
	
	
	@Disabled
	@Test
	void doSelectOne() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSelectOne()             │");
		log.debug("└───────────────────────────┘");		
		
		//1. 전체삭제
		//2. 한건등록
		//3. 한건조회
		//4. 데이터비교
		
		//1.
		userMapper.deleteAll();
		
		//2.
		userMapper.doSave(userDTO01);
		assertEquals(1, userMapper.getCount());
		
		//3.url호출, method:post, param
		
		MockHttpServletRequestBuilder  requestBuilder
		= MockMvcRequestBuilders.get("/user/doSelectOne.do")
		.param("userId", userDTO01.getUserId());		
		
		//4.
		ResultActions resultActions= mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());
		
		//4.1 Model 데이터 조회
		MvcResult  mvcResult=resultActions.andDo(print()).andReturn();
		
		//4.2
		Map<String, Object> model=mvcResult.getModelAndView().getModel();
		UserDTO outVO = (UserDTO) model.get("userDTO");
		log.debug("outVO:{}",outVO);
		//데이터 비교
		isSameUser(outVO, userDTO01);
		
		//4.3 화면
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		
		assertEquals("user/user_mng", viewName);
		
	}
	
	// 데이터 비교
	private void isSameUser(UserDTO outVO, UserDTO dto01) {
		assertEquals(outVO.getUserId(), dto01.getUserId());
		assertEquals(outVO.getName(), dto01.getName());
		assertEquals(outVO.getPassword(), dto01.getPassword());
		assertEquals(outVO.getLogin(), dto01.getLogin());
		assertEquals(outVO.getRecommend(), dto01.getRecommend());
		assertEquals(outVO.getGrade(), dto01.getGrade());
		assertEquals(outVO.getEmail(), dto01.getEmail());
	}	
	
	
	@Disabled
	@Test
	void doUpdate() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doUpdate()                │");
		log.debug("└───────────────────────────┘");		
		
		//1.전체삭제
		//2.등록
		//2.1 등록 데이터 조회
		//3.수정
		//4.상태값 비교
		
		//1.
		userMapper.deleteAll();
		
		//2.
		userMapper.doSave(userDTO01);
		assertEquals(1, userMapper.getCount());
		
		//2.1
		UserDTO outVO = userMapper.doSelectOne(userDTO01);
		
		//3.url호출, method:post, param
		String upString = "_U";
		
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/user/doUpdate.do")
		  .param("userId", outVO.getUserId())
		  .param("name", outVO.getName()+upString)
		  .param("password", outVO.getPassword()+upString)
		  .param("login", String.valueOf(outVO.getLogin()+10))
		  .param("recommend", String.valueOf(outVO.getRecommend()+10))
		  .param("grade", String.valueOf(outVO.getGrade()))
		  .param("email", outVO.getEmail()+upString);
		
		
		//3.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
					
		//3.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);		
		
		//3.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
			
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("pcwk01님이 수정 되었습니다.", resultMessage.getMessage());
				
	}
	
	@Disabled
	@Test
	void doDelete() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");
		//1.전체삭제
		//2.등록
		//3.삭제
		//4.상태값 비교
		
		//1.
		userMapper.deleteAll();
		
		//2.
		userMapper.doSave(userDTO01);
		
		//2.1 비교
		assertEquals(1, userMapper.getCount());
		
		//3.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder
		= MockMvcRequestBuilders.post("/user/doDelete.do")
		.param("userId", userDTO01.getUserId());
		
		
		//3.2 호출
		ResultActions resultActions=mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(
							MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));		
	
		
		//3.3 호출 결과 받기
		String returnBody = resultActions.andDo(print())
		                     .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody:{}",returnBody);
		//{"messageId":1,"message":"pcwk01님이 삭제 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
		
		
		//3.4 json to MessageDTO 변환
		
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage);
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("pcwk01님이 삭제 되었습니다.",resultMessage.getMessage());
	}
	
	@Disabled
	@Test
	void doSave() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ doSave()                  │");
		log.debug("└───────────────────────────┘");

		//1.전체삭제
		//2.등록
		//3.상태값 비교
		
		//1.
		userMapper.deleteAll();
		
		//2.
		//2.1 url호출, method:post, param
		MockHttpServletRequestBuilder  requestBuilder 
		  =MockMvcRequestBuilders.post("/user/doSave.do")
		  .param("userId", userDTO01.getUserId())
		  .param("name", userDTO01.getName())
		  .param("password", userDTO01.getPassword())
		  .param("login", String.valueOf(userDTO01.getLogin()))
		  .param("recommend", String.valueOf(userDTO01.getRecommend()))
		  .param("grade", String.valueOf(userDTO01.getGrade().getValue()))
		  .param("email", userDTO01.getEmail());
		
		//2.2 호출
		ResultActions  resultActions=mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(
			MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
				
		
		//2.3 호출 결과 받기
		String returnBody =  resultActions.andDo(print())
				            .andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody: {}",returnBody);
		//{"messageId":1,"message":"이상무01님이 등록 되었습니다."
		//,"no":0,"totalCnt":0,"pageSize":0,"pageNo":0}
		
		//2.4 json to MessageDTO 변환
		MessageDTO resultMessage = new Gson().fromJson(returnBody, MessageDTO.class);
		log.debug("resultMessage: {}",resultMessage.toString());
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("이상무01님이 등록 되었습니다.", resultMessage.getMessage());
		
	}

	@Disabled
	@Test
	void beans() {
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(userDTO01);
		assertNotNull(userMapper);

		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("userDTO01:{}", userDTO01);
		log.debug("userMapper:{}", userMapper);
	}

}
