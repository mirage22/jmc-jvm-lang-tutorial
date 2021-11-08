/*
 * Copyright (c) 2021, Miroslav Wengner
 *
 * jmc-jvm-lang-tutorial is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  jmc-jvm-lang-tutorial is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *  along with jmc-tutorial. If not, see <http://www.gnu.org/licenses/>.
 */

package com.wengnermiro.jmc.kotlin.util

import com.wengnermiro.jmc.tutorial.latency.LatencyExampleMain
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class ProblematicUtils {
    @OptIn(ExperimentalTime::class)
    suspend fun latencyLoggerProcess() = coroutineScope{
        try {
            delay(Duration.milliseconds(LatencyExampleMain.DELAY_LATENCY))
        } catch (e: InterruptedException) {
            println(e.toString())
        }
    }
}

