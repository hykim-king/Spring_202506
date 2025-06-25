/**
 * Package Name : com.pcwk.ehr.async <br/>
 * 파일명 : AsynControllerTest.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-25<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.async;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.cmn.PLog;

/**
 * @author user
 *
 */
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { 
		 "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class AsynControllerTest {
	Logger log=LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	
	
	//웹브라우저 대역 객체
	MockMvc mockMvc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
	void syncIndex() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *syncIndex()*             │");
		log.debug("└───────────────────────────┘");	
		
		//1.url, type, param
		MockHttpServletRequestBuilder requestBuilder
		  = MockMvcRequestBuilders.get("/async/async_index.do")
		  .param("name", "james");
		
		//2.호출
		ResultActions resultActions= mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());
		
		//3.응답: model
		MvcResult  mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		
		//return 데이터 확인
		String resutrnData = (String) model.get("req_name");
		log.debug("resutrnData: {}",resutrnData);
		
		assertEquals("james", resutrnData);
		
		//화면
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName: {}",viewName);		
		//async/async_index
		
		assertEquals("async/async_index", viewName);
	}
	
	//비동기식 호출
	@Disabled
	@Test
	void asyncResult() throws Exception {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *asyncResult()*           │");
		log.debug("└───────────────────────────┘");		
		
		//1.url, type, param
		MockHttpServletRequestBuilder requestBuilder
		  = MockMvcRequestBuilders.post("/async/async_result99.do")
		  .param("userId", "james")
		  .param("password", "4321a");
		
		//2.호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
		.andExpect(status().is2xxSuccessful());
		
		
		//3.응답
		String returnBody =resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();
		
		log.debug("returnBody:{}",returnBody);
		assertEquals("PCWK_james", returnBody);
	}
	
	
	@Disabled
	@Test
	void beans() {
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		
		log.debug("webApplicationContext:{}",webApplicationContext);
		log.debug("mockMvc:{}",mockMvc);
	}

}
