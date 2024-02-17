package org.example;

public class Philosoph implements Runnable {
    //У каждого потока-философа есть имя, прибор слева и справа, и счетчик числа раз когда он поел
    private final String name;
    private final Object leftDev;
    private final Object rightDev;
    private int eatTimes = 0;

    public Philosoph(String name, Object leftDev, Object rightDev, int eatTimes) {
        this.name = name;
        this.leftDev = leftDev;
        this.rightDev = rightDev;
        this.eatTimes = eatTimes;
    }

    @Override
    public void run() {
        while (eatTimes < 3) {
            //Если приборы заняты, то философ просто размышляет
            System.out.println(this.name + " размышляет");

            //Если есть возможность, берёт левую вилку и блокирует её
            synchronized (leftDev) {
                System.out.println(this.name + " взял левую вилку");

                //Если есть возможность, берёт правую вилку и блокирует её
                synchronized (rightDev) {
                    System.out.println(this.name + " взял правую вилку и начал есть");
                    eatTimes++;
                    System.out.println(this.name + " положил правую вилку");
                }
                //Разблокирована правая вилка
                if (eatTimes < 3) {
                    System.out.println(this.name + " положил левую вилку. Поел и снова размышляет");
                } else {
                    System.out.println(this.name + " положил левую вилку. Поел 3 раза и наелся");
                }
            }
            //Разблокирована левая вилка
        }
    }
}
