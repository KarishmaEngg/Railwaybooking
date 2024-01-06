import javax.swing.*;
import javax.xml.stream.StreamFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //name,email,gender,from,to,doj,payment,bookingId

        JFrame frame = new JFrame("Railway Reservation System");

        JLabel namelb = new JLabel("Name:");
        JLabel emaillb = new JLabel("Email Id:");
        JLabel genderlb = new JLabel("Gender:");
        JLabel mobilelb = new JLabel("Mobile No");
        JLabel fromlb = new JLabel("From:");
        JLabel tolb = new JLabel("To:");
        JLabel dojlb = new JLabel("Date of journey");
        JLabel amountlb = new JLabel("Amount:");
        JLabel bookingIdlb = new JLabel();

        JTextField nametf = new JTextField();
        JTextField emailtf = new JTextField();
        JTextField gendertf = new JTextField();
        JTextField mobiletf = new JTextField();
        JTextField fromtf = new JTextField();
        JTextField totf = new JTextField();
        JTextField dojtf = new JTextField();
        JTextField amounttf = new JTextField();

        JButton closebt = new JButton("Close:");
        JButton bookNowbt = new JButton("Book Ticket");
        JButton clearbt = new JButton("Clear");

        //to set the size and position of components
        namelb.setBounds(20, 10, 100, 30);
        emaillb.setBounds(20, 50, 100, 30);
        genderlb.setBounds(20, 90, 100, 30);
        mobilelb.setBounds(20, 130, 100, 30);
        fromlb.setBounds(370, 10, 100, 30);
        tolb.setBounds(370, 50, 100, 30);
        dojlb.setBounds(370, 90, 100, 30);
        amountlb.setBounds(370, 130, 100, 30);

        nametf.setBounds(140, 10, 200, 30);
        emailtf.setBounds(140, 50, 200, 30);
        gendertf.setBounds(140, 90, 200, 30);
        mobiletf.setBounds(140, 130, 200, 30);
        fromtf.setBounds(480, 10, 200, 30);
        totf.setBounds(480, 50, 200, 30);
        dojtf.setBounds(480, 90, 200, 30);
        amounttf.setBounds(480, 130, 200, 30);

        clearbt.setBounds(70, 190, 100, 30);
        bookNowbt.setBounds(240, 190, 100, 30);
        closebt.setBounds(410, 190, 100, 30);
        bookingIdlb.setBounds(50, 220, 400, 50);

        frame.add(bookingIdlb);
        frame.add(namelb);
        frame.add(emaillb);
        frame.add(genderlb);
        frame.add(mobilelb);
        frame.add(fromlb);
        frame.add(tolb);
        frame.add(dojlb);
        frame.add(amountlb);


        frame.add(nametf);
        frame.add(emailtf);
        frame.add(gendertf);
        frame.add(mobiletf);
        frame.add(fromtf);
        frame.add(totf);
        frame.add(dojtf);
        frame.add(amounttf);

        frame.add(clearbt);
        frame.add(bookNowbt);
        frame.add(closebt);


        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(750, 500);
        frame.setVisible(true);
//to click on clear button
        clearbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametf.setText(" ");
                emailtf.setText("");
                gendertf.setText("");
                mobiletf.setText("");
                mobiletf.setText("");
                fromtf.setText("");
                totf.setText("");
                amounttf.setText("");
            }
        });

        closebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });

        bookNowbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //to check the to validate the form cant be empty
                if (nametf.getText().toString().isEmpty() ||
                        emailtf.getText().toString().isEmpty() ||
                        gendertf.getText().toString().isEmpty() ||
                        mobiletf.getText().toString().isEmpty() ||
                        fromtf.getText().toString().isEmpty() ||
                        totf.getText().toString().isEmpty() ||
                        amounttf.getText().toString().isEmpty()) {
                    bookingIdlb.setText("Please fill this place");
                } else {
                    String url = "jdbc:mysql://localhost:3306/railwaybooking";
                    String username = "root";
                    String password = "";
                    try {
                        Connection connection = DriverManager.getConnection(url, username, password);
                        System.out.println("Database is connected");


                        String sql = "Insert into custmerbooking(name,email,gender,mobile,from,to,doj,amount)" + "values(?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(sql);
                        preparedStmt.setString(1, nametf.getText().toString());
                        preparedStmt.setString(2, emailtf.getText().toString());
                        preparedStmt.setString(3, gendertf.getText().toString());
                        preparedStmt.setString(4, mobiletf.getText().toString());
                        preparedStmt.setString(5, fromtf.getText().toString());
                        preparedStmt.setString(6, totf.getText().toString());

                        preparedStmt.setString(7, dojtf.getText().toString());
                        preparedStmt.setString(8, amounttf.getText().toString());

                        preparedStmt.execute();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex + "not connected");
                    }
                }
                    Random random = new Random();
                    int id = random.nextInt(999999);
                    bookingIdlb.setText("Your Ticket is booked and booking id" + id);


            }
        });
}
}