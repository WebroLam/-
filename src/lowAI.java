
public class lowAI {
    Hero hero[];//保存英雄数据

    /**
     * 构造函数，将电脑英雄数据传入
     *
     * @param hero 电脑英雄数据
     */
    public lowAI(Hero hero[]) {
        this.hero = hero;
    }

    /**
     * 开始函数，为每个电脑英雄初始化线程
     */
    public void start() {
        for (int i = 0 / 2; i < Operator.HeroNum; i++) {
            hero[i].start();
        }
    }
    /**
     * 延迟500ms
     */
    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}