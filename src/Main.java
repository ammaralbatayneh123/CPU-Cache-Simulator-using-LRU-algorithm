import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int userChoice;

        do {
            System.out.println();
            System.out.println("Computer Architecture Final Year Project ");
            System.out.println("Project title : Cache-Processor Simulator using The Least Recently Used (LRU) algorithm ");
            System.out.println("Created by: Ammar Albatayneh ");
            System.out.println("Student id: 2010143");

            System.out.println();

            System.out.println("Please Enter The Cache Capacity!");

            // letting the user enter the cache capacity
            int cacheCapacity = userInput.nextInt();

            // creating the cache object  at the start the cache is always empty
            Cache cache = new Cache(cacheCapacity);

            // creating  a new main memory object
            MainMemory mainMemory = new MainMemory();

            // inserting some data to the main memory at the start of the program
            for (int i = 0; i < 1000000; i++) {
                int randomValue = (int) (Math.random() * 1000000);
                mainMemory.insertDataToTheMemory(i, randomValue);
            }
            System.out.println("---------------------------------------------------");
            System.out.println();

            // creating a new processor object
            Processor processor = new Processor(cache, mainMemory);
            int numberOfRequests = 2000000;

            // here the processor is requesting the data please go to the method implementation for more explaining
            for (int i = 0; i < numberOfRequests; i++) {
                System.out.println("Requesting Data Operation Number " + i);
                System.out.println();
                processor.requestData();
                System.out.println("---------------------------------------------------");
            }

            System.out.println();
            System.out.printf("| %-10s | %-8s |%n", "Cache Hits", "Cache Misses");
            System.out.printf("-----------------------------%n");

            System.out.printf("| %-10s | %-12s |%n", processor.getCacheHits(), processor.getCacheMiss());

            System.out.printf("-----------------------------%n");

            generateBarGraph(processor, numberOfRequests, cacheCapacity);
            System.out.println("Enter 1 To simulate again with different cache size or any number to exit the program");
            userChoice = userInput.nextInt();
        }
        while (userChoice == 1);

        System.out.println("Exiting!!");
    }

    public static void generateBarGraph(Processor processor, int numberOfRequests , int cacheCapacity){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(processor.getCacheHits(), "","Cache Hits");
        dataset.setValue(processor.getCacheMiss(),"","Cache Misses");

        String totalRequests = String.valueOf(numberOfRequests);
        String totalCacheSize = String.valueOf(cacheCapacity);

        JFreeChart chart = ChartFactory.createBarChart("Cache Simulator Results Graph For " + totalRequests + " Requests \n and a cache size of " + totalCacheSize,"","Value",dataset, PlotOrientation.VERTICAL,false,true,false);

        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.green);
        ChartFrame chartFrame = new ChartFrame("Cache Simulator Results Graph",chart);
        chartFrame.setVisible(true);
        chartFrame.setSize(500,500);
    }
}