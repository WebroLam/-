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
    static public int HeroNum;
    private Hero[] Hero;
    public Map map;
    Thread mapThread;
    lowAI aI;

    Operator(int HeroNum) throws IOException {
        super("OperatorThread");
        this.HeroNum = HeroNum;
        map = new Map();
        Hero = new Hero[HeroNum];
        for (int i = 0; i < HeroNum; i++) {
            Hero[i] = new HeroA(map, Hero);
        }
        mapThread = new Thread(map, "MapThread");
        mapThread.start();
        aI = new lowAI(Hero);//智能Ai模块
        aI.start();
    }


    /**
     * @param n         第几个英雄（从1开始）
     * @param FangXiang 只有l、r、u、d，分别代表移动方向的左右上下
     * @param value     移动距离
     */
    private void ShanXian(int n, char FangXiang, int value) {
        Hero[n - 1].ShanXian(FangXiang, value);
    }

    /**
     * 英雄移动
     *
     * @param n         移动的英雄序号
     * @param FangXiang 移动方向
     * @param value     移动距离
     */
    private void move(int n, char FangXiang, int value) throws InterruptedException {
        Hero[n - 1].move(FangXiang, value);
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
    private void attack(int AttackHero, int DefendHero) {
        Hero[AttackHero - 1].attack(DefendHero);
    }

    /**
     * 英雄射箭技能入口
     *
     * @param hero2     射箭的英雄
     * @param fangXiang 射箭方向
     */
    private void arrow(int hero2, char fangXiang) {
        /* TODO Auto-generated method stub */
        Hero[hero2 - 1].arrow(fangXiang);

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
    public void initHero(int n, boolean camp,char name, int atk, int hp, int ex, int x, int y)//n表示第几个英雄,从1开始
    {
        Hero[n - 1].ChuShiHua(camp,name, atk, hp, ex, x, y);
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
            if (heroNum == -1)
                break;
            if (heroNum < 0 || heroNum > 10)
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
        map.flg = false;
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