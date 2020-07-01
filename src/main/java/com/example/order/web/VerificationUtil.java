package com.example.order.web;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
     * @author chencj
     * @date 2020/7/1
     */
@Component
public class VerificationUtil {
   
	@Autowired
	private RedisUtil redisUtil;
	
	public String signCode(String email) {
		String s=RandomStringUtils.randomNumeric(6);
		this.redisUtil.set(email, s, 60*20);
		return s;
	}
	
	public boolean checkCode(String email,String code) {
		String s=this.redisUtil.get(email);
		if(StringUtils.isEmpty(s)) {
			return false;
		}
		if(s.equals(code)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			return false;
		}
		String regex = "(([a-z]|[A-Z]|[0-9])+)@(([a-z]|[A-Z]|[0-9])+)(\\.)(([a-z]|[A-Z]|[0-9])+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
