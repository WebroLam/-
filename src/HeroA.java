/**
 * Ӣ��A�������˺�����ı�������
 */
class HeroA  extends Hero{
    HeroA(Map map,Hero hero[]) {
        super(map,hero);
    }
    /**
     * �������ܣ��˺�����
     * @param ATK ���ܵĹ���ֵ
     * @return
     */
    @Override
    public int defend(int ATK)
    {
        hp -= ATK/2;
        return hp;
    }
}
