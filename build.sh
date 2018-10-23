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
 
echo "Please enter new password of admin: "
read -s admin_password;
# pip install bcrypt

encode= python -c "import bcrypt, sys; salt = bcrypt.gensalt(); hashed = bcrypt.hashpw(sys.argv[0], salt); sys.exit(hashed)" $admin_password
echo $encode
query="s/\$2a\$10\$S7ukTjY7CoR.O2uksyWrjuBDIOCEgBzMPyfAI1aXS36HXLJDKIcdO/$encode/g"
echo $query
eval "sed -i -e  ./src/main/resources/sql/init.sql

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

sed -i -e 's/123123/$2a$10$S7ukTjY7CoR.O2uksyWrjuBDIOCEgBzMPyfAI1aXS36HXLJDKIcdO/g' ./src/main/resources/sql/init.sql

# mvn clean install