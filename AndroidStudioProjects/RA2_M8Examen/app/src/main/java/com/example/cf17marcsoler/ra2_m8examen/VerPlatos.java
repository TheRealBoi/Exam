package com.example.cf17marcsoler.ra2_m8examen;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class VerPlatos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PlatosInteface mListener;

    public VerPlatos() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VerPlatos newInstance(String param1, String param2) {
        VerPlatos fragment = new VerPlatos();
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
        return inflater.inflate(R.layout.fragment_ver_platos, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlatosInteface) {
            mListener = (PlatosInteface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PlatosInteface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface PlatosInteface {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class MiHilo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            URL url;
            String result = "";

            try{
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while (data != -1) {
                    result += (char) data;
                    data = inputStream.read();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            getEntantes(data);

        }

        public void getEntantes(String data) {
            try {

                JSONObject jsonObject = new JSONObject(data);
                JSONObject entrantes = (JSONObject) jsonObject.get("entrantes");

                for(int i = 0; i < entrantes.length(); i++) {
                    JSONObject ingredientes = (JSONObject) entrantes.get("ingredientes");
                    JSONObject precio = (JSONObject) entrantes.get("precio");
                    JSONObject nombre = (JSONObject) entrantes.get("nombre");
                }
            }catch(Exception e){ e.printStackTrace();}

        }

        public void getPrincipales(String data) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONObject principales = (JSONObject) jsonObject.get("principales");

                for(int i = 0; i < principales.length(); i++) {
                    JSONObject ingredientes = (JSONObject) jsonObject.get("ingredientes");
                    JSONObject precio = (JSONObject) jsonObject.get("precio");
                    JSONObject nombre = (JSONObject) jsonObject.get("nombre");
                }

            }catch(Exception e) {e.printStackTrace();}
        }

    }

}
