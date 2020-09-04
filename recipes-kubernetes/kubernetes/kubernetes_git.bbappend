PV = "v1.18.3-beta+git${SRCREV_kubernetes}"
SRCREV_kubernetes = "fe3ac3e38838a09dfd4b48d568083144211a95f8"
SRCREV_kubernetes-release = "569a07bc48cf52e25ba4b1f33772b0e1a5999b27"

SRC_URI = "git://github.com/kubernetes/kubernetes.git;branch=release-1.18;name=kubernetes \
           git://github.com/kubernetes/release;branch=master;name=kubernetes-release;destsuffix=git/release \
           file://0001-hack-lib-golang.sh-use-CC-from-environment.patch \
           file://0001-cross-don-t-build-tests-by-default.patch \
          "

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${systemd_unitdir}/system/kubelet.service.d/

    install -d ${D}${sysconfdir}/kubernetes/manifests/

    install -m 755 -D ${S}/src/import/_output/local/bin/${TARGET_GOOS}/${TARGET_GOARCH}/* ${D}/${bindir}

    install -m 0644 ${WORKDIR}/git/release/cmd/kubepkg/templates/latest/deb/kubelet/lib/systemd/system/kubelet.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/git/release/cmd/kubepkg/templates/latest/deb/kubeadm/10-kubeadm.conf  ${D}${systemd_unitdir}/system/kubelet.service.d/
}

export GOCACHE = "${B}/.cache"
