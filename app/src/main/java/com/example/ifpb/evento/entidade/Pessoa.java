package com.example.ifpb.evento.entidade;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;


public class Pessoa implements Serializable {

    public Pessoa(){

    }

    public Pessoa (String name, String qrcode) {
        this.name = name;
        this.qrcode = qrcode;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String name;

    @SerializedName("qrcode")
    private String qrcode;


    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", qrcode='" + qrcode + '\'' +
                '}';
    }
}