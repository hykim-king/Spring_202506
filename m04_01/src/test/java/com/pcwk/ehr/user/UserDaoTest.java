/**
 * Package Name : com.pcwk.ehr.user <br/>
 * 파일명: UserTest.java <br/>
 */
package com.pcwk.ehr.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserDTO;

public class UserDaoTest {
	UserDao dao;
	UserDTO dto01;
	UserDTO dto02;
	UserDTO dto03;

	ApplicationContext context;

	Logger log = LogManager.getLogger(getClass());
	
	@Before
	public void setUp() throws Exception {
		String path = "applicationContext.xml";
		context = new GenericXmlApplicationContext(path);
		log.debug("context:" + context);

		dto01 = new UserDTO("pcwk01", "이상무01", "4321", "사용안함");
		dto02 = new UserDTO("pcwk02", "이상무02", "4321a", "사용안함");
		dto03 = new UserDTO("pcwk03", "이상무03", "4321a", "사용안함");

		// 메모리에 생성된 객체를 추출
		dao = context.getBean("userDao", UserDao.class);
		log.debug("dao:" + dao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		log.debug("***************************");
		log.debug("@After");
		log.debug("***************************");
	}

	@Test(expected = NullPointerException.class)
	public void getFailure() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		// 3. 단건조회

		// 1.
		dao.deleteAll();

		// 2.
		dao.doSave(dto01);
		int count = dao.getCount();
		assertEquals(1, count);

		String unknownId = dto01.getUserId() + "_99";
		dto01.setUserId(unknownId);

		UserDTO outVO = dao.doSelectOne(dto01);

	}

	//@Ignore
	@Test
	public void addAndGet() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		// 2.1 전체건수 조회
		// 3. 단건조회
		// 4. 비교

		// 1.
		dao.deleteAll();

		// 2.
		int flag = dao.doSave(dto01);
		assertEquals(1, flag);

		// 2.1
		int count = dao.getCount();
		assertEquals(count, 1);

		dao.doSave(dto02);
		count = dao.getCount();
		assertEquals(count, 2);

		dao.doSave(dto03);
		count = dao.getCount();
		assertEquals(count, 3);

		// 3.
		UserDTO outVO = dao.doSelectOne(dto01);
		assertNotNull(outVO);
		isSameUser(outVO, dto01);

		UserDTO outVO2 = dao.doSelectOne(dto02);
		assertNotNull(outVO2);
		isSameUser(outVO2, dto02);

		UserDTO outVO3 = dao.doSelectOne(dto03);
		assertNotNull(outVO3);
		isSameUser(outVO3, dto03);

	}

	// 데이터 비교
	public void isSameUser(UserDTO outVO, UserDTO dto01) {
		assertEquals(outVO.getUserId(), dto01.getUserId());
		assertEquals(outVO.getName(), dto01.getName());
		assertEquals(outVO.getPassword(), dto01.getPassword());
	}

}
