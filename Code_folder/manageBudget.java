import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class manageBudget{

    JDialog jDialog;

    public manageBudget(){
        jDialog =  new JDialog();

        //Header
        JLabel jLabel = new JLabel("Manage Budjet");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //Event Name
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(50,150,200,40);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventTestField = new JTextField();
        eventTestField.setBounds(50,200,200,40);

        //Event Id
        JLabel eventDate = new JLabel("Event Date");
        eventDate.setBounds(50,250,200,40);
        eventDate.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventDate2 = new JTextField();
        eventDate2.setBounds(50,300,200,40);

        //Updated budget
        JLabel updateBudget = new JLabel("Update Budjet");
        updateBudget.setBounds(50,350,200,40);
        updateBudget.setFont(new Font("Serif",Font.BOLD,20));

        JTextField updateBudget2 = new JTextField();
        updateBudget2.setBounds(50,400,200,40);


        //details button
        JButton details = new JButton("Click to Update");
        details.setBounds(350,150,300,40);
        details.setBackground(new Color(1,60,50));
        details.setForeground(Color.WHITE);
        details.setFont(new Font("Serif",Font.BOLD,20));

        JLabel total = new JLabel("");
        total.setBounds(350,200,500,40);
        total.setFont(new Font("Serif",Font.BOLD,30));
        total.setForeground(new Color(100,150,10));



        //Go BAck button
        JButton back = new JButton("Go Back");
        back.setBounds(430,500,150,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,20));

        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventTestField);
        jDialog.add(eventDate);
        jDialog.add(eventDate2);
        jDialog.add(updateBudget);
        jDialog.add(updateBudget2);
        jDialog.add(details);
        jDialog.add(back);
        jDialog.add(total);


        details.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                    PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                            "update event set budget = ? where name = ? and date = ?"
                    );
                    st.setString(1,updateBudget2.getText());
                    st.setString(2,eventTestField.getText());
                    st.setString(3,eventDate2.getText());

                    int result = st.executeUpdate();
                    if(result != 0) {
                        total.setText("The Updated event Budget is : " + updateBudget2.getText());
                    }else{
                        total.setText("No such Event exist on that date");
                    }


                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }


            }
        });

        back.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        }));


        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jDialog.setIconImage(icon.getImage());
        jDialog.setResizable(false);

        jDialog.setLayout(null);
        jDialog.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jDialog.setBounds(400,140,1100,670);
        jDialog.setModal(true);
        jDialog.setVisible(true);

        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


    }

    public static void main(String args[]){
        new manageBudget();
    }




}