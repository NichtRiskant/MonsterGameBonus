package monster_game;

import static jsTools.Graph.*;

public class Student extends Monster {
    public Student(String name, int health, int xPos, int yPos) {
        super(name, health, xPos, yPos);
    }

    @Override
    public void paint() {
        addRect(xPos, yPos + 60, 10, 40, blue); // linkes Bein
        addRect(xPos + 50, yPos + 60, 10, 40, blue); // rechtes Bein
        addRect(xPos, yPos, 60, 60, green); // Körper
        addRect(xPos - 10, yPos + 10, 10, 30, green); // linker Arm
        addRect(xPos + 60, yPos + 10, 10, 30, green); // rechter Arm
        addCircle(xPos + 25, yPos - 20, 20, green); // Kopf
    }

    @Override
    public void attack(Fightable opponent) {
        System.out.println(name + " greift an!");
        try {
            ((Monster) opponent).reduceHealth(3);
        } catch (HealthException e) {
            System.out.println(e.getMessage());
        }
    }
}
