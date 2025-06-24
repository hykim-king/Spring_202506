/**
 * Package Name : com.pcwk.ehr.user <br/>
 * 파일명: UserTest.java <br/>
 */
package com.pcwk.ehr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class UserDaoTest {
	Logger log = LogManager.getLogger(getClass());

	@Autowired
	UserDao dao;

	UserDTO dto01;
	UserDTO dto02;
	UserDTO dto03;

	SearchDTO search;
	
	@Autowired
	ApplicationContext context;

	@BeforeEach
	public void setUp() throws Exception {

		log.debug("context:" + context);

		dto01 = new UserDTO("pcwk01", "이상무01", "4321", "사용안함", 0, 0, Level.BASIC, "jamesol@paran.com");
		dto02 = new UserDTO("pcwk02", "이상무02", "4321a", "사용안함", 55, 10, Level.SILVER, "jamesol@paran.com");
		dto03 = new UserDTO("pcwk03", "이상무03", "4321a", "사용안함", 100, 40, Level.GOLD, "jamesol@paran.com");

		search = new SearchDTO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		log.debug("***************************");
		log.debug("@After");
		log.debug("***************************");
	}
	
	@Test
	void beans() {
		assertNotNull(context);
		assertNotNull(dao);
		
		log.debug(context);
		log.debug(dao);
	}
	
	
	//@Disabled
	@Test
	void doRetrieve() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 다건등록
		//3. paging조회
		
		//1.
		dao.deleteAll();
		
		//2.
		int count = dao.saveAll();
		log.debug("count:"+count);
		
		assertEquals(502, count);
		
		//paging
		search.setPageSize(10);
		search.setPageNo(1);
		
		//검색
		search.setSearchDiv("");
		search.setSearchWord("이상무");
		
		//3.
		List<UserDTO> list=dao.doRetrieve(search);
		for(UserDTO vo :list) {
			log.debug(vo);
		}

		
	}
	
	@Disabled
	@Test
	void doDelete() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록건수 비교
		//3. 삭제
		//4. 등록건수 비교==0
		
		// 1.
		dao.deleteAll();
		
		// 2.
		int flag = dao.doSave(dto01);
		assertEquals(1, flag);
		
		// 2.1
		int count = dao.getCount();
		assertEquals(count, 1);			
		
		//3.
		flag = dao.doDelete(dto01);
		assertEquals(1, flag);
		
		//4.
		count = dao.getCount();
		assertEquals(0, count);			
	}
	
	@Disabled
	@Test
	void doUpate() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		//1. 전체삭제
		//2. 단건등록
		//2.1 등록건수 비교
		//3. 단건조회
		//3.1 등록데이터 비교
		
		//4. 단건조회 데이터 수정
		//5. 수정
		//6. 수정 조회
		//7. 4 비교 6
		
		// 1.
		dao.deleteAll();
		
		// 2.
		int flag = dao.doSave(dto01);
		assertEquals(1, flag);
		
		// 2.1
		int count = dao.getCount();
		assertEquals(count, 1);		
		
		// 3.
		UserDTO outVO = dao.doSelectOne(dto01);
		assertNotNull(outVO);
		isSameUser(outVO, dto01);
		
		//4. 
		String upString = "_U";
		int    upInt    = 999;
		
		outVO.setName(outVO.getName()+upString);
		outVO.setPassword(outVO.getPassword()+upString);
		outVO.setLogin(outVO.getLogin()+upInt);
		outVO.setRecommend(outVO.getRecommend()+upInt);
		outVO.setGrade(outVO.getGrade().GOLD);
		outVO.setEmail(outVO.getEmail()+upString);
		
		log.debug("outVO:"+outVO);
		
		//5.
		flag = dao.doUpdate(outVO);
		assertEquals(1, flag);
		
		//6.
		UserDTO upVO = dao.doSelectOne(outVO);
		
		//7.
		isSameUser(outVO, upVO);
		System.out.println("***");
		
	}
	
	
	@Disabled
	@Test
	public void getAll() throws SQLException {
		// 매번 동일한 결과가 도출 되도록 작성
		// 1. 전체삭제
		// 2. 단건등록
		// 3. 단건등록
		// 4. 단건등록
		// 5. 전체조회: 3건

		// 1.
		dao.deleteAll();

		// 2.
		int flag = dao.doSave(dto01);
		assertEquals(1, flag);

		int count = dao.getCount();
		assertEquals(count, 1);

		// 3
		dao.doSave(dto02);
		count = dao.getCount();
		assertEquals(count, 2);
		// 4
		dao.doSave(dto03);
		count = dao.getCount();
		assertEquals(count, 3);

		// 5
		List<UserDTO> userList = dao.getAll();

		assertEquals(userList.size(), 3);

		for (UserDTO dto : userList) {
			log.debug(dto);
		}

	}

	@Disabled
	@Test
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

		assertThrows(EmptyResultDataAccessException.class, () -> {
			UserDTO outVO = dao.doSelectOne(dto01);

		});

	}

	@Disabled
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
		assertEquals(outVO.getLogin(), dto01.getLogin());
		assertEquals(outVO.getRecommend(), dto01.getRecommend());
		assertEquals(outVO.getGrade(), dto01.getGrade());
		assertEquals(outVO.getEmail(), dto01.getEmail());
	}

}
