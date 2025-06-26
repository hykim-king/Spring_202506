/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명 : WorkDiv.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-25<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.cmn;

import java.util.List;

/**
 * @author user
 *
 */
public interface WorkDiv<T> {
	/**
	 * 목록 조회
	 * 
	 * @param param
	 * @return List<T>
	 */
	List<T> doRetrieve(SearchDTO param);

	/**
	 * 단건 삭제
	 * 
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	int doDelete(T param);

	/**
	 * 수정
	 * 
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	int doUpdate(T param);

	/**
	 * 단건조회
	 * 
	 * @param param
	 * @return T
	 */
	T doSelectOne(T param);

	/**
	 * 단건등록
	 * 
	 * @param param
	 * @return 1(성공)/0(실패)
	 */
	int doSave(T param);
}
