package com.example.ifpb.evento.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ifpb.evento.entidade.Pessoa;
import com.example.ifpb.evento.util.BuscarPessoaCallBack;
import com.example.ifpb.evento.util.HttpService;
import com.example.ifpb.evento.util.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IFPB on 13/04/2016.
 */
public class BuscarQRAsyncTask extends AsyncTask<Integer, Void, Response>{
    private BuscarPessoaCallBack buscarNomeCallBack;
    @Override
    protected Response doInBackground(Integer... numeros) {

        Response response = null;

        Integer valor = numeros[0];
        Gson gson = new Gson();

        Log.i("EditTextListener", "doInBackground (JSON): " );

        try {

            response = HttpService.sendJSONPostResquest("pesquisar/nome/", gson.toJson(valor));

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp
                + " Conteúdo: " + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            buscarNomeCallBack.errorBuscarNome(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Pessoa>>(){}.getType());

            buscarNomeCallBack.backBuscarNome(pessoas);
        }
    }
}
