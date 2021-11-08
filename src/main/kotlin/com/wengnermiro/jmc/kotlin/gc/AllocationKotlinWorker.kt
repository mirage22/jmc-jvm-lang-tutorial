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

package com.wengnermiro.jmc.kotlin.gc

import com.wengnermiro.jmc.tutorial.gc.AllocationWorkerEvent
import com.wengnermiro.jmc.tutorial.gc.AllocationWorkerUtil
import com.wengnermiro.jmc.tutorial.gc.Sample
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
data class SampleKotlin(val id: Int)
class AllocationKotlinWorker(private val id: Int, private val size: Int) {

    private val map: Map<Int, Sample> = AllocationWorkerUtil.createMap(size)
    suspend fun run() = coroutineScope {
        launch {
            var counter = 0
            while (true) {
                val event = AllocationWorkerEvent(id);
                event.begin();
                val set = map.values
                for (e in set) {
                    if (!map.containsKey(e.id)) {
                        println("WARNING, AllocationWorker-$id, number i:${e.id} not present!")
                    }
//                    if(++counter % 1000 == 0){
//                        Thread.yield()
//                    }
                }
                event.commit()
            }
        }
    }

    private fun createMap(size: Int): Map<Number, SampleKotlin> {
        val m = mutableMapOf<Long, SampleKotlin>()
        for (i in 0..size) {
            m[i.toLong()] = SampleKotlin(i)
        }
        return m.toMap()
    }
}
