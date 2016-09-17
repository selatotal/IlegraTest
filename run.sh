#!/bin/bash
if [[ -z $HOMEPATH ]]; then
	export HOMEPATH=../homedir
fi
echo $HOMEPATH
cd bin
java br.com.ilegra.test.main.Main

