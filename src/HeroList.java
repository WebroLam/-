public class HeroList {
    public Hero hero[];
    public int heroNum;
    HeroList(int heroNum)
    {
        this.heroNum = heroNum;
        hero = new Hero[heroNum];

 //       for (int i = 0; i < heroNum; i++)
//            Hero[i] = new HeroA(map);
    }
}
