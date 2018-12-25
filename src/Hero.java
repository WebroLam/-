import java.io.IOException;
import static java.lang.Math.sqrt;

/**
 * Ӣ�ۻ���
 * camp ��ע��Ӫ����Ϊtrue��Ӫ��false��Ӫ
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
    protected long relive_time;//ʱ�䵥λms


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
     * ��Ҵ��
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
     * �����󸴻�ʱ��
     */
    private void dead() {
        long death_timer = System.currentTimeMillis();
        System.out.println(name + "����ʱ��" + (System.currentTimeMillis()) + "ms");
        while (!alive) {
            if (System.currentTimeMillis() - death_timer >= relive_time) {//�ж��Ƿ񸴻�
                GengXinWeiZhi();
                hp = 30;
                alive = true;

                System.out.println("Ӣ��" + name + "�Ѹ��");
            }
        }
    }

    /**
     * �����˴��
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
     * ����˲���ִ�е�����
     */
    private void dying() {
        alive = false;
        map.maps[getX()][getY()] = 'X';
        delay();
        map.maps[getX()][getY()] = '.';
        delay();
    }

    /**
     * �������һλӢ�۵�֮��ľ���
     *
     * @param other ��һλӢ��
     * @return ����һλӢ�۵�֮��ľ��� ����
     */
    public int calculateDistanceFromOther(Hero other) {
        double temp = (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
        return (int) sqrt(temp);
    }

    /**
     * �������һλӢ�۵�֮��ľ���
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
     * Ӣ�۹���
     *
     * @param defendHeroNum ��������Ӣ����ţ���1��ʼ��
     */
    public void attack(int defendHeroNum) {
        attack(Hero[defendHeroNum - 1]);
    }

    /**
     * Ӣ�۹���
     *
     * @param defendHero ������Ӣ��
     */
    public void attack(Hero defendHero) {
        /**
         * д���ļ���¼�ƶ���
         */
        try {
            MyWrite.write(name, "attack " + defendHero.name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (calculateDistanceFromOther(defendHero) <= 2) {
            int DefendHeroHP = defendHero.defend(attack());
            if (DefendHeroHP <= 0) {

                System.out.println("Ӣ��" + defendHero.getname() + "��" + getname() + "��ɱ");
            } else {
                delay();
                System.out.println("Ӣ��" + defendHero.getname() + "ʣ��hp��" + DefendHeroHP);
            }
        } else {
            delay();
            System.out.println("��Ӣ�ۼ�������2���޷�����");
        }
    }


    /**
     * Ӣ������������
     *
     * @param fangXiang �������
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
     * Ӣ���ƶ�
     *
     * @param FangXiang �ƶ�����
     * @param value     �ƶ�����
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

        //m����y��n����x�����Ͻ�Ϊ��0,0��
        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);
    }

    /**
     * ÿ��Ӣ�۶��еļ��ܣ�����
     *
     * @param FangXiang ֻ��l��r��u��d���ֱ�����ƶ��������������
     * @param value     �ƶ�����
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
        //m����y��n����x�����Ͻ�Ϊ��0,0��
    }

    /**
     * ����Ӣ����x��y��ֵ�ڵ�ͼ����ʾ
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
     * ��ʼ��
     *
     * @param name
     * @param atk
     * @param hp
     * @param ex
     * @param x    λ��x
     * @param y    λ��y
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
     * �ƶ���Ŀ�����
     */
    public void moveToTargetPoint() {//���͵�ͼ��x��y����
        if (target_x - x < 0) move('u', 1);//-target_x + x
        else if (target_x - x > 0) move('d', 1);
        if (target_y - y < 0) move('l', 1);
        else if (target_y - y > 0) move('r', 1);
    }

    /**
     * �ƶ���
     */
    public void moveToTargetHero(Hero targetHero) {
        setTarget_xy(targetHero.x, targetHero.y);
        moveToTargetPoint();
    }

    /**
     * ����Ŀ��λ��
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
     * �Զ�������Χ����Ȧ�ڵĵ���
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
     * �Զ����������������ڵĵ���
     */
    private void autoShot() {
        for (int i = 1; i <= 5; i++) {//���һ��һ����������
            if (this.x - i >= 0)//��ֹԽ��
                if (this.map.maps[this.x - i][this.y] >= 'A' && this.map.maps[this.x - i][this.y] <= 'L') {//��         ������
                    this.arrow('u');
                }

            if (this.x + i < Map.size_m)//��ֹԽ��
                if (this.map.maps[this.x + i][this.y] >= 'A' && this.map.maps[this.x + i][this.y] <= 'L') {//��
                    this.arrow('d');
                }

            if (this.y - i >= 0)//��ֹԽ��
                if (this.map.maps[this.x][this.y - i] >= 'A' && this.map.maps[this.x][this.y - i] <= 'L') {//��
                    this.arrow('l');
                }

            if (this.y + i < Map.size_n)//��ֹԽ��
                if (this.map.maps[this.x][this.y + i] >= 'A' && this.map.maps[this.x][this.y + i] <= 'L') {//��
                    this.arrow('r');
                }
        }
    }

    /**
     * ��������
     */
    private void stay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ATK ���ܵĹ���ֵ
     * @return ʣ������ֵ
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