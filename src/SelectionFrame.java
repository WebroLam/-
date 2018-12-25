import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class SelectionFrame extends JFrame {
    Operator Operator;

    SelectionFrame() {
        super("地图选择");
        initJFrame();
        selectMap();
    }

    SelectionFrame(String title) {
        super(title);
        initJFrame();
    }

    void selectHero() {
        if (Operator == null) {
            Operator = new Operator(4);
            Operator.initHero(1, true, 'A', 22, 30, 20, 10, 0);
            Operator.initHero(2, true, 'B', 22, 30, 20, 11, 1);

            Operator.initHero(3, false, 'C', 22, 30, 20, 0, 10);
            Operator.initHero(4, false, 'D', 11, 22, 20, 1, 11);
            Operator.startHero();
        }
    }

    /**
     * 初始化回放英雄
     */
    public BufferedReader initReviewHero() throws IOException {
        Reader rd = null;
        try {
            rd = new FileReader("test_1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(rd);
        String str;

        int heroNum = Integer.valueOf(reader.readLine());
        if (Operator == null) {
            Operator = new Operator(heroNum);

            for (int i = 0; i < heroNum; i++) {
                boolean camp;
                char name;
                int n, atk, hp, ex, x, y;
                str = reader.readLine();
                String[] strSub = str.split(" ");

                n = Integer.valueOf(strSub[0]);
                if (strSub[1] == "true") camp = true;
                else camp = false;
                name = strSub[2].charAt(0);
                atk = Integer.valueOf(strSub[3]);
                hp = Integer.valueOf(strSub[4]);
                ex = Integer.valueOf(strSub[5]);
                x = Integer.valueOf(strSub[6]);
                y = Integer.valueOf(strSub[7]);

                Operator.initHero(n, camp, name, atk, hp, ex, x, y);

            }
        }
        return reader;
    }

    private void initJFrame() {
        setSize(300, 300);
        setLocation(50, 35);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initButton() {
        setTitle("英雄选择");
        setLayout(new GridLayout(3, 2));
        addButton("英雄A");
        addButton("英雄B");
        addButton("英雄C");
        addButton("英雄D");


        JButton startButton = addButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero.GameMode = "Player";
                selectHero();
            }
        });
        startButton.setIcon(new ImageIcon("开始游戏按钮.png"));

        JButton reviewButton = addButton("回放");
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Hero.GameMode = "Review";
                    BufferedReader reader = initReviewHero();
                    new Review(Operator).loading(reader);
                    MyWrite.startTime = System.currentTimeMillis();
                    Operator.startHero();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    private void selectMap() {
        setLayout(new GridLayout(3, 2));


        JButton threeLineButton = addButton("三线地图");
        JButton oneLineButton = addButton("单线地图");


        threeLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.maps = new char[][]{
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '*', '*', '*', '*', '*', '*', '.', '.', '.', '.'},
                        {'.', '.', '*', '.', '.', '.', '*', '.', '.', '.', '.', '.'},
                        {'.', '.', '*', '.', '.', '*', '.', '.', '.', '*', '.', '.'},
                        {'.', '.', '*', '.', '*', '.', '.', '.', '*', '*', '.', '.'},
                        {'.', '.', '*', '*', '.', '.', '.', '*', '.', '*', '.', '.'},
                        {'.', '.', '*', '.', '.', '.', '*', '.', '.', '*', '.', '.'},
                        {'.', '.', '.', '.', '.', '*', '.', '.', '.', '*', '.', '.'},
                        {'.', '.', '.', '.', '*', '*', '*', '*', '*', '*', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                };
                initButton();
                remove(threeLineButton);
                remove(oneLineButton);
            }
        });


        oneLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.maps = new char[][]{
                        {'.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.', '.'},
                        {'.', '.', '*', '*', '*', '*', '*', '*', '.', '.', '.', '*'},
                        {'.', '.', '*', '.', '.', '.', '*', '.', '.', '.', '*', '*'},
                        {'.', '.', '*', '.', '.', '*', '.', '.', '.', '*', '*', '.'},
                        {'.', '.', '*', '.', '*', '.', '.', '.', '*', '*', '.', '.'},
                        {'.', '.', '*', '*', '.', '.', '.', '*', '.', '*', '.', '.'},
                        {'.', '*', '*', '.', '.', '.', '*', '.', '.', '*', '.', '.'},
                        {'*', '*', '.', '.', '.', '*', '.', '.', '.', '*', '.', '.'},
                        {'*', '.', '.', '.', '*', '*', '*', '*', '*', '*', '.', '.'},
                        {'.', '.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '.'},
                };
                initButton();
                remove(threeLineButton);
                remove(oneLineButton);
            }
        });
    }

    private File selectReviewFile() {
        int i = 1;
        File file;
        do {
            file = new File("test_" + i + ".txt");
            i++;
        }
        while (file.exists());

        //SelectionFrame reviewFrame = new SelectionFrame("选择播放文件");
        //reviewFrame.setLayout(new GridLayout(3, 2));
        return file;
    }

    private JButton addButton(String buttonName) {
        JButton jb = new JButton();//实例化一个按钮
        jb.setText(buttonName);
        getContentPane().add(jb);
        return jb;
    }
}