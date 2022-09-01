package br.unigran.lista_compra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unigran.banco_dados.DBHelper;
import br.unigran.banco_dados.ProdutoDB;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText marca;
    EditText quantidade;

    List<Produto> dados;

    ListView listagem;

    DBHelper db;

    ProdutoDB produtoDB;

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

        db = new DBHelper(this);
        produtoDB = new ProdutoDB(db);

        produtoDB.listar(dados);

        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                alert.setMessage("Confirmar");
                alert.setPositiveButton("remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                return false;
            }
        });
    }

    public void salvar(View view) {
        Produto produto = new Produto();
        produto.setMarca(marca.getText().toString());
        produto.setNome(nome.getText().toString());
        produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));

        produtoDB.inserir(produto);
        produtoDB.listar(dados);

        Toast.makeText(this, "Produto salvo com sucesso.", Toast.LENGTH_SHORT).show();
    }
}