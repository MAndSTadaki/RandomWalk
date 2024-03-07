package modelExt;

import java.io.IOException;
import java.util.function.Function;
import randomNumbers.AbstractRandom;
import randomNumbers.Transform;

/**
 * 指数関数分布の場合の酔歩
 *
 * @author tadaki
 */
public class ExponentialCase {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //指数分布に対応した分布関数の逆関数を定義
        // A * exp (-x)
        double A = Math.E / (Math.E - 1);
        Function<Double, Double> invProDist = (x) -> {
            return -Math.log(1 - x / A);
        };
        //変換法による乱数生成のインスタンス
        AbstractRandom aRandom = new Transform(invProDist, 48L);
        int n = 100000;
        int tmax = 1000;
        SimulationContinuous sys = new SimulationContinuous(aRandom, n);
        String filename = ExponentialCase.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        sys.generateHistogram(tmax, filename);
    }
}
