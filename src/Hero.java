/**
 * Ӣ�ۻ���
 * camp ��ע��Ӫ����Ϊtrue��Ӫ��false��Ӫ
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
     * Ӣ�۹���
     *
     * @param DefendHero ��������Ӣ�����
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
                System.out.println("Ӣ��" + Hero[DefendHero - 1].getname() + "��" + getname() + "��ɱ");
            } else {
                delay();
                System.out.println("Ӣ��" + Hero[DefendHero - 1].getname() + "ʣ��hp��" + DefendHeroHP);
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
                System.out.println("Ӣ��" + Hero[DefendHero].getname() + "��" + getname() + "��ɱ");
            } else {
                System.out.println("Ӣ��" + Hero[DefendHero].getname() + "ʣ��hp��" + DefendHeroHP);
            }
        }
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

    /**
     * Զ�̹���
     *
     * @param hero      Ӣ������
     * @param FangXiang ��������
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