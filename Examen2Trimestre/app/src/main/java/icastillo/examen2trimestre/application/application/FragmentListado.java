package icastillo.examen2trimestre.application.application;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import icastillo.examen2trimestre.R;
import icastillo.examen2trimestre.application.application.room.AppDataBase;
import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioFutbolistas;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioPosiciones;
import icastillo.examen2trimestre.application.application.viewmodels.ViewModelListFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListado.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListado extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    MyAdapter adapter;
    Futbolista[] arrayFutbolistas;
    ViewModelListFragment viewModel;
    ArrayList<String> nombrePosiciones;
    RepositorioPosiciones repositorioPosiciones;
    RepositorioFutbolistas repositorioFutbolistas;
    Posicion[] arrayPosiciones;

    //XML
    ListView listView;
    Button btnBackListado;

    public FragmentListado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListado.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListado newInstance(String param1, String param2) {
        FragmentListado fragment = new FragmentListado();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_listado, container, false);

        repositorioFutbolistas=new RepositorioFutbolistas(getActivity().getApplication());
        repositorioPosiciones=new RepositorioPosiciones(getActivity().getApplication());

        btnBackListado=(Button) view.findViewById(R.id.btnBackList);
        btnBackListado.setOnClickListener(this);

        listView=(ListView) view.findViewById(R.id.listView);

        //nombrePosiciones=new ArrayList<String>();

        //ViewModel
        viewModel= ViewModelProviders.of(this).get(ViewModelListFragment.class);
        viewModel.getLiveDataPosiciones().observe(this, new Observer<Posicion[]>() {
            @Override
            public void onChanged(@Nullable Posicion[] posicions) {
                if(posicions!=null){
                    arrayPosiciones=posicions;
                }
            }
        });
        viewModel.getLiveDataFutbolistas().observe(this, new Observer<Futbolista[]>() {
            @Override
            public void onChanged(@Nullable Futbolista[] futbolistas) {
                if(futbolistas!=null){
                    arrayFutbolistas=futbolistas;
                    //Obtener para cada futbolista el nombre de sus posiciones
                    //obtenerNombrePosicionesFutbolista();
                    obtenerPosicionesFutbolista();
                    adapter=new MyAdapter( getActivity() , R.layout.stylelist, R.id.txtNombre, arrayFutbolistas);
                    listView.setAdapter(adapter);
                }
            }
        });


                //viewModel.cargaFutbolistas();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        mListener.OnButtonClick(v);
    }


    public void obtenerNombrePosicionesFutbolista(){
        nombrePosiciones=new ArrayList<String>(arrayFutbolistas.length);
        String nombresConcatenados="";
        for(int i=0;i<arrayFutbolistas.length;i++){
            Posicion[] posicions = repositorioFutbolistas.getArrayPosicionesFutbolista(arrayFutbolistas[i]);
            for(int j=0;j<arrayFutbolistas[i].getIdPosiciones().size();j++){
                nombresConcatenados=nombresConcatenados+" "+posicions[j].getNombrePosicion();
            }
            nombrePosiciones.add(nombresConcatenados);
            nombresConcatenados="";
        }
    }

    public void obtenerPosicionesFutbolista(){
        nombrePosiciones=new ArrayList<String>(arrayFutbolistas.length);
        String nombresConcatenados="";
        for(int i=0;i<arrayFutbolistas.length;i++){

            for(int j=0;j<arrayFutbolistas[i].getIdPosiciones().size();j++){
                if(arrayFutbolistas[i].getIdPosiciones().get(j)==arrayPosiciones[0].getId()){
                    nombresConcatenados=nombresConcatenados+" "+arrayPosiciones[0].getNombrePosicion();
                }
                if(arrayFutbolistas[i].getIdPosiciones().get(j)==arrayPosiciones[1].getId()){
                    nombresConcatenados=nombresConcatenados+" "+arrayPosiciones[1].getNombrePosicion();
                }
                if(arrayFutbolistas[i].getIdPosiciones().get(j)==arrayPosiciones[2].getId()){
                    nombresConcatenados=nombresConcatenados+" "+arrayPosiciones[2].getNombrePosicion();
                }
                if(arrayFutbolistas[i].getIdPosiciones().get(j)==arrayPosiciones[3].getId()){
                    nombresConcatenados=nombresConcatenados+" "+arrayPosiciones[3].getNombrePosicion();
                }
            }
            nombrePosiciones.add(nombresConcatenados);
            nombresConcatenados="";
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void OnButtonClick(View view);
    }


    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, T[] object){
            super(c, resourceId, textId, object);
        }

        @Override
        public int getViewTypeCount(){
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolderFubolista viewHolder=null;

            if(row==null){
                LayoutInflater inflater = getActivity().getLayoutInflater();
                row = inflater.inflate(R.layout.stylelist, parent, false);

                viewHolder = new ViewHolderFubolista(row);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolderFubolista) row.getTag();
            }

            //Bucle para obtener nombre de las posiciones del futbolista
            //LAS POSICIONES FALTAN POR PONER

            viewHolder.getTextViewNombre().setText( arrayFutbolistas[position].getNombre() );
            viewHolder.getTextViewApellidos().setText( arrayFutbolistas[position].getApellidos() );
            viewHolder.getTextViewPosiciones().setText(nombrePosiciones.get(position));
            return (row);
        }

    }


}
