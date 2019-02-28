package com.example.cf17marcsoler.ra2_m8examen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;


public class MakeReserva extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText fecha, comensales, nombre, telefono, comentarios;
    TextView nuevaReserva, comentarioExtraTxt;
    ImageButton sendReserva;

    private MakeReservaListener mListener;

    public MakeReserva() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MakeReserva newInstance() {
        MakeReserva fragment = new MakeReserva();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_reserva, container, false);

        //Region EditTexts
        fecha = view.findViewById(R.id.FechaReservaEditTxt);
        comensales = view.findViewById(R.id.NComensalesEditTxt);
        nombre = view.findViewById(R.id.NombreReservaEditTxt);
        telefono = view.findViewById(R.id.NTelefonoEditTxt);

        comentarios = view.findViewById(R.id.ComentarioEditTxt);
        //endregion

        //Region TextViews
        comentarioExtraTxt = view.findViewById(R.id.comentTxt);

        nuevaReserva = view.findViewById(R.id.NuevaReservaTxt);
        //endregion

        sendReserva= view.findViewById(R.id.SendReserva);

        //Region OnClickListeners
        sendReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.makeReserva(
                        fecha.getText().toString(),
                        Integer.parseInt(comensales.getText().toString()),
                        nombre.getText().toString(),
                        telefono.getText().toString());


                // Llegados a este punto ya no se que estoy haciendo, lo siento :/
                FirebaseDatabase.getInstance().getReference()
                        .child("Reservas")
                        .child(nombre
                                .getText()
                                .toString())
                        .push()
                        .setValue(nombre
                                .getText()
                                .toString());

                FirebaseDatabase.getInstance().getReference()
                        .child("Reservas")
                        .child(nombre
                                .getText()
                                .toString())
                        .push()
                        .setValue(nombre
                                .getText()
                                .toString());

                FirebaseDatabase.getInstance().getReference()
                        .child("Reservas")
                        .child(nombre
                                .getText()
                                .toString())
                        .push()
                        .setValue(nombre
                                .getText()
                                .toString());



            }
        });
        //endregion

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MakeReservaListener) {
            mListener = (MakeReservaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MakeReservaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface MakeReservaListener {
        // TODO: Update argument type and name
        void makeReserva(String fecha, int comensales, String nombre, String telefono);
        void guardar();
    }
}
