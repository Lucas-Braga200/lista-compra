package br.unigran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProdutoHolder extends RecyclerView.ViewHolder {
        public ProdutoHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
