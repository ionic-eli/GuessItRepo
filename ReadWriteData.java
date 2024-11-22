import java.io.*;
import java.util.ArrayList;

public class ReadWriteData 
{
    public static UserManager readData()
    {
        UserManager userManager = new UserManager(); // Instancia de user manager a generar
        
        try
        {
            FileInputStream fis = new FileInputStream("Users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) 
            {
                try 
                {
                    UserVideogame user = null;
                    user = (UserVideogame) ois.readObject(); // hello, alex was here
                    userManager.newUser(user);
                }
                catch (EOFException e) 
                {
                    ois.close();
                    break;
                }   
            }

            ois.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return userManager;
    }

    public static void writeData(ArrayList<UserVideogame> userList)
    {
        try 
        {
            FileOutputStream fos = new FileOutputStream("Users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(int i = 0; i < userList.size(); i++)
            {
                oos.writeObject(userList.get(i));
                // System.out.println("Usuario cargado en el .ser!");
            }
            oos.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }   
}