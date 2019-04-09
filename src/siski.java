/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author den4i_000
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

class MyPanel extends JPanel {
    
    private Image img;
    private int x, y;
    public String name;
    public MyPanel(String path, int x, int y, String name1) {
        try {
            img = ImageIO.read(new File(path));            
            this.setBounds(0, 0, x, y);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка загрузки файла!");
        }
        name = name1;
    }


    @Override
    public void paintComponent(Graphics gr) {
        gr.drawImage(img, 0, 0, null);
    }
}

public class siski extends javax.swing.JFrame {
    private final MyPanel man;
    private final ArrayList<MyPanel> siski;
    private final ArrayList<MyPanel> gnsiski;
    private MyPanel[] travka; 
    private final MyPanel fon;
    private final JLabel score;
    private final MyPanel bonus;
    private Timer timer;
    int CurrentSpeed = 1;
    Boolean CanAddSpeed = false;
    public siski() {
        initComponents();        
        jLabel1.setVisible(false);
        score = new JLabel();
        score.setText("0");
        score.setSize(100, 50);
        this.setSize(900, 600);
        siski = new ArrayList<MyPanel>();
        gnsiski = new ArrayList<MyPanel>();
        bonus = new MyPanel("C:\\Users\\den4i_000\\Documents\\NetBeansProjects\\siski\\src\\trava.png", 1056, 117,"bonus");
        man = new MyPanel("C:\\Users\\den4i_000\\Documents\\NetBeansProjects\\siski\\src\\a man.png", 76, 103,"Man");
        fon = new MyPanel("C:\\Users\\den4i_000\\Documents\\NetBeansProjects\\siski\\src\\ground.png", 900, 600,"fon");
        AddNewSiska();
        getContentPane().add(bonus);
        getContentPane().add(score);
        getContentPane().add(man);
        getContentPane().add(fon);
        score.setLocation(900-100, 0);
        setRandomWithPosition(bonus);
        man.setLocation(this.getWidth()/2-man.getWidth()/2, this.getHeight()-(man.getHeight()+30));
        repaint();
        Start();
    }

    private void setRandomWithPosition(MyPanel frame)
    {
        Random rnd = new Random();
        Random rndHeignt = new Random();
        int height = rndHeignt.nextInt(400);
        rnd.nextInt(getWidth()-50);
        int width = rnd.nextInt(getWidth()-50);
        frame.setLocation(width,-height);
    }
    
    private Boolean isCatch(MyPanel panel)
    {
        if (panel.getLocation().y >=man.getLocation().y-40 && 
                        (panel.getLocation().x+20>=man.getLocation().x && panel.getLocation().x<=man.getLocation().x+man.getWidth()-20))
            return true;
        return false;
    }
    
    private void GameOver()
    {
        timer.stop();
        jButton1.setText("LOSE");
        jLabel1.setVisible(true);
    }
    
    private void Start()
    {
        timer = new Timer(1, new ActionListener() 
        {
            @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    for (MyPanel obj:siski)
                    {
                      MoveObj(obj);
                    } 
                    for (MyPanel obj:gnsiski)
                    {
                      MoveObj(obj);
                    } 
                    MoveObj(bonus);
                    //MoveObj(siski);
                }                
        }
        );
        //timer.start();
    }
    private void AddNewSiska()
    {
        siski.add((new MyPanel("C:\\Users\\den4i_000\\Documents\\NetBeansProjects\\siski\\src\\siska.png", 50,50,"siska")));
        getContentPane().add(siski.get(siski.size()-1));
        setRandomWithPosition(siski.get(siski.size()-1));
    }
    private void CatchBonus()
    {
        Integer tempScore = Integer.parseInt(score.getText())+3;
        score.setText(tempScore.toString());
        CurrentSpeed++;
        gnsiski.add(new MyPanel("C:\\Users\\den4i_000\\Documents\\NetBeansProjects\\siski\\src\\gnsiska.png", 50,50,"gnil"));
        for (MyPanel siska:gnsiski)
        {
            getContentPane().add(siska);
        }
        AddNewSiska();       
        getContentPane().add(man); 
        getContentPane().add(fon);
        repaint();
    }
    private void MoveObj(MyPanel frame)
    {
        if (Integer.parseInt(score.getText())<0)
                        GameOver();
        if (Integer.parseInt(score.getText())>50)
        {
        timer.stop();
        jButton1.setText("WIN");
        jLabel1.setText("YOU WIN");
        jLabel1.setVisible(true);
        }
                    frame.setLocation(frame.getLocation().x, frame.getLocation().y+CurrentSpeed);                            
                        
                    if (frame.getLocation().y > 600)
                        {
                            if (frame.name=="siska")
                            {
                                Integer scoretemp = Integer.parseInt(score.getText())-1;
                                score.setText(scoretemp.toString());                                
                            }                                
                            setRandomWithPosition(frame);
                        }
                    if (isCatch(frame))
                     {
                        if (frame.name == "siska")
                        {
                            Integer tempScore = Integer.parseInt(score.getText())+1;
                            score.setText(tempScore.toString());                            
                        }
                        else if (frame.name=="gnil")     
                        {
                            Integer scoretemp = Integer.parseInt(score.getText())-1;
                            score.setText(scoretemp.toString());
                        }
                        else if (frame.name=="bonus")
                            CatchBonus();
                        setRandomWithPosition(frame); 
                    }
                    repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ground.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Impact", 0, 150)); // NOI18N
        jLabel1.setText("Game over");

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(676, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (jButton1.getText()=="Start")
            return;
        int key = evt.getKeyCode();
            if (key == 39) {
                if (man.getLocation().x+20<getSize().width-man.getWidth())
                    man.setLocation(man.getLocation().x+20, man.getLocation().y);
            }
            if (key == 37) {
                if (man.getLocation().x-20>0)
                    man.setLocation(man.getLocation().x-20, man.getLocation().y);
            }
            repaint();
    }//GEN-LAST:event_formKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //jButton1.setVisible(false);
        //jButton1.setEnabled(false);  
        if (jButton1.getText()=="Start")
        {
            timer.start();
            jButton1.setText("Pause");
        }
        else if (jButton1.getText()=="Pause")
        {
            timer.stop();
            jButton1.setText("Start");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(siski.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(siski.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(siski.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(siski.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new siski().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
