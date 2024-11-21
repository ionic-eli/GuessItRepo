import java.awt.*;
import javax.swing.*;

public class SelectUserToPlay 
{
    @SuppressWarnings("unused")
    SelectUserToPlay(UserManager userManager)
    {
        // Creación de la lista para mostrar los datos
        JList<String> listaConUsuarios = new JList<>(userManager.selectUserList());
        listaConUsuarios.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listaConUsuarios.setLayoutOrientation(JList.VERTICAL); // Tal vez a futuro se pueda hacer un vertical WRAP

        JScrollPane listScroller = new JScrollPane(listaConUsuarios);
        listScroller.setPreferredSize(new Dimension(250, 200));

        // GUI stuff
        JFrame showUsernames = new JFrame("¡Selecciona tu usuario!");
        JPanel panelLayout = new JPanel(new BorderLayout());
        JPanel panelForButtons = new JPanel(new FlowLayout());

        // Compontentes de la GUI
        panelLayout.add(BorderLayout.NORTH, new JLabel("¡Selecciona a un usuario para jugar!"));
        panelLayout.add(BorderLayout.CENTER, listaConUsuarios);

        JButton backToMenu = new JButton("Volver al menú");
        JButton startMatch = new JButton("Iniciar partida");

        // Settings y eventos de los botones
        backToMenu.addActionListener(e -> InnerSelectUserToPlay.closeUserSelectionFrame(showUsernames));
        startMatch.setEnabled(false);
        listaConUsuarios.addListSelectionListener(e -> InnerSelectUserToPlay.itemSelected(listaConUsuarios.getSelectedValue(), startMatch));

        panelForButtons.add(backToMenu);
        panelForButtons.add(startMatch);
        panelLayout.add(BorderLayout.SOUTH, panelForButtons);

        // Last settings of the screen
        showUsernames.add(panelLayout);
        showUsernames.setUndecorated(true);
        showUsernames.setSize(500,300);
        showUsernames.setLocationRelativeTo(null);
        showUsernames.setVisible(true);
    }
    class InnerSelectUserToPlay 
    {
        public static void closeUserSelectionFrame(JFrame frame)
        {
            frame.dispose();
        }  
        
        public static void itemSelected(String userSelected, JButton startMatch)
        {
            startMatch.setEnabled(true); 
        }
    }
}
