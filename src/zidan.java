/**
 * 子弹类，用于发射箭矢
 *
 * @author 78580 .
 */
class zidan extends Thread {
    private int m = 12;
    private int n = 12;
    private int x;
    private int y;
    private Hero hostHero;
    private char fangXiang;


    zidan(int xx, int yy, Hero hostHero, char fangXiang) {
        x = xx;
        y = yy;
        this.hostHero = hostHero;
        this.fangXiang = fangXiang;
    }

    @Override
    public void run() {
        arrow();
    }

    public void arrow() {
        int DefendHero = fire(hostHero.map.maps);
        if (DefendHero != -1) {
            if (hostHero.Hero[DefendHero].camp != hostHero.camp) {
                int DefendHeroHP = hostHero.Hero[DefendHero].defend(hostHero.attack());
                if (DefendHeroHP <= 0) {
                    //System.out.println(hostHero.name + "死亡时间" + (System.currentTimeMillis()) + "ms");

                    //  map.print();
                    System.out.println("英雄" + hostHero.Hero[DefendHero].getname() + "被" + hostHero.getname() + "击杀");

                } else {
                    System.out.println("英雄" + hostHero.Hero[DefendHero].getname() + "剩余hp：" + DefendHeroHP);
                }
            }
        }
    }

    /**
     * @param maps 地图数据传入
     * @return 返回被攻击的英雄序号（从0开始）;-1代表未击中
     */
    public int fire(char[][] maps) {
        switch (fangXiang) {
            case 'u':
            case 'U':
                for (int i = x - 1; i >= 0; i--) {
                    if (maps[i][y] == '*') {
                        break;
                    } else if (maps[i][y] == '.') {
                        maps[i][y] = '↑';
                        delay();
                        maps[i][y] = '.';
                    } else {
                        for (int j = 0; j < Operator.HeroNum; j++) {
                            if (maps[i][y] == hostHero.Hero[j].getname()) {
                                delay();
                                return j;
                            }
                        }
                    }
                }
                break;

            case 'd':
            case 'D':
                for (int i = x + 1; i < m; i++) {
                    if (maps[i][y] == '*') {
                        break;
                    } else if (maps[i][y] == '.') {
                        maps[i][y] = 'I';
                        delay();
                        maps[i][y] = '.';
                    } else {
                        for (int j = 0; j < Operator.HeroNum; j++) {
                            if (maps[i][y] == hostHero.Hero[j].getname()) {
                                delay();
                                return j;
                            }
                        }
                    }
                }
                break;
            case 'l':
            case 'L':
                for (int i = y - 1; i >= 0; i--) {
                    if (maps[x][i] == '*') {
                        break;
                    } else if (maps[x][i] == '.') {
                        maps[x][i] = '←';
                        delay();
                        maps[x][i] = '.';
                    } else {
                        for (int j = 0; j < Operator.HeroNum; j++) {
                            if (maps[x][i] == hostHero.Hero[j].getname()) {
                                delay();
                                return j;
                            }
                        }
                    }
                }
                break;
            case 'r':
            case 'R':
                for (int i = y + 1; i < n; i++) {
                    if (maps[x][i] == '*') {
                        break;
                    } else if (maps[x][i] == '.') {
                        maps[x][i] = '→';
                        delay();
                        maps[x][i] = '.';
                    } else {
                        for (int j = 0; j < Operator.HeroNum; j++) {
                            if (maps[x][i] == hostHero.Hero[j].getname()) {
                                delay();
                                return j;
                            }
                        }
                    }
                }
                break;
        }
        return -1;
    }

    private void delay() {
        /* TODO Auto-generated method stub */

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}