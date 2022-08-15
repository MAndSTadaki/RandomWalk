package model;

import event.SimulationEvent;
import event.SimulationEvent.EventType;
import event.SimulationEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Eventを発生するように拡張
 *
 * スレッドで実行可能にするためにRunnableを実装
 *
 * @author tadaki
 */
public class SimulationExt extends Simulation implements Runnable {

    private final int tmax = 10000;//最大繰り返し回数
    private volatile boolean running = false;
    private volatile int t;
    //一回の表示の間に何回の情報更新を行うか。
    private int updateTiming = 10;

    //イベント発生時に送信する相手
    private final List<SimulationEventListener> listeners;

    /**
     * コンストラクタ
     * @param n walkerの数
     * @param random
     */
    public SimulationExt(int n, Random random) {
        super(n, random);
        listeners = Collections.synchronizedList(new ArrayList<>());
    }

    public void addSimulationEventListener(SimulationEventListener o) {
        listeners.add(o);
    }

    public void removeSimulationEventListener(SimulationEventListener o) {
        listeners.remove(o);
    }

    public void clearSimulationEventListener() {
        listeners.clear();
    }

    /**
     * イベント発生をリスナーに通知する
     *
     * @param eventType
     */
    protected void fireStateChanged(EventType eventType) {
        SimulationEvent e = new SimulationEvent(this, eventType);
        listeners.stream().forEach(p -> p.stateChanged(e));
    }

    @Override
    /**
     * シミュレーションの一ステップに対して、更新イベントを発生
     */
    public List<Integer> oneStep() {
        List<Integer> list = super.oneStep();
        fireStateChanged(EventType.UPDATED);
        return list;
    }

    @Override
    /**
     * シミュレーションの初期化に対して、初期化イベントを発生
     */
    public void initialize() {
        super.initialize();
        fireStateChanged(EventType.INITIALIZED);
    }

    /**
     * n 回の状態更新に対して、一回の更新イベントを発生
     *
     * @param n
     * @return
     */
    public List<Integer> update(int n) {
        List<Integer> list = null;
        for (int i = 0; i < n; i++) {
            list = super.oneStep();
        }
        fireStateChanged(EventType.UPDATED);
        return list;
    }

    /**
     * threadの開始
     */
    public void start() {
        running = true;
        t = 0;
    }

    /**
     * threadの終了
     */
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            update(updateTiming);
            t++;
            if (t > tmax) {
                running = false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void setUpdateTiming(int updateTiming) {
        this.updateTiming = updateTiming;
    }

}
