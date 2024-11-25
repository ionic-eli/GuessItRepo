import java.awt.*;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class SelectUserToPlay {
    @SuppressWarnings("unused")
    SelectUserToPlay(UserManager userManager, JFrame mainMenuFrame, Clip audio) {
        // Creación de la lista para mostrar los datos
        JList<String> listaConUsuarios = new JList<>(userManager.selectUserList());
        listaConUsuarios.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listaConUsuarios.setLayoutOrientation(JList.VERTICAL); // Tal vez a futuro se pueda hacer un vertical WRAP

        JScrollPane listScroller = new JScrollPane(listaConUsuarios);
        listScroller.setPreferredSize(new Dimension(250, 200));

        // GUI stuff
        JFrame showUsernames = new JFrame("¡Selecciona tu usuario!");
        JPanel panelLayout = new JPanel(new BorderLayout());
        JPanel panelForButtons = new JPanel(new FlowLayout());

        // Componentes de la GUI
        panelLayout.add(BorderLayout.NORTH, new JLabel("¡Selecciona a un usuario para jugar!"));
        panelLayout.add(BorderLayout.CENTER, listaConUsuarios);

        JButton backToMenu = new JButton("Volver al menú");
        JButton startMatch = new JButton("Iniciar partida");

        // Settings y eventos de los botones
        listaConUsuarios.addListSelectionListener(e -> startMatch.setEnabled(true));
        backToMenu.addActionListener(e -> showUsernames.dispose());
        startMatch.setEnabled(false);
        startMatch.addActionListener(e -> {
            InnerSelectUserToPlay.startGame(listaConUsuarios.getSelectedValue(), userManager, mainMenuFrame, audio);
            showUsernames.dispose(); // Close the user selection window
        });

        panelForButtons.add(backToMenu);
        panelForButtons.add(startMatch);
        panelLayout.add(BorderLayout.SOUTH, panelForButtons);

        // Últimos ajustes de la ventana
        showUsernames.add(panelLayout);
        showUsernames.setUndecorated(true);
        showUsernames.setSize(300, 300);
        showUsernames.setLocationRelativeTo(null);
        showUsernames.setVisible(true);
    }

    static class InnerSelectUserToPlay {
        public static void startGame(String userSelected, UserManager userManager, JFrame mainMenuFrame, Clip audio) {
            // Detener música y cerrar el menú principal
            if (audio != null && audio.isRunning()) {
                audio.stop();
                audio.close();
            }
            if (mainMenuFrame != null) {
                mainMenuFrame.dispose();
            }

            // Lanzar el juego
            UserVideogame user = userManager.userIdentifier(userSelected);
            new GameScreen(user, userManager);
        }
    }
}
