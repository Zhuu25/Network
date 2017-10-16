
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/*
Maneekan Yanvisit  5810405258
 */
public class Model{
    /*
    use to collect data to/from GUI
     */

    public String vocab;
    int length;


    @FXML
    private Label meaning;

    @FXML
    private Label word;

    public  Model(String vocab){
        meaning.setText("555");
        this.vocab = vocab;
        length = vocab.length();
        setWord();
    }

    public void setWord(){
        String wo = "";
        for (int i = 0 ; i < length; i++){
            if (i < length-1){
                wo += "_ ";
            }
            else{
                wo += "_";
            }
        }
        word.setText(wo);
    }
}
