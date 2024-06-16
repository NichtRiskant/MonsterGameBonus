package monster_game;

import static jsTools.Graph.*;

public class CharacterDrawing {
    public static void drawCharacters(Student student, Monster simpleMonster, Monster advancedMonster) {
        student.paint();
        if (simpleMonster != null) {
            simpleMonster.paint();
        }
        if (advancedMonster != null) {
            advancedMonster.paint();
        }
    }
}
