package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import negocio.beans.Bandeira;
import negocio.beans.Empresa;
import negocio.beans.Endereco;
import negocio.beans.Funcionario;
import negocio.beans.RegistroDeOcorrencia;
import negocio.beans.TaxaFixa;
import negocio.beans.TipoPropriedade;

public class ControladorEmpresas {
    
    private IRepositorioGenerico<Empresa> repositorioEmpresas;
    private static ControladorEmpresas instance;

    private ControladorEmpresas() {
        this.repositorioEmpresas = new RepositorioGenerico<>();
    }

    public static ControladorEmpresas getInstance() {
        if(instance == null) {
            instance = new ControladorEmpresas();
        }

        return instance;
    }

    /**
     * @return the repositorioEmpresas
     */
    public IRepositorioGenerico<Empresa> getRepositorioEmpresas() {
        return repositorioEmpresas;
    }

    public Empresa selecionarEmpresa(String idEmpresa){
        return repositorioEmpresas.buscarPorID(idEmpresa);
    }

    //@Override
    // public void inserir(Empresa obj) throws ObjetoJaExisteException {
    //     repositorioEmpresas.inserir(obj);
    // }

    //@Override
    //public void remover(Empresa obj) throws ObjetoNaoExisteException {

    //}

    public Empresa criarEmpresa(String idEmpresa, String nome, String servico){
        Empresa novaEmpresa = new Empresa(idEmpresa, nome, servico);
        try {
            repositorioEmpresas.inserir(novaEmpresa);
        } catch (Exception e) {
            return null;
        }
        return novaEmpresa;
    }
    public void removerEmpresa(String idEmpresa){
        repositorioEmpresas.removerPorID(idEmpresa);
    }

    public Funcionario criarFuncionarioNaEmpresa(String nome, String identificacao, String senha, String idEmpresa){
        Funcionario novoFuncionario = new Funcionario(nome, identificacao, senha, idEmpresa);
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa); // <- fazer metodo pra pegar empresa por ID

        //try {
            //empresaSelecionada.getRepositorioFuncionarios().inserir(novoFuncionario);
        //} catch (Exception e) {
            //return null;
        //}
        return novoFuncionario;
    }

    public void removerFuncionarioNaEmpresa(String idEmpresa, String idFuncionario){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        //empresaSelecionada.getRepositorioFuncionarios().removerPorID(idFuncionario);
    }

    public void adicionarTaxaDoTipoNaEmpresa(String idEmpresa, TipoPropriedade tipo){
        Empresa empresaSelecionada =  selecionarEmpresa(idEmpresa);
        empresaSelecionada.getTaxas().criarTaxaDoTipo(tipo);
    }


    public void adicionarTaxaFixaPorTipoNaEmpresa(String idEmpresa, TipoPropriedade tipo, TaxaFixa taxaFixa){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        empresaSelecionada.getTaxas().getTaxasDoTipo(tipo).setFixa(taxaFixa);
    }

    public void adicionarTaxaAdicionalPorTipoNaEmpresa(String idEmpresa, TipoPropriedade tipo, double de, double ate, double valor){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        empresaSelecionada.getTaxas().getTaxasDoTipo(tipo).adicionarAdicional(de, ate, valor);
    
    }

    public void adicionarTarifaNaEmpresa(String idEmpresa, String nomeTarifa, Double valor){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        empresaSelecionada.getTaxas().adicionarTarifa(nomeTarifa, valor);
    }

    public void definirBandeiraDaEmpresa(String idEmpresa, Bandeira bandeira, float valor){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        empresaSelecionada.getTaxas().definirBandeira(bandeira, valor);
    }


    //public List<Funcionario> listarFuncionariosDaEmpresa(String idEmpresa){
        //return repositorioEmpresas.buscarPorID(idEmpresa).getRepositorioFuncionarios().listar();
    //}

    public List<Empresa> listarEmpresas(){
        return repositorioEmpresas.listar();
    }


    public void reportarProblema(String protocolo, String assunto, String mensagem, String idEmpresa, LocalDate data, Endereco endereco){
        RegistroDeOcorrencia novoReport = new RegistroDeOcorrencia(protocolo, assunto, mensagem, idEmpresa, data, endereco);
        try {
            selecionarEmpresa(idEmpresa).getRepositorioRDO().inserir(novoReport);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void resolverProblema(String idEmpresa, String protocolo){
        selecionarEmpresa(idEmpresa).getRepositorioRDO().buscarPorID(protocolo).setResolvido(true);
    }

    public List<RegistroDeOcorrencia> listarProblemas(String idEmpresa) {
        return selecionarEmpresa(idEmpresa).getRepositorioRDO().listar();
    }

    public List<RegistroDeOcorrencia> listarProblemasPendentes(String idEmpresa) {
        List<RegistroDeOcorrencia> problemasPendentes = new ArrayList<>();
        for (RegistroDeOcorrencia report : this.listarProblemas(idEmpresa)) {
            if(report.getResolvido()==false) problemasPendentes.add(report);
        }
        return problemasPendentes;
    }




    /* public Cliente criarClienteNaEmpresa(String idEmpresa, String nome, String cpf, LocalDate dataNascimento){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        Cliente novoCliente = new Cliente(nome, cpf, dataNascimento);
        try {
            empresaSelecionada.getRepositorioClientes().inserir(novoCliente);
        } catch (Exception e) {
            return null;
        }
        return novoCliente;
    }

    public void removerClienteDaEmpresa(String idEmpresa, String idCliente){
        Empresa empresaSelecionada = selecionarEmpresa(idEmpresa);
        empresaSelecionada.getRepositorioClientes().removerPorID(idCliente);
    } */

    


}
