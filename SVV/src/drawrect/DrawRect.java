

package drawrect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DrawRect extends JFrame {
    

    //angle 
      double angle  = 0.00f;
   
     // Instantiates a new draw rectangle
    
     public DrawRect() {
        super("The Balance and Neurological Physical Therapy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawRectPanel()); //calls panel for the triangle 
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
     
     
       // Class DrawRectPanel 
    
    private class DrawRectPanel extends JPanel implements MouseListener,
                    MouseMotionListener, ActionListener, KeyListener  {
        
        private Point clickPoint;

        
         // initiates a new draw triangle panel.
        
        public DrawRectPanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            clickPoint = new Point(200, 200);  //changes location 
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);  //panel size
        }
        
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
             g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  //attempt to clear up image 
           
            AffineTransform at = new AffineTransform();
            int x = clickPoint.x - (15);  //divided by 2
            int y = clickPoint.y - (150);
            at.translate(x, y);
            at.rotate(Math.toRadians(angle), clickPoint.x - x, clickPoint.y - y);
            g2d.setTransform(at);
            
            g2d.fillRect(0, 0, 30, 300);  // width by height rect
            g2d.setColor(Color.RED);
            g2d.drawRect(0, 0, 30, 300);
          
            g2d.dispose();

        }
        
        //mouse listener for movement 
        @Override  
        public void mousePressed(MouseEvent e) 
        {
        repaint();  //back to paint 
        }

       
        @Override
        public void mouseReleased(MouseEvent e) {
        }

       
        public void mouseDragged(MouseEvent e) {
            angle = -Math.toDegrees(Math.atan2(e.getPoint().x - clickPoint.x, e.getPoint().y - clickPoint.y)) + 180;  //angle calcuation passed on mouse click 
            
            repaint();
        }

      
       public void mouseEntered(MouseEvent e) {
           
       }

      
       public void mouseExited(MouseEvent e) {

        }

       public void mouseClicked(MouseEvent e) {
        }

       public void mouseMoved(MouseEvent e) {
       }
        
        
        @Override
         public void keyPressed(KeyEvent e)
        {
            int code = e.getKeyCode();
     

                if(code==KeyEvent.VK_ENTER)
                 {
                    System.out.printf("The angle is %.02f\n", angle);
                 }
    
        }
    
        public void keyTyped(KeyEvent e){
       
        }
        
        public void keyReleased(KeyEvent e){
        
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }  //end of panel class 

   
     // main method.
        

    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                new DrawRect();  //starts class 
            }
        });
    }  //end main 

}  //end DrawTriangle class
