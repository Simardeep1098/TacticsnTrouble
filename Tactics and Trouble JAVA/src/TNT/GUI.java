import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel cardPanel;
    private JButton button;
    private JLabel label;

    public GUI() {
        frame = new JFrame("Tactics & Trouble - TnT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        frame.add(mainPanel);

        // Create a CardLayout for switching between panels
        CardLayout cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        mainPanel.add(cardPanel);

        // First Panel (Start Page)
        JPanel firstPanel = new JPanel(new GridBagLayout());
        label = new JLabel("Tactics & Trouble");
        label.setFont(new Font("Sofia Sans", Font.PLAIN, 50));
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.weightx = 1.0;
        gbcLabel.weighty = 0.5;
        gbcLabel.anchor = GridBagConstraints.CENTER;
        firstPanel.add(label, gbcLabel);

        button = new JButton("Start Game");
        button.setFont(new Font("Sofia Sans", Font.PLAIN, 50));
        button.addActionListener(this);
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        gbcButton.weightx = 1.0;
        gbcButton.weighty = 0.5;
        gbcButton.anchor = GridBagConstraints.CENTER;
        firstPanel.add(button, gbcButton);

        // Second Panel (Character Input Page)
        JPanel secondPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels and Input Fields for Character Attributes
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel powerLabel = new JLabel("Power:");
        JTextField powerField = new JTextField(20);
        JLabel defenseLabel = new JLabel("Defense:");
        JTextField defenseField = new JTextField(20);
        JLabel lifeLabel = new JLabel("Life:");
        JTextField lifeField = new JTextField(20);
        JLabel speedLabel = new JLabel("Speed:");
        JTextField speedField = new JTextField(20);
        
        // JComboBox for Weapon Selection
        JLabel weaponLabel = new JLabel("Weapon:");
        String[] weapons = {"Lightning", "Wood", "Metal", "Void", "Spirit"};
        JComboBox<String> weaponComboBox = new JComboBox<>(weapons);

        // Add labels, input fields, and combo box to the panel
        gbc.gridy++;
        secondPanel.add(nameLabel, gbc);
        gbc.gridy++;
        secondPanel.add(nameField, gbc);
        gbc.gridy++;
        secondPanel.add(powerLabel, gbc);
        gbc.gridy++;
        secondPanel.add(powerField, gbc);
        gbc.gridy++;
        secondPanel.add(defenseLabel, gbc);
        gbc.gridy++;
        secondPanel.add(defenseField, gbc);
        gbc.gridy++;
        secondPanel.add(lifeLabel, gbc);
        gbc.gridy++;
        secondPanel.add(lifeField, gbc);
        gbc.gridy++;
        secondPanel.add(speedLabel, gbc);
        gbc.gridy++;
        secondPanel.add(speedField, gbc);
        gbc.gridy++;
        secondPanel.add(weaponLabel, gbc);
        gbc.gridy++;
        secondPanel.add(weaponComboBox, gbc);

        // Create a button to add character
        JButton addPlayer = new JButton("Add Player");
        addPlayer.addActionListener(e -> {
            // Handle character input here
            String name = nameField.getText();
            String power = powerField.getText();
            String defense = defenseField.getText();
            String life = lifeField.getText();
            String speed = speedField.getText();
            String selectedWeapon = (String) weaponComboBox.getSelectedItem();

            // process the character data here

            
        });

        gbc.gridy++;
        gbc.gridwidth = 2;
        secondPanel.add(addPlayer, gbc);

        // Add the character input panel to the cardPanel
        cardPanel.add(firstPanel, "firstPage");
        cardPanel.add(secondPanel, "secondPage");

        // Show the "firstPage" panel initially
        cardLayout.show(cardPanel, "firstPage");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            // Switch to the "secondPage" panel when the button is clicked
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "secondPage");
        }
    }

}
