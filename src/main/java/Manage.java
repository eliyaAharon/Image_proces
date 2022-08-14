import com.google.common.util.concurrent.AtomicDouble;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Manage extends JPanel {
    // fields
    private final static int x = 395, y = 20, width = 175, height = 50, dim = 400;
    private static ChromeDriver driver;
    private static BufferedImage afterFilter, firstBuffer, originalImage;
    private static final int yOfButtons = 90;
    private static JButton[] buttons;
    private static Filters filters;
    private static VisualHelpClass visualHelpClass;
    private FirstAudio firstAudio;
    private static final String audio1 = "פייסבוק פתיח.wav", audio2 = "פיססבוק סיום.wav";

    // My constructor
    public Manage(int x, int y, int width, int height, JFrame frame) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        firstAudio = new FirstAudio(audio1);
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
                firstAudio.stopAudio();
                // facebook interaction
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://he-il.facebook.com/" + name.getText());
                driver.manage().window().maximize();
                String imageUrl;

                java.util.List<WebElement> imgClass = driver.findElements(By.tagName("image"));
                WebElement infoLink = imgClass.get(0);
                imageUrl = infoLink.getAttribute("xlink:href");
                driver.close();
                String s = imageUrl;
                URL url = new URL(s);
                // set the buffers && filters object
                filters = new Filters();
                visualHelpClass = new VisualHelpClass();
                firstBuffer = ImageIO.read(url);
                afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                originalImage = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                JLabel l = new JLabel(new ImageIcon(originalImage));
                l.setBounds(30, 110, dim, dim);
                this.add(l);
                // set all the buttons filters :
                buttons = new JButton[6];
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new JButton();
                    this.add(buttons[i]);
                }
                visualHelpClass.createCustomButton(buttons[0], this.yOfButtons, "Color shift");
                visualHelpClass.createCustomButton(buttons[1], this.yOfButtons + 90, "Black & White");
                visualHelpClass.createCustomButton(buttons[2], buttons[1].getY() + 90, "Inverted color");
                visualHelpClass.createCustomButton(buttons[3], buttons[2].getY() + 90, "Cool mirror ");
                visualHelpClass.createCustomButton(buttons[4], buttons[3].getY() + 90, "Golden warm");
                visualHelpClass.createCustomButton(buttons[5], buttons[4].getY() + 90, "Brighten");
                paint(getGraphics());
                firstAudio = new FirstAudio(audio2);

                // labels before && after
                JLabel beforeLab = new JLabel("Before ");
                JLabel afterLab = new JLabel("After ");
                visualHelpClass.createCustomLabel(beforeLab, 120);
                visualHelpClass.createCustomLabel(afterLab, 780);
                this.add(beforeLab);
                this.add(afterLab);
                // Button effects (all of them)
                buttons[0].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                    colorsChange(afterFilter, 6);
                    paint(getGraphics());
                }));

                buttons[1].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                    colorsChange(afterFilter, 1);
                    paint(getGraphics());
                }));

                buttons[2].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                    colorsChange(afterFilter, 2);
                    paint(getGraphics());
                }));

                buttons[3].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                    colorsChange(afterFilter, 3);
                    paint(getGraphics());
                }));

                buttons[4].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
                    colorsChange(afterFilter, 4);
                    paint(getGraphics());
                }));

                buttons[5].addActionListener((e1 -> {
                    afterFilter = visualHelpClass.resize(firstBuffer, new Dimension(dim, dim));
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
        g.drawImage(afterFilter, 660, 110, null);
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



