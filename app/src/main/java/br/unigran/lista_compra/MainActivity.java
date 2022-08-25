package br.unigran.lista_compra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText marca;
    EditText quantidade;

    List<Produto> dados;

    ListView listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        marca = findViewById(R.id.marca);
        quantidade = findViewById(R.id.quantidade);

        dados = new ArrayList();

        listagem = findViewById(R.id.lista);

        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(adapter);
    }

    public void salvar(View view) {
        Produto produto = new Produto();
        produto.setMarca(marca.getText().toString());
        produto.setNome(nome.getText().toString());
        produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));

        dados.add(produto);
        Toast.makeText(this, "Produto salvo com sucesso.", Toast.LENGTH_SHORT).show();
    }
}