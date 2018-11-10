import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;


/**
 * 战场控制类
 * 用于控制战场
 */
class Operator extends Thread {
    private int size_m = 12;
    private int size_n = 12;
    private int HeroNum;
    private Hero[] Hero;
    public Map map;
    Thread mapThread;

    Operator(int HeroNum) {
        super("OperatorThread");
        this.HeroNum = HeroNum;
        map = new Map();

        Hero = new Hero[HeroNum];
        for (int i = 0; i < HeroNum; i++)
            Hero[i] = new HeroA();
        mapThread = new Thread(map,"MapThread");
        mapThread.start();
    }
    /**
     * @param n         第几个英雄（从1开始）
     * @param FangXiang 只有l、r、u、d，分别代表移动方向的左右上下
     * @param value     移动距离
     */
    private void ShanXian(int n, char FangXiang, int value) {

        int x = Hero[n - 1].getX();
        int y = Hero[n - 1].getY();
        switch (FangXiang) {
            case 'u':
            case 'U':
                if (x - value >= 0) {
                    Hero[n - 1].ShanXian(FangXiang, value, map.maps);
                } else {
                    Hero[n - 1].ShanXian(FangXiang, x, map.maps);
                }
                break;

            case 'd':
            case 'D':
                if (x + value < size_m) {
                    Hero[n - 1].ShanXian(FangXiang, value, map.maps);
                } else {
                    Hero[n - 1].ShanXian(FangXiang, size_m - x - 1, map.maps);
                }
                break;

            case 'l':
            case 'L':
                if (y - value >= 0) {
                    Hero[n - 1].ShanXian(FangXiang, value, map.maps);
                } else {
                    Hero[n - 1].ShanXian(FangXiang, y, map.maps);
                }
                break;

            case 'R':
            case 'r':
                if (y + value < this.size_n) {
                    Hero[n - 1].ShanXian(FangXiang, value, map.maps);
                } else {
                    Hero[n - 1].ShanXian(FangXiang, this.size_n - y - 1, map.maps);
                }
                break;
        }
        map.maps[x][y] = '.';
        GengXinWeiZhi(n);
        //m代表y，n代表x。左上角为（0,0）


        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);


    }

    /**
     * 英雄移动
     *
     * @param n         移动的英雄序号
     * @param FangXiang 移动方向
     * @param value     移动距离
     */
    private void move(int n, char FangXiang, int value) throws InterruptedException {
        int x = Hero[n - 1].getX();
        int y = Hero[n - 1].getY();
        switch (FangXiang) {
            case 'u':
            case 'U':
                for (int i = 0; i < value; i++) {
                    if (x - 1 >= 0 && map.maps[x - 1][y] == '.') {
                        map.maps[x][y] = '.';
                        x--;
                        Hero[n - 1].setX(x);
                        GengXinWeiZhi(n);
                        delay();
                    } else {
                        if (i == 0)
                            delay();
                        break;
                    }
                }
                break;
            case 'd':
            case 'D':
                for (int i = 0; i < value; i++) {
                    if (x + 1 < size_m && map.maps[x + 1][y] == '.') {
                        map.maps[x][y] = '.';
                        x++;
                        Hero[n - 1].setX(x);
                        GengXinWeiZhi(n);
                        delay();
                    } else {
                        if (i == 0)
                            delay();
                        break;
                    }
                }
                break;
            case 'l':
            case 'L':
                for (int i = 0; i < value; i++) {
                    if (y - 1 >= 0 && map.maps[x][y - 1] == '.') {
                        map.maps[x][y] = '.';
                        y--;
                        Hero[n - 1].setY(y);
                        GengXinWeiZhi(n);
                        delay();
                    } else {
                        if (i == 0)
                            delay();
                        break;
                    }
                }
                break;
            case 'r':
            case 'R':
                for (int i = 0; i < value; i++) {
                    if (y + 1 < size_n && map.maps[x][y + 1] == '.') {
                        map.maps[x][y] = '.';
                        y++;
                        Hero[n - 1].setY(y);
                        GengXinWeiZhi(n);
                        delay();
                    } else {
                        if (i == 0)
                            delay();
                        break;
                    }
                }
                break;
        }

        //m代表y，n代表x。左上角为（0,0）


        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);
    }

    private void delay() throws InterruptedException {
        this.sleep(500);
    }
    /**
     * 英雄攻击
     *
     * @param AttackHero 攻击的英雄序号
     * @param DefendHero 被攻击的英雄序号
     */
    private void attack(int AttackHero, int DefendHero) throws InterruptedException {
        int AtkX = Hero[AttackHero - 1].getX();
        int AtkY = Hero[AttackHero - 1].getY();
        int DefX = Hero[DefendHero - 1].getX();
        int DefY = Hero[DefendHero - 1].getY();

        double distance = Math.sqrt((AtkX - DefX) * (AtkX - DefX) + (DefY - AtkY) * (DefY - AtkY));
        if (distance <= 2) {
            int DefendHeroHP = Hero[DefendHero - 1].defend(Hero[AttackHero - 1].attack());
            if (DefendHeroHP <= 0) {
                map.maps[Hero[DefendHero - 1].getX()][Hero[DefendHero - 1].getY()] = 'X';
                delay();
                map.maps[Hero[DefendHero - 1].getX()][Hero[DefendHero - 1].getY()] = '.';
                delay();
                System.out.println("英雄" + Hero[DefendHero - 1].getName() + "被" + Hero[AttackHero - 1].getName() + "击杀");
            } else {
                delay();
                System.out.println("英雄" + Hero[DefendHero - 1].getName() + "剩余hp：" + DefendHeroHP);
            }
        } else {
            delay();
            System.out.println("两英雄间距离大于2，无法攻击");
        }
    }


    /**
     * 初始化英雄
     *
     * @param n    第几个英雄，从1开始
     * @param name 英雄姓名
     * @param atk  攻击值
     * @param hp   生命值
     * @param ex   经验值
     * @param x    初始位置
     * @param y    初始位置
     */
    public void initHero(int n, char name, int atk, int hp, int ex, int x, int y)//n表示第几个英雄,从1开始
    {
        Hero[n - 1].ChuShiHua(name, atk, hp, ex, x, y);
        GengXinWeiZhi(n);
    }

    /**
     * 根据英雄中x，y的值在地图上显示
     */
    private void GengXinWeiZhi(int n) {
        map.maps[Hero[n - 1].getX()][Hero[n - 1].getY()] = Hero[n - 1].getName();
    }

    /**
     * 具体的操作函数，执行操作指令
     */
    public void operator() throws InterruptedException {
        Scanner reader = new Scanner(System.in);
        int value;
        int Hero;
        String CaoZuo;
        char FangXiang;
        while (true) {
            System.out.println("输入所操作的英雄序号，-1结束");
            Hero = reader.nextInt();
            if (Hero == -1)
                break;
            if (Hero > HeroNum || Hero < 1)
                System.out.println("输入的英雄序号错误");
            else {
                label:
                while (true) {
                    System.out.println("输入英雄操作");
                    CaoZuo = reader.next();

                    switch (CaoZuo) {
                        case "ShanXian":
                            System.out.println("闪现方向  闪现距离（最大为5）");
                            FangXiang = reader.next().charAt(0);
                            while (!reader.hasNextInt()) {
                                System.out.println("输入了非数字内容，请重新输入");
                                reader.next();
                            }
                            value = reader.nextInt();
                            if (value > 5)
                                value = 5;
                            ShanXian(Hero, FangXiang, value);
                            map.print();
                            break;
                        case "move":
                            System.out.println("移动方向  移动距离");
                            FangXiang = reader.next().charAt(0);
                            while (!reader.hasNextInt()) {
                                System.out.println("输入了非数字内容，请重新输入");
                                reader.next();
                            }
                            value = reader.nextInt();
                            move(Hero, FangXiang, value);
                            break;
                        case "atk":
                            System.out.println("请输入被攻击玩家序号");

                            while (!reader.hasNextInt()) {
                                System.out.println("输入了非数字内容，请重新输入");
                                reader.next();
                            }
                            value = reader.nextInt();

                            attack(Hero, value);

                            break;
                        case "arrow":
                            System.out.println("请输入攻击方向");

                            FangXiang = reader.next().charAt(0);

                            arrow(Hero, FangXiang);

                            break;
                        case "end":
                            break label;
                    }
                }
            }
        }
        reader.close();
    }

    public boolean operatorFile(String CaoZuo[], int Hero) throws InterruptedException {
        int value;
        char FangXiang;
        if (Hero == -1)
            return false;
        else {
            label:
            switch (CaoZuo[0]) {
                case "ShanXian":
                    FangXiang = CaoZuo[1].charAt(0);
                    value = Integer.valueOf(CaoZuo[2]);
                    if (value > 5)
                        value = 5;
                    ShanXian(Hero, FangXiang, value);
                  //  map.print();
                    break;
                case "move":
                    FangXiang = CaoZuo[1].charAt(0);
                    value = Integer.valueOf(CaoZuo[2]);
                    move(Hero, FangXiang, value);
                    break;
                case "atk":
                    value = Integer.valueOf(CaoZuo[1]);
                    attack(Hero, value);
                    break;
                case "arrow":
                    FangXiang = CaoZuo[1].charAt(0);
                    arrow(Hero, FangXiang);
                    break;
                case "end":
                    return false;
            }
        }
        return true;
    }

    public void file() throws IOException, InterruptedException {
        Reader rd = new FileReader("test.txt");
        BufferedReader reader = new BufferedReader(rd);
        String str = null;

        while (true) {
            this.sleep(500);
            int heroNum = Integer.valueOf(reader.readLine());
            if(heroNum==-1)
                break;
            if(heroNum<0||heroNum>10)
                continue;
            while ((str = reader.readLine()) != null) {
                this.sleep(500);
                int n = str.split(" ").length;
                String[] strSub = new String[n];
                strSub = str.split(" ");
                if (!operatorFile(strSub, heroNum))
                    break;
            }
        }
    }

    /**
     * 英雄射箭技能入口
     *
     * @param hero2     射箭的英雄
     * @param fangXiang 射箭方向
     */
    private void arrow(int hero2, char fangXiang) {
        /* TODO Auto-generated method stub */
        int DefendHero = Hero[hero2 - 1].fire(Hero, fangXiang, map.maps);
        if (DefendHero != -1) {
            int DefendHeroHP = Hero[DefendHero].defend(Hero[hero2 - 1].attack());
            if (DefendHeroHP <= 0) {
                map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = 'X';
                map.print();
                map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = '.';
                map.print();
                System.out.println("英雄" + Hero[DefendHero].getName() + "被" + Hero[hero2 - 1].getName() + "击杀");
            } else {
                System.out.println("英雄" + Hero[DefendHero].getName() + "剩余hp：" + DefendHeroHP);
            }
        }
    }

    @Override
    public void run() {
        try {
            this.file();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}









