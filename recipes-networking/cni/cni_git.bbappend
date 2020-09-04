do_compile_append() {
    cd ${S}/src/import/vendor/github.com/containernetworking/plugins/
	PLUGINS="$(ls -d plugins/ipam/* | grep -v windows)"
	mkdir -p ${WORKDIR}/plugins/bin/
	for p in $PLUGINS; do
	    plugin="$(basename "$p")"
	    echo "building: $p"
	    go build -o ${WORKDIR}/plugins/bin/$plugin github.com/containernetworking/plugins/$p
	done
}

export GOCACHE = "${B}/.cache"