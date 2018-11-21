import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {
    int x = 20;
    int y = 30;

    @Override
    public void paint(Graphics g) {
        try {
            Image img = ImageIO.read(new File("wxfix.png"));
         //   g.clearRect(0, 0, 2000, 2000);
           // g.drawImage(img, x, y, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x += 20;
        y += 20;
    }
}