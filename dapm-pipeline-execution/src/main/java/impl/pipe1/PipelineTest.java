package impl.pipe1;

import algorithm.Algorithm;
import communication.message.impl.event.Event;
import communication.message.Message;
import pipeline.Pipeline;
import pipeline.PipelineBuilder;
import pipeline.processingelement.Sink;
import pipeline.processingelement.Source;
import pipeline.processingelement.operator.SimpleOperator;

public class PipelineTest {

    public static void main(String[] args) {

        // Pipeline: Source<Event> -> Operator<Event, Event, String, String> -> Sink<Event>

        // Source
        //Source<Event> source = new MyEventSource();
        Source<Event> source = new IngestionTriggeringSource();

        // Event Operator
        Algorithm<Message, Event> algorithm = new MyEventAlgorithm();
        SimpleOperator<Event> operator = new MyEventOperator(algorithm);

        // Sink
        Sink sink = new MySink();

        // Create pipeline using pipeline builder
        PipelineBuilder builder = new PipelineBuilder();
        Pipeline pipeline = builder.createPipeline()
                .addProcessingElement(source)
                .addProcessingElement(operator)
                .addProcessingElement(sink)
                .connect(source, operator)
                .connect(operator, sink)
                .getCurrentPipeline();

        pipeline.start();
    }
}
