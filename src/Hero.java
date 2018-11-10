/**
 * Ӣ�ۻ���
 *
 */

class Hero {
    protected char name;
    protected int atk;
    protected int hp;
    protected int exp;
    protected int x, y;

    Hero() {

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
    public void ChuShiHua(char name, int atk, int hp, int ex, int x, int y) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.exp = ex;
        this.x = x;
        this.y = y;
    }

    /**
     * ÿ��Ӣ�۶��еļ��ܣ�����
     * @param FangXiang ���ַ���
     * @param value ���־���
     * @param maps ��ͼ���ݴ���
     */
    public void ShanXian(char FangXiang, int value, char[][] maps) {
        switch (FangXiang) {
            case 'u':
            case 'U':
                while (maps[x - value][y] != '.' && value != 0) {
                    value--;
                }
                x -= value;
                break;
            case 'd':
            case 'D':
                while (maps[x + value][y] != '.' && value != 0) {
                    value--;
                }
                x += value;
                break;
            case 'l':
            case 'L':
                while (maps[x][y - value] != '.' && value != 0) {
                    value--;
                }
                y -= value;
                break;
            case 'r':
            case 'R':
                while (maps[x][y + value] != '.' && value != 0) {
                    value--;
                }
                y += value;
                break;
        }
    }

    /**
     * Զ�̹���
     * @param hero Ӣ������
     * @param FangXiang ��������
     * @param maps ��ͼ����
     * @return
     */
    public int fire(Hero[] hero, char FangXiang, char[][] maps) {
        zidan one = new zidan(x, y);
        return one.fire(hero, FangXiang, maps);
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

    public char getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}