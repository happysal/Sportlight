package it.uniroma3.bigdata.radeon.jobs;

import it.uniroma3.bigdata.radeon.data.ProductWritable;
import it.uniroma3.bigdata.radeon.data.ProductWritableList;
import it.uniroma3.bigdata.radeon.mappers.BestFivePerMonthMapper;
import it.uniroma3.bigdata.radeon.reducers.BestFivePerMonthReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class BestFivePerMonth {

	public static void main(String[] args) throws Exception {
		
		//Probabilmente la specifica che il singolo record � una linea � contenuta nell'oggetto Configuration di default
		Job job = new Job(new Configuration(), "BestFivePerMonth");

		job.setJarByClass(BestFivePerMonth.class);
		
		job.setMapperClass(BestFivePerMonthMapper.class);
		job.setReducerClass(BestFivePerMonthReducer.class);
		
		//Setta il percorso dei dati di input e quello per i dati di output nell'hdfs
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(ProductWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(ProductWritableList.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.waitForCompletion(true);
	}
}