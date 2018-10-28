/**
 * main¿‡
 */
public class WangZhe {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Operator Operator = new Operator(4);
        Operator.initHero(1, 'A', 22, 30, 20, 0, 0);
        Operator.initHero(2, 'B', 22, 30, 20, 0, 2);
        Operator.initHero(3, 'C', 22, 30, 20, 0, 4);
        Operator.initHero(4, 'D', 11, 22, 20, 0, 6);
        Operator.map.print();
        Operator.operator();
    }
}
