package com.web.curation.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{
	
	@Autowired
	UserServiceImpl uSer;

	private static final String SALT =  "sns6joSecret";
	
	private String secretKey = SALT;
	
	private byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	
	private final Key KEY = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	
	@Override
	public String create(UserDto dto){
		String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
						 .setHeaderParam("regDate", System.currentTimeMillis())
						 .setSubject(dto.getEmail())
						 .claim("u_index", dto.getU_index())
						 .claim("nickname", dto.getNickname())
						 .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24)))
						 .signWith(signatureAlgorithm, KEY)
						 .compact();
		return jwt;
	}

	
	@Override
	public boolean validationToken(String jwt) {
		// TODO Auto-generated method stub
		
		if(jwt != null) {
			int userIndex = Integer.parseInt(this.getUserIndex(jwt));
			
			int u_index = uSer.infoUser(this.getUserEmail(jwt)).getU_index();
			
			if(userIndex == u_index)
				return true;
			return false;
		}
		return false;
	}

	@Override
	public boolean getExpToken(String jwt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserEmail(String jwt) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parser()
					.setSigningKey(KEY)
					.parseClaimsJws(jwt)
					.getBody()
					.getSubject();
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public String getUserIndex(String jwt) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parser()
					.setSigningKey(KEY)
					.parseClaimsJws(jwt)
					.getBody()
					.get("u_index") + "";
		} catch(Exception e) {
			return null;
		}
	}	

	
	
//	public byte[] generateKey(){
//		byte[] key = null;
//		try {
//			key = SALT.getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			if(log.isInfoEnabled()){
//				e.printStackTrace();
//			}else{
//				log.error("Making JWT Key Error ::: {}", e.getMessage());
//			}
//		}
//		
//		return key;
//	}
}