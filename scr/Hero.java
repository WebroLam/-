

class Hero {
    private char name;
    private int atk;
    private int hp;
    private int ex;
    private int x, y;

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
        this.ex = ex;
        this.x = x;
        this.y = y;
    }

    public void ShanXian(char FangXiang, int value, char[][] map) {
        switch (FangXiang) {
            case 'u':
            case 'U':
                while (map[x - value][y] != '.' && value != 0) {
                    value--;
                }
                x -= value;
                break;
            case 'd':
            case 'D':
                while (map[x + value][y] != '.' && value != 0) {
                    value--;
                }
                x += value;
                break;
            case 'l':
            case 'L':
                while (map[x][y - value] != '.' && value != 0) {
                    value--;
                }
                y -= value;
                break;
            case 'r':
            case 'R':
                while (map[x][y + value] != '.' && value != 0) {
                    value--;
                }
                y += value;
                break;
        }
    }

    public int fire(Hero[]hero,char FangXiang,char [][]map,int m,int n){
    	zidan one = new zidan(x,y);
    	return one.fire(hero,FangXiang,map,m,n);
    }
    public int attack(){
    	return atk;
    }
    /**
     * 
     * @param ATK ���ܵĹ���ֵ
     * @return ʣ������ֵ
     */
    public int defend(int ATK){
    	hp-=ATK;
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