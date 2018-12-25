import java.io.*;
import java.util.LinkedList;

class Review {
    public static LinkedList<String> Control[] = new LinkedList[10];
    Operator opera;

    Review(Operator oper) {
        for (int i = 0; i < 10; i++) {
            Control[i] = new LinkedList<String>();
        }
        opera = oper;
        //loading(oper);
    }

    public boolean loading(BufferedReader reader) throws IOException {
        String str = null;

        while (true) {
            str = reader.readLine();
            if (str == null)
                break;
            char hero = str.charAt(0);
            int index = opera.findHero(hero);
            if (index >= 0)
                Control[index].addLast(str);
        }
        System.out.println("回放文件读入完成");
        return true;
    }
}


