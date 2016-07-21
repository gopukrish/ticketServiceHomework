Ticket Service Installing and Running instructions
==================================================

1. 		Download the Ticket Service homework code from github respository link below

2. 		Assuming JAVA_HOME and M2_HOME environment variables set, go to code downloaded directory.
3. 		Run the command `mvn clean install`. This will clean and build the "ticketservice-1.0.jar" with dependencies in "/target/lib" and properties files in 	
		"/target/resources/ directory"
4.		Copy ticketservice-1.0.jar, /lib and /resources directories to any desired folder
5.		Go to above copied folder in step 4.
6.		Set the Classpath using command `set CLASSPATH=".:./lib/*:./resources/"`
7.		Then to launch the application run the command `java -jar ticketservice-1.0.jar`
8.		Above command should launch the application with options as shown below.

***************** Welcome To Venue Ticket Booking Service *****************

--------------------------------------
Please enter below options : 
1:	Get available seats in venue
2:	Get available seats by Level
3:	Hold seats in any level
4:	Hold seats in specific level
5:	Hold seats by level range
6:	Reserve seats
7:	Quit the application
--------------------------------------


Your input :

9.		Follow the options in menu to go through holding and reserving seats

Assumptions
===========
1.		By default holding seat time is 60 secs, which is configurable in ticketservice.properties . It should provided in unit of Seconds
2.		Reservation is made using seat hold ID, not using customer email id. 
3.		Email provided while holding seats and provided while reserving might not be same, since reservation is done using seat hold ID.
4.		By default minimum thread pool count is assumed as 20, maximum pool count as 30, maximum time out for thread is considered as 120. These are also 
		configurable in properties
5.		Booked data is stored no where as per requirements, so every time application is launched new venue and levels are created
