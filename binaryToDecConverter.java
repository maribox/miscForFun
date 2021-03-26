import java.util.Scanner;
import java.util.regex.*;

public class binaryToDecConverter {
    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Say what you want to convert: ");
        String convertString = scan.nextLine();

        try {
            Pattern patt = Pattern.compile(".*\\bin\\b.*|^dec|^hex|tern|quart|quin|senary|sept|oct|nonar|duodec",
                    Pattern.CASE_INSENSITIVE);
            Matcher regexMatcher = patt.matcher(convertString);
            while (regexMatcher.find()) {
                for (int i = 1; i <= regexMatcher.groupCount(); i++) {
                    System.out.print(regexMatcher.group(i));
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
}