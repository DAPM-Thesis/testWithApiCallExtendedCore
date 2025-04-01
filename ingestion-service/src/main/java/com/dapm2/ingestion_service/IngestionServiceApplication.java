package com.dapm2.ingestion_service;

import algorithm.Algorithm;
import com.dapm2.ingestion_service.demo.MyEventAlgorithm;
import com.dapm2.ingestion_service.demo.MyEventOperator;
import com.dapm2.ingestion_service.demo.MySink;
import com.dapm2.ingestion_service.demo.MyStreamSource;
import com.dapm2.ingestion_service.streamSources.SSEStreamSource;
import communication.message.Message;
import communication.message.impl.event.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pipeline.Pipeline;
import pipeline.PipelineBuilder;
import pipeline.processingelement.Sink;
import pipeline.processingelement.Source;
import pipeline.processingelement.operator.SimpleOperator;

@SpringBootApplication
public class IngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestionServiceApplication.class, args);
	}

}
