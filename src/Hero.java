

class Hero {
    private char name;
    private int atk;
    private int hp;
    private int ex;
    private int x, y;

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
        this.ex = ex;
        this.x = x;
        this.y = y;
    }

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