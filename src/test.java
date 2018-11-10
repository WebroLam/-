import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test extends JFrame implements KeyListener {
    carton carton_1 = null;//����һ��carton���ʵ��.

    public static void main(String[] args) {
        test one = new test();
        one.start();
    }

    public void start() {
        if (carton_1 == null)//����δ��ʼ��
        {
            this.addKeyListener(this);
            this.setSize(400, 300);
            this.setVisible(true);
            carton_1 = new carton(getGraphics(), this);//��ʼ��
            carton_1.start();//����
        }
    }


    //���������¼��Ĵ���.
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w')//���������ϼ�������
            carton_1.set_direction(0);//���ϵĲ���������true
        if (e.getKeyChar() == 's')//���������¼�������
            carton_1.set_direction(1);//���µĲ���������true
        if (e.getKeyChar() == 'a')//�����������������
            carton_1.set_direction(2);//����Ĳ���������true
        if (e.getKeyChar() == 'd')//���������Ҽ�������
            carton_1.set_direction(3);//���ҵĲ���������true
    }

    //�����ɿ��¼��Ĵ���.
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w')//���������ϼ����ɿ�
            carton_1.clear_direction(0);//���ϵĲ���������false
        if (e.getKeyChar() == 's')//���������¼����ɿ�
            carton_1.clear_direction(1);//���µĲ���������false
        if (e.getKeyChar() == 'a')//��������������ɿ�
            carton_1.clear_direction(2);//����Ĳ���������false
        if (e.getKeyChar() == 'd')//���������Ҽ����ɿ�
            carton_1.clear_direction(3);//���ҵĲ���������false
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

//С���������������
class carton extends Thread//��Thread������
{
    boolean up = false, down = false, left = false, right = false;//�ĸ���ʾ����Ĳ�������
    int position_x = 200, position_y = 150;//����.��ʼλ����(200,150)
    JFrame applet;
    Graphics g;
    int sleep_time = 10;//˯��ʱ��

    Color my_color;

    //��ʼ��ʱҪ�����ֻ���׼����
    public carton(Graphics a, JFrame app) {
        g = a;
        applet = app;
    }

    //���������к���.
    public void run() {
        while (true)//�˺���һֱ����
        {
            set_position();//����С��������

            draw_image();//��С����

            try//��׼����ͣ��ʽ
            {
                sleep(sleep_time);
            } catch (InterruptedException e) {
            }
        }
    }


    //����С���������
    public void set_position() {
        if (up == true && position_y > 1)//�������ϵļ������²���С��������������1
            position_y--;//С���������ƶ�
        if (down == true && position_y < 330)//�������µļ������²���С�����������С��330
            position_y++;//С���������ƶ�
        if (left == true && position_x > 1)//��������ļ������²���С����ĺ��������1
            position_x--;//С���������ƶ�
        if (right == true && position_x < 380)//�������ҵļ������²���С����ĺ�����С��380
            position_x++;//С���������ƶ�
    }

    //����Ļ
    public void draw_image() {

        my_color = new Color(179, 179, 179);
        g.setColor(my_color);
        g.fillRect(0, 0, 400, 350);//������

        my_color = new Color(100, 100, 100);
        g.setColor(my_color);
        g.fillRect(position_x, position_y, 20, 20);//����С��������껭С����
    }

    //��ʾ����Ĳ�����������true����
    public void set_direction(int direction_) {
        switch (direction_) {
            case (0)://���ϼ������������ϵĲ���������λ
                up = true;
                break;
            case (1)://���¼������������µĲ���������λ
                down = true;
                break;
            case (2)://�����������������Ĳ���������λ
                left = true;
                break;
            case (3)://���Ҽ������������ҵĲ���������λ
                right = true;
                break;
            default:
                break;
        }
    }

    //��ʾ����Ĳ�����������false����
    public void clear_direction(int direction_) {
        switch (direction_) {
            case (0)://���ϼ����ɿ������ϵĲ���������false
                up = false;
                break;
            case (1)://���¼����ɿ������µĲ���������false
                down = false;
                break;
            case (2)://��������ɿ�������Ĳ���������false
                left = false;
                break;
            case (3)://���Ҽ����ɿ������ҵĲ���������false
                right = false;
                break;
            default:
                break;
        }
    }
}

// ������HTML�ļ�
//    <APPLET CODE="wumin1.class"WIDTH=400 HEIGHT=370></APPLET>

