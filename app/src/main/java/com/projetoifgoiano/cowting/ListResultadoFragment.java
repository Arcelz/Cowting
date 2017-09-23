package com.projetoifgoiano.cowting;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListResultadoFragment extends Fragment {

    ListView list;
    String[] titles;
    String[] descricao;
    int img;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_resultado, container, false);
        Resources res = getResources();

        titles = res.getStringArray(R.array.resultado_titulo_lista);
        descricao = res.getStringArray(R.array.resultado_descricao_lista);

        list = (ListView) view.findViewById(R.id.listResultado);

        MyAdapter adapter = new MyAdapter(view.getContext(),titles,img,descricao);
        list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        int img;
        String myTitles[];
        String myDescricao[];

        MyAdapter(Context c, String[] titles, int img, String[] descricao) {
            super(c, R.layout.row_resultado, R.id.text1, titles);
            this.img = img;
            this.myDescricao = descricao;
            this.myTitles = titles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_resultado, parent, false);
            ImageView image = (ImageView) row.findViewById(R.id.iconResultados);
            TextView myTitle = (TextView) row.findViewById(R.id.textTitleResultado);
            TextView myDescricao = (TextView) row.findViewById(R.id.textResultado);
            image.setImageResource(R.drawable.icone_resultado);
            myTitle.setText(titles[position]);
            myDescricao.setText(descricao[position]);
            return row;
        }
    }

}
