package ru.KIRPI4228.d4g.loops;

import java.util.List;

public class Loop implements LoopRunnable {
    private static int count = 0;

    private int start;
    private int end;
    private int ownCount;
    private LoopRunnable runningRunnable;

    public Loop(int start, int end, int includes, LoopRunnable runnable) {
        count++;
        ownCount = count - 1;

        this.start = start;
        this.end = end;

        if (ownCount >= includes) {
            this.runningRunnable = runnable;
        } else {
            this.runningRunnable = new Loop(start, end, includes, runnable);
        }
    }

    @Override
    public void run(List<Integer> indexes) {
        for (int i = start; i < end; i++) {
            setIndex(indexes, i);
            runningRunnable.run(indexes);
        }
    }

    private void setIndex(List<Integer> indexes, int index) {
        if (indexes.size() <= ownCount) {
            indexes.add(index);
        } else {
            indexes.set(ownCount, index);
        }
    }
}
