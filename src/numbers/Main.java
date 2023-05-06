package src.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static final String[] ALLOWED_PROPERTIES = new String[]{"even", "odd", "duck", "buzz", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "happy", "sad","-even", "-odd", "-duck", "-buzz", "-palindromic", "-gapful", "-spy", "-square", "-sunny", "-jumping", "-happy", "-sad"};

    private static final String PropertiesErrorMessage = "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";

    public static void main(String[] args) {

        ControllerInterface.printMenu();

        long startNumber;

        long counter;

        // Main engine
        while (true) {
            System.out.print("Enter a request: ");

            String input = ControllerInterface.getUserInput();

            if (input.equals("0")) {
                System.out.println("\n" + "Goodbye!");
                break;
            } else if (input.equals("")) {
                ControllerInterface.printMenu();
            }

            // Collect arguments
            String[] tokens = input.split(" ");

            try {

                if (tokens.length == 1) {

                    startNumber = Long.parseLong(tokens[0]);

                    counter = 1;

                    if (validateInputs(startNumber, counter)) continue;

                    NumberProperties.printSingleNumberProperties(startNumber);

                } else if (tokens.length == 2) {

                    startNumber = Long.parseLong(tokens[0]);

                    counter = Long.parseLong(tokens[1]);

                    if (validateInputs(startNumber, counter)) continue;

                    List<String> results = NumberProperties.getNumberProperties(startNumber, counter);

                    results.forEach(System.out::println);

                } else if (tokens.length == 3) {

                    startNumber = Long.parseLong(tokens[0]);

                    counter = Long.parseLong(tokens[1]);

                    if (validateInputs(startNumber, counter)) continue;

                    String property = tokens[2];

                    property = property.toLowerCase();

                    if (validateProperties(property)) continue;

                    List<String> results = NumberProperties.getNumberProperties(startNumber, counter, property);

                    results.forEach(System.out::println);
                } else if (tokens.length > 3) {

                    startNumber = Long.parseLong(tokens[0]);

                    counter = Long.parseLong(tokens[1]);

                    if (validateInputs(startNumber, counter)) continue;

                    String[] properties = new String[tokens.length - 2];

                    for (int i = 0; i < tokens.length - 2; i++) {
                        properties[i] = tokens[i + 2];
                    }

                    if (validateProperties(properties)) continue;

                    List<String> results = NumberProperties.getNumberProperties(startNumber, counter, properties);

                    results.forEach(System.out::println);
                }

            } catch (Exception e) {
                // Nothing here.
            }

        }

    }


    private static boolean validateProperties(String property) {
        if (!Arrays.asList(ALLOWED_PROPERTIES).contains(property) && property != null) {
            System.out.println("The property " + property + " is wrong.\n" + PropertiesErrorMessage);
            return true;
        }
        return false;
    }

    private static boolean validateProperties(String[] properties) {
        List<String> allowedProperties = Arrays.asList(ALLOWED_PROPERTIES);
        List<String> invalidProperties = new ArrayList<>();
        boolean hasMutuallyExclusiveProperties = false;

        for (String property : properties) {
            if (!allowedProperties.contains(property.toLowerCase())) {
                invalidProperties.add(property.toUpperCase());
            }
        }

        if (invalidProperties.size() > 1) {
            System.out.println("The properties " + invalidProperties + " are wrong.\nAvailable properties: " + PropertiesErrorMessage);
            return true;
        } else if (invalidProperties.size() == 1) {
            System.out.println("The property '" + invalidProperties.get(0).toUpperCase() + "' is wrong.\nAvailable properties: " + PropertiesErrorMessage);
            return true;
        }

        for (int i = 0; i < properties.length - 1; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                String prop1 = properties[i].toLowerCase();
                String prop2 = properties[j].toLowerCase();

                if ((prop1.equals("even") && prop2.equals("odd")) || (prop1.equals("odd") && prop2.equals("even")) ||
                        (prop1.equals("-even") && prop2.equals("-odd")) || (prop1.equals("-odd") && prop2.equals("-even")) ||
                        (prop1.equals("even") && prop2.equals("-even")) || (prop1.equals("-even") && prop2.equals("even")) ||
                        (prop1.equals("odd") && prop2.equals("-odd")) || (prop1.equals("-odd") && prop2.equals("odd")) ||
                        (prop1.equals("sunny") && prop2.equals("square")) || (prop1.equals("square") && prop2.equals("sunny")) ||
                        (prop1.equals("-sunny") && prop2.equals("-square")) || (prop1.equals("-square") && prop2.equals("-sunny")) ||
                        (prop1.equals("spy") && prop2.equals("duck")) || (prop1.equals("duck") && prop2.equals("spy")) ||
                        (prop1.equals("duck") && prop2.equals("-duck")) || (prop1.equals("-duck") && prop2.equals("duck")) ||
                        (prop1.equals("-spy") && prop2.equals("-duck")) || (prop1.equals("-duck") && prop2.equals("-spy"))) {
                    System.out.println("The request contains mutually exclusive properties: [" + properties[i].toUpperCase() + ", " + properties[j].toUpperCase() + "]\nThere are no src.numbers with these properties.");
                    hasMutuallyExclusiveProperties = true;
                }
            }
        }

        return hasMutuallyExclusiveProperties;
    }


    private static boolean validateInputs(long startNumber, long counter) {
        if (startNumber <= 0) {
            System.out.println("The first parameter should be a natural number or zero.\n");
            return true;
        } else if (counter <= 0) {
            System.out.println("The second parameter should be a natural number.\n");
            return true;
        }
        return false;
    }


}
