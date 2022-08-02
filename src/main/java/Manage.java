import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Manage extends JPanel {
    // fields
    private static ChromeDriver driver;
    private static int x = 350, y = 100, width = 200, height = 50;
    private static BufferedImage bufferedImage;
    private static BufferedImage b;
    private static BufferedImage b1;


    // My constructor
    public Manage(int x, int y, int width, int height, JFrame frame) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        // My font
        Font myFont = new Font("Arial", Font.BOLD, 20);

        // textField setting
        JTextField name = new JTextField();
        name.setBounds(this.x, this.y, this.width, this.height);
        name.setFont(myFont);
        this.add(name);
        frame.setVisible(true);

        // button setting
        Font buttonFont = new Font("Arial", Font.BOLD, 17);
        JButton nameButton = new JButton("search ");
        nameButton.setFont(buttonFont);
        nameButton.setBackground(Color.YELLOW);
        nameButton.setBounds(this.x + this.width, this.y, this.width - 80, this.height);
        this.add(nameButton);

        // button affect
        nameButton.addActionListener((e -> {
            try {
                String s = "https://dalicanvas.co.il/wp-content/uploads/2020/02/%D7%A9%D7%A7%D7%99%D7%A2%D7%94-%D7%A7%D7%9C%D7%90%D7%A1%D7%99%D7%AA-1.jpg";
                URL url = new URL(s);

                b = ImageIO.read(url);
                bufferedImage = resize(b, new Dimension(350, 500));
                b1 = resize(b, new Dimension(350, 500));
                JLabel l = new JLabel(new ImageIcon(b1));
                System.out.println("i");
                l.setBounds(40, 50, 350, 500);
                this.add(l);
                paint(getGraphics());
//                Thread.sleep(3000);
//                blue(bufferedImage);
//                paint(getGraphics());
//                Thread.sleep(3000);
//                red(bufferedImage);
//                paint(getGraphics());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));


    }

    public void blue(BufferedImage b) {
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                int z = Color.green.getRGB();
                b.setRGB(x, y, z);
            }
        }
    }

    public void red(BufferedImage b) {
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                int z = Color.RED.getRGB();
                b.setRGB(x, y, z);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bufferedImage, 700, 50, null);
    }

    public BufferedImage resize(BufferedImage image, final Dimension size) {
        final BufferedImage resized = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = resized.createGraphics();
        g.drawImage(image, 0, 0, size.width, size.height, null);
        g.dispose();
        return resized;
    }


}
