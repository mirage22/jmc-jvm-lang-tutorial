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

import com.wengnermiro.jmc.tutorial.latency.LatencyLoggerEvent
import com.wengnermiro.jmc.tutorial.utils.ProblematicUtil
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.ExperimentalTime

/**
 *
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
class LatencyLoggerMutex private constructor() : ProblematicKotlinLogger {
    companion object {
        val INSTANCE = LatencyLoggerMutex()
        var counter = 0
        val mutex = Mutex()
        val process = ProblematicUtil()
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun log(message: String) {
        coroutineScope {
            mutex.withLock {
                val event = LatencyLoggerEvent(message + counter)
                process.latencyLoggerProcess()
                event.commit()
            }
        }
    }
}
