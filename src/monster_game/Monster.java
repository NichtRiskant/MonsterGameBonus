package monster_game;

import static jsTools.Graph.*;

public abstract class Monster implements Fightable {
    String name;
    int health;
    int xPos, yPos;

    public Monster(String name, int health, int xPos, int yPos) {
        this.name = name;
        this.health = health;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public abstract void paint();

    // Rekursive Methode zum Zeichnen
    public void paintRecursive(int count) {
        if (count > 0) {
            addRect(xPos + count * 10, yPos - 80, 10, 10, red); // Position über dem Monster
            paintRecursive(count - 1);
        }
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public void reduceHealth(int amount) throws HealthException {
        health -= amount;
        if (health < 0) {
            throw new HealthException(name + " hat negative Gesundheit!");
        }
    }

    public abstract void attack(Fightable opponent);

    public abstract int getAttackDamage(); // Neue Methode hinzufügen
}
