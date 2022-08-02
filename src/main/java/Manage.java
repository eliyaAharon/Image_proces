import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Manage extends JPanel {
    // fields
    private static ChromeDriver driver;
    private static int x = 395, y = 20, width = 175, height = 50;
    private static BufferedImage afterFilter;
    private static BufferedImage firstBuffer;
    private static BufferedImage originalImage;
    private static final int yOfButtons = 90;
    private static JButton filter1, filter2, filter3, filter4, filter5, filter6;


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

        // Main button setting
        Font buttonFont = new Font("Arial", Font.BOLD, 17);
        JButton nameButton = new JButton("search ");
        nameButton.setFont(buttonFont);
        nameButton.setBackground(Color.YELLOW);
        nameButton.setBounds(this.x + this.width, this.y, this.width - 80, this.height);
        this.add(nameButton);

        // Main button affect
        nameButton.addActionListener((e -> {
            try {
                String s = "https://dalicanvas.co.il/wp-content/uploads/2020/02/%D7%A9%D7%A7%D7%99%D7%A2%D7%94-%D7%A7%D7%9C%D7%90%D7%A1%D7%99%D7%AA-1.jpg";
                URL url = new URL(s);
                // set the buffers
                firstBuffer = ImageIO.read(url);
                afterFilter = resize(firstBuffer, new Dimension(350, 500));
                originalImage = resize(firstBuffer, new Dimension(350, 500));
                JLabel l = new JLabel(new ImageIcon(originalImage));
                System.out.println("i");
                l.setBounds(40, 50, 350, 500);
                this.add(l);
                // set the first filter button
                filter1 = new JButton();
                createCustomButton(filter1, this.yOfButtons, "first filter");
                this.add(filter1);

                // set the second filter button
                filter2 = new JButton();
                createCustomButton(filter2, this.yOfButtons + 90, "second filter");
                this.add(filter2);

                // set the third filter button
                filter3 = new JButton();
                createCustomButton(filter3, filter2.getY() + 90, "third filter");
                this.add(filter3);

                // set the fourth filter button
                filter4 = new JButton();
                createCustomButton(filter4, filter3.getY() + 90, "forth filter");
                this.add(filter4);

                // set the fifth filter button
                filter5 = new JButton();
                createCustomButton(filter5, filter4.getY() + 90, "fifth filter");
                this.add(filter5);

                // set the sixth filter button
                filter6 = new JButton();
                createCustomButton(filter6, filter5.getY() + 90, " sixth  filter");
                this.add(filter6);
                paint(getGraphics());

                // buttons affects (all of them )
                filter1.addActionListener((e1 -> {
                    red(afterFilter);
                    paint(getGraphics());
                }));

                filter2.addActionListener((e1 -> {

                }));

                filter3.addActionListener((e1 -> {

                }));

                filter4.addActionListener((e1 -> {

                }));

                filter5.addActionListener((e1 -> {

                }));

                filter6.addActionListener((e1 -> {

                }));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));

    }

    public void red(BufferedImage b) {
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                int z = Color.RED.getRGB();
                b.setRGB(x, y, z);
            }
        }
    }

    // paint method
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(afterFilter, 700, 50, null);
    }

    // Resize the bufferImage
    public BufferedImage resize(BufferedImage image, final Dimension size) {
        final BufferedImage resized = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = resized.createGraphics();
        g.drawImage(image, 0, 0, size.width, size.height, null);
        g.dispose();
        return resized;
    }

    // create custom button
    public static void createCustomButton(JButton button, int y, String tittle) {
        Font buttonFont = new Font("Arial", Font.BOLD, 17);
        button.setBackground(Color.YELLOW);
        button.setBounds(460, y, 175, 50);
        button.setText(tittle);
        button.setFont(buttonFont);
        }
    }



