public class Battle {
    public void fight(Warrior player, Warrior monster, WorldOfWar.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("---Ход: " + turn + "---");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else isFightEnded = makeHit(player, monster, fightCallback);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Warrior defender, Warrior attacker, WorldOfWar.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;

        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся", attacker.getName()));
        }

        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            if (attacker.isRaisable()) {
                attacker.setLevel(1 + attacker.getExperience()/100);
                System.out.println(String.format("Ваш уровень повышен до %d", attacker.getLevel()));
            }
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }
}
