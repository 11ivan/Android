package icastillo.pruebaexamen2trimestre.application;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.time.Clock;

import icastillo.pruebaexamen2trimestre.R;
import icastillo.pruebaexamen2trimestre.room.entities.Persona;
import icastillo.pruebaexamen2trimestre.viewholders.ViewHolderPersona;
import icastillo.pruebaexamen2trimestre.viewmodels.ViewModel;


public class FragmentListPersonas extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    MyAdapter adapterListaPersonas;
    ViewModel viewModel;
    Persona[] arrayPersonas;

    //Mis variables XML
    ListView listView;
    Button btnAddPersona;

    public FragmentListPersonas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragmentPersonas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListPersonas newInstance(String param1, String param2) {
        FragmentListPersonas fragment = new FragmentListPersonas();
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
        View view=inflater.inflate(R.layout.fragment_list, container, false);

        listView=(ListView) view.findViewById(R.id.listViewPersonas);
        btnAddPersona=(Button) view.findViewById(R.id.btnAddPersona);
        listView.setOnItemClickListener(this);
        btnAddPersona.setOnClickListener(this);

        //ViewModel
        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getLiveDataPersonas().observe(this, new Observer<Persona[]>() {
            @Override
            public void onChanged(@Nullable Persona[] personas) {
                if(personas!=null){
                    arrayPersonas=personas;
                    adapterListaPersonas=new MyAdapter( ((MainActivity)getActivity()), R.layout.stylelistapersonas, R.id.nombrePersona, arrayPersonas);
                    listView.setAdapter(adapterListaPersonas);
                }
            }
        });

        viewModel.cargaPersonas();

        /*if( arrayPersonas != null ){
            adapterListaPersonas=new MyAdapter( ((MainActivity)getActivity()), R.layout.stylelistapersonas, R.id.nombrePersona, arrayPersonas);
            listView.setAdapter(adapterListaPersonas);
        }*/

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try{
            mListener=(OnFragmentInteractionListener) activity;
        }catch (ClassCastException cce){}//La Activity debe Implementar OnFragmentInteractionListener
    }*/

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
        mListener.buttonClickListener(v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.listItemClickListener(view, position);
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
        void listItemClickListener(View view, int position);
        void buttonClickListener(View view);
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
            ViewHolderPersona viewHolder=null;

            if(row==null){
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    row = inflater.inflate(R.layout.stylelistapersonas, parent, false);

                    viewHolder = new ViewHolderPersona(row);
                    row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolderPersona) row.getTag();
            }

            viewHolder.getTextViewNombre().setText( arrayPersonas[position].getNombre() );
            viewHolder.getTextViewApellidos().setText( arrayPersonas[position].getApellidos() );

            return (row);
        }

    }



}