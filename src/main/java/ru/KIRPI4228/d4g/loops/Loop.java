package ru.KIRPI4228.d4g.loops;

import java.util.List;

public class Loop implements LoopRunnable {
    private static int count = 0;

    private int start;
    private int end;
    private int includes;
    private LoopRunnable runnable;

    public Loop(int start, int end, int includes, LoopRunnable runnable) {
        this.start = start;
        this.end = end;
        this.includes = includes;
        this.runnable = runnable;
    }

    @Override
    public void run(List<Integer> indexes) {
        count++;

        int ownCount = count;

        for (int i = start; i < end; i++) {
            setIndex(indexes, ownCount, i);
            if (count >= includes) {
                runnable.run(indexes);
            } else {
                new Loop(start, end, includes, runnable).run(indexes);
            }
        }
    }

    private void setIndex(List<Integer> indexes, int ownCount, int index) {
        if (indexes.size() <= ownCount) {
            indexes.add(index);
        } else {
            indexes.set(ownCount, index);
        }
    }
}
