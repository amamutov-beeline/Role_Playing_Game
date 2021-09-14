public class Player extends Warrior {
    public Player(String name, int hp, int gold, int agility, int experience, int power, int level) {
        super(name, hp, gold, agility, experience, power, level);
    }

    @Override
    public boolean isRaisable(){
        if (getExperience() >= 100 * getLevel()){
            return true;
        }
        else return false;
    }
}
