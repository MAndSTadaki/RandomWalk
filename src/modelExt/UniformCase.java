package modelExt;

import java.io.IOException;
import randomNumberExamples.Uniform;
import randomNumbers.AbstractRandom;

/**
 * 連続的な一様乱数の場合の酔歩
 * @author tadaki
 */
public class UniformCase {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AbstractRandom aRandom = new Uniform(-0.5, 0.5, 48L);//一様乱数
        int n = 100000;//walker数
        int tmax = 1000;//時間ステップ
        SimulationContinuous sys = new SimulationContinuous(aRandom, n);
        String filename = UniformCase.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        sys.generateHistogram(tmax, filename);
        System.out.println(filename);
    }

}
