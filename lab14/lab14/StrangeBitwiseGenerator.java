package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;
    private int weirdState;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        if (state < period) {
            state++;
            weirdState = state & (state >> 7) % period;
            return normalize(weirdState);
        } else {
            state = 1;
            weirdState = state & (state >> 7) % period;
            return normalize(weirdState);
        }
    }

    private double normalize(int value) {
        double half = (period - 1) / 2.0;
        return (value - half) / half;
    }
}
