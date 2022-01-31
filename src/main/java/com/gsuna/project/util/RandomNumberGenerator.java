package com.gsuna.project.util;

import java.util.Random;

public class RandomNumberGenerator {
	
	private static Random random = new Random();
	
	public static Long generateNumber() {
		return random.nextLong();
	}

}
