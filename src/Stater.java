import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Stater extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/login/login.fxml"));
        Scene scene = new Scene(root,350,220);
        primaryStage.getIcons().add(new Image("/ui/icons/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); //设置不可随意改变大小
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->System.exit(0));
    }


    public static void main(String[] args) {
//        PetManageSystemUtil.operatorController.findOperatorById(1);
        launch(args);
    }
}