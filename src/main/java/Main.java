import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        Clipboard clipboard = new Clipboard();
        OCR ocr = new OCR();

        Button button = new Button("convert");
        ImageView ImageView = new ImageView();
        Text text = new Text();

        VBox VBox = new VBox();
        VBox.getChildren().addAll(button,ImageView,text);

        button.setOnAction(event -> {
            text.setText(ocr.performOCR(clipboard.getPictureFromClipboard()));
            Image image = SwingFXUtils.toFXImage(ocr.processedImage, null);
            ImageView.setImage(image);
            clipboard.writeToClipboard(ocr.performOCR(clipboard.getPictureFromClipboard()));
        });

        primaryStage.setScene(new Scene(VBox, 55, 55));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
