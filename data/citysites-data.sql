-- MySQL dump 10.13  Distrib 5.5.23, for Win64 (x86)
--
-- Host: localhost    Database: tampagov_citysites
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `citysites`
--

DROP TABLE IF EXISTS `citysites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citysites` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `site_desc` longtext,
  `live_feed` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `site_name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `site_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citysites`
--

LOCK TABLES `citysites` WRITE;
/*!40000 ALTER TABLE `citysites` DISABLE KEYS */;
INSERT INTO `citysites` VALUES ('40289981381b64a001381b64a50b0001','211 North Tampa Street','','Tampa','Calling all developers, coders, hackers, designers, dreamers, doers, thinkers, and problem solvers. Whether you\'re from Tampa or Timbuktu, we are looking for folks who are passionate about using technology to make Tampa a better place to live, work, and play..','https://search.twitter.com/search.atom?q=%23mayorshackathon',27.946223,-82.457672,'Mayor\'s Hackathon 2012','FL','Live','http://www.tampagov.net/information_resources/hackathon/index.asp','33602'),('40289981381b64d601381b72a8670001','401 E Bird St','','Tampa','The Sulphur Springs Tower is a Local Landmark Structure.  It is located at 401 East Bird Street, between the I-275 and Florida Avenue.  Each local landmark has a designation report which provides historical information on the landmark.','',28.0225506,-82.4594749,'Sulphur Springs Tower','FL','Historical','http://www.tampagov.net/dept_Historic_Preservation/information_resources/landmark_structures/sulphur_tower.asp','33604'),('40289981381b64d601381bb0f4930002','1818 East 9th Avenue','','Tampa','Ybor City is a historic neighborhood in Tampa, Florida located just northeast of downtown. It was founded in the 1880s by cigar manufacturers and was populated by thousands of immigrants, mainly from Spain, Cuba, and Italy. For the next 50 years, workers in Ybor City\'s cigar factories would roll millions of cigars annually.','',27.961817,-82.438644,'Ybor City','FL','External','http://www.ybor.org/','33601'),('40289981381b64d601381bb4c8da0003','4810 N Himes Ave','','Tampa','This park is made up of approximately 132 acres of Florida wildlife, offering an enjoyable view. If it isn\'t the land that sparks your interest, it will be the ponds. There are two ponds that fall within the boundaries of the park. The northern pond offers fishing, and some have said that the fishing is what sets Al Lopez Park aside from other parks.','',27.989361,-82.502947,'Al Lopez Park - Pavillion 7','FL','Functional','http://www.tampagov.net/parks_search_webapp/ParkDetail.aspx?nbr=1','33614');
/*!40000 ALTER TABLE `citysites` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-23 23:44:48
