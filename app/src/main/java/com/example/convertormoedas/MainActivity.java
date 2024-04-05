package com.example.convertormoedas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.FrameMetricsAggregator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private EditText quantiaEditText;
    private Button buttonMostrarGrafico;
    private Button buttonLimpar;
    private Spinner spinnerMoeda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantiaEditText = findViewById(R.id.quantiaEditText);

        buttonMostrarGrafico = findViewById(R.id.buttonMostrarGrafico);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        spinnerMoeda = findViewById(R.id.spinnerMoeda);

        buttonMostrarGrafico.setOnClickListener(this);
        buttonLimpar.setOnClickListener(this);
        spinnerMoeda.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.moedas, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoeda.setAdapter(adapter);


    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


        if (pos == 0) return;

        if (quantiaEditText.getText().toString().isEmpty()) return;

        String moedaSelecionada = parent.getItemAtPosition(pos).toString();


        double quantia = Double.parseDouble(quantiaEditText.getText().toString());

        Moeda moeda = new Moeda(quantia);
        double valorConvertido = 0.0;

        if (moedaSelecionada.equals("Dolar")){
            valorConvertido = moeda.converterMoedaDolar();
        } else if (moedaSelecionada.equals("Euro")){
            valorConvertido = moeda.converterMoedaEuro();
        }

        String msg = String.format("O valor convertido é de %.2f ", valorConvertido);

        this.mostrarResultado(msg);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLimpar){
            this.limparCampo();
        }
        else if (view.getId() == R.id.buttonMostrarGrafico){
            this.abrirTela();
        }
    }

    public void mostrarResultado(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado da Conversão da Moeda");
        builder.setMessage(msg);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void limparCampo(){
        quantiaEditText.setText("");
        spinnerMoeda.setSelection(0);

    }
    public void abrirTela(){
        Intent telaGrafico = new Intent(this, GraficoActivity.class);
        startActivity(telaGrafico);

        finish();
    }
}