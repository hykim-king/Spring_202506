package com.pcwk.ehr.mymapper;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.mymapper.dao.MyMapperDaoImpl;
import com.pcwk.ehr.mymapper.domain.MyMapperDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
class MyMapperDaoTest  {
	Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	MyMapperDaoImpl  dao;
	
	MyMapperDTO  dto;
	
	
	@BeforeEach
	void setUp() throws Exception {
		dto = new MyMapperDTO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void doHello() {
		dto.setUserId("pcwk");
		dto.setPassword("4321");
		
		
		MyMapperDTO outVO=dao.doHello(dto);
		log.debug("outVO:{}",outVO);
		assertNotNull(outVO);
	}
	
	@Disabled
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(dao);
		assertNotNull(dto);
		
		
		log.debug("context:"+context);
		log.debug("dao:"+dao);
		log.debug("dto:"+dto);
	}

}
