import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentRegistration {
    public static void main(String[] args) {
        JFrame frame = new JFrame("🎓 Student Registration Form");
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 255)); // light lavender

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel heading = new JLabel("Register Student");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setBounds(190, 20, 250, 30);
        heading.setForeground(new Color(33, 33, 99));
        frame.add(heading);

        JLabel name = new JLabel("Name:");
        name.setBounds(50, 70, 100, 30);
        name.setFont(labelFont);
        frame.add(name);
        JTextField txtName = new JTextField();
        txtName.setBounds(180, 70, 250, 30);
        txtName.setFont(fieldFont);
        frame.add(txtName);

        JLabel email = new JLabel("Email:");
        email.setBounds(50, 110, 100, 30);
        email.setFont(labelFont);
        frame.add(email);
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(180, 110, 250, 30);
        txtEmail.setFont(fieldFont);
        frame.add(txtEmail);

        JLabel mobile = new JLabel("Mobile No:");
        mobile.setBounds(50, 150, 100, 30);
        mobile.setFont(labelFont);
        frame.add(mobile);
        JTextField txtMobile = new JTextField();
        txtMobile.setBounds(180, 150, 250, 30);
        txtMobile.setFont(fieldFont);
        frame.add(txtMobile);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(50, 190, 100, 30);
        pass.setFont(labelFont);
        frame.add(pass);
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(180, 190, 250, 30);
        txtPass.setFont(fieldFont);
        frame.add(txtPass);

        JLabel dob = new JLabel("DOB (yyyy-mm-dd):");
        dob.setBounds(50, 230, 150, 30);
        dob.setFont(labelFont);
        frame.add(dob);
        JTextField txtDob = new JTextField();
        txtDob.setBounds(210, 230, 220, 30);
        txtDob.setFont(fieldFont);
        frame.add(txtDob);

        JLabel semester = new JLabel("Semester:");
        semester.setBounds(50, 270, 100, 30);
        semester.setFont(labelFont);
        frame.add(semester);
        JTextField txtSemester = new JTextField();
        txtSemester.setBounds(180, 270, 250, 30);
        txtSemester.setFont(fieldFont);
        frame.add(txtSemester);

        JLabel branch = new JLabel("Branch:");
        branch.setBounds(50, 310, 100, 30);
        branch.setFont(labelFont);
        frame.add(branch);
        JTextField txtBranch = new JTextField();
        txtBranch.setBounds(180, 310, 250, 30);
        txtBranch.setFont(fieldFont);
        frame.add(txtBranch);

        JButton btn = new JButton("Register");
        btn.setBounds(220, 370, 140, 40);
        btn.setBackground(new Color(51, 153, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        frame.add(btn);

        frame.setLayout(null);
        frame.setVisible(true);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String email = txtEmail.getText();
                String mobile = txtMobile.getText();
                String password = new String(txtPass.getPassword());
                String DOB = txtDob.getText();
                String SEMESTER = txtSemester.getText();
                String BRANCH = txtBranch.getText();

                if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty()
                        || DOB.isEmpty() || SEMESTER.isEmpty() || BRANCH.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "❗ Please fill all fields");
                    return;
                }

                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=STUDYGROUP;encrypt=false;",
                            "sa", "hacker");

                    String query = "INSERT INTO infostd (name, email, mobil_no, password, DOB, SEMESTER, BRANCH) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, mobile);
                    ps.setString(4, password);
                    try {
                        ps.setDate(5, Date.valueOf(DOB));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "❗ Invalid DOB. Use yyyy-mm-dd");
                        return;
                    }
                    ps.setString(6, SEMESTER);
                    ps.setString(7, BRANCH);

                    int i = ps.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(frame, "✅ Registration Successful!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "❌ Registration Failed");
                    }

                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "⚠️ Error: " + ex.getMessage());
                }
            }
        });
    }
}
