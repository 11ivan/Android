package icastillo.examen2trimestre.application.application;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import icastillo.examen2trimestre.R;
import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioFutbolistas;
import icastillo.examen2trimestre.application.application.viewmodels.ViewModelFragmentInsercion;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInsercion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInsercion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInsercion extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Futbolista futbolista;
    RepositorioFutbolistas repositorioFutbolistas;
    Posicion[] arrayPosiciones;
    ViewModelFragmentInsercion viewModelFragmentInsercion;

    //XML
    EditText editTextNombre;
    EditText editTextApellidos;
    Button btnSave;
    Button btnBackInsert;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    public FragmentInsercion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInsercion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInsercion newInstance(String param1, String param2) {
        FragmentInsercion fragment = new FragmentInsercion();
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
        View view=inflater.inflate(R.layout.fragment_insercion, container, false);

        editTextNombre=(EditText) view.findViewById(R.id.nombrePersonaIns);
        editTextApellidos=(EditText) view.findViewById(R.id.apellidosPersonaIns);
        btnSave=(Button) view.findViewById(R.id.btnSaveFutbolista);
        btnBackInsert=(Button) view.findViewById(R.id.btnBackInsert);
        checkBox1=(CheckBox) view.findViewById(R.id.check1);
        checkBox2=(CheckBox) view.findViewById(R.id.check2);
        checkBox3=(CheckBox) view.findViewById(R.id.check3);
        checkBox4=(CheckBox) view.findViewById(R.id.check4);

        btnBackInsert.setOnClickListener(this);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);

        repositorioFutbolistas=new RepositorioFutbolistas(getActivity().getApplication());
        futbolista=new Futbolista();

        //Boton Save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobar datos del Futbolista
                if(!compruebaDatosFutbolista()){
                    errorNombreApellido();
                }else if(!haSeleccionado()){
                    errorPosicion();
                }else {
                    //Dar Nombre y Apellidos
                    futbolista.setNombre(editTextNombre.getText().toString());
                    futbolista.setApellidos(editTextApellidos.getText().toString());

                    //Dar id de las posiciones al Futbolista
                    asignarIdPosicionesFutbolista();
                    //Insertar Futbolista
                    repositorioFutbolistas.insertFutbolista(futbolista);

                    //Limpiar Datos
                    limpiaDatos();
                }
            }
        });

        viewModelFragmentInsercion = ViewModelProviders.of(this).get(ViewModelFragmentInsercion.class);
        viewModelFragmentInsercion.getLiveDataPosiciones().observe(this, new Observer<Posicion[]>() {
            @Override
            public void onChanged(@Nullable Posicion[] posicions) {
                if(posicions!=null && posicions.length>0){
                    arrayPosiciones=posicions;

                    //Obtenemos los nombres de las posiciones para los checkBox
                    for(int i=0;i<arrayPosiciones.length;i++){
                        switch (i){
                            case 0:
                                checkBox1.setText(arrayPosiciones[i].getNombrePosicion());
                            break;
                            case 1:
                                checkBox2.setText(arrayPosiciones[i].getNombrePosicion());
                            break;
                            case 2:
                                checkBox3.setText(arrayPosiciones[i].getNombrePosicion());
                            break;
                            case 3:
                                checkBox4.setText(arrayPosiciones[i].getNombrePosicion());
                            break;
                        }
                    }
                }
            }
        });//Fin Observer

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

    public void asignarIdPosicionesFutbolista(){
        ArrayList<Integer> idPosiciones=new ArrayList<>();
        if(checkBox1.isChecked()){
            idPosiciones.add(arrayPosiciones[0].getId());
        }
        if(checkBox2.isChecked()){
            idPosiciones.add(arrayPosiciones[1].getId());
        }
        if(checkBox3.isChecked()){
            idPosiciones.add(arrayPosiciones[2].getId());
        }
        if(checkBox4.isChecked()){
            idPosiciones.add(arrayPosiciones[3].getId());
        }
        futbolista.setIdPosiciones(idPosiciones);
    }

    public boolean compruebaDatosFutbolista(){
        boolean vale=false;

        if(editTextNombre.getText().toString().replaceAll(" ", "").length()>0 &&
           editTextApellidos.getText().toString().replaceAll(" ", "").length()>0){
            vale=true;
        }
        return vale;
    }

    public boolean haSeleccionado(){
        boolean vale=true;

        if(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()){
            vale=false;
        }
        return vale;
    }

    public void errorNombreApellido(){
        Toast.makeText(getActivity(), "Nombre y/o Apellidos no son correctos", Toast.LENGTH_SHORT).show();
    }

    public void errorPosicion(){
        Toast.makeText(getActivity(), "Debe elegir una posicion", Toast.LENGTH_SHORT).show();
    }

    public void limpiaDatos(){
        if(checkBox1.isChecked()) {
            checkBox1.setChecked(false);
        }
        if(checkBox2.isChecked()){
            checkBox2.setChecked(false);
        }
        if(checkBox3.isChecked()){
            checkBox3.setChecked(false);
        }
        if(checkBox4.isChecked()) {
            checkBox4.setChecked(false);
        }
        editTextNombre.setText("");
        editTextApellidos.setText("");
        futbolista=new Futbolista();
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.check1:
                //Si está chequeado y el futbolista no contiene ya esa id
                /*if(isChecked && !futbolista.getIdPosiciones().contains(0)){
                    futbolista.getIdPosiciones().add(0, arrayPosiciones[0].getId());
                }*/
            break;
            case R.id.check2:
                //Si está chequeado y el futbolista no contiene ya esa id
                /*if(isChecked && !futbolista.getIdPosiciones().contains(1)){
                    futbolista.getIdPosiciones().add(1, arrayPosiciones[1].getId());
                }*/
            break;
            case R.id.check3:
                //Si está chequeado y el futbolista no contiene ya esa id
                /*if(isChecked && !futbolista.getIdPosiciones().contains(0)){
                    futbolista.getIdPosiciones().add(0, arrayPosiciones[0].getId());
                }*/
            break;
            case R.id.check4:
                //Si está chequeado y el futbolista no contiene ya esa id
                /*if(isChecked && !futbolista.getIdPosiciones().contains(0)){
                    futbolista.getIdPosiciones().add(0, arrayPosiciones[0].getId());
                }*/
            break;
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
}
