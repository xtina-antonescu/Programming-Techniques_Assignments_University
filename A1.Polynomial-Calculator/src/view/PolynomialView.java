package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialView extends JFrame{

    //define all the components of the window
    private static final JLabel title = new JLabel("Polynomial Calculator");
    private static final JLabel polynomial1 = new JLabel("P(x):");
    private static final JLabel polynomial2 = new JLabel("Q(x):");
    private static final JLabel result = new JLabel("Result:");
    private static final JTextField introduceP1= new JTextField("");
    private static final JTextField introduceP2 = new JTextField("");
    private static final JTextField showResult = new JTextField("");
    private static final JPanel operations= new JPanel();
    private static final JButton opAddition=new JButton("Addition");
    private static final JButton opSubstraction=new JButton("Substraction");
    private static final JButton opMultiplication=new JButton("Multiplication");
    private static final JButton opDivision=new JButton("Division");
    private static final JButton opDerivative=new JButton("Derivative");
    private static final JButton opIntegration=new JButton("Integration");

    //style the window
    public PolynomialView(){
        setTitle("Polynomial Calculator");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0));
        this.getContentPane().setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
    }

    private void addComponents(){
        addTitle();
        addLabel1();
        addLabel2();
        addResult();
        addIntroduceP1();
        addIntroduceP2();
        addShowResult();
        addOperations();
    }

    //style each component of the window individually
    private void addTitle(){
        title.setBounds(290, 10, 700, 30);
        title.setVisible(true);
        title.setFont(new Font("Roboto", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        add(title);
    }

    private void addLabel1(){
        polynomial1.setBounds(70,50, 80,30);
        polynomial1.setVisible(true);
        polynomial1.setFont(new Font("Roboto", Font.BOLD, 15));
        polynomial1.setForeground(Color.WHITE);
        add(polynomial1);
    }

    private void addLabel2(){
        polynomial2.setBounds(70, 90, 80, 30);
        polynomial2.setVisible(true);
        polynomial2.setFont(new Font("Roboto", Font.BOLD, 15));
        polynomial2.setForeground(Color.WHITE);
        add(polynomial2);
    }

    private void addResult(){
        result.setBounds(70, 130, 80, 30);
        result.setVisible(true);
        result.setFont(new Font("Roboto", Font.BOLD, 15));
        result.setForeground(Color.WHITE);
        add(result);
    }

    private void addIntroduceP1(){
        introduceP1.setBounds(150,50, 500,30);
        introduceP1.setVisible(true);
        introduceP1.setFont(new Font("Roboto", Font.BOLD, 15));
        introduceP1.setForeground(Color.BLACK);
        add(introduceP1);
    }

    private void addIntroduceP2(){
        introduceP2.setBounds(150, 90, 500, 30);
        introduceP2.setVisible(true);
        introduceP2.setFont(new Font("Roboto", Font.BOLD, 15));
        introduceP2.setForeground(Color.BLACK);
        add(introduceP2);
    }

    private void addShowResult(){
        showResult.setBounds(150,130,500, 30);
        showResult.setVisible(true);
        showResult.setFont(new Font("Roboto", Font.BOLD, 15));
        showResult.setForeground(Color.BLACK);
        add(showResult);
    }



    private void addOperations(){
        operations.setBounds(150, 300, 500, 200);
        operations.setVisible(true);
        operations.setLayout(new GridLayout(2,3,20,30));
        opAddition.setBackground(new Color(255, 165, 0));
        opSubstraction.setBackground(new Color(255, 165, 0));
        opMultiplication.setBackground(new Color(255, 165, 0));
        opDivision.setBackground(new Color(255, 165, 0));
        opDerivative.setBackground(new Color(255, 165, 0));
        opIntegration.setBackground(new Color(255, 165, 0));
        operations.add(opAddition);
        operations.add(opSubstraction);
        operations.add(opMultiplication);
        operations.add(opDivision);
        operations.add(opDerivative);
        operations.add(opIntegration);
        operations.setBackground(Color.BLACK);
        add(operations);
    }

    public String getPolynomial1(){
        return introduceP1.getText();
    }

    public String getPolynomial2(){
        return introduceP2.getText();
    }

    public void setResult(String result){
        showResult.setText(result);
    }

    public void addAdditionActionListener(final ActionListener actionListener){
        opAddition.addActionListener(actionListener);
    }

    public void addSubstractionActionListener(final ActionListener actionListener){
        opSubstraction.addActionListener(actionListener);
    }

    public void addMultiplicationActionListener(final ActionListener actionListener){
        opMultiplication.addActionListener(actionListener);
    }

    public void addDivisionActionListener(final ActionListener actionListener){
        opDivision.addActionListener(actionListener);
    }

    public void addDerivationActionListener(final ActionListener actionListener){
        opDerivative.addActionListener(actionListener);
    }

    public void addIntegrationActionListener(final ActionListener actionListener){
        opIntegration.addActionListener(actionListener);
    }
}
