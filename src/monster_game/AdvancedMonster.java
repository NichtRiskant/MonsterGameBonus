package monster_game;

import static jsTools.Graph.*;

public class AdvancedMonster extends Monster {
    public AdvancedMonster(String name, int health, int xPos, int yPos) {
        super(name, health, xPos, yPos);
    }

    @Override
    public void paint() {
        addRect(xPos, yPos + 60, 10, 40, green); // linkes Bein
        addRect(xPos + 50, yPos + 60, 10, 40, green); // rechtes Bein
        addRect(xPos + 25, yPos + 60, 10, 40, green); // mittleres Bein
        addRect(xPos, yPos, 60, 60, yellow); // Körper
        addRect(xPos - 10, yPos + 10, 10, 30, yellow); // linker Arm
        addRect(xPos + 60, yPos + 10, 10, 30, yellow); // rechter Arm
        addRect(xPos - 10, yPos + 30, 10, 30, yellow); // zweiter linker Arm
        addRect(xPos + 60, yPos + 30, 10, 30, yellow); // zweiter rechter Arm
        addCircle(xPos + 25, yPos - 20, 20, yellow); // Kopf
    }

    @Override
    public void attack(Fightable opponent) {
        System.out.println(name + " führt einen Spezialangriff aus!");
        try {
            ((Monster) opponent).reduceHealth(4);
        } catch (HealthException e) {
            System.out.println(e.getMessage());
        }
    }
}
