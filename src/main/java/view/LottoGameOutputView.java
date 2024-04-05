package view;

import lotto.GameResult;
import lotto.Lotto;
import lotto.LottoResult;

import java.util.Arrays;
import java.util.Comparator;

public class LottoGameOutputView {
    private static final String DISPLAY_NUMBER_OF_LOTTOS_MSG_FORMAT = "%d개를 구매했습니다.\n";
    private static final String DISPLAY_RESULT_HEADER_MSG = "당첨 통계\n---------";
    private static final String DISPLAY_RESULT_LOTTO_MSG_FORMAT = "%s (%d원) - %d개";
    private static final String DISPLAY_RESULT_PROFIT_RATE_MSG = "총 수익률은 %,.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";

    public static void displayNumberOfLottos(int numOfLottos) {
        System.out.printf(DISPLAY_NUMBER_OF_LOTTOS_MSG_FORMAT, numOfLottos);
    }

    public static void displayLotto(Lotto lottoNums) {
        System.out.println(lottoNums.getLottoNumbers());
    }

    public static void displayResultHeader() {
        System.out.println(DISPLAY_RESULT_HEADER_MSG);
    }

    public static void displayResultLotto(GameResult gameResult) {
        Arrays.stream(LottoResult.values())
                .sorted(Comparator.comparingInt(LottoResult::getPrize))
                .filter(LottoResult::isWinning)
                .forEach(lottoResult -> System.out.println(makeLottoResult(lottoResult, gameResult.getResultCount(lottoResult))));
    }

    private static String makeLottoResult(LottoResult lottoResult, long count) {
        return String.format(DISPLAY_RESULT_LOTTO_MSG_FORMAT, makeLottoResultName(lottoResult), lottoResult.getPrize(), count);
    }

    private static String makeLottoResultName(LottoResult lottoResult) {
        String resultName = String.format("%d개 일치", lottoResult.getMatchCount());
        if (lottoResult == LottoResult.SECOND_PRIZE) {
            resultName += ", 보너스 볼 일치";
        }
        return resultName;
    }

    public static void displayResultProfitRate(GameResult gameResult) {
        System.out.printf(DISPLAY_RESULT_PROFIT_RATE_MSG, gameResult.getProfitRate());
    }

}
