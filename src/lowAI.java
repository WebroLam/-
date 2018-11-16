public class lowAI {
    Hero hero[];

    public lowAI(Hero hero[]) {
        this.hero = hero;
    }

    public void start() {
        for (int i = 5; i < 10; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    System.out.println(finalI +"线程成功启动");
                    while (hero[finalI].hp > 0)
                        autoAttck(hero[finalI]);
                }
            }.start();
        }
    }

    private void autoAttck(Hero hero) {
        int def = -1;
        for (int i = -2; i < 3; i++) {
            if (hero.x + i < 0 || hero.x + i >= Map.size_m) continue;
            for (int j = -2; j < 3; j++) {
                if (hero.y + j < 0 || hero.y + j >= Map.size_n) continue;
                if (hero.map.maps[hero.x + i][hero.y + j] >= 'A' && hero.map.maps[hero.x + i][hero.y + j] <= 'L') {
                    for (int k = 0; k < Operator.HeroNum; k++) {
                        if (hero.map.maps[hero.x + i][hero.y + j] == hero.Hero[k].getname())
                            if (hero.camp != hero.Hero[k].camp) {//属于不同阵营
                                if (i < 0) hero.move('u', -i);
                                else hero.move('d', i);
                                if (j < 0) hero.move('l', -j);
                                else hero.move('r', j);
                                hero.attack(k + 1);
                                delay();

                            }
                    }
                }
            }
        }
    }

    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}