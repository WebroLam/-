/**
 * 英雄基类
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
     * 初始化
     *
     * @param name
     * @param atk
     * @param hp
     * @param ex
     * @param x    位置x
     * @param y    位置y
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
     * 每个英雄都有的技能，闪现
     * @param FangXiang 闪现方向
     * @param value 闪现距离
     * @param maps 地图数据传入
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
     * 远程攻击
     * @param hero 英雄数据
     * @param FangXiang 攻击方向
     * @param maps 地图数据
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