package com.projetoifgoiano.cowting;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class ListActivity extends Fragment {

    ListView list;
    String[] titles;
    String[] descricao;
    int img;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container, false);
        Resources res = getResources();

        titles = res.getStringArray(R.array.tituloLista);
        descricao = res.getStringArray(R.array.descricao);

        list = (ListView) view.findViewById(R.id.listCord);

        MyAdapter adapter = new MyAdapter(view.getContext(),titles,img,descricao);
        list.setAdapter(adapter);
        return view;
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        int img;
        String myTitles[];
        String myDescricao[];

        MyAdapter(Context c, String[] titles, int img, String[] descricao) {
            super(c, R.layout.row, R.id.text1, titles);
            this.img = img;
            this.myDescricao = descricao;
            this.myTitles = titles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView image = (ImageView) row.findViewById(R.id.iconMaps);
            TextView myTitle = (TextView) row.findViewById(R.id.textTitle);
            TextView myDescricao = (TextView) row.findViewById(R.id.textLongitude);
            image.setImageResource(R.drawable.icone_map);
            myTitle.setText(titles[position]);
            myDescricao.setText(descricao[position]);
            return row;
        }
    }

}
