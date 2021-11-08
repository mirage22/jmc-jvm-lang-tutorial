package com.wengnermiro.jmc.tutorial.gc;

import java.util.Objects;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class Sample {
    final int id;

    public Sample(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return id == sample.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
