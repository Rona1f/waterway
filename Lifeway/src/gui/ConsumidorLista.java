package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.beans.Funcionario;
import negocio.beans.Propriedade;
import negocio.beans.TaxasTipo;
import negocio.beans.TipoEmpresa;
import negocio.controle.Fachada;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.graalvm.compiler.nodes.graphbuilderconf.GeneratedInvocationPlugin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ConsumidorLista implements Initializable {

    @FXML
    private Button botaoSair;
    @FXML
    private Button botaoCriarLeitura;
    @FXML
    private Button botaoVerListaConsumires;
    @FXML
    private Button botaoPerfilADM;
    @FXML
    private Button botaoListaRO;
    @FXML
    private Button botaoHomeADM;
    @FXML
    private TableView<Propriedade> consumidorList;

    @FXML
    private TableColumn<Propriedade, String> colunaProprietario;
    @FXML
    private TableColumn<Propriedade, String> colunaTipo;
    @FXML
    private TableColumn<Propriedade, String> colunaEndereco;
    @FXML
    private TableColumn<Propriedade, String> colunaSituacao;

    @FXML
    private Label labelConsumidorList;

    // String[] consumidores = {"Vicente", "Rona", "Ganso"};

    Propriedade propriedadeSelecionada;

    public void SairConta(ActionEvent event) throws IOException {
        App x = new App();
        x.trocarCena("Login.fxml");

    }

    public void irCriarLeitura(ActionEvent event) throws IOException {
        App v = new App();
        v.trocarCena("CriarLeitura.fxml");

    }

    public void irListaConsumidores(ActionEvent event) throws IOException {
        App a = new App();
        a.trocarCena("ConsumidorLista.fxml");

    }

    public void irPerfilADM(ActionEvent event) throws IOException {
        App b = new App();
        b.trocarCena("PerfilADM.fxml");

    }

    public void irListaRO(ActionEvent event) throws IOException {
        App c = new App();
        c.trocarCena("ListaRO.fxml");

    }

    public void irHomeADM(ActionEvent event) throws IOException {
        App d = new App();
        d.trocarCena("HomeADM.fxml");

    }

    public void atualizarLista() {
        consumidorList.getItems().removeAll(consumidorList.getItems());
        for (Propriedade propriedade : Fachada.getInstance().listarPropriedade()) {
            if (propriedade.getEmpresaContratada()
                    .equals(((Funcionario) Fachada.getInstance().getUsuarioLogado()).getEmpresa())) {
                consumidorList.getItems().addAll(propriedade);
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        colunaProprietario.setCellValueFactory(new PropertyValueFactory<>("clienteProprietario"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("idPropriedade"));

        atualizarLista();

        consumidorList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Propriedade>() {

            @Override
            public void changed(ObservableValue<? extends Propriedade> arg0, Propriedade arg1, Propriedade arg2) {
                // TODO Auto-generated method stub
                propriedadeSelecionada = consumidorList.getSelectionModel().getSelectedItem();
            }

        });

        

        

        /*
         * consumidorList.getSelectionModel().selectedItemProperty().addListener(new
         * ChangeListener<String>(){
         * 
         * @Override public void changed(ObservableValue<? extends String> arg0, String
         * arg1, String arg2) { consumidorAtual =
         * consumidorList.getSelectionModel().getSelectedItem();
         * labelConsumidorList.setText(consumidorAtual); }
         * 
         * });
         */
    }
    public void criarConta() {
        
        
     
        



    }

}