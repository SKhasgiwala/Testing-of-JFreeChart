package org.jfree.newTestFiles;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DefaultPieDataset;
import static org.hamcrest.CoreMatchers.is;
import org.jfree.data.general.PieDataset;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
public class FirstClassTest {
    private JFreeChart pieChart;

    //Test whether the piechart is being populated with the dataset or not
    @Test
    public void testChartDataset() {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Apple", 60);
        data.setValue("Orange", 120);
        data.setValue("Banana", 90);
        data.setValue("Strawberry", 150);
        this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        PieDataset data1 = piePlot.getDataset();

        List list = data.getKeys();  //Test keys of piechart
        List list1 = data1.getKeys();
        assertEquals(list, list1);

        Number valOne = data1.getValue(0);//Test value of piechart
        assertEquals(60.0, valOne);
    }


    //Test various piechart properties once the chart is created like title, legend, tooltip, notify functionality
    @Test
    public void testChartProperties(){
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Apple", 60);
        data.setValue("Orange", 120);
        data.setValue("Banana", 90);
        data.setValue("Strawberry", 150);
        this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
        assertEquals("Pie Chart for fruit sold",pieChart.getTitle().getText());//test title

        boolean legend=pieChart.getLegend().isVisible();//test legend
        assertTrue(legend);

        PiePlot plot= (PiePlot) pieChart.getPlot();//test tooltipgenerator
        PieToolTipGenerator tooltip1=plot.getToolTipGenerator();
        PiePlot plot1 = new PiePlot(data);
        plot1.setToolTipGenerator(new StandardPieToolTipGenerator());
        PieToolTipGenerator tooltip2=plot1.getToolTipGenerator();
        assertEquals(tooltip2,tooltip1);

        boolean testNotify=pieChart.isNotify();//test functionality of whether listeners are notified or not when chart changes
        assertTrue(testNotify);

        boolean testBorder=pieChart.isBorderVisible();//test functionality of whether border is visible or not
        assertFalse(testBorder);

    }

    //Test for IllegalArgumentException thrown in case of null dataset
@Test
    public void testNullDataset(){
    try {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue(null, null);
        this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
    }
    catch (IllegalArgumentException e){
        assertThat(e.getMessage(),is("Null 'key' argument."));
    }
}

//Test for checking whether saving the chart as PNG does not produce any exception
@Test
    public void testSaveAsPNG(){
    DefaultPieDataset data = new DefaultPieDataset();
    data.setValue("Apple", 60);
    data.setValue("Orange", 120);
    data.setValue("Banana", 90);
    data.setValue("Strawberry", 150);
    this.pieChart = ChartFactory.createPieChart("Pie Chart for fruit sold", data);
    try {
        ChartUtils.saveChartAsPNG(new File("/Users/salonikhasgiwala/Desktop/Spring 2018/Piechart"),pieChart,1000,1000);
    } catch (Exception e) {
        assertNull(e);
    }

}
}
