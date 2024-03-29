package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioConfiguraciones;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OptionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionsFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Mis Variables xml
    Button btnBack;
    Activity activity;
    RadioGroup radioGroupSeleccionTablero;
    ImageView imageTableroSeleccionado;
    EditText editTextNombreUsuario;
    RadioButton radioButtonAzul;
    RadioButton radioButtonAluminio;
    Button btnUpdateConfiguracion;

    //Configuracion configuracion;
    RepositorioConfiguraciones repositorioConfiguraciones;

    public OptionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionsFragment newInstance(String param1, String param2) {
        OptionsFragment fragment = new OptionsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_options, container, false);

        //Boton Atras
        btnBack=(Button) view.findViewById(R.id.btnBackOpciones);
        btnBack.setOnClickListener(this);

        //Boton update configuracion
        btnUpdateConfiguracion=(Button) view.findViewById(R.id.btnActualizaConfiguracion);
        btnUpdateConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Actualizamos la configuración (si ha cambiado)
                ((MainActivity)getActivity()).configuracionDeUsuario.setTipoTablero( (Integer) ((Object) imageTableroSeleccionado.getTag()) );
                //Configuracion configuracion=((MainActivity)getActivity()).configuracionDeUsuario;
                ((MainActivity)getActivity()).repositorioConfiguraciones.updateConfiguracion(((MainActivity)getActivity()).configuracionDeUsuario);

                //Actualizamos nombre de usuario (si ha cambiado)       NO SE ESTÁ COMPROBANDO EL NOMBRE
                ((MainActivity)getActivity()).usuarioDeViewModel.setNombre(editTextNombreUsuario.getText().toString());
                ((MainActivity)getActivity()).repositorioUsuarios.updateUsuario( ((MainActivity)getActivity()).usuarioDeViewModel );//Actualizando entra en onChanged ??
            }
        });

        //RadioGroup Seleccion Tablero
        radioGroupSeleccionTablero=(RadioGroup)view.findViewById(R.id.radioGroupSeleccionTablero);
        //radioGroupSeleccionTablero.setOnCheckedChangeListener(this);//Si la configuracion no es null

        //RadioButtons
        radioButtonAzul=(RadioButton) view.findViewById(R.id.radioButtonAzul);
        radioButtonAluminio=(RadioButton) view.findViewById(R.id.radioButtonAluminio);

        //Imagen Tablero Seleccionado
        imageTableroSeleccionado=(ImageView)view.findViewById(R.id.imageSeleccionTablero);

        //EditText del nombre del usuario
        editTextNombreUsuario=(EditText) view.findViewById(R.id.editTextNombreUsuarioFragmentOptions);

        //Cargar los datos de la configuracion del usuario
        String nombreUsuario=((MainActivity)getActivity()).usuarioDeViewModel.getNombre();//¿Por qué carga el Fragment antes de entrar en onChanged?
        editTextNombreUsuario.setText(nombreUsuario);
//CARGA EL FRAGMENT DE FORMA AUTOMATICA, PASA POR ONCHANGED Y VUELVE A CARGAR EL FRAGMENT CON MI METODO

        /*repositorioConfiguraciones=new RepositorioConfiguraciones(getActivity().getApplication());    //Y SI CARGAMOS LA CONFIGURACION EN ONCHANGED?
        int id=((MainActivity)getActivity()).usuarioDeViewModel.getId();
        configuracion=repositorioConfiguraciones.getConfiguracionUsuario( id );*/

        if(((MainActivity)getActivity()).configuracionDeUsuario!=null) {
            radioGroupSeleccionTablero.setOnCheckedChangeListener(this);
            //Poner la imagen de la configuracion del usuario      << //No es necesario. Marcando el radioButton entra en onCheckedChanged y actualiza la imagen
            //imageTableroSeleccionado.setImageResource(configuracion.getTipoTablero());

            //Marcar radioButton que corresponda
            checkRadioButton();
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //Este metodo es el que se encarga de la comunicacion de los botones con MainActivity
    @Override
    public void onClick(View view) {
        mListener.ClickListener(view);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try{
            mListener=(OnFragmentInteractionListener) activity;
        }catch (ClassCastException cce){}//La Activity debe Implementar OnFragmentInteractionListener
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButtonAzul:
                imageTableroSeleccionado.setImageResource(R.drawable.tablero4enraya);
                imageTableroSeleccionado.setTag(R.drawable.tablero4enraya);
                //configuracion.setTipoTablero(R.drawable.tablero4enraya);
                //((MainActivity)getActivity()).configuracionDeUsuario.setTipoTablero( (Integer) ((Object) imageTableroSeleccionado.getTag()) );
            break;

            case R.id.radioButtonAluminio:
                imageTableroSeleccionado.setImageResource(R.drawable.tableroaluminio);
                imageTableroSeleccionado.setTag(R.drawable.tableroaluminio);
                //configuracion.setTipoTablero(R.drawable.tableroaluminio);
                //((MainActivity)getActivity()).configuracionDeUsuario.setTipoTablero( (Integer) ((Object) imageTableroSeleccionado.getTag()) );
            break;
        }
    }

    public void checkRadioButton(){
        switch (((MainActivity)getActivity()).configuracionDeUsuario.getTipoTablero()){
            case R.drawable.tablero4enraya:
                radioButtonAzul.setChecked(true);
            break;

            case R.drawable.tableroaluminio:
                radioButtonAluminio.setChecked(true);
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
        void ClickListener(View view);
    }

    /*public interface OnClickListener{
        void ClickListener(View view);
    }*/

}
