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

import com.wengnermiro.jmc.tutorial.utils.ThreadContainer;
import com.wengnermiro.jmc.tutorial.utils.ThreadContainerBuilder;

import java.io.IOException;

/**
 * Memory allocation example
 *
 * hint: by changing a small piece of code. GC pressure is be 0
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class AllocationExampleMain {

    public static final int THREADS_NUMBER = 2;
    public static final int ALLOCATION_SIZE = 10_000;

    public static void main(String[] args) throws IOException {
        System.out.println("Memory allocation example...");

        ThreadContainerBuilder builder = new ThreadContainerBuilder()
                .addThreadGroup("Allocation-Workers");
        for (int i = 0; i < THREADS_NUMBER; i++) {
            builder.addRunnable(new AllocationWorker(i, ALLOCATION_SIZE));
        }

        ThreadContainer container = builder.build();

        container.startAsDaemon();
        System.out.println("Latency started status:" + container.isStarted() + ", threads:" + THREADS_NUMBER);
        System.out.println("Press any key to quit!");
        System.out.flush();
        System.in.read();
    }
}
