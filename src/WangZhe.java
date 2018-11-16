import java.io.IOException;
/**
 * main¿‡aaa
 */
public class WangZhe {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Operator Operator = new Operator(10);
        Operator.initHero(1, true, 'A', 22, 30, 20, 0, 0);
        Operator.initHero(2, true, 'B', 22, 30, 20, 0, 2);
        Operator.initHero(3, true, 'C', 22, 30, 20, 0, 5);
        Operator.initHero(4, true, 'D', 11, 22, 20, 0, 6);
        Operator.initHero(5, true, 'E', 11, 22, 20, 0, 10);


        Operator.initHero(6, false, 'F', 22, 30, 20, 11, 2);
        Operator.initHero(7, false, 'G', 22, 30, 20, 11, 4);
        Operator.initHero(8, false, 'H', 11, 22, 20, 11, 6);
        Operator.initHero(9, false, 'K', 22, 30, 20, 11, 8);
        Operator.initHero(10, false, 'J', 22, 30, 20, 11, 10);

        Operator.start();
    }
}
