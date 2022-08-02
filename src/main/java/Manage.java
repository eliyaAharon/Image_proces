import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

                String s = "https://instagram.ftlv19-1.fna.fbcdn.net/v/t51.2885-15/295795062_623287322557953_4457508725246154812_n.jpg?stp=dst-jpg_e35_p1080x1080&cb=2d435ae8-ef10543b&_nc_ht=instagram.ftlv19-1.fna.fbcdn.net&_nc_cat=109&_nc_ohc=RlUPBz-l3NkAX_INxbX&edm=ALQROFkBAAAA&ccb=7-5&ig_cache_key=Mjg5NDY4NDU3Njg1MTIwMTA0MA%3D%3D.2-ccb7-5&oh=00_AT-HYAX4TL79thWZMIevQA2Z6mYeld9mwaDv0Nmu2whYPQ&oe=62F13BBB&_nc_sid=30a2ef";
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
                createCustomButton(filter1, this.yOfButtons, "Red filter");
                this.add(filter1);

                // set the second filter button
                filter2 = new JButton();
                createCustomButton(filter2, this.yOfButtons + 90, "Black & White");
                this.add(filter2);

                // set the third filter button
                filter3 = new JButton();
                createCustomButton(filter3, filter2.getY() + 90, "Inverted color");
                this.add(filter3);

                // set the fourth filter button
                filter4 = new JButton();
                createCustomButton(filter4, filter3.getY() + 90, "Mirror 🤬 ");
                this.add(filter4);

                // set the fifth filter button
                filter5 = new JButton();
                createCustomButton(filter5, filter4.getY() + 90, "Golden warm");
                this.add(filter5);

                // set the sixth filter button
                filter6 = new JButton();
                createCustomButton(filter6, filter5.getY() + 90, "Original");
                this.add(filter6);
                paint(getGraphics());

                // buttons effects (all of them )
                filter1.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    red(afterFilter);
                    paint(getGraphics());
                }));

                filter2.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 1);
                    paint(getGraphics());
                }));

                filter3.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 2);
                    paint(getGraphics());
                }));


                filter4.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 3);
                    paint(getGraphics());
                }));

                filter5.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 4);
                    paint(getGraphics());
                }));

                filter6.addActionListener((e1 -> {
                    afterFilter = resize(firstBuffer, new Dimension(350, 500));
                    paint(getGraphics());
                }));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));

    }

    public void red(BufferedImage b) {
        for (double x = 0.3; x < b.getWidth(); x++) {
            for (double y = 0.3; y < b.getHeight(); y+=2) {
                double z = Color.RED.getRGB();
                b.setRGB((int) x, (int) y, (int) z);
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


    public static void colorsChange(BufferedImage b, int n) {

        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                int pixel = b.getRGB(x, y);

                switch (n) {
                    case 1:
                        Color color = new Color(pixel);
                        Color newColor = blackAndWhite(color);
                        b.setRGB(x, y, newColor.getRGB());
                        break;
                    case 2:
                        Color color2 = new Color(pixel);
                        Color newColor2 = reverseImgColor(color2);
                        b.setRGB(x, y, newColor2.getRGB());
                        break;
                    case 3 :
                        Color color3 = new Color(pixel);
                        afterFilter.setRGB(b.getWidth() -x -1 , y,color3.getRGB());
                        break;
                    case 4 :
                        Color color4 = new Color(pixel);
                        Color newColor4 = warm(color4);
                        b.setRGB(x, y, newColor4.getRGB());


                }
            }
        }
    }


    public static Color blackAndWhite(Color color) {
        int red = color.getRed();
        red = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        int green = color.getGreen();
        green = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        int blue = color.getBlue();
        blue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        Color blackWhite = new Color(red, green, blue);
        return blackWhite;
    }

    public static Color reverseImgColor(Color color) {

        int red = color.getRed();
        red = 255 - red;

        int green = color.getGreen();
        green = 255 - green;

        int blue = color.getBlue();
        blue = 255 - blue;

        Color newColor = new Color(red, green, blue);
        return newColor;
    }
    public static Color warm (Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int outputRed = (int)((red * 0.393) + (green *0.769) + (blue * 0.189));
        int outputGreen = (int)((red * 0.349) + (green *0.686) + (blue * 0.168));
        int outputBlue = (int)((red * 0.272) + (green *0.534) + (blue * 0.131));

        Color warmFilter = new Color(topColor(outputRed,1),topColor(outputGreen,1),topColor(outputBlue,1));
        return warmFilter;
    }
    public static int topColor (int original, double timesBy) {
        original *= timesBy;
        if (original > 255) {
            original = 255;
        }
        return original;
    }


}



