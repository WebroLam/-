import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ��ͼ��
 * ���ڱ����ͼ����
 * �����ͼ
 */
class Map extends JFrame implements Runnable {
    public static int size_m = 12;
    public static int size_n = 12;
    public boolean flg = true;
    private MyPanel Jpanel = new MyPanel();
    JLabel label[][] = new JLabel[size_m][size_n];
    char[][] maps;
    Hero hero[];

    Map(Hero hero[]) {
        super("������ҫ");
        this.hero = hero;
        initJFrame();
        addBar();
        addkeyListener();
        initMaps();
        initJLabel();
        //        Graphics g = this.getGraphics();
//        Image img = ImageIO.read(new File("wxfix.png"));
//        g.drawImage(img, 30, 80, null);
    }

    /**
     * ��ʼ����ͼ����
     */
    private void initMaps() {
        maps = new char[size_m][size_n];
        for (int i = 0; i < size_m; i++)
            for (int j = 0; j < size_n; j++)
                maps[i][j] = '.';
        for (int i = 2; i < 5; i++)
            maps[3][i] = '*';
    }

    /**
     * ��ʼ��JFrame
     */
    private void initJFrame() {
        setSize(size_n * 50, size_m * 50);
        setLocation(500, 350);
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * �����Ϸ��˵���
     * �˵���ѡ�-������ʼ��Ϸ������������Ϸ��
     * �����ڡ�-����Webro��
     */
    private void addBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu jm_Source = new JMenu("ѡ��");//�˵�һ
        JMenuItem jmi_Start = new JMenuItem("��ʼ��Ϸ");//�Ӳ˵�һ
        jmi_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("23asd23asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd");
            }
        });
        JMenuItem jmi_End = new JMenuItem("������Ϸ");//�Ӳ˵���
        jmi_End.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jm_Source.add(jmi_Start);
        jm_Source.add(jmi_End);


        JMenu jm_About = new JMenu("����");//�˵���
        JMenuItem jmi_Author = new JMenuItem("Webro");//�Ӳ˵�һ
        jm_About.add(jmi_Author);


        menuBar.add(jm_Source);
        menuBar.add(jm_About);
    }

    /**
     * ��ʼ��JLabel
     */
    private void initJLabel() {
        for (int i = 0; i < size_m; i++) {
            for (int j = 0; j < size_n; j++) {
                label[i][j] = new JLabel(String.valueOf(maps[i][j]));
                label[i][j].setFont(new Font("Consolas", Font.PLAIN, 32));
                label[i][j].setBounds(50 * j, i * 50, 50, 50);//����labelλ��,����һ��Ҫ���ã���Ȼ������label
                label[i][j].setOpaque(true);//���ò�͸��
                Jpanel.add(label[i][j]);
            }
            //  jlabel.setBounds(5, 20, 10, 20);
            // jlabel.setOpaque(true);
            // jlabel.setText(String.valueOf(maps[0]));
            //  Jpanel.add(jlabel);
            setContentPane(Jpanel);
        }
    }

    @Override
    public void run() {
        while (flg) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Jpanel.repaint();
            //printToWindow();
            print();
        }
    }

    /**
     * ������
     */
    void mouseListener() {
        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
    }

    void addkeyListener() {
        int heroNum = Operator.nowHero;

        KeyAdapter key = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyChar()) {
                    case 'w':
                    case 'W':
                        hero[heroNum].move('u', 1);
                        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                        break;
                    case 's':
                    case 'S':
                        hero[heroNum].move('d', 1);
                        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                        break;
                    case 'A':
                    case 'a':
                        hero[heroNum].move('l', 1);
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        break;
                    case 'D':
                    case 'd':
                        hero[heroNum].move('r', 1);
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                        break;
                }
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
     * ��ӡ��ͼ������̨
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
     * ��ӡ��ͼ������
     */
    public void printToWindow() {
        StringBuilder out = new StringBuilder();
        out.append("<html>");
        for (int i = 0; i < size_m; i++) {
            out.append(maps[i]);
            out.append("<br/>");
        }
        // System.out.println("===============");
        out.append("<html>");
        //jlabel.setText(out.toString());
    }

    void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}