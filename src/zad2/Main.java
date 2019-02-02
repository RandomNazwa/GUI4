/**
 *
 *  @author Trembowska Katarzyna S18233
 *
 */

package zad2;
import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

class Colour implements ActionListener {

    static Color kolorTła[] = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    int liczbaKlików;

    public Colour() {
        super();
        liczbaKlików = 0;
    }

    public void actionPerformed(ActionEvent e) {
        liczbaKlików++;
        ((JButton)e.getSource()).setBackground(kolorTła[liczbaKlików%6]);
    }

}


public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tęcza");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));

        JButton button = new JButton("Kliknij!");
        button.setFont(new Font("",Font.BOLD,30));
        button.setBackground(Color.RED);
        frame.add(button);
        Colour change = new Colour();
        button.addActionListener(change);
    }
  
}