# ConveneUp2
Registraion app for campaigning committee who register interested competators in an event.

How to use it?
1. Create a (mysql/phpmyadmin) webhosting account. (eg. www.000webhost.com)
2. Open cPanel and create a table for login authentication and for participant entries using phpMyadmin.(The table structure is independent of the android java code. But is dependent in the php scripts you are going to save.)
3. Open File manager and store the required phps in the website root folder which usually is public_html or ht_docs. 
    The data is sent from the app using POST method. The Post data is an array of key-value pairs. (eg. (Username,xyz) where Username is the key and xyz is the actual value of it.
    So the key sent from the java code should match the ke in the phpscript. i.e. they should be similar.
    The php script interacts with the database using sql commands the where the variablr use the sql statement are nothing but keys recieved using POST method.
4. After the scripts are done. Try and run them to chech the errors by opening the link www.YOURLINK.com/<file_name>.php.
5. Reprogram the code as per your needs.
6. The app requires an internet connection. Turn on the internet and start registering.
