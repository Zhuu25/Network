/*
Maneekan Yanvisit  5810405258
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class Model{

    public String type;
    public String vocab;
    private String wo = "";
    public String vv;
    public int wrong;
    public int score;
    protected String name;
    public Stage stage;

    @FXML
    protected Circle head;
    @FXML
    protected Line body;
    @FXML
    protected Line leLeg;
    @FXML
    protected Line riLeg;
    @FXML
    protected Line leArm;
    @FXML
    protected Line riArm;


    @FXML
    protected Label word;

    @FXML
    public void initialize(){
        type = Client.type;
        name = Client.user;
        stage = Client.stage;
        score = Client.score;
        vocab = Client.vocab;

        vv = vocab;
        vocab = getVocab(vocab.toUpperCase());
        setWord(vv);
    }

    @FXML
    public void setNewGame(){
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Client.setScore(0);
        try {
            stage.setScene(new Scene((Parent) loadMain.load()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void getNewWord(){
        Client.setScore(score);
        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("ModelPage.fxml"));
        try {
            Client.connecting(type);
            stage.setScene(new Scene((Parent) loadMain.load()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void setWord(String vo){
        for (int i = 0 ; i < vo.length(); i++){
            if (i < vo.length()-1){
                if (vo.charAt(i) == ' '){
                    wo += "  ";
                }
                else {
                    wo += "_ ";
                }
            }
            else{
                wo += "_";
            }
        }
        word.setText(wo);
    }

    @FXML
    public void play(ActionEvent event){
        int check = 0;
        int under = 0;
        StringBuilder builder = new StringBuilder(wo);
        Button bt = (Button) event.getSource();
        String ch = bt.getText();
        bt.setVisible(false);
        for (int i = 0; i < vocab.length(); i++){
            if (ch.charAt(0) == (vocab.charAt(i))){
                builder.setCharAt(i, ch.charAt(0));
                wo = builder.toString();
                word.setText(builder.toString());
                check += 1;
            }
        }
        for (int i = 0; i < builder.length(); i++){
            if (builder.charAt(i) == '_'){
                under += 1;
            }
        }
        if (under == 0){
            win();
        }
        if (check == 0){
            wrong += 1;
            if (wrong == 1){
                head.setOpacity(1);
            }
            else if (wrong == 2){
                body.setOpacity(1);
            }
            else if (wrong == 3){
                leLeg.setOpacity(1);
            }
            else if (wrong == 4){
                riLeg.setOpacity(1);
            }
            else if (wrong == 5){
                leArm.setOpacity(1);
            }
            else if (wrong == 6){
                riArm.setOpacity(1);
            }
            else{
                lose();

            }
        }
    }

    @FXML
    public void lose(){
        ButtonType buttonType = new ButtonType("Exit");
        ButtonType buttonType1 = new ButtonType("New Game");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The answer is "+vv+".\nYour can do "+score+" words.", buttonType, buttonType1);
        alert.setHeaderText("Game Over!");
        alert.showAndWait();
        if (alert.getResult() == buttonType){
            stage.close();
            alert.close();
        }
        if (alert.getResult() == buttonType1){
            setNewGame();
        }
    }

    @FXML
    public void win(){
        score += 1;
        ButtonType buttonType = new ButtonType("Exit");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are correct. The answer is "+vv+".", ButtonType.NEXT, buttonType);
        alert.setHeaderText("Congratulations "+name);
        alert.showAndWait();
        if (alert.getResult() == buttonType){
            stage.close();
            alert.close();
        }
        if (alert.getResult() == ButtonType.NEXT){
            getNewWord();
        }
    }

    public String getVocab(String ori){
        String st = "";
        for (int i = 0; i < ori.length(); i++){
            if (i == ori.length()-1){
                st += ori.charAt(i);
            }
            else{
                st += ori.charAt(i)+" ";
            }
        }
        return st;
    }
}
