/**
 * 英雄B，具有攻击力翻倍的被动技能
 */
class HeroB extends Hero{
    /**
     * 被动技能
     * 攻击值翻倍
     * @return 返回翻倍的攻击力
     */
    @Override
    public int attack() {
        return atk*2;
    }
}
