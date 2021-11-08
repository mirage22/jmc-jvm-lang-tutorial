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


import java.util.concurrent.TimeUnit;

/**
 * Latency logger with issues
 *
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class LatencyLogger {

    private static final LatencyLogger INSTANCE = new LatencyLogger();

    public static LatencyLogger getLogger() {
        return INSTANCE;
    }

    public synchronized void log(String message) {
        LatencyLoggerEvent event = new LatencyLoggerEvent(message);
        event.begin();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // Don't care
            System.err.println(e.getMessage());
        }
        event.commit();
    }
}
