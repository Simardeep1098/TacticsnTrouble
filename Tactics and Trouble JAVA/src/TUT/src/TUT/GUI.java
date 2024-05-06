package TUT;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class GUI {
// Declare Swing components and other class fields...
    private JFrame frame;
    private JTextField iname;
    private JTextField life;
    private JList<String> listView;
    private JTextField power;
    private JTextField defence;
    private JTextField speed;
    private JComboBox<String> weapon;
    private JButton addToListButton;

    private List<Player> players; // List to store player information
    private DefaultListModel<String> listModel;

    private JList<Monster> monsterList;
    private DefaultListModel<Monster> listModel2;
    private List<Monster> monsters;

    private JButton movetopanel2;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel Chooseplayer;
    private JPanel panel3;
    private JLabel TurnOutcomes;
    private JPanel panel4;
    private JComboBox<String> playerChoices;

    private JTextArea gameResultsTextArea;
    private Random random;
    private Scanner scanner;
    private boolean displayCombatMessages = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {

        initialize();
        players = new ArrayList<>(); // Initialize the players list
        monsters = new ArrayList<>(); // Initialize the monsters list

        addInitialMonsters();
    }

    private void updateCombatMessages(String message) {
        // Update combat messages in the gameResultsTextArea...
        if (!displayCombatMessages) {
            displayCombatMessages = true;
            panel3.setVisible(true);
            panel4.setVisible(false);
            
        }

        gameResultsTextArea.append(message + "\n");
    }

    private void addInitialMonsters() {
        monsters.add(new Monster("DC-Rahul", 10, 12, 6, "Lightning", 30));
        monsters.add(new Monster("Danger-Simardeep", 8, 10, 10, "Wood", 10));
        monsters.add(new Monster("Rocky_Rock", 20, 5, 20, "Metal", 60));
        monsters.add(new Monster("Gun_man", 9, 30, 20, "Void", 30));
        monsters.add(new Monster("Kirmada", 7, 40, 10, "Spirit", 20));

        panel1.add(weapon);
        for (Monster monster : monsters) {
            listModel2.addElement(monster);
        }
    }

    private void initialize() {
        // Create the main application window...
        frame = new JFrame();
        random = new Random();
        scanner = new Scanner(System.in);

        frame.setBounds(100, 100, 867, 466);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel1 = new JPanel(); // Initialize panel1
        panel1.setBounds(0, 0, 843, 419);
        frame.getContentPane().add(panel1);
        panel1.setLayout(null);

        Chooseplayer = new JLabel("Select Player:");
        Chooseplayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Chooseplayer.setBounds(45, 25, 131, 21);
        panel1.add(Chooseplayer);

        JLabel name = new JLabel("Name");
        name.setBounds(45, 73, 45, 13);
        panel1.add(name);

        iname = new JTextField();
        iname.setBounds(134, 70, 96, 19);
        panel1.add(iname);
        iname.setColumns(10);

        JLabel powerLabel = new JLabel("Power");
        powerLabel.setBounds(45, 133, 45, 13);
        panel1.add(powerLabel);

        power = new JTextField();
        power.setBounds(134, 130, 96, 19);
        panel1.add(power);
        power.setColumns(10);

        JLabel speedLabel = new JLabel("Speed");
        speedLabel.setBounds(45, 163, 45, 13);
        panel1.add(speedLabel);

        speed = new JTextField();
        speed.setBounds(134, 160, 96, 19);
        panel1.add(speed);
        speed.setColumns(10);

        JLabel lifeLabel = new JLabel("Life");
        lifeLabel.setBounds(45, 193, 45, 13);
        panel1.add(lifeLabel);

        life = new JTextField();
        life.setBounds(134, 190, 96, 19);
        panel1.add(life);
        life.setColumns(10);

        JLabel weaponLabel = new JLabel("Weapon");
        weaponLabel.setBounds(45, 223, 57, 13);
        panel1.add(weaponLabel);

        weapon = new JComboBox<String>();
        weapon.setBounds(134, 220, 96, 21);
        weapon.addItem("Lightning");
        weapon.addItem("Wood");
        weapon.addItem("Metal");
        weapon.addItem("Void");
        weapon.addItem("Spirit");

        panel1.add(weapon);

        JLabel defenceLabel = new JLabel("Defence");
        defenceLabel.setBounds(45, 253, 57, 13);
        panel1.add(defenceLabel);

        defence = new JTextField();
        defence.setBounds(134, 250, 96, 19);
        panel1.add(defence);
        defence.setColumns(10);

        listModel = new DefaultListModel<String>();
        listView = new JList<String>(listModel);
        listView.setBounds(350, 10, 473, 409);
        panel1.add(listView);

        addToListButton = new JButton("Add to List");
        addToListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameText = iname.getText();
                String powerText = power.getText();
                String speedText = speed.getText();
                String lifeText = life.getText();
                String weaponText = (String) weapon.getSelectedItem();
                String defenceText = defence.getText();

                // Check if any of the fields are empty
                if (!nameText.isEmpty() && !powerText.isEmpty() && !speedText.isEmpty()
                        && !lifeText.isEmpty() && !weaponText.isEmpty() && !defenceText.isEmpty()) {
                    // Convert text fields to appropriate data types (e.g., int)
                    int powerValue = Integer.parseInt(powerText);
                    int speedValue = Integer.parseInt(speedText);
                    int lifeValue = Integer.parseInt(lifeText);
                    int defenceValue = Integer.parseInt(defenceText);

                    // Create a new Player object and add it to the players list
                    Player player = new Player(nameText, powerValue, speedValue, lifeValue, weaponText, defenceValue);
                    players.add(player);
                    player.setPowerUpUsed(false);

                    // Update the list view with player information
                    String entry = "Name: " + nameText + " | Power: " + powerText
                            + " | Speed: " + speedText + " | Life: " + lifeText + " | Weapon: " + weaponText
                            + " | Defence: " + defenceText;
                    listModel.addElement(entry);

                    // Clear input fields
                    iname.setText("");
                    power.setText("");
                    speed.setText("");
                    life.setText("");
                    weapon.setSelectedIndex(0);
                    defence.setText("");
                }
            }
        });

        addToListButton.setBounds(134, 300, 120, 21);
        panel1.add(addToListButton);

        // Rest of your code for panel1
        movetopanel2 = new JButton("Select Monster");
        movetopanel2.setBounds(134, 343, 120, 21);
        panel1.add(movetopanel2);

        movetopanel2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hide panel1 and show panel2
                panel1.setVisible(false);
                panel2.setVisible(true);

            }
        });

        // panel2 start from here:::::::::::::::::::

        panel2 = new JPanel();
        panel2.setBounds(0, 0, 843, 419);
        frame.getContentPane().add(panel2);
        panel2.setLayout(null);

        // Label for selecting a monster
        JLabel selectMonsterLabel = new JLabel("Select a Monster:");
        selectMonsterLabel.setBounds(45, 20, 200, 30);
        panel2.add(selectMonsterLabel);

        listModel2 = new DefaultListModel<>();
        monsterList = new JList<>(listModel2);
        monsterList.setBounds(55, 74, 363, 150);
        panel2.add(monsterList);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(166, 313, 150, 30);
        panel2.add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);
                panel4.setVisible(true);
                // startCombat();
            }
        });


        new JButton("Start Game");
        // panel3 start from here:::::::::::::::::::
        panel3 = new JPanel();
        panel3.setBounds(0, 0, 853, 429);
        frame.getContentPane().add(panel3);
        panel3.setVisible(displayCombatMessages);
        panel3.setLayout(null);

        TurnOutcomes = new JLabel("Turn Outcomes");
        TurnOutcomes.setBounds(386, 9, 70, 13);
        panel3.add(TurnOutcomes);

        gameResultsTextArea = new JTextArea();
        gameResultsTextArea.setBounds(327, 40, 310, 182);
        gameResultsTextArea.setEditable(false);
        panel3.add(gameResultsTextArea);

        // Add panel4
        panel4 = new JPanel();
        panel4.setBounds(0, 0, 853, 429);
        frame.getContentPane().add(panel4);
        panel4.setLayout(null);

        JLabel selectActionLabel = new JLabel("Select Action:");
        selectActionLabel.setBounds(45, 20, 200, 30);
        panel4.add(selectActionLabel);

        playerChoices = new JComboBox<String>();
        playerChoices.addItem("Attack a Monster");
        playerChoices.addItem("Heal a Player");
        playerChoices.addItem("Revive a Player");
        playerChoices.addItem("Power up");
        playerChoices.setBounds(45, 60, 150, 30);
        panel4.add(playerChoices);

        JButton submitChoiceButton = new JButton("Submit");
        submitChoiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) playerChoices.getSelectedItem();
                startCombat();

            }
        });
        submitChoiceButton.setBounds(45, 100, 100, 30);
        panel4.add(submitChoiceButton);

        frame.setVisible(true); // Make the frame visible
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
                        System.out.println("Player " + player.getName() + " attacked " + targetMonster.getName()
                                + " for " + damage + " damage.");
                        break;

                    case 2:
                        // Player chooses to heal another player
                        Player targetPlayer = players.get(random.nextInt(players.size()));
                        boolean healSuccess = attemptHeal(player, targetPlayer);
                        if (healSuccess) {
                            System.out.println(
                                    "Player " + player.getName() + " healed Player " + targetPlayer.getName() + ".");
                        } else {
                            System.out.println("Healing failed.");
                        }
                        break;

                    case 3:
                        // Player chooses to revive a dead player
                        Player deadPlayer = findDeadPlayer();
                        if (deadPlayer != null) {
                            revivePlayer(player, deadPlayer);
                            System.out.println(
                                    "Player " + player.getName() + " revived Player " + deadPlayer.getName() + ".");
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
                System.out.println(monster.getName() + " attacked Player " + targetPlayer.getName() + " for " + damage
                        + " damage.");

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
        if (player.getWeaponType().equals(monster.getWeaponType())) {
            baseDamage *= 2; // Double damage for matching weapon types
        } else if (player.getWeaponType().equals("Void") && monster.getWeaponType().equals("Spirit")) {
            baseDamage *= 2; // Special bonus for Void weapon against Spirit monsters
        } else if (player.getWeaponType().equals("Spirit") && monster.getWeaponType().equals("Void")) {
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
        if (monster.getWeaponType().equals(player.getWeaponType())) {
            baseDamage *= 2; // Double damage for matching weapon types
        }
        int damageDealt = Math.max(1, baseDamage - player.getDefense());
        return damageDealt;
    }
}