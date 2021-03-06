SUMMARY = "FRU properties config for openpower-vpd-parser"
PR = "r1"

inherit native
inherit obmc-phosphor-license
inherit openpower-fru-vpd
inherit mrw-xml

SRC_URI += "file://config.yaml"

DEPENDS += " \
           mrw-native \
           mrw-perl-tools-native \
           "

PROVIDES += "virtual/openpower-fru-properties"

S = "${WORKDIR}"

do_install() {
        DEST=${D}${properties_datadir}
        install -d ${DEST}

        ${bindir}/perl-native/perl \
            ${bindir}/gen_fru_properties.pl \
            -m ${mrw_datadir}/${MRW_XML} \
            -c config.yaml \
            -o ${DEST}/out.yaml
}
