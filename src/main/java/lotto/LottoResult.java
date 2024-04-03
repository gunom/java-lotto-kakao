package lotto;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoResult {
    FIRST_PRIZE(6, false, 2_000_000_000),
    SECOND_PRIZE(5, true, 30_000_000),
    THIRD_PRIZE(5, false, 1_500_000),
    FORTH_PRIZE(4, false, 50_000),
    FIFTH_PRIZE(3, false, 5_000),
    LOOSE(-1, false, 0);

    private final int matchCount;
    private final boolean bonus;
    private final int prize;

    LottoResult(int matchCount, boolean bonus, int prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    private boolean matchWithCount(int winCount, boolean hasBonus) {
        if (this == SECOND_PRIZE) {
            return this.matchCount == winCount && this.bonus == hasBonus;
        }
        return this.matchCount == winCount;
    }

    public static LottoResult getResult(int winCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.matchWithCount(winCount, hasBonus))
                .findFirst()
                .orElse(LottoResult.LOOSE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isNotBlank() {
        return this != LOOSE;
    }

    public static Comparator<LottoResult> comparator() {
        return Comparator.comparing(LottoResult::getMatchCount).thenComparingInt(result -> result.bonus ? 0 : 1);
    }

}
