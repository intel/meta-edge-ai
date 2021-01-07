DESCRIPTION = "k3s collectd plugin and config"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=030cb33d2af49ccebca74d0588b84a21"

SRC_URI = "git://github.com/intel/edge-ai-collectd.git;protocol=https \
          "
SRCREV = "34f6f680a920221ab503951b29cf3efd31ac2d60"

do_install() {
    install -d ${D}/${sysconfdir}/edge-ai/collectd
    install -m 644 ${WORKDIR}/git/collectd.conf ${D}/${sysconfdir}/edge-ai/collectd/collectd.conf
    install -d ${D}/${sysconfdir}/edge-ai/collectd/collectd_python
    install -m 755 ${WORKDIR}/git/collectd_python/intel_ddr.py ${D}/${sysconfdir}/edge-ai/collectd/collectd_python/intel_ddr.py
    install -m 755 ${WORKDIR}/git/collectd_python/flexnoc.py ${D}/${sysconfdir}/edge-ai/collectd/collectd_python/flexnoc.py
    install -m 755 ${WORKDIR}/git/collectd_python/ioremap.py ${D}/${sysconfdir}/edge-ai/collectd/collectd_python/ioremap.py
    install -m 755 ${WORKDIR}/git/collectd_python/kmb_noc.py ${D}/${sysconfdir}/edge-ai/collectd/collectd_python/kmb_noc.py
    install -m 755 ${WORKDIR}/git/kmb_collectd_plugin.sh ${D}/${sysconfdir}/edge-ai/collectd/kmb_collectd_plugin.sh
    install -d ${D}/${sysconfdir}/edge-ai/deploy
    install -m 0644 ${WORKDIR}/git/collectd_deploy.yaml ${D}/${sysconfdir}/edge-ai/deploy/2-collectd_deploy.yaml
}

FILES_${PN} += "${sysconfdir}/edge-ai/collectd"
FILES_${PN} += "${sysconfdir}/edge-ai/collectd/collectd_python"
FILES_${PN} += "${sysconfdir}/edge-ai/deploy/2-collectd_deploy.yaml"
