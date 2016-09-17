#!/bin/bash
if [[ -z $HOMEPATH ]]; then
	export HOMEPATH=/Users/talesviegas/repositorios/pessoal/IlegraTest/homedir
fi
echo $HOMEPATH
cd bin
java br.com.ilegra.test.main.Main

