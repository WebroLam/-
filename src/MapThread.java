import java.awt.*;

public class MapThread extends Thread
{
    @Override
    public void run(){
        System.out.println("MyThread running");
        Panel one = new Panel();
        one.repaint();
    }
}