
----------------------------------------------------------------------------------
City Sites
Tampa Hackathon 2012
Robin Curts <robincurts@gmail.com>

A proof of concept targetted at bringing city services and information into the 
hands of Tampa residents and visitors when they need it most.

----------------------------------------------------------------------------------

This web application is essentially an entry point to city of tampa services that
can be accessed by QR codes. These QR codes would be placed all over the city
to allow people to get information about where they are standing. This interaction 
is handled by a City Site.

City Sites are categorized into four types:

Historical: The most basic City Site that could give information about a landmark
or structure.

External: A city site that passes information onto a non City of Tampa website.

Live: A City Site that can display current information about a location via City of 
Tampa RSS feeds. An example could be the meeting agenda at today's city council 
meeting or a list of events coming to the concert hall at Lowry Park.

Functional: A City Site that lets an individual interact with a landmark. An 
example could be a pavilion at a park. Someone could scan the QR code and reserve
it directly from their mobile device, without having to make an extra trip
downtown - allowing them to pay for it right there and be on the Ranger's docket
the next day.

----------------------------------------------------------------------------------

In addition, city sites know about each other, and they can tell someone what other
sites they might find near them. A future concept could be creating historical 
"walking tours" of City Sites, or create a workout routine with City Site data on
exercise equipment along the bayshore, helping our residents track and control
their workouts.

City Sites have the potential for "City Graffiti", though this feature was removed
due to time constraints of the Hackathon. The idea is that users can interact with 
any City Site and leave their mark, be it a photo or just a signature, connecting 
their experiences in Tampa with social media networks around the world.

----------------------------------------------------------------------------------

This application is available at http://tinyurl.com/hackathoncitysites

Because it is meant for mobile devices interacting with QR codes, from your browser
you will have a slightly different experience. When you access the site you will be
presented with a map of tampa showing all City Sites, and two links, one
to the admin and one with demo information about the website.  Note: the demo is 
going to be outside of the scope of the hackathon, so judges might want to ignore
this link and stick to this document.

The admin will show all city sites and allow a city of tampa worker to interact
with each object. The edit button allows a user to update the city site content. The
QR code button will display the QR code that should be used on landmarks around the
city. The map button will just bring up a google map showing the location of the
city site.  By clicking the "View Mobile Site" you can look at what the mobile
device would see, should it access the service via a QR code. Please note that
external City Sites will go directly to the external URL rather than an 
information page hosted by the city.

----------------------------------------------------------------------------------

Technical Details:

This application is being run on Amazon's EC2 cloud. 
It is a Java web application built using the Spring 3 and Hibernate frameworks.
The database is MySQL and stores City Sites and Reservations.
The application is built using Maven 2, and can be built via maven with the 
command: mvn package

