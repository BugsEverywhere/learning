package indi.simon.learning.source;

import indi.simon.learning.models.*;
import org.apache.flink.api.connector.source.*;
import org.apache.flink.core.io.SimpleVersionedSerializer;

/**
 * @author chenzhuo(zhiyue)
 */
public class ShitHappenSource implements Source<MessageModel, ShitSplit, Void> {
    @Override
    public Boundedness getBoundedness() {
        return null;
    }

    @Override
    public SourceReader<MessageModel, ShitSplit> createReader(SourceReaderContext sourceReaderContext) throws Exception {
        return new ShitReader();
    }

    @Override
    public SplitEnumerator<ShitSplit, Void> createEnumerator(SplitEnumeratorContext<ShitSplit> splitEnumeratorContext) throws Exception {
        return new ShitSplitEnumerator();
    }

    @Override
    public SplitEnumerator<ShitSplit, Void> restoreEnumerator(SplitEnumeratorContext<ShitSplit> splitEnumeratorContext, Void unused) throws Exception {
        return null;
    }

    @Override
    public SimpleVersionedSerializer<ShitSplit> getSplitSerializer() {
        return new ShitSplitSerializer();
    }

    @Override
    public SimpleVersionedSerializer<Void> getEnumeratorCheckpointSerializer() {
        return null;
    }
}
