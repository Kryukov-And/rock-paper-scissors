public class Chooser {
    public static void chooseWinner(int userMove, int compMove) {
        if (notDraw(userMove, compMove)) {
            winOrLose(userMove, compMove);
        }
    }

    private static boolean winOrLose(int userMove, int compMove) {
        if (compMove > userMove) {
            return (compMove - userMove <= 3) ? lose() : win();
        }
        return (userMove - compMove <= 3) ? win() : lose();
    }

    private static boolean lose() {
        System.out.println("You lose :(");
        return false;
    }

    private static boolean win() {
        System.out.println("You win!");
        return true;
    }

    private static boolean notDraw(int userMove, int compMove) {
        if (userMove == compMove) {
            System.out.println("Draw!");
            return false;
        }
        return true;
    }
}
