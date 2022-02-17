import javax.swing.*;
import java.awt.event.ActionListener;

public class View {

    private JFrame framePP;

    private JPanel panel;

    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;

    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextField textField6;

    private JRadioButton rButton;
    private JRadioButton rButton_1;
    private JRadioButton rButton_2;
    private JRadioButton rButton_3;
    private ButtonGroup checkboxes;

    private JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    public View() {
        initialize();
    }
    private void textFields() {
        // textFields

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(35, 20, 30, 30);
        panel.add(textField);

        textField1 = new JTextField();
        textField1.setColumns(10);
        textField1.setBounds(135, 20, 30, 30);
        panel.add(textField1);

        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(232, 20, 30, 30);
        panel.add(textField2);

       textField3 = new JTextField();
        textField3.setColumns(10);
        textField3.setBounds(320, 20, 30, 30);
        panel.add(textField3);

        textField4 = new JTextField();
        textField4.setColumns(10);
        textField4.setBounds(450, 20, 30, 30);
        panel.add(textField4);
        
        textField5 = new JTextField();
        textField5.setColumns(10);
        textField5.setBounds(50, 80, 30, 30);
        panel.add(textField5);

        textField6 = new JTextField();
        textField6.setColumns(10);
        textField6.setBounds(180, 80, 30, 30);
        panel.add(textField6);

        textArea_1=new JTextArea();
        textArea_1.setColumns(10);
        textArea_1.setBounds(5, 220, 500,500);
        textArea_1.setEditable(false);
        panel.add(textArea_1);



    }

    private void labels() {
        // labels
        label = new JLabel("Timp de simulare:");
        label.setBounds(5, 5, 100, 15);
        panel.add(label);


       label_1 = new JLabel("Nr. clienti:");
        label_1.setBounds(125, 5, 100, 15);
        panel.add(label_1);


        label_2 = new JLabel("Nr. cozi:");
        label_2.setBounds(220, 5, 100, 15);
        panel.add(label_2);

        label_3 = new JLabel("Timp minim sosire:");
        label_3.setBounds(280, 1, 150, 22);
        panel.add(label_3);

        label_4 = new JLabel("Timp maxim sosire:");
        label_4.setBounds(400, 1, 150, 22);
        panel.add(label_4);

        label_5 = new JLabel("Timp minim procesare:");
        label_5.setBounds(5, 60, 150, 22);
        panel.add(label_5);

        label_6 = new JLabel("Timp maxim procesare:");
        label_6.setBounds(150, 60, 150, 22);
        panel.add(label_6);


    }



    private void buttons() {

        // buton
        button = new JButton("Confirma");
        button.setFocusable(false);


        button.setBounds(300, 80, 99, 22);
        panel.add(button);


    }

    private void initialize() {

//frameul
        framePP = new JFrame();
        framePP.setTitle("Simulator");
        framePP.setSize(600, 500);
        framePP.setBounds(100, 300, 600, 700);
        framePP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePP.getContentPane().setLayout(null);

//panel
        panel = new JPanel();
        panel.setBounds(29, 74, 600, 500);
        framePP.getContentPane().add(panel);
        panel.setLayout(null);

       labels();
        textFields();
        buttons();

        framePP.setVisible(true);
    }

    private String getPolynomial1(){
        return textField.getText();
    }
    private String getPolynomial2(){
        return textField1.getText();
    }
    public void confirm(ActionListener listenerForConfirm)
    {
        button.addActionListener(listenerForConfirm);
    }

    public void setText(String text)
    {
        textArea_1.setText(text);
    }

    public String getSimulationTime()
    {
        return textField.getText();

    }

    public String getNrClients()
    {
        return textField1.getText();

    }
    public String getNrQueues()
    {
        return textField2.getText();

    }

    public String getMinimumArrivalTime()
    {
        return textField3.getText();

    }


    public String getMaximumArrivalTime()
    {
        return textField4.getText();

    }

    public String getMaximumProcessingTime()
    {
        return textField5.getText();

    }

    public String getMinimumProcessingTime()
    {
        return textField6.getText();

    }






}
