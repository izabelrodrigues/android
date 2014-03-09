package com.devspace.ninja.navigationdrawerexample2;//o nome do seu pacote

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OptionFragment extends Fragment {
	
	private View rootView;
	private TextView txtFrag;
	public static final String OPTION = "option";
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initViews(inflater, container);
		
		onConfigFrag();
		return rootView;
	}

	private void initViews(LayoutInflater inflater, ViewGroup container){
		
		rootView = inflater.inflate(R.layout.option_fragment, container, false);
		txtFrag = (TextView) rootView.findViewById(R.id.option_fragment_txt_frag);
	}
	
	private void onConfigFrag(){
		int i = getArguments().getInt(OPTION); //Recupera o parâmetro passado como argumento para essa classe

		//Muda o texto de exibição de acordo com o parâmetro passado.
		switch (i) {
		case 0:
			txtFrag.setText(getResources().getString(R.string.option_frag_txt01));
			break;
		case 1:
			txtFrag.setText(getResources().getString(R.string.option_frag_txt02));
			break;
		case 2:
			txtFrag.setText(getResources().getString(R.string.option_frag_txt03));
			break;
		case 3:
			txtFrag.setText(getResources().getString(R.string.option_frag_txt04));
			break;
		default:
			txtFrag.setText(getResources().getString(R.string.option_frag_txt01));
		}
		
	}
	
}
