package monster_game;

import static jsTools.Graph.*;

public class Window {
    public static void initializeWindow(int windowWidth, int groundYPosition, int grassHeight, int earthHeight) {
        initWindow(); // Fenster initialisieren

        // Boden zeichnen, angepasst an das Fenster
        addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
        addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde
    }
}
