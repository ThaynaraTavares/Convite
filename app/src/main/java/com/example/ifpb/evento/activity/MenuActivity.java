package com.example.ifpb.evento.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ifpb.evento.entidade.Pessoa;
import com.example.ifpb.evento.util.BuscarPessoaCallBack;
import com.example.ifpb.evento.util.PessoasCustomAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.ifpb.evento.R;

import java.util.List;

public class MenuActivity extends Activity implements BuscarPessoaCallBack{
    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.buscaNome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, BuscaNome.class);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
        findViewById(R.id.buscaQR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(MenuActivity.this);
                scanIntegrator.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult
                (requestCode, resultCode, intent);

        if (scanningResult != null) {
            String conteudoLido = scanningResult.getContents();



        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nenhum dado foi recebido", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    @Override
    public void backBuscarNome(List<Pessoa> pessoas) {
        this.pessoas.clear();
        this.pessoas.addAll(pessoas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorBuscarNome(String error) {

        pessoas.clear();
        adapter.notifyDataSetChanged();

        Toast.makeText(this, error, Toast.LENGTH_LONG);
    }
}
