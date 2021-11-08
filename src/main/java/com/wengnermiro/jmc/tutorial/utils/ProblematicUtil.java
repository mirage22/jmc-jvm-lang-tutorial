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

package com.wengnermiro.jmc.tutorial.utils;

import java.util.concurrent.TimeUnit;

import static com.wengnermiro.jmc.tutorial.latency.LatencyExampleMain.DELAY_LATENCY;

/**
 * ProblematicUtils some problematics utils
 */
public class ProblematicUtil {

    public void latencyLoggerProcess() {
        try {
            TimeUnit.MILLISECONDS.sleep(DELAY_LATENCY);
        } catch (InterruptedException e) {
            // Don't care
            System.err.println(e.getMessage());
        }
    }

}
