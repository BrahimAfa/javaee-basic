-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table article.articles
CREATE TABLE IF NOT EXISTS `articles` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '0',
  `content` text NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table article.articles: ~5 rows (approximately)
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` (`id`, `title`, `content`) VALUES
	(1, 'How to avoid these 6 mistakes when applying for grants in Singapore', 'Rated number one in the world by the World Bank for ease of doing business, Singapore provides a variety of grants. In its most recent budget, the city-stateâs government amplified its support toward the tech sector through a number of publicly funded schemes.\r\n\r\nStill, many tech companies are currently missing out on the opportunity to benefit from these grants. We take a look at some of the main slip-ups.'),
	(2, 'StashAway banks $5.3m funding, but road to profitability for robo-advisors remains unclear', 'Over the past few years, Singapore has seen the birth of several fintech startups that are using automation and AI to replicate the role of human financial advisors, helping users to preserve and grow their wealth through passive investing.\r\n\r\nWith lower fees than banks and fund managers, these robo-advisors reckon they can make more money for their users than their offline competitors. But the more pressing question for them and their backers is, how can they can make money for themselves while operating with razor-thin margins?\r\n\r\nOne of this new crop of robo-advisors, StashAway, announced today that it has raised US$5.3 million in its series A round.'),
	(3, 'Asia news roundup: Flipkart losses widen to $3.6b, Toutiao and Weibo duke it out, and mores', 'FlipkartÃÂÃÂ¢ÃÂÃÂÃÂÃÂs losses equate to half its venture funding (India). The Indian ecommerce firm has raised about US$6.1 billion since launching 10 years ago. But regulatory filings in Singapore suggest that as of March last year, its accumulated losses widened to US$3.6 billion  from US$1.53 billion a year previously. It was reported last week that US retail giant Walmart is closing in on a US$7 billion stake in Flipkart. (Bloomberg)\r\n\r\nAlibaba might be buying Rocket InternetÃÂÃÂ¢ÃÂÃÂÃÂÃÂs Pakistan business (Pakistan/China). The Chinese tech giant is said to be negotiating a price for Daraz, RocketÃÂÃÂ¢ÃÂÃÂÃÂÃÂs Pakistani retail unit, in the latest sign of its interest in the country. It emerged last week that Alibaba affiliate Ant Financial invested US$185 million in Pakistani mobile financial services platform Telenor Microfinance Bank. (Bloomberg)'),
	(4, 'Alibaba and Twitter backer makes first-ever India bet', 'Growth-stage venture capital and private equity firm Insight Venture Partners, an investor in several high-profile software-as-a-service (SaaS) companies like Shopify, NewRelic, and Wix, today revealed its first investment in India: subscription billing software-maker Chargebee.\r\n\r\nThe New York-based investor led Chargebeeâs US$18 million series C round of funding, in which existing investors Accel Partners and Tiger Global Management participated. Insight, which has raised over US$18 billion in capital, has invested in more than 300 companies worldwide, including Hootsuite, Flipboard, Twitter, JD, and Alibaba. Chennai-based Chargebee caught its eye at the SaaStr conference in San Francisco a year ago.'),
	(5, 'Will esports boom in Southeast Asia like it did in China?', 'Discuss features short but strong and insightful opinions on interesting startup, entrepreneurship, and tech topics. Got a topic or question to suggest? Drop us an email or leave a comment.\r\n\r\nThe esports market in China is huge. With 16 percent of the global total in 2017, it is the second-biggest gaming market after North America. The rise of mobile esports can boost it even further.\r\n\r\nWill we see this kind of growth in all of Southeast Asia? Is the esports industry worth investing in if we look at long-term growth? We asked a couple of investors for their thoughts.');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;

-- Dumping structure for table article.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(90) NOT NULL DEFAULT '0',
  `password` varchar(60) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table article.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`) VALUES
	(1, 'admin', 'admin123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
