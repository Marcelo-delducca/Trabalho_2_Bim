package com.example.trabalho_2_bim;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private TextView textNome, textCategoria, textPreco, textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        // Inicializar TextViews
        textNome = findViewById(R.id.textNome);
        textCategoria = findViewById(R.id.textCategoria);
        textPreco = findViewById(R.id.textPreco);
        textDescricao = findViewById(R.id.textDescricao);

        // Receber o produto via Intent
        Produto produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null) {
            textNome.setText(produto.getNome());
            textCategoria.setText(produto.getCategoria());
            textPreco.setText(String.format("R$ %.2f", produto.getPreco()));
            textDescricao.setText(produto.getDescricao());
        } else {
            // Caso o produto seja nulo
            textNome.setText("Produto não encontrado");
        }
    }
}
