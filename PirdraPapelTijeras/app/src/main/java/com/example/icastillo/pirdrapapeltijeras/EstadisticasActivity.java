package com.example.icastillo.pirdrapapeltijeras;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class EstadisticasActivity extends ListActivity {

    GestoraEstadisticasActivity gestoraEstadisticasActivity=new GestoraEstadisticasActivity();
    Spinner spinner;
    ArrayList<Player> arrayListPlayers=new ArrayList<>();
    MyAdapter<Player> adapterPlayers;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        spinner=(Spinner) findViewById(R.id.spinner);
        sharedPreferences=getSharedPreferences( getString(R.string.file_estadisticas), Context.MODE_PRIVATE);
        arrayListPlayers=gestoraEstadisticasActivity.getListPlayers(this, sharedPreferences);
        adapterPlayers=new MyAdapter<Player>(this, R.layout.stylelistestadisticas, R.id.txtNombreJugador, arrayListPlayers);
        setListAdapter(adapterPlayers);
    }



    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, ArrayList<T> objects){
            super(c, resourceId, textId, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolderPlayer viewHolderPlayer=null;
            Player player=null;

            if(row==null ) {
                LayoutInflater inflater = getLayoutInflater();
                if(position%getViewTypeCount()==0) {
                    row = inflater.inflate(R.layout.styleliststadisticaspar, parent, false);
                }else {
                    row = inflater.inflate(R.layout.stylelistestadisticas, parent, false);
                }
                viewHolderPlayer=new ViewHolderPlayer(row, R.id.txtNombreJugador, R.id.txtVictorias, R.id.txtDerrotas, R.id.txtEmpates, R.id.txtVecesPiedra, R.id.txtVecesPapel, R.id.txtVecesTijera);
                row.setTag(viewHolderPlayer);
            }else{
                viewHolderPlayer=(ViewHolderPlayer) row.getTag();
            }

            player=new Player(arrayListPlayers.get(position));
            viewHolderPlayer.getNombre().setText(player.getNombre());
            viewHolderPlayer.getVictorias().setText(String.valueOf(player.getVictorias()));
            viewHolderPlayer.getDerrotas().setText(String.valueOf(player.getDerrotas()));
            viewHolderPlayer.getEmpates().setText(String.valueOf(player.getEmpates()));
            viewHolderPlayer.getVecesPiedra().setText(String.valueOf(player.getVecesPiedra()));
            viewHolderPlayer.getVecesPapel().setText(String.valueOf(player.getVecesPapel()));
            viewHolderPlayer.getVecesTijera().setText(String.valueOf(player.getVecesTijera()));

            return row;
        }


    }//Fin clase MyAdapter


}
