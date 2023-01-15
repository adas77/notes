package pl.backend.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordStrength {
    public static boolean validatePassStrength(String pass) {

        int len = pass.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                        '*', '(', ')', '-', '+'));

        for (char c : pass.toCharArray()) {
            if (Character.isLowerCase(c))
                hasLower = true;
            if (Character.isUpperCase(c))
                hasUpper = true;
            if (Character.isDigit(c))
                hasDigit = true;
            if (set.contains(c))
                specialChar = true;
        }

        if (hasDigit && hasLower && hasUpper && specialChar && (len >= 8)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateCommonPass(String pass) {
        List<String> passwords = List.of(
                "password",
                "12345678",
                "baseball",
                "football",
                "jennifer",
                "superman",
                "trustno1",
                "michelle",
                "sunshine",
                "123456789",
                "12345678910",
                "starwars",
                "computer",
                "corvette",
                "princess",
                "iloveyou",
                "maverick",
                "samantha",
                "steelers",
                "whatever",
                "hardcore",
                "internet",
                "mercedes",
                "bigdaddy"

        );

        if (passwords.contains(pass)) {
            return false;
        }
        return true;
    }
}
