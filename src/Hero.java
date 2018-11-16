/**
 * 英雄基类
 * camp 标注阵营，分为true阵营和false阵营
 */
class Hero {
    protected boolean camp;
    protected char name;
    protected int atk;
    protected int hp;
    protected int exp;
    protected int x, y;
    protected Map map;
    protected Hero Hero[];

    Hero(Map map, Hero hero[]) {
        this.map = map;
        this.Hero = hero;
    }

    /**
     * 英雄攻击
     *
     * @param DefendHero 被攻击的英雄序号
     */
    public void attack(int DefendHero) {
        int x = this.x;
        int y = this.y;
        int DefX = Hero[DefendHero - 1].getX();
        int DefY = Hero[DefendHero - 1].getY();

        double distance = Math.sqrt((x - DefX) * (x - DefX) + (DefY - y) * (DefY - y));
        if (distance <= 2) {
            int DefendHeroHP = Hero[DefendHero - 1].defend(attack());
            if (DefendHeroHP <= 0) {
                map.maps[Hero[DefendHero - 1].getX()][Hero[DefendHero - 1].getY()] = 'X';
                delay();
                map.maps[Hero[DefendHero - 1].getX()][Hero[DefendHero - 1].getY()] = '.';
                delay();
                System.out.println("英雄" + Hero[DefendHero - 1].getname() + "被" + getname() + "击杀");
            } else {
                delay();
                System.out.println("英雄" + Hero[DefendHero - 1].getname() + "剩余hp：" + DefendHeroHP);
            }
        } else {
            delay();
            System.out.println("两英雄间距离大于2，无法攻击");
        }
    }

    /**
     * 英雄射箭技能入口
     *
     * @param fangXiang 射箭方向
     */
    public void arrow(char fangXiang) {
        /* TODO Auto-generated method stub */
        int DefendHero = fire(Hero, fangXiang);
        if (DefendHero != -1) {
            int DefendHeroHP = Hero[DefendHero].defend(attack());
            if (DefendHeroHP <= 0) {
                map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = 'X';
                delay();
                //map.print();
                map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = '.';
                delay();
                //  map.print();
                System.out.println("英雄" + Hero[DefendHero].getname() + "被" + getname() + "击杀");
            } else {
                System.out.println("英雄" + Hero[DefendHero].getname() + "剩余hp：" + DefendHeroHP);
            }
        }
    }

    /**
     * 英雄移动
     *
     * @param FangXiang 移动方向
     * @param value     移动距离
     */
    public void move(char FangXiang, int value) {
        switch (FangXiang) {
            case 'u':
            case 'U':
                for (int i = 0; i < value; i++) {
                    if (x - 1 >= 0 && map.maps[x - 1][y] == '.') {
                        map.maps[x][y] = '.';
                        x--;
                        GengXinWeiZhi();
                        delay();
                    } else break;
                }
                break;
            case 'd':
            case 'D':
                for (int i = 0; i < value; i++) {
                    if (x + 1 < map.size_m && map.maps[x + 1][y] == '.') {
                        map.maps[x][y] = '.';
                        x++;
                        GengXinWeiZhi();
                        delay();
                    } else break;
                }
                break;
            case 'l':
            case 'L':
                for (int i = 0; i < value; i++) {
                    if (y - 1 >= 0 && map.maps[x][y - 1] == '.') {
                        map.maps[x][y] = '.';
                        y--;
                        GengXinWeiZhi();
                        delay();
                    } else break;
                }
                break;
            case 'r':
            case 'R':
                for (int i = 0; i < value; i++) {
                    if (y + 1 < map.size_n && map.maps[x][y + 1] == '.') {
                        map.maps[x][y] = '.';
                        y++;
                        GengXinWeiZhi();
                        delay();
                    } else break;
                }
                break;
        }

        //m代表y，n代表x。左上角为（0,0）
        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);
    }

    /**
     * 每个英雄都有的技能，闪现
     *
     * @param FangXiang 只有l、r、u、d，分别代表移动方向的左右上下
     * @param value     移动距离
     */
    public void ShanXian(char FangXiang, int value) {
        switch (FangXiang) {
            case 'u':
            case 'U':
                if (x - value < 0) {
                    value = x;
                }
                while (map.maps[x - value][y] != '.' && value != 0) {
                    value--;
                }
                x -= value;
                break;
            case 'd':
            case 'D':
                if (x + value >= map.size_m) {
                    value = map.size_m - x - 1;
                }
                while (map.maps[x + value][y] != '.' && value != 0) {
                    value--;
                }
                x += value;
                break;
            case 'l':
            case 'L':
                if (y - value < 0) {
                    value = y;
                }
                while (map.maps[x][y - value] != '.' && value != 0) {
                    value--;
                }
                y -= value;
                break;
            case 'R':
            case 'r':
                if (y + value >= map.size_n) {
                    value = map.size_n - y - 1;
                }
                while (map.maps[x][y + value] != '.' && value != 0) {
                    value--;
                }
                y += value;
                break;
        }
        map.maps[x][y] = '.';
        GengXinWeiZhi();
        //m代表y，n代表x。左上角为（0,0）
    }

    /**
     * 根据英雄中x，y的值在地图上显示
     */
    private void GengXinWeiZhi() {
        map.maps[x][y] = name;
    }

    protected void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     *
     * @param name
     * @param atk
     * @param hp
     * @param ex
     * @param x    位置x
     * @param y    位置y
     */
    public void ChuShiHua(boolean camp, char name, int atk, int hp, int ex, int x, int y) {
        this.camp = camp;
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.exp = ex;
        this.x = x;
        this.y = y;
        GengXinWeiZhi();
    }

    /**
     * 远程攻击
     *
     * @param hero      英雄数据
     * @param FangXiang 攻击方向
     * @return
     */
    public int fire(Hero[] hero, char FangXiang) {
        zidan one = new zidan(x, y);
        return one.fire(hero, FangXiang, map.maps);
    }

    public int attack() {
        return atk;
    }

    /**
     * @param ATK 遭受的攻击值
     * @return 剩余生命值
     */
    public int defend(int ATK) {
        hp -= ATK;
        return hp;
    }

    public void setX(int val) {
        x = val;
    }

    public void setY(int val) {
        y = val;
    }

    public char getname() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}