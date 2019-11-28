package com.mall.common.base.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @author wenguoli
 * @date 2019/9/28 17:53
 */
public class RandomUtils {
    /**
     * 随机数Int的生成
     */
    // 随机数生成无边界的Int
    public static int getRandomForIntegerUnbounded() {
        int intUnbounded = new Random().nextInt();
        System.out.println(intUnbounded);
        return intUnbounded;
    }

    // 生成有边界的Int
    public static int getRandomForIntegerBounded(int min, int max) {
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        System.out.println(intBounded);
        return intBounded;
    }

    // 包含1而不包含10
    // 使用Apache Common Math3来生成有边界的Int
    public static int getRandomForIntegerBounded2(int min, int max) {
        int intBounded = new RandomDataGenerator().nextInt(min, max);
        System.out.println(intBounded);
        return intBounded;
    }

    // 包含1且包含10
    // 使用Apache Common Lang3的工具类来生成有边界的Int
    public static int getRandomForIntegerBounded3(int min, int max) {
        int intBounded = org.apache.commons.lang3.RandomUtils.nextInt(min, max);
        System.out.println(intBounded);
        return intBounded;
    }

    // 使用TreadLocalRandom来生成有边界的Int,包含min而不包含max
    public static int getRandomForIntegerBounded4(int min, int max) {
        int threadIntBound = ThreadLocalRandom.current().nextInt(min, max);
        System.out.println(threadIntBound);
        return threadIntBound;
    }

    /**
     * 随机数Long的生成
     */
    // 随机数生成无边界的Long
    public static long getRandomForLongUnbounded() {
        long unboundedLong = new Random().nextLong();
        System.out.println(unboundedLong);
        return unboundedLong;
    }

    // 因为Random类使用的种子是48bits，所以nextLong不能返回所有可能的long值，long是64bits。
    // 使用Random生成有边界的Long
    public static long getRandomForLongBounded(long min, long max) {
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        System.out.println(rangeLong);
        return rangeLong;
    }

    // 使用Apache Commons Math3来生成有边界的Long(RandomDataGenerator类提供的生成随机数的方法)
    public static long getRandomForLongBounded2(long min, long max) {
        long rangeLong = new RandomDataGenerator().nextLong(min, max);
        System.out.println(rangeLong);
        return rangeLong;
    }

    // 使用Apache Commons Lang3的工具类来生成有边界的Long(RandomUtils提供了对java.util.Random的补充)
    public static long getRandomForLongBounded3(long min, long max) {
        long longBounded = org.apache.commons.lang3.RandomUtils.nextLong(min, max);
        System.out.println(longBounded);
        return longBounded;
    }

    // 使用ThreadLocalRandom生成有边界的Long
    public static long getRandomForLongBounded4(long min, long max) {
        long threadLongBound = ThreadLocalRandom.current().nextLong(min, max);
        System.out.println(threadLongBound);
        return threadLongBound;
    }

    /**
     * 随机数Float的生成
     */
    // 随机数Float的生成生成0.0-1.0之间的Float随机数
    public static float getRandomForFloat0To1() {
        float floatUnbounded = new Random().nextFloat();
        System.out.println(floatUnbounded);
        return floatUnbounded;
    }

    // 以上只会生成包含0.0而不包括1.0的float类型随机数生成有边界的Float随机数
    public static float getRandomForFloatBounded(float min, float max) {
        float floatBounded = min + new Random().nextFloat() * (max - min);
        System.out.println(floatBounded);
        return floatBounded;
    }

    // 使用Apache Common Math来生成有边界的Float随机数
    public static float getRandomForFloatBounded2(float min, float max) {
        float randomFloat = new RandomDataGenerator().getRandomGenerator().nextFloat();
        float generatedFloat = min + randomFloat * (max - min);
        System.out.println(generatedFloat);
        return generatedFloat;
    }

    // 使用Apache Common Lang来生成有边界的Float随机数
    public static float getRandomForFloatBounded3(float min, float max) {
        float generatedFloat = org.apache.commons.lang3.RandomUtils.nextFloat(min, max);
        System.out.println(generatedFloat);
        return generatedFloat;
    }

    // 使用ThreadLocalRandom生成有边界的Float随机数
    // ThreadLocalRandom类没有提供

    /**
     * 随机数Double的生成
     */
    // 生成0.0d-1.0d之间的Double随机数
    public static double getRandomForDouble0To1() {
        double generatorDouble = new Random().nextDouble();
        System.out.println(generatorDouble);
        return generatorDouble;
    }

    // 与Float相同，以上方法只会生成包含0.0d而不包含1.0d的随机数生成带有边界的Double随机数
    public static double getRandomForDoubleBounded(double min, double max) {
        double boundedDouble = min + new Random().nextDouble() * (max - min);
        System.out.println(boundedDouble);
        return boundedDouble;
    }

    // 使用Apache Common Math来生成有边界的Double随机数
    public static double getRandomForDoubleBounded2(double min, double max) {
        double boundedDouble = new RandomDataGenerator().getRandomGenerator().nextDouble();
        double generatorDouble = min + boundedDouble * (max - min);
        System.out.println(generatorDouble);
        return generatorDouble;
    }

    // 使用Apache Common Lang生成有边界的Double随机数
    public static double getRandomForDoubleBounded3(double min, double max) {
        double generatedDouble = org.apache.commons.lang3.RandomUtils.nextDouble(min, max);
        System.out.println(generatedDouble);
        return generatedDouble;
    }

    // 使用ThreadLocalRandom生成有边界的Double随机数
    public static double getRandomForDoubleBounded4(double min, double max) {
        double generatedDouble = ThreadLocalRandom.current().nextDouble(min, max);
        System.out.println(generatedDouble);
        return generatedDouble;
    }


    /**
     * count 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     * letters true,生成的字符串可以包括字母字符
     * numbers true,生成的字符串可以包含数字字符
     * String random = RandomStringUtils.random(15, true, false);
     */
    public static String random(int count, boolean letters, boolean numbers) {
        String random = RandomStringUtils.random(count, letters, numbers);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数。
     * 将从所有字符集中选择字符
     * random = RandomStringUtils.random(22);
     */
    public static String random(int count) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String random = RandomStringUtils.random(count, chars);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数。
     * 字符将从字符串指定的字符集中选择，不能为空。如果NULL，则使用所有字符集。
     * random = RandomStringUtils.random(15, "abcdefgABCDEFG123456789");
     */
    public static String random(int count, String chars) {
        String random = RandomStringUtils.random(count, chars);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     * count:计算创建的随机字符长度
     * start:字符集在开始时的位置
     * end:字符集在结束前的位置，必须大于65
     * letters true,生成的字符串可以包括字母字符
     * numbers true,生成的字符串可以包含数字字符
     * random = RandomStringUtils.random(1009, 5, 129, true, true);
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        String random = RandomStringUtils.random(count, start, end, letters, numbers);
        System.out.println(random);
        return random;
    }

    /**
     * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z的选择）。
     * count:创建随机字符串的长度
     * random = RandomStringUtils.randomAlphabetic(15);
     */
    public static String randomAlphabetic(int count) {
        String random = RandomStringUtils.randomAlphabetic(count);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z的选择）。
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     * random = RandomStringUtils.randomAlphabetic(2, 15);
     */
    public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
        String random = RandomStringUtils.randomAlphabetic(minLengthInclusive, maxLengthExclusive);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     * count ：创建的随机数长度
     * random = RandomStringUtils.randomAlphanumeric(15);
     */
    public static String randomAlphanumeric(int count) {
        String random = RandomStringUtils.randomAlphanumeric(count);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     * random = RandomStringUtils.randomAlphanumeric(5, 68);
     */
    public static String randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive) {
        String random = RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数，字符将从ASCII值介于32到126之间的字符集中选择（包括）
     * count:随机字符串的长度
     * RandomStringUtils.randomAscii(15);
     */
    public static String randomAscii(int count) {
        String random = RandomStringUtils.randomAscii(count);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从ASCII值介于32到126之间的字符集中选择（包括）
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     * random = RandomStringUtils.randomAscii(15, 30);
     */
    public static String randomAscii(int minLengthInclusive, int maxLengthExclusive) {
        String random = RandomStringUtils.randomAscii(minLengthInclusive, maxLengthExclusive);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
     * count:生成随机数的长度
     * random = RandomStringUtils.randomNumeric(15);
     */
    public static String randomNumeric(int count) {
        String random = RandomStringUtils.randomNumeric(count);
        System.out.println(random);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,将从数字字符集中选择字符.
     * minLengthInclusive, 要生成的字符串的包含最小长度
     * maxLengthExclusive 要生成的字符串的包含最大长度
     * random = RandomStringUtils.randomNumeric(15, 20);
     */
    public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive) {
        String random = RandomStringUtils.randomNumeric(minLengthInclusive, maxLengthExclusive);
        System.out.println(random);
        return random;
    }

}
