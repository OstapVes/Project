CREATE SCHEMA IF NOT EXISTS `mydb`;
USE `mydb` ;


CREATE TABLE IF NOT EXISTS `mydb`.`investment_company` (
  `idinvestment_company` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `website` VARCHAR(45) NULL,
  `date_of_foundation` VARCHAR(45) NOT NULL,
  `address_of_the_head_office` VARCHAR(45) NULL,
  `head_of_the_company` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idinvestment_company`));

CREATE TABLE IF NOT EXISTS `mydb`.`investment_manager` (
  `idinvestment_manager` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `identification_number` INT NOT NULL,
  PRIMARY KEY (`idinvestment_manager`),
  UNIQUE INDEX `identification_number_UNIQUE` (`identification_number` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS `mydb`.`stages_of_startup_development` (
  `idstages_of_startup_development` INT NOT NULL,
  `seed_stage` TINYINT NOT NULL,
  `startup_stage` TINYINT NOT NULL,
  `growth_stage` TINYINT NOT NULL,
  `expansion_stage` TINYINT NOT NULL,
  `exit_stage` TINYINT NOT NULL,
  PRIMARY KEY (`idstages_of_startup_development`));


CREATE TABLE IF NOT EXISTS `mydb`.`application` (
  `idapplication` INT NOT NULL,
  `name_of_the_startup` VARCHAR(45) NOT NULL,
  `round` VARCHAR(45) NOT NULL,
  `name_of_the_investment_manager` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idapplication`));


CREATE TABLE IF NOT EXISTS `mydb`.`business_angel` (
  `idbusiness_angel` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idbusiness_angel`));


CREATE TABLE IF NOT EXISTS `mydb`.`business_incubator` (
  `idbusiness_incubator` INT NOT NULL,
  `number_of_seats` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `website` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idbusiness_incubator`));


CREATE TABLE IF NOT EXISTS `mydb`.`startup` (
  `idstartup` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `business_model` VARCHAR(45) NOT NULL,
  `competitors` VARCHAR(45) NOT NULL,
  `marketing_strategy` VARCHAR(45) NOT NULL,
  `amount_of_investment` VARCHAR(45) NOT NULL,
  `website` VARCHAR(45) NOT NULL,
  `founding_date` VARCHAR(45) NOT NULL,
  `twitter_address` VARCHAR(45) NOT NULL,
  `stages_of_startup_development_idstages_of_startup_development` INT NOT NULL,
  `application_idapplication` INT NOT NULL,
  `business_angel_idbusiness_angel` INT NOT NULL,
  `business_incubator_idbusiness_incubator` INT NOT NULL,
  PRIMARY KEY (`idstartup`),
  INDEX `fk_startup_stages_of_startup_development1_idx` (`stages_of_startup_development_idstages_of_startup_development` ASC) VISIBLE,
  INDEX `fk_startup_application1_idx` (`application_idapplication` ASC) VISIBLE,
  INDEX `fk_startup_business_angel1_idx` (`business_angel_idbusiness_angel` ASC) VISIBLE,
  INDEX `fk_startup_business_incubator1_idx` (`business_incubator_idbusiness_incubator` ASC) VISIBLE,
  CONSTRAINT `fk_startup_stages_of_startup_development1`
    FOREIGN KEY (`stages_of_startup_development_idstages_of_startup_development`)
    REFERENCES `mydb`.`stages_of_startup_development` (`idstages_of_startup_development`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_application1`
    FOREIGN KEY (`application_idapplication`)
    REFERENCES `mydb`.`application` (`idapplication`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_business_angel1`
    FOREIGN KEY (`business_angel_idbusiness_angel`)
    REFERENCES `mydb`.`business_angel` (`idbusiness_angel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_business_incubator1`
    FOREIGN KEY (`business_incubator_idbusiness_incubator`)
    REFERENCES `mydb`.`business_incubator` (`idbusiness_incubator`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    CREATE INDEX startup_founding_date on `mydb`.`startup` (founding_date ASC) VISIBLE;



CREATE TABLE IF NOT EXISTS `mydb`.`round_of_financing` (
  `idround_of_financing` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `amount_of_investment` VARCHAR(45) NOT NULL,
  `startup_idstartup` INT NOT NULL,
  PRIMARY KEY (`idround_of_financing`),
  INDEX `fk_round_of_financing_startup1_idx` (`startup_idstartup` ASC) VISIBLE,
  CONSTRAINT `fk_round_of_financing_startup1`
    FOREIGN KEY (`startup_idstartup`)
    REFERENCES `mydb`.`startup` (`idstartup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    CREATE INDEX round_of_financing_amount_of_investment on `mydb`.`round_of_financing` (amount_of_investment ASC) VISIBLE;




CREATE TABLE IF NOT EXISTS `mydb`.`investment_company_has_investment_manager` (
  `investment_company_idinvestment_company` INT NOT NULL,
  `investment_manager_idinvestment_manager` INT NOT NULL,
  PRIMARY KEY (`investment_company_idinvestment_company`, `investment_manager_idinvestment_manager`),
  INDEX `fk_investment_company_has_investment_manager_investment_man_idx` (`investment_manager_idinvestment_manager` ASC) VISIBLE,
  INDEX `fk_investment_company_has_investment_manager_investment_com_idx` (`investment_company_idinvestment_company` ASC) VISIBLE,
  CONSTRAINT `fk_investment_company_has_investment_manager_investment_compa1`
    FOREIGN KEY (`investment_company_idinvestment_company`)
    REFERENCES `mydb`.`investment_company` (`idinvestment_company`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_investment_company_has_investment_manager_investment_manag1`
    FOREIGN KEY (`investment_manager_idinvestment_manager`)
    REFERENCES `mydb`.`investment_manager` (`idinvestment_manager`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `mydb`.`investment_manager_has_startup` (
  `investment_manager_idinvestment_manager` INT NOT NULL,
  `startup_idstartup` INT NOT NULL,
  PRIMARY KEY (`investment_manager_idinvestment_manager`, `startup_idstartup`),
  INDEX `fk_investment_manager_has_startup_startup1_idx` (`startup_idstartup` ASC) VISIBLE,
  INDEX `fk_investment_manager_has_startup_investment_manager1_idx` (`investment_manager_idinvestment_manager` ASC) VISIBLE,
  CONSTRAINT `fk_investment_manager_has_startup_investment_manager1`
    FOREIGN KEY (`investment_manager_idinvestment_manager`)
    REFERENCES `mydb`.`investment_manager` (`idinvestment_manager`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_investment_manager_has_startup_startup1`
    FOREIGN KEY (`startup_idstartup`)
    REFERENCES `mydb`.`startup` (`idstartup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
 
 
 INSERT INTO mydb.application(idapplication, name_of_the_startup, round, name_of_the_investment_manager,status) 
 values(1,"Startup1","web","Andriy", "considered"),(2,"Startup2","backend","Anton", "considered"),(3,"Startup3","web","Anton", "considered");
 
 Insert into mydb.business_angel(idbusiness_angel, first_name, last_name)
 values(1,"Anton", "Antonovich"),(2,"Petro", "Petrovich"),(3,"Nazar", "Nazarovich");
 
 insert into mydb.business_incubator(idbusiness_incubator, number_of_seats, address, website)
 values(1, 2, "Lviv", "https://github.com/"),(2, 20, "Kyiv", "https://github.com/"),(3, 2, "Lviv", "https://github.com/");
 
 insert into mydb.investment_company(idinvestment_company, name,description,website,date_of_foundation,address_of_the_head_office,
 head_of_the_company)
 values(1, "Alala","Big investing", "investing.com", "2000y.", "Kyiv", "Anton Antonovich"), 
 (2, "Alalllllla"," investing", "investing.com", "2001y.", "Lviv", "Nazar Nazarovich"),
 (3, "blalalaalalalala","Small investing", "investing.com", "2002y.", "Kyiv", "Petro Petrovich");
 
 insert into mydb.investment_manager(idinvestment_manager, name, surname, identification_number)
 values(1, "Anton", "Antonov", 2345), (2, "Andriy", "Andriev", 7654), (3, "Nazar", "Nazarov", 344);
  
 insert into mydb.investment_company_has_investment_manager(investment_company_idinvestment_company, investment_manager_idinvestment_manager)
 values(1,2),(2,3),(3,1);
 

 
 insert into mydb.stages_of_startup_development(idstages_of_startup_development, seed_stage, startup_stage, growth_stage,
 expansion_stage,exit_stage)
 values(1, 0,0,0,0,0),(2, 1,0,0,0,0),(3, 1,1,0,0,0),(4, 1,1,1,1,1);
 
 insert into mydb.startup(idstartup, name, description, business_model, competitors, marketing_strategy, amount_of_investment,
 website, founding_date, twitter_address, stages_of_startup_development_idstages_of_startup_development,
 application_idapplication, business_angel_idbusiness_angel, business_incubator_idbusiness_incubator)
values(1, "Name1", "lalaalalala", "model", "Not", "stategy","1k$","invest.com", "2002y.", "twitter//aaa", 1,1,1,1),
(2, "Name2", "lalaalalala", "model", "Not", "stategy","1k$","invest.com", "2020y.", "twitter//aaa", 3,1,2,1),
(3, "Name3", "lalaalalala", "model", "Not", "stategy","1k$","invest.com", "2009y.", "twitter//aaa", 1,2,2,3); 
 
 insert into mydb.round_of_financing(idround_of_financing, name, amount_of_investment, startup_idstartup)
 values(1, "A", "1mln$", 1), (2, "C", "1k$", 3), (3, "B", "100k$", 2);
insert into mydb.investment_manager_has_startup(investment_manager_idinvestment_manager, startup_idstartup)
 values(1,2),(2,3),(3,1);