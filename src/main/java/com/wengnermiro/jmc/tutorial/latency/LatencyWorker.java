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

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public record LatencyWorker(int id, int loopCounter) implements Runnable {

    private static final LatencyLogger LOGGER = LatencyLogger.getLogger();

    @Override
    public void run() {
        while (true){
            LatencyWorkerEvent event = new LatencyWorkerEvent(id);
            event.begin();
            int x = 1;
            int y = 1;
            for (int i = 1; i < loopCounter; i++) {
                x += 1;
                y = y % (this.loopCounter + 3);
                if (x % (this.loopCounter + 4) == 0 || y == 0) {
                    System.out.printf("""
                            LatencyWorker-%s, Should not happen%n""", id);
                }
            }
            event.commit();
            LOGGER.log(String.format("""
                    LatencyWorker-%s work done
                    """, id));
            Thread.yield();
        }
    }
}
