import java.io.IOException;
import static java.lang.Math.sqrt;

/**
 * 英雄基类
 * camp 标注阵营，分为true阵营和false阵营
 */
class Hero extends Thread {

    public static String GameMode = null;

    protected boolean camp;
    protected boolean robot;
    protected char name;
    protected int atk;
    protected int hp;
    protected int exp;
    protected int x, y;
    protected int target_x, target_y;
    protected Map map;
    protected Hero Hero[];
    protected int speed;
    protected int atk_speed;
    protected boolean alive;
    protected long relive_time;//时间单位ms


    Hero(Map map, Hero hero[]) {
        relive_time = 8 * 1000;//ms
        robot = true;
        speed = 5;
        alive = true;
        this.map = map;
        this.Hero = hero;
    }

    @Override
    public void run() {
        if (GameMode.equals("Player"))
            playing();
        else
            review();
    }

    private void review() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                playing();
            }
        }).start();

        while (Review.Control[getHeroNum(name)].size() > 0) {
            String str = Review.Control[getHeroNum(name)].getFirst();
            String[] strSub = str.split(" ");

            while (Integer.valueOf(strSub[1]) > System.currentTimeMillis() - MyWrite.startTime) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            switch (strSub[2]) {
                case "setTarget_xy":
                    setTarget_xy(Integer.valueOf(strSub[3]), Integer.valueOf(strSub[3]));
                    break;
                case "arrow":
                    arrow(strSub[3].charAt(0));
                    break;
                case "attack":
                    attack(getHeroNum(strSub[3].charAt(0)));
                    break;
            }
        }
    }

    private int getHeroNum(char NAME) {
        for (int i = 0; i < Operator.HeroNum; i++) {
            if (NAME == Hero[i].name) {
                return i;
            }
        }
        return -1;
    }

    public void playing() {
        if (!camp) {
            while (true) {
                robot_alive();
                dead();
            }
        } else {
            while (true) {
                player_alive();
                dead();
            }
        }
    }

    /**
     * 玩家存活
     */
    private void player_alive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (alive) {
                    if (target_x - x != 0 || target_y - y != 0) {
                        moveToTargetPoint();
                    }
                    delay();
                }
            }
        }).start();

        while (true) {
            if (hp <= 0) {
                dying();
                break;
            }
            delay();
        }
    }

    /**
     * 死亡后复活时间
     */
    private void dead() {
        long death_timer = System.currentTimeMillis();
        System.out.println(name + "死亡时间" + (System.currentTimeMillis()) + "ms");
        while (!alive) {
            if (System.currentTimeMillis() - death_timer >= relive_time) {//判断是否复活
                GengXinWeiZhi();
                hp = 30;
                alive = true;

                System.out.println("英雄" + name + "已复活！");
            }
        }
    }

    /**
     * 机器人存活
     */
    private void robot_alive() {
        while (alive) {
            //  System.out.println("alive"+hp);
            if (hp <= 0) {
                dying();
                break;
            }
            delay();
            switch ((int) ((Math.random() * 30) % 3)) {
                case 0:
                    autoAttck();
                    break;
                case 1:
                    autoShot();
                    break;
                case 2:
                    stay();
                    break;
            }
        }
    }

    /**
     * 死亡瞬间后执行的事情
     */
    private void dying() {
        alive = false;
        map.maps[getX()][getY()] = 'X';
        delay();
        map.maps[getX()][getY()] = '.';
        delay();
    }

    /**
     * 计算和另一位英雄的之间的距离
     *
     * @param other 另一位英雄
     * @return 和另一位英雄的之间的距离 整型
     */
    public int calculateDistanceFromOther(Hero other) {
        double temp = (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
        return (int) sqrt(temp);
    }

    /**
     * 计算和另一位英雄的之间的距离
     *
     * @param other_x
     * @param other_y
     * @return
     */
    public int calculateDistanceFromOther(int other_x, int other_y) {
        double temp = (x - other_x) * (x - other_x) + (y - other_y) * (y - other_y);
        return (int) sqrt(temp);
    }

    /**
     * 英雄攻击
     *
     * @param defendHeroNum 被攻击的英雄序号（从1开始）
     */
    public void attack(int defendHeroNum) {
        attack(Hero[defendHeroNum - 1]);
    }

    /**
     * 英雄攻击
     *
     * @param defendHero 被攻击英雄
     */
    public void attack(Hero defendHero) {
        /**
         * 写入文件，录制动作
         */
        try {
            MyWrite.write(name, "attack " + defendHero.name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (calculateDistanceFromOther(defendHero) <= 2) {
            int DefendHeroHP = defendHero.defend(attack());
            if (DefendHeroHP <= 0) {

                System.out.println("英雄" + defendHero.getname() + "被" + getname() + "击杀");
            } else {
                delay();
                System.out.println("英雄" + defendHero.getname() + "剩余hp：" + DefendHeroHP);
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
        try {
            MyWrite.write(name, "arrow " + fangXiang);
        } catch (IOException e) {
            e.printStackTrace();
        }
        zidan one = new zidan(x, y, this, fangXiang);
        one.start();
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
                        delay(1000/speed);
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
                        delay(1000/speed);
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
                        delay(1000/speed);
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
                        delay(1000/speed);
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

    protected void delay(int time ) {
        try {
            Thread.sleep(time);
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
        this.target_x = x;
        this.target_y = y;
        GengXinWeiZhi();
    }

    public int attack() {
        return atk;
    }

    /**
     * 移动到目标距离
     */
    public void moveToTargetPoint() {//鼠标和地图的x、y互换
        if (target_x - x < 0) move('u', 1);//-target_x + x
        else if (target_x - x > 0) move('d', 1);
        if (target_y - y < 0) move('l', 1);
        else if (target_y - y > 0) move('r', 1);
    }

    /**
     * 移动到
     */
    public void moveToTargetHero(Hero targetHero) {
        setTarget_xy(targetHero.x, targetHero.y);
        moveToTargetPoint();
    }

    /**
     * 设置目标位置
     *
     * @param target_x
     * @param target_y
     */
    public void setTarget_xy(int target_x, int target_y) {
        try {
            MyWrite.write(name, "setTarget_xy " + target_x + " " + target_y);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.target_x = target_x;
        this.target_y = target_y;
    }

    /**
     * 自动攻击周围距两圈内的敌人
     */
    private void autoAttck() {
        for (int k = 0; k < Operator.HeroNum / 2; k++) {
            if (calculateDistanceFromOther(Hero[k]) <= 3 && Hero[k].alive) {
                moveToTargetHero(Hero[k]);
                attack(k + 1);
            }
        }
    }

    /**
     * 自动射击上下左右五格内的敌人
     */
    private void autoShot() {
        for (int i = 1; i <= 5; i++) {//五格，一格一格往外层遍历
            if (this.x - i >= 0)//防止越界
                if (this.map.maps[this.x - i][this.y] >= 'A' && this.map.maps[this.x - i][this.y] <= 'L') {//上         下左右
                    this.arrow('u');
                }

            if (this.x + i < Map.size_m)//防止越界
                if (this.map.maps[this.x + i][this.y] >= 'A' && this.map.maps[this.x + i][this.y] <= 'L') {//下
                    this.arrow('d');
                }

            if (this.y - i >= 0)//防止越界
                if (this.map.maps[this.x][this.y - i] >= 'A' && this.map.maps[this.x][this.y - i] <= 'L') {//左
                    this.arrow('l');
                }

            if (this.y + i < Map.size_n)//防止越界
                if (this.map.maps[this.x][this.y + i] >= 'A' && this.map.maps[this.x][this.y + i] <= 'L') {//右
                    this.arrow('r');
                }
        }
    }

    /**
     * 发呆三秒
     */
    private void stay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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