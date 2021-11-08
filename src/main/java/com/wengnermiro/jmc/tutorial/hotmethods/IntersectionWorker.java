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

package com.wengnermiro.jmc.tutorial.hotmethods;

import java.util.Collection;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public record IntersectionWorker(int id, int number, Collection<Integer> c1,
                                 Collection<Integer> c2) implements Runnable {

    @Override
    public void run() {
        while (true) {
            IntersectionWorkerEvent event = new IntersectionWorkerEvent(id);
            event.begin();
            ValuesContainer vc1 = new ValuesContainer(number, c1);
            ValuesContainer vc2 = new ValuesContainer(number, c2);

            vc1.init(5);
            vc2.init(7);

            int countIntersections = vc1.countIntersections(vc2);
            event.setIntersectionSize(countIntersections);
            event.commit();
            Thread.yield();
        }
    }
}
