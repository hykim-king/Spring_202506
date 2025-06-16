/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명: DTO.java <br/>
 */
package com.pcwk.ehr.cmn;

/**
 * @author user
 *
 */
public class DTO {
	private int no          ;//번호
	private int totalCnt    ;//총 글수
	
	public DTO() {}

	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	@Override
	public String toString() {
		return "DTO [no=" + no + ", totalCnt=" + totalCnt + "]";
	}


	
	
}
