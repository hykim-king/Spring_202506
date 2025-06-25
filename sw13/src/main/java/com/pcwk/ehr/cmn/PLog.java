/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명 : PLog.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-17<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.cmn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.common.reflection.qual.GetClass;

/**
 * @author user
 *
 */
public interface PLog {
	public static final Logger log=LogManager.getLogger(PLog.class);
}
