DESCRIPTION = "resource oriented scheduler for vision edge"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

SRC_URI = "git://github.com/intel/edge-ai-resource-oriented-scheduler.git;protocol=https"
SRCREV = "ed21e1a57c72116201ff8b82688ab5dd399380bd"

inherit go

GO_IMPORT = "github.com/intel/edge-ai-resource-oriented-scheduler"

do_compile() {
	go build -buildmode=pie ${GO_IMPORT}
}

DEPLOY_FILE = "${@bb.utils.contains('CORE_IMAGE_EXTRA_INSTALL', 'k3s', 'ros-deploy-k3s.yaml', 'ros-deploy-k8s.yaml', d)}"

do_install() {
    install -d ${D}/${sysconfdir}/edge-ai/scheduler
    install -m 0755 ${WORKDIR}/build/edge-ai-resource-oriented-scheduler ${D}/${sysconfdir}/edge-ai/scheduler/ros
    install -m 644 ${WORKDIR}/build/src/${GO_IMPORT}/sched-config-k3s.yaml ${D}/${sysconfdir}/edge-ai/scheduler/sched-config-k3s.yaml
    install -m 644 ${WORKDIR}/build/src/${GO_IMPORT}/sched-policy-k3s.json ${D}/${sysconfdir}/edge-ai/scheduler/sched-policy-k3s.json
    install -m 644 ${WORKDIR}/build/src/${GO_IMPORT}/sched-config-k8s.yaml ${D}/${sysconfdir}/edge-ai/scheduler/sched-config-k8s.yaml
    install -m 644 ${WORKDIR}/build/src/${GO_IMPORT}/sched-policy-k8s.json ${D}/${sysconfdir}/edge-ai/scheduler/sched-policy-k8s.json

    install -d ${D}/${sysconfdir}/edge-ai/deploy
    install -m 644 ${WORKDIR}/build/src/${GO_IMPORT}/${DEPLOY_FILE} ${D}/${sysconfdir}/edge-ai/deploy/0-ros-deploy.yaml
}

FILES_${PN} += "${sysconfdir}/edge-ai/scheduler"
FILES_${PN} += "${sysconfdir}/edge-ai/deploy/0-ros-deploy.yaml"
