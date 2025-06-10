/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명: UserDao.java <br/>
 */
package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pcwk.ehr.user.domain.UserDTO;

public class UserDao {
	Logger log = LogManager.getLogger(getClass());

	private DataSource dataSource;

	public UserDao() {
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int getCount() throws SQLException {

		int count = 0;
		// 1.
		// 2.
		Connection conn = dataSource.getConnection();
		log.debug("1.conn:" + conn);
		StringBuilder sb = new StringBuilder(200);
		sb.append("select count(*) total_cnt from member \n");

		log.debug("2.sql:\n" + sb.toString());
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		log.debug("3.pstmt:" + pstmt);

		// 4 sql실행
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		log.debug("4.count:" + count);

		return count;
	}

	/**
	 * 전체 삭제
	 * 
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException {

		// delete from member;
		// 1.
		// 2.
		Connection conn = dataSource.getConnection();
		log.debug("1.conn:" + conn);

		StringBuilder sb = new StringBuilder(200);
		sb.append(" delete from member  \n");

		log.debug("2.sql:\n" + sb.toString());
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		log.debug("3.pstmt:" + pstmt);

		// 3.2 sql실행
		int flag = pstmt.executeUpdate();

		log.debug("4.flag:" + flag);

		// 4.리소스 닫기
		pstmt.close();
		conn.close();
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
		UserDTO outVO = null;
		// 1.
		// 2.
		Connection conn = dataSource.getConnection();

		log.debug("1.conn:" + conn);
		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                \n");
		sb.append("     user_id,          \n");
		sb.append("     name,             \n");
		sb.append("     password,         \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') AS  reg_dt_str          \n");
		sb.append(" FROM                  \n");
		sb.append("     member            \n");
		sb.append(" WHERE  user_id = ?    \n");

		log.debug("2.sql:\n" + sb.toString());

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		log.debug("3.pstmt:" + pstmt);
		log.debug("3.1 param:" + param);

		// param
		pstmt.setString(1, param.getUserId());

		// 4 sql실행
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			outVO = new UserDTO();

			// db 데이터 조회
			outVO.setUserId(rs.getString("user_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPassword(rs.getString("password"));
			outVO.setRegDt(rs.getString("reg_dt_str"));

			log.debug("4.1 outVO:" + outVO);

		}

		// 조회된 데이터가 없으면 예외 발생
		if (null == outVO) {
			throw new NullPointerException(param.getUserId() + " (아이디)를 확인하세요.");
		}

		// 5. 자원반납
		rs.close();
		pstmt.close();
		conn.close();

		return outVO;
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
		// 1.
		// 2.
		Connection conn = dataSource.getConnection();

		log.debug("1.conn:" + conn);

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

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		log.debug("3.pstmt:" + pstmt);
		log.debug("3.1 param:" + param);

		// param설정
		pstmt.setString(1, param.getUserId());
		pstmt.setString(2, param.getName());
		pstmt.setString(3, param.getPassword());

		// 3.2 sql실행
		flag = pstmt.executeUpdate();

		log.debug("4.flag:" + flag);

		// 4.리소스 닫기
		pstmt.close();
		conn.close();

		return flag;
	}

}
