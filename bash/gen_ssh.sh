#!/usr/bin/env bash
ssh-keygen -t rsa -P ""

cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys

#ssh localhost

ssh hduser@127.0.0.1 -p 2222