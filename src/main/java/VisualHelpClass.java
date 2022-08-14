import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VisualHelpClass {

    // create custom button
    public void createCustomButton(JButton button, int y, String tittle) {
        Font buttonFont = new Font("Arial", Font.BOLD, 17);
        button.setBackground(Color.yellow);
        button.setBounds(460, y, 175, 50);
        button.setText(tittle);
        button.setFont(buttonFont);
    }

    // Resize the bufferImage
    public BufferedImage resize(BufferedImage image, final Dimension size) {
        final BufferedImage resized = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = resized.createGraphics();
        g.drawImage(image, 0, 0, size.width, size.height, null);
        g.dispose();
        return resized;
    }

    // create labels method
    public void createCustomLabel(JLabel label, int x) {
        Font myFont = new Font("Monospaced", Font.ITALIC, 42);
        label.setFont(myFont);
        label.setBounds(x , 30 , 300 , 50);
        label.setForeground(Color.ORANGE.darker());
        label.setOpaque(true);
    }
}
