package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.user.domain.UserDTO;

public interface UserDao {

	//다건등록
	int saveAll();

	List<UserDTO> doRetrieve(UserDTO param);

	int doDelete(UserDTO param);

	int doUpdate(UserDTO param);

	List<UserDTO> getAll();

	void deleteAll() throws SQLException;

	int getCount() throws SQLException;

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return UserDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	UserDTO doSelectOne(UserDTO param) throws SQLException;

	/**
	 * 단건등록
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	int doSave(UserDTO param) throws SQLException;

}