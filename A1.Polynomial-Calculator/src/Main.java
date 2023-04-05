import controller.PolynomialController;
import model.PolynomialOperations;
import model.Polynomial;
import view.PolynomialView;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        PolynomialView polynomialView = new PolynomialView();
        polynomialView.setVisible(true);
        PolynomialController polynomialController = new PolynomialController(polynomialView);
        polynomialController.operationsListeners();
    }
}
