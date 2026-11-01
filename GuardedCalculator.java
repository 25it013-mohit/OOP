import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        int attemptCounter = 0;

        System.out.println("--- Guarded Calculator ---");

        while (!success) {
            attemptCounter++;
            System.out.println("\n[Attempt #" + attemptCounter + "]");

            try {
                System.out.print("Enter the first number: ");
                String input1 = scanner.nextLine();
                double num1;
                try {
                    num1 = Double.parseDouble(input1);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("The first input is not a valid number.");
                }
                System.out.print("Enter an operator (+, -, *, /): ");
                String op = scanner.nextLine();
                System.out.print("Enter the second number: ");
                String input2 = scanner.nextLine();
                double num2;
                try {
                    num2 = Double.parseDouble(input2);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("The second input is not a valid number.");
                }
                double result = 0;
                switch (op) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                            if (num2 == 0) {
                            throw new DivideByZeroException("Cannot divide by zero.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Error: Invalid operator entered.");
                        continue;
                }
                System.out.println("Result: " + num1 + " " + op + " " + num2 + " = " + result);
                success = true;

            } catch (NumberFormatException ex) {
                System.out.println("Input Error: " + ex.getMessage());
            } catch (DivideByZeroException ex) {
                System.out.println("Arithmetic Error: " + ex.getMessage());
            } finally {
                System.out.println("System Log: Finished processing attempt #" + attemptCounter + ".");
            }
        }

        System.out.println("\nCalculation completed successfully. Program exiting.");
        scanner.close();
    }
}
