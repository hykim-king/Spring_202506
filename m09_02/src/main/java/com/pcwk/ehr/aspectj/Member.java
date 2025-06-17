/**
 * Package Name : com.pcwk.ehr.aspectj <br/>
 * 파일명 : Member.java <br/>
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
package com.pcwk.ehr.aspectj;

/**
 * @author user
 *
 */
public interface Member {

	int doSave();
	
	int doUpdate();
	
	int doInsert();
	
	int delete();
	
	int doRetrieve(int num);
	
}
