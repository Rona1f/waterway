package negocio.controle;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import excecoes.CPFInvalidoException;
import excecoes.ElementoJaExisteException;
import excecoes.PropriedadeJaCadastradaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import negocio.beans.Conta;
import negocio.beans.Propriedade;
import negocio.beans.ValidaCPF;

public class ControladorPropriedade {

    private static ControladorPropriedade instance;

    private IRepositorioGenerico<Propriedade> repositorioPropriedade;

    ControladorPropriedade() {
        this.repositorioPropriedade = new RepositorioGenerico<>("propriedades.dat");
    }

    public static ControladorPropriedade getInstance() {
        if (instance == null) {
            instance = new ControladorPropriedade();
        }
        return instance;
    }

    public void criarNovaPropriedade(Propriedade propriedade) throws ElementoJaExisteException {
        repositorioPropriedade.inserir(propriedade);
    }

    public void excluirPropriedade(Propriedade propriedade) throws ElementoJaExisteException {
        repositorioPropriedade.remover(propriedade);
    }

    public List<Propriedade> listarPropriedade() {
        return repositorioPropriedade.listar();
    }

    /**
     * Método que identifica propriedade com contas a pagar.
     */
    public void checarInadimplentes() {
        for (Propriedade propriedade : ControladorPropriedade.getInstance().listarPropriedade()) {
            propriedade.setInadimplente(false);
        }
        for (Conta conta : ControladorConta.getInstance().listarContas()) {
            if (conta.getPaga() == false) {
                int indice = repositorioPropriedade.listar().indexOf(conta.getPropriedade());
                repositorioPropriedade.listar().get(indice).setInadimplente(true);
            }
        }
        repositorioPropriedade.salvar();
    }

    /**
     * Método para cadastrar propriedade.
     * 
     * @param propriedade
     * @throws NoSuchAlgorithmException
     * @throws PropriedadeJaCadastradaException
     */
    public void cadastrarPropriedade(Propriedade propriedade)
            throws NoSuchAlgorithmException, PropriedadeJaCadastradaException {

        if (propriedade == null) {
            gerarErroPropriedade("Preencha os campos obrigatórios.");
        }

        try {
            this.repositorioPropriedade.inserir(propriedade);
        } catch (ElementoJaExisteException e) {
            throw new PropriedadeJaCadastradaException(e);
        }

    }

    /**
     * Método para cadastrar propriedades comerciais e industriais.
     * (Contém a informação adicional do CNPJ.)
     * @param propriedade
     * @throws NoSuchAlgorithmException
     * @throws PropriedadeJaCadastradaException
     * @throws CPFInvalidoException
     */
    public void cadastrarPropriedadeComercial(Propriedade propriedade)
            throws NoSuchAlgorithmException, PropriedadeJaCadastradaException, CPFInvalidoException {
        if (propriedade == null) {
            gerarErroPropriedade("Preencha os campos obrigatórios.");
        }

        String cnpj = propriedade.getCnpj();

        if (!(ValidaCPF.isCNPJ(cnpj))) {
            gerarErroPropriedade("CNPJ inválido.");
        } else {
            cadastrarPropriedade(propriedade);
        }
    }


    public void gerarErroPropriedade(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Cadastro não realizado.");
        // alert.setHeaderText("");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
