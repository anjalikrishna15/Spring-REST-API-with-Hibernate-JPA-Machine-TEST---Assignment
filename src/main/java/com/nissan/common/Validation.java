package com.nissan.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {
	public Boolean isNameValid(String name) {
		boolean bool = false;
		try {
			Pattern namePattern = Pattern.compile("[^A-Za-z]");
			Matcher nameMatcher = namePattern.matcher(name);
			if (nameMatcher.find()) {
				throw new InvalidNameException("HEY INVALID NAME,NAME CAN ONLY HAVE ALPHABETS");

			} else if (name.length() > 30) {
				throw new InvalidNameException("HEY! NAME SHOULD NOT BE MORE THAN 30 CHARACHTERS");
			} else {
				bool = true;
			}
		} catch (InvalidNameException e) {
			e.getMessage();

		}
		return bool;
	}

	public Boolean isNumberValid(long num) {
		boolean bool1 = false;
		String number = Long.toString(num);
		try {
			Pattern numPattern = Pattern.compile("[^0-9]");
			Matcher numMatcher = numPattern.matcher(number);
			if (numMatcher.find()) {
				throw new Exception("HEY INVALID NUMBER,NUMBER CAN ONLY HAVE DIGITS");
			} else if (number.length() != 10) {
				throw new Exception("HEY INVALID NUMBER, NUMBER SHOULD BE 10 DIGITS");
			} else {
				bool1 = true;
			}
		} catch (Exception e) {
			e.getMessage();

		}
		return bool1;
      
	}
	

}
