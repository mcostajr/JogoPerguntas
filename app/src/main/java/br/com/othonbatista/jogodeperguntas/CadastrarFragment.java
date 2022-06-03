package br.com.othonbatista.jogodeperguntas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarFragment extends Fragment {

    private TextView mEditTextPergunta, mEditTextResposta;

    public CadastrarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar, container, false);

        Button buttonInserir = view.findViewById(R.id.buttonCadastrar);
        mEditTextPergunta = view.findViewById(R.id.editTextPergunta);
        mEditTextResposta = view.findViewById(R.id.editTextResposta);


        buttonInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pergunta = mEditTextPergunta.getText().toString();
                String resposta = mEditTextResposta.getText().toString();

                if ((!pergunta.isEmpty()) && (!resposta.isEmpty())) {
                    Questoes questoes = new Questoes(pergunta, resposta);

                    BancoDeDados.getBancoDeDadosInstancia(getActivity())
                            .getDAO()
                            .inserirQuestao(questoes);

                    mEditTextPergunta.setText("");
                    mEditTextResposta.setText("");

                    Toast.makeText(getActivity(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}