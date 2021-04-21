package Templates.UserGui;

import Entities.User;
import Services.SceneLoader;
import Services.UserServices;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ShowUsersController implements Initializable {


    public Button btn_delete;
    public Button btn_modify;


    public Button btn_ban;

    public Button btn_back;
    public TableColumn avatar_column;
    public HBox lnk_show_users;
    public HBox lnk_show_stats;
    public TableColumn cin_column;
    public HBox action_buttons;
    public HBox lnk_dashboard_home;
    public TextField txt_search_box;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> first_name_column;

    @FXML
    private TableColumn<User, String> last_name_column;

    @FXML
    private TableColumn<User, String> email_column;

    @FXML
    private TableColumn<User, String> password_column;

    @FXML
    private void btn_banAction(ActionEvent event)
    {
        if (event.getSource()==btn_ban)
        {
            if (btn_ban.getText().equals("Ban"))
            {
                this.banUser();
            }
            else if (btn_ban.getText().equals("Unban"))
            {
                this.unbanUser();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

             first_name_column.setCellValueFactory(new PropertyValueFactory<User,String>("first_name"));
             first_name_column.setMinWidth(100);
             last_name_column.setCellValueFactory(new PropertyValueFactory<User,String>("last_name"));
             last_name_column.setMinWidth(100);
             email_column.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
             email_column.setMinWidth(100);
             cin_column.setCellValueFactory(new PropertyValueFactory<User,String>("cin"));
             cin_column.setMinWidth(100);
             avatar_column.setCellValueFactory(new PropertyValueFactory<User, ImageView>("pic"));
             avatar_column.setMaxWidth(200);


             TableColumn <User, Integer> isBanned_column= new TableColumn<User, Integer>();
             isBanned_column.setText("is_Banned");
             isBanned_column.setMinWidth(50);
             tableView.getColumns().add(isBanned_column);
             isBanned_column.setCellValueFactory(new PropertyValueFactory<User,Integer>("is_banned"));
             btn_ban = new Button();
             btn_ban.setPrefWidth(this.btn_modify.getPrefWidth());
             action_buttons.getChildren().add(btn_ban);
             btn_ban.setVisible(false);
             btn_ban.setTextFill(Color.WHITE);
             btn_ban.setOnAction(this::btn_banAction);
             btn_ban.getStyleClass().add("btn_login");


             this.loadUsers();


            FilteredList<User> filteredData = new FilteredList<>(this.tableView.getItems());
        SortedList <User> sortableData = new SortedList<>(filteredData);
        sortableData.comparatorProperty().bind(this.tableView.comparatorProperty());
            this.txt_search_box.textProperty().addListener((observable, oldValue, newValue) ->
                    filteredData.setPredicate(createPredicate(newValue))
            );

            this.tableView.setItems(sortableData);


    }

    private Predicate<User> createPredicate(String searchText){
        return user -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchTable(user, searchText);
        };
    }


    public void loadUsers()
    {
        UserServices us = new UserServices();
        tableView.setItems(us.showUsers());
    }



    public void onMouseClicked()
    {
      User row = tableView.getSelectionModel().getSelectedItem();
      if (row!=null )
      {

           if (row.getIs_banned()==0)
           {
               btn_ban.setText("Ban");
           }
           else
           {
               btn_ban.setText("Unban");
           }
            btn_ban.setVisible(true);
      }
    }

     public void ModifyUser()
     {
        User u = this.tableView.getSelectionModel().getSelectedItem();
       if (u!=null)
       {
           ProfileController pc= new ProfileController();
           pc.setUser(u);
           SceneLoader.loadScene("UserGui/Profile.fxml",this.btn_modify);
       }
     }

    public void deleteUser()
    {
        UserServices us = new UserServices();
        User u = this.tableView.getSelectionModel().getSelectedItem();
        if (u!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous êtes sur le point de supprimer un utilisateur");
            alert.setContentText("Êtes vous sûr ?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                us.deleteUser(u.getEmail());

            }

            this.showUsers();
        }
    }


    public void banUser()
    {
        UserServices us = new UserServices();
        User u= this.tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Vous êtes sur le point de bannir un utilisateur");
        alert.setContentText("Êtes vous sûr ?");


        if (alert.showAndWait().get() == ButtonType.OK){
            us.banUser(u.getEmail());
        }
        this.showUsers();
    }

    public void unbanUser()
    {
        UserServices us = new UserServices();
        User u= this.tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Vous êtes sur le point de lever l'interdiction d'un utilisateur");
        alert.setContentText("Êtes vous sûr ?");
        if (alert.showAndWait().get() == ButtonType.OK){
            us.unbanUser(u.getEmail());

        }

        this.showUsers();
    }


     public void goBack()
     {
         SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.btn_back);
     }


    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);


    }


    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }

    public void showDashboard()
    {
        SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.lnk_dashboard_home);
    }


        private boolean searchTable(User user, String searchText){

            if (user.getCin()!=null )
            {
               return  user.getCin().toLowerCase().contains(searchText.trim().toLowerCase());
            };
        return (user.getEmail().trim().toLowerCase().contains(searchText.toLowerCase())) ||
                (user.getFirst_name().concat(user.getLast_name()).replaceAll("\\s+","").toLowerCase().contains(searchText.replaceAll("\\s+","").toLowerCase()));

    }


}
