import java.util.ArrayList;

public class UserManager // Clase que se encarga de gestionar los datos y consultas de información sobre los usuarios registrados en la aplicación
{
    private ArrayList<UserVideogame> usuariosDelJuego = new ArrayList<>(); // Lista que controla TODOS los registros de los usuarios

    // Métodos que no tienen relación con la GUI pero son relativas a temas de consulta de datos
    public void newUser(UserVideogame nuevoUsuario)
    {
        usuariosDelJuego.add(nuevoUsuario);
    }

    public ArrayList<UserVideogame> getUsersList()
    {
        return usuariosDelJuego;
    }

    public String[] selectUserList()
    {
        int amountUsers = getUsersList().size();
        String[] usernames = new String[amountUsers]; 

        for(int i = 0; i < amountUsers; i++)
        {
            usernames[i] = getUsersList().get(i).getUsername();
        }

        return usernames;
    }
}