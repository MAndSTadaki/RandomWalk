package modelExt;

import randomNumbers.AbstractRandom;

/**
 * 連続的な位置へランダムに移動するWalker
 * @author tadaki
 */
public class ContinuousWalker {
    protected final AbstractRandom abstractRandom;//乱数生成器
    protected double x;

    /**
     * 乱数生成器と初期位置を与えて初期化
     * 
     * @param abstractRandom
     * @param x 
     */
    public ContinuousWalker(AbstractRandom abstractRandom, double x) {
        this.abstractRandom = abstractRandom;
        this.x = x;
    }
    
    public double walk(){
        x += abstractRandom.getNext();
        return x;
    }

    public double getX() {
        return x;
    }
    
    
}
