/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명: UserDao.java <br/>
 */
package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pcwk.ehr.user.domain.UserDTO;

public class UserDao {

	public UserDao() {
	}

	
	// 이름 : getConnection
	// param : 없음
	// return Connection
	//
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// 1.
		Connection conn=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2.
		String url = "jdbc:oracle:thin:@192.168.100.30:1522:XE";
		String user = "scott";
		String password = "pcwk";
		conn = DriverManager.getConnection(url, user, password);

		System.out.println("1.conn:" + conn);
		
		return conn;
	}
	
	
	
	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return UserDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserDTO doSelectOne(UserDTO param) throws ClassNotFoundException, SQLException {
		UserDTO outVO = null;
		// 1.
		// 2.
		Connection conn = getConnection();

		System.out.println("1.conn:" + conn);
		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                \n");
		sb.append("     user_id,          \n");
		sb.append("     name,             \n");
		sb.append("     password,         \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') AS  reg_dt_str          \n");
		sb.append(" FROM                  \n");
		sb.append("     member            \n");
		sb.append(" WHERE  user_id = ?    \n");

		System.out.println("2.sql:\n" + sb.toString());

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		System.out.println("3.pstmt:" + pstmt);
		System.out.println("3.1 param:" + param);

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

			System.out.println("4.1 outVO:" + outVO);

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
	public int doSave(UserDTO param) throws ClassNotFoundException, SQLException {
		int flag = 0;
		// 1.
		// 2.
		Connection conn = getConnection();

		System.out.println("1.conn:" + conn);

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

		System.out.println("2.sql:\n" + sb.toString());

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		System.out.println("3.pstmt:" + pstmt);
		System.out.println("3.1 param:" + param);

		// param설정
		pstmt.setString(1, param.getUserId());
		pstmt.setString(2, param.getName());
		pstmt.setString(3, param.getPassword());

		// 3.2 sql실행
		flag = pstmt.executeUpdate();

		System.out.println("4.flag:" + flag);

		// 4.리소스 닫기
		pstmt.close();
		conn.close();

		return flag;
	}

}
