package utils; 

import java.util.Scanner;
import utils.LoopHelper;


public class InputHelper{

    // Scanner instance for this class only
    private static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * Prompts the user to enter a integer within a specified range
     * Returns -1 if user types "exit"
     */

    public static int getInt(
        final String message, 
        final int min_range, 
        final int max_range, 
        final String emptyErrorMessage,
        final String formatErrorMessage
    ){
        final int[] result = new int[1];

        LoopHelper.runLoop (() -> {

            System.out.print(message);

            // User input
            String input = SCANNER.nextLine().trim();
            
            // Check if input is not empty
            if (!input.isEmpty()){
                
                // Handle special exit command
                if (input.equalsIgnoreCase("exit")) {
                    result[0] = -1;
                    return false;
                }
                
                try {

                    int intValue = Integer.parseInt(input);

                    // Check input range
                    if (intValue < min_range || intValue > max_range){
                        System.out.printf("The number must be between %d and %d, not %d%n", min_range, max_range, intValue);
                        return true;
                    }

                    result[0] = intValue;
                    return false;
                    
                } catch (NumberFormatException e) {
                    System.out.println(formatErrorMessage);
                    return true;
                }
            } else {
                System.out.println(emptyErrorMessage);
                return true;
            }
        });
        return result[0];
    }

    /**
     * Prompts to the user to enter a double within specified range
     * If "isOptional" is true, user can leave it empty. (In this case returns null)
     * Returns -1.00 if user types "exit".
     */

    public static Double getDouble(
        final String message, 
        final int min_range, 
        final int max_range,
        final boolean isOptional, 
        final String emptyErrorMessage,
        final String formatErrorMessage
    ){
        final Double[] result = new Double[1];

        LoopHelper.runLoop(() -> {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();

            // Handle empty input
            if (!input.isEmpty()){
                
                // Handle special exit command
                if (input.equalsIgnoreCase("exit")) {
                        result[0] = -1.00;
                        return false;
                }

                try {

                    double doubleValue = Double.parseDouble(input);

                    // Check input range
                    if (doubleValue < min_range || doubleValue > max_range){
                        System.out.printf("The number must be between %d and %d, not %.2f%n" ,min_range, max_range, doubleValue);
                        result[0] = doubleValue;
                        return true;
                    }

                    result[0] = doubleValue;
                    return false;
                    
                } catch (NumberFormatException e) {
                    System.out.println(formatErrorMessage);
                    return true;
                }

            } else {

                //If optional returns null
                if (isOptional) {
                    result[0] = null;
                    return false;    
                
                // If not optional continues the loop
                } else{
                    System.out.println(emptyErrorMessage);
                    return true;
                
                }
            }
        });
        return result[0];
    }
    
    /**
     * Prompts to the user to enter a string. Returns the input if not empty
     * If optional, returns null when input is not provided.
     */
    public static String getString(
        final String message,
        final boolean isOptional, 
        final String emptyErrorMessage
    ){
        final String[] result = new String[1];

        LoopHelper.runLoop(() -> {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            
            if (!input.isEmpty()){
                result[0] = input;
                return false;
            } else if (isOptional){
                result[0] = null;
                return false;
            } else {
                System.out.println(emptyErrorMessage != null ? emptyErrorMessage : "Input can't be empty");
                return true;
            }
        });
        return result[0];
    };

    /**
     * Overload method of getString method, in case "isOptional" is true and not "emptyErrorMessage" is provided
     */
    public static String getString(String message, boolean isOptional){
        return getString(message, isOptional, null);
    }
}
