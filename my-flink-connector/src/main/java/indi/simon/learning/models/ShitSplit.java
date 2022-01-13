package indi.simon.learning.models;

import org.apache.flink.api.connector.source.SourceSplit;

import java.util.UUID;

/**
 * @author chenzhuo(zhiyue)
 */
public class ShitSplit implements SourceSplit {

    private final String splitId;

    public ShitSplit() {
        splitId = "split_" + UUID.randomUUID().toString();
    }

    @Override
    public String splitId() {
        return splitId;
    }

    public ShitSplit(String splitId) {
        this.splitId = splitId;
    }
}
