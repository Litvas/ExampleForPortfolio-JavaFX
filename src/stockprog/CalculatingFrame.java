package stockprog;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;
import stockprog.entity.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatingFrame extends Application {


    private ObservableList<String> optionsCategories;
    private List<String> tempData;
    private static final String request = "http://finance.google.com/finance/info?client=ig&q=NSE:";
    private String result;
    private TableView<Stock> tableData;
    private Map<String, String> tempDataMap;
    ReadData readData = new DataHandler();
    RequestHandler requestHandler = new RequestHandlerImpl();
    ObservableList<Stock> initedData = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        //  stage.setMaximized(true);
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(pane, 1000, 700);
        Label codeCompanyLabel = new Label("Company`s code");
        Label categoryLabel = new Label("Category");
        final TextField codeCompanyText = new TextField();
        tempData = (List<String>) readData.readFileCategory("src/stockprog/datalist/categoriesList.txt");
        optionsCategories = readData.generateList(tempData);
        tempDataMap = readData.readFileCompany("src/stockprog/datalist/companies.txt");
        ComboBox category = new ComboBox(optionsCategories);
        Button addButton = new Button("Add");
        Button fetchButton = new Button("Fetch");

        pane.add(generateTable(), 2, 3, 10, 10);
        pane.add(addButton, 4, 1);
        pane.add(fetchButton, 4, 2);
        pane.add(codeCompanyLabel, 2, 1);
        pane.add(codeCompanyText, 2, 2);
        pane.add(categoryLabel, 3, 1);
        pane.add(category, 3, 2);


        fetchButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                Map<String, Object> hashMap = new HashMap<String, Object>();
                String companyCode = codeCompanyText.getText();
                try {
                    URL url = new URL(request + companyCode);
                    result = requestHandler.sendRequest(url);
                    hashMap = requestHandler.parseRequest(result);
                    hashMap.put("companyCode", companyCode);
                    initTableData(hashMap, tempDataMap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        pane.setHgap(20);
        pane.setVgap(15);
        stage.setTitle("CalculatingData stock data");
        stage.setScene(scene);
        stage.show();
    }

    private TableView<Stock> generateTable() {

        tableData = new TableView();
        tableData.setPrefWidth(600);

        TableColumn companyColumn = new TableColumn("Company Name");
        companyColumn.setPrefWidth(200);
        companyColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn categoryColumn = new TableColumn("Category");
        categoryColumn.setPrefWidth(200);
        categoryColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn ioHighColumn = new TableColumn("IO High");
        ioHighColumn.setPrefWidth(75);
        ioHighColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn ioLowColumn = new TableColumn("IO Low");
        ioLowColumn.setPrefWidth(75);
        ioLowColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn maColumn = new TableColumn("MA");
        maColumn.setPrefWidth(75);
        maColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn changeColumn = new TableColumn("Change");
        changeColumn.setPrefWidth(75);
        changeColumn.setSortType(TableColumn.SortType.ASCENDING);

        tableData.getColumns().addAll(companyColumn,
                categoryColumn,
                ioHighColumn,
                ioLowColumn,
                maColumn,
                changeColumn);

        companyColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("companyName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("companyCategory"));
        ioHighColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("highInitialOpeningPrice"));
        ioLowColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("lowInitialOpeningPrice"));
        maColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("movingAverage"));
        changeColumn.setCellValueFactory(new PropertyValueFactory<Stock, Double>("change"));

        return tableData;
    }

       private void initTableData(Map<String, Object> initedHashMap, Map<String, String> initedHashMap2) {
        Stock st = new Stock();
        st.setCompanyName(initedHashMap2.get(initedHashMap.get("companyCode")));
        st.setHighInitialOpeningPrice((Double) initedHashMap.get("highInitialOpeningPrice"));
        st.setLowInitialOpeningPrice((Double) initedHashMap.get("lowInitialOpeningPrice"));
        initedData.add(st);
        tableData.setItems(initedData);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
