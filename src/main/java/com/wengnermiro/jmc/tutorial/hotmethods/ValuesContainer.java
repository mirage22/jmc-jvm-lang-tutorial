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
public class ValuesContainer {

    private int maxElements;
    private Collection<Integer> collection;

    public ValuesContainer(int maxElements, Collection<Integer> collection) {
        this.maxElements = maxElements;
        this.collection = collection;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public void setMaxElements(int maxElements) {
        this.maxElements = maxElements;
    }

    public void setCollection(Collection<Integer> collection) {
        this.collection = collection;
    }

    public void init(int modulo) {
        collection.clear();
        for (int i = 0; i < maxElements; i++) {
            if (i % modulo != 0) collection.add(i);
        }
    }

    public Collection<Integer> getCollection() {
        return collection;
    }

    public int countIntersections(ValuesContainer c) {
        int count = 0;
        for (int n : collection) {
            if (c.getCollection().contains(n)) {
                count++;
            }
        }
        return count;
    }
}
