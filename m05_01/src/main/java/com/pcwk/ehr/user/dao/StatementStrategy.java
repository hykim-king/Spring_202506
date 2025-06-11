package com.pcwk.ehr.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {

	//전략(Strategy): 알고리즘을 나타내는 인터페이스
    public PreparedStatement  makePreparedStatement(Connection conn) throws SQLException;
	
}
