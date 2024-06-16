package monster_game;

import static jsTools.Graph.*;

public class World {
    public static void initializeWindow(int windowWidth, int groundYPosition, int grassHeight, int earthHeight, int skyYPosition) {
        initWindow(); // Fenster initialisieren


        // Boden zeichnen, angepasst an das Fenster
        addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
        addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde
        addRect(0,0,windowWidth, skyYPosition, cyan );

    }
}
