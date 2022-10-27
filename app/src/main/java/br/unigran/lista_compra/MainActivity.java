package br.unigran.lista_compra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unigran.adapter.ProdutoAdapter;
import br.unigran.banco_dados.DBHelper;
import br.unigran.banco_dados.ProdutoDB;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText marca;
    EditText quantidade;

    Button botaoSalvar;
    Button botaoCancelar;

    Integer atualizando;

    List<Produto> dados;

    ListView listagem;

    DBHelper db;

    ProdutoDB produtoDB;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutManager = new LinearLayoutManager(this);

        nome = findViewById(R.id.nome);
        marca = findViewById(R.id.marca);
        quantidade = findViewById(R.id.quantidade);
        botaoSalvar = findViewById(R.id.salvar);
        botaoCancelar = findViewById(R.id.cancelar);

        dados = new ArrayList();

        // listagem = findViewById(R.id.lista);

        // ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        // listagem.setAdapter(adapter);

        db = new DBHelper(this);
        produtoDB = new ProdutoDB(db);
        produtoDB.listar(dados);

        adapter = new ProdutoAdapter(dados);

        recyclerView = findViewById(R.id.idRecycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        toggleEstadoAtualizacao(null);

        /*
        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int j, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setMessage("Realmente quer remover?");
                alert.setPositiveButton("remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        produtoDB.remover(dados.get(j).getId());
                        produtoDB.listar(dados);
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
                return false;
            }
        });
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toggleEstadoAtualizacao(dados.get(i).getId());
                nome.setText(dados.get(i).getNome());
                marca.setText(dados.get(i).getMarca());
                quantidade.setText(dados.get(i).getQuantidade().toString());
            }
        });
         */
    }

    public void toggleEstadoAtualizacao(Integer id) {
        if (id == null) {
            atualizando = null;
            botaoCancelar.setEnabled(false);
        } else {
            atualizando = id;
            botaoCancelar.setEnabled(true);
        }
    }

    public void limparCampos() {
        marca.setText("");
        nome.setText("");
        quantidade.setText("");
    }

    public void cancelarAtualizacao(View view) {
        atualizando = null;
        toggleEstadoAtualizacao(null);
        limparCampos();
    }

    public void salvar(View view) {
        Produto produto = new Produto();
        if (atualizando != null) {
            produto.setId(atualizando);
        }

        produto.setMarca(marca.getText().toString());
        produto.setNome(nome.getText().toString());
        produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));

        if (atualizando != null) {
            produtoDB.atualizar(produto);
            Toast.makeText(this, "Produto atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            produtoDB.inserir(produto);
            Toast.makeText(this, "Produto salvo com sucesso.", Toast.LENGTH_SHORT).show();
        }
        toggleEstadoAtualizacao(null);
        limparCampos();
        produtoDB.listar(dados);
    }
}