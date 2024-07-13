package monster_game;

import static jsTools.Graph.*;

public class Student extends Monster {
    public Student(String name, int health, int xPos, int yPos) {
        super(name, health, xPos, yPos);
    }

    @Override
    public void paint() {

        addRect(xPos + 5, yPos + 40, 10, 40, black);    // linkes Bein
        addRect(xPos + 25, yPos + 40, 10, 40, black);  // rechtes Bein


        addRect(xPos, yPos, 40, 40, blue);


        addRect(xPos - 10, yPos + 10, 10, 35, green);  // linker Arm
        addRect(xPos + 40, yPos + 10, 35, 10, green);  // rechter Arm


        addCircle(xPos + 13, yPos - 20, 20, green);

        // Schwert zeichnen (Standard-Schwert)
        addRect(xPos + 70, yPos - 28, 5, 55, black);  // Schwertklinge
        addRect(xPos + 65, yPos + 5, 15, 5, black); // Schwertgriff
    }

    @Override
    public void attack(Fightable opponent) {
        System.out.println(name + " greift mit dem Schwert an!");
        try {
            ((Monster) opponent).reduceHealth(2);
        } catch (HealthException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getAttackDamage() {
        return 2;
    }
}
