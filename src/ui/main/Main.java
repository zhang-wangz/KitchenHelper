package ui.main;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.jfoenix.validation.NumberValidator;
import enums.BuyFoodStatusEnum;
import enums.FoodOrderStatusEnum;
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
import model.*;
import ui.add.addBuyFood.AddBuyFood;
import ui.add.addFoodInfo.AddFoodInfo;
import ui.add.addFoodOrder.AddFoodOrder;
import ui.add.addFoodType.AddFoodType;
import ui.add.addOperator.AddOperator;
import ui.add.addUser.AddUser;
import util.BaseException;
import util.EnumUtils;
import util.KitchenSystemUtil;

import java.io.FileInputStream;
import java.net.URL;
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
    private Tab foodInfoTab;

    @FXML
    private Tab foodTypeTab;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab orderBuyTab;

    //订单相关
    @FXML
    private Text orderStatusOfOrder;


    @FXML
    private Text orderStatusOfBuy;


    @FXML
    private Text appointmentSata;


    //检索moudle
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
    private TableView<BeanFoodInfo> foodInfoTableView;

    @FXML
    private TableColumn<BeanFoodInfo, Integer> foodinfoidCol;

    @FXML
    private TableColumn<BeanFoodInfo, Integer> foodinfonameCol;

    @FXML
    private TableColumn<BeanFoodInfo, Integer> foodTypeNameOffoodInfoCol;


    @FXML
    private TableColumn<BeanFoodInfo, Integer> foodinfopriceCol;

    @FXML
    private TableColumn<BeanFoodInfo, Integer> foodinfonumCol;

    @FXML
    private  TableColumn<BeanFoodInfo,String> foodinfodesCol;


    @FXML
    private TableView<BeanFoodType> foodTypeTbl;

    @FXML
    private TableColumn<BeanFoodType, String> foodTypeIdCol;

    @FXML
    private TableColumn<BeanFoodType, String> foodTypeNameCol;

    @FXML
    private TableColumn<BeanFoodType, String> foodTypeDesCol;

//订单相关
    @FXML
    private TableView<BeanOrderDetail> orderTbl;

    @FXML
    private JFXComboBox<BeanFoodOrder> orderBox;

    @FXML
    private TableView<BeanBuyFood> buyOrderTbl;

    @FXML
    private JFXComboBox<BeanBuyFood> buyOrderBox;

//订单相关
    @FXML
    private TableColumn<BeanOrderDetail, BeanFoodInfo> orderProductCol;

    @FXML
    private TableColumn<BeanOrderDetail, Integer> orderNumCol;

    @FXML
    private TableColumn<BeanOrderDetail,Integer> orderPriceCol;

    @FXML
    private TableColumn<BeanOrderDetail, BeanFoodInfo> orderProductBuyCol;

    @FXML
    private TableColumn<BeanOrderDetail, Integer> orderNumBuyCol;

//    @FXML
//    private TableColumn<BeanOrderDetail,Integer> orderPriceBuyCol;



//
//    @FXML
//    private JFXComboBox<BeanAppointment> appointmentBox;
//
//    @FXML
//    private TableView<BeanAppointmentDetail> appointmentTbl;
//
//    @FXML
//    private TableColumn<BeanAppointmentDetail, BeanService> appointmentServiceCol;
//
//    @FXML
//    private TableColumn<BeanAppointmentDetail, BeanPet> appointmentPetCol;
//
//    @FXML
//    private TableColumn<BeanAppointmentDetail, LocalDate> appointmentDateCol;
//
//    @FXML
//    private TableView<BeanService> serviceTbl;
//
//    @FXML
//    private TableColumn<BeanService, Integer> ServiceIdCol;
//
//    @FXML
//    private TableColumn<BeanService, String> ServiceNameCol;
//
//    @FXML
//    private TableColumn<BeanService, Integer> ServicePriceCol;
//
//    @FXML
//    private TableView<BeanProduct> productTbl;
//
//    @FXML
//    private TableColumn<BeanProduct, Integer> ProductIdCol;
//
//    @FXML
//    private TableColumn<BeanProduct, String> ProductNameCol;
//
//    @FXML
//    private TableColumn<BeanProduct, String> ProductBrandCol;
//
//    @FXML
//    private TableColumn<BeanProduct, Integer> ProductPriceCol;
//
//    @FXML
//    private TableColumn<BeanProduct, String> ProductBarcodeCol;
//
//    @FXML
//    private TableColumn<BeanProduct, BeanCategory> ProductCategory;
//


    @FXML
    private Text adminTotal;

    @FXML
    private Text userTotal;

    @FXML
    private Text foodInfoTotal;

    @FXML
    private Text foodTypeTotal;

    //订单相关
    @FXML
    private Text orderTotal;

    @FXML
    private Text orderBuyTotal;



    @FXML
    private Text appointmentTotal;

    @FXML
    private Text serviceTotal;

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
    private ObservableList<BeanFoodInfo> foodInfos = null;
    private ObservableList<BeanFoodType> foodTypes = null;
    private ObservableList<BeanOrderDetail> orderDetails = null;
    private ObservableList<BeanBuyFood> buyFoods = null;


//    private ObservableList<BeanService> services = null;
//    private ObservableList<BeanProduct> products = null;


//    private ObservableList<BeanAppointmentDetail> appointmentDetails = null;
    private PieChart orderPie = null;
    private PieChart appointmentPie = null;
    private String choice1 = "";
    private String choice2 = "";

//    @FXML
//    void showProductBarcode(ActionEvent event) {
//        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
//        if(product == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/barcode/barcode.fxml"));
//            Parent parent = loader.load();
//            Barcode barcode = loader.getController();
//            barcode.inflateUI(product.getProdBarcode());
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("条形码");
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
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

    @FXML
    void deleteFoodInfo(ActionEvent event){
        BeanFoodInfo foodInfo = foodInfoTableView.getSelectionModel().getSelectedItem();
        if(foodInfo == null){
            showDialog("请选择要操作的食材信息");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                KitchenSystemUtil.foodInfoController.delFoodInfo(foodInfo.getFoodId());
            } catch (Exception exception1) {
                showDialog("该食材目前处于活跃状态,不可删除");
                return;
            }
            foodInfos.remove(foodInfo);
            showDialog("食材信息"+foodInfo.getFoodName()+"已删除");
        });
        showConfirmDialog("是否要删除食材信息"+foodInfo.getFoodName()+" ?", Arrays.asList(btnCancel, btnOK));
    }




    @FXML
    void deleteFoodType(ActionEvent event){
        BeanFoodType foodType = foodTypeTbl.getSelectionModel().getSelectedItem();
        if(foodType == null){
            showDialog("请选择要删除的分类");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                KitchenSystemUtil.foodTypeController.delCategory(foodType.getFoodTypeId());
            } catch (Exception exception) {
                showDialog("该分类目前处于活跃状态,不可删除");
                return;
            }
            foodTypes.remove(foodType);
            showDialog("分类"+foodType.getFoodTypeName()+"已删除");
        });
        showConfirmDialog("是否要删除分类"+foodType.getFoodTypeName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteOrderDetail(ActionEvent event){
        BeanOrderDetail orderDetail = orderTbl.getSelectionModel().getSelectedItem();

        if(orderDetail == null){
            showDialog("请选择要删除的订单货物");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            Integer status = null;
            try {
                status = KitchenSystemUtil.foodOrderController.findOrderById(orderDetail.getOrderId()).getOrderStatus();
                showDialog("订单状态异常，请稍后再试");
            } catch (BaseException ex) {
                ex.printStackTrace();
            }
            if(status != 0){
                showDialog("订单货物" + orderDetail.getOrderId() + "未处于下单状态，无法删除");
            }else {
                KitchenSystemUtil.foodOrderController.delOrderDetail(orderDetail);
                showDialog("订单货物" + orderDetail.getOrderId() + "已删除");
            }

        });
        showConfirmDialog("是否要删除订单货物"+orderDetail.getFoodId()+" ?", Arrays.asList(btnCancel, btnOK));
    }




    @FXML
    void deleteBuyOrderDetail(ActionEvent event){
        BeanBuyFood buyFood = buyOrderTbl.getSelectionModel().getSelectedItem();

        if(buyFood == null){
            showDialog("请选择要删除的采购单货物");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
                Integer status = null;
                KitchenSystemUtil.buyFoodController.delOrderDetail(buyFood);
                showDialog("订单货物" + buyFood.getBuyOrderId() + "已删除");


        });
        showConfirmDialog("是否要删除订单货物"+buyFood.getFoodId()+" ?", Arrays.asList(btnCancel, btnOK));
    }



    @FXML
    void deleteOrder(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要删除的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            int num = KitchenSystemUtil.foodOrderController.loadDetailByOrderId(order.getOrderId()).size();
            if(num > 0){
                showDialog("订单"+order.getOrderId()+"处于活跃状态，不可删除");
            }else {
                KitchenSystemUtil.foodOrderController.delOrder(order.getOrderId());
                showDialog("订单" + order.getOrderId() + "已删除");
            }
        });
        showConfirmDialog("是否要删除订单"+order.getOrderId()+" ?", Arrays.asList(btnCancel, btnOK));
    }




//    @FXML
//    void deleteProduct(ActionEvent event){
//        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
//        if(product == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("去意已决");
//        JFXButton btnCancel = new JFXButton("再想想");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("删除");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            try {
//                KitchenSystemUtil.productController.delProduct(product.getProdId());
//            } catch (Exception exception1) {
//                showDialog("该产品目前处于活跃状态,不可删除");
//                return;
//            }
//            products.remove(product);
//            showDialog("产品"+product.getProdName()+"已删除");
//        });
//        showConfirmDialog("是否要删除产品"+product.getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
//    }


//    @FXML
//    void deleteService(ActionEvent event){
//        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
//        if(service == null){
//            showDialog("请选择要操作的服务");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("去意已决");
//        JFXButton btnCancel = new JFXButton("再想想");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("删除");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            try {
//                KitchenSystemUtil.serviceController.delService(service.getServId());
//            } catch (Exception exception) {
//                showDialog("该服务目前处于活跃状态,不可删除");
//                return;
//            }
//            services.remove(service);
//            showDialog("服务"+service.getServName()+"已删除");
//        });
//        showConfirmDialog("是否要删除服务"+service.getServName()+" ?", Arrays.asList(btnCancel, btnOK));
//    }
//
//    @FXML
//    void deleteOrderProduct(ActionEvent event){
//        BeanOrderDetail detail = orderTbl.getSelectionModel().getSelectedItem();
//        if(detail == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("去意已决");
//        JFXButton btnCancel = new JFXButton("再想想");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("删除");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            try {
//                KitchenSystemUtil.foodOrderController.delOrderDetail(detail);
//            } catch (Exception exception) {
//                showDialog("该产品目前处于活跃状态,不可删除");
//                return;
//            }
//            orderDetails.remove(detail);
//
//            List<BeanAppointment> list = KitchenSystemUtil.appointmentController.loadAll();
//            for(BeanAppointment a1 : list){
//                List<BeanAppointmentDetail> details = KitchenSystemUtil.appointmentController.loadDetailByAppointmentId(a1.getAppId());
//                if(details.size() == 0)
//                    KitchenSystemUtil.appointmentController.delAppointment(a1.getAppId());
//            }
//            showDialog("产品"+detail.getProduct().getProdName()+"已删除");
//        });
//
//        showConfirmDialog("是否要删除产品"+detail.getProduct().getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
//    }





//    @FXML
//    void deleteAppointmentService(ActionEvent event){
//        BeanAppointmentDetail detail = appointmentTbl.getSelectionModel().getSelectedItem();
//        if(detail == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("去意已决");
//        JFXButton btnCancel = new JFXButton("再想想");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("删除");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            try {
//                KitchenSystemUtil.appointmentController.delAppointmentDetail(detail);
//            } catch (Exception exception) {
//                showDialog("该预约目前处于活跃状态,不可删除");
//                return;
//            }
//            appointmentDetails.remove(detail);
//
//            List<BeanAppointment> list = KitchenSystemUtil.appointmentController.loadAll();
//            for(BeanAppointment a1 : list){
//                List<BeanAppointmentDetail> details = KitchenSystemUtil.appointmentController.loadDetailByAppointmentId(a1.getAppId());
//                if(details.size() == 0) {
//                    KitchenSystemUtil.appointmentController.delAppointment(a1.getAppId());
//                }
//            }
//
//            showDialog("预约"+detail.getService().getServName()+"已删除");
//        });
//        showConfirmDialog("是否要删除预约"+detail.getService().getServName()+" ?", Arrays.asList(btnCancel, btnOK));
//    }

//    @FXML
//    void deleteAppointment(ActionEvent event){
//        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
//        if(appointment == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("去意已决");
//        JFXButton btnCancel = new JFXButton("再想想");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("删除");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            KitchenSystemUtil.appointmentController.delAppointment(appointment.getAppId());
//            showDialog("预约"+appointment.getAppId()+"已删除");
//        });
//        showConfirmDialog("是否要删除预约"+appointment.getAppId()+" ?", Arrays.asList(btnCancel, btnOK));
//    }

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
    void editFoodInfo(ActionEvent event){
        BeanFoodInfo beanFoodInfo = foodInfoTableView.getSelectionModel().getSelectedItem();
        if(beanFoodInfo == null){
            showDialog("请选择要操作的食材");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addFoodInfo/addFoodInfo.fxml"));
            Parent parent = loader.load();
            AddFoodInfo addFoodInfo = (AddFoodInfo) loader.getController();
            addFoodInfo.inflateUI(beanFoodInfo);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑食材信息");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

       // refreshFoodInfo(new ActionEvent());
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

    @FXML
    void editFoodType(ActionEvent event){
        BeanFoodType foodType = foodTypeTbl.getSelectionModel().getSelectedItem();
        if(foodType == null){
            showDialog("请选择要操作的分类");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addFoodType/addFoodType.fxml"));
            Parent parent = loader.load();
            AddFoodType addCategory = (AddFoodType) loader.getController();
            addCategory.inflateUI(foodType);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑分类");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

        @FXML
    void editOrder(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOrder/addOrder.fxml"));
            Parent parent = loader.load();
            AddFoodOrder addOrder = (AddFoodOrder) loader.getController();
            addOrder.inflateUI(order);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("编辑订单");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editOrderBuy(ActionEvent event){
        BeanBuyFood buyFood = (buyOrderBox.getSelectionModel().getSelectedItem());
        if(buyFood == null){
            showDialog("请选择要操作的采购单");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addBuyFood/addBuyFood.fxml"));
            Parent parent = loader.load();
            AddBuyFood addBuyFood = (AddBuyFood)loader.getController();
            addBuyFood.inflateUI(buyFood);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("编辑采购单");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



//    @FXML
//    void editService(ActionEvent event){
//        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
//        if(service == null){
//            showDialog("请选择要操作的服务");
//            return;
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addService/addService.fxml"));
//            Parent parent = loader.load();
//            AddService addService = (AddService) loader.getController();
//            addService.inflateUI(service);
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.getIcons().add(new Image("/ui/icons/icon.png"));
//            stage.setTitle("编辑服务");
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    @FXML
//    void editProduct(ActionEvent event){
//        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
//        if(product == null){
//            showDialog("请选择要操作的产品");
//            return;
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addProduct/addProduct.fxml"));
//            Parent parent = loader.load();
//            AddProduct addProduct = (AddProduct) loader.getController();
//            addProduct.inflateUI(product);
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.getIcons().add(new Image("/ui/icons/icon.png"));
//            stage.setTitle("编辑产品");
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//

//
//    @FXML
//    void editAppointmentService(ActionEvent event){
//        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
//        if(appointment == null){
//            showDialog("请选择要操作的预约");
//            return;
//        }
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addAppointment/addAppointment.fxml"));
//            Parent parent = loader.load();
//            AddAppointment addAppointment = (AddAppointment) loader.getController();
//            addAppointment.inflateUI(appointment);
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("编辑预约");
//            stage.setScene(new Scene(parent));
//            stage.show();
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }

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
    void refreshFoodInfo(ActionEvent event){
        foodInfos.clear();
        foodInfos = getFoodInfo();
        foodInfoTableView.setItems(foodInfos);
    }

    @FXML
    void refreshUser(ActionEvent event){
        users.clear();
        users = getUser();
        userTbl.setItems(users);
    }


    @FXML
    void refreshFoodType(ActionEvent event){
        foodTypes.clear();
        foodTypes = getFoodTypes();
        foodTypeTbl.setItems(foodTypes);
    }



//    @FXML
//    void refreshProduct(ActionEvent event){
//        products.clear();
//        products = getProduct();
//        productTbl.setItems(products);
//    }

//
    @FXML
    void refreshOrder(ActionEvent event){
        orderBox.getItems().clear();
        orderBox.setItems(getOrder());
        orderDetails.clear();
        orderDetails = getOrderDetail();
        orderTbl.setItems(orderDetails);
    }

    @FXML
    void refreshOrderBuy(ActionEvent event){
        buyOrderBox.getItems().clear();
        buyOrderBox.setItems(getOrderBuy()); //存储id
        buyFoods.clear();
        buyFoods = getOrderBuy();
        buyOrderTbl.setItems(buyFoods);//存储全部
    }


//
//    @FXML
//    void refreshService(ActionEvent event){
//        appointmentBox.getItems().clear();
//        appointmentBox.setItems(getAppointment());
//        services.clear();
//        services = getService();
//        serviceTbl.setItems(services);
//    }

//    @FXML
//    void refreshAppointment(ActionEvent event){
//        appointmentBox.setItems(getAppointment());
//        appointmentDetails.clear();
//        appointmentDetails = getAppointmentDetail();
//        appointmentTbl.setItems(appointmentDetails);
//    }

    /*
     * Other
     * */
    @FXML
    void levelUpOperator(ActionEvent event){

    }

    @FXML
    void levelDownOperator(ActionEvent event){}

    @FXML
    void sendOrder(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("配送");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderStatus(1);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"开始配送");
        });
        showConfirmDialog("是否已将订单"+order.getOrderId()+"货物全部发出 ?", Arrays.asList(btnCancel, btnOK));
    }


    @FXML
    void sendOrderBuy(ActionEvent event){
        BeanBuyFood order = buyOrderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("发货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setStatus(1);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getBuyOrderId()+"开始发货");
        });
        showConfirmDialog("是否已将订单"+order.getBuyOrderId()+"货物全部发出 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishOrder(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("确认送达");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderStatus(2);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已送达");
        });
        showConfirmDialog("订单"+order.getOrderId()+"货物全部确认收货 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishOrderBuy(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的采购单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("确认收货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderStatus(2);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已入库");
        });
        showConfirmDialog("订单"+order.getOrderId()+"货物全部确认收货 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void changeOrderToCancel(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("确认退货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderStatus(3);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已退货");
        });
        showConfirmDialog("订单"+order.getOrderId()+"货物全部确认退货 ?", Arrays.asList(btnCancel, btnOK));
    }


    @FXML
    void changeOrderBuyToCancel(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的采购单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("确认退货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderStatus(3);
            KitchenSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已退货");
        });
        showConfirmDialog("订单"+order.getOrderId()+"货物全部确认退货 ?", Arrays.asList(btnCancel, btnOK));
    }


//    @FXML
//    void finishAppointment(ActionEvent event){
//        BeanAppointment order = appointmentBox.getSelectionModel().getSelectedItem();
//        if(order == null){
//            showDialog("请选择要操作的预约");
//            return;
//        }
//        JFXButton btnOK = new JFXButton("是");
//        JFXButton btnCancel = new JFXButton("否");
//        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            showCancelDialog("预约完成");
//        });
//        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
//            order.setAppState("已完成");
//            KitchenSystemUtil.update(order);
//            showDialog("预约"+order.getAppId()+"已完成");
//        });
//        showConfirmDialog("预约"+order.getAppId()+"服务全部完成 ?", Arrays.asList(btnCancel, btnOK));
//    }

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
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
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
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
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
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
        dialogLayout.setHeading(new Label(message));
        buttons.forEach(jfxButton -> {jfxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> dialog.close());});
        dialog.setOnDialogOpened((event -> buttons.get(0).requestFocus()));
        dialogLayout.setActions(buttons);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

//    @FXML
//    void loadOrderOrAppointmentInfo(ActionEvent event) {
//        if(choice1.isEmpty()){
//            showDialog("没有选择要查询数据类型");
//            return;
//        }
//
//        try{
//            int id = Integer.parseInt(orderId.getText());
//            if(choice1.equals("预约")){
//                BeanAppointment appointment = null;
//                appointment = KitchenSystemUtil.appointmentController.findAppointmentById(id);
//                orderUser.setText("用户名: "+appointment.getUser().getUserName());
//                orderPrice.setText("价格: "+appointment.getAppState());
//            }else{
//                BeanMyOrder order = null;
//                order = KitchenSystemUtil.orderController.findOrderById(id);
//                orderUser.setText("用户名: "+order.getOrderUser().getUserName());
//                orderPrice.setText("价格: "+order.getOrderPrice().toString());
//                orderStatus.setText("状态: "+order.getOrderState());
//                orderTel.setText("联系电话: "+order.getOrderUser().getUserTel().toString());
//            }
//        }catch (Exception exception){
//            showDialog("sorry! nothing found.");
//        }
//    }
//
//    @FXML
//    void loadOtherInfo(ActionEvent event){
//        if(choice2.isEmpty()){
//            showDialog("没有选择要查询的数据类型");
//            return;
//        }
//        try{
//            if(choice2.equals("管理员")){
//                List<BeanOperator> list = KitchenSystemUtil.operatorController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanOperator b: list){
//                        ones += (b.getOpName()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanOperator b = list.get(0);
//                appUser.setText("用户名: "+b.getOpName());
//                appDate.setText("");
//                appPet.setText("等级: "+b.getOpLevel().toString());
//                appStatus.setText("");
//            }else if(choice2 .equals("类别")){
//                List<BeanCategory> list = KitchenSystemUtil.categoryController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanCategory b: list){
//                        ones += (b.getCateName()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanCategory b = list.get(0);
//                appUser.setText("名称: "+b.getCateName());
//                appDate.setText("");
//                appPet.setText("详情: "+b.getCateDetail());
//                appStatus.setText("");
//            }else if(choice2.equals("宠物")){
//                List<BeanPet> list = KitchenSystemUtil.petController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanPet b: list){
//                        ones += (b.getPetNikename()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanPet b = list.get(0);
//                appUser.setText("昵称: "+b.getPetNikename());
//                appDate.setText("主人名称: "+b.getUser().getUserName());
//                appPet.setText("宠物别名: "+b.getPetAlias());
//                appStatus.setText("");
//            }else if(choice2.equals("产品")){
//                List<BeanProduct> list = KitchenSystemUtil.productController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanProduct b: list){
//                        ones += (b.getProdName()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanProduct b = list.get(0);
//                appUser.setText("类别: "+b.getProdCategory().getCateName());
//                appDate.setText("价格: "+b.getProdPrice().toString());
//                appPet.setText("名称: "+b.getProdName());
//                appStatus.setText("品牌: "+b.getProdBrand());
//            }else if(choice2.equals("服务")){
//                List<BeanService> list = KitchenSystemUtil.serviceController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanService b: list){
//                        ones += (b.getServName()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanService b = list.get(0);
//                appUser.setText("名称: "+b.getServName());
//                appDate.setText("类别: "+b.getCategory().getCateName());
//                appPet.setText("价格: "+b.getServPrice().toString());
//                appStatus.setText("");
//            }else if(choice2.equals("用户")){
//                List<BeanMyUser> list = KitchenSystemUtil.userController.search(keyword.getText());
//                if(list.size()==0){
//                    showDialog("啥都么找到!");
//                    return;
//                }
//                if(list.size()>1){
//                    String ones = "";
//                    for (BeanMyUser b: list){
//                        ones += (b.getUserName()+", ");
//                    }
//                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
//                }
//                BeanMyUser b = list.get(0);
//                appUser.setText("用户名: "+b.getUserName());
//                appDate.setText("联系方式: "+b.getUserTel().toString());
//                appPet.setText("电子邮件: "+b.getUserEmail());
//                appStatus.setText("其他联系方式: "+b.getUserContact());
//            }
//        }catch (Exception e){
//            showDialog("Oops sth bad occur!");
//            e.printStackTrace();
//        }
//    }

    @FXML
    void refreshHomeTab(ActionEvent e){
        initChart();
        initStatics();
    }


    @FXML
    void selectOrderId(ActionEvent event){
        BeanFoodOrder order = orderBox.getSelectionModel().getSelectedItem();
        orderDetails.clear();
        if(order == null){
            orderDetails.clear();
            orderTbl.setItems(orderDetails);
        }else {
            orderStatusOfOrder.setText(EnumUtils.getByCode(order.getOrderStatus(),FoodOrderStatusEnum.class).getMsg());
            orderDetails = getOrderDetail(order.getOrderId());
            orderTbl.setItems(orderDetails);
        }
    }

    @FXML
    void selectOrderBuyId(ActionEvent event) throws BaseException {
        BeanBuyFood order = buyOrderBox.getSelectionModel().getSelectedItem();
        buyFoods.clear();
        if(order == null){
            buyFoods.clear();
            buyOrderBox.setItems(buyFoods);
        }else {
            orderStatusOfOrder.setText(EnumUtils.getByCode(order.getStatus(), BuyFoodStatusEnum.class).getMsg());
            buyFoods = getOrderBuyDetail(order.getBuyOrderId());
            orderTbl.setItems(orderDetails);
        }
    }



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

        foodinfoidCol.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        foodinfonameCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodTypeNameOffoodInfoCol.setCellValueFactory(new PropertyValueFactory<>("foodTypeNameOfFoodInfo"));
        foodinfonumCol.setCellValueFactory(new PropertyValueFactory<>("foodNum"));
        foodinfopriceCol.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
        foodinfodesCol.setCellValueFactory(new PropertyValueFactory<>("foodDes"));
        foodInfoTableView.setItems(foodInfos);

//        ServiceIdCol.setCellValueFactory(new PropertyValueFactory<>("servId"));
//        ServiceNameCol.setCellValueFactory(new PropertyValueFactory<>("servName"));
//        ServicePriceCol.setCellValueFactory(new PropertyValueFactory<>("servPrice"));
//        serviceTbl.setItems(services);
//
//        ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("prodId"));
//        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("prodName"));
//        ProductBrandCol.setCellValueFactory(new PropertyValueFactory<>("prodBrand"));
//        ProductBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("prodBarcode"));
//        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("prodPrice"));
//        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("prodCategory"));
//        productTbl.setItems(products);
//
        foodTypeIdCol.setCellValueFactory(new PropertyValueFactory<>("foodTypeId"));
        foodTypeDesCol.setCellValueFactory(new PropertyValueFactory<>("foodTypeDes"));
        foodTypeNameCol.setCellValueFactory(new PropertyValueFactory<>("foodTypeName"));
        foodTypeTbl.setItems(foodTypes);

        //orderTbl显示的是订单详情
        orderProductCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        orderNumCol.setCellValueFactory(new PropertyValueFactory<>("num"));
        orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderTbl.setItems(orderDetails);


        orderProductBuyCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        orderNumBuyCol.setCellValueFactory(new PropertyValueFactory<>("num"));
//        orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyOrderTbl.setItems(buyFoods);

//
//        appointmentServiceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
//        appointmentDateCol.setCellValueFactory(new PropertyValueFactory<>("app_date"));
//        appointmentPetCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
//        appointmentTbl.setItems(appointmentDetails);
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

    private ObservableList<BeanFoodInfo> getFoodInfo(){
        ObservableList<BeanFoodInfo> foodInfos = FXCollections.observableArrayList();
        List<BeanFoodInfo> list = KitchenSystemUtil.foodInfoController.loadAll();
        for (BeanFoodInfo e: list){
            foodInfos.add(e);
        }
        return foodInfos;
    }



    //   分类
    private ObservableList<BeanFoodType> getFoodTypes(){
        ObservableList<BeanFoodType> foodTypes = FXCollections.observableArrayList();
        List<BeanFoodType> list = KitchenSystemUtil.foodTypeController.loadAll();
        for (BeanFoodType e: list){
            foodTypes.add(e);
        }
        return foodTypes;
    }



//    private ObservableList<BeanService> getService(){
//        ObservableList<BeanService> services = FXCollections.observableArrayList();
//        List<BeanService> list = KitchenSystemUtil.serviceController.loadAll();
//        for (BeanService e: list){
//            services.add(e);
//        }
//        return services;
//    }

//    private ObservableList<BeanProduct> getProduct(){
//        ObservableList<BeanProduct> products = FXCollections.observableArrayList();
//        List<BeanProduct> list = KitchenSystemUtil.productController.loadAll();
//        for (BeanProduct e: list){
//            products.add(e);
//        }
//        return products;
//    }


    private ObservableList<BeanOrderDetail> getOrderDetail(){
        ObservableList<BeanOrderDetail> details = FXCollections.observableArrayList();
        List<BeanOrderDetail> list = KitchenSystemUtil.foodOrderController.loadAllDetails();
        for (BeanOrderDetail e: list){
            details.add(e);
        }
        return details;
    }


    private ObservableList<String> getOrderBuy(){
        ObservableList<String> details = FXCollections.observableArrayList();
        List<String> list = KitchenSystemUtil.buyFoodController.loadAllOnlyOne();
        for (String e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanFoodOrder> getOrder(){
        ObservableList<BeanFoodOrder> details = FXCollections.observableArrayList();
        List<BeanFoodOrder> list = KitchenSystemUtil.foodOrderController.loadAll();
        for (BeanFoodOrder e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanOrderDetail> getOrderDetail(String orderId){
        ObservableList<BeanOrderDetail> details = FXCollections.observableArrayList();
        List<BeanOrderDetail> list = KitchenSystemUtil.foodOrderController.loadDetailByOrderId(orderId);
        for (BeanOrderDetail e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanBuyFood> getOrderBuyDetail(String orderId) throws BaseException {
        ObservableList<BeanBuyFood> details = FXCollections.observableArrayList();
        List<BeanBuyFood> list = KitchenSystemUtil.buyFoodController.loadDetailByOrderId(orderId);
        for (BeanBuyFood e: list){
            details.add(e);
        }
        return details;
    }


//    private ObservableList<BeanAppointmentDetail> getAppointmentDetail(){
//        ObservableList<BeanAppointmentDetail> details = FXCollections.observableArrayList();
//        List<BeanAppointmentDetail> list = KitchenSystemUtil.appointmentController.loadAllDetails();
//        for (BeanAppointmentDetail e: list){
//            details.add(e);
//        }
//        return details;
//    }

//    private ObservableList<BeanAppointmentDetail> getAppointmentDetail(int appId){
//        ObservableList<BeanAppointmentDetail> details = FXCollections.observableArrayList();
//        List<BeanAppointmentDetail> list = KitchenSystemUtil.appointmentController.loadDetailByAppointmentId(appId);
//        details.addAll(list);
//        return details;
//    }
//
//    private ObservableList<BeanAppointment> getAppointment(){
//        ObservableList<BeanAppointment> appointments = FXCollections.observableArrayList();
//        List<BeanAppointment> list = KitchenSystemUtil.appointmentController.loadAll();
//        appointments.addAll(list);
//        return appointments;
//    }

    private ObservableList<PieChart.Data> getOrderPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        int count1 = KitchenSystemUtil.foodOrderController.getOrderCount(2);
        int count2 = KitchenSystemUtil.foodOrderController.getOrderCount(1);
        int count3 = KitchenSystemUtil.foodOrderController.getOrderCount(0);
        int count4 = KitchenSystemUtil.foodOrderController.getOrderCount(4);

        data.add(new PieChart.Data("已完成订单 ( " + String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已配送订单 ( " + String.valueOf(count2) +" )",count2));
        data.add(new PieChart.Data("未配送订单 ( " + String.valueOf(count3) +" )",count3));
        data.add(new PieChart.Data("已退货订单 ( " + String.valueOf(count3) +" )",count4));
        return data;
    }

    private ObservableList<PieChart.Data> getAppointmentPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//        int count1 = KitchenSystemUtil.appointmentController.getAppointmentCount("预约创建完成");
//        int count2 = KitchenSystemUtil.appointmentController.getAppointmentCount("已完成");
        int count1 = 1;
        int count2 = 2;
        data.add(new PieChart.Data("未完成采购 ( "+String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已完成采购 ( "+String.valueOf(count2) +" )",count2));
        return data;
    }

    private ObservableList<String> getChoice1(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("订单","预约");
        return choice;
    }

    private ObservableList<String> getChoice2(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("产品","服务", "类别","用户","食材","管理员");
        return choice;
    }

    private void loadData(){
        this.operators = getOperator();
        this.users = getUser();
        this.foodInfos = getFoodInfo();
        this.foodTypes =  getFoodTypes();
//        this.products = getProduct();
//        this.services=  getService();
        this.orderDetails = getOrderDetail();
        this.buyFoods = getOrderBuy();

//        this.appointmentDetails = getAppointmentDetail();
        orderBox.setItems(getOrder());
//        appointmentBox.setItems(getAppointment());
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

                }else if(newValue.equals(foodInfoTab)) {
                    refreshFoodInfo(new ActionEvent());
                    foodInfos = getFoodInfo();
                }else if(newValue.equals(userTab)) {
                    refreshUser(new ActionEvent());
                }else if(newValue.equals(adminTab)){
                    refreshOperator(new ActionEvent());
                }else if(newValue.equals(foodTypeTab)){
                    refreshFoodType(new ActionEvent());
                }else if(newValue.equals(orderTab)){
                    refreshOrder(new ActionEvent());
                }else if(newValue.equals(orderBuyTab)){
                    refreshOrderBuy(new ActionEvent());
                }

//                }
//                    refreshAppointment(new ActionEvent());
//                }else if(newValue.equals(adminTab)){
//                    refreshOperator(new ActionEvent());

//                }else if(newValue.equals(categoryTab)){
//                    refreshCategory(new ActionEvent());
//
//                }else if(newValue.equals(productTab)){
//                    refreshProduct(new ActionEvent());
//
//                }else if (newValue.equals(serviceTab)){
//                    refreshService(new ActionEvent());
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
        foodInfoTotal.setText("食材信息个数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanFoodInfo")));
        orderTotal.setText("订单总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanFoodOrder")));
        orderBuyTotal.setText("采购总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanBuyFood")));
//        appointmentTotal.setText("预约总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanAppointment")));
//        serviceTotal.setText("服务总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanService")));
//        productTotal.setText("产品总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanProduct")));
        foodTypeTotal.setText("分类总数: "+ String.valueOf(KitchenSystemUtil.getCount("BeanFoodType")));
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
            hamburger.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (Event event) -> {
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
