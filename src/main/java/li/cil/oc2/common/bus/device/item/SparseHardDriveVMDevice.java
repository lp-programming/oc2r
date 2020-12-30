package li.cil.oc2.common.bus.device.item;

import li.cil.ceres.BinarySerialization;
import li.cil.sedna.api.device.BlockDevice;
import li.cil.sedna.device.block.SparseBlockDevice;
import li.cil.sedna.utils.ByteBufferInputStream;
import net.minecraft.item.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Optional;

public final class SparseHardDriveVMDevice extends AbstractHardDriveVMDevice<SparseBlockDevice> {
    private final BlockDevice base;
    private final boolean readonly;

    ///////////////////////////////////////////////////////////////////

    public SparseHardDriveVMDevice(final ItemStack stack, final BlockDevice base, final boolean readonly) {
        super(stack);
        this.base = base;
        this.readonly = readonly;
    }

    ///////////////////////////////////////////////////////////////////

    @Override
    protected int getSize() {
        return (int) base.getCapacity();
    }

    @Override
    protected SparseBlockDevice createDevice() {
        return new SparseBlockDevice(base, readonly);
    }

    @Override
    protected Optional<InputStream> getSerializationStream(final SparseBlockDevice device) {
        if (device.getBlockCount() == 0) {
            return Optional.empty();
        }

        return Optional.of(new SerializationStream(device));
    }

    @Override
    protected OutputStream getDeserializationStream(final SparseBlockDevice device) {
        return new DeserializationStream(device);
    }

    ///////////////////////////////////////////////////////////////////

    private static final class SerializationStream extends InputStream {
        private final SparseBlockDevice device;
        private ByteBufferInputStream stream;

        private SerializationStream(final SparseBlockDevice device) {
            this.device = device;
        }

        @Override
        public int read() {
            ensureSerialized();
            return stream.read();
        }

        @Override
        public int read(final byte[] b, final int off, final int len) {
            ensureSerialized();
            return stream.read(b, off, len);
        }

        @Override
        public long skip(final long n) throws IOException {
            return stream.skip(n);
        }

        @Override
        public int available() throws IOException {
            return stream.available();
        }

        private void ensureSerialized() {
            if (stream != null) {
                return;
            }

            stream = new ByteBufferInputStream(BinarySerialization.serialize(device));
        }
    }

    private static final class DeserializationStream extends OutputStream {
        private final SparseBlockDevice device;
        private final ByteArrayOutputStream stream;

        public DeserializationStream(final SparseBlockDevice device) {
            this.device = device;
            stream = new ByteArrayOutputStream();
        }

        @Override
        public void write(final int b) {
            stream.write(b);
        }

        @Override
        public void write(final byte[] b, final int off, final int len) {
            stream.write(b, off, len);
        }

        @Override
        public void close() {
            BinarySerialization.deserialize(ByteBuffer.wrap(stream.toByteArray()), device);
        }
    }
}