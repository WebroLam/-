import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 地图类
 * 用于保存地图数据
 * 输出地图
 */
class Map extends JFrame implements Runnable {
    public static int size_m = 12;
    public static int size_n = 12;
    public static char[][] maps;

    public boolean flg = true;

    private Hero hero[];
    private MyPanel Jpanel = new MyPanel();
    private JLabel label[][] = new JLabel[size_m][size_n];

    Map(Hero hero[]) {
        super("王者荣耀");
        this.hero = hero;
        initJFrame();
        addBar();
        addkeyListener();
        addMouseListener();
        //initMaps();
        initJLabel();
        //        Graphics g = this.getGraphics();
//        Image img = ImageIO.read(new File("wxfix.png"));
//        g.drawImage(img, 30, 80, null);
        //victory();
    }

    /**
     * 初始化地图数据
     */
    private void initMaps() {

        for (int i = 0; i < size_m; i++)
            for (int j = 0; j < size_n; j++)
                maps[i][j] = '.';
        for (int i = 2; i < 5; i++)
            maps[3][i] = '*';
    }

    /**
     * 初始化JFrame
     */
    private void initJFrame() {
        setSize(size_n * 60 + 25, size_m * 60 + 25);
        setLocation(500, 35);
        setVisible(true);
       // setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 加入上方菜单栏
     * 菜单“选项”-》“开始游戏”、“结束游戏”
     * “关于”-》“Webro”
     */
    private void addBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu jm_Source = new JMenu("选项");//菜单一
        JMenuItem jmi_Start = new JMenuItem("开始游戏");//子菜单一
        jmi_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("23asd23asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd");
            }
        });
        JMenuItem jmi_End = new JMenuItem("结束游戏");//子菜单二
        jmi_End.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jm_Source.add(jmi_Start);
        jm_Source.add(jmi_End);


        JMenu jm_About = new JMenu("关于");//菜单二
        JMenuItem jmi_Author = new JMenuItem("Webro");//子菜单一
        jm_About.add(jmi_Author);


        menuBar.add(jm_Source);
        menuBar.add(jm_About);
    }

    /**
     * 初始化JLabel
     */
    private void initJLabel() {
        setLayout(null);
        add(Jpanel);
        Jpanel.setBounds(25, 25, 625, 625);
        Jpanel.setVisible(true);
        Jpanel.setBackground(Color.GRAY);
        Jpanel.setLayout(null);
        for (int i = 0; i < size_m; i++) {
            for (int j = 0; j < size_n; j++) {
                label[i][j] = new JLabel();
                label[i][j].setText(String.valueOf(maps[i][j]));
                label[i][j].setFont(new Font("Consolas", Font.PLAIN, 32));
                label[i][j].setBounds(25 + 50 * j, 10 + i * 50, 50, 50);//设置label位置,这里一定要设置，不然看不到label
                label[i][j].setOpaque(true);//设置不透明
                label[i][j].setBackground(Color.GRAY);
                Jpanel.add(label[i][j]);
            }

        }
    }

    @Override
    public void run() {
        while (flg) {
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //printToWindow();
            printVision();
        }
    }

    /**
     * 鼠标监听
     */
    void addMouseListener() {
        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println((e.getY() - 15) / 50 + "," + (e.getX() - 10) / 50);
                hero[Operator.nowHero].setTarget_xy((e.getY() - 15) / 50, (e.getX() - 10) / 50);
                /*
                 * @param target_y 鼠标x/300*12
                 * @param target_x 鼠标y/600*12
                 */
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        };
        Jpanel.addMouseListener(mouse);
    }

    void addkeyListener() {
        int heroNum = Operator.nowHero;
        KeyAdapter key = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                new Thread(new Runnable() {
                    @Override
                    synchronized public void run() {
                        switch (e.getKeyChar()) {
                            case 'w':
                            case 'W':
                                hero[heroNum].arrow('u');
                                break;
                            case 's':
                            case 'S':
                                hero[heroNum].arrow('d');
                                break;
                            case 'A':
                            case 'a':
                                hero[heroNum].arrow('l');
                                break;
                            case 'D':
                            case 'd':
                                hero[heroNum].arrow('r');
                                break;
                        }
                    }
                }).start();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                    case 'W':

                    case 's':
                    case 'S':

                    case 'A':
                    case 'a':

                    case 'D':
                    case 'd':
                        break;
                }
            }
        };

        addKeyListener(key);
    }

    /**
     * 打印地图到控制台
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

    /**
     * 根据英雄之间的距离有选择的显示英雄 (距离在5格之内才显示)
     */
    public void printVision() {
        //   System.out.println("===============");
        for (int i = 0; i < size_m; i++) {
            for (int j = 0; j < size_n; j++) {
                if (maps[i][j] < 'A' || maps[i][j] > 'Z' || hero[Operator.nowHero].calculateDistanceFromOther(i, j) <= 5)
                    label[i][j].setText(String.valueOf(maps[i][j]));
                else
                    label[i][j].setText(String.valueOf("."));
            }
        }
    }


    public void victory() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JOptionPane.showConfirmDialog(null, "恭喜~！你已获胜！", "Vetory", JOptionPane.YES_NO_OPTION);
            }
        }).start();
    }


    /**
     * 打印地图到窗口
     */
    public void printToWindow() {
        // StringBuilder out = new StringBuilder();
        //   out.append("<html>");
        //    for (int i = 0; i < size_m; i++) {
        //      out.append(maps[i]);
        //      out.append("<br/>");
        //  }
        // System.out.println("===============");
        // out.append("<html>");
        for (int i = 0; i < size_m; i++) {
            for (int j = 0; j < size_n; j++) {
                label[i][j].setText(String.valueOf(maps[i][j]));
            }
            //  jlabel.setBounds(5, 20, 10, 20);
            // jlabel.setOpaque(true);
            // jlabel.setText(String.valueOf(maps[0]));
            //  Jpanel.add(jlabel);

        }
    }

    void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}