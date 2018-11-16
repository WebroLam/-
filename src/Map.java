import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 地图类
 * 用于保存地图数据
 * 输出地图
 */
class Map extends JFrame implements Runnable {
    public static int size_m = 12;
    public static int size_n = 12;
    public boolean flg = true;
    private JPanel Jpanel = new JPanel();
    JLabel jlabel = new JLabel();
    char[][] maps;

    Map() throws IOException {
        super("王者荣耀");
        setSize(size_n * 50, size_m * 50);
        //    setLocation(1000,350);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addBar();
        initJLabel();
        setVisible(true);
//        Graphics g = this.getGraphics();
//        Image img = ImageIO.read(new File("wxfix.png"));
//        g.drawImage(img, 30, 80, null);

        maps = new char[size_m][size_n];
        for (int i = 0; i < size_m; i++)
            for (int j = 0; j < size_n; j++)
                maps[i][j] = '.';
        for (int i = 2; i < 5; i++)
            maps[3][i] = '*';
     //   maps[10][6]='*';maps[10][4]='*';maps[10][5]='*';maps[10][7]='*';maps[10][8]='*';
    }

    private void addBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem jmi_Start = new JMenuItem("开始游戏");
        jmi_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jmi_Start)
                {

                }
            }
        });

        JMenuItem jmi_End = new JMenuItem("结束游戏");
        jmi_End.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jmi_End)
                    System.exit(0);
            }
        });

        JMenuItem jmi_Author = new JMenuItem("Webro");


        JMenu jm_Source = new JMenu("选项");
        jm_Source.add(jmi_Start);
        jm_Source.add(jmi_End);

        JMenu jm_About = new JMenu("关于");
        jm_About.add(jmi_Author);

        menuBar.add(jm_Source);
        menuBar.add(jm_About);
    }

    private void initJLabel() {
      //  jlabel.setBounds(5, 20, 10, 20);
        jlabel.setOpaque(true);
       // jlabel.setText(String.valueOf(maps[0]));
        Jpanel.add(jlabel);
        setContentPane(Jpanel);
    }

    @Override
    public void run() {
        while (flg) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printToWindow();
        }
    }

    /**
     * 打印地图
     */
    public void print() {
        System.out.println("===============");
        for (int i = 0; i < size_m; i++) {
            for (int j = 0; j < size_n; j++)
                System.out.print(maps[i][j]);
            System.out.println();
        }
        System.out.println("===============");
       // initJLabel();
    }

    public void printToWindow() {
        StringBuilder out = new StringBuilder();
        out.append("<html>");
        for (int i = 0; i < size_m; i++) {
            out.append(maps[i]);
            out.append("<br/>");
        }
        // System.out.println("===============");
        out.append("<html>");
        jlabel.setText(out.toString());
    }
}