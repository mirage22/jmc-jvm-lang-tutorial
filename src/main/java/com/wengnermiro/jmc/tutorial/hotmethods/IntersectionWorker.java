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
public class IntersectionWorker implements Runnable {

    private int id;
    private int elementsNumber;
    private Collection<Integer> c1;
    private Collection<Integer> c2;

    public IntersectionWorker(int id, int elementsNumber, Collection<Integer> c1, Collection<Integer> c2) {
        this.id = id;
        this.elementsNumber = elementsNumber;
        this.c1 = c1;
        this.c2 = c2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElementsNumber() {
        return elementsNumber;
    }

    public void setElementsNumber(int elementsNumber) {
        this.elementsNumber = elementsNumber;
    }

    public Collection<Integer> getC1() {
        return c1;
    }

    public void setC1(Collection<Integer> c1) {
        this.c1 = c1;
    }

    public Collection<Integer> getC2() {
        return c2;
    }

    public void setC2(Collection<Integer> c2) {
        this.c2 = c2;
    }

    @Override
    public void run() {
        while (true) {
            IntersectionWorkerEvent event = new IntersectionWorkerEvent(id);
            event.begin();
            ValuesContainer vc1 = new ValuesContainer(elementsNumber, c1);
            ValuesContainer vc2 = new ValuesContainer(elementsNumber, c2);

            vc1.init(5);
            vc2.init(7);

            int countIntersections = vc1.countIntersections(vc2);
            event.setIntersectionSize(countIntersections);
            event.commit();
            Thread.yield();
        }
    }
}
