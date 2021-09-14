public class Skeleton extends Warrior {
    public Skeleton(String name, int hp, int gold, int agility, int experience, int power, int level) {
        super(name, hp, gold, agility, experience, power, level);
    }

    @Override
    public boolean isRaisable() {
        return false;
    }
}