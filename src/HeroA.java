/**
 * Ӣ��A�������˺�����ı�������
 */
class HeroA  extends Hero{
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
