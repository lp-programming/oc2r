/* SPDX-License-Identifier: MIT */

package li.cil.oc2r.common.vm.provider;

import li.cil.oc2r.common.vm.device.PciRootPortDevice;
import li.cil.oc2r.common.vm.device.SimpleFramebufferDevice;
import li.cil.sedna.devicetree.DeviceTreeRegistry;

public final class DeviceTreeProviders {
    public static void initialize() {
        DeviceTreeRegistry.putProvider(SimpleFramebufferDevice.class, new SimpleFramebufferDeviceProvider());
        DeviceTreeRegistry.putProvider(PciRootPortDevice.class, new PciRootPortDeviceProvider());
    }
}
