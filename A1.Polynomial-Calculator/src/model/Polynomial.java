package model;

import java.util.HashMap;
import java.util.Map;

public class Polynomial{

    //define attribute list for a polynomial
    private int degree;
    private HashMap<Integer, Double> monomialList;

    //default constructor
    public Polynomial(){

    }

    //contructor of the polynomial class
    public Polynomial(int degree, HashMap<Integer,Double> monomialList){
        this.degree=degree;
        this.monomialList=monomialList;
    }

    //setter and getter methods for polynomial attributes
    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public HashMap<Integer, Double> getMonomialList() {
        return monomialList;
    }

    public void setMonomialList(HashMap<Integer,Double> monomialList) {
        this.monomialList = monomialList;
    }

    @Override
    public String toString() {
        String parsedString = "";
        String monomial;
        boolean isNull = true;
        for(Map.Entry<Integer,Double> entry: monomialList.entrySet()){
            monomial = "";
            Integer key = entry.getKey();
            Double value = entry.getValue();
            if(value != 0) {
                isNull = false;
                monomial += "+";
                monomial += value.toString();
                monomial += "*x^";
                monomial += key.toString();
                parsedString = monomial + parsedString;
            }
        }
        if(isNull == false) {
            return parsedString;
        }

        return "0";
    }
}
