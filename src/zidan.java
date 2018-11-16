/**
 * 子弹类，用于发射箭矢
 *
 * @author SZU
 */
class zidan {
    private int m = 12;
    private int n = 12;
    private int x;
    private int y;

    zidan(int xx, int yy) {
        x = xx;
        y = yy;
    }

    /**
     * @param hero      英雄数据传入
     * @param FangXiang 攻击方向
     * @param maps      地图数据传入
     * @return 返回被攻击的英雄序号（从0开始）;-1代表未击中
     */
    public int fire(Hero[] hero, char FangXiang, char[][] maps) {
        switch (FangXiang) {
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
                            if (maps[i][y] == hero[j].getname()) {
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
                            if (maps[i][y] == hero[j].getname()) {
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
                            if (maps[x][i] == hero[j].getname()) {
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
                            if (maps[x][i] == hero[j].getname()) {
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