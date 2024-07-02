import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ManageEvent {

    JFrame jFrame;
    JScrollBar jScrollBar;
    public ManageEvent(){
        jFrame = new JFrame();

        //Header
        JLabel jLabel = new JLabel("Manage Event");
        jLabel.setBounds(0,0,1400,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);


        //        //Left side
        JLabel jLabel2 = new JLabel();
        jLabel2.setBounds(0,100,300,700);
        jLabel2.setBackground(new Color(64,250,179));
        jLabel2.setOpaque(true);

        JLabel logo = new JLabel();
        logo.setBounds(1290,5,80,80);
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\learn Swing\\image\\IIEST_logo_white.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon2);



        //Create Event
        JButton jButton = new JButton("Create Event");
        jButton.setBounds(50,150,200,100);
        jButton.setBackground(new Color(1,90,50));
        jButton.setForeground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(new Color(150,252,150),1));
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Font",Font.PLAIN,20));

        //Cancel Event
        JButton jButton2 = new JButton("Cancel Event");
        jButton2.setBounds(50,260,200,100);
        jButton2.setBackground(new Color(1,90,50));
        jButton2.setForeground(Color.WHITE);
        jButton2.setBorder(BorderFactory.createLineBorder(new Color(150,252,150),1));
        jButton2.setFocusPainted(false);
        jButton2.setFont(new Font("Font",Font.PLAIN,20));



        //Manage Budjet
        JButton jButton4 = new JButton("Manage Budget");
        jButton4.setBounds(50,370,200,100);
        jButton4.setBackground(new Color(1,90,50));
        jButton4.setForeground(Color.WHITE);
        jButton4.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton4.setFocusPainted(false);
        jButton4.setFont(new Font("Font",Font.PLAIN,20));



        //Manage Sponcors
        JButton jButton6 = new JButton("Manage Sponcors");
        jButton6.setBounds(50,480,200,100);
        jButton6.setBackground(new Color(1,90,50));
        jButton6.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton6.setForeground(Color.WHITE);
        jButton6.setFocusPainted(false);
        jButton6.setFont(new Font("Font",Font.PLAIN,20));


        //show event
        JButton jButton5 = new JButton("Show Event");
        jButton5.setBounds(350,150,200,50);
        jButton5.setBackground(new Color(1,90,50));
        jButton5.setForeground(Color.WHITE);
        jButton5.setBorder(BorderFactory.createLineBorder(new Color(150,255,150),1));
        jButton5.setFocusPainted(false);
        jButton5.setFont(new Font("Font",Font.PLAIN,20));


        // BAck button
        JButton back = new JButton("Back");
        back.setBounds(350,660,150,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,20));

        //jpanel
//        JPanel panel = new JPanel();
//        panel.setBounds(300,250,1100,1100);
//        panel.setBackground(Color.getHSBColor(20,300,100));
//        panel.setLayout(null);




        //header of scroll pane : manually created  (ignore the headers they are added after creation of JScrollpane)
        JLabel header1 = new JLabel("Event Name");
        header1.setBounds(350,220,140,20);
        header1.setBackground(new Color(1,90,50));
        header1.setHorizontalAlignment(SwingConstants.CENTER);
        header1.setForeground(Color.WHITE);
        header1.setOpaque(true);

        JLabel header2 = new JLabel("Date");
        header2.setBounds(490,220,140,20);
        header2.setBackground(new Color(1,90,50));
        header2.setHorizontalAlignment(SwingConstants.CENTER);
        header2.setForeground(Color.WHITE);
        header2.setOpaque(true);

        JLabel header3 = new JLabel("Venue");
        header3.setBounds(630,220,140,20);
        header3.setBackground(new Color(1,90,50));
        header3.setHorizontalAlignment(SwingConstants.CENTER);
        header3.setForeground(Color.WHITE);
        header3.setOpaque(true);

        JLabel header4 = new JLabel("Time");
        header4.setBounds(770,220,140,20);
        header4.setBackground(new Color(1,90,50));
        header4.setHorizontalAlignment(SwingConstants.CENTER);
        header4.setForeground(Color.WHITE);
        header4.setOpaque(true);

        JLabel header5 = new JLabel("Duration");
        header5.setBounds(910,220,140,20);
        header5.setBackground(new Color(1,90,50));
        header5.setHorizontalAlignment(SwingConstants.CENTER);
        header5.setForeground(Color.WHITE);
        header5.setOpaque(true);

        JLabel header6 = new JLabel("Budget");
        header6.setBounds(1050,220,140,20);
        header6.setBackground(new Color(1,90,50));
        header6.setHorizontalAlignment(SwingConstants.CENTER);
        header6.setForeground(Color.WHITE);
        header6.setOpaque(true);

        JLabel header7 = new JLabel("Description");
        header7.setBounds(1190,220,160,20);
        header7.setBackground(new Color(1,90,50));
        header7.setHorizontalAlignment(SwingConstants.CENTER);
        header7.setForeground(Color.WHITE);
        header7.setOpaque(true);

        jFrame.add(header1);
        jFrame.add(header2);
        jFrame.add(header3);
        jFrame.add(header4);
        jFrame.add(header5);
        jFrame.add(header6);
        jFrame.add(header7);


        //show the events list to the page from database
        JTable table = new JTable();
        table.setBackground(Color.getHSBColor(20,300,100));
        table.setFont(new Font("Serif",Font.BOLD,15));

        JScrollPane jScrollPane = new JScrollPane(table);
        table.setTableHeader(null);
        jScrollPane.setBounds(350,240,1000,400);
        jScrollPane.setBackground(Color.getHSBColor(20,300,100));
        jScrollPane.getViewport().setBackground(Color.getHSBColor(20,300,100));
        jFrame.add(jScrollPane);


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");
            String eventInfo = "select * from event";
            ResultSet resultSet = connection.createStatement().executeQuery(eventInfo);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e){
            e.printStackTrace();
        }


        //create Event Action Listner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateEvent();
            }
        });


        //Cancel Event Action Listner
        jButton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CancelEvent();
            }
        });



        //budget management Action Listner
        jButton4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new manageBudget();
            }
        });

        jButton5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               jFrame.setVisible(false);
               new ManageEvent();

            }
        });

        //Manage Sponsors Action Listner
        jButton6.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageSponsors();
            }
        });


        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Major();
            }
        });

        jFrame.add(logo);
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(jButton2);
        jFrame.add(jButton4);
        jFrame.add(jButton5);
        jFrame.add(jButton6);
        jFrame.add(jLabel2);
        jFrame.add(back);



        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setResizable(false);

        jFrame.setBounds(100,10,1400,800);
        jFrame.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jFrame.setLayout(null);
        jFrame.setVisible(true);


        jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String args[]){
        new ManageEvent();
    }
}
















