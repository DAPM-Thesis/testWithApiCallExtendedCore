package impl.pipe1;

import communication.message.impl.event.Event;
import pipeline.processingelement.Source;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class IngestionTriggeringSource extends Source<Event> {

    private boolean triggered = false;

    public IngestionTriggeringSource() {
        triggerIngestionService();
    }

    @Override
    public Event process() {
        // No actual event generation — this is only for triggering ingestion externally
        try {
            Thread.sleep(1000); // prevent tight loop
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    private void triggerIngestionService() {
        if (triggered) return;

        try {
            String endpoint = "http://localhost:8081/ingest/start";
            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int responseCode = con.getResponseCode();
            System.out.println("Ingestion API Response Code: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("Ingestion Triggered: " + response);
            } else {
                System.err.println("Failed to trigger ingestion. HTTP status: " + responseCode);
            }

            con.disconnect();
            triggered = true;

        } catch (Exception e) {
            System.err.println("Error triggering ingestion service: " + e.getMessage());
        }
    }
}
