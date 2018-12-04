import static java.lang.Math.sqrt;

/**
 * Ӣ�ۻ���
 * camp ��ע��Ӫ����Ϊtrue��Ӫ��false��Ӫ
 */
class Hero extends Thread {
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
        relive_time = 15 * 1000;//ms
        robot = true;
        speed = 1;
        alive = true;
        this.map = map;
        this.Hero = hero;
    }

    @Override
    public void run() {
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
        // map.print();
        map.maps[getX()][getY()] = '.';
        delay();
        // map.maps[x][y] = '.';
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