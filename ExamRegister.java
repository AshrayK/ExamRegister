package SwingPractice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExamRegister extends JFrame {
    public static void main(String[] args) {
        new ExamRegister();
    }
    private List<String> registeredCourses = new ArrayList<>();

    public ExamRegister() {
        setTitle("Exam Registration");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JTextField regNumberField = new JTextField(25);
        JTextField courseField = new JTextField(25);

        JButton rButton = new JButton("Register");
        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNumber = regNumberField.getText();
                String course = courseField.getText();

                if (registeredCourses.stream().anyMatch(x -> x.equals(regNumber + "-" + course))) {
                    JOptionPane.showMessageDialog(null, "Student has already registered for this subject already.");
                } else {
                    registeredCourses.add(regNumber + "-" + course);
                    JOptionPane.showMessageDialog(null, "Registration successful.");
                }
            }
        });

        JButton dispButton = new JButton("View");
        dispButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNumber = regNumberField.getText();
                List<String> studentCourses = registeredCourses.stream()
                        .filter(c -> c.startsWith(regNumber + "-"))
                        .collect(Collectors.toList());

                if (studentCourses.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Student has not registered for any course.");
                } else {
                    StringBuilder courses = new StringBuilder();
                    int i=1;
                    for (String course : studentCourses) {
                        courses.append(i +") ").append(course.split("-")[1]).append("\n");
                        i++;
                    }
                    JOptionPane.showMessageDialog(null, "List of Courses registered by student: \n" + courses.toString());
                }
            }
        });
        panel.add(new JLabel("Enter Registration Number:"));
        panel.add(regNumberField);
        panel.add(new JLabel("Enter Course:"));
        panel.add(courseField);
        panel.add(new JLabel("Do you want to Register:"));
        panel.add(rButton);
        panel.add(new JLabel("Do you want to View Table:"));
        panel.add(dispButton);
        setVisible(true);
    }
}
