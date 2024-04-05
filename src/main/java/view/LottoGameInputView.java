package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGameInputView {
    private static final String INPUT_BUDGET_MSG = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS_MSG = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_BONUS_NUMBERS_MSG = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MSG = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MSG = "수동으로 구매할 번호를 입력해 주세요.";

    public static int getBudget() {
        return retryableInput(LottoGameInputView::inputBudget, LottoGameInputView::isValidBudget);
    }

    private static <T> T retryableInput(Supplier<T> supplier, Predicate<T> validator) {
        T input = supplier.get();
        while (!validator.test(input)) {
            input = supplier.get();
        }
        return input;
    }

    private static boolean isValidBudget(int budget) {
        return budget >= 1_000;
    }

    private static int inputBudget() {
        return inputInt(INPUT_BUDGET_MSG);
    }

    private static int inputInt(String inputMsg) {
        String input = inputString(inputMsg, true);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return inputInt(inputMsg);
        }
    }

    private static String inputString(String msg, boolean hasMessage) {
        if (hasMessage) {
            System.out.println(msg);
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<Integer> getWinningNumber() {
        return getWinningLottoNumbers();
    }

    private static List<Integer> getWinningLottoNumbers() {
        return retryableInput(LottoGameInputView::inputWinningLottoNumbers, LottoGameInputView::isValidLottoNumbers);
    }

    private static boolean isValidLottoNumbers(List<Integer> lottoNumbers) {
        boolean isSixLength = lottoNumbers.size() == 6;
        boolean outBoundedNumber = lottoNumbers.stream().anyMatch(number -> number < 1 || number > 45);
        boolean duplicatedNumber = lottoNumbers.size() != new HashSet<>(lottoNumbers).size();
        return isSixLength && !outBoundedNumber && !duplicatedNumber;
    }

    private static List<Integer> inputWinningLottoNumbers() {
        return inputIntList(INPUT_WINNING_LOTTO_NUMBERS_MSG, true);
    }

    private static List<Integer> inputIntList(String inputMsg, boolean hasMessage) {
        String input = inputString(inputMsg, hasMessage);
        try {
            return Arrays.stream(input.split(", "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return inputIntList(inputMsg, true);
        }
    }

    public static int getBonusNumber(List<Integer> winningLottoNumbers) {
        return getWinningBonusNumber(winningLottoNumbers);
    }

    private static int getWinningBonusNumber(List<Integer> winningLottoNumbers) {
        Function<List<Integer>, Predicate<Integer>> mixedValidate =
                lottoNumbers ->
                        bonusNumber -> validateWinningBonusNumber(bonusNumber, lottoNumbers);
        return retryableInput(LottoGameInputView::inputWinningBonusNumber, mixedValidate.apply(winningLottoNumbers));
    }

    private static boolean validateWinningBonusNumber(int winningBonusNumber, List<Integer> winningLottoNumber) {
        boolean outBoundedBonusNumber = winningBonusNumber < 1 || winningBonusNumber > 45;
        boolean duplicatedBonusNumber = winningLottoNumber.contains(winningBonusNumber);
        return !outBoundedBonusNumber && !duplicatedBonusNumber;
    }

    private static int inputWinningBonusNumber() {
        return inputInt(INPUT_WINNING_BONUS_NUMBERS_MSG);
    }

    public static List<List<Integer>> getManualLottoNumbers(int manualLottoCount) {
        return getManualLottoNumbersList(manualLottoCount);
    }

    private static List<List<Integer>> getManualLottoNumbersList(int manualLottoCount) {
        return retryableInput(() -> inputManualLottoNumbers(manualLottoCount),
                manualLottoNumbers -> isValidManualLottoNumbers(manualLottoNumbers, manualLottoCount));
    }

    private static boolean isValidManualLottoNumbers(List<List<Integer>> manualLottoNumbers, int manualLottoCount) {
        boolean isEnoughManualLottoCount = manualLottoNumbers.size() == manualLottoCount;
        boolean isAllValidLottoNumbers = manualLottoNumbers.stream().allMatch(LottoGameInputView::isValidLottoNumbers);
        return isEnoughManualLottoCount && isAllValidLottoNumbers;
    }

    private static List<List<Integer>> inputManualLottoNumbers(int manualLottoCount) {
        return inputManualLottoNumbersList(manualLottoCount);
    }

    private static List<List<Integer>> inputManualLottoNumbersList(int manualLottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MSG);
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> inputIntList(INPUT_MANUAL_LOTTO_NUMBERS_MSG, false))
                .collect(Collectors.toList());
    }

    public static int getManualLottoCount(int budget) {
        Function<Integer, Predicate<Integer>> mixedValidate =
                budgetValue -> manualLottoCount -> validateManualLottoCount(budgetValue, manualLottoCount);
        return retryableInput(LottoGameInputView::inputManualLottoCount, mixedValidate.apply(budget));
    }

    private static boolean validateManualLottoCount(int budget, int manualLottoCount) {
        boolean isPositive = manualLottoCount > 0;
        boolean isEnoughBudget = budget >= manualLottoCount * 1_000;
        return isPositive && isEnoughBudget;
    }

    private static int inputManualLottoCount() {
        return inputInt(INPUT_MANUAL_LOTTO_COUNT_MSG);
    }
}
