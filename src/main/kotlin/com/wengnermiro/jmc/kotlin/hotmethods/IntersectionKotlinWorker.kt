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

package com.wengnermiro.jmc.kotlin.hotmethods

import com.wengnermiro.jmc.tutorial.hotmethods.IntersectionWorkerEvent
import com.wengnermiro.jmc.tutorial.hotmethods.ValuesContainer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
class IntersectionKotlinWorker(
    private val id: Int,
    private val number: Int,
    private val c1: Collection<Int>,
    private val c2: Collection<Int>
) {

    suspend fun run() = coroutineScope {
        launch {
            while (true) {
                val event = IntersectionWorkerEvent(id)
                event.begin()
                val vc1 = ValuesContainer(number, c1)
                val vc2 = ValuesContainer(number, c2)

                vc1.init(5)
                vc2.init(7)

                event.intersectionSize = vc1.countIntersections(vc2)
                event.commit()
            }
        }

    }
}
