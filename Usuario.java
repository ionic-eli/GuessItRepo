// Clase para modelar a un usuario de la vida real
import java.io.Serializable;

public class Usuario implements Serializable
{
    // Atributos
    private String username;
    private int puntaje;
    
    Usuario(String username)
    {
        this.username = username;
        puntaje = 0;
    }

    // Setters y getters 

    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    public String getUsername() 
    {
        return username;
    }

    public void setPuntaje(int puntaje) 
    {
        this.puntaje = puntaje;
    }   

    public int getPuntaje()
    {
        return puntaje;
    }
}