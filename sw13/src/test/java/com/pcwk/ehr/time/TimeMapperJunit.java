package com.pcwk.ehr.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.PLog;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class TimeMapperJunit implements PLog {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	TimeMapper timeMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void getMapperDateTime() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *getMapperDateTime()*     │");
		log.debug("└───────────────────────────┘");
		
		
		log.debug("getMapperDateTime():{}",timeMapper.getMapperDateTime());
	}
	
	
	@Disabled
	@Test
	void getDateTime() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *getDateTime()*           │");
		log.debug("└───────────────────────────┘");
		
		log.debug("timeMapper.getDateTime():{}",timeMapper.getDateTime());
		
	}

	@Disabled
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(timeMapper);
		
		log.debug("context:" + context);
		log.debug("timeMapper:" + timeMapper);
	}

}
