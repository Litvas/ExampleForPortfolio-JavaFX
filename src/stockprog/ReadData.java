package stockprog;


import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public interface ReadData {

    Map<String, String> readFileCompany(String pathToFile);

    ObservableList<String> generateList(List<String> list);

    List<String> readFileCategory(String pathToFile);

}
