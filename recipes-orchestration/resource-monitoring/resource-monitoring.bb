DESCRIPTION = "k3s resource monitoring deployment"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/intel/edge-ai-resource-monitoring-deployment.git;protocol=https \
          "
SRCREV = "2e95ad6c8ae387f9d57421b52fe01038480ca867"

do_install() {
    install -d ${D}/${sysconfdir}/edge-ai/deploy
    install -m 0644 ${WORKDIR}/git/prometheus-adapter.yaml ${D}/${sysconfdir}/edge-ai/deploy/5-prometheus-adapter.yaml
    install -m 0644 ${WORKDIR}/git/prometheus-deploy.yml ${D}/${sysconfdir}/edge-ai/deploy/6-prometheus-server.yaml
}

FILES_${PN} += "${sysconfdir}/edge-ai/deploy/5-prometheus-adapter.yaml"
FILES_${PN} += "${sysconfdir}/edge-ai/deploy/6-prometheus-server.yaml"
