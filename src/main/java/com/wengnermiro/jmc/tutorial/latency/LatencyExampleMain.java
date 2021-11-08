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

package com.wengnermiro.jmc.tutorial.latency;

import com.wengnermiro.jmc.tutorial.utils.ThreadContainer;
import com.wengnermiro.jmc.tutorial.utils.ThreadContainerBuilder;

import java.io.IOException;

/**
 * Latency example
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class LatencyExampleMain {

    private static final int THREADS_NUMBER = 20;

    public static void main(String[] args) throws IOException {
        System.out.println("Latency example...");

        ThreadContainerBuilder builder = new ThreadContainerBuilder()
                .addThreadGroup("Latency-Workers");
        for(int i=0; i < THREADS_NUMBER; i++){
            builder.addRunnable(new LatencyWorker(i, 30_000_000));
        }

        ThreadContainer container = builder.build();

        container.startAsDaemon();
        System.out.printf("""
                Latency started status:%s, threads:%s%n""", container.isStarted(), THREADS_NUMBER);
        System.out.println("Press any key to quit!");
        System.out.flush();
        System.in.read();
    }
}
