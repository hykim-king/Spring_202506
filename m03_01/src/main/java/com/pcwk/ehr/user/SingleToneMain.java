/**
 * Package Name : com.pcwk.ehr.user <br/>
 * 파일명: SingleToneMain.java <br/>
 */
package com.pcwk.ehr.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pcwk.ehr.user.dao.DaoFactory;
import com.pcwk.ehr.user.dao.UserDao;

public class SingleToneMain {

	public static void main(String[] args) {
		// none singletone

		DaoFactory factory = new DaoFactory();
		UserDao dao01 = factory.userDao();
		UserDao dao02 = factory.userDao();

		System.out.println("dao01:" + dao01);
		System.out.println("dao02:" + dao02);

		System.out.println("========================================");

		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao10 = (UserDao) context.getBean("userDao");
		UserDao dao20 = context.getBean("userDao", UserDao.class);
		System.out.println("dao10:" + dao10);
		System.out.println("dao20:" + dao20);

	}

}
