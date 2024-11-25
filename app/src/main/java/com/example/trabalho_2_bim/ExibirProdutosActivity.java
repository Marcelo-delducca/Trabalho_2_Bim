package com.example.trabalho_2_bim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ExibirProdutosActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapter;
    private ArrayList<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_produtos);

        // Inicializar ListView
        listViewProdutos = findViewById(R.id.listViewProdutos);

        // Receber a lista de produtos
        listaProdutos = (ArrayList<Produto>) getIntent().getSerializableExtra("produtos");

        if (listaProdutos == null || listaProdutos.isEmpty()) {
            Toast.makeText(this, "Nenhum produto cadastrado!", Toast.LENGTH_SHORT).show();
            listaProdutos = new ArrayList<>(); // Evita NullPointerException
        }

        // Configurar o adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProdutos);
        listViewProdutos.setAdapter(adapter);

        // Evento de clique para exibir detalhes
        listViewProdutos.setOnItemClickListener((parent, view, position, id) -> {
            Produto produto = listaProdutos.get(position);

            // Intent para abrir a nova tela com detalhes do produto
            Intent intent = new Intent(ExibirProdutosActivity.this, DetalhesProdutoActivity.class);
            intent.putExtra("produto", produto);
            startActivity(intent);
        });
    }
}
