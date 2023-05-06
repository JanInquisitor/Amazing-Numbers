package src.numbers;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class NumberProperties {
    public static List<String> getNumberProperties(long start, long count, String[] properties) {
        List<String> results = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#,###");
        properties = removeDash(properties);// this is for the following properties: [-odd,-even,-square,-sunny]
        long found = 0;
        for (long i = start; found < count; i++) {
            String numStr = formatter.format(i);
            String numProps = getNumberPropertiesString(i);
            if (properties.length == 0 || containsAllProperties(numProps, properties)) {
                results.add(String.format("%15s is %s", numStr, numProps));
                found++;
            }
        }
        return results;
    }


    public static List<String> getNumberProperties(long start, long count, String property) {
        List<String> results = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#,###");
        long found = 0;
//        property = removeDash(property); // this is for the following properties: [-odd,-even,-square,-sunny]
        if (property.startsWith("-")) {
            for (long i = start; found < count; i++) {
                String propertyWithoutDash = property.substring(1);
                String numStr = formatter.format(i);
                String numProps = getNumberPropertiesString(i);
                if (propertyWithoutDash.isEmpty() || !numProps.toLowerCase().contains(propertyWithoutDash.toLowerCase())) {
                    results.add(String.format("%15s is %s", numStr, numProps));
                    found++;
                }
            }
        } else {
            for (long i = start; found < count; i++) {
                String numStr = formatter.format(i);
                String numProps = getNumberPropertiesString(i);
                if (property == null || property.isEmpty() || numProps.toLowerCase().contains(property.toLowerCase())) {
                    results.add(String.format("%15s is %s", numStr, numProps));
                    found++;
                }
            }
        }
        return results;
    }

    public static List<String> getNumberProperties(long start, long count) {
        List<String> results = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#,###");
        for (long i = start; i < start + count; i++) {
            String numStr = formatter.format(i);
            String numProps = getNumberPropertiesString(i);
            results.add(String.format("%15s is %s", numStr, numProps));
        }
        return results;
    }


    private static boolean containsAllProperties(String numProps, String[] properties) {
        for (String prop : properties) {
            if (prop.startsWith("-")) {
                String option = prop.substring(1);
                if (numProps.toLowerCase().contains(option)) {
                    return false;
                }
            } else {
                if (!numProps.toLowerCase().contains(prop.toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }


    public static String getNumberPropertiesString(long number) {
        List<String> props = new ArrayList<>();
        props.add(isEven(number) ? "even" : "");
        props.add(isOdd(number) ? "odd" : "");
        props.add(isPalindromic(number) ? "palindromic" : "");
        props.add(isSpy(number) ? "spy" : "");
        props.add(isSquare(number) ? "square" : "");
        props.add(isSunny(number) ? "sunny" : "");
        props.add(isJumping(number) ? "jumping" : "");
        props.add(isHappy(number) ? "happy" : "sad");
        props.add(isBuzz(number) ? "buzz" : "");
        props.add(isDuck(number) ? "duck" : "");
        props.add(isGapful(number) ? "gapful" : "");
        return Arrays.stream(props.toArray(new String[0]))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(", "));
    }

    private static String removeDash(String word) {
        if (word.startsWith("-")) {
            String option = word.substring(1);

            switch (option) {
                case "even" -> word = "odd";
                case "odd" -> word = "even";
                case "sunny" -> word = "square";
                case "square" -> word = "sunny";
            }
        }
        return word;
    }

    private static String[] removeDash(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].startsWith("-")) {
                String option = tokens[i].substring(1);
                if (option.equals("even")) {
                    tokens[i] = "odd";
                } else if (option.equals("odd")) {
                    tokens[i] = "even";
                }
            }
        }
        return tokens;
    }

    private static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    private static boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    private static boolean isPalindromic(long number) {
        String str = String.valueOf(number);
        return str.contentEquals(new StringBuilder(str).reverse());
    }

    private static boolean isGapful(long number) {
        if (number < 100) {
            return false;
        }
        String stringNumber = Long.toString(number);
        String[] digits = stringNumber.split("");
        long divisor = Integer.parseInt(digits[0] + digits[stringNumber.length() - 1]);
        return number % divisor == 0;
    }

    private static boolean isSpy(long number) {
        long digit, sum = 0,
                product = 1;
        while (number > 0) {
            digit = number % 10;

            // getting the
            // sum of digits
            sum += digit;

            // getting the product
            // of digits
            product *= digit;
            number = number / 10;
        }

        // Comparing the
        // sum and product
        return sum == product;
    }

    private static boolean isEven(long number) {
        return number % 2 == 0;
    }

    private static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    static boolean isSquare(double x) {

        // Find floating point value of
        // square root of x.
        double sr = Math.sqrt(x);

        // If square root is an integer
        return ((sr - Math.floor(sr)) == 0);
    }

    // Function to check Sunny Number
    static boolean isSunny(long N) {
        return isSquare(N + 1);
    }

    static boolean isJumping(long number) {
        String numStr = String.valueOf(number);
        int n = numStr.length();
        if (n <= 1) {
            return true;
        }
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(Character.getNumericValue(numStr.charAt(i)) - Character.getNumericValue(numStr.charAt(i - 1)));
            if (diff != 1) {
                return false;
            }
        }
        return true;
    }

    static int numSquareSum(long n) {
        int squareSum = 0;
        while (n != 0) {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

    //  method return true if n is Happy number
    static boolean isHappy(long n) {
        long slow, fast;

        //  initialize slow and fast by n
        slow = fast = n;
        do {
            //  move slow number
            // by one iteration
            slow = numSquareSum(slow);

            //  move fast number
            // by two iteration
            fast = numSquareSum(numSquareSum(fast));

        }
        while (slow != fast);

        //  if both number meet at 1,
        // then return true
        return (slow == 1);
    }


    public static void printSingleNumberProperties(long number) {
        String message = "Properties of " + number + "\n";

        if (isBuzz(number)) {
            message += "        buzz: true\n";
        } else {
            message += "        buzz: false\n";
        }

        if (isDuck(number)) {
            message += "        duck: true\n";
        } else {
            message += "        duck: false\n";
        }

        if (isPalindromic(number)) {
            message += "        palindromic: true\n";
        } else {
            message += "        palindromic: false\n";
        }

        if (isGapful(number)) {
            message += "        gapful: true\n";
        } else {
            message += "        gapful: false\n";
        }

        if (isSpy(number)) {
            message += "        spy: true\n";
        } else {
            message += "        spy: false\n";
        }

        if (isSquare(number)) {
            message += "        square: true\n";
        } else {
            message += "        square: false\n";
        }

        if (isSunny(number)) {
            message += "        sunny: true\n";
        } else {
            message += "        sunny: false\n";
        }

        if (isJumping(number)) {
            message += "        jumping: true\n";
        } else {
            message += "        jumping: false\n";
        }

        if (isHappy(number)) {
            message += "        happy: true\n";
            message += "        sad: false\n";
        } else {
            message += "        happy: false\n";
            message += "        sad: true\n";
        }

        if (isEven(number)) {
            message += "        even: true\n";
        } else {
            message += "        even: false\n";
        }

        if (isOdd(number)) {
            message += "        odd: true\n";
        } else {
            message += "        odd: false\n";
        }

        System.out.println(message);

    }
}
