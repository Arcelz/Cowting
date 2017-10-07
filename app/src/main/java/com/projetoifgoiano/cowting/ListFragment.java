package com.projetoifgoiano.cowting;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class ListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ListView list;
    String[] titles;
    String[] descricao;
    String[] descricaoCod;
    int img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container, false);
        Resources res = getResources();

        titles = res.getStringArray(R.array.tituloLista);
        descricao = res.getStringArray(R.array.descricao);
        descricaoCod =  res.getStringArray(R.array.descricaoCord);

        list = (ListView) view.findViewById(R.id.listCord);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = ((TextView) view.findViewById(R.id.descricaoCord)).getText().toString();
                Intent intent = new Intent(getActivity(),MapsActivity.class);
                intent.putExtra("Cordenada",selected);
                startActivity(intent);
            }
        });

        MyAdapter adapter = new MyAdapter(view.getContext(),titles,img,descricao,descricaoCod);
        list.setAdapter(adapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        int img;
        String myTitles[];
        String myDescricao[];
        String myDescricaoCord[];

        MyAdapter(Context c, String[] titles, int img, String[] descricao, String[] descricaoCord) {
            super(c, R.layout.row, R.id.text1, titles);
            this.img = img;
            this.myDescricao = descricao;
            this.myDescricaoCord = descricaoCord;
            this.myTitles = titles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView image = (ImageView) row.findViewById(R.id.iconMaps);
            TextView myTitle = (TextView) row.findViewById(R.id.textTitle);
            TextView myDescricao = (TextView) row.findViewById(R.id.textLongitude);
            TextView myDescricaoCord = (TextView) row.findViewById(R.id.descricaoCord);
            image.setImageResource(R.drawable.icone_map);
            myTitle.setText(titles[position]);
            myDescricao.setText(descricao[position]);
            myDescricaoCord.setText(descricaoCod[position]);
            return row;
        }
    }

}
