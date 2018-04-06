package lykrast.mysticalwildlife.common.util;

import java.util.Random;

public class RandomUtil {
	private RandomUtil() {}
	
	/**
	 * Returns a random integer between the 2 values included.
	 * 
	 * @param rand Random to use
	 * @param min Minimum value, included
	 * @param max Maximum value, included
	 * @return A random int between the 2 values
	 */
	public static int boundedInt(Random rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
	
	/**
	 * Returns the sum of n random integer between the 2 values included.
	 * Used for fortune drop calculations.
	 * 
	 * @param rand Random to use
	 * @param min Minimum value of each value, included
	 * @param max Maximum value of each value, included
	 * @param repeats Number of times to repeat
	 * @return The sum of all generated random ints
	 */
	public static int boundedIntRepeated(Random rand, int min, int max, int repeats) {
		int total = 0;
		for (int i=0; i<repeats; i++) total += boundedInt(rand, min, max);
		return total;
	}
}
