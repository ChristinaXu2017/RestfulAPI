-- Create table lab360
CREATE TABLE lab360 (
 id INT AUTO_INCREMENT PRIMARY KEY,
  miR_ID VARCHAR(2550),
  miR VARCHAR(255),
  number VARCHAR(255),
  pure_number INT,
  new_pure_number INT,
  miRNA_order INT,
  sequence VARCHAR(255),
  chr VARCHAR(255),
  strand ENUM('+', '-'),
  start INT,
  precursor_seq VARCHAR(255),
  Total_mature_reads_include_extra INT,
  Total_star_reads INT,
  Total_LAB_mature_reads INT,
  Total_LAB_star_reads INT,
  Total_QLD_mature_reads INT,
  Total_QLD_star_reads INT,
  LAB_FLOWER_mature INT,
  LAB_FLOWER_star INT,
  QLD_FLOWER_mature INT,
  QLD_FLOWER_star INT,
  LAB_ROOT_mature INT,
  LAB_ROOT_star INT,
  LAB_SEED_mature INT,
  LAB_SEED_star INT,
  QLD_SEED_mature INT,
  QLD_SEED_star INT,
  read_same_direction INT,
  read_both_direction INT,
  genomic_location INT,
  pri_left_range VARCHAR(255),
  pri_right_range VARCHAR(255),
  structure TEXT
);

-- Load data from CSV file into lab360 table
LOAD DATA INFILE '/var/lib/mysql-files/LAB.csv' 
INTO TABLE lab360 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 LINES;
