package it.uniroma3.radeon.sa.main.storm;

import it.uniroma3.radeon.sa.utils.PropertyLoader;
import it.uniroma3.radeon.storm.spouts.KafkaSpoutBuilder;

import java.util.Properties;

import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;

public class ClassifyTopology {
	
	public static void main(String[] args) {
		String configFile = args[0];
		Properties prop = PropertyLoader.loadProperties(configFile);
		
		//Settaggio del KafkaSpout
		KafkaSpoutBuilder spoutBuilder = new KafkaSpoutBuilder()
		                                     .setZookeeperServer(prop.getProperty("zkServer"))
		                                     .setTopic(prop.getProperty("topic"))
		                                     .setZkRoot(prop.getProperty("zkRoot"))
		                                     .setConsumerGroup(prop.getProperty("consumerGroup"));
		
		KafkaSpout spout = spoutBuilder.buildSpout();
		
		//Definizione della topologia
		TopologyBuilder topology = new TopologyBuilder();
	}

}