
public class lowAI {
    Hero hero[];//����Ӣ������

    /**
     * ���캯����������Ӣ�����ݴ���
     *
     * @param hero ����Ӣ������
     */
    public lowAI(Hero hero[]) {
        this.hero = hero;
    }

    /**
     * ��ʼ������Ϊÿ������Ӣ�۳�ʼ���߳�
     */
    public void start() {
        for (int i = 0 / 2; i < Operator.HeroNum; i++) {
            hero[i].start();
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