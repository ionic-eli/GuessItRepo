import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {
    private JFrame gameScreenFrame;
    private GamePanel gamePanel;
    private JLabel targetLabel;
    private JLabel timerLabel;
    private JLabel currentLabel;
    private JLabel scoreLabel;
    private JLabel operationLabel;
    private UserManager userManager; // reference to UserManager
    private boolean isMainMenuOpened = false; // Prevent multiple main menu instances

    public GameScreen(UserVideogame userPlaying, UserManager userManager) {
        this.userManager = userManager; // store the reference to UserManager

        // configuración principal de la ventana
        gameScreenFrame = new JFrame("¡Adivina!");
        gameScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreenFrame.setSize(1000, 700);
        gameScreenFrame.setLayout(new BorderLayout());

        // panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // panel superior
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel topLabelsPanel = new JPanel(new GridLayout(1, 4));
        targetLabel = createLabel("Objetivo: 0");
        timerLabel = createLabel("Tiempo: 0s");
        currentLabel = createLabel("Actual: 0");
        scoreLabel = createLabel("Puntuación: " + userPlaying.getScore());
        topLabelsPanel.add(targetLabel);
        topLabelsPanel.add(timerLabel);
        topLabelsPanel.add(currentLabel);
        topLabelsPanel.add(scoreLabel);

        // boton de regresar al menú principal en la esquina superior derecha
        JButton returnToMenuButton = new JButton("Volver al menú");
        returnToMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnToMenuButton.addActionListener(e -> returnToMainMenu());
        infoPanel.add(topLabelsPanel, BorderLayout.CENTER);
        infoPanel.add(returnToMenuButton, BorderLayout.EAST);

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // panel derecho con operadores
        JPanel operatorsPanel = createOperatorPanel();
        mainPanel.add(operatorsPanel, BorderLayout.EAST);

        // panel central (GamePanel) con relleno y espaciado
        gamePanel = new GamePanel(this, userPlaying); // pass GameScreen and UserVideogame
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Dynamic padding
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        // panel inferior
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        operationLabel = createLabel("Operador: +");
        JLabel userLabel = createLabel("Usuario: " + userPlaying.getUsername());
        bottomPanel.add(operationLabel);
        bottomPanel.add(userLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // agregar todo al frame
        gameScreenFrame.add(mainPanel);
        gameScreenFrame.setVisible(true);

        // temporizador
        Timer timer = new Timer(1000, e -> {
            timerLabel.setText("Tiempo: " + gamePanel.getElapsedTime() + "s");
            targetLabel.setText("Objetivo: " + gamePanel.getTargetNum());
            currentLabel.setText("Actual: " + gamePanel.getCurrentNum());
            scoreLabel.setText("Puntuación: " + userPlaying.getScore());
        });
        timer.start();
    }

    // método para obtener el UserManager
    public UserManager getUserManager() {
        return userManager;
    }

    // método para crear etiquetas
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    // método para crear el panel de operadores
    private JPanel createOperatorPanel() {
        JPanel operatorsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        String[] operators = {"+", "-", "*", "/"};

        for (String operator : operators) {
            JButton operatorButton = new JButton(operator);
            operatorButton.setFont(new Font("Arial", Font.BOLD, 20));
            operatorButton.addActionListener(e -> {
                gamePanel.setCurrentOperation(operator);
                operationLabel.setText("Operador: " + operator);
            });
            operatorsPanel.add(operatorButton);
        }
        operatorsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding for spacing
        return operatorsPanel;
    }

    // método para volver al menú principal
    public void returnToMainMenu() {
        if (!isMainMenuOpened) {
            isMainMenuOpened = true;
            gameScreenFrame.dispose(); // cerrar la pantalla del juego
            new MainScreen(userManager); // abrir el menú principal
        }
    }

    public void updateLabels(int target, int current) {
        targetLabel.setText("Objetivo: " + target);
        currentLabel.setText("Actual: " + current);
        repaint();
    }

    public static void main(String[] args) {
        UserVideogame testUser = new UserVideogame("Jugador1");
        UserManager testManager = new UserManager(); // Create a test UserManager
        new GameScreen(testUser, testManager);
    }
}
