package itech2309a2;

class Monster {
    private String name;
    private int speed;
    private int power;
    private int defense;
    private int life;
    private int health;
    private String monsterType;

    public Monster(String name, int speed, int power, int defense, int life, String monsterType) {
        this.name = name;
        this.speed = speed;
        this.power = power;
        this.defense = defense;
        this.life = life;
        this.health = life;
        this.monsterType = monsterType;
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

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
