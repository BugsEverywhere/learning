package indi.simon.learning.models;

import org.apache.flink.api.connector.source.ReaderOutput;
import org.apache.flink.api.connector.source.SourceReader;
import org.apache.flink.core.io.InputStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author chenzhuo(zhiyue)
 */
public class ShitReader implements SourceReader<MessageModel, ShitSplit> {

    @Override
    public void start() {

    }

    @Override
    public InputStatus pollNext(ReaderOutput<MessageModel> readerOutput) throws Exception {
        readerOutput.collect(new MessageModel());
        Thread.sleep(5000L);
        //总是返回
        return InputStatus.MORE_AVAILABLE;
    }

    @Override
    public List<ShitSplit> snapshotState(long l) {
        return null;
    }

    @Override
    public CompletableFuture<Void> isAvailable() {
        return null;
    }

    @Override
    public void addSplits(List<ShitSplit> list) {

    }

    @Override
    public void notifyNoMoreSplits() {

    }

    @Override
    public void close() throws Exception {

    }
}
