import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 地图类
 * 用于保存地图数据
 * 输出地图
 */
class Map extends JFrame implements Runnable {
    private int m = 12;
    private int n = 12;
    char[][] maps;

    Map()
    {
        super("MapThread");
        maps = new char[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                maps[i][j] = '.';
        for(int i = 2; i < 5; i++)
            maps[3][i]='*';
    }
    @Override
    public void run(){
        while (true)
        {
            try {
                Thread.sleep(500  );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print();
        }
    }
    /**
     * 打印地图
     */
    public void print() {
        System.out.println("===============");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(maps[i][j]);
            System.out.println();
        }
        System.out.println("===============");
    }
}