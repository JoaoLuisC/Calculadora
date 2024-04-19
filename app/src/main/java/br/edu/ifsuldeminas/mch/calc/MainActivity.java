package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";

    private Button buttonDelete;
    private Button buttonPorcento;
    private Button buttonDivisao;
    private Button buttonMultiplicacao;
    private Button buttonSubtracao;
    private Button buttonSoma;
    private Button buttonIgual;
    private Button buttonVirgula;



    //NUMEROS
    private Button buttonZero;
    private Button buttonUm;
    private Button buttonDois;
    private Button buttonTres;
    private Button buttonQuatro;
    private Button buttonCinco;
    private Button buttonSeis;
    private Button buttonSete;
    private Button buttonOito;
    private Button buttonNove;


    //TEXTOS
    private boolean posResult = false;
    private String expressao = "";
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        //EXPRESSOES
        //EXPRESSÕES studio recomendou criar uma varivel local e excluir a variavel que eu criei la em cima, pq?

        //CLEAR
        Button buttonReset = findViewById(R.id.buttonResetID);
        buttonReset.setOnClickListener(v -> {
            reset();
            expressao = "";
            posResult = false;
        });

        //IGUAL
        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonIgual.setOnClickListener(view -> {
            Calculable avaliadorExpressao = null;
            try {
                if (textViewResultado.length() != 0 && textViewUltimaExpressao.length() != 0){
                    avaliadorExpressao = new ExpressionBuilder(expressao).build();
                    double resultado = avaliadorExpressao.calculate();
                    textViewUltimaExpressao.setText(Double.toString(resultado));
                    textViewResultado.setText("");
                }else {
                    posResult = true;
                    avaliadorExpressao = new ExpressionBuilder(expressao).build();
                    double resultado = avaliadorExpressao.calculate();
                    textViewUltimaExpressao.setText(expressao);
                    textViewResultado.setText(Double.toString(resultado));
                }
            } catch (Exception e) {
                Log.d(TAG, Objects.requireNonNull(e.getMessage()));
            }
        });

        //DELETE
        buttonDelete = findViewById(R.id.buttonDeleteID);
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
        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSoma.setOnClickListener(v -> {

            if (expressao.length() == 0){
                textViewUltimaExpressao.setText("");
            }else if (expressao.charAt(expressao.length() - 1) != '+'){
                expressao += buttonSoma.getText();
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.length() > 0 && (expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*')){
                expressao = expressao.substring(0, expressao.length() - 1);
                expressao += buttonSoma.getText();
                textViewUltimaExpressao.setText(expressao);
            } else if (expressao.charAt(expressao.length() - 1) == '+') {
                textViewUltimaExpressao.setText(expressao);
            }

        });

        //SUBTRAÇÃO

        buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonSubtracao.setOnClickListener(v -> {

            if (expressao.length() == 0){
                expressao += buttonSubtracao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.charAt(expressao.length() - 1) != '-'){
                expressao += buttonSubtracao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.length() > 0 && (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%' || expressao.charAt(expressao.length() - 1) == '*')){
                expressao = expressao.substring(0, expressao.length() - 1);
                expressao += buttonSubtracao.getText();
                textViewUltimaExpressao.setText(expressao);
            } else if (expressao.charAt(expressao.length() - 1) == '-') {
                textViewUltimaExpressao.setText(expressao);
            }

        });

        //MULTIPLICAÇÃO

        buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonMultiplicacao.setOnClickListener(v -> {

            if (expressao.length() == 0){
                textViewUltimaExpressao.setText("");
            }else if (expressao.charAt(expressao.length() - 1) != '*'){
                expressao += buttonMultiplicacao.getText();
                textViewUltimaExpressao.setText(expressao);
            }else if (expressao.length() > 0 && (expressao.charAt(expressao.length() - 1) == '+' || expressao.charAt(expressao.length() - 1) == '-' || expressao.charAt(expressao.length() - 1) == '/' || expressao.charAt(expressao.length() - 1) == '%')){
                expressao = expressao.substring(0, expressao.length() - 1);
                expressao += buttonMultiplicacao.getText();
                textViewUltimaExpressao.setText(expressao);
            } else if (expressao.charAt(expressao.length() - 1) == '*') {
                textViewUltimaExpressao.setText(expressao);
            }

        });

        //DIVISAO

        //PORCENTAGEM

        //VIRGULA


        //NUMEROS

        //ZERO
        buttonZero = findViewById(R.id.buttonZeroID);
        buttonZero.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonZero.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonZero.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //UM
        buttonUm = findViewById(R.id.buttonUmID);
        buttonUm.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonUm.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonUm.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //DOIS
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonDois.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonDois.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonDois.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //TRES
        buttonTres = findViewById(R.id.buttonTresID);
        buttonTres.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonTres.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonTres.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //QUATRO
        buttonQuatro= findViewById(R.id.buttonQuatroID);
        buttonQuatro.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonQuatro.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonQuatro.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //CINCO
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonCinco.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonCinco.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonCinco.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //SEIS
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSeis.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonSeis.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonSeis.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //SETE
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonSete.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonSete.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonSete.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //OITO
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonOito.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonOito.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonOito.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });
        //NOVE
        buttonNove = findViewById(R.id.buttonNoveID);
        buttonNove.setOnClickListener(v -> {
            if (posResult){
                reset();
                expressao += buttonNove.getText();
                textViewUltimaExpressao.setText(expressao);
                posResult = false;
            }else{
                expressao += buttonNove.getText();
                textViewUltimaExpressao.setText(expressao);
            }
        });

    }

    private void reset(){
        expressao = "";
        textViewResultado.setText("0");
        textViewUltimaExpressao.setText("");
    }
}