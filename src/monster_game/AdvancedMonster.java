package monster_game;

import static jsTools.Graph.*;

public class AdvancedMonster extends Monster {
    public AdvancedMonster(String name, int health, int xPos, int yPos) {
        super(name, health, xPos, yPos);
    }

    @Override
    public void paint() {
        // Beine
        addRect(xPos , yPos +40, 20, 100, black);   // linkes Bein
        addRect(xPos + 60, yPos +40, 20, 100, black);  // rechtes Bein

        // Körper
        addRect(xPos, yPos -20, 80, 96, yellow);

        // Arme
        addRect(xPos + 80, yPos + 20, 80, 24, red);  // linker Arm
        addRect(xPos - 80, yPos + 20, 80, 24, red);  // rechter Arm

        // Kopf
        addCircle(xPos + 13, yPos - 60, 40, red);
        //Schwert
        addRect(xPos -90, yPos -60, 10, 110, black);  // Schwertklinge
        addRect(xPos -100, yPos +10, 30, 10, black);   // Schwertgriff
//Schwert2
        addRect(xPos +160, yPos -60, 10, 110, black);  // Schwertklinge rechts
        addRect(xPos +150, yPos +10, 30, 10, black);   // Schwertgriff rechts
    }

    @Override
    public void attack(Fightable opponent) {
        System.out.println(name + " führt einen starken Angriff aus!");
        try {
            ((Monster) opponent).reduceHealth(3); // Stärkerer Angriff
        } catch (HealthException e) {
            System.out.println(e.getMessage());
        }
    }
}
