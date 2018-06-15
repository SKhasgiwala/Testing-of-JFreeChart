package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jfree.chart.AreaChartTest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Step3 {
    private JFreeChart areaChart;
    AreaChartTest.LocalListener l;
    CategoryPlot plot;

    @Given("^I have an initial chart with a dataset")
    public void theChartWithADatasetIsAvailable(){
        try {
            this.areaChart = AreaChartTest.createAreaChart();
            BufferedImage image = new BufferedImage(200 , 100,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            this.areaChart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null,
                    null);
            g2.dispose();
        }
        catch (Exception e) {
            fail("There should be no exception.");
        }
    }

    @When("^I replace the dataset with new data values")
    public void replaceDatasetWithNewValues(){
        Number[][] data = new Integer[][]
                {{new Integer(-30), new Integer(-20)},
                        {new Integer(-10), new Integer(10)},
                        {new Integer(20), new Integer(30)}};
        CategoryDataset newData = DatasetUtils.createCategoryDataset(
                "S", "C", data);
         l = new AreaChartTest.LocalListener();
        this.areaChart.addChangeListener(l);
         plot = (CategoryPlot) this.areaChart.getPlot();
        plot.setDataset(newData);
    }

    @Then("^I check that the new dataset is OK")
    public void datasetShouldBeOK(){
        ValueAxis axis = plot.getRangeAxis();
        Range range = axis.getRange();
        assertTrue("Expecting the lower bound of the range to be around -30: "
                + range.getLowerBound(), range.getLowerBound() <= -30);
        assertTrue("Expecting the upper bound of the range to be around 30: "
                + range.getUpperBound(), range.getUpperBound() >= 30);

    }

}
