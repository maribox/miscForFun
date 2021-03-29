import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Arrays;

public class baseConverter {
    public static int globalTransferVar;

    public static void main(String... args) {
        int base1;
        int base2;
        /*---------------- Input Section --------------*/

        Scanner scan = new Scanner(System.in);
        System.out.print("Say between what you want to convert: ");
        String convertString = scan.nextLine();
        ArrayList<patternGroup> matchList = new ArrayList<patternGroup>();

        Pattern patt = Pattern.compile("[0-9]|bin|dec|hex|tern|quart|quin|senary|sept|oct|nonar|duodec", //
                Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = patt.matcher(convertString);
        ArrayList<Integer> convertBases = new ArrayList<Integer>();
        while (regexMatcher.find()) {
            for (int i = 0; i <= regexMatcher.groupCount(); i++) {
                // System.out.println(isNumeric(regexMatcher.group(i)));
                if (isNumeric(regexMatcher.group(i))) {
                    if (matchList.size() > 0 && matchList.get(matchList.size() - 1).end == regexMatcher.start(i)) {
                        matchList.set(matchList.size() - 1,
                                new patternGroup(matchList.get(matchList.size() - 1).val * 10 + globalTransferVar,
                                        matchList.get(matchList.size() - 1).start, regexMatcher.end(i)));
                        try {
                            convertBases.set(convertBases.size() - 1, matchList.get(matchList.size() - 1).val);
                        } catch (IndexOutOfBoundsException e) {
                            convertBases.add(matchList.get(matchList.size() - 1).val);

                        }
                    } else {
                        matchList.add(new patternGroup(globalTransferVar, regexMatcher.start(i), regexMatcher.end(i)));
                        convertBases.add(globalTransferVar);

                    }

                } else {

                    switch (regexMatcher.group(i)) {
                    case "bin":
                        convertBases.add(2);
                        break;
                    case "dec":
                        convertBases.add(10);
                        break;
                    case "hex":
                        convertBases.add(16);
                        break;
                    case "tern":
                        convertBases.add(3);
                        break;
                    case "quart":
                        convertBases.add(4);
                        break;
                    case "quin":
                        convertBases.add(5);
                        break;
                    case "senary":
                        convertBases.add(6);
                        break;
                    case "sept":
                        convertBases.add(7);
                        break;
                    case "oct":
                        convertBases.add(8);
                        break;
                    case "nonar":
                        convertBases.add(9);
                        break;
                    case "duodec":
                        convertBases.add(12);
                        break;
                    default:
                        throw new Error("Didn't find any code. This shouldn't happen.");
                    }

                    matchList.add(new patternGroup(globalTransferVar, regexMatcher.start(i), regexMatcher.end(i)));
                }
            }

        }

        try {
            System.out.print(
                    "\nYou want to convert base " + convertBases.get(0) + " to base " + convertBases.get(1) + ".");
            base1 = convertBases.get(0);
            base2 = convertBases.get(1);

        } catch (IndexOutOfBoundsException e) {
            try {
                System.out.println("Couldn't catch both bases. \nFrom which base do you convert from?  (only number)");
                System.out.print("Convert base      to     .\t\t Press ENTER to switch to next\rConvert base ");
                base1 = scan.nextInt();
                // System.out.println("To what base? (only number)");
                System.out.print("Convert base " + base1);
                printSpace(4 - String.valueOf(base1).length());
                System.out.print(" to ");
                base2 = scan.nextInt();
                System.out.print("\nYou want to convert base " + base1 + " to base " + base2 + ".");

            } catch (Exception ex) {
                System.out.println(ex);
                throw new Error("Couldn't get your bases. Exiting.");
            }
        }

        // System.out.println(base1 + " " + base2);

        System.out.println("\n\nWhat Number do you want to convert? ");
        String inputNumber = scan.nextLine();

        /*---------------- Convert Section --------------*/

        inputNumber = inputNumber.replace(",", ".");
        System.out.println(inputNumber);

        double value = 0.0;

        boolean notReady = true;
        int length1;
        ArrayList<String> valueList = new ArrayList<String>();
        valueList = Arrays.asList(inputNumber);
        System.out.print(valueList.get(0).getClass().getName());

        for (String digit : valueList) {
            System.out.print(digit);
        }

    }

    // int i = 1;
    /*
     * for( patternGroup match:matchList) { System.out.println(i + ": " +
     * match.val); i += 1; }
     * 
     */
    // System.out.println(convertString);

    public static void printSpace(int n) { // Python: Ah mate, just do x * " ". Java: MAKE A METHOD.
        if (n > 0) {
            System.out.print(" ");
            printSpace(n - 1);
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            globalTransferVar = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}