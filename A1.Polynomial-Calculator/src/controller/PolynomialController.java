package controller;

import model.Polynomial;
import model.PolynomialOperations;
import view.PolynomialView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialController {

    private final PolynomialView polynomialView;
    private Polynomial polynomial1;
    private Polynomial polynomial2;
    private PolynomialOperations polynomialOperations;

    public PolynomialController(PolynomialView polynomialView) {
        this.polynomialView = polynomialView;
    }

    public Polynomial createPolynomialFromString(String polynomial){

        HashMap<Integer, Double> p = new HashMap<>();
        Pattern monomial = Pattern.compile("[-+]?[0-9]+(?:\\.[0-9]+)?\\*[xX]\\^[0-9]+");
        Matcher matchMonomial = monomial.matcher(polynomial);
        boolean ok = false;
        while(matchMonomial.find()) {
            String matchedMonomial = matchMonomial.group(0);
            ok = true;
            double coefficient = Double.parseDouble(matchedMonomial.split("\\*")[0]);
            int degree = Integer.parseInt(matchedMonomial.split("\\^")[1]);
            // create a polynomial term using the extracted coefficient and degree
            // add the polynomial term to the polynomial
            p.put(degree, coefficient);
        }

        if (!ok) {
            throw new IllegalArgumentException("Invalid input! The input must respect the format +coefficient*x^degree");
        }

        return new Polynomial(p.size() - 1, p);
    }

    public void operationsListeners(){

        polynomialView.addAdditionActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomial2 = createPolynomialFromString(polynomialView.getPolynomial2());
                    polynomialView.setResult(polynomialOperations.addPolynomials(polynomial1, polynomial2).toString());
                }catch(Exception e1){
                    //TODO: handle exception
                }
            }
        });

        polynomialView.addSubstractionActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomial2 = createPolynomialFromString(polynomialView.getPolynomial2());
                   polynomialView.setResult(polynomialOperations.substractPolynomials(polynomial1, polynomial2).toString());
                }catch(Exception e2){
                    //TODO: handle exception
                }
            }
        });

        polynomialView.addMultiplicationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomial2 = createPolynomialFromString(polynomialView.getPolynomial2());
                    polynomialView.setResult(polynomialOperations.multiplyPolynomials(polynomial1, polynomial2).toString());
                }catch(Exception e3){
                    //TODO: handle exception
                }
            }
        });

        polynomialView.addDivisionActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomial2 = createPolynomialFromString(polynomialView.getPolynomial2());
                    String result;
                    result = polynomialOperations.dividePolynomials(polynomial1, polynomial2).get(0).toString();
                    result +=", ";
                    result += polynomialOperations.dividePolynomials(polynomial1, polynomial2).get(1).toString();
                    polynomialView.setResult(result);
                }catch(Exception e4){
                    //TODO: handle exception
                }
            }
        });

        polynomialView.addIntegrationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomialView.setResult(polynomialOperations.integratePolynomial(polynomial1).toString());
                }catch(Exception e5){
                    //TODO: handle exception
                }
            }
        });

        polynomialView.addDerivationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    polynomial1 = createPolynomialFromString(polynomialView.getPolynomial1());
                    polynomialView.setResult(polynomialOperations.derivatePolynomial(polynomial1).toString());
                }catch(Exception e6){
                    //TODO: handle exception
                }
            }
        });
    }


}
