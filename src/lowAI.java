
public class lowAI {
    Hero hero[];//保存英雄数据

    /**
     * 构造函数，将电脑英雄数据传入
     * @param hero 电脑英雄数据
     */
    public lowAI(Hero hero[]) {
        this.hero = hero;
    }

    /**
     * 开始函数，为每个电脑英雄初始化线程
     */
    public void start() {
        for (int i = Operator.HeroNum / 2; i < Operator.HeroNum; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    System.out.println(finalI + "线程成功启动");
                    while (hero[finalI].hp > 0) {
                        switch ((int) ((Math.random() * 30) % 3)) {
                            case 0:
                                autoAttck(hero[finalI]);
                            case 1:
                                autoShot(hero[finalI]);
                            case 2:
                                stay();
                        }

                    }
                }
            }.start();
        }
    }

    /**
     * 自动攻击周围距两圈内的敌人
     *
     * @param hero 发起攻击的英雄
     */
    private void autoAttck(Hero hero) {
        for (int i = -2; i < 3; i++) {
            if (hero.x + i < 0 || hero.x + i >= Map.size_m) continue;//防止越界
            for (int j = -2; j < 3; j++) {
                if (hero.y + j < 0 || hero.y + j >= Map.size_n) continue;//防止越界
                if (hero.map.maps[hero.x + i][hero.y + j] >= 'A' && hero.map.maps[hero.x + i][hero.y + j] <= 'L') {
                    findHero(hero, i, j);
                }
            }
        }
    }

    /**
     * 查询英雄所属序号
     *
     * @param hero 英雄
     * @param i    位置x
     * @param j    位置y
     */
    private void findHero(Hero hero, int i, int j) {
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

    /**
     * 自动射击上下左右五格内的敌人
     *
     * @param hero
     */
    private void autoShot(Hero hero) {
        for (int i = 1; i <= 5; i++) {//五格，一格一格往外层遍历
            if (hero.x - i >= 0)//防止越界
                if (hero.map.maps[hero.x - i][hero.y] >= 'A' && hero.map.maps[hero.x - i][hero.y] <= 'L') {//上         下左右
                    hero.arrow('u');
                }

            if (hero.x + i < Map.size_m)//防止越界
                if (hero.map.maps[hero.x + i][hero.y] >= 'A' && hero.map.maps[hero.x + i][hero.y] <= 'L') {//下
                    hero.arrow('d');
                }

            if (hero.y - i >= 0)//防止越界
                if (hero.map.maps[hero.x][hero.y - i] >= 'A' && hero.map.maps[hero.x][hero.y - i] <= 'L') {//左
                    hero.arrow('l');
                }

            if (hero.y + i < Map.size_n)//防止越界
                if (hero.map.maps[hero.x][hero.y + i] >= 'A' && hero.map.maps[hero.x][hero.y + i] <= 'L') {//右
                    hero.arrow('r');
                }
        }
    }

    /**
     * 发呆三秒
     */
    private void stay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 延迟500ms
     */
    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}