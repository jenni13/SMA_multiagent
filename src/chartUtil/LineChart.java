package chartUtil;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {

    DefaultCategoryDataset dataset;

    public LineChart(String applicationTitle) {
        super(applicationTitle);
        dataset = new DefaultCategoryDataset( );
    }

    public void addTocreateDataset(int value, String rowKey, String columnKey) {
        dataset.addValue( value , rowKey , columnKey );
    }

    public void createLineChart(String chartTitle, String categoryAxisLabel, String valueAxisLabel) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
}