DESCRIPTION = "k3s device plugin for kmb resource"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/intel/edge-ai-device-plugin.git;protocol=https \
          "
SRCREV = "11a688842b629e9d0e97bda7add95ce40c219f15"

inherit go

GO_IMPORT = "github.com/intel/edge-ai-device-plugin"

do_compile() {
    go build -buildmode=pie ${GO_IMPORT}
}

do_install() {
    install -d ${D}/${sysconfdir}/edge-ai/device-plugin
    install -m 0755 ${WORKDIR}/build/edge-ai-device-plugin ${D}/${sysconfdir}/edge-ai/device-plugin/kmb_plugin
    install -d ${D}/${sysconfdir}/edge-ai/deploy
    install -m 0644 ${WORKDIR}/build/src/${GO_IMPORT}/device_plugin_daemonset.yaml ${D}/${sysconfdir}/edge-ai/deploy/1-device_plugin_daemonset.yaml
}

FILES_${PN} += "${sysconfdir}/edge-ai/device-plugin/kmb_plugin"
