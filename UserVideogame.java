// Clase que modela a un usuario desde el punto de vista del videojuego
import java.util.ArrayList;

public class UserVideogame extends Usuario
{
    // Atributos en tiempo de ejecución del programa
    transient private int vecesUsadoSuma;
    transient private int vecesUsadoResta;
    transient private int vecesUsadoMul;
    transient private int vecesUsadoDiv;
    transient private ArrayList<String> tiempoPorNumero; // Arreglo con la cantidad de tiempo por cada numero objetivo acertado.

    // Constructor típico para el registro de un usuario el cual tiene un parametro de tipo String
    UserVideogame(String username)
    {
        super(username);
        vecesUsadoSuma = 0;
        vecesUsadoResta = 0;
        vecesUsadoMul = 0;
        vecesUsadoDiv = 0;
        tiempoPorNumero = new ArrayList<>();
    }

    // Métodos setters y getters de la clase UserVideogame

    public int getVecesUsadoSuma() {
        return vecesUsadoSuma;
    }

    public void setVecesUsadoSuma(int vecesUsadoSuma) {
        this.vecesUsadoSuma = vecesUsadoSuma;
    }

    public int getVecesUsadoResta() {
        return vecesUsadoResta;
    }

    public void setVecesUsadoResta(int vecesUsadoResta) {
        this.vecesUsadoResta = vecesUsadoResta;
    }

    public int getVecesUsadoMul() {
        return vecesUsadoMul;
    }

    public void setVecesUsadoMul(int vecesUsadoMul) {
        this.vecesUsadoMul = vecesUsadoMul;
    }

    public int getVecesUsadosDiv() {
        return vecesUsadoDiv;
    }

    public void setVecesUsadosDiv(int vecesUsadosDiv) {
        this.vecesUsadoDiv = vecesUsadosDiv;
    }

    public ArrayList<String> getTiempoPorNumero() {
        return tiempoPorNumero;
    }

    public void setTiempoPorNumero(ArrayList<String> tiempoPorNumero) {
        this.tiempoPorNumero = tiempoPorNumero;
    }   
}