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
    protected static void registerNewUser()
    {
        ArrayList<UserVideogame> users = userManager.getUsersList();
        int userExist = 0; // Por defecto, el usuario existe.

        String username = JOptionPane.showInputDialog(null, "Ingresa el nombre del usuario que quieres registrar", "Registro de usuario", JOptionPane.QUESTION_MESSAGE);

        if(!username.equals(""))  // Verificando que el inputText no esté vacío
        {
            if(users.size() > 0) // Verificando que la lista no está vacío
            {
                for(UserVideogame user : users)
                {
                    // Validando que el nombre de usuario no exista para agregarlo a la lista de usuaruis
                    if(user.getUsername().equals(username)) // Si el nombre de usuario existe...
                    {
                        JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya está registrado, intente usando otro nombre.", "Usuario ya registrado.", JOptionPane.ERROR_MESSAGE);
                        userExist = 0; // El usuario existe, por lo tanto, no puede registrarse 
                        break;
                    }
                    userExist = 1; // El usuario no existe
                }
            }
            else
            {
                userExist = 1; // El usuario no existe
            }
    
            if(userExist == 1)
            {
                userManager.newUser(new UserVideogame(username)); // Enviando una referencia de Usuario con el username recién ingresado
                JOptionPane.showMessageDialog(null, "El usuario ha sido registrado exitosamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Necesita ingresar un nombre para poder registrarse.", "Campo de texto vacío", JOptionPane.ERROR_MESSAGE);
        }
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