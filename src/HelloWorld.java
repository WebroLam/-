
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//经验增加，人物类？地图类？
		Map map = new Map(10,15,2);
		map.ChuShiHua(1, 'A', 20, 30, 20, 2, 3);
		map.ChuShiHua(2, 'B', 30, 20, 10, 2, 4);
		map.print();
	}
}
