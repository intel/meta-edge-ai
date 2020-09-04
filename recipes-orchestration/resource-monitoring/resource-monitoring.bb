DESCRIPTION = "k3s resource monitoring deployment"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/intel/edge-ai-resource-monitoring-deployment.git;protocol=https \
          "
SRCREV = "94a80f95f81c80c00115f497474cf7ec4f8939f6"

do_install() {
    install -d ${D}/${sysconfdir}/edge-ai/deploy
    install -m 0644 ${WORKDIR}/git/prometheus-operator.yaml ${D}/${sysconfdir}/edge-ai/deploy/3-prometheus-operator.yaml
    install -m 0644 ${WORKDIR}/git/prometheus-inst.yaml ${D}/${sysconfdir}/edge-ai/deploy/4-prometheus-inst.yaml
    install -m 0644 ${WORKDIR}/git/prometheus-adapter.yaml ${D}/${sysconfdir}/edge-ai/deploy/5-prometheus-adapter.yaml
}

FILES_${PN} += "${sysconfdir}/edge-ai/deploy/3-prometheus-operator.yaml"
FILES_${PN} += "${sysconfdir}/edge-ai/deploy/4-prometheus-inst.yaml"
FILES_${PN} += "${sysconfdir}/edge-ai/deploy/5-prometheus-adapter.yaml"