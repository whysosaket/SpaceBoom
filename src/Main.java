import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    //setting up background
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("SpaceBoom");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);


        Game game = new Game();

        //this.add(background);
        frame.add(game);
        frame.setVisible(true);
    }

}
