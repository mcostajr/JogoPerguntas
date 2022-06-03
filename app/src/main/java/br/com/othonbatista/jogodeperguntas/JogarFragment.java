package br.com.othonbatista.jogodeperguntas;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class JogarFragment extends Fragment {

    private List<Questoes> mListQuestoes;
    private TextView mTextViewPergunta, mTextViewResposta;

    public JogarFragment() {
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
        View view = inflater.inflate(R.layout.fragment_jogar, container, false);

        Button mbuttonExibirResposta = view.findViewById(R.id.buttonExibirResposta);
        Button mbuttonPular = view.findViewById(R.id.buttonPular);

        mTextViewPergunta = view.findViewById(R.id.textViewPergunta);
        mTextViewResposta = view.findViewById(R.id.textViewResposta);
        mListQuestoes = BancoDeDados.getBancoDeDadosInstancia(getActivity()).getDAO().pesquisarTodasQuestoes();
        proximaQuestao();

        mbuttonExibirResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirResposta();
            }
        });

        mbuttonPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proximaQuestao();
            }
        });
        return view;
    }

    private void proximaQuestao(){
        if (!mListQuestoes.isEmpty()){
            int totalDeQuestoes = mListQuestoes.size();

            int indexAleatorio = new Random().nextInt(totalDeQuestoes);

            Questoes questoes = mListQuestoes.get(indexAleatorio);

            mTextViewPergunta.setText(questoes.getPergunta());
            mTextViewResposta.setText(questoes.getResposta());

            mTextViewResposta.setVisibility(View.GONE);
        }
    }

    private void exibirResposta() {
        mTextViewResposta.setVisibility(View.VISIBLE);
    }


}