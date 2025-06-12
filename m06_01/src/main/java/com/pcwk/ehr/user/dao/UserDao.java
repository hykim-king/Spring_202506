/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명: UserDao.java <br/>
 */
package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

public class UserDao {
	Logger log = LogManager.getLogger(getClass());

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private RowMapper<UserDTO> rowMapper = new RowMapper<UserDTO>() {

		@Override
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDTO outVO = new UserDTO();

			outVO.setUserId(rs.getString("user_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegDt(rs.getString("reg_dt_str"));
			// -----------------------------------------------------------------
			outVO.setLogin(rs.getInt("login"));
			outVO.setRecommend(rs.getInt("recommend"));
			outVO.setGrade(Level.valueOf(rs.getInt("grade")));
			outVO.setEmail(rs.getString("email"));

			log.debug("outVO:" + outVO);

			return outVO;
		}

	};

	public UserDao() {
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//다건등록
	public int saveAll() {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder(100);
		sb.append(" INSERT INTO member                                           \n");
		sb.append(" SELECT 'jamesol' || level AS user_id,                        \n");
		sb.append("        '이상무'   || level AS name,                            \n");
		sb.append("        '4321_'   || level AS password,                       \n");
		sb.append("        MOD(level,10)      AS login,                          \n");
		sb.append("        MOD(level,2)       AS recommend,                      \n");
		sb.append("        DECODE( MOD(level,3),0,3,MOD(level,3)) AS grade,      \n");
		sb.append("        'jamesol@paran.com9' AS email,                        \n");
		sb.append("        SYSDATE - level AS reg_dt                             \n");
		sb.append("   FROM dual                                                  \n");
		sb.append("  CONNECT BY LEVEL <=502                                      \n");
		log.debug("1.sql:\n" + sb.toString());
		flag = jdbcTemplate.update(sb.toString());
		log.debug("1.flag:" + flag);
		return flag;
	}
	
	public List<UserDTO> doRetrieve(UserDTO param){
		List<UserDTO> list=new ArrayList<UserDTO>();
		
		//paging: total_cnt, no
		StringBuilder sb = new StringBuilder(100);
		sb.append(" SELECT A.*,B.*                                                         \n");
		sb.append("   FROM (                                                               \n");
		sb.append("     SELECT tt3.RNUM AS no,                                             \n");
		sb.append("            tt3.user_id,                                                \n");
		sb.append("            tt3.name,                                                   \n");
		sb.append("            tt3.password,                                               \n");
		sb.append("            tt3.login,                                                  \n");
		sb.append("            tt3.recommend,                                              \n");
		sb.append("            tt3.grade,                                                  \n");
		sb.append("            tt3.email,                                                  \n");
		sb.append("            TO_CHAR(tt3.reg_dt,'YYYY/MM/DD HH24:MI:SS') AS reg_dt_str   \n");
		sb.append("     FROM (                                                             \n");
		sb.append("         SELECT ROWNUM as RNUM,                                         \n");
		sb.append("                tt2.*                                                   \n");
		sb.append("           FROM(                                                        \n");
		sb.append("                 SELECT t1.*                                            \n");
		sb.append("                  FROM member t1                                        \n");
		sb.append("                 ORDER BY t1.reg_dt DESC                                \n");
		sb.append("         )tt2                                                           \n");
		sb.append("         WHERE ROWNUM <=10                                              \n");
		sb.append("     )tt3                                                               \n");
		sb.append("     WHERE RNUM  >=1                                                    \n");
		sb.append(" )A                                                                     \n");
		sb.append(" CROSS JOIN                                                             \n");
		sb.append(" (                                                                      \n");
		sb.append("   SELECT COUNT(*) AS total_cnt                                         \n");
		sb.append("     FROM member                                                        \n");
		sb.append(" )B                                                                     \n");		
		
		Object[] args = {  };
		
		RowMapper<UserDTO> rowMapper = new RowMapper<UserDTO>() {

			@Override
			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserDTO outVO = new UserDTO();

				outVO.setUserId(rs.getString("user_id"));
				outVO.setName(rs.getString("name"));
				outVO.setPassword(rs.getString("password"));
				outVO.setRegDt(rs.getString("reg_dt_str"));
				outVO.setLogin(rs.getInt("login"));
				outVO.setRecommend(rs.getInt("recommend"));
				outVO.setGrade(Level.valueOf(rs.getInt("grade")));
				outVO.setEmail(rs.getString("email"));
				// -----------------------------------------------------------------
				outVO.setTotalCnt(rs.getInt("total_cnt"));
				outVO.setNo(rs.getInt("no"));
				

				//log.debug("outVO:" + outVO);

				return outVO;
			}

		};		
		log.debug("1.sql:\n" + sb.toString());
		list = jdbcTemplate.query(sb.toString(), rowMapper);
		return list;
	}
	
	
	
	public int doDelete(UserDTO param) {
		int flag = 0;

		StringBuilder sb = new StringBuilder(100);
		sb.append(" DELETE FROM member  \n");
		sb.append(" WHERE user_id = ?   \n");

		log.debug("1.sql:\n" + sb.toString());
		log.debug("2.param:\n" + param.toString());

		Object[] args = { param.getUserId() };

		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("3.flag:" + flag);

		return flag;
	}

	public int doUpdate(UserDTO param) {
		int flag = 0;
		StringBuilder sb = new StringBuilder(200);
		sb.append(" UPDATE member              \n");
		sb.append(" SET  name      = ?,        \n");
		sb.append("      password  = ?,        \n");
		sb.append("      login     = ?,        \n");
		sb.append("      recommend = ?,        \n");
		sb.append("      grade     = ?,        \n");
		sb.append("      email     = ?,        \n");
		sb.append("      reg_dt    = SYSDATE   \n");
		sb.append(" WHERE                      \n");
		sb.append("         user_id = ?        \n");
		log.debug("2.sql:\n" + sb.toString());

		Object[] args = { param.getName(), param.getPassword(), param.getLogin(), param.getRecommend(),
				param.getGrade().getValue(), param.getEmail(), param.getUserId()

		};
		log.debug("param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = jdbcTemplate.update(sb.toString(), args);
		log.debug("flag:" + flag);

		return flag;
	}

	public List<UserDTO> getAll() {
		List<UserDTO> userList = new ArrayList<UserDTO>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                                      \n");
		sb.append("     user_id,                                                \n");
		sb.append("     name,                                                   \n");
		sb.append("     password,                                               \n");
		sb.append("     login,                                                  \n");
		sb.append("     recommend,                                              \n");
		sb.append("     grade,                                                  \n");
		sb.append("     email,                                                  \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') AS reg_dt_str   \n");
		sb.append(" FROM                                                        \n");
		sb.append("     member                                                  \n");
		sb.append(" ORDER BY reg_dt DESC                                        \n");

		log.debug("1.sql:\n" + sb.toString());

		userList = jdbcTemplate.query(sb.toString(), rowMapper);

		return userList;
	}

	public void deleteAll() throws SQLException {
		// SQL작성만!
		// 1. Connection : X
		// 2. 자원 반납 : close() X
		StringBuilder sb = new StringBuilder(100);
		sb.append(" DELETE FROM member  \n");
		log.debug("2.sql:\n" + sb.toString());
		jdbcTemplate.update(sb.toString());

	}

	public int getCount() throws SQLException {

		int count = 0;
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT COUNT(*) total_cnt FROM member \n");
		log.debug("1.sql:\n" + sb.toString());

		count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		log.debug("2.count:" + count);

		return count;
	}

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return UserDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserDTO doSelectOne(UserDTO param) throws SQLException {
		// 변수 선언
		UserDTO outDTO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                                      \n");
		sb.append("     user_id,                                                \n");
		sb.append("     name,                                                   \n");
		sb.append("     password,                                               \n");
		sb.append("     login,                                                  \n");
		sb.append("     recommend,                                              \n");
		sb.append("     grade,                                                  \n");
		sb.append("     email,                                                  \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') AS reg_dt_str   \n");
		sb.append(" FROM                                                        \n");
		sb.append("     member                                                  \n");
		sb.append(" WHERE user_id = ?                                           \n");

		log.debug("1.sql:\n" + sb.toString());

		// Param
		Object[] args = { param.getUserId() };

		log.debug("2.param: ");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		outDTO = jdbcTemplate.queryForObject(sb.toString(), rowMapper, args);

		if (null == outDTO) {// 조회된 데이터가 없으면 예외 발생
			throw new EmptyResultDataAccessException(param.getUserId() + " (아이디)를 확인하세요.", 0);
		}

		return outDTO;
	}

	/**
	 * 단건등록
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int doSave(UserDTO param) throws SQLException {
		int flag = 0;

		// 3.1 sql
		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO member (   \n");
		sb.append("     user_id,           \n");
		sb.append("     name,              \n");
		sb.append("     password,          \n");
		sb.append("     login,             \n");
		sb.append("     recommend,         \n");
		sb.append("     grade,             \n");
		sb.append("     email,             \n");
		sb.append("     reg_dt             \n");
		sb.append(" ) VALUES ( ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            ?,          \n");
		sb.append("            SYSDATE )   \n");
		log.debug("2.sql:\n" + sb.toString());

		Object[] args = { param.getUserId(), param.getName(), param.getPassword(), param.getLogin(),
				param.getRecommend(), param.getGrade().getValue(), param.getEmail()

		};
		log.debug("param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = jdbcTemplate.update(sb.toString(), args);

		log.debug("flag:" + flag);

		return flag;
	}

}
