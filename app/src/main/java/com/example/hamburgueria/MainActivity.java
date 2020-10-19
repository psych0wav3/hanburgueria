package com.example.hamburgueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int quantidade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void subtrair(View view){
        quantidade = quantidade - 1;
        displayQuant(quantidade);
    }

    public void displayQuant(int qtd){
        TextView qtdTextView = (TextView) findViewById(R.id.quantidade_tv);
        qtdTextView.setText(String.valueOf(qtd));
    }

    public void somar(View view) {
        quantidade = quantidade + 1;
        displayQuant(quantidade);
    }

    public  void enviarPedido(View view){
        EditText textName = (EditText) findViewById(R.id.nome);
        String nome = textName.getText().toString();

        CheckBox checkboxBacon = (CheckBox) findViewById(R.id.baicon);
        boolean baconCheck = checkboxBacon.isChecked();

        CheckBox checkboxQueijo = (CheckBox) findViewById(R.id.queijo);
        boolean queijoCheck = checkboxQueijo.isChecked();

        CheckBox checkboxOnionrings = (CheckBox) findViewById(R.id.onionrings);
        boolean onionsCheck = checkboxOnionrings.isChecked();

        int valor = calcularPreco(baconCheck, queijoCheck, onionsCheck);

        String mensagem = "Nome: " +nome;
        mensagem += "\nBacon? " + baconCheck;
        mensagem += "\nQueijo? " + queijoCheck;
        mensagem += "\n Onion Rings? " + onionsCheck;
        mensagem += "\n Quantidade: " + quantidade;
        mensagem += "\n\n Total : R$ " + valor;

        TextView resumo = (TextView) findViewById(R.id.resumo_pedido);
        resumo.setText(mensagem);

        Intent intent = new Intent(Intent.ACTION_SENDTO);  //email
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de  " + nome);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        if(intent.resolveActivity(getPackageManager()) !=null)
            startActivity(intent);

    }

    public int calcularPreco(boolean baconCheck, boolean queijoCheck, boolean onionsCheck){
        int precoBase = 20;

        if(baconCheck)
            precoBase += 2;
        if(queijoCheck)
            precoBase += 2;
        if(onionsCheck)
            precoBase += 3;

        return precoBase * quantidade;

    }
}