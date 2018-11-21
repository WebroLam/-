
public class lowAI {
    Hero hero[];//����Ӣ������

    /**
     * ���캯����������Ӣ�����ݴ���
     * @param hero ����Ӣ������
     */
    public lowAI(Hero hero[]) {
        this.hero = hero;
    }

    /**
     * ��ʼ������Ϊÿ������Ӣ�۳�ʼ���߳�
     */
    public void start() {
        for (int i = Operator.HeroNum / 2; i < Operator.HeroNum; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    System.out.println(finalI + "�̳߳ɹ�����");
                    while (hero[finalI].hp > 0) {
                        switch ((int) ((Math.random() * 30) % 3)) {
                            case 0:
                                autoAttck(hero[finalI]);
                            case 1:
                                autoShot(hero[finalI]);
                            case 2:
                                stay();
                        }

                    }
                }
            }.start();
        }
    }

    /**
     * �Զ�������Χ����Ȧ�ڵĵ���
     *
     * @param hero ���𹥻���Ӣ��
     */
    private void autoAttck(Hero hero) {
        for (int i = -2; i < 3; i++) {
            if (hero.x + i < 0 || hero.x + i >= Map.size_m) continue;//��ֹԽ��
            for (int j = -2; j < 3; j++) {
                if (hero.y + j < 0 || hero.y + j >= Map.size_n) continue;//��ֹԽ��
                if (hero.map.maps[hero.x + i][hero.y + j] >= 'A' && hero.map.maps[hero.x + i][hero.y + j] <= 'L') {
                    findHero(hero, i, j);
                }
            }
        }
    }

    /**
     * ��ѯӢ���������
     *
     * @param hero Ӣ��
     * @param i    λ��x
     * @param j    λ��y
     */
    private void findHero(Hero hero, int i, int j) {
        for (int k = 0; k < Operator.HeroNum; k++) {
            if (hero.map.maps[hero.x + i][hero.y + j] == hero.Hero[k].getname())
                if (hero.camp != hero.Hero[k].camp) {//���ڲ�ͬ��Ӫ
                    if (i < 0) hero.move('u', -i);
                    else hero.move('d', i);
                    if (j < 0) hero.move('l', -j);
                    else hero.move('r', j);
                    hero.attack(k + 1);
                    delay();
                }
        }
    }

    /**
     * �Զ����������������ڵĵ���
     *
     * @param hero
     */
    private void autoShot(Hero hero) {
        for (int i = 1; i <= 5; i++) {//���һ��һ����������
            if (hero.x - i >= 0)//��ֹԽ��
                if (hero.map.maps[hero.x - i][hero.y] >= 'A' && hero.map.maps[hero.x - i][hero.y] <= 'L') {//��         ������
                    hero.arrow('u');
                }

            if (hero.x + i < Map.size_m)//��ֹԽ��
                if (hero.map.maps[hero.x + i][hero.y] >= 'A' && hero.map.maps[hero.x + i][hero.y] <= 'L') {//��
                    hero.arrow('d');
                }

            if (hero.y - i >= 0)//��ֹԽ��
                if (hero.map.maps[hero.x][hero.y - i] >= 'A' && hero.map.maps[hero.x][hero.y - i] <= 'L') {//��
                    hero.arrow('l');
                }

            if (hero.y + i < Map.size_n)//��ֹԽ��
                if (hero.map.maps[hero.x][hero.y + i] >= 'A' && hero.map.maps[hero.x][hero.y + i] <= 'L') {//��
                    hero.arrow('r');
                }
        }
    }

    /**
     * ��������
     */
    private void stay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ӳ�500ms
     */
    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}