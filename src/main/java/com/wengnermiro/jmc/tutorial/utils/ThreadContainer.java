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

package com.wengnermiro.jmc.tutorial.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class ThreadContainer {

    private final List<Thread> list = new ArrayList<>();
    private boolean started;

    void addRunnable(ThreadGroup g, Runnable r, int number){
        list.add(new Thread(g, r, g.getName() + "-" + number));
    }

    public void startAsDaemon(){
        if(!started){
            System.out.println("Container started!");
            for(Thread t: list){
                t.setDaemon(true);
                t.start();
            }
            started = true;
        } else {
            System.out.println("Container started");
        }
    }

    public boolean isStarted() {
        return started;
    }
}
