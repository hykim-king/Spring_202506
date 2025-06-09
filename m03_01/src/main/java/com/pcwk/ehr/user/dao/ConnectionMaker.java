package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	/**
	 * DB 연결
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Connection makeConnection() throws ClassNotFoundException, SQLException;

}