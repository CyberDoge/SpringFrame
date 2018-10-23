#!/bin/bash
echo "check mysql installed..."
if  ! type mysql > /dev/null; then
    echo "please install mysql"
    exit 1
fi
UP=$(pgrep mysql | wc -l);
sudo_password='';
if [ "$UP" -ne 1 ];
then
        echo "MySQL is down. Enter password to start it";
        sudo -S service mysql start
else
        echo "All is well.";
fi
 
echo "Please enter new password for admin: "
read -s admin_password;
#todo find lib for hashing as in java
# pip install bcrypt
#encode= python -c "import bcrypt, sys; hashed = bcrypt.hashpw(sys.argv[0], bcrypt.gensalt(prefix=b\"2a\", rounds=10)); sys.exit(hashed)" $admin_password
#echo $encode
#query='s/$2a$10$S7ukTjY7CoR.O2uksyWrjuBDIOCEgBzMPyfAI1aXS36HXLJDKIcdO/' + $encode + '/g'
#eval "sed -i -e  ./src/main/resources/sql/init.sql

echo "Source sql script"
echo -n "Enter login: "
read user
mysql -u $user -p  < ./src/main/resources/sql/init.sql

maven_path= which mvn
echo "check maven installed..."
if  ! type mvn > /dev/null; then
    echo "please install mysql"
    exit 1
fi

mvn clean install