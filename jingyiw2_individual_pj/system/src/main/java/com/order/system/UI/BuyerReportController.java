package com.order.system.UI;
import com.order.system.DAO.ItemDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuyerReportController {
    @FXML
    private PieChart age_pie;

    @FXML
    private PieChart gender_pie;

    @FXML
    private PieChart income_pie;

    @FXML
    private BarChart<String, Integer> state_bar;

    @FXML
    public void initialize(){
        //gender
        ArrayList<Integer> genderNum = new ItemDAO().getGenderNum();
        ObservableList<PieChart.Data> genderPieData = FXCollections.observableArrayList(
                new PieChart.Data("Male",genderNum.get(0)),
                new PieChart.Data("Female",genderNum.get(1))
        );
        genderPieData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),"amount: ",data.pieValueProperty())));
        gender_pie.getData().addAll(genderPieData);

        //age
        ArrayList<Integer> ageNum = new ItemDAO().getAgeNum();
        ObservableList<PieChart.Data> agePieData = FXCollections.observableArrayList(
                new PieChart.Data("<18",ageNum.get(0)),
                new PieChart.Data("[24,30)",ageNum.get(1)),
                new PieChart.Data("[18,24)",ageNum.get(2)),
                new PieChart.Data("[30,50)",ageNum.get(3)),
                new PieChart.Data(">=50",ageNum.get(4))
        );
        agePieData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),"amount: ",data.pieValueProperty())));
        age_pie.getData().addAll(agePieData);

        //income
        ArrayList<Integer> incomeNum = new ItemDAO().getIncomeNum();
        ObservableList<PieChart.Data> incomePieData = FXCollections.observableArrayList(
                new PieChart.Data("<1200",incomeNum.get(0)),
                new PieChart.Data("[1200,4000)",incomeNum.get(1)),
                new PieChart.Data("[4000,12000)",incomeNum.get(2)),
                new PieChart.Data(">=12000",incomeNum.get(3))
        );
        incomePieData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),"amount: ",data.pieValueProperty())));
        income_pie.getData().addAll(incomePieData);

        //address bar chart
        Map<String,Integer> state_map = new ItemDAO().getStateNum();

        XYChart.Series<String,Integer> series = new XYChart.Series();
        series.setName("state");
        for (String key: state_map.keySet()){
            series.getData().add(new XYChart.Data(key,state_map.get(key)));
        }
        state_bar.getData().addAll(series);
    }
}
