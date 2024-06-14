package monster_game;

import static jsTools.Graph.*;

public class SimpleMonster extends Monster {
    public SimpleMonster(String name, int health, int xPos, int yPos) {
        super(name, health, xPos, yPos);
    }

    @Override
    public void paint() {
        addRect(xPos, yPos + 60, 10, 40, red); // linkes Bein
        addRect(xPos + 50, yPos + 60, 10, 40, red); // rechtes Bein
        addRect(xPos, yPos, 60, 60, orange); // Körper
        addRect(xPos - 10, yPos + 10, 10, 30, orange); // linker Arm
        addRect(xPos + 60, yPos + 10, 10, 30, orange); // rechter Arm
        addCircle(xPos + 25, yPos - 20, 20, orange); // Kopf
    }

    @Override
    public void attack(Fightable opponent) {
        System.out.println(name + " greift an!");
        try {
            ((Monster) opponent).reduceHealth(2);
        } catch (HealthException e) {
            System.out.println(e.getMessage());
        }
    }
}
