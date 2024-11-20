import java.io.*;

public class Runner 
{
    static UserManager userManager = new UserManager();

    public static void main(String[] args) 
    {
        // Leer el archivo serializado
        try(FileInputStream fis = new FileInputStream("Users.ser")) 
        {
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) 
            {
                try 
                {
                    UserVideogame user = null;
                    user = (UserVideogame) ois.readObject();
                    System.out.println("Nombre de usuario: " + user.getUsername());
                    userManager.newUser(user);
                }
                catch (EOFException e) 
                {
                    ois.close();
                    break;
                }   
            }
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            
        }
        finally
        { 
            new MainScreen(userManager); // Independientemente de la existencia se "Users.ser", el código llegará aquí
        }
    }
}