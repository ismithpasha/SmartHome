-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 22, 2019 at 10:21 PM
-- Server version: 10.0.38-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ismithpa_smarthome`
--

-- --------------------------------------------------------

--
-- Table structure for table `arduinodata`
--

CREATE TABLE `arduinodata` (
  `TokenId` int(11) NOT NULL,
  `device_id` varchar(100) NOT NULL,
  `device_imei` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  `device_timezone` varchar(100) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arduinodata`
--

INSERT INTO `arduinodata` (`TokenId`, `device_id`, `device_imei`, `time`, `device_timezone`, `latitude`, `longitude`) VALUES
(4, '10001', '78666335', '12:12', 'Dhaka', '23.8917', '92.8976'),
(5, '444', '434', '5', '53', '35353', '355'),
(6, '56435', '33234', '12:12', 'Dhaka', '332', '555');

-- --------------------------------------------------------

--
-- Table structure for table `emergencycontacts`
--

CREATE TABLE `emergencycontacts` (
  `ContactId` int(11) NOT NULL,
  `ContactName` varchar(30) NOT NULL,
  `ContactMobile` varchar(20) NOT NULL,
  `ContactEmail` varchar(70) NOT NULL,
  `ContactAddress` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emergencycontacts`
--

INSERT INTO `emergencycontacts` (`ContactId`, `ContactName`, `ContactMobile`, `ContactEmail`, `ContactAddress`) VALUES
(1, 'Badda Police Station', '+8801191001155', 'ocbadda@dmp.gov.bd', 'H-12A, R-17, DIT Project, Marul Badda, Dhaka'),
(2, 'Banani Police Station', '+880119100115500', 'ismith9903@gmail.com', 'H-12A, R-17, R-7, H-37, Banani, Dhaka.'),
(3, 'Bangshal Police Station', '+880176905805300', 'ismith9903@gmail.com', 'English Road, Fazlul Haque Comunity Center(2nd Floor), Bangshal, Dhaka.'),
(4, 'Bimanbondor Police Station', '+880119100115500', 'ismith9903@gmail.com', '1st Floor RAB(HQ), Uttara, Dhaka.'),
(5, 'Cantonment Police Station', '+880119100115500', 'ismith9903@gmail.com', 'MES Jia Koloni, Cantonment, Dhaka.'),
(6, 'Chalkbazar Police Station', '+880176905805300', 'ismith9903@gmail.com', 'Bakshi Bazer Road, Dhaka.');

-- --------------------------------------------------------

--
-- Table structure for table `fcmtokens`
--

CREATE TABLE `fcmtokens` (
  `TokenId` int(11) NOT NULL,
  `Token` varchar(999) NOT NULL,
  `RequestTime` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fcmtokens`
--

INSERT INTO `fcmtokens` (`TokenId`, `Token`, `RequestTime`) VALUES
(1, 'cV3w23W341s:APA91bFcRHwNqx9OwsoQEqzcD002LbGQYuES2KkotOG2SM-u_Bm-SrztX14DkmA26ru5vjUOHI3SWyI-ZSjPSNioH23YrE6FAOAE26bMSpxVnKaFEn9IGHAf7r9NGBk6_yr3wtlac3VtpgIt2lfOYU4_NibR_-DvcA', '2018-06-27 02:45:07'),
(2, 'deYRkrz1xrs:APA91bE-dRWhG2XuGdWzj9a55H0v88s2mlAtf_Vie5f5Kl8Wel9nYg6_lp5LYv-nHBPdjQGnrzzRDdAcOWnHza_Ak30MplIWoIPx_WFMoePuxCfwlwOK6k-faYc-zmUlhAFbS5gqA4XA3K-xNNNZfIOtnDfqw6KLeA', '2018-06-29 12:54:02'),
(3, 'dgQZkpO4L7Y:APA91bFZV6DYFMni4cufUaFR_CpyVxOFVO1lGYNXn2_6W-tpKYs_2IQz7jgsurLE7ABVFFGhg4ISY1_g_JEgqlA5gQGkn8uk_VfWa8zRVUtCjban44BwlgC8suOLCZRN963GZqItdkO0FOh90eaIzL8nEjyhm85kPw', '2018-06-29 02:48:51'),
(4, 'czVLawWhltI:APA91bGLx3A2Ee40liPC2FRjlvCJPv6m_9pz0hr3IMP8jJv36QL8kQqNZeM4h3wE_lYhA8n-ukQC6K42ZZkyeKw9O75Dj2z_Z2ZtzeLNCieoTwtbGjEvjG_Q2c5FQSPk1GjmVGD8lYSxkTp15HsDeLerUK8L8rcQZQ', '2018-06-29 02:53:25'),
(5, 'fGEq7LwLlYA:APA91bE2J2HYw_0vdXjUnz4lzDYYfg148uXK8OSYAm9IWenaPNmR7KF8l8T4Phl2XnYlGdyq9E5E8Ahohlrf3IjVAaHDIdb7MUxrJKzp3ciGvGuj7H_zPpm1Sy4aAa6sbv5Ge_n03tE4hFK8ZybjgnKjRSMOeV_45g', '2018-06-29 02:58:11'),
(6, 'cF7grLV5o2M:APA91bFbOgggzOli9F-67T0xJlE9OTx0mAyLmeFZ6huIPeLJ2MZspzGxNNZhmI-DAblkIMnYJrDtuH8e-NxCPwB1Iyqkzzc2mzZtF7k0Gpww50Xjk8GJq1q3dETuY1gazG-SM81Vcnnh-9cFnqX-WmUBtWnglP4izQ', '2018-06-29 03:04:25'),
(7, 'fHuIL5LVp_E:APA91bHgjtuP4MBcqIOxaHDwEJUtP3G2Hwz9t1dtSmPrmRILDhYk__pLbqywvXI1vgVyoW0OXQABwCNXeeTqSOtUPufxHUbhfADMIZm9vsnmEl0MzaSOfePfrfEo75As_hHXE0kqrauP3eNo5RxfbdL3Hblm9dokIw', '2018-06-29 03:09:45'),
(8, 'c9sJ_h3CYzA:APA91bHAAHN6gpsD9sVPKhehB6mdOYgJ4haiJwAmXqKXfjndG-p9II2swgMBD8Y-Pk8p03jlfeO35esl0Yad1fAUD_chEB1RXmJ9dKEI_mUcfyPuy6cvugagS7yedHfxZ2jP1rIi8HuifEUdNHy2dnAnMRSV7TZu3Q', '2018-06-29 03:25:19'),
(9, 'dFAHCi_6zlU:APA91bFAtF3zYKn21wfpexzNzDJsve2mC_AmB8zT5QGGSvL1MtQ74PL8jF8v_8EfIDymTMEEvrsznHvxCZLnkZtgPcjWV9B4ayDtu73G_D_KQinH-njW2aBRtg2VlTzBN9tBznvko6s2AfhEz0azNLodmpWpr8n0JQ', '2018-06-29 03:26:24'),
(10, 'eVxsdKjJvqw:APA91bFfYw8xnRFf2_xYBZDqkZubXrnRC0oCMNU8Z1Zc2X61TeCud4MFeVEvqRxI8nr0D306JlBcBEGVjVXjU2Bvjdsp1d6vl38EW8q10hH5CcK6LXbV_J8PfdeCelmrFcIg-98h9W0TXtcV4Be-ZTHgqyGUXAAIAQ', '2018-06-29 03:33:04'),
(11, 'f4bWPkE61Uc:APA91bGsNWWYdRgynaz7dSPfLUYrBEG-nXkKn_qU0kdLFtbiN1fPnwFw6nsFceKX6-JUn5ptydFQ6qEUVDc6khEHYrrDFrX4J6XWFM0ebr_4Q2hQ-SO2Yb4WE0M73Lywig747BgFdYDhgGljBcBiTPkpug3pv5776w', '2018-06-29 03:36:55'),
(12, 'cxbUoB1C708:APA91bFlUjOH7yQtydvcFmTGMDsjNgLwH5TlpcSobi2CZkmZ75Nh6yLKNHTp3wjYf6DNhHYj1n4bg3yicfD8SmHHcTDAzRPRP52DiJ2yNbNr7SO9xGALhWRqqR8oThhIHUtbgJFUfVSx0YOQjp4o1FV7drJnam5KFw', '2018-06-29 03:37:57'),
(13, 'eBafaPMJTxo:APA91bFXUnj7Ou0tsy-ltXgxvsewAumSZ0Qbcqrc1MFjZH6W6rLGsZCIYc7HsT2447CN0YUQaR3cNwmDiJOTvx9lanV6VLxc7FQjKAXyJXU9MN2v5NRTj428V9dO8x-tPVwFJKtBNZh7gOcXjQ9fUf-ii4GUKvDASg', '2018-06-29 03:39:01'),
(14, 'e--qhjEHd8s:APA91bEMprDR8LoJQauxf5LzLFhzvxbCh2lBTevhZuX7agwOxB1yGm0eKZqlQnm0cvOk4H9DgXnDmCyueCh5slsnhgQdFwdgm0AlpkmLdNexEUwbkmeTT07OGWqFwPtME7Gx2pyMyIPOC64dvI_qj8o7O1vkpDNxEA', '2018-06-29 03:39:50'),
(15, 'eHqaR4O_PW0:APA91bEB1qnA-qbiROMtY1iGJJMfwFF-OgYKvQo54KoeqEhkglQDEGeocBdPc-IZ9sZXkxtoNVCjDkDF2QImYKqtKrOe46jp0eOblkgEBQLKvpV_SwAM8XbkXYoRMTfcRFb2qBts5WzNtpi3v4Zjlegg1VzGXlojlA', '2018-06-29 03:42:15'),
(16, 'dgoxh1te6LA:APA91bEG8iL1MVrEdvx4nAD5NfN034EQhI9LrboSwv8QtAc3Xv8ppw7Uf-EIenvsoYs4BMurkHvGDAuAJrV2-SOuLfeQjL4aDgvlvvAU4Q2dPtvLG3jki6zN3C8zOT1ibptKltAIoviF5f_uARxDaJgYtqXDdIErqg', '2018-06-29 03:46:02'),
(17, 'eoLYBi0aNbk:APA91bGnXIq-bVYtIYzjzQ6OVF0QLtqpDbSwlNL5B71p1kNRV-FbleOPZij_dPQEh8GwXrOGKA47oD_0KA5WN8N_q8AMCBRtUeKTW0nZShWmTlFkf6MF1qo1-kXzz7yWMPu4otbNBcyN1j3s3T3OZoSP7kdckDG4Mw', '2018-06-29 03:47:13'),
(18, 'dlRiN8N0yWs:APA91bF-XdxP6E2mEw5SvCzZbAfqBt9IRe25TgeAgsUVVZXkzupZtvYVVZM7a2QZ66CX0Tm-s5pD4HONEZtsHnoPuhQvAOf8nuT2zOXnq0QDMTELPL4iU5VYQ18Tutep8Soy6HmR1EUDcJX5XwfseiwcJvtw-3I_KA', '2018-06-29 03:54:29'),
(19, 'dGrpZtoy1zU:APA91bEegQFYOsNPppUxfDMix3pDbl2ywJ15WHRSeXZflPnOMyATul_0LlX-kehahTA9Z-z1718xHol5JznnU2H0m6S57QfqLKiD7xA-wDWgvA8cqxl3TdQUI05JYtDEV7IaTPhnAJ0tzYRhQvyFOi-IHWJUCvlEjg', '2018-06-29 03:58:01'),
(20, 'frlWNwJJswY:APA91bHRlknwb1o0KOHt83jUjuPhaiqcqQOjDC4ns1qDAsKhJuCzPLFsTQJ4p40t7_OakiBc2EHlCnxCH4VmO3evX10pgQIYZnPTkc7CFs6QxeAWhYB-M_8ouAQfvEOkQCSy7Sfjx9KryUcpJXvGL6YxnUKjck0SEA', '2018-07-05 02:23:00'),
(21, 'ezC4U6PdUjs:APA91bEDhqE3tUfgE1jbAMT-_LNVwTn_qWXMsMLt8qlXzhtgTcZit1vogSqwvsqBiF0Dr-FqFz0XPczkEw1rQqk37D1YERQNSG32i2Cyy5ulVfdefd6n4BwO7c9EfWvEPa_7ET5Bj0S6t-fSBV1qD2SWNuqAp1ep-A', '2018-07-05 02:29:25'),
(22, 'cFnRtQfKH6E:APA91bEG4-Lb4sWGiAf4NUD1CX4Qb51bNFODl32JB0nqF7u9tRSQB9RIJiMfdpqpy_hb8zPFPhgc5NEs-yBRfiqrIS-lyXbABtpKfUlnCDkZv15jaEBX2JS7aDzPXgqVBw7YmBEdbtMoetm2fW7rCt7b0rHtqFj1Vw', '2018-07-05 02:40:02'),
(23, 'c1Uvy2U_W1E:APA91bH4_-2ICDUBTPwePBFJJZp9zok6s_VXzt9cjFSzLHx-YfoeMQGkFu1bt86kicGkkssThhOLp70NvUqMOPT29lAHk58_ZbjR7MpdeG4CqCYEMUalndFhLmJy_nRn7YKi4HPleWWPkhANE_1QTMct1YrUbxDieQ', '2018-07-05 02:56:02'),
(24, 'fqaNHEueHpo:APA91bH7QdkAiFZFSGcZsw9Qv_HiVYRERmODiSPIFypeO2Mo31BoyXP984MwI7tFzA9kn-yClArsqfe_YbFxn0AYW4gdEOxa51MJhKhuCaX211cwmSTHi_HfdLEt1n3vwk1BeQGBYHjukHIouxHkF06UV3D2iRcP7Q', '2018-07-05 03:00:43'),
(25, 'dU_-Fn4hAtU:APA91bEOCb8PKntASHbJR7NVinzVdJnFsRRouv0ZxrTvLVyz-oen4h68Y6soawCtJpHIfrtfDNLLu8RsgvDYhqoWiAJpHSeJgYn6KhRZWr7KMDQr4j5Zba-8tN1xNY6sFeO3kGwCvpLck66mIgqSusolEOhggujyHQ', '2018-07-05 03:04:44'),
(26, 'egm7M8nlkFY:APA91bHGtbBsbgCXFuGfn0raVQvbwS4NsazfPgz-NC8NwcSAWbChdfz3190EL4uTc1be5yS5IVPMQYA06NcnECUqrEBB6Up3gY0c0zdc9cLhYiGEHA3s_r65nWYNREmC4l19V82sbe8AS4w3Ls2H0OUArtEJ_wILuw', '2018-07-05 03:13:16'),
(27, 'fwn_YVoAu-w:APA91bGXbiqsqQLpdxUC6yzxgumJQ0yA49Ycy-aV_LGo7Aho6edR4cGaWWh8yBVgI0NWekAQgBKWdVScTt0GVJXGO-ZrzSWx4AJn9xyZhipaLVD1sWoKmWCqE292Ncl3OJcG9MExReaoQM0r6ZFrvmZRWLQJqEa2kA', '2018-07-05 03:56:57'),
(28, 'dRg38bJejSw:APA91bHCaEafy393kJVjZqO6fIEokLwqYdbdjRnHRggq1PW22Ox_0012eIUdNV-t3B0p_6Fg2TueFvItjkryYz5K0ml-nAlY_tPORYS2S82XRXHf6OFf8EZUDh-owgtZVIV2P3eYBaLKI76hytv_Zrrl95WP_FCDUg', '2018-07-05 03:58:37'),
(29, 'exf8vcUELZo:APA91bEaGS10lzebPP58wzv3NJnHDzAoVcihhCn8-JRR4ECG5pq4u9p-og4ZHjwo6aWNhtChI3bamr-9-0fJ0_5BHU0bL2KuNXaNdJgYKwSFMr3MWmO5fB51nRcFhmSp8nAPoRWNrgLrkYMa695qVKfCa5UMcugEEw', '2018-07-05 04:19:49'),
(30, 'e7Qu3g8lAeo:APA91bEgpNgiy6GJTb3wCevcVAiP_ToRsSgFBkTnbrnMt_zE-nLhwZUGukyDxahpycBinmRcm968vb5f7RKFo2IWi9SP7Gl_PHYDYEhO6vmWiU7M1HP497xD3pG8NVRzNwYNv-cfCVf3yxzjZe2V0zTqEyHrGiVAXA', '2018-07-05 04:27:39'),
(31, 'c-3RiffyV8E:APA91bG9o1bJPJTQhJ02MDID5qh7L5g7F0UdS8xh6kyKP0O2LfTg-u7bKe2UkEPAEjNjsQaC5qqFGbeKSZPLq9_95czr0Egji5OUPloNnWDuiDZWOU4FA_yLG_6Z0JvnNTgqGcm7AD8MCsEKuhMbPJb5xQMUF-vOvA', '2018-07-05 04:35:34'),
(32, 'dEsF-IFxT3Q:APA91bF6iMAcSZFVTHqnQhM4VehLwn5ddrjQmgDtpfMA22AUWbxfDpye2c7F28xnzgL8o7uTqfOs--7Mh_jooMGXRZm3PT5WbnNurAf9YtS-4wY9vuIl81lPwKsI1FaKv-hfXGL7rpAzPCMIvlq7WGe0MGWGcJ99TA', '2018-07-05 04:38:31'),
(33, 'cxjQ5RWYtjU:APA91bEo0RUmIhDtNzRLjQH8eCMYsCgMtsRVj7RjQPzwP8LNF4fzTUEBIgeiujdf6QWkb_M1trraV8b8r76xPgtrPYHPS_Q3WCND7o8hG9qlVKXPy5PYQhRWN2AJPW3jOBS_4P9hP0HGgcIn87t6n_h3ZaZIAdrIQA', '2018-07-05 04:45:32'),
(34, 'eqHBitVXq44:APA91bGz05vpsL_6DgijvMbwQ1_c9UI6nIylVXA6jXE2MY3ZKdtDCHiq9PN2J_GVlmwH0b0WEtQ65CxEKNMT-1wtE9KZMGPW3Mvfc485_3Gaxpm-S0zyHqWt7UN2dB0YUaHZbAcTxRjT8uWqZXdqZSsflA9lN5rhmQ', '2018-07-05 04:47:55'),
(35, 'cWfQiS19h7E:APA91bHUK32HH_UB4-Dl-STir4SY5yISSuqkWNHy31DS_uzPFFvN6gK5yUYFMjdMbHVKY5OzNz-O2FesPUx37Qon3dUDwvSAm7N-xWVHtYSc-gOd_rXgomRGhXo2g0FaVNf47Fu00FVizhfNA8UIo25VsAh3ml6Vjw', '2018-07-05 04:49:51'),
(36, 'cdFTOww5kuA:APA91bHbYA6LlakbQK9DxeUYXFgKzTHgIDeJjLZMDVuSzBmooCb6chK9zIpbZXAfKLt_oz71IZkUI6daMvGP3-vfjt9shVw5OHKQWAmk1j8J3iZ1-TUBHL5IP_6PgPcRcX8IDlPK9Ax7K-fmuaHgVwCvQgpKch5k2Q', '2018-07-05 05:06:10'),
(37, 'eDSbNi-7uv8:APA91bEA3u9gbmWaQuHhe2-LbRC5uu0C8cV_YXiWm2WCMmzk1Nka4l96jvAQzp-eAi1Si-qB07CieM8GBNek1f1SEWzfw298XJ5kYOa9SUaxQ7FbEyarADrjjmOYVw4P-TwSFb7NmGQvgJEOqW-ccHrfEuH18kEdPQ', '2018-07-05 05:33:25'),
(38, 'fWsV0EOivrw:APA91bHqtcIPSUCNe-U0yfg3uZwbE4Mp2mGPrh-Er-Wl19_qAbjY_IfeogjVPr0bnOZkbOhzk-W0m41kxnoqkggRRSuMtA71V4WO8bZDHb2dDDWHlUqYLbX_gH23tdQJGlaQ9re8YSp3FUZSh2B6aKYlCp-6jvgGTg', '2018-07-05 05:43:03'),
(39, 'eLcadqedw_0:APA91bEWRQhZu4AnPOSA7wRH0BMfggYE9sy7ncrthSWibYTVfG6vV48U0511bVRpj7b6uue7_VtQfh6YM2gTGTpir1XmOG5v8evQ7Xkmb0Jv4fCP35G_DJ8JgH3FPghQUkp__AHA02uv32oE7VVBnjEzR-vKiNlLaQ', '2018-07-05 06:27:15'),
(40, 'cIohKW5LGjs:APA91bHBOzzEYmP_NV7357tmPvvA3xNNXXuRVViiQCyZl8lqQQk4r9iDnwxr2KCvOYwOSPGQTEgkD_AWahP8QG194ak7Hxh-n0XaVs_8JeLVJep9I8OXgzS_V_h37NnPOJgUKXOf0mvf7U18jvF9fGPBn9lAfZNppw', '2018-07-05 02:10:59'),
(41, 'dSHfgrDqsKY:APA91bH3yO6yQv0qfU1wwiuTiDX1kW-52Nc-h3oKF8xJvsJfGXSq2IpbL1uBzoAPdGSYFSDkam4LmAWBmWke2apufbCN0UEnIZJM_qbPzZ5QGO5B06DDfBEt1ZWMAOQ9-Xd8os0uWM0d5I3gBtxIE_-Bttbol_HN-Q', '2018-07-05 02:13:24'),
(42, 'eREMjj22b3w:APA91bGXcHEAjMTCo683EYRQPykJ0KY5g_XvcfbI9UZxdAnkgfBG2lSUphIVnxATkzNKngfUGx1T7pJfEWquz85LxABHbe-PEOuwv9KPPaivihK6lJPcD8JQXvZyl-lolvyDPg0-JlKxgnuUgSekudASV19Gofpv8A', '2018-07-05 02:48:52'),
(43, 'eammcebZlGM:APA91bE_dd3egiocW7UgULWefS1zXFVBVlucUeQJKc8gQyjcIcGIk9qJ6170_A3JCEkHXHQDRPxHPNwva1fa2zejWkY1-7fcgSB-e7X7ablr2rgA1ByodGGw-EIix1k856FoHyKXYPdV-dBc_CAJPt4k52o2s-hhaQ', '2018-07-06 01:27:37'),
(44, 'fsQL95a5ofo:APA91bGjcrCS6bQwjGxXf20UM8pAWJW9ugUNc-7GzZjgexqd9VzeZ9qcO7UtXh_QV831UrRus_erGTOygn5VV5-v_9A4peScVUzODF3rZOi0zWriGi1O3JArvRdKwqvisHHTsIg_vUloXm37yzN3gv2EbPUOiIXPgg', '2018-07-06 01:30:41'),
(45, 'dQrqtc6lNOs:APA91bFUiH0T1NhMaRpdYRlcwIR7m-QYWPBjE62w7GDZtsm1lmUhSbeR_jD5oeSynF1WJxWqaNRl3EmxxKTnqF04Z4DE9XJHQGQ_jOPFZRuhNuLQtowvnNWqt3XnQe7HGQizZ0rI5N5Nu0qsKduXzHB1fi9GvUkZXg', '2018-07-06 01:33:29'),
(46, 'euWvP-Uaf9I:APA91bG5iznWa5Xgfz5Y0MfTFggYdDxDEv-EgVX3NpxX-4KBDhQ_Ou7YOupIWgDkndHqSAZUE4vDaFF07Me20JOfYvF1yK9O6vH1IOjOgNeBus2QYYQaLmtLVesRAX6MM_1m0najnk2jerYOmlxq3OOi1YgGPzD0Uw', '2018-07-06 02:07:23'),
(47, 'cwYKFszCh88:APA91bEGfLFIzdgu9lb_SUFAd7EcmRd3My181LhMyXYTD6HuN4qVcfC4t9lhZ2KRMc82HQ_KXIIyAxJWNntPYbSQwGTuAm5xVx-HDPp5aMyL-ArdO1I7fuGrLHNefkfFzK7TkDuFgl2w30H2aaUVMJMbYugQzs2wdw', '2018-07-06 02:14:21'),
(48, 'd-QgAD1JzfQ:APA91bEXh1CN33aRi5WahJeoC09nAV9Ah1-7IUVhnfowkXdpc7aFUzsmrmGVp2LDo48rHs9EJ4GUUr3C32X8XOPbfo7kgjoUMJBx8szdHKxhi_m3uO-OIjHIWRQPkYXbSJNYJi1g4-XQWbS9QvutZkNfOYCX48T6Ug', '2018-07-06 02:23:19'),
(49, 'ebN0CXqpvSI:APA91bEQcL7LZyDzSG9BW1MObMFoB2z2T5UF-CLA8CRz0k2T85TTmxkMSuNcgU3UbTnnAaYGu_Ru8HXujjPSf8Jg1UjxsY2FPH4Xb-OXYLaHfWBA2Yr8IIYhoHxW2P_yXwt5v8UHK9PcuvwSWk0JpPISgvoNK_hhvA', '2018-07-06 02:29:12'),
(50, 'cgfjG87Ay_4:APA91bESXMYvZ44cQe79tDuC-TAX9Ruzcr4c2AtCypI13JmI94zzo7Tb6buvZ0XbF6ejIww5QgDSOG-fnRDF764ZPk_9I2zWwbgu-MkzjNuCEX7ymDKr_ckHfaybqXoblUGPT13Tf2gDCml_yUYTa4KY3Rz8vLXV2Q', '2018-07-06 02:35:10'),
(51, 'eStuWMM3M7I:APA91bH42NTw_mbTDKnA95xI-3p9rIS2LeCdJ8JgmMBPvW9VwuRjB9ClCFMK0zgk1boQ2qZ_xMZyoTpb0mJAxdw0VTsXzDszckPzfL-IS6M6pvPm4sD49tBbzBnbTYPBw04x7ZHSuKNWcuFvIJEKFgBTv6Hk3jBP4g', '2018-07-06 02:39:05'),
(52, 'cR4B0gsh_NY:APA91bG8cikUxgUCYtSb4a_OSdaB2RSUc_A5nEWA9LoYZUxlNVYU4tA-CfFD6dF-u6goIn1IdTDW1anO77bbyQy2dXgkwQYaeIc5r68DvSGNP-zwwEuMDml1IdSNWkb-AyD-7DrxcqTpY5LlUUdk7v1cMmkHuE3slA', '2018-07-06 02:41:15'),
(53, 'egDuclYtPyg:APA91bEVzZziBze20xnSUUmNENbJKZ4y6lwITN9egr8VCI3ZaWQWZGDNuOpwKkWkBnZRhRtAZGH09iliD3lVpIWCgo_HPjDFc1ASEu_ZllGQ-l2cNCULd7kGvb1qsaCl3stCUA8YmjROJ25E0271t0ka8cZJr_7Pmg', '2018-07-06 02:46:46'),
(54, 'es2MYm8oc5o:APA91bEg2Ez7tk5_zBiP_ZcNSsa9KyIRqNtHKFf4HTuI1BDFyhXTyB6MvcAv1tbqBW9Yfs0O-gXbOfbHa7976SzUEKSCEEaFAQD_xbXnzX6G_TZAUTtzfYpJSUiPswCezcSgZycRWkk5XZh7mu7__QqnsTy4aTBkyA', '2018-07-06 02:48:50'),
(55, 'fDySmVPZyW8:APA91bFP3sprYfH24RhAQek7AK2DY0RN56eQY19DJYxPVRpLM9trVYXL3Rsrk7USGs_u_diCxR3TvQzlb7fqE0V5V7lpUdfywUn9pqSCQgumUNUNd5PGy4jETWPPRaTk-VLNjScvxE1zrgpn8f1ReyiQd7B_Xgq-bg', '2018-07-06 02:50:13'),
(56, 'eh2sUVC7jtY:APA91bHgmPX84diy3mewG6DJtP3sgrsegXKZL_t5SzS0Kitc3bBHLSvF-O3Jnb-yElZX8C3-GknzLDmtI9DoC6ojWtkRA2kPJ4SKPozNxmBarwrGBTgpoIjpN--kgP7zMboci83j6fdCVc5DWA__7YkkQpHtinvWbQ', '2018-07-06 02:53:12'),
(57, 'cAGFRpbwbrk:APA91bGfB-SuuTxdRBw9Y4SZuIPxqs2wE21q-mkKlERVSgbFMty0e6y0YY_K-66uLhgXvvKjdZ_v_itUzB2ijMlSPJXqUr0k39y-CTQoyLGvzwryjSPWKe6yuMIJ6Ai8LYYgkF_gH5B72RqGqOQ0ZKA7Tpcm41obGQ', '2018-07-06 04:25:36'),
(58, 'dEab12ykMaQ:APA91bHhad0WFfSRAmWSY-XHaEZBOWFVDKCwg89_ZOenE6Jb0ARFcd_9sy_tmSDT0R5jmkJSC5ul7p8rvB-JtblSvwmrS33MS2vNK14rP7rGe-GhT1G8sLXVRRDDTiBBTgkJo4gwqeB4zjS0J3mqypdZz1M3NRsWzQ', '2018-07-06 04:42:29'),
(59, 'eDmOein4m-w:APA91bF72FdBQisH9IGus6pH_a6Uq7SgaGf7Ei-MyvGGHcG-R1hPXHxMj_4iPExGA6gOieNmn4AshBkQf2kRRoFRIa-l1DHCdpe760-TO66GNOwb3z3ISvZiD8qOynxUBxUvnc-qTLGygplOQ6XDOFAkJU6jMHVIHA', '2018-07-06 05:09:55'),
(60, 'd1ZM5ufq6I0:APA91bGO1ouAoN4zRZFH3xLhiFswkRptqIrv7czchEyvwHO-cJD1gn-KulmG9Pc-LeJp5wvinV3GJ1AbZUGUMHLaEVrXzDP5UqAwm9SVVFyXWMhT9hY3ZT15RagXDabgCOTs_3k2KGADNQgQOwBqmSdNNY6gC7Q27Q', '2018-07-06 05:11:13'),
(61, 'cGlynxUEHHc:APA91bElkNGkthgZdRIS7V19w7ttSz0r0NNocfL-SW1OBLatF2-sxZmC2dwWZuvffb-Cqjs1z3A1b77pnRVfS54z10EV9_i22ZkTO4hDDMLPMbnEgqg2wmgW5gAt-BfLtvGGt08xTB7YVtyFmRRPVTLdcnvkl33KrQ', '2018-07-06 05:18:56'),
(62, 'cZPHM4v3Vi8:APA91bHR5razsfV_B2g4IBTb5eVB2SBoBazHnti2KI4RSCyJ0KM5hOqJ6IcMHJSTXbndlRgvruFJzagFa1hzycNXudwbbl_An4BfZeSlRQXw9epfpAVKB59jt7VFS5kGib7NRbuJvlkJWHSJwJYR3gw3oCTdtTWioQ', '2018-07-06 05:47:33'),
(63, 'c1a6Pucd7GU:APA91bGb152itVMptOF0q8inWod7IBM8eFfhfzVBae5be-FjwU-niCwOIGDdF0gE5_eG5FtyUA-Srs5lkBVdGXk673h-9u6K-FI6VgSPyFK9dgbs4SFDMYkdu68dkvHc2lZLEKY1Irk8Ef0WQX9OVQwncIG4fPN-cA', '2018-07-06 05:51:15'),
(64, 'd-l6jQwEIns:APA91bF75QpWm1JcPv-lfFil28_hDRpe1wboxT3qy4JCE3dz1jXKCHBJa0uOVeopYa78NGu3AgvGe1pSor9MqQhD_6nXga3IgKdEcti3O07y46XDlYEfU4GWPl5ZC70Ejg-FS4ta6xT8twKxBoF9oereo0o0GWJ0uw', '2018-07-06 06:01:37'),
(65, 'ejGDl7oDObk:APA91bHorx2ADIMsA4ugO7AB-cBFF_h7XyrlHiB5gkv374hbbdobBM65-dEjt8ZQT1HnMf_SLLN9xkf_M-XaHZ6U9U56SmMxjYYaf6dIvcI0c4qWjJVHotJJUw94x1xGxj5jOS7RBfTZ7E5xDSBD_Ry2VpNJzrbB6w', '2018-07-06 11:19:09'),
(66, 'fnsTL4-1ag8:APA91bHPFsms7xvs9iOXY4IKaS0cRoGfekQtAVrjsNDRuwxPqn-buSY9rD6uTcj_ea_iZJj3kJsVSmcRzDplNECjx1Mf0Dyn_LvzB5aiiu36lsKAtGxis5iJsJ2_KVgg049yYtp1u8HLP8OMEB5tzv8w5HHwBa3vzg', '2018-07-06 11:21:20'),
(67, 'dKHuWdWLSDA:APA91bHQ8nUxh7dRIEvYkd2ngd5bEhQkE7MQF2LN8Yd4eGJBSV52BAtZQC_yBjhY5JZKVsuXHYghgVoBou84oF7yvI70dXAM12xz3UEZVVGECEx0F9-Qpslm-nZdkbiU_6zXAj_PVfJJ8IlHNru3kWbsDMtzEWEN1w', '2018-07-06 11:23:37'),
(68, 'flzhneK3DA0:APA91bEx8CWI-Hv-IzwfPbV00x7wKYz80TaWvX7VO5E8b2QC3lu_lyQGVUWULKbn9G98vioY0Hxkd_gUmH2PHbPiX_S4AkFQxe8OGLWCt_7nGgxx6GIXtbnD3-fhVfEYEkLS7zxfgKYUlZ5mOeefjoouUmOh9GETrA', '2018-07-06 11:52:38'),
(69, 'cDq5yC0Dr80:APA91bHLkAmaQfawIfGBaVtJ-3gXF6e3WemHWO1O-yTk_ubaD24YYib6YQ7nncbitSGT-eXO5mAaMY0i8ESOFgMcItaZZPJY1HDmjdvSgsmzzDhmGhLmSFJjxtzU_kV5sHtGFLchQWhPh_G4st8E56_2SZdxUPrTeA', '2018-07-06 11:54:32'),
(70, 'eaA9k_9JKuM:APA91bFVzF5lML9zvyvamLRka5hm6gmD-jv2elQwDZV1Qe99rBN6fM-qWyZXfTpsi8w8Gc4qKJvdH_qWFKyV20Wv1bF-kTZRtRgZ8GXGzgYjQli1IoDMw43PdOrxw_FAcZPFrtB5vLdX96sWqWaZgZb3DReAMPiBkA', '2018-07-06 11:56:49'),
(71, 'dr_Pei2zcbY:APA91bFc9bI8Bvn-jRJYp0dWWw7GP7yFGRMt3NxvzBpC73_Qvf2J5kOKnBfNiTPSyTWIYTW_cNOMrKxET-5UMdH4AaTykaIG92hcnMJrdzXEkX2cJWmZVpje8byrdnhgazDmlavHg2GOR_vIa5jEDcBC1R2TfCF0Lw', '2018-07-06 12:06:38'),
(72, 'cG7jUV44nNE:APA91bFgw6xEf29rjPTessRVXwlQ-NC3qgPl7TqwFY901f5V-Wz14og6YMNZS0o7XmUrFPiuPJpI14Q0XYk0ZHO1fycylW2lp9itRRBLKgQFzKgDm_4D-mUVSohm5CM9UCiADnx8DOES_UZ5eBtJO7hQ0K4j1IJzZg', '2018-07-06 12:13:22'),
(73, 'emWbaqlBp5M:APA91bGKrqa_kfIg55-uXtULFhh9ljcYpVu5hdlxDhLSrEmBrdLjT4qmL_FZwBXNsFc-10xiESoMSLa22FNHBp5uw8y9j3yO5Id_UkfUMQWf21bx0wP0AB-zx5y2KItG398aEsO5w1-btZZvy6S-Zz5jb3styHgt-w', '2018-07-06 12:16:07'),
(74, 'cwx99rSvWKA:APA91bFmNL0xdmGwESyvgROWO8QJzbPhr_8-0L3dV-WY9Ofs631rhzUGxRwWdxSOeHHq_uqesYc4N7Vl7xwvAr7Thrp_CjnNJTK6s3W43NVnCV69CrtPJ_FjrIhLeTs-ysrrC8Qe73YcVBX9ozJeGwYb71Z-8KMnLA', '2018-07-06 12:19:09'),
(75, 'fPRL1M_sk4g:APA91bFgkBCFrTchj7rhA74rW3I-FLvJ_1OlmCeNM-8yrmVxCmy8xxhWp_7_JdpNbWJt4HRba18GsHsnvs4bOt5QX5pIH4T3HogjBAm8pTT0AyB6Xx2FniZM036-jLg7SMPGhyFDAZTwjEmQPL08RjEepwudDSKMeA', '2018-07-06 12:36:34'),
(76, 'dNpCugBBLnk:APA91bGU7HakMV0-YPBkM2IUnOUwWS_S6uT_NVL-PleHz0GBNWfhWVV1swWbfTSCjRwXojr2CcITcKgDXbZBfsNWKc36jbS1goM2ihjZNwh81x0XqXqFSo2CMcuefjl_VhPYrByBR6Fec75w83DwRfTvQco_bLsHWA', '2018-07-06 12:41:35'),
(77, 'epwEa-5lKrM:APA91bFf6X92KmjSxQd03oHhUfFbk3Iyxop7Bc2Iim6A7-sahD1LH9V3KMswnDACYVu5RbrjNTsq9MlFL-ufodPK5gMeaB3mHoF0T_VsNKD7uwQP9XvRoebFcMfULuyOKHfxBNfBjvc6ftjjWQcSGCrVpa34rY56_w', '2018-07-06 12:45:30'),
(78, 'e6WxsdSwF3Q:APA91bFwNHSlmfp-iJJck49apRVvoYG4GQHW6IOF0gRbG45Suyb7CeRkwS2dR-yV8lDAfz3wSk82y9EqMvPEL-l0mN6Nnt_N_uyoGK_EtmS1v_isRwuyq6XrFRTLLyfhCs3zfmDcdnDAckSqDocaibfAsPEZQRqrvg', '2018-07-06 01:00:23'),
(79, 'eytRl4UqdnY:APA91bEJTnfJoorF1vWEK6fOHprtWE1i8E8496e1xIvXjxxzB_6hooIpfKc7X0s_LAxXceDViwQy9P0yfXHHi_dMcONEXr6HzqrQvNCvoOoKm_7oKh5IwTFkr9YdIvYKiULU4Sa2h9lgsGx--mU74_BKiR6J7N8a4w', '2018-07-06 01:01:17'),
(80, 'cT0E9hGueLc:APA91bFYB82J15C0xRAgLkoOW9rR0aUb2Xf9zCj3oSnl5d_0Yyr3ihaSA_V8M90HyLxpX9hpItoENeVXvQz_wB_pKlJWk6mici2c2jGNMPXdZEnZOHHaZqccI_3tAXgjqg48lfR41dzG', '2019-06-22 10:04:30');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `MessageID` int(11) NOT NULL,
  `SenderID` int(11) NOT NULL,
  `ReceiverID` int(11) NOT NULL,
  `Message` varchar(255) NOT NULL,
  `MessageTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`MessageID`, `SenderID`, `ReceiverID`, `Message`, `MessageTime`) VALUES
(1, 1, 3, 'hi', '2018-01-28 15:49:04'),
(2, 3, 1, 'hello', '2018-01-28 15:49:12'),
(3, 1, 3, 'How are you?', '2018-01-28 16:01:05'),
(4, 3, 1, 'I am fine.', '2018-01-28 16:34:40');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(999) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `phone`) VALUES
(7, 'Sakib', 'sakib@mail.com', '$2y$10$HWRX4.40LB2ROTkzp0x7xuZzKc6QPvvAdevFS79UIWO0G5xO0puBO', '01100112233'),
(8, 'Shihab', 'shihab@mail.com', '$2y$10$BYJiAhkOsrBrIrsxhNhb0.ni0VeR3C.QiOxxGBjA7x1Su/drrTeL6', '01100112235'),
(9, 'tazin', 'tazin@mail.com', '$2y$10$hhfLzxuOrWKrP9huhDbOeOC1c.GG06Qe3DRGnsQe55tiSWOYJ.nD2', '01100112237'),
(10, 'Ahnaf', 'ahnaf@mail.com', '$2y$10$M7/PkIKFsPrRzdzn1WR6DumvBK4zL/4YX4clzPKgifHzunSFPiv9a', '01100112236'),
(11, 'Reem', 'reem.nsu@gmail.com', '$2y$10$mneOfo1zcn7sPC0ikfpwEOlue4fYEsHkTuo1VQYBwKAVKvfdehQme', '01100112234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arduinodata`
--
ALTER TABLE `arduinodata`
  ADD PRIMARY KEY (`TokenId`);

--
-- Indexes for table `emergencycontacts`
--
ALTER TABLE `emergencycontacts`
  ADD PRIMARY KEY (`ContactId`);

--
-- Indexes for table `fcmtokens`
--
ALTER TABLE `fcmtokens`
  ADD PRIMARY KEY (`TokenId`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`MessageID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arduinodata`
--
ALTER TABLE `arduinodata`
  MODIFY `TokenId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `emergencycontacts`
--
ALTER TABLE `emergencycontacts`
  MODIFY `ContactId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `fcmtokens`
--
ALTER TABLE `fcmtokens`
  MODIFY `TokenId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `MessageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
