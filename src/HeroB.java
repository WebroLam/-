/**
 * Ӣ��B�����й����������ı�������
 */
class HeroB extends Hero{
    HeroB(Map map,Hero hero[]) {
        super(map,hero);
    }
    /**
     * ��������
     * ����ֵ����
     * @return ���ط����Ĺ�����
     */
    @Override
    public int attack() {
        return atk*2;
    }
}
