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

package com.wengnermiro.jmc.kotlin.latency

import com.wengnermiro.jmc.tutorial.latency.LatencyWorkerEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * LatencyKotlinWorker
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
class LatencyKotlinWorker(private val id: Int, var loopCounter: Int, private val logger: ProblematicKotlinLogger) {

    suspend fun run() = coroutineScope {
        launch {
            while (true) {
                val event = LatencyWorkerEvent(id)
                event.begin()
                var x = 1
                var y = 1
                for (i in 1..loopCounter) {
                    x += 1;
                    y = y % (loopCounter + 3)
                    if (x % (loopCounter + 4) == 0 || y == 0) {
                        println(
                            "LatencyKotlinWorker-$id, should " +
                                    "not happen, loopCounter:$loopCounter"
                        )
                    }
                }

                logger.log("LatencyKotlinWorker-$id, work done, counter=$loopCounter")
                event.commit()
                Thread.yield()
            }
        }
    }
}
