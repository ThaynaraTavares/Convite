package com.example.ifpb.evento.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ifpb.evento.R;
import com.example.ifpb.evento.entidade.Pessoa;

import java.util.List;

/**
 * Created by IFPB on 13/04/2016.
 */
public class PessoasCustomAdapter extends BaseAdapter {

    Context context;

    List<Pessoa> pessoas;

    public PessoasCustomAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)  context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.txtQrCode = (TextView) convertView.findViewById(R.id.qrCode);
            holder.txtFullName = (TextView) convertView.findViewById(R.id.name);
            holder.txtId = (TextView) convertView.findViewById(R.id.id);
     ;

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        Pessoa pessoaItem = (Pessoa) getItem(position);

        holder.txtFullName.setText(pessoaItem.getNome());
        holder.txtQrCode.setText("QR CODE: "+pessoaItem.getQrcode());
        holder.txtId.setText("Identificador numérico: "+ pessoaItem.getId());


        return convertView;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pessoas.indexOf(getItem(position));
    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtFullName;
        TextView txtQrCode;
        TextView txtId;

    }
}