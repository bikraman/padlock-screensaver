package screensaver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenSaver {
    
    class ssPanel extends JPanel implements MouseListener{
    
         public ssPanel(){
            setBackground(Color.BLACK);
            addMouseListener(this); 
         }
         
         @Override
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
        
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Graphics2D g2d = (Graphics2D) g;
      
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
            DateFormat dateFormat = new SimpleDateFormat("hh mm ss");
            Date date = new Date();
            String fDT = dateFormat.format(date);
       
            FontRenderContext context = g2d.getFontRenderContext();
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             try {
                 ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("HelveticaNeue-UltraLight.otf")));
             } catch (FontFormatException ex) {
                 Logger.getLogger(ScreenSaver.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(ScreenSaver.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            
            Font messageTextFont = new Font("HelveticaNeue-UltraLight",Font.PLAIN,225);
            TextLayout txt = new TextLayout("00:00:00", messageTextFont, context);
            Rectangle2D bounds = txt.getBounds();
            int xString = (int)((screenSize.width - (int)bounds.getWidth()) / 2 );
            int yString = (int)((screenSize.height/2) + (int)(bounds.getHeight()/2));

            g2d.setFont(messageTextFont);
            g2d.setColor(Color.white);
       
            g2d.drawString(fDT, xString, yString);
   
            Timer timer = new Timer(100, new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                   
                       repaint();  
                }
           
             });
            timer.start();
    } 

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked!");
            System.exit(0);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("pressed!");
            //System.exit(0);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("released!");
           // System.exit(0);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("entered!");
            //System.exit(0);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("exited!");
            //System.exit(0);
        }
  }
  
     
    public static void main(String[] args) {
        
      ScreenSaver instance = new ScreenSaver();
      instance.go();
       
    }  
    
    public void go(){
        
        JFrame frame = new JFrame("Story X");
        JPanel jpan = new ssPanel();
     
        frame.add(jpan);
     
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
       });
    }

}
