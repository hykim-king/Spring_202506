package com.pcwk.ehr.cmn;

public class MessageDTO extends DTO {
	private int messageId; // 상태:1(성공)/1이외는 실패
	private String message;// 메시지

	//Default생성자
	//인자있는 생성자
	//getter/setter
	//toString
	
	
	public MessageDTO() {
	}

	/**
	 * @param messageId
	 * @param message
	 */
	public MessageDTO(int messageId, String message) {
		super();
		this.messageId = messageId;
		this.message = message;
	}

	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageId=" + messageId + ", message=" + message + "]";
	}

}
