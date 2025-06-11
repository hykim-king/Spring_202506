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

	public List<UserDTO> getAll() {
		List<UserDTO> userList = new ArrayList<UserDTO>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                                     \n");
		sb.append("     user_id,                                               \n");
		sb.append("     name,                                                  \n");
		sb.append("     password,                                              \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') AS reg_dt_str  \n");
		sb.append(" FROM                                                       \n");
		sb.append("     member                                                 \n");
		sb.append(" ORDER BY reg_dt DESC                                       \n");

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
		sb.append(" SELECT                \n");
		sb.append("     user_id,          \n");
		sb.append("     name,             \n");
		sb.append("     password,         \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') AS  reg_dt_str          \n");
		sb.append(" FROM                  \n");
		sb.append("     member            \n");
		sb.append(" WHERE  user_id = ?    \n");

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
		sb.append(" INSERT INTO member (  \n");
		sb.append("     user_id,          \n");
		sb.append("     name,             \n");
		sb.append("     password,         \n");
		sb.append("     reg_dt            \n");
		sb.append(" ) VALUES ( ?,         \n");
		sb.append("            ?,         \n");
		sb.append("            ?,         \n");
		sb.append("            SYSDATE )  \n");
		log.debug("2.sql:\n" + sb.toString());

		Object[] args = { param.getUserId(), param.getName(), param.getPassword() };
		log.debug("param:");
		for (Object obj : args) {
			log.debug(obj.toString());
		}

		flag = jdbcTemplate.update(sb.toString(), args);

		log.debug("flag:" + flag);

		return flag;
	}

}
