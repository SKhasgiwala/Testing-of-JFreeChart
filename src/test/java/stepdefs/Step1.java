package stepdefs;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Step1 {

    DefaultPieDataset data = new DefaultPieDataset();
    private JFreeChart pieChart;
    @Given("^I have the dataset")
    public void theDatasetIsPresent(){

        data.setValue("Apple", 60);
        data.setValue("Orange", 120);
        data.setValue("Banana", 90);
        data.setValue("Strawberry", 150);
    }


    @When("^I populate the values in chart")
    public void PopulateTheValuesInCHart(){
        this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
    }

    @When("^set it to visible")
    public void setToVisible(){
        pieChart.isBorderVisible();
    }

    @Then("^pie chart is displayed to me")
    public void pieChartShouldBeDisplayed(){
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        PieDataset data1 = piePlot.getDataset();

        List list = data.getKeys();  //Test keys of piechart
        List list1 = data1.getKeys();
        assertEquals(list, list1);

        Number valOne = data1.getValue(0);//Test value of piechart
        assertEquals(60.0, valOne);
    }
}
