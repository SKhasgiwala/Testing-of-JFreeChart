package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PieChartTest;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class Step2 {
    DefaultPieDataset data = new DefaultPieDataset();
    private JFreeChart pieChart;
    PiePlot plot;
    PieChartTest.LocalListener l;
    @Given("^I have an initial dataset")
    public void theDatasetIsAvailable(){
        data.setValue("Apple", 60);
        data.setValue("Orange", 120);
        data.setValue("Banana", 90);
        data.setValue("Strawberry", 150);
         l = new PieChartTest.LocalListener();
        this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
        this.pieChart.addChangeListener(l);
        plot = (PiePlot) this.pieChart.getPlot();

    }

    @When("^I replace the dataset with null")
    public void replaceTheDatasetWithNull(){

        plot.setDataset(null);
    }

    @Then("^I receive a chart change notification")
    public void receiveChartChangeNotification(){
        assertEquals(true, l.flag);
    }

    @Then("^The dataset should be null")
    public void datasetShouldBeNull(){
        assertNull(plot.getDataset());
    }


}
