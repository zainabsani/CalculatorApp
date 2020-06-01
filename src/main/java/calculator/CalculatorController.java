package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        Button button = (Button) event.getSource();
        String pM = "\u00B1";

        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
            if (operator.isEmpty()) {
                return;
            }
            double number2 = Double.parseDouble(display.getText());
            double result = calculator.calculate(number1, number2, operator);
            display.setText(String.format("%.2f", result));
            operator = "";
        }
        else if (operatorPressed.equals("AC")) {
            display.setText("");
            //display.clear();
        }
        else if (operatorPressed.equals("\u00B1")) {
            int num = Integer.parseInt(display.getText());
            num = num * (-1);
            display.setText(String.valueOf(num));
        }
        else if(operatorPressed.equals(".")){
            display.setText(display.getText()+".");
        }
        else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

}
/*
else if(operatorPressed.equals("&#177;")){
            int num = Integer.parseInt(display.getText());
            if(num == 0){
                display.getText();
            }
            else {
                num = num * (-1);
                display.setText(String.valueOf(num));
            }
        }
 */