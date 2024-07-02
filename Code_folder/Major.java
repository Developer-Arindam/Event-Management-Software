import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class Major {

    JFrame jFrame;
    public Major(){
        jFrame = new JFrame();

        //Header
        JLabel jLabel = new JLabel("Event Management Software");
        jLabel.setBounds(0,0,1400,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

//        //Left side
//        JLabel jLabel2 = new JLabel();
//        jLabel2.setBounds(0,100,300,700);
//        jLabel2.setBackground(new Color(100,150,100));
//        jLabel2.setOpaque(true);

        //right side
        JLabel right = new JLabel();
        right.setBounds(0,100,1400,700);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\image\\image2.gif").getImage().getScaledInstance(1400, 700, Image.SCALE_DEFAULT));
        right.setIcon(imageIcon);

        JLabel logo = new JLabel();
        logo.setBounds(1290,5,80,80);
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src\\image\\IIEST_logo_white.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon2);


        //Change Password
        JButton jButton = new JButton("Change\n Password");
        jButton.setBounds(50,150,200,100);
        jButton.setBackground(new Color(1,90,50));
        jButton.setForeground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Font",Font.BOLD,20));

        //Event Registration
        JButton jButton2 = new JButton("Event Registration");
        jButton2.setBounds(50,260,200,100);
        jButton2.setBackground(new Color(1,90,50));
        jButton2.setForeground(Color.WHITE);
        jButton2.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton2.setFocusPainted(false);
        jButton2.setFont(new Font("Font",Font.PLAIN,20));



        //Manage Event
        JButton jButton3 = new JButton("Manage Event");
        jButton3.setBounds(50,370,200,100);
        jButton3.setBackground(new Color(1,90,50));
        jButton3.setForeground(Color.WHITE);
        jButton3.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton3.setFocusPainted(false);
        jButton3.setFont(new Font("Font",Font.PLAIN,20));


        //Track Events
        JButton jButton5 = new JButton("Track Events");
        jButton5.setBounds(50,480,200,100);
        jButton5.setBackground(new Color(1,90,50));
        jButton5.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton5.setForeground(Color.WHITE);
        jButton5.setFocusPainted(false);
        jButton5.setFont(new Font("Font",Font.PLAIN,20));

        //Log out
        JButton jButton6 = new JButton("Log out");
        jButton6.setBounds(50,590,200,100);
        jButton6.setBackground(new Color(1,90,50));
        jButton6.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton6.setForeground(Color.WHITE);
        jButton6.setFocusPainted(false);
        jButton6.setFont(new Font("Font",Font.PLAIN,20));


        jFrame.add(logo);
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(jButton2);
        jFrame.add(jButton3);
        jFrame.add(jButton5);
        jFrame.add(jButton6);
        jFrame.add(right);        //a JLabel to hold the image





        //change password action Listner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new ChangePassword();
            }
        });

        //eventRegistration action Listner
        jButton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new eventRegistration();
            }
        });


        //Manage event Action Listner
        jButton3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new login2();
            }
        });




        //Track Event Action Listner
        jButton5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrackEvents(null,null);
            }
        });



        //Logout button action listner
        jButton6.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose(); //see the differences btw setvisible(false) and dispose() and can modify in the rest of the code
            }
        });

        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setResizable(false);


        jFrame.setBounds(100,10,1400,800);
        jFrame.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jFrame.setLayout(null);
        jFrame.setVisible(true);

    }



        public static void main(String args[]){
            new Major();

    }
}
