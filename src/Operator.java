import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;


/**
 * ս��������
 * ���ڿ���ս��
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
        aI = new lowAI(Hero);//����Aiģ��
        aI.start();
    }


    /**
     * @param n         �ڼ���Ӣ�ۣ���1��ʼ��
     * @param FangXiang ֻ��l��r��u��d���ֱ�����ƶ��������������
     * @param value     �ƶ�����
     */
    private void ShanXian(int n, char FangXiang, int value) {
        Hero[n - 1].ShanXian(FangXiang, value);
    }

    /**
     * Ӣ���ƶ�
     *
     * @param n         �ƶ���Ӣ�����
     * @param FangXiang �ƶ�����
     * @param value     �ƶ�����
     */
    private void move(int n, char FangXiang, int value) throws InterruptedException {
        Hero[n - 1].move(FangXiang, value);
    }

    private void delay() throws InterruptedException {
        this.sleep(500);
    }

    /**
     * Ӣ�۹���
     *
     * @param AttackHero ������Ӣ�����
     * @param DefendHero ��������Ӣ�����
     */
    private void attack(int AttackHero, int DefendHero) {
        Hero[AttackHero - 1].attack(DefendHero);
    }

    /**
     * Ӣ������������
     *
     * @param hero2     �����Ӣ��
     * @param fangXiang �������
     */
    private void arrow(int hero2, char fangXiang) {
        /* TODO Auto-generated method stub */
        Hero[hero2 - 1].arrow(fangXiang);

    }


    /**
     * ��ʼ��Ӣ��
     *
     * @param n    �ڼ���Ӣ�ۣ���1��ʼ
     * @param name Ӣ������
     * @param atk  ����ֵ
     * @param hp   ����ֵ
     * @param ex   ����ֵ
     * @param x    ��ʼλ��
     * @param y    ��ʼλ��
     */
    public void initHero(int n, boolean camp,char name, int atk, int hp, int ex, int x, int y)//n��ʾ�ڼ���Ӣ��,��1��ʼ
    {
        Hero[n - 1].ChuShiHua(camp,name, atk, hp, ex, x, y);
    }

    /**
     * ����Ĳ���������ִ�в���ָ��
     */
    public void operator() throws InterruptedException {
        Scanner reader = new Scanner(System.in);
        int value;
        int Hero;
        String CaoZuo;
        char FangXiang;
        while (true) {
            System.out.println("������������Ӣ����ţ�-1����");
            Hero = reader.nextInt();
            if (Hero == -1)
                break;
            if (Hero > HeroNum || Hero < 1)
                System.out.println("�����Ӣ����Ŵ���");
            else {
                label:
                while (true) {
                    System.out.println("����Ӣ�۲���");
                    CaoZuo = reader.next();

                    switch (CaoZuo) {
                        case "ShanXian":
                            System.out.println("���ַ���  ���־��루���Ϊ5��");
                            FangXiang = reader.next().charAt(0);
                            while (!reader.hasNextInt()) {
                                System.out.println("�����˷��������ݣ�����������");
                                reader.next();
                            }
                            value = reader.nextInt();
                            if (value > 5)
                                value = 5;
                            ShanXian(Hero, FangXiang, value);
                            map.print();
                            break;
                        case "move":
                            System.out.println("�ƶ�����  �ƶ�����");
                            FangXiang = reader.next().charAt(0);
                            while (!reader.hasNextInt()) {
                                System.out.println("�����˷��������ݣ�����������");
                                reader.next();
                            }
                            value = reader.nextInt();
                            move(Hero, FangXiang, value);
                            break;
                        case "atk":
                            System.out.println("�����뱻����������");

                            while (!reader.hasNextInt()) {
                                System.out.println("�����˷��������ݣ�����������");
                                reader.next();
                            }
                            value = reader.nextInt();

                            attack(Hero, value);

                            break;
                        case "arrow":
                            System.out.println("�����빥������");

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