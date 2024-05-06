package itech2309a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TacticsAndTroubleGame game = new TacticsAndTroubleGame();
        game.setupPlayers();
        game.setupCombat();
        game.startCombat();
    }
}

class TacticsAndTroubleGame {
    private List<Player> players;
    private List<Monster> monsters;
    private Random random;
    private Scanner scanner;

    public TacticsAndTroubleGame() {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public void setupPlayers() {
        // Input player details and add them to the players list
        players.add(new Player("Player1", 2, 10, 5, 50, "Lightning"));
        players.add(new Player("Player2", 3, 8, 7, 60, "Wood"));
    }

    public void setupCombat() {
        // Initialize monsters and add them to the monsters list
        monsters.add(new Monster("Monster1", 1, 12, 3, 100, "Metal"));
        monsters.add(new Monster("Monster2", 2, 15, 5, 120, "Void"));

        // Randomize the turn order
        Collections.shuffle(players);
        Collections.shuffle(monsters);
    }

    public void startCombat() {
        boolean gameOver = false;

        while (!gameOver) {
            // Shuffle turn order
            Collections.shuffle(players);
            Collections.shuffle(monsters);

            // Player turn
            for (Player player : players) {
                System.out.println("Player " + player.getName() + "'s turn:");
                System.out.println("Choose an action:");
                System.out.println("1. Attack a Monster");
                System.out.println("2. Heal a Player");
                System.out.println("3. Revive a Player");
                System.out.println("4. Power up");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Player chooses to attack a monster
                        Monster targetMonster = monsters.get(random.nextInt(monsters.size()));
                        int damage = calculatePlayerDamage(player, targetMonster);
                        targetMonster.takeDamage(damage);
                        System.out.println("Player " + player.getName() + " attacked " + targetMonster.getName() + " for " + damage + " damage.");
                        break;

                    case 2:
                        // Player chooses to heal another player
                        Player targetPlayer = players.get(random.nextInt(players.size()));
                        boolean healSuccess = attemptHeal(player, targetPlayer);
                        if (healSuccess) {
                            System.out.println("Player " + player.getName() + " healed Player " + targetPlayer.getName() + ".");
                        } else {
                            System.out.println("Healing failed.");
                        }
                        break;

                    case 3:
                        // Player chooses to revive a dead player
                        Player deadPlayer = findDeadPlayer();
                        if (deadPlayer != null) {
                            revivePlayer(player, deadPlayer);
                            System.out.println("Player " + player.getName() + " revived Player " + deadPlayer.getName() + ".");
                        } else {
                            System.out.println("No dead players to revive.");
                        }
                        break;

                    case 4:
                        // Player chooses to power up
                        boolean powerUpSuccess = attemptPowerUp(player);
                        if (powerUpSuccess) {
                            System.out.println("Player " + player.getName() + " powered up.");
                        } else {
                            System.out.println("Power-up failed.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Skipping turn.");
                        break;
                }

                // Check if the game is over
                if (areAllMonstersDead() || areAllPlayersDead()) {
                    gameOver = true;
                    break;
                }
            }

            // Monster turn
            for (Monster monster : monsters) {
                Player targetPlayer = players.get(random.nextInt(players.size()));
                int damage = calculateMonsterDamage(monster, targetPlayer);
                targetPlayer.takeDamage(damage);
                System.out.println(monster.getName() + " attacked Player " + targetPlayer.getName() + " for " + damage + " damage.");

                // Check if the game is over
                if (areAllMonstersDead() || areAllPlayersDead()) {
                    gameOver = true;
                    break;
                }
            }
        }

        // Checking the winner
        if (areAllMonstersDead()) {
            System.out.println("Congratulations! All monsters have been defeated. Players win!");
        } else if (areAllPlayersDead()) {
            System.out.println("All players have been defeated. Monsters win!");
        }
    }

    // methods to check game over conditions
    private boolean areAllMonstersDead() {
        for (Monster monster : monsters) {
            if (monster.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean areAllPlayersDead() {
        for (Player player : players) {
            if (player.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    private int calculatePlayerDamage(Player player, Monster monster) {
        int baseDamage = player.getPower();

        // Apply weapon type bonuses
        if (player.getWeaponType().equals(monster.getMonsterType())) {
            baseDamage *= 2; // Double damage for matching weapon types
        } else if (player.getWeaponType().equals("Void") && monster.getMonsterType().equals("Spirit")) {
            baseDamage *= 2; // Special bonus for Void weapon against Spirit monsters
        } else if (player.getWeaponType().equals("Spirit") && monster.getMonsterType().equals("Void")) {
            baseDamage *= 2; // Special bonus for Spirit weapon against Void monsters
        }

        int damageDealt = Math.max(1, baseDamage - monster.getDefense());
        return damageDealt;
    }

    private boolean attemptHeal(Player healer, Player targetPlayer) {
        if (random.nextDouble() < 0.5) {
            int healAmount = healer.getPower();
            targetPlayer.heal(healAmount);
            return true;
        }
        return false;
    }

    private Player findDeadPlayer() {
        for (Player player : players) {
            if (player.getHealth() <= 0) {
                return player;
            }
        }
        return null;
    }

    private void revivePlayer(Player reviver, Player deadPlayer) {
        int revivedHealth = (int) (0.3 * deadPlayer.getLife());
        deadPlayer.setHealth(revivedHealth);
    }

    private boolean attemptPowerUp(Player player) {
        if (player.getSpeed() > 1 && random.nextDouble() < 0.75) {
            player.setPower(player.getPower() * 2);
            player.setSpeed(player.getSpeed() / 2);
            return true;
        }
        return false;
    }

    private int calculateMonsterDamage(Monster monster, Player player) {
        int baseDamage = monster.getPower();
        if (monster.getMonsterType().equals(player.getWeaponType())) {
            baseDamage *= 2; // Double damage for matching weapon types
        }
        int damageDealt = Math.max(1, baseDamage - player.getDefense());
        return damageDealt;
    }
}