package FX.FXEncryptor;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    private Cipher cipher = new Cipher(100);
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
    @FXML TextArea plain;
    @FXML TextArea hidden;
    public void encrypt() {
        hidden.setText(Cipher.processMessage(Cipher.Type.ENCRYPT,plain.getText(),cipher.getKey()));
    }
    public void decrypt() {
        plain.setText(Cipher.processMessage(Cipher.Type.DECRYPT,hidden.getText(),cipher.getKey()));
    }
    public void getList1() {
        new Stage() {{
            setScene(new Scene(new TextArea(getAscii(plain.getText())) {{setWrapText(true);}}));
        }}.show();
    }
    public void getList2() {
        new Stage() {{
            setScene(new Scene(new TextArea(getAscii(hidden.getText())) {{setWrapText(true);}}));
        }}.show();
    }
    public static String getAscii(String s) {
        List<String> x = new LinkedList<>();
        for (char c: s.toCharArray())
            x.add(Integer.toString(c));
        return x.toString();
    }
    public void getKey() {
        new Stage() {{
            setScene(new Scene(new TextArea(cipher.getKey()) {{setWrapText(true);}}));
        }}.show();
    }
    public void setKey() {
        Stage s = new Stage();
        VBox area = new VBox();
        TextArea key = new TextArea();
        area.getChildren().add(key);
        Button ok = new Button("OK");
        ok.setOnAction(event -> {
            cipher.setKey(key.getText());
            s.close();
        });
        area.getChildren().add(ok);
        s.setScene(new Scene(area));
        s.show();
    }
}
