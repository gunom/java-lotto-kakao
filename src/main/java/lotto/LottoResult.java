package lotto;

import java.util.Arrays;

public enum LottoResult {
    SIX_MATCH(6, false),
    FIVE_AND_BONUS_MATCH(5, true),
    FIVE_MATCH(5, false),
    FOUR_MATCH(4, false),
    THREE_MATCH(3, false),
    BLANK_MATCH(-1, false);

    private final int matchCount;
    private final boolean bonus;

    LottoResult(int matchCount, boolean bonus) {
        this.matchCount = matchCount;
        this.bonus = bonus;
    }

    private boolean match(int winCount, boolean hasBonus) {
        return this.matchCount == winCount && this.bonus == hasBonus;
    }

    public static LottoResult getResult(int winCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values()).
                filter(lottoResult -> lottoResult.match(winCount, hasBonus)).
                findFirst().
                orElse(LottoResult.BLANK_MATCH);
    }
}
