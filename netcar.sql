-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 17-Jan-2019 às 14:12
-- Versão do servidor: 10.1.37-MariaDB-0+deb9u1
-- PHP Version: 7.0.33-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `netcar`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agendamento`
--

CREATE TABLE `agendamento` (
  `cd_agendamento` int(11) NOT NULL,
  `cd_usuario` int(11) NOT NULL,
  `cd_tpveiculo` int(11) NOT NULL,
  `cd_servico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `agendamento`
--

INSERT INTO `agendamento` (`cd_agendamento`, `cd_usuario`, `cd_tpveiculo`, `cd_servico`) VALUES
(1, 1, 2, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `cd_servico` int(11) NOT NULL,
  `servico` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `servico`
--

INSERT INTO `servico` (`cd_servico`, `servico`) VALUES
(1, 'lavagem'),
(2, 'americana'),
(3, 'aspiração'),
(4, 'geral');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tarifa`
--

CREATE TABLE `tarifa` (
  `cd_tpveiculo` int(11) NOT NULL,
  `cd_servico` int(11) NOT NULL,
  `preco` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tarifa`
--

INSERT INTO `tarifa` (`cd_tpveiculo`, `cd_servico`, `preco`) VALUES
(1, 1, 10),
(1, 2, 20),
(1, 4, 15),
(2, 1, 15),
(2, 2, 30),
(2, 3, 10),
(2, 4, 20),
(3, 1, 15),
(3, 2, 35),
(3, 3, 10),
(3, 4, 20),
(4, 1, 25),
(4, 2, 50),
(4, 3, 10),
(4, 4, 40),
(5, 1, 20),
(5, 2, 50),
(5, 3, 20),
(5, 4, 40),
(6, 1, 50),
(6, 2, 150),
(6, 3, 30),
(6, 4, 70),
(7, 1, 15),
(7, 2, 40),
(7, 3, 15),
(7, 4, 30);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_veiculo`
--

CREATE TABLE `tipo_veiculo` (
  `cd_tpveiculo` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo_veiculo`
--

INSERT INTO `tipo_veiculo` (`cd_tpveiculo`, `tipo`) VALUES
(1, 'moto'),
(2, 'passeio'),
(3, 'suv'),
(4, 'caminhão 2 eixos'),
(5, 'van'),
(6, 'micro-ônibus'),
(7, 'pick-up');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `cd_usuario` int(11) NOT NULL,
  `nome` varchar(245) NOT NULL,
  `endereco` varchar(245) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `fixo` varchar(45) DEFAULT NULL,
  `senha` varchar(8) DEFAULT NULL,
  `nivel` int(11) DEFAULT '0',
  `idt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`cd_usuario`, `nome`, `endereco`, `celular`, `fixo`, `senha`, `nivel`, `idt`) VALUES
(1, 'Manoel', 'Rua da laguna', '99988-7766', '3312-4567', NULL, 0, 0),
(2, 'Joaquim', 'Travessa Arlequim', '98765-4321', '3344-5566', NULL, 0, 0),
(3, 'Lorena', 'Rua Feitosa', '99856-9090', '3107-7788', NULL, 0, 0),
(5, 'Jose Pereira', 'tv barcelos', '99856-0000', '5390-6723', NULL, 0, 0),
(6, 'Thiago Bruno', 'Rua da Pátria', '99856-9090', '3107-7788', NULL, 0, 0),
(7, 'Jose Alfredo', 'Rua Ibirapora', '2345-6789', '9900-8875', NULL, 0, 0),
(8, 'Suzane', 'Rua da Pedreira', '99999-3333', '2233-8899', NULL, 0, 1111111111);

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `cd_veiculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agendamento`
--
ALTER TABLE `agendamento`
  ADD PRIMARY KEY (`cd_agendamento`),
  ADD KEY `fk_table1_usuario1` (`cd_usuario`),
  ADD KEY `fk_agendamento_tipo_veiculo1` (`cd_tpveiculo`),
  ADD KEY `fk_agendamento_servico1` (`cd_servico`);

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`cd_servico`);

--
-- Indexes for table `tarifa`
--
ALTER TABLE `tarifa`
  ADD KEY `fk_tarifa_tipo_veiculo` (`cd_tpveiculo`),
  ADD KEY `fk_tarifa_servico1` (`cd_servico`);

--
-- Indexes for table `tipo_veiculo`
--
ALTER TABLE `tipo_veiculo`
  ADD PRIMARY KEY (`cd_tpveiculo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cd_usuario`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`cd_veiculo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agendamento`
--
ALTER TABLE `agendamento`
  MODIFY `cd_agendamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `cd_servico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tipo_veiculo`
--
ALTER TABLE `tipo_veiculo`
  MODIFY `cd_tpveiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cd_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `cd_veiculo` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `agendamento`
--
ALTER TABLE `agendamento`
  ADD CONSTRAINT `fk_agendamento_servico1` FOREIGN KEY (`cd_servico`) REFERENCES `servico` (`cd_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_agendamento_tipo_veiculo1` FOREIGN KEY (`cd_tpveiculo`) REFERENCES `tipo_veiculo` (`cd_tpveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_table1_usuario1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tarifa`
--
ALTER TABLE `tarifa`
  ADD CONSTRAINT `fk_tarifa_servico1` FOREIGN KEY (`cd_servico`) REFERENCES `servico` (`cd_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tarifa_tipo_veiculo` FOREIGN KEY (`cd_tpveiculo`) REFERENCES `tipo_veiculo` (`cd_tpveiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
