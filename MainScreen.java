import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
    
public class MainScreen
{
    MainScreen(UserManager userManager)
    {
        new EventsMainScreen(userManager); // Dandole la lista a la clase que maneja los eventos
        createMainScreen(); // Este método construye el menú de inicio
    }
    
    @SuppressWarnings("unused") // Esto es para los lambdas de los ActionListeners de los botones del menú.
    private void createMainScreen()
    {
        JFrame mainFrame = new JFrame("Guess It");
        JPanel panelBL = new JPanel(new BorderLayout());
        JPanel columnGridPanel = new JPanel(new GridLayout(4, 1));
        int widthImg = 300;
        int heigthImg = 300;

        // Settings de la GUI
        mainFrame.setUndecorated(true); // Ésta linea la usaremos solo para cuando la GUI esté ordenada
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setFocusCycleRoot(true);
        
        try 
        {
            // Cargando la canción del menú de inicio
            File audioFile = new File("audios/song.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip audioClip = AudioSystem.getClip();

            audioClip.open(audioStream);
            // audioClip.start(); // Inicio de la canción
        
            // Procesamiento de la imagen
            BufferedImage originalImage = ImageIO.read(new File("images/Logo.png"));
            BufferedImage logoGuessIt = new BufferedImage(widthImg, heigthImg, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g = logoGuessIt.createGraphics();
            g.drawImage(originalImage.getScaledInstance(widthImg, heigthImg, Image.SCALE_SMOOTH),0,0, null);

            // Componentes de la GUI
            JLabel picLabel = new JLabel(new ImageIcon(logoGuessIt));
            JButton buttonStartGame = new JButton("Inicar juego");
            JButton buttonRegisterUser = new JButton("Registrar un nuevo usuario");
            JButton buttonShowScoreboard = new JButton("Tabla de puntuaciones");
            JButton buttonExit = new JButton("Salir");

            // Propiedades de cada botón
            Dimension buttonSize = new Dimension(30, 50);
            buttonStartGame.setForeground(Color.WHITE);
            buttonStartGame.setBackground(Color.GREEN);
            buttonExit.setForeground(Color.WHITE);
            buttonExit.setBackground(Color.RED);

            buttonStartGame.setPreferredSize(buttonSize);
            buttonRegisterUser.setPreferredSize(buttonSize);
            buttonShowScoreboard.setPreferredSize(buttonSize);
            buttonExit.setPreferredSize(buttonSize);
            
            // Eventos de cada botón
            buttonStartGame.addActionListener(e -> EventsMainScreen.startGame(mainFrame, audioClip));
            buttonRegisterUser.addActionListener(e -> EventsMainScreen.registerNewUser());
            buttonShowScoreboard.addActionListener(e -> EventsMainScreen.showScoreboard());
            buttonExit.addActionListener(e -> EventsMainScreen.closeApp(audioClip));
            
            // Agregando cada compontente al Panel
            panelBL.add(BorderLayout.NORTH, picLabel);
            columnGridPanel.add(buttonStartGame);
            columnGridPanel.add(buttonRegisterUser);
            columnGridPanel.add(buttonShowScoreboard);
            columnGridPanel.add(buttonExit);
            panelBL.add(BorderLayout.CENTER, columnGridPanel);
            mainFrame.add(panelBL);

            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null); // Para que aparezca en el centro de la pantalla
            mainFrame.setVisible(true);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}