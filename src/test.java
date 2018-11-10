import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test extends JFrame implements KeyListener {
    carton carton_1 = null;//声明一个carton类的实例.

    public static void main(String[] args) {
        test one = new test();
        one.start();
    }

    public void start() {
        if (carton_1 == null)//假如未初始化
        {
            this.addKeyListener(this);
            this.setSize(400, 300);
            this.setVisible(true);
            carton_1 = new carton(getGraphics(), this);//初始化
            carton_1.start();//启动
        }
    }


    //键被按下事件的处理.
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w')//假如是向上键被按下
            carton_1.set_direction(0);//向上的布尔变量置true
        if (e.getKeyChar() == 's')//假如是向下键被按下
            carton_1.set_direction(1);//向下的布尔变量置true
        if (e.getKeyChar() == 'a')//假如是向左键被按下
            carton_1.set_direction(2);//向左的布尔变量置true
        if (e.getKeyChar() == 'd')//假如是向右键被按下
            carton_1.set_direction(3);//向右的布尔变量置true
    }

    //键被松开事件的处理.
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w')//假如是向上键被松开
            carton_1.clear_direction(0);//向上的布尔变量置false
        if (e.getKeyChar() == 's')//假如是向下键被松开
            carton_1.clear_direction(1);//向下的布尔变量置false
        if (e.getKeyChar() == 'a')//假如是向左键被松开
            carton_1.clear_direction(2);//向左的布尔变量置false
        if (e.getKeyChar() == 'd')//假如是向右键被松开
            carton_1.clear_direction(3);//向右的布尔变量置false
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

//小方块所属类的描述
class carton extends Thread//从Thread类派生
{
    boolean up = false, down = false, left = false, right = false;//四个表示方向的布尔变量
    int position_x = 200, position_y = 150;//坐标.初始位置是(200,150)
    JFrame applet;
    Graphics g;
    int sleep_time = 10;//睡眠时间

    Color my_color;

    //初始化时要将各种环境准备好
    public carton(Graphics a, JFrame app) {
        g = a;
        applet = app;
    }

    //这是主运行函数.
    public void run() {
        while (true)//此函数一直运行
        {
            set_position();//计算小方块坐标

            draw_image();//画小方块

            try//标准的暂停方式
            {
                sleep(sleep_time);
            } catch (InterruptedException e) {
            }
        }
    }


    //计算小方块的坐标
    public void set_position() {
        if (up == true && position_y > 1)//假如向上的键被按下并且小方块的纵坐标大于1
            position_y--;//小方块向上移动
        if (down == true && position_y < 330)//假如向下的键被按下并且小方块的纵坐标小于330
            position_y++;//小方块向下移动
        if (left == true && position_x > 1)//假如向左的键被按下并且小方块的横坐标大于1
            position_x--;//小方块向左移动
        if (right == true && position_x < 380)//假如向右的键被按下并且小方块的横坐标小于380
            position_x++;//小方块向右移动
    }

    //画屏幕
    public void draw_image() {

        my_color = new Color(179, 179, 179);
        g.setColor(my_color);
        g.fillRect(0, 0, 400, 350);//画背景

        my_color = new Color(100, 100, 100);
        g.setColor(my_color);
        g.fillRect(position_x, position_y, 20, 20);//根据小方块的坐标画小方块
    }

    //表示方向的布尔变量的置true方法
    public void set_direction(int direction_) {
        switch (direction_) {
            case (0)://向上键被按下则向上的布尔变量置位
                up = true;
                break;
            case (1)://向下键被按下则向下的布尔变量置位
                down = true;
                break;
            case (2)://向左键被按下则向左的布尔变量置位
                left = true;
                break;
            case (3)://向右键被按下则向右的布尔变量置位
                right = true;
                break;
            default:
                break;
        }
    }

    //表示方向的布尔变量的置false方法
    public void clear_direction(int direction_) {
        switch (direction_) {
            case (0)://向上键被松开则向上的布尔变量置false
                up = false;
                break;
            case (1)://向下键被松开则向下的布尔变量置false
                down = false;
                break;
            case (2)://向左键被松开则向左的布尔变量置false
                left = false;
                break;
            case (3)://向右键被松开则向右的布尔变量置false
                right = false;
                break;
            default:
                break;
        }
    }
}

// 下面是HTML文件
//    <APPLET CODE="wumin1.class"WIDTH=400 HEIGHT=370></APPLET>

