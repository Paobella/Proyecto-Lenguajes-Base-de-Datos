-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: plushieshop
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Naruto','Anime',1),(2,'Dragon Ball','Anime',1),(3,'One Piece','Anime',1),(4,'Mario Bros','Videojuegos',1),(5,'Fnaf','Videojuegos',1),(6,'Plantas vs Zombies','Videojuegos',1),(7,'Polar','Osos',1),(8,'Panda','Osos',1),(9,'Escandalosos','Osos',1),(10,'Otros','Osos',1),(11,'My Melody','MyMelody',1),(12,'Cinnamoroll','MyMelody',1),(13,'Kuromi','MyMelody',1),(14,'Pompompuri','MyMelody',1),(15,'Hello Kitty','MyMelody',1);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `id_factura` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `fecha` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` int DEFAULT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,1,'2022-01-05',211560,2),(2,2,'2022-01-07',554340,2),(3,3,'2022-01-07',871000,2),(4,1,'2022-01-15',244140,1),(5,2,'2022-01-17',414800,1),(6,3,'2022-01-21',420000,1),(7,1,'2024-06-12',36.48,1),(8,1,'2024-06-12',18.24,1),(9,1,'2024-06-12',18.24,1),(10,1,'2024-06-12',18.24,1),(11,1,'2024-06-12',18.24,1),(12,1,'2024-06-12',18.24,1);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `id_categoria` int NOT NULL,
  `nombre` varchar(1000) NOT NULL,
  `descripcion` varchar(1600) NOT NULL,
  `precio` double DEFAULT NULL,
  `existencias` int DEFAULT NULL,
  `ruta_imagen` varchar(2048) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,1,'Peluche Naruto 25cm','Muñeco de peluche de Naruto para niños, juguete coleccionable de anime,  regalo de cumpleaños y navidad, 25cm',18.24,10,'https://www.king-jouet.com/fstrz/r/s/images.king-jouet.com/6/gu909093_6.jpg?frz-v=3234',1),(2,1,'Peluche Sasuke 25cm','Muñeco de peluche de Sasuke para niños, juguete coleccionable de anime,  regalo de cumpleaños y navidad, 25cm',18.24,10,'https://media.entertainmentearth.com/assets/images/b5b9233b5fbb43e4be9dc40966ef3f80lg.jpg',1),(3,1,'Peluche Kakashi 25cm','Muñeco de peluche de Kakashi para niños, juguete coleccionable de anime,  regalo de cumpleaños y navidad, 25cm',18.24,3,'https://www.cadeau-naruto.com/wp-content/uploads/2023/11/peluche-kakashi-hatake.jpg',1),(4,1,'Peluche Sakura','Muñeco de peluche de Sakura para niños, regalo de cumpleaños y navidad',15.3,15,'https://i.ebayimg.com/images/g/1egAAOSwJLBiSsBC/s-l1600.jpg',1),(5,1,'Peluche Hinata 25cm','Muñeco de peluche de Hinata para niños, juguete coleccionable de anime,  regalo de cumpleaños y navidad',18.24,7,'https://baseec-img-mng.akamaized.net/images/item/origin/b834a5566407f66b1f3dac5aaa258092.jpg?imformat=generic',1),(6,2,'Peluche Goku 25cm','Muñeco de peluche de Goku para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',18.24,9,'https://i.pinimg.com/736x/27/ea/b2/27eab2aa35434a3234acad1356e5d4ef--dragon-ball-z-plushies.jpg',1),(7,2,'Peluche Vegeta 25cm','Muñeco de peluche de Vegeta para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',15,10,'https://m.media-amazon.com/images/I/715ommdiKJL._AC_SY606_.jpg',1),(8,2,'Peluche Piccolo 25cm','Muñeco de peluche de Vegeta para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',20,5,'https://i5.walmartimages.com/asr/894534e7-9d88-4ecf-a627-0a393d0e3550.4bab59009d7d7bfcc9e4c832d53e56f3.jpeg',1),(9,2,'Peluche Freezer 15cm','Muñeco de peluche de Freezer para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',17.5,6,'https://tiendadfanatic.com/wp-content/uploads/2021/04/Peluche-Freezer-Dragon-Ball-Z-15cm.jpg',1),(10,2,'Peluche Bulma 30cm','Muñeco de peluche de Bulma para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',22,11,'https://todomasbarato.es/6458-thickbox_default/peluche-de-bulma-de-dragon-ball-z-30-cm-nuevo-con-etiquetas-chica-madre-goku.jpg',1),(11,3,'Peluche Luffy 20cm','Muñeco de peluche de Luffy para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',18.24,20,'https://www.figurine-discount.com/46208-thickbox_default/one-piece-peluche-luffy-20-cm.jpg',1),(12,3,'Peluche Chopper 25cm','Muñeco de peluche de Chopper para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',15,11,'https://www.figurine-discount.com/22042-thickbox_default/one-piece-peluche-chopper-25-cm.jpg',1),(13,3,'Peluche Zoro 25cm','Muñeco de peluche de Zoro para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',20,24,'https://okocollection.fr/1038-large_default/one-piece-peluche-sanji.jpg',1),(14,3,'Peluche Sanji 30cm','Muñeco de peluche de Sanji para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',17.5,5,'https://www.cdiscount.com/pdt2/5/8/8/1/550x550/fp3701315823588/rw/peluche-one-piece-sanji-30-cm.jpg',1),(15,3,'Peluche Nami 25cm','Muñeco de peluche de Nami para niños, juguete coleccionable del anime, regalo de cumpleaños y navidad',22,22,'https://i5.walmartimages.com/seo/Plush-One-Piece-Nami-Soft-Doll-Toys-New-ge52554_e46b501f-1206-4f58-a4b2-a515cb4cc8a6.f8f8237130251aeaff1d19858f142ef7.jpeg?odnHeight=640&odnWidth=640&odnBg=FFFFFF',1),(16,4,'Peluche de hongo','Muñeco de peluche de hongo para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 25cm',18.24,2,'https://trebolmayoreo.com/wp-content/uploads/2022/06/100002001-Peluche-Hongo-verde-grande-fino.jpg',1),(17,4,'Peluche de Luigi','Muñeco de peluche de Luigi para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 25cm',15,4,'https://i.pinimg.com/originals/dd/f0/63/ddf0634dc5c6dddbcd979011016b508b.jpg',1),(18,4,'Peluche de Princesa Peach','Muñeco de peluche de Princesa Peach para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 25cm',10,30,'https://i.pinimg.com/originals/a1/f3/a8/a1f3a8d70bf1daf7cc9f127ec4283a08.jpg',1),(19,4,'Peluche de Bowser','Muñeco de peluche de Bowser para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',17500,10,'https://www.buildabear.cl/wp-content/uploads/2022/02/23566.jpg',1),(20,4,'Peluche de Yoshi','Muñeco de peluche de Yoshi para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 25cm',22000,5,'https://cdn.idealo.com/folder/Product/203164/8/203164847/s11_produktbild_max/san-ei-super-mario-bros-peluches-yoshi.jpg',1),(21,5,'Peluche de Freddy Fazbear','Muñeco de peluche de Freddy Fazbear para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 20cm',18.24,1,'https://falabella.scene7.com/is/image/FalabellaPE/gsc_119656307_2428722_3?wid=800&hei=800&qlt=70',1),(22,5,'Peluche de Bonnie the Bonnie','Muñeco de peluche de Bonnie the Bonnie para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 20cm',16,5,'https://cdnx.jumpseller.com/aratoystore/image/12602876/resize/540/540?1648671306',1),(23,5,'Peluche de Foxy the Pitrate Fox','Muñeco de peluche de Foxy the Pirate Fox para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 20cm',10,5,'https://http2.mlstatic.com/D_NQ_NP_781779-MLC73509665107_122023-O.webp',1),(24,5,'Peluche de High Score Toy Chica','Muñeco de peluche de High Score Toy Chica para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 20cm',17.5,10,'https://videodis.es/46413-large_default/peluche-five-nights-at-freddy-s-high-score-chica-inverted-18-cm.jpg',1),(25,5,'Peluche de Chica the Chicken','Muñeco de peluche de Chica the Chicken para niños, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 20cm',20,5,'https://boomshock.co/cdn/shop/products/IMG-20221014-WA0005.jpg?v=1665780290&width=1946',1),(26,6,'Peluche de Lanzaguisantes','Muñeco de peluche de Lanzaguisantes, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',23,8,'https://i5.walmartimages.com.mx/mg/gm/3pp/asr/f971a2bc-41ca-4594-8acc-c06911c8075c.aa0de9e918f043df7a39763ae8e01b8f.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF',1),(27,6,'Peluche de Zombie','Muñeco de peluche de Zombie, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',16,10,'https://promart.vteximg.com.br/arquivos/ids/384411-1000-1000/46.jpg?v=637127139349830000',1),(28,6,'Peluche de Girasol','Muñeco de peluche de Girasol, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',10,3,'https://ae01.alicdn.com/kf/Hed5f799b4b9b4af79c6e9c0fccb57374Q.jpg_640x640Q90.jpg_.webp',1),(29,6,'Peluche de Cactus','Muñeco de peluche de Cactus, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',18.5,6,'https://i5.walmartimages.com.mx/mg/gm/3pp/asr/0e016bc4-0e5d-45a2-b784-881b3940eee0.fed2792022692735d3825e84dee08d47.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF',1),(30,6,'Peluche de Jalapeño','Muñeco de peluche de Jalapeño, juguete coleccionable del videojuego, regalo de cumpleaños y navidad, 30cm',21,5,'https://i5.walmartimages.com.mx/mg/gm/3pp/asr/1a2d85d8-3187-4660-b408-596d3c2a569d.eabb450b9d9854ad38f4be21e574af1c.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF',1),(31,11,'Cinnamoroll con pijama','Muñeco de peluche de MyMelody Cinnamoroll con un pijama azul, juguete coleccionable de MyMelody, regalo de cumpleaños y navidad, 20cm',18.24,20,'https://http2.mlstatic.com/D_NQ_NP_880946-MLC69930227264_062023-O.webp',1),(32,11,'Kuromi traje original','Muñeco de peluche de MyMelody Kuromy, juguete coleccionable de MyMelody, regalo de cumpleaños y navidad, 15cm',16,10,'https://i.pinimg.com/736x/79/6a/a6/796aa657a7f88e9f109b52b94d43af4e.jpg',1),(33,11,'Pompompuri','Muñeco de peluche de Pompompuri, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',10,21,'https://th.bing.com/th/id/OIP.5oZXkZBgH9VAhY4uHe8eMwHaHa?rs=1&pid=ImgDetMain',1),(34,11,'Hello Kitty ','Muñeco de peluche de MyMelody Hello Kitty, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',17.5,21,'https://th.bing.com/th/id/R.166a678f1af4a3c72810b601f97886ce?rik=LLnZeAWQaQ3ZDA&pid=ImgRaw&r=0',1),(35,11,'Pompompuri (Gato)','Muñeco de peluche de MyMelody Pompompuri con un traje de Gato, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',16.5,21,'https://i.pinimg.com/736x/10/9d/40/109d407ab6eed35663a8bccfce571613.jpg',1),(36,11,'Cinnamoroll traje GODIVA 2023','Muñeco de peluche de MyMelody Cinnamoroll con traje de GODIVA 2023, juguete coleccionable de MyMelody, regalo de cumpleaños y navidad, 22cm',18,10,'https://happycruise.jp/wp-content/uploads/2023/01/valentine2023-sanrio-godiva12.jpg',1),(37,12,'Peluche de Cinnamoroll con pijama','Muñeco de peluche de Cinnamoroll con un pijama azul, juguete coleccionable de Cinnamoroll, regalo de cumpleaños y navidad, 20cm',18.24,20,'https://http2.mlstatic.com/D_NQ_NP_880946-MLC69930227264_062023-O.webp',1),(38,12,'Peluche de Cinnamoroll original','Muñeco de peluche de Cinnamoroll, juguete coleccionable de Cinnamoroll, regalo de cumpleaños y navidad, 11cm',10,10,'https://th.bing.com/th/id/OIP._W14G4-swnEg1fJ9fDLuQAHaHY?pid=ImgDet&w=180&h=180&c=7&dpr=1,3',1),(39,12,'Cinnamoroll (Enamorado)','Muñeco de peluche de Cinnamoroll con traje azul y destellos en los ojos, juguete coleccionable de Cinnamoroll, regalo de cumpleaños y navidad, 15cm',18,10,'https://meccha-japan.com/557634-large_default/plush-cinnamoroll-sanrio-birthday-2024.jpg',1),(40,12,'Peluche de Cinnamoroll (Fresas Season)','Muñeco de peluche de Cinnamoroll con traje de colores pasteles y fresas, juguete coleccionable de Cinnamoroll, regalo de cumpleaños y navidad, 20cm',15.5,10,'https://i.ebayimg.com/images/g/hJ8AAOSwprNi731R/s-l1600.png',1),(41,12,'Cinnamoroll 20 Aniversario','Muñeco de peluche de Cinnamoroll con traje alusivo al 20 aniversario, juguete coleccionable de Cinnamoroll, regalo de cumpleaños y navidad, 26cm',18.5,10,'https://meccha-japan.com/248937-large_default/peluche-kotekikai-cinnamoroll-20th.jpg',1),(42,12,'Peluche de Cinnamoroll (Traje GODIVA 2023)','Muñeco de peluche de Cinnamoroll con traje de GODIVA 2023, juguete coleccionable de MyMelody, regalo de cumpleaños y navidad, 22cm',18,10,'https://happycruise.jp/wp-content/uploads/2023/01/valentine2023-sanrio-godiva12.jpg',1),(43,13,'Peluche de Kuromi (Traje lila)','Muñeco de peluche de Kuromi con traje color lila y detalles rosita, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',19,18,'https://i.etsystatic.com/10186101/r/il/ec4661/3150024846/il_fullxfull.3150024846_khvt.jpg',1),(44,13,'Peluche de Kuromi (Traje original)','Muñeco de peluche de Kuromi con su traje original, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',16,15,'https://i.pinimg.com/736x/79/6a/a6/796aa657a7f88e9f109b52b94d43af4e.jpg',1),(45,13,'Peluche de Kuromi (Gala Gotica)','Muñeco de peluche de Kuromi con un traje de gala negro con rojo y detalle decorativo en su orejita, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',17.3,17,'https://img.shoplineapp.com/media/image_clips/64a4478bb489f20011a75d67/original.jpg?1688487819',1),(46,13,'Peluche de Kuromi (Picnic)','Muñeco de peluche de Kuromi con lazo y moño estilo picnic con tonalidades moradas, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',12,26,'https://th.bing.com/th/id/R.b5d09fb38dcebd71efaec0182f3610bd?rik=rioDyEn7cNZAEA&riu=http%3a%2f%2fwww.sanrio.com%2fcdn%2fshop%2ffiles%2f877069-Zoom.1_600x.jpg%3fv%3d1692218616&ehk=01QnUNRv0pQAyDFUIbXpvJdoYGTJB5G3ZBavpO1nfSg%3d&risl=&pid=ImgRaw&r=0',1),(47,13,'Peluche de Kuromi (Negro Elegancia)','Muñeco de peluche de Kuromi con traje de gala color negro y detalles en el mismo color, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',16.5,21,'https://d2bzx2vuetkzse.cloudfront.net/unshoppable_producs/ad320ac8-4e02-4834-a5fa-6acaf3048f25.jpeg',1),(48,13,'Peluche de Kuromi (Tierna)','Muñeco de peluche de Kuromi con un moño color lila y un bolso del mismo color, juguete coleccionable de Kuromi, regalo de cumpleaños y navidad, 22cm',10,10,'https://th.bing.com/th/id/R.f7e548194e9cc5f823431ef8301491a5?rik=u%2bb08eKqJVZ2pw&riu=http%3a%2f%2fwww.sanrio.com%2fcdn%2fshop%2fproducts%2f661660-Zoom.1_600x.jpg%3fv%3d1680901226&ehk=5BdcpxbZht1ZsWBiYyuRiTpg78oP%2bwDBBENIiewF%2fHc%3d&risl=&pid=ImgRaw&r=0',1),(49,14,'Peluche de Pompompuri (Panda)','Muñeco de peluche de Pompompuri con un traje de Panda, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',18.24,21,'https://th.bing.com/th/id/R.77e58d40f079820b3022f3392191ae3d?rik=ay8ZiwsCKMsPXQ&pid=ImgRaw&r=0',1),(50,14,'Peluche de Pompompuri (Vaca)','Muñeco de peluche de Pompompuri con un traje de Vaca, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',16,21,'https://cdn.shopify.com/s/files/1/0416/8083/0620/products/184420-Zoom.1_1600x.jpg?v=1613076991',1),(51,14,'Peluche de Pompompuri','Muñeco de peluche de Pompompuri, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',10,21,'https://th.bing.com/th/id/OIP.5oZXkZBgH9VAhY4uHe8eMwHaHa?rs=1&pid=ImgDetMain',1),(52,14,'Peluche de Pompompuri con una bellota','Muñeco de peluche de Pompompuri jugando con una bellota, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',17.5,21,'https://th.bing.com/th/id/OIP.2-kThkfXmE0rQ-DPqRa_eQHaHa?w=800&h=800&rs=1&pid=ImgDetMain',1),(53,14,'Peluche de Pompompuri (Gato)','Muñeco de peluche de Pompompuri con un traje de Gato, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',16.5,21,'https://i.pinimg.com/736x/10/9d/40/109d407ab6eed35663a8bccfce571613.jpg',1),(54,14,'Peluche de Pompompuri (Scout)','Muñeco de peluche de Pompompuri con traje de Scout, juguete coleccionable de Pompompuri, regalo de cumpleaños y navidad, 22cm',18,21,'https://th.bing.com/th/id/R.c9bb553143234b49d31424615b68b60a?rik=f5GVfof%2b3tKxsg&pid=ImgRaw&r=0',1),(55,15,'Peluche de Hello Kitty (Chupa Chups)','Muñeco de peluche de Hello Kitty con traje de Chupa Chups, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',17.3,21,'https://th.bing.com/th/id/OIP.L0PQm5Z_88bufLILyOsaXQHaG3?w=640&h=594&rs=1&pid=ImgDetMain',1),(56,15,'Peluche de Hello Kitty (Traje original)','Muñeco de peluche de Hello Kitty con el traje original, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',9,21,'https://gadgetsin.com/uploads/2010/10/sanrio_dancing_hello_kitty_speaker_1.jpg',1),(57,15,'Peluche de Hello Kitty (Traje original rosado)','Muñeco de peluche de Hello Kitty con el traje original version rosado, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',11,21,'https://meccha-japan.com/195143-large_default/plush-howa-howa-2l-hello-kitty.jpg',1),(58,15,'Peluche de Hello Kitty (Vestido y zapatillas)','Muñeco de peluche de Hello Kitty con un vestido rosado y zapatillas moradas, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',17.5,21,'https://th.bing.com/th/id/R.166a678f1af4a3c72810b601f97886ce?rik=LLnZeAWQaQ3ZDA&pid=ImgRaw&r=0',1),(59,15,'Peluche de Hello Kitty (Enamorada)','Muñeco de peluche de Hello Kitty con vestido rosa y destellos en los ojos, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',16,21,'https://th.bing.com/th/id/R.d087759c84b570e9264ee0482d1fee10?rik=pmyj40Sx92kNaA&riu=http%3a%2f%2fwww.sanrio.com%2fcdn%2fshop%2ffiles%2fzz-2309756237_KT_--1_2000x.jpg%3fv%3d1695702500&ehk=Ee4jyxm%2bzL4E2GPshJIEOpT9TnadKGQ0HgT3FBd3KrU%3d&risl=&pid=ImgRaw&r=0',1),(60,15,'Peluche de Hello Kitty (Pijama)','Muñeco de peluche de Hello Kitty con un pijama rosita, juguete coleccionable de Hello Kitty, regalo de cumpleaños y navidad, 22cm',10,21,'https://i.pinimg.com/originals/54/f6/02/54f602190af0d8185043267a21ded238.jpg',1),(61,7,'Peluche de Polar (Normal)','Muñeco de peluche de Polar normal, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://media.entertainmentearth.com/assets/images/6227c1ee4dd74cb2ba4323bae9648f0blg.jpg?_gl=1*18p7dfe*_ga*NjQwOTk1MzY1LjE3MTAyMTgxMDE.*_ga_M8MT85W31R*MTcxMzg1NjMzNS4yLjAuMTcxMzg1NjMzNS42MC4wLjA.',1),(62,7,'Peluche de Polar (Cuatro patas)','Muñeco de peluche de Polar cuatro patas, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://resources.claroshop.com/medios-plazavip/mkt/5eaaec2ca4dcc_2000003410101-1jpg.jpg?scale=700&qlty=80',1),(63,7,'Peluche de Polar (Cumpleañero)','Muñeco de peluche de Polar Cumpleañero, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://miniso-bh.com/wp-content/uploads/2021/05/2010143612105_1.jpg',1),(64,8,'Peluche de Panda (Normal)','Muñeco de peluche de Polar Normal, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://f.fcdn.app/imgs/fbe799/www.minisouruguay.com.uy/miniuy/6828/webp/catalogo/694105513216969410551321761/600x600/peluche-sentado-escandalosos-panda.jpg',1),(65,8,'Peluche de Panda (con Suetita)','Muñeco de peluche de Polar con Suetita, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://minisope.vtexassets.com/arquivos/ids/233747-1200-1200?v=638167773951970000&width=1200&height=1200&aspect=true',1),(66,8,'Peluche de Panda (con Manzanita)','Muñeco de peluche de Polar con Manzanita, juguete coleccionable de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://dojiw2m9tvv09.cloudfront.net/26518/product/X_192135-1600-16009126.jpg?130&time=1713857039',1),(67,9,'Peluches de Escandalosos','Muñecos de peluche de escandalosos, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://http2.mlstatic.com/D_NQ_NP_924964-MLA54979793842_042023-O.webp',1),(68,9,'Peluches de Escandalosos (Apilados)','Muñecos de peluche de escandalosos apilados, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://http2.mlstatic.com/D_NQ_NP_921822-MLM51330080109_082022-O.webp',1),(69,9,'Peluches de Escandalosos (Frutas)','Muñecos de peluche de escandalosos frutas, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,9,'https://www.peluchesymas.com/wp-content/uploads/2021/05/as.jpg',1),(70,10,'Peluches de Ositos Cariñositos (Gruñosito)','Muñeco de peluche de cariñosito, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,10,'https://http2.mlstatic.com/D_NQ_NP_873801-MCO44201264752_112020-O.webp',1),(71,10,'Peluches de Ositos Cariñositos (Amorosita)','Muñeco de peluche de amorosita, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,10,'https://i.pinimg.com/564x/5e/3a/53/5e3a53fd33cf4c8be5c728c8ef8a079b.jpg',1),(72,10,'Peluches de Ositos Cariñositos (Dormilonsito)','Muñeco de peluche de dormilonsito, juguetes coleccionables de escandalosos , regalo de cumpleaños y navidad, 22cm',10,10,'https://m.media-amazon.com/images/I/71HfOYloy+L.SS700.jpg',1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(2048) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tarjeta` varchar(16) NOT NULL,
  `pin` varchar(4) NOT NULL,
  `fecha` date DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Laura','María','Laura@example.com','Heredia Centro','user_123','$2a$12$04CrX/liDFv9De2pwewjH.V54GWW.4aa/LrZJwetwXn5otlGFPv7.','****-1111','1234','2030-04-15',1,2),(2,'Alondra','Loría','alon123@example.com','Llanos del Sol','user_456','$2a$12$Iaz69g9rD9tzKO0kaB.V/.UkwIRHMPr9nPU1kCM7jc.9.wkRrx7Yi','****-2222','5678','2028-04-15',1,2),(3,'Carlos','Jimenez','jimenez@example.com','Calle Sánchez','user_789','$2a$12$stCHKC9z1UJs3rDa2kisfucZnHTXruTuNJwsOILv5bzLOMdc9cRkG','****-3333','9101','2029-04-15',1,2),(4,'Admin','Admin','admin@example.com','La Aurora','admin','$2a$12$mwkEba3Ge19v4g1dIN3pmuT2oDVyd51NWDHpquHq4wdVeesbcVBBC','****-4444','1314','2032-04-15',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `id_factura` int NOT NULL,
  `id_producto` int NOT NULL,
  `precio` double DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_factura` (`id_factura`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`),
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,1,5,45000,3),(2,1,9,15780,2),(3,1,10,15000,3),(4,2,5,45000,1),(5,2,14,154000,3),(6,2,9,15780,3),(7,3,14,154000,1),(8,3,6,57000,1),(9,3,15,330000,2),(10,1,6,57000,2),(11,1,8,27600,3),(12,1,9,15780,3),(13,2,8,27600,3),(14,2,14,154000,2),(15,2,3,24000,1),(16,3,15,330000,1),(17,3,12,45000,1),(18,3,10,15000,3),(19,7,1,18.24,2),(20,8,1,18.24,1),(21,9,1,18.24,1),(22,10,1,18.24,1),(23,11,1,18.24,1),(24,12,1,18.24,1);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-21 11:01:08
