package com.pcwk.ehr.mymapper.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.mymapper.domain.MyMapperDTO;

@Repository
public class MyMapperDaoImpl implements PLog {
	
	
	final String NAMESPACE = "com.pcwk.ehr.mymapper";
	final String DOT       = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;//DB연결, SQL수행, 자원반납
	
	
	public MyMapperDaoImpl() {
		
	}
	
	public MyMapperDTO doHello(MyMapperDTO inVO) {
		MyMapperDTO  outVO = null;
		
		String statement = NAMESPACE+DOT+"doHello";
		log.debug("1.statement:"+statement);
		log.debug("2.param:"+inVO);
		
		outVO=sqlSessionTemplate.selectOne(statement, inVO);
		log.debug("3.outVO:"+outVO);
		return outVO;
	}
}
