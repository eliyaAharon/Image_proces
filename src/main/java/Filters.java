import java.awt.*;

public class Filters {

    // class of filters methods

    // blackAndWhite -->
    public Color blackAndWhite(Color color) {
        int red = color.getRed();
        red = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        int green = color.getGreen();
        green = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        int blue = color.getBlue();
        blue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        Color blackWhite = new Color(red, green, blue);
        return blackWhite;
    }

    // colorShiftLeft -->
    public Color colorShiftLeft(Color color) {

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        Color newColor = new Color(blue, red, green);
        return newColor;
    }

    //  brighter -->
    public Color brighter(Color color) {

        int red = color.getRed();
        if (red < 210) {
            red = red + 45;
        }

        int green = color.getGreen();
        if (green < 210) {
            green = green + 45;
        }

        int blue = color.getBlue();
        if (blue < 210) {
            blue = blue + 45;
        }

        Color newColor = new Color(red, green, blue);
        return newColor;
    }

    // warm -->
    public Color warm(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int outputRed = (int) ((red * 0.393) + (green * 0.769) + (blue * 0.189));
        int outputGreen = (int) ((red * 0.349) + (green * 0.686) + (blue * 0.168));
        int outputBlue = (int) ((red * 0.272) + (green * 0.534) + (blue * 0.131));

        Color warmFilter = new Color(topColor(outputRed, 1), topColor(outputGreen, 1), topColor(outputBlue, 1));
        return warmFilter;
    }

    //  topColor help method -->
    public static int topColor(int original, double timesBy) {
        original *= timesBy;
        if (original > 255) {
            original = 255;
        }
        return original;
    }

    // reverseImgColor -->
    public Color reverseImgColor(Color color) {

        int red = color.getRed();
        red = 255 - red;

        int green = color.getGreen();
        green = 255 - green;

        int blue = color.getBlue();
        blue = 255 - blue;

        Color newColor = new Color(red, green, blue);
        return newColor;

    }
}
