import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Scoreboard
{
    public void leerUsuarios(ArrayList<UserVideogame> usuariosDelJuego)
    {
        int cantidadDeUsuarios = usuariosDelJuego.size();

        // Para acceder aquí debe existir por lo menos un 
        if(cantidadDeUsuarios > 0)
        {
            // Settings del Frame
            JFrame scoreboardFrame = new JFrame();
            // scoreboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            scoreboardFrame.setSize(1920, 1080);

            // Mostrando los datos en formato de tabla
            String[] header = {"Nombre de usuario", "Veces usado: suma", "Veces usado: resta", "Veces usado: multiplicación", "Veces usado: división", "Puntaje"}; // Falta agregar el tiempo
            Object[][] data = new Object[3][3]; 
    
            JTable scoresTable = new JTable(data, header);
            scoresTable.getTableHeader().setBounds(50,0,700,50);
            scoresTable.setBounds(50,50,700,200);

            // scoreboardFrame.add(scoresTable);
            scoreboardFrame.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡No hay jugadores registrados en la aplicación!", "No hay usuarios registrados", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Posible clase SortByAlgo
    // public void sortByPuntaje() {
        
    // }
}
