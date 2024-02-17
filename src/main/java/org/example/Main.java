package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> names = new ArrayList<>(Arrays.asList(
                "Аристотель", "Платон", "Сократ", "Диоген", "Пифагор"));
        System.out.println("Философы сидят по кругу в следующем порядке: " + names);

        //Создаем массив потоков-философов и массив вилок = кол-ву философов
        Philosoph[] phils = new Philosoph[names.size()];
        Object[] devices = new Object[phils.length];
        for (int i = 0; i < devices.length; i++) {
            devices[i] = new Object();
        }

        for (int i = 0; i < phils.length; i++) {
            Object leftDev = devices[i];
            Object rightDev = devices[(i + 1) % devices.length];
            //Все потоки-философы кроме последнего пытаются взять и заблокировать сначала левую вилку,
            // потом правую, а последний философ - наоборот, сначала правую, потом левую вилку
            //Это позволит избежать дедлока, когда все взяли левую вилку и ждут правую
            if (i == (phils.length - 1)) {
                phils[i] = new Philosoph(names.remove(0), rightDev, leftDev, 0);
            } else {
                phils[i] = new Philosoph(names.remove(0), leftDev, rightDev, 0);
            }
            new Thread(phils[i]).start();
        }
    }
}