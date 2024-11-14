package com.example.unicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String lastInput = "";
    private String operator = "";
    private double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Button initialization
        Button btnMC = findViewById(R.id.btnMC);
        Button btnMR = findViewById(R.id.btnMR);
        Button btnMS = findViewById(R.id.btnMS);
        Button btnMPlus = findViewById(R.id.btnMPlus);
        Button btnC = findViewById(R.id.btnC);
        Button btnCE = findViewById(R.id.btnCE);
        Button btnSign = findViewById(R.id.btnSign);
        Button btnSqrt = findViewById(R.id.btnSqrt);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btn0 = findViewById(R.id.btn0);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnAdd = findViewById(R.id.btnAdd);

        // Number buttons click listeners
        View.OnClickListener numberListener = v -> {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            display.setText(currentInput);
        };

        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn0.setOnClickListener(numberListener);
        btnDot.setOnClickListener(numberListener);

        // Operator buttons click listeners
        View.OnClickListener operatorListener = v -> {
            Button button = (Button) v;
            if (!currentInput.isEmpty()) {
                if (!operator.isEmpty()) {
                    calculateResult();
                }
                operator = button.getText().toString();
                lastInput = currentInput;
                currentInput = "";
            }
        };

        btnAdd.setOnClickListener(operatorListener);
        btnSubtract.setOnClickListener(operatorListener);
        btnMultiply.setOnClickListener(operatorListener);
        btnDivide.setOnClickListener(operatorListener);

        // Other functionality buttons
        btnSign.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = -value;
                currentInput = String.valueOf(value);
                display.setText(currentInput);
            }
        });

        btnSqrt.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                if (value >= 0) {
                    value = Math.sqrt(value);
                    currentInput = String.valueOf(value);
                    display.setText(currentInput);
                } else {
                    display.setText("Error");
                }
            }
        });

        btnC.setOnClickListener(v -> {
            currentInput = "";
            display.setText("0");
        });

        btnCE.setOnClickListener(v -> {
            currentInput = currentInput.length() > 0 ? currentInput.substring(0, currentInput.length() - 1) : "";
            display.setText(currentInput.isEmpty() ? "0" : currentInput);
        });

        btnEqual.setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !lastInput.isEmpty()) {
                calculateResult();
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                lastInput = "";
                operator = "";
            }
        });
    }

    private void calculateResult() {
        double num1 = Double.parseDouble(lastInput);
        double num2 = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }
    }
}
