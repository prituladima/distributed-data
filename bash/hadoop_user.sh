#!/usr/bin/env bash
sudo addgroup hadoop
sudo adduser --ingroup hadoop hduser

#Result
#Adding group `hadoop' (GID 1002) ...
#Done.
#Adding user `hduser' ...
#Adding new user `hduser' (1002) with group `hadoop' ...
#Creating home directory `/home/hduser' ...
#Copying files from `/etc/skel' ...
#Enter new UNIX password:
#Retype new UNIX password:
#passwd: password updated successfully
#Changing the user information for hduser
#Enter the new value, or press ENTER for the default
#        Full Name []: Hadoop Spark
#        Room Number []: Cassandra
#        Work Phone []:
#        Home Phone []:
#        Other []:
#Is the information correct? [Y/n] Y
#Done
