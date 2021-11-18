package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor <T extends Aggregator>{
	T agg;
	String table;

    public AggregatorProcessor(T agg, String table) {
        this.agg = agg;
        this.table = table;
    }
    public double runAggregator(int column) throws IOException {
        StockFileReader reader1 = new StockFileReader(table);
        List<String> stlist =  reader1.readFileData();
        column--;
        for (String a:stlist){
            String [] arr = a.split(",");
            Double num = Double.parseDouble(arr[column]);
            agg.add(num);
        }
        return  agg.calculate();
    }
}
