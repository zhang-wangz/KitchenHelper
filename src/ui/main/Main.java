package ui.main;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.jfoenix.validation.NumberValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import ui.add.addAppointment.AddAppointment;
//import ui.add.addCategory.AddCategory;
//import ui.add.addOperator.AddOperator;
//import ui.add.addOrder.AddOrder;
//import ui.add.addPet.AddPet;
//import ui.add.addProduct.AddProduct;
//import ui.add.addService.AddService;
//import ui.add.barcode.Barcode;
import model.BeanMyUser;
import model.BeanOperator;
import ui.add.addOperator.AddOperator;
import ui.add.addUser.AddUser;
import util.BaseException;
import util.KitchenSystemUtil;

import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Main implements Initializable{

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab adminTab;

    @FXML
    private Tab userTab;

    @FXML
    private Tab serviceTab;

    @FXML
    private Tab appointmentTab;

    @FXML
    private Tab categoryTab;

    @FXML
    private Tab productTab;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab petTab;

    @FXML
    private Text orderSata;

    @FXML
    private Text appointmentSata;

    @FXML
    private Text orderUser;

    @FXML
    private Text orderPrice;

    @FXML
    private Text orderTel;

    @FXML
    private Text orderStatus;

    @FXML
    private JFXComboBox<String> choiceType1;

    @FXML
    private JFXComboBox<String> choiceType2;

    @FXML
    private Text appUser;

    @FXML
    private Text appDate;

    @FXML
    private Text appPet;

    @FXML
    private Text appStatus;

    @FXML
    private JFXTextField keyword;

    @FXML
    private JFXTextField orderId;

    @FXML
    private TableView<BeanOperator> operatorTbl;

    @FXML
    private TableColumn<BeanOperator, Integer> OperatorIdCol;

    @FXML
    private TableColumn<BeanOperator, String> OperatornameCol;

    @FXML
    private TableColumn<BeanOperator, Integer> OPeratorlevCol;

    @FXML
    private TableView<BeanMyUser> userTbl;

    @FXML
    private TableColumn<BeanMyUser, Integer> UserIdCol;

    @FXML
    private TableColumn<BeanMyUser, String> UsernameCol;

    @FXML
    private TableColumn<BeanMyUser, Integer> UserTelCol;

    @FXML
    private TableColumn<BeanMyUser, String> UserEmailCol;

    @FXML
    private TableColumn<BeanMyUser, String> UserContactCol;

    @FXML
    private Text adminTotal;

    @FXML
    private Text userTotal;

    @FXML
    private Text PetTotal;

    @FXML
    private Text orderTotal;

    @FXML
    private Text appointmentTotal;

    @FXML
    private Text serviceTotal;

    @FXML
    private Text categoryTotal;

    @FXML
    private Text productTotal;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private VBox charContainer;

    private ObservableList<BeanOperator> operators = null;
    private ObservableList<BeanMyUser> users = null;




    private PieChart orderPie = null;
    private PieChart appointmentPie = null;
    private String choice1 = "";
    private String choice2 = "";


    /*
    *  Delete Operation
    *
    * */
    @FXML
    void deleteOperator(ActionEvent event) {
        BeanOperator operator = operatorTbl.getSelectionModel().getSelectedItem();
        if(operator == null){
            showDialog("请选择要操作的管理员");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try{
                KitchenSystemUtil.operatorController.delOperator(operator.getOpId());
                operators.remove(operator);
                showDialog("管理员"+operator.getOpName()+"已删除");
            }catch (BaseException e1){
                showDialog(e1.getMessage());
            }
        });
        showConfirmDialog("是否要删除管理员"+operator.getOpName()+" ?", Arrays.asList(btnCancel,btnOK));
    }

    @FXML
    void deleteUser (ActionEvent event){
        BeanMyUser user = userTbl.getSelectionModel().getSelectedItem();
        if(user == null){
            showDialog("请选择要操作的用户");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                KitchenSystemUtil.userController.delUser(user.getUserId());
            } catch (Exception exception1) {
                showDialog("该用户目前处于活跃状态,不可删除");
            }
            users.remove(user);
            showDialog("用户"+user.getUserName()+"已删除");
        });
       showConfirmDialog("是否要删除用户"+user.getUserName()+" ?",Arrays.asList(btnCancel, btnOK));

    }




    /*
    *  Edit operation
    * */

    @FXML
    void editOperator(ActionEvent event){
        BeanOperator beanOperator = operatorTbl.getSelectionModel().getSelectedItem();
        if(beanOperator == null){
            showDialog("请选择要操作的管理员");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOperator/addOperator.fxml"));
            Parent parent = loader.load();
            AddOperator addOperator = (AddOperator) loader.getController();
            addOperator.inflateUI(beanOperator);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑管理员");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editUser(ActionEvent event){
        BeanMyUser beanUser = userTbl.getSelectionModel().getSelectedItem();
        if(beanUser == null){
            showDialog("请选择要操作的用户");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addUser/addUser.fxml"));
            Parent parent = loader.load();
            AddUser addUser = (AddUser) loader.getController();
            addUser.inflateUI(beanUser);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑用户");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    /*
    * refresh operation
    * */
    @FXML
    void refreshOperator(ActionEvent event){
        operators.clear();
        operators = getOperator();
        operatorTbl.setItems(operators);
    }


    @FXML
    void refreshUser(ActionEvent event){
        users.clear();
        users = getUser();
        userTbl.setItems(users);
    }

    /*
    * Other
    * */
    @FXML
    void levelUpOperator(ActionEvent event){

    }

    @FXML
    void levelDownOperator(ActionEvent event){

    }


    @FXML
    void OrderOrAppointmentChoice(ActionEvent event){
        choice1 = choiceType1.getSelectionModel().getSelectedItem();
    }

    @FXML
    void otherChoice(ActionEvent event){
        choice2 = choiceType2.getSelectionModel().getSelectedItem();
    }

    private void showDialog(String message){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("已知晓");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialog.setOnDialogOpened((event -> button.requestFocus()));
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            dialog.close();
        });
        button.addEventHandler(KeyEvent.KEY_PRESSED, (Event e)->{
            rootAnchorPane.setEffect(null);
            dialog.close();
        });
        dialogLayout.setHeading(new Label(message));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

    private void showCancelDialog(String cate){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("已知晓");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            rootAnchorPane.setEffect(null);
            dialog.close();
        });

        button.addEventHandler(KeyEvent.KEY_PRESSED, (Event e)->{
            rootAnchorPane.setEffect(null);
            dialog.close();
        });
        dialog.setOnDialogOpened((event -> button.requestFocus()));
        dialogLayout.setHeading(new Label("取消"+cate+"操作"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

    private void showConfirmDialog(String message, List<JFXButton> buttons){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialogLayout.setHeading(new Label(message));
        buttons.forEach(jfxButton -> {jfxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> dialog.close());});
        dialog.setOnDialogOpened((event -> buttons.get(0).requestFocus()));
        dialogLayout.setActions(buttons);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }


    @FXML
    void refreshHomeTab(ActionEvent e){
        initChart();
        initStatics();
    }

//    @FXML
//    void selectOrderId(ActionEvent event){
//        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
//        orderDetails.clear();
//        if(order == null){
//            orderDetails.clear();
//            orderTbl.setItems(orderDetails);
//        }else {
//            orderSata.setText(order.getOrderState());
//            orderDetails = getOrderDetail(order.getOrderId());
//            orderTbl.setItems(orderDetails);
//        }
//    }
//
//    @FXML
//    void selectAppointmentId(ActionEvent event){
//        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
//        appointmentDetails.clear();
//        appointmentDetails = getAppointmentDetail(appointment.getAppId());
//        appointmentTbl.setItems(appointmentDetails);
//        appointmentSata.setText(appointment.getAppState());
//    }

    private void initCol() {
        OperatorIdCol.setCellValueFactory(new PropertyValueFactory<>("opId"));
        OperatornameCol.setCellValueFactory(new PropertyValueFactory<>("opName"));
        OPeratorlevCol.setCellValueFactory(new PropertyValueFactory<>("opLevel"));
        operatorTbl.setItems(operators);

        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        UserEmailCol.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        UserTelCol.setCellValueFactory(new PropertyValueFactory<>("userTel"));
        UserContactCol.setCellValueFactory(new PropertyValueFactory<>("userContact"));
        userTbl.setItems(users);


    }

    private ObservableList<BeanOperator> getOperator(){
        ObservableList<BeanOperator> operators = FXCollections.observableArrayList();
        List<BeanOperator> list = KitchenSystemUtil.operatorController.loadAll();
        for (BeanOperator e: list){
            operators.add(e);
        }
        return operators;
    }

    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = KitchenSystemUtil.userController.loadAll();
        for (BeanMyUser e: list){
            users.add(e);
        }
        return users;
    }

    private ObservableList<PieChart.Data> getOrderPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//        int count1 = KitchenSystemUtil.orderController.getOrderCount("已完成");
//        int count2 = KitchenSystemUtil.orderController.getOrderCount("已发货");
//        int count3 = KitchenSystemUtil.orderController.getOrderCount("订单创建完成");
         int count1 = 1;
         int count2 = 2;
         int count3 = 3;
        data.add(new PieChart.Data("已完成订单 ( " + String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已发货订单 ( " + String.valueOf(count2) +" )",count2));
        data.add(new PieChart.Data("未发货订单 ( " + String.valueOf(count3) +" )",count3));
        return data;
    }

    private ObservableList<PieChart.Data> getAppointmentPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//        int count1 = KitchenSystemUtil.appointmentController.getAppointmentCount("预约创建完成");
//        int count2 = KitchenSystemUtil.appointmentController.getAppointmentCount("已完成");
        int count1 = 1;
        int count2 = 2;
        data.add(new PieChart.Data("未完成预约 ( "+String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已完成预约 ( "+String.valueOf(count2) +" )",count2));
        return data;
    }

    private ObservableList<String> getChoice1(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("订单","预约");
        return choice;
    }

    private ObservableList<String> getChoice2(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("宠物","产品","类别","用户","服务","管理员");
        return choice;
    }

    private void loadData(){
        this.operators = getOperator();
        this.users = getUser();

        choiceType1.setItems(getChoice1());
        choiceType2.setItems(getChoice2());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NumberValidator numberValidator = new NumberValidator();
        try{
            Image image = new Image(new FileInputStream("src/ui/icons/error-sign.png"));
            numberValidator.setIcon(new ImageView(image));

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        numberValidator.setMessage("请输入纯数字");

        orderId.getValidators().add(numberValidator);

        orderId.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    orderId.validate();
                }
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if(newValue.equals(homeTab)){
                    initChart();
                    initStatics();
                    charContainer.getChildren().clear();
                    charContainer.getChildren().add(orderPie);
                    charContainer.getChildren().add(appointmentPie);
                }else if(newValue.equals(appointmentTab)){
//                    refreshAppointment(new ActionEvent());
//                }else if(newValue.equals(adminTab)){
//                    refreshOperator(new ActionEvent());
//
//                }else if(newValue.equals(categoryTab)){
//                    refreshCategory(new ActionEvent());
//
//                }else if(newValue.equals(orderTab)){
//                    refreshOrder(new ActionEvent());
//
//                }else if(newValue.equals(petTab)){
//                    refreshPet(new ActionEvent());
//
//                }else if(newValue.equals(productTab)){
//                    refreshProduct(new ActionEvent());
//
//                }else if (newValue.equals(serviceTab)){
//                    refreshService(new ActionEvent());
//
//                }else if(newValue.equals(userTab)) {
//                    refreshService(new ActionEvent());

                }
            }
        });

        loadData();

        initCol();

        initDrawer();

        initChart();

        initStatics();

        charContainer.getChildren().add(orderPie);
        charContainer.getChildren().add(appointmentPie);
    }

    private void initStatics() {
        adminTotal.setText("管理员总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanOperator")));
        userTotal.setText("用户总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanMyUser")));
//        PetTotal.setText("宠物总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanPet")));
//        orderTotal.setText("订单总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanMyOrder")));
//        appointmentTotal.setText("预约总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanAppointment")));
//        serviceTotal.setText("服务总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanService")));
//        productTotal.setText("产品总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanProduct")));
//        categoryTotal.setText("分类总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanCategory")));
    }

    private void initChart() {
       orderPie = new PieChart(getOrderPieData());
       orderPie.autosize();
       appointmentPie = new PieChart(getAppointmentPieData());
       appointmentPie.autosize();
    }

    private void initDrawer() {
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/ui/main/toolbar/toolbar.fxml"));
            drawer.setSidePane(toolbar);
            drawer.setDefaultDrawerSize(150);
            HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
            task.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
                task.setRate(task.getRate() * -1);
                task.play();
                if(drawer.isClosed()){
                    drawer.open();
                    drawer.setMinWidth(175);
                }else{
                    drawer.close();
                    drawer.setMinWidth(0);
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
