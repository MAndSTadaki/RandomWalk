package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Random Walkにおいて、各位置のWalkerの頻度を計測
 *
 * @author tadaki
 */
public class PositionHistogram {

    /**
     * このクラスのインスタンスは生成できない
     */
    private PositionHistogram() {
    }

    /**
     * 位置ヒストグラムの取得
     *
     * @param walkers 対象となるWalkerのリスト
     * @return 位置とその位置に居るWalkerの比率のリスト
     */
    public static List<Point2D.Double> getHist(List<Walker> walkers) {
        //変位の幅を計測
        int xmax = walkers.get(0).getX();
        int xmin = xmax;
        for (Walker w : walkers) {
            xmax = Math.max(xmax, w.getX());
            xmin = Math.min(xmin, w.getX());
        }
        //ヒストグラムの生成
        int h[] = new int[xmax - xmin + 1];
        for (Walker w : walkers) {
            int k = w.getX() - xmin;
            h[k]++;
        }
        //ヒストグラムをリストに変換
        //ヒストグラムから相対頻度に変換
        List<Point2D.Double> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < h.length; i += 2) {
            double x = i + xmin;
            //ヒストグラムの幅が2であることに注意
            double y = (double) h[i] / walkers.size() / 2.;
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

}
