import java.io.*;
import java.util.ArrayList;

public class UserVideogame extends Usuario {
    // atributos persistentes y transitorios
    private int vecesUsadoSuma; // ya no es transitorio
    private int vecesUsadoResta; // ya no es transitorio
    private int vecesUsadoMul; // ya no es transitorio
    private int vecesUsadoDiv; // ya no es transitorio
    private ArrayList<String> tiempoPorNumero; // arreglo con la cantidad de tiempo por cada número objetivo acertado

    private int score; // puntaje del usuario
    private int tiempoTotalJugado; // suma de todos los temporizadores
    private int mejorTiempo; // menor tiempo para completar un nivel

    // constructor típico para el registro de un usuario el cual tiene un parámetro de tipo string
    UserVideogame(String username) {
        super(username);
        vecesUsadoSuma = 0;
        vecesUsadoResta = 0;
        vecesUsadoMul = 0;
        vecesUsadoDiv = 0;
        tiempoPorNumero = new ArrayList<>();
        score = 0;
        tiempoTotalJugado = 0;
        mejorTiempo = 0;
    }

    // métodos setters y getters de la clase uservideogame

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
        if (tiempoPorNumero == null) {
            tiempoPorNumero = new ArrayList<>();
        }
        return tiempoPorNumero;
    }

    public void setTiempoPorNumero(ArrayList<String> tiempoPorNumero) {
        this.tiempoPorNumero = tiempoPorNumero;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    public void setTiempoTotalJugado(int tiempoTotalJugado) {
        this.tiempoTotalJugado = tiempoTotalJugado;
    }

    public int getMejorTiempo() {
        return mejorTiempo;
    }

    public void setMejorTiempo(int mejorTiempo) {
        this.mejorTiempo = mejorTiempo;
    }

    // método para inicializar campos transitorios después de la deserialización
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // deserializa los campos no transitorios
        if (tiempoPorNumero == null) {
            tiempoPorNumero = new ArrayList<>();
        }
    }
}
