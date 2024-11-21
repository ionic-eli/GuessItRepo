import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameScreen 
{
    GameScreen(UserVideogame userPlaying)
    {
        // Main Settings of the Game Frame
        JFrame gameScreenFrame = new JFrame("Guess it!");
        gameScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Game here
        gameScreenFrame.getContentPane().add(new JLabel(userPlaying.getUsername())); 

        // Last settings of the Game Frame
        gameScreenFrame.setSize(400,400);
        gameScreenFrame.setVisible(true);
    }
}
