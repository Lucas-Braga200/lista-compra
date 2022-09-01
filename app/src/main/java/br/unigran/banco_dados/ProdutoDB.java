package br.unigran.banco_dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.unigran.lista_compra.Produto;

public class ProdutoDB {
    DBHelper db;
    private SQLiteDatabase conexao;

    public ProdutoDB(DBHelper db) {
        this.db = db;
    }

    public void inserir(Produto produto) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("marca", produto.getMarca());
        valores.put("quantidade", produto.getQuantidade());
        conexao.insertOrThrow("produto", null, valores);
        conexao.close();
    }

    public void atualizar() {
    }

    public void remover(Integer id) {
        conexao = db.getWritableDatabase();
        conexao.delete("produto", "id=?", new String[]{ id+"" });
        conexao.close();
    }

    public void listar(List dados) {
        dados.clear();
        conexao = db.getReadableDatabase();
        String names[] = {"id", "nome", "marca", "quantidade"};
        Cursor query = conexao.query("produto", names, null, null, null, null, "nome");
        while (query.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(Integer.parseInt(query.getString(0)));
            produto.setNome(query.getString(1));
            produto.setMarca(query.getString(2));
            produto.setQuantidade(Integer.parseInt(query.getString(3)));
            dados.add(produto);
        }
        conexao.close();
    }
}
