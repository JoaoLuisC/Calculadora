package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";

    //EXPRESSÕES
    private Button buttonReset;
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
        buttonReset = findViewById(R.id.buttonResetID);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                expressao = "";
                posResult = false;
            }
        });

        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                try {
                    avaliadorExpressao = new ExpressionBuilder(expressao).build();

                    Double resultado = avaliadorExpressao.calculate();

                    textViewUltimaExpressao.setText(expressao);
                    textViewResultado.setText(resultado.toString());
                    posResult = true;
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });

        //NUMEROS

        //ZERO
        buttonZero = findViewById(R.id.buttonZeroID);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonZero.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonZero.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //UM
        buttonUm = findViewById(R.id.buttonUmID);
        buttonUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonUm.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonUm.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //DOIS
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonDois.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonDois.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //TRES
        buttonTres = findViewById(R.id.buttonTresID);
        buttonTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonTres.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonTres.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //QUATRO
        buttonQuatro= findViewById(R.id.buttonQuatroID);
        buttonQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonQuatro.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonQuatro.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //CINCO
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += 5;
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonCinco.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //SEIS
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonSeis.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonSeis.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //SETE
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonSete.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonSete.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //OITO
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonOito.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonOito.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });
        //NOVE
        buttonNove = findViewById(R.id.buttonNoveID);
        buttonNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posResult == true){
                    reset();
                    expressao += buttonNove.getText();
                    textViewUltimaExpressao.setText(expressao);
                    posResult = false;
                }else{
                    expressao += buttonNove.getText();
                    textViewUltimaExpressao.setText(expressao);
                }
            }
        });

    }

    private void reset(){
        textViewResultado.setText("0");
        textViewUltimaExpressao.setText("");
    }
}