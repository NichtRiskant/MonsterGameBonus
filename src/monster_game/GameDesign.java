package monster_game;

import java.util.List;
import static jsTools.Graph.*;

public class GameDesign {

    public static void initializeWindow(int windowWidth, int groundYPosition, int grassHeight, int earthHeight, int skyYPosition) {
        initWindow(); // Fenster initialisieren

        // Boden zeichnen, angepasst an das Fenster
        addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
        addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde
        addRect(0, 0, windowWidth, skyYPosition, cyan); // Himmel
    }

    public static void drawCharacters(Student student, List<Monster> monsters) {
        student.paint();
        for (Monster monster : monsters) {
            if (monster != null) {
                monster.paint();
            }
        }
    }

    public static void clearAndRedrawWindow(int groundYPosition, int grassHeight, int windowWidth, int earthHeight, Student student, List<Monster> monsters, int choice) {
        clearWindow();
        addRect(0, 0, windowWidth, 100, cyan); // Himmel
        addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
        addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde
        student.paint();
        monsters.forEach(monster -> {
            if (monster != null && !monster.isDefeated()) {
                monster.paint();
            }
        });
        Drawing.drawLevel(choice);
    }
}
