package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGame {
    private static final int LOTTO_PRICE = 1_000;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());
    public static final int LOTTO_NUMBERS_LENGTH = 6;
    private final List<Lotto> lottos;
    private int manualLottoCount = 0;
    private int autoLottoCount = 0;

    public LottoGame(int budget, List<List<Integer>> manualLottos, NumberGenerator numberGenerator) {
        validateLottoGame(budget, manualLottos);
        int numToBuy = (budget / LOTTO_PRICE) - manualLottos.size();
        lottos = getLottos(manualLottos, numberGenerator, numToBuy);
        manualLottoCount = manualLottos.size();
        autoLottoCount = numToBuy;
    }

    private void validateLottoGame(int budget, List<List<Integer>> manualLottos) {
        isInvalidBudget(budget);
        isNotEnoughBudget(budget, manualLottos.size());
    }

    private void isInvalidBudget(int budget) {
        if (budget < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 금액은 1,000원 이상이어야 합니다.");
        }
    }

    private void isNotEnoughBudget(int budget, int manualLottoCount) {
        if (budget < manualLottoCount * LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 금액이 부족합니다.");
        }
    }

    private List<Lotto> getLottos(List<List<Integer>> manualLottos, NumberGenerator numberGenerator, int numToBuy) {
        List<Lotto> manualLotto = generateManualLotto(manualLottos);
        List<Lotto> autoLotto = generateAutoLotto(IntStream
                .range(0, numToBuy)
                .mapToObj(i -> numberGenerator.generateNumbers(CANDIDATE_NUMBERS, LOTTO_NUMBERS_LENGTH)));
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLotto);
        lottos.addAll(autoLotto);
        return lottos;
    }

    private List<Lotto> generateAutoLotto(Stream<List<Integer>> numToBuy) {
        return numToBuy
                .map(this::generateLotto)
                .collect(Collectors.toList());
    }

    private List<Lotto> generateManualLotto(List<List<Integer>> manualLottos) {
        return manualLottos.stream()
                .map(this::generateLotto)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public GameResult getGameResult(WinningLotto winningLotto) {
        List<LottoResult> results = matchResult(winningLotto);
        double profitRate = calculateProfitRate(results);
        return new GameResult(results, profitRate);
    }

    private List<LottoResult> matchResult(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::matchNumber)
                .collect(Collectors.toList());
    }

    private double calculateProfitRate(List<LottoResult> results) {
        int totalPrize = results.stream().mapToInt(LottoResult::getPrize).sum();
        int numBuy = results.size();
        int totalPay = numBuy * LOTTO_PRICE;
        return (double) totalPrize / totalPay;
    }

    private Lotto generateLotto(List<Integer> lottoNumber) {
        return new Lotto(lottoNumber.stream().map(LottoNumber::of).collect(Collectors.toList()));
    }

    public WinningLotto generateWinningLotto(List<Integer> winningLottoNumber, int bonusNumber) {
        Lotto winningLotto = generateLotto(winningLottoNumber);
        return new WinningLotto(winningLotto, LottoNumber.of(bonusNumber));
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
