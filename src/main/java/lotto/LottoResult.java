package lotto;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoResult {
    FIRST_PRIZE(6, 2_000_000_000, (count, bonus) -> count == 6),
    SECOND_PRIZE(5, 30_000_000, (count, bonus) -> count == 5 && bonus),
    THIRD_PRIZE(5, 1_500_000, (count, bonus) -> count == 5 && !bonus),
    FORTH_PRIZE(4, 50_000, (count, bonus) -> count == 4),
    FIFTH_PRIZE(3, 5_000, (count, bonus) -> count == 3),
    LOOSE(-1, 0, (count, bonus) -> false);

    private final int matchCount;
    private final int prize;
    private final BiPredicate<Integer, Boolean> matchPredicate;

    LottoResult(int matchCount, int prize, BiPredicate<Integer, Boolean> matchPredicate) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.matchPredicate = matchPredicate;
    }

    public static LottoResult getResult(int winCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.matchPredicate.test(winCount, hasBonus))
                .findFirst()
                .orElse(LottoResult.LOOSE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isWinning() {
        return this != LOOSE;
    }
}
