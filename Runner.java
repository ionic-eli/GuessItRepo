public class Runner 
{
    public static void main(String[] args) 
    {
        new MainScreen(ReadWriteData.readData()); // Independientemente de la existencia se "Users.ser", el código llegará aquí 
    }
}