import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Window window = new Window();
    }

    public Window() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // frame setting
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Image process ❤ ");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);

        Manage m = new Manage(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        this.add(m);

    }


}
