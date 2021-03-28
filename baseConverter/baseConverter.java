import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class baseConverter {
    public static int globalTransferVar;

    public static void main(String... args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Say what you want to convert: ");
        String convertString = scan.nextLine();
        ArrayList<patternGroup> matchList = new ArrayList<patternGroup>();

        Pattern patt = Pattern.compile("[0-9]|bin|dec|hex|tern|quart|quin|senary|sept|oct|nonar|duodec", //
                Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = patt.matcher(convertString);
        ArrayList<Integer> convertBases = new ArrayList<Integer>();
        while (regexMatcher.find()) {
            for (int i = 0; i <= regexMatcher.groupCount(); i++) {
                System.out.println(isNumeric(regexMatcher.group(i)));
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
                        System.out.println("binary");
                        convertBases.add(2);
                        break;
                    case "dec":
                        System.out.println("dec");
                        convertBases.add(10);
                        break;
                    case "hex":
                        System.out.println("hex");
                        convertBases.add(16);
                        break;
                    case "tern":
                        System.out.println("tern");
                        convertBases.add(3);
                        break;
                    case "quart":
                        System.out.println("quart");
                        convertBases.add(4);
                        break;
                    case "quin":
                        System.out.println("quin");
                        convertBases.add(5);
                        break;
                    case "senary":
                        System.out.println("senary");
                        convertBases.add(6);
                        break;
                    case "sept":
                        System.out.println("sept");
                        convertBases.add(7);
                        break;
                    case "oct":
                        System.out.println("oct");
                        convertBases.add(8);
                        break;
                    case "nonar":
                        System.out.println("nonar");
                        convertBases.add(9);
                        break;
                    case "duodec":
                        System.out.println("duodec");
                        convertBases.add(12);
                        break;
                    default:
                        throw new Error("Didn't find any code. This shouldn't happen.");
                    }
                    matchList.add(new patternGroup(globalTransferVar, regexMatcher.start(i), regexMatcher.end(i)));
                }
            }

        }
        int i = 0;
        for (patternGroup match : matchList) {
            System.out.println(i + ": " + match.val);
            i += 1;
        }

        System.out.print("You want to convert base " + convertBases.get(0) + " to base " + convertBases.get(1));

    }

    int i = 1;
    /*
     * for( patternGroup match:matchList) { System.out.println(i + ": " +
     * match.val); i += 1; }
     * 
     */
    // System.out.println(convertString);

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