/**
 * Package Name : com.pcwk.ehr.user <br/>
 * 파일명: UserMain.java <br/>
 */
package com.pcwk.ehr.user;

import java.sql.SQLException;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserDTO;

/**
 * @author user
 *
 */
public class UserMain {

	
	UserDTO dto01;
	UserDao dao;
	
	public UserMain() {
		dto01 = new UserDTO("pcwk01", "이상무01", "4321", "사용안함");
		dao   = new UserDao();
				
	}
	
	public void getConnection() {
		try {
			dao.doSave(dto01);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doSave() {
		try {
			int flag = dao.doSave(dto01);
			
			if(1==flag) {
				System.out.println("----------------");
				System.out.println("---doSave성공----");
				System.out.println("----------------");
			}else {
				System.out.println("---doSave실패----");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void doSelectOne() {
		 try {
			UserDTO dto=dao.doSelectOne(dto01);
			
			if(null !=dto) {
				System.out.println("----------------");
				System.out.println("---doSelectOne성공----");
				System.out.println("----------------");
			}else {
				System.out.println("---doSelectOne실패----");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserMain main=new UserMain();
		main.doSave();
		//main.doSelectOne();

	}

}
