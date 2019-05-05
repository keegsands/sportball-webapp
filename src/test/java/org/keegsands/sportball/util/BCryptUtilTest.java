package org.keegsands.sportball.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.Test;

public class BCryptUtilTest {

	@Test
	public void encryptPassword() {
		final String password = "password";
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String encodedPassword = passwordEncoder.encode(password);
		System.out.println(encodedPassword);
	}
}
