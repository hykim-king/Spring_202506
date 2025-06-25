package com.pcwk.ehr.time;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

	// xml sql id="getMapperDateTime"
	public String getMapperDateTime();

	@Select("SELECT SYSDATE FROM dual")
	public String getDateTime();

	
}
