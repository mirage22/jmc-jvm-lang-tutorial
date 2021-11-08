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
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 *
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
sealed class CounterEvent
object IncEvent : CounterEvent()
class GetCounterEvent(val result: CompletableDeferred<Int>) : CounterEvent()

@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.counterActor() = actor<CounterEvent> {
    val counter = AtomicInteger()
    for (event in channel) {
        when (event) {
            is IncEvent -> counter.incrementAndGet()
            is GetCounterEvent -> event.result.complete(counter.get())
        }
    }
}

class LatencyLoggerActor private constructor() : ProblematicKotlinLogger {
    companion object {
        val INSTANCE = LatencyLoggerActor()
    }


    @OptIn(ExperimentalTime::class)
    override suspend fun log(message: String) {

        coroutineScope {
            val counter = counterActor()
            val event = LatencyLoggerEvent("")

            println("log-counter-actor-start")
            counter.send(IncEvent)
            try {
                delay(Duration.milliseconds(DELAY_LATENCY))
            } catch (e: InterruptedException) {
                println(e.toString())
            }
            val response = CompletableDeferred<Int>()
            counter.send(GetCounterEvent(response))
            println("log-counter-actor-response")
            val counterVal = response.await()
            val eventText = "$message, counter=$counterVal"
            println("log-counter-actor-response-text:$eventText")
            event.message = message;
            event.commit()
            Thread.yield()
        }

    }
}
