import java.security.SecureRandom;
import java.util.Objects;
import java.util.Scanner;

class Main
{
    public static void rockPaperScissors(String[] elements) {
        int compMove = makeMove(elements.length);
        String key = HMAC.execute(elements[compMove]);
        if (isUserPlayed(elements, compMove))
            System.out.println(key);
    }

    private static boolean isUserPlayed(String[] elements, int compMove) {
        printAllMoves(elements);
        String userMove = readUserMove();
        return processUserMove(elements, userMove, compMove);
    }

    private static boolean processUserMove(String[] elements, String userMove, int compMove) {
        if (userMove.equals("?")) {
            ResultTable.showTable(elements);
            return false;
        }
        return processUserMoveDigit(elements, userMove, compMove);
    }

    private static boolean processUserMoveDigit(String[] elements, String userMove, int compMove) {
        int userMoveDigit = Integer.parseInt(userMove);
        if (userMoveDigit == 0) {
            return false;
        }
        System.out.println("Your move: " + elements[userMoveDigit - 1] + "\nComputer move: " + elements[compMove]);
        Chooser.chooseWinner(userMoveDigit - 1, compMove);
        return true;
    }


    private static String readUserMove() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    private static void printAllMoves(String[] elements) {
        System.out.println("Available moves:");
        for (int i = 1; i <= elements.length; i++) {
            System.out.println(i + " - " + elements[i - 1]);
        }
        System.out.print("0 - exit\n? - help\nEnter your move:");
    }

    public static int makeMove(int upperBound) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(upperBound);
    }

    public static boolean checkArguments(String[] args) {
        if (checkArgsAmount(args.length) && checkSimilarArgs(args)) {
            return true;
        }
        System.out.println("Invalid arguments were given");
        return false;
    }

    private static boolean checkSimilarArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if ((i != j) && (Objects.equals(args[i], args[j]))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkArgsAmount(int length) {
        return !((length == 1) || (length % 2 == 0));
    }



    public static void main(String[] args) {
        if (checkArguments(args)) {
            rockPaperScissors(args);
        }
    }
}