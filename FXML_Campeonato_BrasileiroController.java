package campeonato_brasileiro;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXML_Campeonato_BrasileiroController implements Initializable {
    @FXML private TextField nrJogos;
    @FXML private TextField equipe;
    @FXML private TextField pontuacao;
    @FXML private TextField nrVitorias;
    @FXML private TextField nrDerrotas;
    @FXML private TextField nrEmpates;
    @FXML private TextField golsContra;
    @FXML private TextField golsFavor;
    @FXML private TextArea idTextArea;
    @FXML private Label LtimeMaiorVit;
    @FXML private Label LtimeMaiorAtaq;
    @FXML private Label LtimeMelhorDef;
    @FXML private Label LtimeMelhorAprov;
    
    FileWriter fw;
    FileReader fr;
    
    int vitorias = 0;
    int derrotas = 0;
    int empates = 0;
    
    int maiorVit = 0;
    String timeMaiorVit = "";
    
    int maiorAtaq = 0;
    String timeMaiorAtaq = "";
    
    int melhorDef = 999;
    String timeMelhorDef = "";
    
    int aprov = 0;
    int melhorAprov = 0;
    String timeMelhorAprov = "";
    int nrPontosDisp = 0;
    
    int jogosTot = 0;
    int totJogosTime = 0;
    int jogosTotCampeonato = 0;
//============x=================================================================    
    @FXML
    private void ActionCarregarButton() { // ao ser clicado em INCLUIR       

        // cria o arquivo TXT e concatena os dados
        String dados = "";
        dados = 
        equipe.getText() + ";" + pontuacao.getText() + ";" + 
        nrVitorias.getText() + ";" + nrDerrotas.getText() + ";" + 
        nrEmpates.getText() + ";" + golsContra.getText() + ";" + 
        golsFavor.getText() + "\r\n";
        System.out.println("Linha de dados" + dados);
        
        // Salva os "dados" num arquivo de nome "campeonato.txt"
        try {
            fw = new FileWriter("campeonato.txt", true);
            BufferedWriter arq = new BufferedWriter(fw);
            arq.write(dados);
            arq.close();
            System.out.println("Gravação feita com sucesso.");
        }catch(IOException e){
            System.out.println ("Houve um erro: " + e);
        }
        //===================
        idTextArea.clear(); // IMPORTANTE para não duplicar os dados na idTextArea
        try{
            fr = new FileReader("campeonato.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
                
            while((s = br.readLine()) != null){ 
                System.out.println("Aqui a STRING s " + s);
                String campos[] = s.split(";");
                //nesse FOR os dados serão inseridos na TextArea
                for (String i:campos)
                    idTextArea.appendText(i + "\t\t");
                    idTextArea.appendText("\r\n");
                    
            //a partir daqui serão realizados os cálculos de:
            // maior numero de vitórias =  maiorVit
            if(Integer.parseInt(campos[2]) > maiorVit){
                   maiorVit = Integer.parseInt(campos[2]);
                   timeMaiorVit = campos[0];
                }
            
            // maior numero de ataque =  maiorAtaq   
            if(Integer.parseInt(campos[6]) > maiorAtaq){
                   maiorAtaq = Integer.parseInt(campos[6]);
                   timeMaiorAtaq = campos[0];
                }
            
            // melhor defesa =  melhorDef   
            if(Integer.parseInt(campos[5]) < melhorDef){
                   melhorDef = Integer.parseInt(campos[5]);
                   timeMelhorDef = campos[0];
                }
            
            // melhor aproveitamento
            int numeroJogos = Integer.parseInt(campos[2])+ Integer.parseInt(campos[3]) + Integer.parseInt(campos[4]);
                if(Integer.parseInt(campos[1]) * 100 / (numeroJogos > 0 ? numeroJogos * 3 : 1) > melhorAprov){
                    melhorAprov = Integer.parseInt(campos[1]);
                    timeMelhorAprov = campos[0];
                }
          
/*
// limitando quantidade de jogos totais e gerando alerta
            vitorias = Integer.parseInt(nrVitorias.getText());
            derrotas = Integer.parseInt(nrDerrotas.getText());
            empates = Integer.parseInt(nrEmpates.getText());
            jogosTot = Integer.parseInt(nrJogos.getText());
            System.out.println("Número de Jogos: " + jogosTot);
            if(jogosTot > 0){
                totJogosTime = vitorias + derrotas + empates;
            }
            System.out.println("Número de Jogos de cada time: " + totJogosTime);
        
            if(jogosTot != totJogosTime){
                JOptionPane.showMessageDialog(Alerta, "O número total de jogos desta equipe não confere com o total de jogos.");
            }
        */
            // os campos de formulário recebem um vazio após o carregamento
                    equipe.setText("");
                    pontuacao.setText("");
                    nrVitorias.setText("");
                    nrDerrotas.setText("");
                    nrEmpates.setText("");
                    golsContra.setText("");
                    golsFavor.setText("");
                    System.out.println("Os campos recebem um vazio");
            }
               // mostra os cáculos no TextArea de cáculos
               LtimeMaiorVit.setText(timeMaiorVit);
               LtimeMaiorAtaq.setText(timeMaiorAtaq);
               LtimeMelhorDef.setText(timeMelhorDef);
               LtimeMelhorAprov.setText(timeMelhorAprov);
        }catch (IOException e){
            System.out.println ("Houve um erro: " + e);
        }
    }
    //========================================================================
    @FXML
    private void ActionAbrirButton() { // ao ser clicado abre o arquivo campeonato.txt
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();    
        try {
            desktop.open(new File("campeonato.txt"));
        } catch (IOException ex) {
            Logger.getLogger(FXML_Campeonato_BrasileiroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //========================================================================
    @FXML
    private void handleMenuCloseAction() {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}