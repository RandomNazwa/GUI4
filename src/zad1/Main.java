/**
 *
 *  @author Trembowska Katarzyna S18233
 *
 */

package zad1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {

  public static void main(String[] args) {
		
      String rozklad[] = { "North", "West", "Center", "East", "South"};
      Color kolorTla[] = {
              new Color(240, 230, 140),
              new Color(255,192, 203),
              new Color(152, 251, 152),
              new Color(176,224,230),
              new Color(216,191,216) };
      Color kolorTekstu[] = {
              new Color(255,215,0),
              new Color(220,20,60),
              new Color(0,100,0),
              new Color(70,130,180),
              new Color(75,0,130) };
      Font czcionka[] = {
              new Font("",Font.BOLD, 25),
              new Font("",Font.ITALIC,20),
              new Font("",Font.BOLD,30),
              new Font("",Font.ITALIC,40),
              new Font("",Font.BOLD, 50)  };
      String napis[] = {
              "Witam, witam",
              "SHOW",
              "MUST",
              "GO",
              "ON!" };
      String podpowiedz[] = {
              "Powitanie",
              "Tytuł",
              "piosenki",
              "zespołu",
              "Queen"};
      Border ramka[] = {
              BorderFactory.createLineBorder(Color.YELLOW, 5,true),
              BorderFactory.createEtchedBorder(Color.PINK, Color.GRAY),
              BorderFactory.createLineBorder(Color.GREEN, 10),
              BorderFactory.createEtchedBorder(Color.BLUE, Color.CYAN),
              BorderFactory.createLineBorder(Color.MAGENTA, 15,true),};
      
      JFrame frame = new JFrame();
      frame.setVisible(true);
      frame.setSize(500,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Rozkład BorderLayout");
      frame.setLocationRelativeTo(null);
      frame.setLayout(new BorderLayout());
      for (int i = 0; i <= 4; i++) {
          JLabel label = new JLabel(napis[i],JLabel.CENTER);
          frame.add(label, rozklad[i]);
          label.setBackground(kolorTla[i]);
          label.setForeground(kolorTekstu[i]);
          label.setOpaque(true);
          label.setFont(czcionka[i]);
          label.setToolTipText(podpowiedz[i]);
          label.setBorder(ramka[i]);
      }
  }
}
