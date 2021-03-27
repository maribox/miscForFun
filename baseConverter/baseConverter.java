import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class baseConverter {
    public static int globalTransferVar;

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Say what you want to convert: ");
        String convertString = scan.nextLine();
        List<List<String>> addresses = new ArrayList<List<String>>();
        try {
            Pattern patt = Pattern.compile("[0-9]|bin|dec|hex|tern|quart|quin|senary|sept|oct|nonar|duodec",
                    Pattern.CASE_INSENSITIVE);
            Matcher regexMatcher = patt.matcher(convertString);
            while (regexMatcher.find()) {
                for (int i = 0; i <= regexMatcher.groupCount(); i++) {
                    System.out.println(isNumeric(regexMatcher.group(i)));

                    matchList.add(new ArrayList<Object>());
                    System.out.println(regexMatcher.start(i));
                    System.out.println(regexMatcher.end(i));
                    // matched text: regexMatcher.group(i)
                    // match start: regexMatcher.start(i)
                    // match end: regexMatcher.end(i)
                }
            }
        } catch (PatternSyntaxException ex) {
            // Syntax error in the regular expression
        }

        // System.out.println(convertString);
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