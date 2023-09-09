package com.example.myapplication99;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private TextView textViewName;
    private TextView textViewResult;
    private String userName = "";
    private double pesoToDollarRate = 0.018;
    private double dollarToPesoRate = 56.75;
    private Button buttonPesoToDollar;
    private Button buttonDollarToPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        textViewName = findViewById(R.id.textViewName);
        textViewResult = findViewById(R.id.textViewResult);


        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearResult();
            }
        });


        for (int i = 0; i < 10; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appendNumber(finalI);
                }
            });
        }


        Button buttonSetName = findViewById(R.id.buttonSetName);
        buttonSetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setName();
            }
        });


        buttonPesoToDollar = findViewById(R.id.buttonPesoToDollar);
        buttonPesoToDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCurrency("Peso a Dólar", pesoToDollarRate);
            }
        });

        buttonDollarToPeso = findViewById(R.id.buttonDollarToPeso);
        buttonDollarToPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCurrency("Dólar a Peso", dollarToPesoRate);
            }
        });
    }

    private void clearResult() {

        textViewResult.setText("");
        disableConversionButtons();
    }

    private void appendNumber(int number) {

        String currentText = textViewResult.getText().toString();
        String newText = currentText + String.valueOf(number);
        textViewResult.setText(newText);
    }

    private void convertCurrency(String conversionType, double rate) {
        if (userName.isEmpty()) {
            textViewResult.setText("Ingresa tu nombre antes de realizar una conversión.");
        } else {

            String currentText = textViewResult.getText().toString();
            double amount = Double.parseDouble(currentText);
            double result = amount * rate;
            textViewResult.setText("Hola, " + userName + ". " + conversionType + ": " + result);
        }
    }

    private void disableConversionButtons() {

        buttonPesoToDollar.setEnabled(false);
        buttonDollarToPeso.setEnabled(false);
    }

    private void enableConversionButtons() {

        buttonPesoToDollar.setEnabled(true);
        buttonDollarToPeso.setEnabled(true);
    }

    private void setName() {
        userName = editTextName.getText().toString();
        updateNameTextView();
        enableConversionButtons();
    }

    private void updateNameTextView() {
        String nameText = "Hola, " + userName + ". Peso a Dólar: " + calculateCurrency(pesoToDollarRate) +
                ", Dólar a Peso: " + calculateCurrency(dollarToPesoRate);
        textViewName.setText(nameText);
    }

    private double calculateCurrency(double rate) {
        String currentText = textViewResult.getText().toString();
        if (!currentText.isEmpty()) {
            double amount = Double.parseDouble(currentText);
            return amount * rate;
        }
        return 0.0; // Valor predeterminado si no hay entrada
    }

    public void pesoToDollar(View view) {
        convertCurrency("Peso a Dólar", pesoToDollarRate);
    }

    public void dollarToPeso(View view) {
        convertCurrency("Dólar a Peso", dollarToPesoRate);
    }
}
