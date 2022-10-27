package br.unigran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import br.unigran.lista_compra.Produto;
import br.unigran.lista_compra.R;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoHolder> {
    List<Produto> dados;

    public ProdutoAdapter(List<Produto> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha, parent, false);
        return new ProdutoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoHolder holder, int position) {
        holder.titulo.setText(dados.get(position).getNome());
        holder.subtitulo.setText(dados.get(position).getMarca());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ProdutoHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public TextView subtitulo;
        public Button editar;
        public ProdutoHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.idProduto);
            subtitulo = itemView.findViewById(R.id.idMarca);
            editar = itemView.findViewById(R.id.idEditar);
        }
    }
}
