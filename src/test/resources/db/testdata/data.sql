-- MariaDB dump 10.19-11.3.2-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: anyquizaidb
-- ------------------------------------------------------
-- Server version	11.3.2-MariaDB-1:11.3.2+maria~ubu2204

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `answer`
--

SET foreign_key_checks = 0;

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES
(11,10,'Electrical Engineering','C'),
(12,10,'Chemical Engineering','D'),
(13,10,'Civil Engineering','B'),
(14,10,'Mechanical Engineering','A'),
(15,11,'Deconstruction','A'),
(16,11,'Destruction','B'),
(17,11,'Manufacturing','D'),
(18,11,'Construction','C'),
(19,12,'Mining Engineering','B'),
(20,12,'Software Engineering','A'),
(21,12,'Biotechnology Engineering','C'),
(22,12,'Fashion Design Engineering','D'),
(23,13,'Aerospace Engineering','B'),
(24,13,'Computer Engineering','D'),
(25,13,'Civil Engineering','C'),
(26,13,'Biomedical Engineering','A'),
(27,14,'Computer Engineering','D'),
(28,14,'Electrical Engineering','A'),
(29,14,'Mechanical Engineering','B'),
(30,14,'Chemical Engineering','C'),
(31,15,'To create art installations','D'),
(32,15,'To grow vegetables','A'),
(33,15,'To provide shelter for animals','B'),
(34,15,'To connect two points over an obstacle','C'),
(35,16,'Writing novels','C'),
(36,16,'Designing buildings','A'),
(37,16,'Solving problems','D'),
(38,16,'Cooking meals','B'),
(39,17,'Cooking','B'),
(40,17,'Gardening','C'),
(41,17,'Painting','D'),
(42,17,'Engineering','A'),
(43,18,'Science, Technology, Engineering, and Mathematics','B'),
(44,18,'Studying Technology and Engineering Majors','A'),
(45,18,'Sailing to Explore Mars','C'),
(46,18,'Solving Technical Engineering Mysteries','D'),
(47,19,'Petroleum Engineering','D'),
(48,19,'Aerospace Engineering','A'),
(49,19,'Biomedical Engineering','B'),
(50,19,'Environmental Engineering','C'),
(51,20,'Atlantic Ocean','A'),
(52,20,'Indian Ocean','C'),
(53,20,'Southern Ocean','D'),
(54,20,'Pacific Ocean','B'),
(55,21,'Arctic Ocean','D'),
(56,21,'Atlantic Ocean','B'),
(57,21,'Pacific Ocean','A'),
(58,21,'Indian Ocean','C'),
(59,22,'Pacific Ocean','B'),
(60,22,'Indian Ocean','C'),
(61,22,'Arctic Ocean','D'),
(62,22,'Atlantic Ocean','A'),
(63,23,'Indian Ocean','C'),
(64,23,'Pacific Ocean','A'),
(65,23,'Atlantic Ocean','B'),
(66,23,'Southern Ocean','D'),
(67,24,'Pacific Ocean','A'),
(68,24,'Southern Ocean','D'),
(69,24,'Indian Ocean','C'),
(70,24,'Atlantic Ocean','B'),
(71,25,'Pacific Ocean','B'),
(72,25,'Arctic Ocean','D'),
(73,25,'Indian Ocean','C'),
(74,25,'Atlantic Ocean','A'),
(75,26,'Southern Ocean','D'),
(76,26,'Indian Ocean','C'),
(77,26,'Pacific Ocean','B'),
(78,26,'Arctic Ocean','A'),
(79,27,'Arctic Ocean','D'),
(80,27,'Pacific Ocean','A'),
(81,27,'Indian Ocean','C'),
(82,27,'Atlantic Ocean','B'),
(83,28,'Southern Ocean','D'),
(84,28,'Atlantic Ocean','B'),
(85,28,'Pacific Ocean','A'),
(86,28,'Indian Ocean','C'),
(87,29,'Southern Ocean','D'),
(88,29,'Pacific Ocean','B'),
(89,29,'Indian Ocean','A'),
(90,29,'Atlantic Ocean','C'),
(91,30,'Tiger','A'),
(92,30,'Leopard','D'),
(93,30,'Jaguar','B'),
(94,30,'Lion','C'),
(95,31,'Cheetah','A'),
(96,31,'Lion','B'),
(97,31,'Jaguar','C'),
(98,31,'Leopard','D'),
(99,32,'Tiger','B'),
(100,32,'Lion','C'),
(101,32,'Leopard','A'),
(102,32,'Cheetah','D'),
(103,33,'Lion','A'),
(104,33,'Leopard','B'),
(105,33,'Tiger','C'),
(106,33,'Cheetah','D'),
(107,34,'Jaguar','A'),
(108,34,'Lion','C'),
(109,34,'Tiger','B'),
(110,34,'Cheetah','D'),
(111,35,'Strawberry','B'),
(112,35,'Cherry','D'),
(113,35,'Watermelon','C'),
(114,35,'Pineapple','A'),
(115,36,'Coconut','C'),
(116,36,'Plum','B'),
(117,36,'Kiwi','D'),
(118,36,'Pear','A'),
(119,37,'Pomegranate','B'),
(120,37,'Peach','D'),
(121,37,'Kiwi','A'),
(122,37,'Mango','C'),
(123,38,'Blueberry','A'),
(124,38,'Raspberry','B'),
(125,38,'Blackberry','C'),
(126,38,'Grape','D'),
(127,39,'Orange','D'),
(128,39,'Banana','B'),
(129,39,'Grapes','C'),
(130,39,'Apple','A'),
(131,40,'Mule','A'),
(132,40,'Slip-on','B'),
(133,40,'Oxford','C'),
(134,40,'Clog','D'),
(135,41,'Reebok','D'),
(136,41,'Nike','A'),
(137,41,'Adidas','B'),
(138,41,'Puma','C'),
(139,42,'Wedges','B'),
(140,42,'Heels','D'),
(141,42,'Platforms','C'),
(142,42,'Flats','A'),
(143,43,'Flip-flop','C'),
(144,43,'Sneaker','A'),
(145,43,'Boot','D'),
(146,43,'Loafer','B'),
(147,44,'Leather','A'),
(148,44,'Mesh','C'),
(149,44,'Rubber','D'),
(150,44,'Canvas','B'),
(151,45,'Joseph Stalin','A'),
(152,45,'Leon Trotsky','C'),
(153,45,'Mikhail Gorbachev','D'),
(154,45,'Vladimir Lenin','B'),
(155,46,'Christopher Columbus','A'),
(156,46,'Vasco da Gama','C'),
(157,46,'Amerigo Vespucci','D'),
(158,46,'Marco Polo','B'),
(159,47,'Attack on Pearl Harbor','A'),
(160,47,'Invasion of Poland','B'),
(161,47,'Battle of Stalingrad','C'),
(162,47,'D-Day','D'),
(163,48,'Margaret Thatcher','A'),
(164,48,'Queen Elizabeth II','B'),
(165,48,'Theresa May','D'),
(166,48,'Angela Merkel','C'),
(167,49,'World War I','B'),
(168,49,'Korean War','D'),
(169,49,'World War II','A'),
(170,49,'Vietnam War','C'),
(171,50,'Egypt','C'),
(172,50,'Rome','B'),
(173,50,'China','D'),
(174,50,'Greece','A'),
(175,51,'Karl Marx','A'),
(176,51,'Vladimir Lenin','C'),
(177,51,'Mao Zedong','D'),
(178,51,'Friedrich Engels','B'),
(179,52,'Julius Caesar','A'),
(180,52,'Caligula','D'),
(181,52,'Augustus','B'),
(182,52,'Nero','C'),
(183,53,'Romans','B'),
(184,53,'Mayans','A'),
(185,53,'Egyptians','D'),
(186,53,'Greeks','C'),
(187,54,'John Adams','D'),
(188,54,'Thomas Jefferson','B'),
(189,54,'Abraham Lincoln','C'),
(190,54,'George Washington','A'),
(191,55,'Butterfly','D'),
(192,55,'Grasshopper','B'),
(193,55,'Ant','C'),
(194,55,'Fruit fly','A'),
(195,56,'Monarch','A'),
(196,56,'Atlas Moth','C'),
(197,56,'Blue Morpho','B'),
(198,56,'Swallowtail','D'),
(199,57,'Grasshopper','D'),
(200,57,'Cockroach','A'),
(201,57,'Cicada','B'),
(202,57,'Beetle','C'),
(203,58,'Protection','A'),
(204,58,'Digestion','B'),
(205,58,'Reproduction','C'),
(206,58,'Photosynthesis','D'),
(207,59,'Firefly','B'),
(208,59,'Ant','D'),
(209,59,'Ladybug','A'),
(210,59,'Grasshopper','C'),
(211,60,'Butterfly','A'),
(212,60,'Bee','D'),
(213,60,'Mosquito','B'),
(214,60,'Beetle','C'),
(215,61,'Dragonfly','C'),
(216,61,'Bee','D'),
(217,61,'Ant','A'),
(218,61,'Water Strider','B'),
(219,62,'Nectar','A'),
(220,62,'Leaves','B'),
(221,62,'Fruits','D'),
(222,62,'Other insects','C'),
(223,63,'Digestion','C'),
(224,63,'Reproduction','D'),
(225,63,'Sensory perception','A'),
(226,63,'Breathing','B'),
(227,64,'1-2 weeks','A'),
(228,64,'6-12 months','C'),
(229,64,'2-3 years','D'),
(230,64,'1-2 months','B'),
(231,65,'Maggie','A'),
(232,65,'Meg','C'),
(233,65,'Lisa','B'),
(234,65,'Bart','D'),
(235,66,'Bob','C'),
(236,66,'Stan','D'),
(237,66,'Homer','A'),
(238,66,'Peter','B'),
(239,67,'Arlen','C'),
(240,67,'Springfield','A'),
(241,67,'Quahog','B'),
(242,67,'South Park','D'),
(243,68,'Ned','B'),
(244,68,'Apu','A'),
(245,68,'Moe','C'),
(246,68,'Barney','D'),
(247,69,'Snoopy','C'),
(248,69,'Santa\'s Little Helper','A'),
(249,69,'Brian','B'),
(250,69,'Scooby-Doo','D'),
(251,70,'Family Guy','B'),
(252,70,'King of the Hill','D'),
(253,70,'The Simpsons','A'),
(254,70,'Futurama','C'),
(255,71,'Family Guy','B'),
(256,71,'South Park','C'),
(257,71,'The Simpsons','A'),
(258,71,'American Dad!','D'),
(259,72,'Flexo','B'),
(260,72,'Clamps','C'),
(261,72,'Roberto','D'),
(262,72,'Bender','A'),
(263,73,'Luanne','B'),
(264,73,'Nancy','C'),
(265,73,'Peggy','A'),
(266,73,'Connie','D'),
(267,74,'Jerry','B'),
(268,74,'Summer','C'),
(269,74,'Beth','D'),
(270,74,'Morty','A'),
(271,75,'Bob\'s Burgers','A'),
(272,75,'Rick and Morty','C'),
(273,75,'Archer','D'),
(274,75,'The Cleveland Show','B'),
(275,76,'Santa\'s Little Helper','B'),
(276,76,'Snoopy','C'),
(277,76,'Brian','A'),
(278,76,'Pluto','D'),
(279,77,'The Cleveland Show','B'),
(280,77,'Rick and Morty','C'),
(281,77,'Archer','D'),
(282,77,'Bob\'s Burgers','A'),
(283,78,'The Simpsons','A'),
(284,78,'South Park','C'),
(285,78,'American Dad!','D'),
(286,78,'Family Guy','B'),
(287,79,'The Simpsons','A'),
(288,79,'Family Guy','B'),
(289,79,'South Park','C'),
(290,79,'American Dad!','D'),
(291,80,'Morocco','A'),
(292,80,'Ethiopia','C'),
(293,80,'Egypt','D'),
(294,80,'Algeria','B'),
(295,81,'Zimbabwe','B'),
(296,81,'Tanzania','D'),
(297,81,'Kenya','C'),
(298,81,'Botswana','A'),
(299,82,'Uganda','B'),
(300,82,'Tanzania','C'),
(301,82,'Namibia','D'),
(302,82,'Rwanda','A'),
(303,83,'Ghana','D'),
(304,83,'Nigeria','A'),
(305,83,'South Africa','B'),
(306,83,'Kenya','C'),
(307,84,'Morocco','A'),
(308,84,'Mali','B'),
(309,84,'Egypt','D'),
(310,84,'Tanzania','C'),
(311,85,'South Africa','C'),
(312,85,'Tanzania','B'),
(313,85,'Ghana','A'),
(314,85,'Botswana','D'),
(315,86,'Spanish','C'),
(316,86,'English','D'),
(317,86,'Portuguese','A'),
(318,86,'French','B'),
(319,87,'Namib Desert','C'),
(320,87,'Kalahari Desert','B'),
(321,87,'Sahara Desert','A'),
(322,87,'Libyan Desert','D'),
(323,88,'Rwanda','B'),
(324,88,'Uganda','D'),
(325,88,'Kenya','A'),
(326,88,'Tanzania','C'),
(327,89,'Uganda','B'),
(328,89,'Kenya','A'),
(329,89,'Tanzania','C'),
(330,89,'Ethiopia','D'),
(331,90,'Dinar','A'),
(332,90,'Pound','B'),
(333,90,'Shilling','D'),
(334,90,'Rial','C'),
(335,91,'Mount Meru','D'),
(336,91,'Mount Kenya','B'),
(337,91,'Mount Kilimanjaro','A'),
(338,91,'Mount Elgon','C'),
(339,92,'Cairo','D'),
(340,92,'Lagos','A'),
(341,92,'Abuja','B'),
(342,92,'Accra','C'),
(343,93,'South Africa','A'),
(344,93,'Nigeria','B'),
(345,93,'Ghana','C'),
(346,93,'Ethiopia','D'),
(347,94,'Kenya','A'),
(348,94,'Ethiopia','D'),
(349,94,'Tanzania','B'),
(350,94,'Uganda','C'),
(351,95,'Manatee','D'),
(352,95,'Lobster','B'),
(353,95,'Clownfish','A'),
(354,95,'Cuttlefish','C'),
(355,96,'Blue whale','C'),
(356,96,'Seal','B'),
(357,96,'Penguin','A'),
(358,96,'Starfish','D'),
(359,97,'Shark','C'),
(360,97,'Dolphin','B'),
(361,97,'Octopus','A'),
(362,97,'Sea turtle','D'),
(363,98,'Whale','B'),
(364,98,'Sea anemone','A'),
(365,98,'Seahorse','D'),
(366,98,'Jellyfish','C'),
(367,99,'Angelfish','C'),
(368,99,'Squid','B'),
(369,99,'Eel','D'),
(370,99,'Crab','A'),
(371,100,'16 ounces','D'),
(372,100,'128 ounces','C'),
(373,100,'32 ounces','A'),
(374,100,'64 ounces','B'),
(375,101,'Energy production','A'),
(376,101,'Immune system support','D'),
(377,101,'Muscle growth','B'),
(378,101,'Digestive health','C'),
(379,102,'Potassium','C'),
(380,102,'Calcium','B'),
(381,102,'Iron','A'),
(382,102,'Zinc','D'),
(383,103,'Protein','A'),
(384,103,'Fat','C'),
(385,103,'Carbohydrates','B'),
(386,103,'Vitamins','D'),
(387,104,'Vitamin C','B'),
(388,104,'Vitamin A','A'),
(389,104,'Vitamin K','D'),
(390,104,'Vitamin D','C'),
(391,105,'David Rudisha','C'),
(392,105,'Mo Farah','B'),
(393,105,'Eliud Kipchoge','D'),
(394,105,'Usain Bolt','A'),
(395,106,'Masai Mara','C'),
(396,106,'Etosha','D'),
(397,106,'Serengeti','A'),
(398,106,'Kruger','B'),
(399,107,'Elephant','A'),
(400,107,'Zebra','D'),
(401,107,'Lion','B'),
(402,107,'Giraffe','C'),
(403,108,'Accra','B'),
(404,108,'Nairobi','A'),
(405,108,'Cairo','D'),
(406,108,'Lagos','C'),
(407,109,'French','C'),
(408,109,'English','B'),
(409,109,'Arabic','D'),
(410,109,'Swahili','A'),
(411,110,'Lip liner','D'),
(412,110,'Beauty blender','B'),
(413,110,'Eyelash curler','A'),
(414,110,'Tweezers','C'),
(415,111,'Braids','A'),
(416,111,'Ponytail','B'),
(417,111,'Top knot','D'),
(418,111,'Bangs','C'),
(419,112,'To enhance eyelashes','C'),
(420,112,'To moisturize lips','A'),
(421,112,'To add color to cheeks','B'),
(422,112,'To shape eyebrows','D'),
(423,113,'Hair straightener','C'),
(424,113,'Hair oil','B'),
(425,113,'Hair spray','A'),
(426,113,'Dry shampoo','D'),
(427,114,'To hydrate the skin','A'),
(428,114,'To add color to the skin','C'),
(429,114,'To remove makeup','D'),
(430,114,'To create a smooth base for makeup application','B'),
(431,115,'Flat-Top Brush','D'),
(432,115,'Angled Brush','C'),
(433,115,'Fan Brush','A'),
(434,115,'Kabuki Brush','B'),
(435,116,'Hair Straightener','D'),
(436,116,'Teasing Comb','B'),
(437,116,'Curling Wand','A'),
(438,116,'Hair Dryer','C'),
(439,117,'Eyeshadow Technique','A'),
(440,117,'Blush Application','C'),
(441,117,'Foundation Mixing','D'),
(442,117,'Lip Contouring','B'),
(443,118,'Brow Gel','C'),
(444,118,'Eyeliner','B'),
(445,118,'Mascara','A'),
(446,118,'Brow Pencil','D'),
(447,119,'Add Color','A'),
(448,119,'Hydrate Skin','B'),
(449,119,'Prep Skin','C'),
(450,119,'Remove Makeup','D'),
(451,120,'Highlighter','B'),
(452,120,'Bronzer','A'),
(453,120,'Setting Powder','C'),
(454,120,'Eyeliner','D'),
(455,121,'Lengthen Eyelashes','D'),
(456,121,'Create Definition','B'),
(457,121,'Remove Makeup','C'),
(458,121,'Moisturize Lips','A'),
(459,122,'Texturizer','B'),
(460,122,'Heat Protectant','C'),
(461,122,'Hair Color','A'),
(462,122,'Setting Spray','D'),
(463,123,'Crimper','C'),
(464,123,'Paddle Brush','D'),
(465,123,'Flat Iron','A'),
(466,123,'Hot Rollers','B'),
(467,124,'Highlighting Technique','C'),
(468,124,'Temporary Color Spray','D'),
(469,124,'All-Over Color','A'),
(470,124,'Ombre Effect','B'),
(471,125,'Landing','D'),
(472,125,'Cruise','B'),
(473,125,'Takeoff','A'),
(474,125,'Descent','C'),
(475,126,'Weather conditions','C'),
(476,126,'Pilot error','A'),
(477,126,'Passenger interference','D'),
(478,126,'Mechanical failure','B'),
(479,127,'Front of the plane','B'),
(480,127,'Window seat','D'),
(481,127,'Back of the plane','C'),
(482,127,'Middle of the plane','A'),
(483,128,'Military plane','C'),
(484,128,'Helicopter','A'),
(485,128,'Private plane','D'),
(486,128,'Commercial jet','B'),
(487,129,'To record flight data','A'),
(488,129,'To control the plane remotely','D'),
(489,129,'To send distress signals','B'),
(490,129,'To provide emergency oxygen','C'),
(491,130,'Team','B'),
(492,130,'Royals','A'),
(493,130,'Green Light','C'),
(494,130,'Homemade Dynamite','D'),
(495,131,'25','B'),
(496,131,'21','A'),
(497,131,'19','C'),
(498,131,'Lemonade','D'),
(499,132,'Katy Perry','A'),
(500,132,'Lady Gaga','D'),
(501,132,'Rihanna','B'),
(502,132,'Beyoncé','C'),
(503,133,'Can\'t Stop the Feeling!','D'),
(504,133,'Shake It Off','C'),
(505,133,'Uptown Funk','A'),
(506,133,'Happy','B'),
(507,134,'Ariana Grande','C'),
(508,134,'Justin Bieber','B'),
(509,134,'Taylor Swift','D'),
(510,134,'Ed Sheeran','A'),
(511,135,'Lil Wayne','A'),
(512,135,'Lil Pump','C'),
(513,135,'Lil Yachty','D'),
(514,135,'Lil Uzi Vert','B'),
(515,136,'Eminem','D'),
(516,136,'Jay-Z','C'),
(517,136,'Kanye West','B'),
(518,136,'Drake','A'),
(519,137,'The Weeknd','C'),
(520,137,'Pharrell Williams','A'),
(521,137,'Mark Ronson','D'),
(522,137,'Ed Sheeran','B'),
(523,138,'Dwayne Carter','A'),
(524,138,'Rakim Mayers','B'),
(525,138,'Kendrick Duckworth','C'),
(526,138,'Shawn Carter','D'),
(527,139,'OutKast','A'),
(528,139,'Macklemore & Ryan Lewis','B'),
(529,139,'Run the Jewels','C'),
(530,139,'Rae Sremmurd','D'),
(531,140,'Kendrick Lamar','D'),
(532,140,'Kanye West','A'),
(533,140,'J. Cole','B'),
(534,140,'Drake','C'),
(535,141,'Jay-Z','C'),
(536,141,'Snoop Dogg','A'),
(537,141,'Nas','D'),
(538,141,'Eminem','B'),
(539,142,'Calvin Broadus','B'),
(540,142,'Andre Young','A'),
(541,142,'Tupac Shakur','D'),
(542,142,'Marshall Mathers','C'),
(543,143,'The Notorious B.I.G.','D'),
(544,143,'Wu-Tang Clan','B'),
(545,143,'Public Enemy','C'),
(546,143,'N.W.A.','A'),
(547,144,'Kanye West','B'),
(548,144,'Travis Scott','A'),
(549,144,'Drake','C'),
(550,144,'Kendrick Lamar','D');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES
(1,8,'What do cows eat?','A'),
(2,8,'What do cows drink?','A'),
(3,8,'What sound do cows make?','A'),
(4,8,'How many stomachs do cows have?','C'),
(5,8,'What is a baby cow called?','C'),
(10,11,'Which type of engineering deals with electrical circuits?','C'),
(11,11,'What is the term for the process of converting raw materials into finished products?','D'),
(12,11,'Which engineering field deals with the extraction of natural resources?','B'),
(13,11,'Which engineering discipline focuses on the design and construction of roads and bridges?','C'),
(14,11,'Which engineering field focuses on the design and production of computer hardware?','D'),
(15,11,'What is the main purpose of a bridge in civil engineering?','C'),
(16,11,'What is the primary role of an engineer?','D'),
(17,11,'What is the term for the application of scientific principles to design or develop structures, machines, etc.?','A'),
(18,11,'What does the acronym \'STEM\' stand for in the context of education?','B'),
(19,11,'Which engineering discipline focuses on designing aircraft and spacecraft?','A'),
(20,12,'Which ocean borders the east coast of the United States?','A'),
(21,12,'Which ocean is known for its strong El Niño and La Niña weather patterns?','A'),
(22,12,'Which ocean is the saltiest ocean on Earth?','A'),
(23,12,'Which ocean is known for the Galapagos Islands?','A'),
(24,12,'Which ocean is known for the Great Barrier Reef?','A'),
(25,12,'Which ocean is home to the Bermuda Triangle?','A'),
(26,12,'Which ocean is surrounded by the Arctic Circle?','A'),
(27,12,'What is the largest ocean on Earth?','A'),
(28,12,'What is the deepest ocean on Earth?','A'),
(29,12,'Which ocean is located between Africa and Australia?','A'),
(30,13,'Which big cat is known for its mane and is referred to as the \'king of the jungle\'?','C'),
(31,13,'Which big cat is well-known for its speed and agility?','A'),
(32,13,'Which big cat is known for its ability to swim and love for water?','A'),
(33,13,'Which big cat is known for its black rosettes on a golden-yellow coat?','B'),
(34,13,'Which big cat is the largest in the world?','B'),
(35,14,'Which fruit has a red skin, is often used in pies, and is a symbol of love?','D'),
(36,14,'Which fruit is known for its fuzzy brown skin and sweet green flesh?','D'),
(37,14,'Which fruit is green on the outside, red on the inside, and has many black seeds?','B'),
(38,14,'Which fruit is small, round, and grows in clusters on a vine?','D'),
(39,14,'Which fruit is known for its yellow peel and is a good source of vitamin C?','D'),
(40,15,'Which shoe style features a lace-up design?','C'),
(41,15,'Which brand is known for the Air Jordan line of sneakers?','A'),
(42,15,'What are shoes with a raised heel called?','D'),
(43,15,'What type of shoe is typically worn for formal occasions?','B'),
(44,15,'Which material is commonly used to make running shoes?','C'),
(45,16,'Which leader is associated with the Bolshevik Revolution in Russia?','B'),
(46,16,'Who is known for discovering America in 1492?','A'),
(47,16,'Which event marked the beginning of World War II?','B'),
(48,16,'Who was the first female Prime Minister of the United Kingdom?','A'),
(49,16,'Which war ended with the signing of the Treaty of Versailles?','B'),
(50,16,'Which country was ruled by the Pharaohs?','C'),
(51,16,'Who wrote \'The Communist Manifesto\'?','A'),
(52,16,'Who was the first Emperor of Rome?','B'),
(53,16,'Which ancient civilization built the pyramids?','D'),
(54,16,'Who was the first President of the United States?','A'),
(55,17,'Which insect is capable of carrying objects many times its body weight?','C'),
(56,17,'What is the largest species of butterfly in the world?','C'),
(57,17,'Which insect is known for its distinctive chirping sound produced by rubbing its wings together?','B'),
(58,17,'What is the purpose of an insect\'s exoskeleton?','A'),
(59,17,'Which insect is known for its ability to produce light through bioluminescence?','B'),
(60,17,'Which insect is known for its ability to pollinate flowers and crops?','D'),
(61,17,'Which insect is known for its ability to walk on water due to surface tension?','B'),
(62,17,'What is the main diet of a praying mantis?','C'),
(63,17,'What is the primary function of an insect\'s antennae?','A'),
(64,17,'What is the lifespan of a worker bee during the summer months?','A'),
(65,18,'Who is the youngest daughter in The Simpsons?','A'),
(66,18,'What is the name of the father in The Simpsons?','A'),
(67,18,'What is the name of the town where The Simpsons live?','A'),
(68,18,'Who is the owner of the Kwik-E-Mart in The Simpsons?','A'),
(69,18,'What is the name of the family dog in The Simpsons?','A'),
(70,19,'In which show would you find characters named Stewie and Brian?','B'),
(71,19,'Which American cartoon sitcom features a character named Peter Griffin?','B'),
(72,19,'In \'Futurama\', what is the name of the robot who works as a bending unit?','A'),
(73,19,'In \'King of the Hill\', what is the name of Hank Hill\'s wife?','A'),
(74,19,'In \'Rick and Morty\', what is the name of Rick\'s grandson?','A'),
(75,19,'Which cartoon sitcom focuses on the Belcher family who run a burger restaurant?','A'),
(76,19,'What is the name of the talking dog in \'Family Guy\'?','A'),
(77,19,'Which show features the character Linda Belcher?','A'),
(78,19,'Which show features the character Principal Shepherd?','B'),
(79,19,'Which show features the character Randy Marsh?','C'),
(80,20,'Which country is known for the Great Pyramid of Giza?','D'),
(81,20,'Which country is famous for the Serengeti National Park?','D'),
(82,20,'Which country is home to the Maasai tribe?','C'),
(83,20,'Which country is known as the \'Rainbow Nation\'?','B'),
(84,20,'Which country is famous for the ancient city of Timbuktu?','B'),
(85,21,'Which African country is known as the \'Rainbow Nation\'?','C'),
(86,21,'What is the official language of Angola?','A'),
(87,21,'What is the largest desert in Africa?','A'),
(88,21,'Which African country is known as the \'Land of a Thousand Hills\'?','B'),
(89,21,'Which African country is known as the \'Pearl of Africa\'?','B'),
(90,21,'What is the currency of Egypt?','B'),
(91,21,'What is the highest mountain in Africa?','A'),
(92,21,'What is the capital city of Nigeria?','B'),
(93,21,'Which African country is known as the \'Giant of Africa\'?','B'),
(94,21,'Which African country is home to the Maasai people?','A'),
(95,22,'Which sea creature has a long, slender body and can change color to blend in with its surroundings?','C'),
(96,22,'Which sea creature is the largest mammal on Earth?','C'),
(97,22,'Which sea creature has eight legs and two pinchers?','A'),
(98,22,'What sea creature is known for its colorful tentacles and stinging cells?','C'),
(99,22,'What sea creature has a hard shell and can retract into it for protection?','A'),
(100,23,'What is the recommended daily intake of water in ounces for adults?','B'),
(101,23,'What is the main function of fiber in the diet?','C'),
(102,23,'Which mineral is important for strong bones and teeth?','B'),
(103,23,'Which nutrient is known as the body\'s main source of energy?','B'),
(104,23,'Which vitamin is commonly found in citrus fruits?','B'),
(105,24,'Which famous Kenyan long-distance runner won multiple Olympic gold medals?','D'),
(106,24,'Which of the following is a famous national park in Kenya?','C'),
(107,24,'Which animal is known as the \'King of the Jungle\' in Kenya?','B'),
(108,24,'What is the capital city of Kenya?','A'),
(109,24,'What is the official language of Kenya?','B'),
(110,25,'Which tool is commonly used to apply foundation?','B'),
(111,25,'What type of hairstyle involves twisting sections of hair together?','A'),
(112,25,'What is the purpose of mascara in makeup?','C'),
(113,25,'Which product is used to add volume to hair?','A'),
(114,25,'What is the purpose of primer in makeup?','B'),
(115,26,'Which makeup brush is typically used for applying foundation?','B'),
(116,26,'Which hair tool is used to create volume at the roots?','B'),
(117,26,'What does the term \'cut crease\' refer to in makeup?','A'),
(118,26,'Which makeup product is used to fill in eyebrows?','D'),
(119,26,'What is the main purpose of a primer in makeup application?','C'),
(120,26,'Which makeup product is used to set foundation and concealer?','C'),
(121,26,'What is the purpose of a lip liner?','B'),
(122,26,'What type of product is \'mousse\' in hair styling?','B'),
(123,26,'Which hair styling tool is used to create curls?','B'),
(124,26,'What does the term \'balayage\' refer to in hair coloring?','C'),
(125,27,'Which phase of a flight is the most dangerous in terms of accidents?','D'),
(126,27,'What is the most common cause of plane crashes?','A'),
(127,27,'Which is the safest seat on a plane during a crash?','C'),
(128,27,'Which type of aircraft has the best safety record?','B'),
(129,27,'What is the purpose of the black box on an airplane?','A'),
(130,28,'What is the title of the debut single by Lorde released in 2013?','A'),
(131,28,'What is the title of Adele\'s album released in 2015?','B'),
(132,28,'Who is the singer of the song \'Roar\' released in 2013?','A'),
(133,28,'Which song by Pharrell Williams was a huge hit in 2014?','B'),
(134,28,'Which artist released the hit song \'Shape of You\' in 2017?','A'),
(135,29,'What is the stage name of the rapper born Symere Bysil Woods?','B'),
(136,29,'Which artist released the song \'Hotline Bling\' in 2015?','A'),
(137,29,'Which artist collaborated with Bruno Mars on the song \'Uptown Funk\' in 2014?','D'),
(138,29,'What is the real name of the rapper Kendrick Lamar?','C'),
(139,29,'Which duo released the hit song \'Thrift Shop\' in 2012?','B'),
(140,30,'Which hip-hop artist is known for the album \'Good Kid, M.A.A.D City\'?','D'),
(141,30,'Which rapper is known for the hit song \'Lose Yourself\' from the \'8 Mile\' soundtrack?','B'),
(142,30,'What is the real name of the rapper who goes by the stage name \'2Pac\'?','D'),
(143,30,'Which rap group released the influential album \'Straight Outta Compton\'?','A'),
(144,30,'Which rapper released the album \'To Pimp a Butterfly\' in 2015?','D');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES
(1,'eblzwK',18,'Easy Cow Quiz',5,'/var/quiz_photos/1.png','EASY','2024-05-26 19:05:28'),
(2,'87nfJl',18,'Dog Breeds Quiz',10,'/var/quiz_photos/2.png','EASY','2024-05-26 22:53:50'),
(3,'vWzFs1',11,'Basic Computer Knowledge Quiz',10,'/var/quiz_photos/3.png','EASY','2024-05-26 23:10:39'),
(4,'qkBem6',18,'Phone Knowledge Quiz',10,'/var/quiz_photos/4.png','EASY','2024-05-26 23:35:59'),
(5,'zDld8K',18,'Famous Computer Pioneers Quiz',10,'/var/quiz_photos/5.png','EASY','2024-05-26 23:39:59'),
(6,'kh9HDH',1,'Planes Quiz',5,'/var/quiz_photos/6.png','EASY','2024-06-02 17:00:35'),
(7,'hFkX4r',18,'Easy Cow Quiz',5,'/var/quiz_photos/7.png','EASY','2024-06-02 18:11:53'),
(8,'CVieXR',18,'Easy Cow Quiz',5,'/var/quiz_photos/8.png','EASY','2024-06-02 18:15:00'),
(11,'FmqVqU',18,'Engineering Basics Quiz',10,'/var/quiz_photos/11.png','EASY','2024-06-02 18:27:19'),
(12,'uLNGp0',1,'Ocean Quiz',10,'/var/quiz_photos/12.png','EASY','2024-06-02 18:32:12'),
(13,'mzIdXV',1,'Big Cats Quiz',5,'/var/quiz_photos/13.png','EASY','2024-06-02 18:39:56'),
(14,'QnF6GO',1,'Fruit Quiz',5,'/var/quiz_photos/14.png','EASY','2024-06-16 16:02:50'),
(15,'28G0Ru',4,'Shoes Quiz',5,'/var/quiz_photos/15.png','EASY','2024-06-16 16:11:46'),
(16,'PsBZii',7,'World History Quiz',20,'/var/quiz_photos/16.png','EASY','2024-06-16 18:25:30'),
(17,'2h8wGS',1,'Insect Quiz',10,'/var/quiz_photos/17.png','MEDIUM','2024-06-16 18:31:02'),
(18,'Vkpt4f',7,'The Simpsons Easy Quiz',5,'/var/quiz_photos/18.png','EASY','2024-06-16 18:53:32'),
(19,'Qu1MsB',6,'American Cartoon Sitcoms Quiz',10,'/var/quiz_photos/19.png','HARD','2024-06-16 18:56:51'),
(20,'QcxNKq',18,'African Countries Quiz',5,'/var/quiz_photos/20.png','EASY','2024-07-06 19:32:52'),
(21,'soul6I',18,'African Countries Quiz',10,'/var/quiz_photos/21.png','MEDIUM','2024-07-06 19:37:11'),
(22,'5gOzxG',18,'Sea Creatures Quiz',5,'/var/quiz_photos/22.png','EASY','2024-07-06 19:40:49'),
(23,'iTsKGh',9,'Nutrition Quiz',5,'/var/quiz_photos/23.png','EASY','2024-07-06 20:24:30'),
(24,'4tq4AE',7,'Kenya Quiz',5,'/var/quiz_photos/24.png','EASY','2024-07-06 23:28:43'),
(25,'5UZMi8',18,'Hair and Makeup Quiz',5,'/var/quiz_photos/25.png','EASY','2024-07-06 23:40:56'),
(26,'PHat0t',18,'Hard Hair and Makeup Quiz',10,'/var/quiz_photos/26.png','HARD','2024-07-06 23:45:24'),
(27,'P9dA8k',18,'Plane Crashes Quiz',5,'/var/quiz_photos/27.png','EASY','2024-07-06 23:51:23'),
(28,'Al7Z90',16,'Pop Music from 2010s Quiz',5,'/var/quiz_photos/28.png','EASY','2024-08-01 18:46:36'),
(29,'40Byht',16,'Hip Hop Music of the 2010s Quiz',5,'/var/quiz_photos/29.png','EASY','2024-08-01 18:52:18'),
(30,'NDppxA',16,'Hard Rap Music Quiz',5,'/var/quiz_photos/30.png','HARD','2024-08-01 18:53:07');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES
(1,13,'au','chol',1,0.2),
(2,13,'af','Mann',3,0.6),
(3,13,'af','choln',1,0.2),
(4,13,'af','cholnl',3,0.6),
(5,13,'af','cholnll',0,0),
(6,13,'af','cholnlllh',2,0.4),
(7,13,'af','cholnlllhh',0,0),
(8,13,'af','cholnlllhh777',1,0.2),
(9,13,'au','Joe',5,1),
(10,13,'au','Joej',2,0.4),
(11,13,'dz','kkk',1,0.2),
(15,13,'gt','kkkk',2,0.4),
(17,14,'au','cholx',5,1),
(18,15,'au','Joe X',5,1),
(19,16,'au','chol20',9,0.45),
(20,17,'af','nlnjnkj',0,0),
(21,18,'au','Cholxa',5,1),
(22,18,'af','kjbbih',0,0),
(23,19,'au','Another0ne',9,0.9),
(24,18,'dz','asdsad',4,0.8),
(35,18,'af','asd',5,1),
(36,18,'aq','penguin',5,1),
(37,18,'dz','noone',5,1),
(38,18,'au','boss',5,1),
(39,20,'au','ChuckNorris',5,1),
(40,23,'au','Chol',5,1),
(41,23,'ke','Lb',5,1),
(42,25,'dz','xcx',3,0.6),
(43,26,'as','mn',7,0.7),
(44,28,'au','chol101',3,0.6),
(45,18,'au','MrBlue',5,1),
(46,30,'af','AnotherOne',2,0.4),
(47,30,'au','Nick',1,0.2);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-04  8:36:05
