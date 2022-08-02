import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Window {
    private static JFrame myFrame;
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window() {
        // frame setting
        myFrame = new JFrame();
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setTitle("Image process ❤️");
        myFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        myFrame.setLocationRelativeTo(null);
        myFrame.setBackground(Color.BLACK);

        Manage m = new Manage(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, myFrame);
        myFrame.add(m);

    }


}
