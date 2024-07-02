import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ShowEvent{

    JFrame jFrame;

    public ShowEvent(){
        jFrame =  new JFrame();

        //Header
        JLabel jLabel = new JLabel("Event List");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //header of scroll pane : manually created  (ignore the headers they are added after creation of JScrollpane)
        JLabel header1 = new JLabel("Event Name");
        header1.setBounds(50,155,140,20);
        header1.setBackground(new Color(1,60,50));
        header1.setHorizontalAlignment(SwingConstants.CENTER);
        header1.setForeground(Color.WHITE);
        header1.setOpaque(true);

        JLabel header2 = new JLabel("Date");
        header2.setBounds(190,155,140,20);
        header2.setBackground(new Color(1,60,50));
        header2.setHorizontalAlignment(SwingConstants.CENTER);
        header2.setForeground(Color.WHITE);
        header2.setOpaque(true);

        JLabel header3 = new JLabel("Venue");
        header3.setBounds(330,155,140,20);
        header3.setBackground(new Color(1,60,50));
        header3.setHorizontalAlignment(SwingConstants.CENTER);
        header3.setForeground(Color.WHITE);
        header3.setOpaque(true);

        JLabel header4 = new JLabel("Time");
        header4.setBounds(470,155,140,20);
        header4.setBackground(new Color(1,60,50));
        header4.setHorizontalAlignment(SwingConstants.CENTER);
        header4.setForeground(Color.WHITE);
        header4.setOpaque(true);

        JLabel header5 = new JLabel("Duration");
        header5.setBounds(610,155,140,20);
        header5.setBackground(new Color(1,60,50));
        header5.setHorizontalAlignment(SwingConstants.CENTER);
        header5.setForeground(Color.WHITE);
        header5.setOpaque(true);

        JLabel header6 = new JLabel("Budget");
        header6.setBounds(750,155,140,20);
        header6.setBackground(new Color(1,60,50));
        header6.setHorizontalAlignment(SwingConstants.CENTER);
        header6.setForeground(Color.WHITE);
        header6.setOpaque(true);

        JLabel header7 = new JLabel("Description");
        header7.setBounds(890,155,160,20);
        header7.setBackground(new Color(1,60,50));
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
        table.setBounds(0,0,1000,600);
        table.setBackground(Color.getHSBColor(20,300,100));
        table.setFont(new Font("Serif",Font.BOLD,15));

        JScrollPane jScrollPane = new JScrollPane(table);
        table.setTableHeader(null);
        jScrollPane.setBounds(50,175,1000,300);
        jScrollPane.setBackground(Color.getHSBColor(20,300,100));
        jScrollPane.setOpaque(true);
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








        //Go BAck button
        JButton back = new JButton("Go Back");
        back.setBounds(430,550,150,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,20));

        jFrame.add(jLabel);

        jFrame.add(back);



        back.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new TrackEvents(null,null);
            }
        }));




        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jFrame.setBounds(400,140,1100,670);
        jFrame.setVisible(true);




    }

    public static void main(String args[]){
       new ShowEvent();
    }




}