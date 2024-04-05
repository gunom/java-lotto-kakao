package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    private static final int LOTTO_PRICE = 1_000;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());
    public static final int LOTTO_NUMBERS_LENGTH = 6;
    private final List<Lotto> lottos;
    private static final LottoNumbers lottoNumbers = new LottoNumbers();

    public LottoGame(int budget, NumberGenerator numberGenerator) {
        int numToBuy = budget / LOTTO_PRICE;
        this.lottos = IntStream
                .range(0, numToBuy)
                .mapToObj(i -> numberGenerator.generateNumbers(CANDIDATE_NUMBERS, LOTTO_NUMBERS_LENGTH))
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

    private Lotto generateLotto(List<Integer> winningLottoNumber) {
        return new Lotto(lottoNumbers.getLottoNumbers(winningLottoNumber));
    }

    public WinningLotto generateWinningLotto(List<Integer> winningLottoNumber, int bonusNumber) {
        Lotto winningLotto = generateLotto(winningLottoNumber);
        return new WinningLotto(winningLotto, lottoNumbers.getLottoNumber(bonusNumber));
    }
}
