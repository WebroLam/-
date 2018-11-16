/**
 * 英雄A，具有伤害减半的被动技能
 */
class HeroA  extends Hero{
    HeroA(Map map,Hero hero[]) {
        super(map,hero);
    }
    /**
     * 被动技能，伤害减半
     * @param ATK 遭受的攻击值
     * @return
     */
    @Override
    public int defend(int ATK)
    {
        hp -= ATK/2;
        return hp;
    }
}
