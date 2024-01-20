/**
 * Stores the statistics such as: health, max health, strength, armor
 * 
 * @author Ivan Kosov
 */
public class Stats {
    private int health;
    private int maxHealth;
    private int strength;
    private int armor;

    /**
     * Initializes the fields
     * 
     * @param maxHealth is the maximum amount health
     * @param strength is the amount strength
     * @param armor is the amount armor
     */
    public Stats(int maxHealth, int strength, int armor) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.armor = armor;
    }

    /**
     * Subtracts a certain amount from health
     * 
     * @param damage is the amount to subtract
     * @return dealt damage
     */
    public int damage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Negative damage");
        }
        int damageAmount = Math.max(0, damage - armor);
        health = Math.max(0, health - damageAmount);
        return damageAmount;
    }

    /**
     * Increases health by a certain amount
     * 
     * @param amount is the amount of health to increase
     * @return healed amount
     */
    public int heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative heal");
        }
        int healedAmount = Math.min(amount, maxHealth - health);
        health += healedAmount;
        return healedAmount;
    }

    /**
     * Checks if the health is above zero
     * 
     * @return true if alive, false if dead
     */
    public boolean alive() {
        return health > 0;
    }

    /**
     * @return maximum health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return current armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Increases armor by given amount
     * 
     * @param amount is the amount of armor to increase
     */
    public void increaseArmor(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Negative armor");
        }
        armor += amount;
    }

    /**
     * @return current strength
     */
    public int getStrength() {
        return strength;
    }
}
