package indi.simon.learning.models;

import org.apache.flink.core.io.SimpleVersionedSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenzhuo(zhiyue)
 */
public class ShitSplitSerializer implements SimpleVersionedSerializer<ShitSplit> {

    private final AtomicInteger version;

    public ShitSplitSerializer() {
        this.version = new AtomicInteger(0);
    }

    @Override
    public int getVersion() {
        return version.get();
    }

    @Override
    public byte[] serialize(ShitSplit obj) throws IOException {
        version.incrementAndGet();
        return obj.splitId().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ShitSplit deserialize(int version, byte[] serialized) throws IOException {
        return new ShitSplit(new String(serialized, StandardCharsets.UTF_8));
    }
}
