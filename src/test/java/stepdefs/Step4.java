package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Step4 {

    LegendTitle t1;
    LegendTitle t2;
    Rectangle2D bounds1;
    @Given("^I have an initial XYchart with dataset and a legend title")
    public void availableInitialXYchartWithDatasetAndLegendTitle(){
        XYPlot plot = new XYPlot();
        bounds1 = new Rectangle2D.Double(10.0, 20.0, 30.0, 40.0);
        t1 = new LegendTitle(plot);
        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f,
                4.0f, Color.YELLOW));
        t1.setBounds(bounds1);
    }

    @When("^I clone the legend title")
    public void cloneLegendTitle() throws CloneNotSupportedException{
         t2 = (LegendTitle) t1.clone();
    }

    @Then("^The two legend title must not be equal")
    public void legendTitleShouldNotBeEqual(){
        assertTrue(t1 != t2);
    }

    @Then("^The two legend titles must represent same class")
    public void legendTitleMustRepresentSameClass(){
        assertTrue(t1.getClass() == t2.getClass());
        assertTrue(t1.equals(t2));
    }

    @Then("^They are independent of each other")
    public void independentOfeachOther(){
        bounds1.setFrame(40.0, 30.0, 20.0, 10.0);
        assertFalse(t1.equals(t2));
        t2.setBounds(new Rectangle2D.Double(40.0, 30.0, 20.0, 10.0));
        assertTrue(t1.equals(t2));
    }
}
