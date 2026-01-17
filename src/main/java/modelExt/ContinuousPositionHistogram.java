package modelExt;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Random Walkにおいて、各位置のWalkerの頻度を計測
 *
 * @author tadaki
 */
public class ContinuousPositionHistogram {

    /**
     * このクラスのインスタンスは生成できない
     */
    private ContinuousPositionHistogram() {
    }

    /**
     * 位置ヒストグラムの取得
     *
     * @param walkers 対象となるWalkerのリスト
     * @return 位置とその位置に居るWalkerの比率のリスト
     */
    public static List<Point2D.Double> getHist(List<ContinuousWalker> walkers) {
        return getHist(walkers,1.);
    }
    public static List<Point2D.Double> getHist(List<ContinuousWalker> walkers,double bin) {
        //変位の幅を計測
        double xmaxd = (int) Math.ceil(walkers.get(0).getX());
        double xmind = xmaxd;
        for (ContinuousWalker w : walkers) {
            xmaxd = Math.max(xmaxd, w.getX());
            xmind = Math.min(xmind, w.getX());
        }
        int xmax = (int) Math.ceil(xmaxd/bin)-1;
        int xmin = (int) Math.floor(xmind/bin);
        //ヒストグラムの生成
        int h[] = new int[xmax - xmin + 1];
        for(ContinuousWalker w:walkers){
            int k = (int)((w.getX()-xmind)/bin);
            h[k]++;
        }
        //ヒストグラムをリストに変換
        //ヒストグラムから相対頻度に変換
        List<Point2D.Double> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < h.length; i++) {
            double x = (i + xmin + 0.5)*bin;
            double y = (double) h[i] / walkers.size();
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

}
