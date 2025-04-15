import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CricketQuizGUI extends JFrame implements ActionListener {
    String[][] questions = {
        {"Who is the highest run scorer in international cricket?", "Sachin Tendulkar", "Virat Kohli", "Ricky Ponting", "MS Dhoni", "1"},
        {"Which country won the first ICC T20 World Cup?", "India", "Australia", "Pakistan", "England", "1"},
        {"Who has taken the most wickets in Test cricket?", "Shane Warne", "Anil Kumble", "Muttiah Muralitharan", "James Anderson", "3"},
        {"Which Indian player is known as 'The Hitman'?", "Virat Kohli", "KL Rahul", "Rohit Sharma", "Hardik Pandya", "3"},
        {"Where was the 2023 Cricket World Cup final held?", "Mumbai", "Ahmedabad", "Delhi", "Kolkata", "2"},
        {"Which team won the 2023 Cricket World Cup?", "1. India", "2. England", "3. Australia", "4. New Zealand", "3"}

    };

    int currentQuestion = 0, score = 0;
    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton;

    public CricketQuizGUI() {
        setTitle("Cricket Quiz Game");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question here");
        questionLabel.setForeground(Color.BLUE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();

        setVisible(true);
	addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        JOptionPane.showMessageDialog(null, "Thanks for playing!");
    }
});
    }

    void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText("Q" + (currentQuestion + 1) + ". " + questions[currentQuestion][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
                options[i].setSelected(false);
            }
        } else {
            showScore();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (currentQuestion < questions.length) {
            String correct = questions[currentQuestion][5];
            for (int i = 0; i < 4; i++) {
                if (options[i].isSelected() && (i + 1) == Integer.parseInt(correct)) {
                    score++;
                }
            }
            currentQuestion++;
            loadQuestion();
        }
    }

    void showScore() {
        JOptionPane.showMessageDialog(this, "Quiz Over!\nYour Score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
          JOptionPane.showMessageDialog(null, "Welcome to the Cricket Quiz Game!");
    new CricketQuizGUI();
    }
}
