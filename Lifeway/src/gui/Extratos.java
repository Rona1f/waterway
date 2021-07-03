package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.beans.Conta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import negocio.controle.Fachada;


public class Extratos implements Initializable {

    @FXML
    private TableView<Conta> contaList;
    @FXML 
    private TableColumn<Conta, String> colunaPropriedade;
    @FXML
    private TableColumn<Conta, String> colunaEmissao;
    @FXML
    private TableColumn<Conta, String> colunaVencimento;
    @FXML
    private TableColumn<Conta, String> colunaPreco;
    @FXML
    private TableColumn<Conta, String> colunaTaxa;
    @FXML
    private TableColumn<Conta, String> colunaPagaEm;

    private Conta contaSelecionada;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colunaPropriedade.setCellValueFactory(new PropertyValueFactory<>("propriedade"));
        colunaEmissao.setCellValueFactory(new PropertyValueFactory<>("dataEmissao"));
        colunaVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colunaTaxa.setCellValueFactory(new PropertyValueFactory<>("taxaAplicada"));
        
        colunaPagaEm.setCellValueFactory(new PropertyValueFactory<>("pagaEm"));

        

        atualizarLista();

        contaList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Conta>(){
            @Override
            public void changed(ObservableValue<? extends Conta> arg0, Conta arg1, Conta arg2) {
                contaSelecionada = contaList.getSelectionModel().getSelectedItem();
                
                
            }
        });


        
    }

    private void atualizarLista() {
        for (Conta conta : Fachada.getInstance().listarContas()) {
            if(conta.getPropriedade().getClienteProprietario().equals(Fachada.getInstance().getUsuarioLogado()) && conta.getPaga()==true) {
                contaList.getItems().addAll(conta);
            }
        }
    }


    public void SairConta(ActionEvent event) throws IOException {
        App x = new App();
        x.trocarCena("Login.fxml");

    }

    public void irExtratos(ActionEvent event) throws IOException {
        App v = new App();
        v.trocarCena("Extratos.fxml");

    }

    public void irPagamentos(ActionEvent event) throws IOException {
        App a = new App();
        a.trocarCena("Pagamentos.fxml");

    }

    public void irPerfil(ActionEvent event) throws IOException {
        App b = new App();
        b.trocarCena("Perfil.fxml");

    }

    public void irRO(ActionEvent event) throws IOException {
        App c = new App();
        c.trocarCena("RO.fxml");

    }

    public void irHome(ActionEvent event) throws IOException {
        App d = new App();
        d.trocarCena("Menu.fxml");

    }

    public void irListaTaxas() throws IOException {
        App d = new App();
        d.trocarCena("Taxas.fxml");
    }

}