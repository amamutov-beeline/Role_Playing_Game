import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorldOfWar {
    private static BufferedReader br;
    private static Warrior gamer = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя персонажа: ");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (gamer == null) {
            gamer = new Player(
                    string,
                    1000,
                    0,
                    500,
                    0,
                    20,
                    1
            );
            System.out.println(String.format("Спасти наш мир вызвался %s!", gamer.getName()));
            printNavigation();
        }
        switch (string) {
            case "1" -> System.out.println("Хотите купить зелье за 50 золота? (купить/выйти)");
            case "2" -> commitFight();
            case "3" -> System.exit(1);
            case "да" -> command("2");
            case "нет", "выйти" -> {
                printNavigation();
                command(br.readLine());
            }
            case "купить" -> {
                if (store()) {
                    System.out.println("Ваше здоровье пополнено на 100 единиц");
                    command("1");
                } else {
                    System.out.println("Недостаточно золота, приходите еще!");
                    printNavigation();
                }
            }
        }
        command(br.readLine());
    }

    private static boolean store() {
        if (gamer.getGold() >= 50) {
            gamer.setHp(gamer.getHp() + 100);
            gamer.setGold(gamer.getGold() - 50);
            return true;
        } else return false;
    }

    private static void commitFight() {
        battle.fight(gamer, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.",
                        gamer.getName(), gamer.getExperience(), gamer.getGold(), gamer.getHp()));
                System.out.println("Желаете продолжить поход? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }

    public static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static Warrior createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин", 100, 25, 10, 150, 15, 1);
        else return new Skeleton("Скелет", 100, 20, 30, 200, 40, 1);
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }
}