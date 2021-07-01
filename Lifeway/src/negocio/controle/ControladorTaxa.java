package negocio.controle;

import java.util.List;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import excecoes.ElementoJaExisteException;
import negocio.beans.Taxa;

public class ControladorTaxa {
    
    private static ControladorTaxa instance;

    private IRepositorioGenerico<Taxa> repositorioTaxa;

    private ControladorTaxa() {
        repositorioTaxa = new RepositorioGenerico<>();
    }

    public static ControladorTaxa getInstance() {
        if(instance == null) {
            instance = new ControladorTaxa();
        }
        return instance;
    }

    public void criarTaxa(Taxa taxa) throws ElementoJaExisteException {
        repositorioTaxa.inserir(taxa);
    }

    public void removerTaxa(Taxa taxa) {
        repositorioTaxa.remover(taxa);
    }

    public List<Taxa> listarTaxas() {
        return repositorioTaxa.listar();
    }
}
