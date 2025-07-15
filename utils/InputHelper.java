package utils; 
import java.util.Scanner;

public class InputHelper{
    static Scanner scanner = new Scanner(System.in);
    
    public static int getInt(
        String message, 
        int min_range, 
        int max_range, 
        String emptyErrorMessage,
        String formatErrorMessage
    ){
        while (true) {
            System.out.print(message);
            String option = scanner.nextLine();

            if (!option.trim().isEmpty()){
                try {
                    int intOption = Integer.parseInt(option.trim());

                    if (intOption < min_range || intOption > max_range){
                        String outOfRangeErrorMessage = String.format("The number must be between %d and %d, not %d", min_range, max_range, intOption);
                        System.out.println(outOfRangeErrorMessage);
                        continue;
                    }

                    return intOption;
                } catch (NumberFormatException e) {
                    System.out.println(formatErrorMessage);
                }
            } else {
                System.out.println(emptyErrorMessage);
            }
        }
    };
};