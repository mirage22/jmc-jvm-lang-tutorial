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

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

/**
 * Latency Example for Actor Problematic Logger
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
private const val CONTEXTS_NUMBER = 5
fun main(): Unit = runBlocking {
    println("Latency example started...")
    val workerJobs = mutableListOf<Job>()
    for (i in 1..CONTEXTS_NUMBER) {
        launch(Dispatchers.Default) {
            val job = LatencyKotlinWorker(i, 2, LatencyLoggerActor.INSTANCE).run()
            workerJobs.add(job)
        }
    }
    println("Press any key to quit!")
    System.out.flush()
    readLine()
    counterActor().close()
    exitProcess(0)
}
