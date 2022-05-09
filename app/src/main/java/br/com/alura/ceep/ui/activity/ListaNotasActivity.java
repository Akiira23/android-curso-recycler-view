package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.recyclerview.adapter.ListasNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this,
                        FormularioNotaActivity.class);
                startActivity(iniciaFormularioNota);
            }
        });

        List<Nota> todasNotas = notasDeExemplo();
        configuraRecyclerView(todasNotas);
    }

    @Override
    protected void onResume() {
        NotaDAO dao = new NotaDAO();
        List<Nota> todasNotas = dao.todos();
        configuraRecyclerView(todasNotas);
        super.onResume();
    }

    private List<Nota> notasDeExemplo() {
        NotaDAO dao = new NotaDAO();
        dao.insere(new Nota("Primeira nota ", "Descrição pequena."));
        dao.insere(new Nota("Segunda nota ", "Segunda descrição " +
                "é bem maior que a da primeira nota."));

        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
        // configuraLayoutManager(listaNotas); //configurado no xml "app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager""
    }

    private void configuraLayoutManager(RecyclerView listaNotas) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(layoutManager);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        listaNotas.setAdapter(new ListasNotasAdapter(this, todasNotas));
    }
}