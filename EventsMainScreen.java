import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class EventsMainScreen {
    protected static UserManager userManager; // referencia estática para manejar usuarios
    private static JFrame mainMenuFrame; // Almacena el marco principal
    private static Clip audioClip; // Almacena la música de fondo

    EventsMainScreen(UserManager userManager) {
        EventsMainScreen.userManager = userManager;
    }

    // eventos / métodos de cada botón de la clase mainscreen

    protected static void startGame(JFrame frame, Clip audio) {
        // inicializar referencias al menú principal y al clip de audio
        mainMenuFrame = frame;
        audioClip = audio;

        // inicializa la selección de usuario para comenzar el juego
        new SelectUserToPlay(userManager, mainMenuFrame, audioClip);
    }

    protected static void showScoreboard() {
        // muestra la tabla de puntuaciones con los datos de usuarios
        new Scoreboard().leerUsuarios(userManager.getUsersList());
    }

    protected static int registerNewUser() {
        String msg = "";
        String titulo = "";
        ArrayList<UserVideogame> users = userManager.getUsersList();

        String username = JOptionPane.showInputDialog(
            null,
            "Ingresa el nombre del usuario que quieres registrar",
            "Registro de usuario",
            JOptionPane.QUESTION_MESSAGE
        );

        if (username == null || username.trim().isEmpty()) {
            msg = "Necesita ingresar un nombre para poder registrarse.";
            titulo = "Campo de texto vacío";
            JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        for (UserVideogame user : users) {
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                msg = "Ese nombre de usuario ya está registrado. Intente con otro nombre.";
                titulo = "Usuario ya registrado";
                JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }

        userManager.newUser(new UserVideogame(username.trim()));
        msg = "El usuario ha sido registrado exitosamente.";
        titulo = "Registro exitoso";
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
        return 1;
    }

    protected static void closeApp(Clip audio) {
        if (userManager.getUsersList().size() > 0) {
            ReadWriteData.writeData(userManager.getUsersList());
        }

        if (audio != null) {
            audio.stop();
            audio.close();
        }
        System.exit(0);
    }
}
