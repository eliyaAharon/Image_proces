import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    private static int x = 395, y = 20, width = 175, height = 50;
    private static ChromeDriver driver;
    private static BufferedImage afterFilter, firstBuffer, originalImage;
    private static final int yOfButtons = 90;
    private static JButton filter1, filter2, filter3, filter4, filter5, filter6;
    private static Filters filters;
    private static VisualHelpClass visualHelpClass;

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
                // facebook interaction
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://web.facebook.com/" + name.getText());
                driver.manage().window().maximize();
                String imageUrl;

                if (driver.getPageSource().contains("//*[@id=\"content\"]/div/div/div/div[2]/div/div[1]") || driver.getPageSource().contains("//*[@id=\"content\"]/div/i")) {
                    imageUrl = "https://p0.pikist.com/photos/100/172/dark-night-sky-stars-galaxy-mountain-landscape-nature-water.jpg";
                    driver.close();
                } else {
                    java.util.List<WebElement> imgClass = driver.findElements(By.tagName("image"));
                    WebElement infoLink = imgClass.get(0);
                    imageUrl = infoLink.getAttribute("xlink:href");
                    driver.close();
                }

                String s = imageUrl;
                URL url = new URL(s);

                // set the buffers && filters object
                filters = new Filters();
                visualHelpClass = new VisualHelpClass();
                firstBuffer = ImageIO.read(url);
                afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                originalImage = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                JLabel l = new JLabel(new ImageIcon(originalImage));
                l.setBounds(40, 50, 350, 500);
                this.add(l);
                // set the first filter button
                filter1 = new JButton();
                visualHelpClass.createCustomButton(filter1, this.yOfButtons, "Color shift");
                this.add(filter1);

                // set the second filter button
                filter2 = new JButton();
                visualHelpClass.createCustomButton(filter2, this.yOfButtons + 90, "Black & White");
                this.add(filter2);

                // set the third filter button
                filter3 = new JButton();
                visualHelpClass.createCustomButton(filter3, filter2.getY() + 90, "Inverted color");
                this.add(filter3);

                // set the fourth filter button
                filter4 = new JButton();
                visualHelpClass.createCustomButton(filter4, filter3.getY() + 90, "Mirror 🤬 ");
                this.add(filter4);

                // set the fifth filter button
                filter5 = new JButton();
                visualHelpClass.createCustomButton(filter5, filter4.getY() + 90, "Golden warm");
                this.add(filter5);

                // set the sixth filter button
                filter6 = new JButton();
                visualHelpClass.createCustomButton(filter6, filter5.getY() + 90, "Brighten");
                this.add(filter6);
                paint(getGraphics());

                // buttons effects (all of them )
                filter1.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 6);
                    paint(getGraphics());
                }));

                filter2.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 1);
                    paint(getGraphics());
                }));

                filter3.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 2);
                    paint(getGraphics());
                }));

                filter4.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 3);
                    paint(getGraphics());
                }));

                filter5.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 4);
                    paint(getGraphics());
                }));

                filter6.addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(350, 500));
                    colorsChange(afterFilter, 5);
                    paint(getGraphics());
                }));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
    }

    // paint method
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(afterFilter, 700, 50, null);
    }

    // main image process method
    public static void colorsChange(BufferedImage b, int n) {
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                int pixel = b.getRGB(x, y);
                Color color = new Color(pixel);
                switch (n) {
                    case 1:
                        Color newColor = filters.blackAndWhite(color);
                        b.setRGB(x, y, newColor.getRGB());
                        break;
                    case 2:
                        Color newColor2 = filters.reverseImgColor(color);
                        b.setRGB(x, y, newColor2.getRGB());
                        break;
                    case 3:
                        b.setRGB(b.getWidth() - x - 1, y, color.getRGB());
                        break;
                    case 4:
                        Color newColor4 = filters.warm(color);
                        b.setRGB(x, y, newColor4.getRGB());
                        break;
                    case 5:
                        Color newColor5 = filters.brighter((color));
                        b.setRGB(x, y, newColor5.getRGB());
                        break;
                    case 6:
                        Color newColor6 = filters.colorShiftLeft(color);
                        b.setRGB(x, y, newColor6.getRGB());
                        break;

                }
            }
        }
    }

}



