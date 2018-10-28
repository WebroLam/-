
public class WangZhe {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //经验增加，人物类？地图类？
        Operator Operator = new Operator( 3);
        Operator.ChuShiHua(1, 'A', 20, 30, 20, 0, 0);
        Operator.ChuShiHua(2, 'B', 20, 30, 20, 0, 2);
        Operator.ChuShiHua(3, 'C', 20, 30, 20, 0, 4);
        Operator.map.print();
        Operator.operator();
    }
}
