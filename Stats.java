public class Stats {
    private int health;
    private int maxHealth;
    private int strength;
    private int armour;

    public Stats(int maxHealth, int strength, int armour) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.armour = armour;
    }

    public int damage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Negative damage");
        }
        int damageAmount = Math.max(0, damage - armour);
        health = Math.max(0, health - damageAmount);
        return damageAmount;
    }

    public int heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative heal");
        }
        int healedAmount = Math.min(amount, maxHealth - health);
        health += healedAmount;
        return healedAmount;
    }

    public boolean alive() {
        return health > 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getArmour() {
        return armour;
    }

    public int getStrength() {
        return strength;
    }
}
