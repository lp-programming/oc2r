/* SPDX-License-Identifier: MIT */

package li.cil.oc2.common.vm.context.global;

import li.cil.sedna.api.device.MemoryMappedDevice;
import li.cil.sedna.api.memory.MappedMemoryRange;
import li.cil.sedna.api.memory.MemoryAccessException;
import li.cil.sedna.api.memory.MemoryMap;
import li.cil.sedna.api.memory.MemoryRange;

import javax.annotation.Nullable;
import java.util.Optional;

final class GlobalMemoryMap implements MemoryMap {
    private final MemoryMap memoryMap;

    ///////////////////////////////////////////////////////////////////

    GlobalMemoryMap(final MemoryMap memoryMap) {
        this.memoryMap = memoryMap;
    }

    ///////////////////////////////////////////////////////////////////

    @Override
    public boolean addDevice(final long address, final MemoryMappedDevice device) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeDevice(final MemoryMappedDevice device) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MappedMemoryRange> getMemoryRange(final MemoryMappedDevice device) {
        return memoryMap.getMemoryRange(device);
    }

    @Override
    public Optional<MappedMemoryRange> getMemoryRange(final MemoryRange memoryRange) {
        return memoryMap.getMemoryRange(memoryRange);
    }

    @Nullable
    @Override
    public MappedMemoryRange getMemoryRange(final long address) {
        return memoryMap.getMemoryRange(address);
    }

    @Override
    public void setDirty(final MemoryRange range, final int offset) {
        memoryMap.setDirty(range, offset);
    }

    @Override
    public long load(final long address, final int sizeLog2) throws MemoryAccessException {
        return memoryMap.load(address, sizeLog2);
    }

    @Override
    public void store(final long address, final long value, final int sizeLog2) throws MemoryAccessException {
        memoryMap.store(address, value, sizeLog2);
    }
}