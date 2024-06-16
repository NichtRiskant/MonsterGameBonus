package monster_game;

import static jsTools.Graph.*;

public class Drawing {

    // Methode zur Anzeige des Levels
    public static void drawLevel(int level) {
        String levelText = "Level " + level;
        int xStart = 10;
        int yStart = 10;
        int rectSize = 5;
        int spacing = 2;

        for (char c : levelText.toCharArray()) {
            drawCharacter(c, xStart, yStart, rectSize, spacing);
            xStart += (rectSize + spacing) * 6; // Anpassung der Startposition für das nächste Zeichen
        }
    }

    public static void drawCharacter(char c, int xStart, int yStart, int rectSize, int spacing) {
        // Muster für die Zeichen (einfaches Beispiel für L, e, v, l und Ziffern)
        int[][] L = {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
        };
        int[][] E = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
        };
        int[][] V = {
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
        };
        int[][] L2 = L; // Wiederverwendung des L-Musters

        int[][] num1 = {
                {0, 1, 0},
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1},
        };
        int[][] num2 = {
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 1},
        };
        int[][] num3 = {
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
        };

        int[][] pattern;

        switch (c) {
            case 'L': pattern = L; break;
            case 'e': pattern = E; break;
            case 'v': pattern = V; break;
            case 'l': pattern = L2; break;
            case '1': pattern = num1; break;
            case '2': pattern = num2; break;
            case '3': pattern = num3; break;
            default: return;
        }

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] == 1) {
                    addRect(xStart + j * (rectSize + spacing), yStart + i * (rectSize + spacing), rectSize, rectSize, black);
                }
            }
        }
    }

    public static void overpaintMonster(Monster monster) {
        // Monster übermalen
        if (monster instanceof SimpleMonster) {
            new SimpleMonster(monster.name, monster.health, monster.xPos, monster.yPos) {
                @Override
                public void paint() {
                    // Übermalen mit Weiß
                    addRect(xPos , yPos +40, 20, 100, white);   // linkes Bein
                    addRect(xPos + 60, yPos +40, 20, 100, white);  // rechtes Bein
                    addRect(xPos, yPos -20, 80, 96, white);  // Körper
                    addRect(xPos + 80, yPos + 20, 24, 80, white);  // linker Arm
                    addRect(xPos - 80, yPos + 20, 80, 24, white);  // rechter Arm
                    addCircle(xPos + 13, yPos - 60, 40, white);  // Kopf
                    addRect(xPos -90, yPos -60, 10, 110, white);  // Schwertklinge
                    addRect(xPos -100, yPos +10, 30, 10, white);  // Schwertgriff
                }
            }.paint();
        } else if (monster instanceof AdvancedMonster) {
            new AdvancedMonster(monster.name, monster.health, monster.xPos, monster.yPos) {
                @Override
                public void paint() {
                    // Übermalen mit Weiß
                    addRect(xPos , yPos +40, 20, 100, white);   // linkes Bein
                    addRect(xPos + 60, yPos +40, 20, 100, white);  // rechtes Bein
                    addRect(xPos, yPos -20, 80, 96, white);  // Körper
                    addRect(xPos + 80, yPos + 20, 80, 24, white);  // linker Arm
                    addRect(xPos - 80, yPos + 20, 80, 24, white);  // rechter Arm
                    addCircle(xPos + 13, yPos - 60, 40, white);  // Kopf
                    addRect(xPos -90, yPos -60, 10, 110, white);  // Schwertklinge
                    addRect(xPos -100, yPos +10, 30, 10, white);  // Schwertgriff
                    addRect(xPos +160, yPos -60, 10, 110, white);  // Schwertklinge rechts
                    addRect(xPos +150, yPos +10, 30, 10, white);  // Schwertgriff rechts
                }
            }.paint();
        }
    }

    public static void overpaintStudent(Student student) {
        // Student übermalen
        addRect(student.xPos + 5, student.yPos + 40, 10, 40, white);   // linkes Bein
        addRect(student.xPos + 25, student.yPos + 40, 10, 40, white);  // rechtes Bein
        addRect(student.xPos, student.yPos, 40, 40, white);            // Körper
        addRect(student.xPos - 10, student.yPos + 10, 10, 35, white);  // linker Arm
        addRect(student.xPos + 40, student.yPos + 10, 35, 10, white);  // rechter Arm
        addCircle(student.xPos + 13, student.yPos - 20, 20, white);    // Kopf
        addRect(student.xPos + 70, student.yPos + -28, 5, 55, white);  // Schwertklinge
        addRect(student.xPos + 65, student.yPos + 5, 15, 5, white);    // Schwertgriff
    }
}
