package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PolynomialOperations {

    public static Polynomial addPolynomials(Polynomial polynomial1, Polynomial polynomial2){

        HashMap<Integer,Double> resultHash = new HashMap<>();
        int resultDegree = 0;

        for (Map.Entry<Integer, Double> entry1 : polynomial1.getMonomialList().entrySet()) {
            int degree = entry1.getKey();
            double coefficient = entry1.getValue();
            resultHash.put(degree, coefficient + resultHash.getOrDefault(degree,0.0));
        }

        for (Map.Entry<Integer, Double> entry2 : polynomial2.getMonomialList().entrySet()) {
            int degree = entry2.getKey();
            double coefficient = entry2.getValue();
            resultHash.put(degree, coefficient + resultHash.getOrDefault(degree,0.0));
        }

        //set the degree of the result the higher degree of the two polynomials
        resultDegree = Math.max(polynomial1.getDegree(), polynomial2.getDegree());

        return new Polynomial(resultDegree, resultHash);
    }

    public static Polynomial substractPolynomials(Polynomial polynomial1, Polynomial polynomial2) {

        HashMap<Integer,Double> resultHash = new HashMap<>();
        int resultDegree = 0;

        if(polynomial1.getDegree() < polynomial2.getDegree()){
            JOptionPane.showMessageDialog(null, "WARNING: substraction is performed in the following order: polynomial1 - polynomial2");
        }

        for (Map.Entry<Integer, Double> entry1 : polynomial1.getMonomialList().entrySet()) {
            int degree = entry1.getKey();
            double coefficient = entry1.getValue();
            resultHash.put(degree, coefficient + resultHash.getOrDefault(degree,0.0));
        }

        for (Map.Entry<Integer, Double> entry2 : polynomial2.getMonomialList().entrySet()) {
            int degree = entry2.getKey();
            double coefficient = entry2.getValue();
            resultHash.put(degree, - coefficient + resultHash.getOrDefault(degree,0.0));
        }

        //set the degree of the result the degree of the hisghest non-null polynomial
        for (Map.Entry<Integer, Double> entry : resultHash.entrySet()) {
            if (entry.getValue() != 0.0) {
                resultDegree = Math.max(resultDegree, entry.getKey());
            }
        }

        return new Polynomial(resultDegree, resultHash);
    }


    public static Polynomial multiplyPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        HashMap<Integer,Double> resultHash = new HashMap<>();
        int resultDegree = 0;

        for(Map.Entry<Integer, Double> entry1 : polynomial1.getMonomialList().entrySet()){
            for(Map.Entry<Integer, Double> entry2 : polynomial2.getMonomialList().entrySet()){
                int degree1 = entry1.getKey();
                double coefficient1 = entry1.getValue();

                int degree2 = entry2.getKey();
                double coefficient2 = entry2.getValue();

                resultHash.put(degree1 + degree2, coefficient1 * coefficient2 + resultHash.getOrDefault(degree1 + degree2,0.0));

            }
        }
        return new Polynomial(resultDegree, resultHash);
    }

    public static ArrayList<Polynomial> dividePolynomials(Polynomial polynomial1, Polynomial polynomial2) {

        //cannot divide by polynomial
        if(polynomial2.getMonomialList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ERROR: Division is not possible, cannot divide by a null polynomial");
            return null;
        }

        // Initialize variables for the quotient and remainder
        HashMap<Integer, Double> quotient = new HashMap<>();
        HashMap<Integer, Double> remainder = new HashMap<>(polynomial1.getMonomialList());
        ArrayList<Polynomial> p = new ArrayList<>();
        int degree;
        double coefficient;

        while (remainder != null && remainder.size() >= polynomial2.getMonomialList().size()) {

            //divide leading term of remainder to leading termn of polynomial2 and obtain a term t, defined by degree and coefficient
            degree = remainder.size() - polynomial2.getMonomialList().size();
            coefficient = remainder.get(remainder.size() - 1) / polynomial2.getMonomialList().get(polynomial2.getMonomialList().size() - 1);

            HashMap<Integer, Double> t = new HashMap<>();
            t.put(degree, coefficient);

            HashMap<Integer, Double> product = new HashMap<>();
            HashMap<Integer, Double> substr = new HashMap<>();
            HashMap<Integer, Double> add = new HashMap<>();
            HashMap<Integer, Double> add2 = new HashMap<>();

            add2.putAll(addPolynomials(new Polynomial(quotient.size() - 1, quotient), new Polynomial(t.size() - 1, t)).getMonomialList());
            quotient.clear();
            quotient.putAll(add2);
            product.putAll(multiplyPolynomials(new Polynomial(t.size() - 1, t), polynomial2).getMonomialList());
            substr.putAll(substractPolynomials(new Polynomial(remainder.size() - 1, remainder), new Polynomial(product.size() - 1, product)).getMonomialList());

            // Check if any monomials were removed during the subtraction step
            if (substr.size() == remainder.size()) {
                break;
            }

            remainder.clear();
            remainder.putAll(substr);
        }

        Polynomial p1 = new Polynomial(polynomial1.getDegree() - polynomial2.getDegree(), quotient);
        Polynomial p2 = new Polynomial(remainder.size() - 1, remainder);

        p.add(p1);
        p.add(p2);
        return p;
    }


    public static Polynomial derivatePolynomial(Polynomial polynomial){
        HashMap<Integer,Double> resultHash = new HashMap<>();
        int resultDegree = polynomial.getDegree();


        for (Map.Entry<Integer, Double> entry : polynomial.getMonomialList().entrySet()) {
            int degree = entry.getKey();
            double coefficient = entry.getValue();
            if(degree > 0) {
                resultHash.put(degree - 1, coefficient * degree);
            }
        }

        return new Polynomial(resultDegree, resultHash);
    }

    public static Polynomial integratePolynomial(Polynomial polynomial){
        HashMap<Integer,Double> resultHash = new HashMap<>();
        int resultDegree = polynomial.getDegree();

        for (Map.Entry<Integer, Double> entry : polynomial.getMonomialList().entrySet()) {
            int degree = entry.getKey();
            double coefficient = entry.getValue();
            resultHash.put(degree + 1, coefficient / (degree + 1));
        }

        return new Polynomial(resultDegree, resultHash);
    }
}
