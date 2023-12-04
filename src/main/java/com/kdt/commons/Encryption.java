package com.kdt.commons;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {
	public static String getSHA512(String pw) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(pw.getBytes());
		return String.format("%0128x", new BigInteger(1, md.digest()));

	}
}
