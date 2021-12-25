import java.util.Objects;

public class ResultTable {

    public static void showTable(String[] elements) {
        String[][] resultTable = ResultTable.fillResultTable(elements.length);
        for (int i = -1; i < resultTable.length; i++) {
            for (int j = -1; j < resultTable.length; j++) {
                printElement(elements, resultTable, i, j);
            }
            System.out.println();
        }
    }

    public static void printElement(String[] elements, String[][] resultTable, int i, int j) {
        if ((i == -1) && (j == -1)) {
            System.out.print("y\\c\t");
        }
        else if (i == -1) {
            System.out.print(elements[j] + "\t");
        }
        else if (j == -1) {
            System.out.print(elements[i] + "\t");
        }
        else {
            System.out.print(resultTable[i][j] + "\t");
        }
    }

    public static String[][] fillResultTable(int amount) {
        String[][] resultTable = new String[amount][amount];
        fillWins(fillLoses(fillDraws(resultTable)));
        return resultTable;
    }

    public static String[][] fillDraws(String[][] resultTable) {
        for (int i = 0; i < resultTable.length; i++) {
            resultTable[i][i] = "Draw";
        }
        return resultTable;
    }

    public static String[][] fillLoses(String[][] resultTable) {
        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable.length; j++) {
                if ((Objects.nonNull(resultTable[i][j])) && (Objects.equals(resultTable[i][j], "Draw"))) {
                    for (int k = 1; k <= resultTable.length / 2; k++) {
                        resultTable[i][(j + k) % resultTable.length] = "Lose";
                    }
                    break;
                }
            }
        }
        return resultTable;
    }

    public static void fillWins(String[][] resultTable) {
        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable.length; j++) {
                if ((Objects.nonNull(resultTable[i][j])) && (resultTable[i][j].equals("Lose")) &&
                        (Objects.isNull(resultTable[i][(j + 1) % resultTable.length]))) {
                    for (int k = 1; k <= resultTable.length / 2; k++) {
                        resultTable[i][(j + k) % resultTable.length] = "Win ";
                    }
                    break;
                }
            }
        }
    }
}
