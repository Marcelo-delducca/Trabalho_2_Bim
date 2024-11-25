package com.example.trabalho_2_bim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNomeProduto, edtPrecoProduto, edtDescricaoProduto;
    private Spinner spinnerCategoria;
    private Button btnSalvarProduto, btnExibirProdutos;
    private ArrayList<Produto> listaProdutos = new ArrayList<>(); // Lista para armazenar produtos

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Inicializar componentes
        edtNomeProduto = findViewById(R.id.etNomeProduto);
        edtPrecoProduto = findViewById(R.id.etPreco);
        edtDescricaoProduto = findViewById(R.id.etDescricao);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnSalvarProduto = findViewById(R.id.btnSalvar);
        btnExibirProdutos = findViewById(R.id.btnExibirProdutos);

        String[] categorias = {"Eletrônicos", "Alimentos", "Roupas", "Livros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        // Evento de clique para salvar o produto
        btnSalvarProduto.setOnClickListener(v -> {
            // Verificar se os campos estão preenchidos
            String nome = edtNomeProduto.getText().toString();
            String categoria = spinnerCategoria.getSelectedItem().toString();
            String precoStr = edtPrecoProduto.getText().toString();
            String descricao = edtDescricaoProduto.getText().toString();

            if (nome.isEmpty() || precoStr.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Converter preço para double
            double preco = Double.parseDouble(precoStr);

            // Criar um novo produto
            Produto produto = new Produto(nome, categoria, preco, descricao);

            // Adicionar o produto à lista
            listaProdutos.add(produto);

            // Limpar os campos após salvar
            edtNomeProduto.setText("");
            edtPrecoProduto.setText("");
            edtDescricaoProduto.setText("");

            // Mostrar mensagem de sucesso
            Toast.makeText(CadastroActivity.this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
        });

        // Evento de clique para exibir a lista de produtos
        btnExibirProdutos.setOnClickListener(v -> {
            // Passar a lista de produtos para a ExibirProdutosActivity
            Intent intent = new Intent(CadastroActivity.this, ExibirProdutosActivity.class);
            intent.putExtra("produtos", listaProdutos); // Passando a lista de produtos
            startActivity(intent);
        });
    }
}
