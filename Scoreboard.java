import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Scoreboard {

    public void leerUsuarios(ArrayList<UserVideogame> usuariosDelJuego) {
        int cantidadDeUsuarios = usuariosDelJuego.size();

        // verificar que existan usuarios registrados
        if (cantidadDeUsuarios > 0) {
            // configuraciones del frame de la tabla de puntuaciones
            JFrame scoreboardFrame = new JFrame("Tabla de Puntuaciones");
            scoreboardFrame.setSize(800, 600);
            scoreboardFrame.setLocationRelativeTo(null);
            scoreboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // definir los encabezados de la tabla
            String[] header = {"Nombre de Usuario", "Puntaje", "Mejor Tiempo", "Tiempo Total", 
                               "Veces Usado: +", "Veces Usado: -", "Veces Usado: *", "Veces Usado: /"};

            // llenar los datos de la tabla
            Object[][] data = new Object[cantidadDeUsuarios][header.length];
            for (int i = 0; i < cantidadDeUsuarios; i++) {
                UserVideogame user = usuariosDelJuego.get(i);
                data[i][0] = user.getUsername();
                data[i][1] = user.getScore();
                data[i][2] = user.getMejorTiempo();
                data[i][3] = user.getTiempoTotalJugado();
                data[i][4] = user.getVecesUsadoSuma();
                data[i][5] = user.getVecesUsadoResta();
                data[i][6] = user.getVecesUsadoMul();
                data[i][7] = user.getVecesUsadosDiv();
            }

            // crear la tabla con los datos
            DefaultTableModel model = new DefaultTableModel(data, header);
            JTable scoresTable = new JTable(model);
            scoresTable.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(scoresTable);

            // panel superior con opciones de ordenamiento
            JPanel topPanel = new JPanel();
            JLabel sortLabel = new JLabel("Ordenar por:");
            String[] sortOptions = {"Puntaje", "Mejor Tiempo", "Tiempo Total Jugado"};
            JComboBox<String> sortDropdown = new JComboBox<>(sortOptions);

            sortDropdown.addActionListener(e -> {
                String selectedOption = (String) sortDropdown.getSelectedItem();
                sortData(usuariosDelJuego, selectedOption);
                updateTableData(model, usuariosDelJuego);
            });

            topPanel.add(sortLabel);
            topPanel.add(sortDropdown);

            // agregar componentes al frame
            scoreboardFrame.setLayout(new BorderLayout());
            scoreboardFrame.add(topPanel, BorderLayout.NORTH);
            scoreboardFrame.add(scrollPane, BorderLayout.CENTER);
            scoreboardFrame.setVisible(true);
        } else {
            // mensaje de error si no hay usuarios
            JOptionPane.showMessageDialog(null, "¡No hay jugadores registrados en la aplicación!", 
                                          "No hay usuarios registrados", JOptionPane.ERROR_MESSAGE);
        }
    }

    // método para ordenar los datos basado en la opción seleccionada
    private void sortData(ArrayList<UserVideogame> usuarios, String option) {
        switch (option) {
            case "Puntaje":
                usuarios.sort(Comparator.comparingInt(UserVideogame::getScore).reversed());
                break;
            case "Mejor Tiempo":
                usuarios.sort(Comparator.comparingInt(UserVideogame::getMejorTiempo));
                break;
            case "Tiempo Total Jugado":
                usuarios.sort(Comparator.comparingInt(UserVideogame::getTiempoTotalJugado).reversed());
                break;
        }
    }

    // método para actualizar los datos de la tabla después de ordenar
    private void updateTableData(DefaultTableModel model, ArrayList<UserVideogame> usuarios) {
        model.setRowCount(0); // borrar las filas existentes
        for (UserVideogame user : usuarios) {
            model.addRow(new Object[]{
                user.getUsername(),
                user.getScore(),
                user.getMejorTiempo(),
                user.getTiempoTotalJugado(),
                user.getVecesUsadoSuma(),
                user.getVecesUsadoResta(),
                user.getVecesUsadoMul(),
                user.getVecesUsadosDiv()
            });
        }
    }
}
