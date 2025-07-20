    package utils;
    import java.util.function.Consumer;
    import java.util.Scanner;
    import java.util.Map;
    import utils.InputHelper;
    import utils.LoopHelper;

    public class MenuHandler {

        /**
         * Shows a text menu and handle user input to run mapped actions.
         */
        public static void show(
            String menuText,
            int minOption,
            int maxOption,
            Map<Integer, Consumer<Scanner>> actions,
            Scanner scanner
        ) {

            // Loop until uses chooses exit (Always last option)
            LoopHelper.runLoop( () -> {

                // Print options
                System.out.println(menuText);

                // Get user's input with validation
                int option = InputHelper.getInt(
                    "Choose an option: ",
                    minOption,
                    maxOption,
                    "Can't be empty",
                    "Please enter a valid number"
                );

                // Retrieve the action associated with the chosen option
                Consumer<Scanner> action = actions.get(option);

                // Check if input is not null
                if (action != null) {
                    // Execute action, passing the Scanner
                    action.accept(scanner);
                    // If option is the max option, stop the loop
                    if (option == maxOption) {
                        return false;
                    } else{
                        return true;
                    }
                } else {
                    // Print error in case of invalid option
                    System.out.println("Invalid option!");
                    return true;
                }
            });
        }
    }
