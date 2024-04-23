package br.edu.ifsuldeminas.mch.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";

    //TEXTOS
    private boolean posResult = false;
    private String expressao = "";
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;
    private String resultadoToViewExpressao;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        //CLEAR
        Button buttonReset = findViewById(R.id.buttonResetID);
        buttonReset.setOnClickListener(v -> {
            reset();
            posResult = false;
        });

        //IGUAL
        Button buttonIgual = findViewById(R.id.buttonIgualID);
        buttonIgual.setOnClickListener(view -> {

            Calculable avaliadorExpressao;

            if(expressao.isEmpty()){
                reset();
            }else{
                try {
                    if (isLastCharOperator(expressao) || expressao.charAt(expressao.length() - 1) == '('){
                        Toast.makeText(getApplicationContext(), "Formato usado inválido", Toast.LENGTH_SHORT).show();
                    }else {
                        avaliadorExpressao = new ExpressionBuilder(expressao).build();
                        double resultado = avaliadorExpressao.calculate();
                        textViewUltimaExpressao.setText(expressao);

                        String resultadoFormatado = Double.toString(resultado);
                        resultadoFormatado = removeDecimalIfZero(resultadoFormatado);

                        textViewResultado.setText(resultadoFormatado);
                        resultadoToViewExpressao = resultadoFormatado;
                        posResult = true;
                    }
                } catch (Exception e) {
                    Log.d(TAG, Objects.requireNonNull(e.getMessage()));
                }
            }
        });

        //DELETE
        Button buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonDelete.setOnClickListener(v -> {
            int tam = expressao.length();

            if (tam > 0) {
                expressao = expressao.substring(0, tam - 1);
                textViewUltimaExpressao.setText(expressao);
            }else{
                textViewUltimaExpressao.setText("");
            }
        });

        //SOMA
        Button buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSoma.setOnClickListener(v -> {

            handleResultPosition();

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if (expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*') {
                expressao = expressao.substring(0, expressao.length() - 1) + buttonSoma.getText();
                textViewUltimaExpressao.setText(expressao);
            }else  if (expressao.charAt(expressao.length() - 1) == '+') {
                textViewUltimaExpressao.setText(expressao);
            }else if(!isLastCharOperator(expressao)){
                expressao += buttonSoma.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //SUBTRAÇÃO
        Button buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonSubtracao.setOnClickListener(v -> {

            handleResultPosition();

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*') {
                expressao = expressao.substring(0, expressao.length() - 1) + buttonSubtracao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else  if (expressao.charAt(expressao.length() - 1) == '-') {
                textViewUltimaExpressao.setText(expressao);
            }else if(!isLastCharOperator(expressao)){
                expressao += buttonSubtracao.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //MULTIPLICAÇÃO
        Button buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonMultiplicacao.setOnClickListener(v -> {

            handleResultPosition();

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if ((expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-') && expressao.charAt(expressao.length() - 2) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%') {
                expressao = expressao.substring(0, expressao.length() - 1) + buttonMultiplicacao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else  if (expressao.charAt(expressao.length() - 1) == '*' || expressao.charAt(expressao.length() - 1) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if(!isLastCharOperator(expressao)){
                expressao += buttonMultiplicacao.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //DIVISAO
        Button buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonDivisao.setOnClickListener(v -> {

            handleResultPosition();

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if ((expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-') && expressao.charAt(expressao.length() - 2) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*') {
                expressao = expressao.substring(0, expressao.length() - 1) + buttonDivisao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else  if (expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if(!isLastCharOperator(expressao)){
                expressao += buttonDivisao.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //PORCENTAGEM
        Button buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonPorcento.setOnClickListener(v -> {

            handleResultPosition();

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if ((expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-') && expressao.charAt(expressao.length() - 2) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '*') {
                expressao = expressao.substring(0, expressao.length() - 1) + buttonPorcento.getText();
                textViewUltimaExpressao.setText(expressao);
            }else  if (expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '(') {
                textViewUltimaExpressao.setText(expressao);
            }else if(!isLastCharOperator(expressao)){
                expressao += buttonPorcento.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //VIRGULA
        Button buttonVirgula = findViewById(R.id.buttonVirgulaID);
        buttonVirgula.setOnClickListener(v -> {

            if (expressao.isEmpty()){
                textViewUltimaExpressao.setText("");
            }else if (expressao.charAt(expressao.length() - 1) == '.' || expressao.charAt(expressao.length() - 1) == '(' ||expressao.charAt(expressao.length() - 1) == ')') {
                textViewUltimaExpressao.setText(expressao);
            }else if (isLastCharOperator(expressao)) {
                textViewUltimaExpressao.setText(expressao);
            }else if (!isLastCharOperator(expressao)) {
                if (canAddDecimal(expressao)){
                    expressao += ".";
                    textViewUltimaExpressao.setText(expressao);
                }else{
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });

        //PARENTESES
        Button buttonParenteses = findViewById(R.id.buttonParenthesesID);
        buttonParenteses.setOnClickListener(v ->{

            if (expressao.isEmpty() ){
                expressao += "(";
                textViewUltimaExpressao.setText(expressao);
            } else if (!isPossibleToAddParentheses(expressao)) {
                if (expressao.charAt(expressao.length() - 1) == '(' || isLastCharOperator(expressao) ){
                    textViewUltimaExpressao.setText(expressao);
                }else{
                    expressao += ")";
                    textViewUltimaExpressao.setText(expressao);
                }
            } else if(expressao.charAt(expressao.length() - 1) == '('){
                textViewUltimaExpressao.setText(expressao);
            }else  if (isLastCharOperator(expressao)){
                expressao += "(";
                textViewUltimaExpressao.setText(expressao);
            }
        });

        //NUMEROS
        setupNumericButton(findViewById(R.id.buttonZeroID));
        setupNumericButton(findViewById(R.id.buttonUmID));
        setupNumericButton(findViewById(R.id.buttonDoisID));
        setupNumericButton(findViewById(R.id.buttonTresID));
        setupNumericButton(findViewById(R.id.buttonQuatroID));
        setupNumericButton(findViewById(R.id.buttonCincoID));
        setupNumericButton(findViewById(R.id.buttonSeisID));
        setupNumericButton(findViewById(R.id.buttonSeteID));
        setupNumericButton(findViewById(R.id.buttonOitoID));
        setupNumericButton(findViewById(R.id.buttonNoveID));
    }
    private void reset(){
        expressao = "";
        textViewResultado.setText("0");
        textViewUltimaExpressao.setText("");
    }
    private boolean isLastCharOperator(String expressao){
        return expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*';
    }
    private boolean isPossibleToAddParentheses(String expression) {
        int openParenthesesCount = 0;
        int closeParenthesesCount = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                openParenthesesCount++;
            } else if (c == ')') {
                closeParenthesesCount++;
            }
        }

        return openParenthesesCount == closeParenthesesCount;
    }
    private String removeDecimalIfZero(String expressao) {
        try {
            double numero = Double.parseDouble(expressao);
            if (hasDecimalPart(numero)) {
                return expressao;
            } else {
                if (expressao.contains(".0")) {
                    expressao = expressao.substring(0, expressao.indexOf("."));
                }
                return expressao;
            }
        } catch (NumberFormatException e) {
            return expressao;
        }
    }
    public boolean hasDecimalPart(double number) {
        int integerPart = (int) number;
        double decimalPart = number - integerPart;
        return decimalPart != 0;
    }
    private boolean canAddDecimal(@NonNull String expressao) {

        for (int i = expressao.length() - 1; i >= 0; i--) {
            char c = expressao.charAt(i);

            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(') {
                return true;
            }else if (c == '.') {
                return false;
            }
        }
        return true;
    }
    private void setupNumericButton(Button button) {
        button.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += button.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            } else {
                expressao += button.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
    }
    private void handleResultPosition() {
        if (posResult) {
            expressao = resultadoToViewExpressao;
            textViewUltimaExpressao.setText(expressao);
            textViewResultado.setText("");
            posResult = false;
        }
    }
}