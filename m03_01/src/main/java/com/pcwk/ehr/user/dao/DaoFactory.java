/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명 : DaoFactory.java <br/>
 */
package com.pcwk.ehr.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보
public class DaoFactory {

	@Bean // 오브젝트 생성을 담당하는 IoC용 메서드라는 표시
	public UserDao userDao() {

		UserDao dao = new UserDao(connectionMaker());

		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}

}
