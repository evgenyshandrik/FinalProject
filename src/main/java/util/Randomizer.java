package util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Randomizer {

    private Randomizer() {
    }

    private static final Random random = new Random();
    private static final Faker faker = new Faker();

    public static Object randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomEmail(int min, int max) {
        return randomString(min, max, false) + "@" + randomString(min, max, false) + "." + randomString(min, max, false);
    }

    public static String randomAddress() {
        return faker.address().streetAddress();
    }

    public static String randomString(int min, int max, boolean numbers) {
        return RandomStringUtils.random(Integer.parseInt(randomInt(min, max).toString()), true, numbers);
    }

    public static String randomPhone() {
        return "+" + RandomStringUtils.random(12, false, true);
    }
}
