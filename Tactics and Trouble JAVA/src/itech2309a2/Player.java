package itech2309a2;

class Player {
    private String name;
    private int speed;
    private int power;
    private int defense;
    private int life;
    private int health;
    private String weaponType;

    public Player(String name, int speed, int power, int defense, int life, String weaponType) {
        this.name = name;
        this.speed = speed;
        this.power = power;
        this.defense = defense;
        this.life = life;
        this.health = life;
        this.weaponType = weaponType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void heal(int amount) {
        health += amount;
        if (health > life) {
            health = life;
        }
    }
}
