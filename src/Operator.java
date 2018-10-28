import java.util.*;

/**
 * ս��������
 * ���ڿ���ս��
 */
class Operator {
    private int size_m = 12;
    private int size_n = 12;
    private int HeroNum;
    private Hero[] Hero;
    public Map map;

    Operator(int HeroNum) {
        this.HeroNum = HeroNum;
        map = new Map();

        Hero = new Hero[HeroNum];
        for (int i = 0; i < HeroNum; i++)
            Hero[i] = new HeroA();
    }

    /**
     * @param n         �ڼ���Ӣ�ۣ���1��ʼ��
     * @param FangXiang ֻ��l��r��u��d���ֱ�����ƶ��������������
     * @param value     �ƶ�����
     *
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
        //m����y��n����x�����Ͻ�Ϊ��0,0��


        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);


    }
    
    private void move(int n, char FangXiang, int value) {
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
                        map.print();
                    } else {
                        if (i == 0)
                            map.print();
                        break;
                    }
                }
                break;
            case 'd':
            case 'D':
                for (int i = 0; i < value; i++) {
                    if (x + 1 <size_m && map.maps[x + 1][y] == '.') {
                        map.maps[x][y] = '.';
                        x++;
                        Hero[n - 1].setX(x);
                        GengXinWeiZhi(n);
                        map.print();
                    } else {
                        if (i == 0)
                            map.print();
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
                        map.print();
                    } else {
                        if (i == 0)
                            map.print();
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
                        map.print();
                    } else {
                        if (i == 0)
                            map.print();
                        break;
                    }
                }
                break;
        }

        //m����y��n����x�����Ͻ�Ϊ��0,0��


        //	if(Hero[n-1].getX())
        //	Hero[n-1].ShanXian(x, y);
        //	GengXinWeiZhi(n);
    }
    private void attack(int AttackHero,int DefendHero)
    {
    	int AtkX = Hero[AttackHero-1].getX();
    	int AtkY = Hero[AttackHero-1].getY();
    	int DefX = Hero[DefendHero-1].getX();
    	int DefY = Hero[DefendHero-1].getY();
    	
    	double distance = Math.sqrt((AtkX-DefX)*(AtkX-DefX)+(DefY-AtkY)*(DefY-AtkY));
    	if(distance<=2){
    		int DefendHeroHP=Hero[DefendHero-1].defend(Hero[AttackHero-1].attack());
    		if(DefendHeroHP<=0){
    			map.maps[Hero[DefendHero-1].getX()][Hero[DefendHero-1].getY()] = 'X';
    			map.print();
    			map.maps[Hero[DefendHero-1].getX()][Hero[DefendHero-1].getY()] = '.';
    			map.print();
    			System.out.println("Ӣ��"+Hero[DefendHero-1].getName()+"��"+Hero[AttackHero-1].getName()+"��ɱ");
    		}else{
    			System.out.println("Ӣ��"+Hero[DefendHero-1].getName()+"ʣ��hp��"+DefendHeroHP);
    		}
    	}
    	else
    	{
    		System.out.println("��Ӣ�ۼ�������2���޷�����");
    	}
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
    public void initHero(int n, char name, int atk, int hp, int ex, int x, int y)//n��ʾ�ڼ���Ӣ��,��1��ʼ
    {
        Hero[n - 1].ChuShiHua(name, atk, hp, ex, x, y);
        GengXinWeiZhi(n);
    }

    /**
     * ����Ӣ����x��y��ֵ�ڵ�ͼ����ʾ
     *
     */
    private void GengXinWeiZhi(int n) {
        map.maps[Hero[n - 1].getX()][Hero[n - 1].getY()] = Hero[n - 1].getName();
    }

    public void operator() {
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

	private void arrow(int hero2, char fangXiang) {
        /* TODO Auto-generated method stub */
		int DefendHero=Hero[hero2-1].fire(Hero,fangXiang, map.maps);
		if(DefendHero!=-1)
		{
			int DefendHeroHP=Hero[DefendHero].defend(Hero[hero2-1].attack());
    		if(DefendHeroHP<=0){
    			map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = 'X';
    			map.print();
    			map.maps[Hero[DefendHero].getX()][Hero[DefendHero].getY()] = '.';
    			map.print();
    			System.out.println("Ӣ��"+Hero[DefendHero].getName()+"��"+Hero[hero2-1].getName()+"��ɱ");
    		}else{
    			System.out.println("Ӣ��"+Hero[DefendHero].getName()+"ʣ��hp��"+DefendHeroHP);
    		}
		}
	}
}









