package indi.simon.learning.models;

import org.apache.flink.api.connector.source.SplitEnumerator;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class ShitSplitEnumerator implements SplitEnumerator<ShitSplit, Void> {

    @Override
    public void start() {

    }

    @Override
    public void handleSplitRequest(int i, @Nullable String s) {

    }

    @Override
    public void addSplitsBack(List<ShitSplit> list, int i) {

    }

    @Override
    public void addReader(int i) {

    }

    @Override
    public Void snapshotState(long l) throws Exception {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
