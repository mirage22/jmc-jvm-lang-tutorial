/*
 *
 *  * Copyright (c) 2021, Miroslav Wengner
 *  *
 *  * jmc-jvm-lang-tutorial is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * jmc-jvm-lang-tutorial is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with jmc-tutorial. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.wengnermiro.jmc.tutorial.gc;

import java.util.Collection;
import java.util.Map;

import static com.wengnermiro.jmc.tutorial.gc.AllocationWorkerUtil.createMap;
import static com.wengnermiro.jmc.tutorial.gc.AllocationWorkerUtil.evaluate;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class AllocationWorker implements Runnable {
    private final Map<Integer, Sample> map;
    private final int id;

    public AllocationWorker(int id, int size) {
        this.id = id;
        this.map = createMap(size);
    }

    @Override
    public void run() {
        long counter = 0;
        while (true) {
            AllocationWorkerEvent event = new AllocationWorkerEvent(id);
            event.begin();
            Collection<Sample> set = map.values();
            for (Sample e : set) {
                evaluate(map, e.id());

                if (++counter % 1000 == 0) {
                    Thread.yield();
                }
            }
            event.commit();
        }
    }


}
