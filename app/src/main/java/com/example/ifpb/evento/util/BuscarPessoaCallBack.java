package com.example.ifpb.evento.util;

import com.example.ifpb.evento.entidade.Pessoa;

import java.util.List;

public interface BuscarPessoaCallBack {

    void backBuscarNome(List<Pessoa> names);

    void errorBuscarNome(String error);
}
