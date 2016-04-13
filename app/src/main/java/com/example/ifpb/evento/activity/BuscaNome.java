package com.example.ifpb.evento.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ifpb.evento.util.BuscarPessoaCallBack;
import com.example.ifpb.evento.util.PessoasCustomAdapter;
import com.example.ifpb.evento.R;
import com.example.ifpb.evento.asynctask.BuscarNomeAsyncTask;
import com.example.ifpb.evento.entidade.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class BuscaNome extends Activity implements TextWatcher, OnItemClickListener, BuscarPessoaCallBack {

    private EditText nomeEditText;
    private static int TAMANHO_MINIMO_TEXTO = 4;

    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_nome);

        nomeEditText = (EditText) findViewById(R.id.buscaNome);
        nomeEditText.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.nomesListView);
        pessoas = new ArrayList<Pessoa>();
        adapter = new PessoasCustomAdapter(this, pessoas);

        // Adapter modificado.
        nomesListView.setAdapter(adapter);

        // Evento de OnItemClickListener.
        nomesListView.setOnItemClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        Log.i("EditTextListener", "onTextChanged: " + charSequence);
        String nome = charSequence.toString();

        // Consultar o servidor. Criar o JSONObject e uma AsyncTask<JSONObject, Void, Response>

        if (nome.length() >= TAMANHO_MINIMO_TEXTO) {
            // JSON
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);

            BuscarNomeAsyncTask buscarNomeAsyncTask = new BuscarNomeAsyncTask(this);
            buscarNomeAsyncTask.execute(pessoa);
        }
        else{
            this.pessoas.clear();
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
