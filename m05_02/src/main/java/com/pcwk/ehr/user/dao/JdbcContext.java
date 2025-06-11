package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcContext {
	Logger log = LogManager.getLogger(getClass());

	private DataSource dataSource;

	public JdbcContext() {
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
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
}
