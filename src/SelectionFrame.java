import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SelectionFrame extends JFrame {
    Operator Operator;

    SelectionFrame() {
        super("英雄选择");
        initJFrame();
        initButton();
    }

    void selectHero() {
        Operator = new Operator(4);
        Operator.initHero(1, true, 'A', 22, 30, 20, 0, 0);
        Operator.initHero(2, true, 'B', 22, 30, 20, 0, 2);


        Operator.initHero(3, false, 'C', 22, 30, 20, 11, 0);
        Operator.initHero(4, false, 'D', 11, 22, 20, 11, 6);
    }

    private void initJFrame() {
        setSize(300, 300);
        setLocation(50, 35);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initButton() {
        setLayout(new GridLayout(3, 2));
        addButton("英雄A");
        addButton("英雄B");
        addButton("英雄C");
        addButton("英雄D");


        JButton startButton = addButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectHero();
            }
        });
        startButton.setIcon(new ImageIcon("开始游戏按钮.png"));
    }

    private JButton addButton(String buttonName) {
        JButton jb = new JButton();//实例化一个按钮
        jb.setText(buttonName);
        getContentPane().add(jb);
        return jb;
    }
}