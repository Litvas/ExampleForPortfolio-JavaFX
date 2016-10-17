package stockprog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataHandler implements CalculatingData, ReadData {

    private ArrayList<Double> priceHiLo;
    private ArrayList<Double> priceMA;


    @Override
    public List<String> readFileCategory(String pathToFile) {
        List<String> data = new ArrayList<String>();
        try {
            File file = new File(pathToFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + pathToFile);
        }
        return data;
    }

    @Override
    public Map<String, String> readFileCompany(String pathToFile) {
        Map<String, String> data = new HashMap<String, String>();
        try {
            File file = new File(pathToFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[]temp=scanner.nextLine().split("\t");
                data.put(temp[0], temp[1]);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + pathToFile);
        }
        return data;
    }

    @Override
    public ObservableList<String> generateList(List<String> list) {
        ObservableList<String> optionsCategories = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            optionsCategories.add(list.get(i));
        }
        return optionsCategories;
    }

    @Override
    public void calcHigtLowValue() {

    }

    @Override
    public void calcMovingAverage() {

    }

    @Override
    public void calcPercent() {

    }
}
