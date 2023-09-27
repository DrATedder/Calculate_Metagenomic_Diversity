import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculate_Metagenomic_Diversity extends JFrame {
    private JTextField directoryField;
    private JTextArea outputTextArea;

    public Calculate_Metagenomic_Diversity() {
        setTitle("Calculate_Metagenomic_Diversity");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        JLabel directoryLabel = new JLabel("Input Directory:");
        directoryField = new JTextField(30);
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new BrowseButtonListener());

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        panel.add(directoryLabel, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        panel.add(directoryField, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        panel.add(browseButton, gridBagConstraints);

        JButton runAnalysisButton = new JButton("Run Analysis");
        runAnalysisButton.addActionListener(new RunAnalysisButtonListener());

        JButton startAgainButton = new JButton("Start Again");
        startAgainButton.addActionListener(new StartAgainButtonListener());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        panel.add(runAnalysisButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        panel.add(startAgainButton, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        panel.add(exitButton, gridBagConstraints);

        outputTextArea = new JTextArea(20, 60);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        outputTextArea.setMargin(new Insets(10, 10, 10, 10));
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gridBagConstraints);

        JButton authorInfoButton = new JButton("Author Info");
        authorInfoButton.addActionListener(new AuthorInfoButtonListener());

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        panel.add(authorInfoButton, gridBagConstraints);

        add(panel);
    }

    private class BrowseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(Calculate_Metagenomic_Diversity.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                directoryField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private class RunAnalysisButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputDirectory = directoryField.getText();
            if (inputDirectory.isEmpty()) {
                JOptionPane.showMessageDialog(Calculate_Metagenomic_Diversity.this,
                        "Please select an input directory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            outputTextArea.setText(""); // Clear previous output

            try {
                Process process = Runtime.getRuntime().exec("python alpha_beta_diversity.py " + inputDirectory);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    outputTextArea.append(line + "\n");
                }
                reader.close();
                process.waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                outputTextArea.append("An error occurred while running the analysis.\n");
            }
        }
    }

    private class StartAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            directoryField.setText(""); // Clear the input directory field
            outputTextArea.setText(""); // Clear the output text area
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0); // Exit the application
        }
    }

    private class AuthorInfoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Calculate_Metagenomic_Diversity.this,
                    "Author: Dr. Andrew Tedder\nUniversity of Bradford", "Author Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculate_Metagenomic_Diversity app = new Calculate_Metagenomic_Diversity();
            app.setVisible(true);
        });
    }
}
