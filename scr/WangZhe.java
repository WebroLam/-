
public class WangZhe {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //�������ӣ������ࣿ��ͼ�ࣿ
        Map map = new Map(8, 8, 3);
        map.ChuShiHua(1, 'A', 20, 30, 20, 0, 0);
        map.ChuShiHua(2, 'B', 20, 30, 20, 0, 2);
        map.ChuShiHua(3, 'C', 20, 30, 20, 0, 4);
        map.print();
        map.operator();
    }
}
