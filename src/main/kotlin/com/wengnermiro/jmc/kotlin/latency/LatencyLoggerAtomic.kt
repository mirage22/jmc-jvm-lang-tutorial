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

import com.wengnermiro.jmc.tutorial.latency.LatencyExampleMain.DELAY_LATENCY
import com.wengnermiro.jmc.tutorial.latency.LatencyLoggerEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 *
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
class LatencyLoggerAtomic private constructor() : ProblematicKotlinLogger {
    companion object {
        val INSTANCE = LatencyLoggerAtomic()
        @Volatile
        private var counter = 0
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun log(message: String) {
        coroutineScope {
            val event = LatencyLoggerEvent(message + counter++)
            try {
                delay(Duration.milliseconds(DELAY_LATENCY))
            } catch (e: InterruptedException) {
                println(e.toString())
            }
            event.commit()
        }
    }
}
