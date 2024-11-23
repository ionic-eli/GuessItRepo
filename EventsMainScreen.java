import javax.swing.*;
import java.util.ArrayList;
import javax.sound.sampled.Clip;

public class EventsMainScreen
{
    protected static UserManager userManager;

    EventsMainScreen(UserManager userManager)
    {
        EventsMainScreen.userManager = userManager; // Igualando las referencias para evitar listas duplicadas
    }

    // EVENTOS / MÉTODOS DE CADA BOTÓN DE LA CLASE MainScreen

    protected static void startGame(JFrame mainFrame, Clip audio)
    {
        new SelectUserToPlay(userManager);
    }
    
    protected static void showScoreboard()
    {
        new Scoreboard().leerUsuarios(userManager.getUsersList());
    }
    
    protected static int registerNewUser()
    {
        // Considerar dict
        // key = value
        // error = "Mensaje de error"
        
        String msg = "";
        String titulo = "";
        ArrayList<UserVideogame> users = userManager.getUsersList();

        String username = JOptionPane.showInputDialog(null, "Ingresa el nombre del usuario que quieres registrar", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);

        if(username.equals(""))
        {
            msg = "Necesita ingresar un nombre para poder registrarse.";
            titulo = "Campo de texto vacío";
            JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        // Busco que el nombre de usuario no coincida con otros
        for(UserVideogame user: users)
        {
            // Validando que el nombre de usuario no exista para agregarlo a la lista de usuaruis
            if(user.getUsername().equals(username)) // Si el nombre de usuario existe...
            {
                msg = "Usuario ya registrado.";
                titulo = "Ese nombre de usuario ya está registrado, intente usando otro nombre.";
                JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }

        msg = "El usuario ha sido registrado exitosamente.";
        titulo = "Campo de texto vacío";

        userManager.newUser(new UserVideogame(username)); // Enviando una referencia de Usuario con el username recién ingresado
        JOptionPane.showMessageDialog(null, msg, "Campo de texto vacío", JOptionPane.INFORMATION_MESSAGE);
        return 1;
    }

    protected static void closeApp(Clip audio)
    {
        if(userManager.getUsersList().size() > 0)
        {
            ReadWriteData.writeData(userManager.getUsersList()); 
        }
        
        audio.stop();
        System.exit(0);
    }
}