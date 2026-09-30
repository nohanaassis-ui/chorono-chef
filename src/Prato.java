import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Prato {
    private final String nome;
    private final List<Ingrediente> ingredientes; // Declaração da lista

    public Prato(String nome) {
        this.nome = nome;
        this.ingredientes = new ArrayList<>(); // <--- ESTA LINHA É O QUE FALTAVA!
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        if (ingrediente != null) {
            this.ingredientes.add(ingrediente);
        }
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public double calcularCustoTotal() {
        double total = 0.0;
        for (Ingrediente ing : ingredientes) {
            total += ing.getCusto();
        }
        return total;
    }
}