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

	public int jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		int flag = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			log.debug("1.conn:" + conn);

			// ------------------------------------------------------------------
			pstmt = stmt.makePreparedStatement(conn);
			// ------------------------------------------------------------------

			// 3.2 sql실행
			flag = pstmt.executeUpdate();
			log.debug("4.flag:" + flag);
		} catch (SQLException e) {
			throw e;
		} finally {
			// 4.리소스 닫기
			if (null != pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}

			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return flag;
	}

	public void deleteAll() throws SQLException {

		StatementStrategy strategy = new UserDaoDeleteAll();
		jdbcContextWithStatementStrategy(strategy);

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

		// 자원반납
		rs.close();
		pstmt.close();
		conn.close();

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
		UserDTO outVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
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
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("3.pstmt:" + pstmt);
			log.debug("3.1 param:" + param);

			// param
			pstmt.setString(1, param.getUserId());

			// 4 sql실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				outVO = new UserDTO();
				outVO.setUserId(rs.getString("user_id"));
				outVO.setName(rs.getString("name"));
				outVO.setPassword(rs.getString("password"));
				outVO.setRegDt(rs.getString("reg_dt_str"));
				log.debug("4.1 outVO:" + outVO);
			}

			if (null == outVO) {// 조회된 데이터가 없으면 예외 발생
				throw new NullPointerException(param.getUserId() + " (아이디)를 확인하세요.");
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			// 5. 자원반납
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}

			if (null != pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}

			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}

		}

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
		
		StatementStrategy stmt=new UserDaoDoSave(param);
		flag = jdbcContextWithStatementStrategy(stmt);
		log.debug("flag:"+flag);
		
		
		return flag;
	}

}
