package com.example.nifi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.*;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.io.StreamCallback;
import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Tags({"employee", "salary", "json", "update"})
@CapabilityDescription("Updates salary based on employee age.")
public class UpdateEmployeeSalary extends AbstractProcessor {
    public static final Relationship SUCCESS = new Relationship.Builder()
            .name("success")
            .description("All successful FlowFiles")
            .build();
    private Set<Relationship> relationships;
    @Override
    protected void init(final ProcessorInitializationContext context) {
        final Set<Relationship> rels = new HashSet<>();
        rels.add(SUCCESS);
        relationships = Collections.unmodifiableSet(rels);
    }
    @Override
    public Set<Relationship> getRelationships() {
        return relationships;
    }
    @Override
    public void onTrigger(ProcessContext context, ProcessSession session) throws ProcessException {
        FlowFile flowFile = session.get();
        if (flowFile == null) return;
        FlowFile updatedFlowFile = session.write(flowFile, new StreamCallback() {
            @Override
            public void process(InputStream in, OutputStream out) throws IOException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                ObjectMapper mapper = new ObjectMapper();
                String line;
                while ((line = reader.readLine()) != null) {
                    ObjectNode node = (ObjectNode) mapper.readTree(line);
                    int age = node.get("age").asInt();
                    double salary = node.get("salary").asDouble();
                    double multiplier = (age < 25) ? 1.05 : (age <= 35 ? 1.10 : 1.15);
                    node.put("salary", salary * multiplier);
                    writer.write(mapper.writeValueAsString(node));
                    writer.newLine();
                }
                writer.flush();
            }
        });
        session.transfer(updatedFlowFile, SUCCESS);
    }
}