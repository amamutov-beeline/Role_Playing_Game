public abstract class Warrior implements Attack, LevelUp {
    private String name;
    private int hp;
    private int gold;
    private int agility;
    private int experience;
    private int power;
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public Warrior(String name, int hp, int gold, int agility, int experience, int power, int level) {
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.agility = agility;
        this.experience = experience;
        this.power = power;
        this.level = level;
    }

    @Override
    public int attack() {
        if (agility * 3 > getRandomValue()) return power;
        else return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return name + "'s" + " healthpower " + hp;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }
}