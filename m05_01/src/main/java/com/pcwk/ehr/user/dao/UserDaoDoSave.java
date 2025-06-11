/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일이름: UserDaoDoSave.java <br/>
 */
package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pcwk.ehr.user.domain.UserDTO;

/**
 * @author user
 *
 */
public class UserDaoDoSave implements StatementStrategy {
	Logger log = LogManager.getLogger(getClass());

	UserDTO inVO;

	public UserDaoDoSave() {
	}

	public UserDaoDoSave(UserDTO inVO) {
		this.inVO = inVO;
	}

	@Override
	public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
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
		log.debug("3.1 inVO:" + inVO);

		// param설정
		pstmt.setString(1, inVO.getUserId());
		pstmt.setString(2, inVO.getName());
		pstmt.setString(3, inVO.getPassword());

		return pstmt;
	}

}
