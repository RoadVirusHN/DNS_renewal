package com.web.curation.service;

public class RandomPassword {
	
	/**
	 * 비밀번호로 사용 가능한 문자
	 * 알파벳 대소문자, 숫자, 일부 특수문자
	 */
	static final char PW_CHARACTER_LIST[] = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '~','!','@','#','$','%','^','&','*','(',')','_','+',
            '`','1','2','3','4','5','6','7','8','9','0','-','='
	};
	
	static final int PW_LENGTH = 6;
	
	
	/**
	 * PW_LENGTH 값만큼 무작위 값으로 문자열을 생성
	 * @return PW_LENGTH 값만큼 생성된 문자열 
	 */
	public static String getRandomPassword() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < PW_LENGTH; i++) {
			int pickIdx = (int)(Math.random() * (PW_CHARACTER_LIST.length)); // PW_CHARACTER_LIST.length-1 
			sb.append(PW_CHARACTER_LIST[pickIdx]);
		}
		return sb.toString();
	}

}

