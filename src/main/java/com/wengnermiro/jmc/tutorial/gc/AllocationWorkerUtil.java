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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
final public class AllocationWorkerUtil {
    public static void evaluate(Map<Integer, Sample> map, Integer id) {
        if (!map.containsKey(id)) {
            System.out.printf("""
                    WARNING, AllocationWorker-%s, number i:%s not present!
                    """, id, id);

        }
    }

    public static Map<Integer, Sample> createMap(int size) {
        Map<Integer, Sample> result = new HashMap<>();
        for (int i = 0; i < size; i++) {
            result.put(i, new Sample(i));
        }
        return result;
    }
}
