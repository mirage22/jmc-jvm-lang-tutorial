package com.wengnermiro.jmc.tutorial.hotmethods;

import com.wengnermiro.jmc.tutorial.utils.ThreadContainer;
import com.wengnermiro.jmc.tutorial.utils.ThreadContainerBuilder;

import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class HotMethodsExampleMain {

    public static final int THREADS_NUMBER = 4;
    public static final int ELEMENTS_NUMBER = 1000;

    public static void main(String[] args) throws IOException {
        System.out.println("HotMethods Example...");

        ThreadContainerBuilder builder = new ThreadContainerBuilder()
                .addThreadGroup("Hot-Method-Workers");
        for (int i = 0; i < THREADS_NUMBER; i++) {
            builder.addRunnable(new IntersectionWorker(i, ELEMENTS_NUMBER, new LinkedList<>(), new LinkedList<>()));
        }
        ThreadContainer container = builder.build();

        container.startAsDaemon();
        System.out.println("ThreadContainer started:" + container.isStarted() + ", threads:" + THREADS_NUMBER);
        System.out.println("Press any key to quit!");
        System.out.flush();
        System.in.read();
    }
}
