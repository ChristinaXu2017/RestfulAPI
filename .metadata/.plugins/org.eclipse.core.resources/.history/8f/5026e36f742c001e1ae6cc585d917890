
insert into lab360_mirna (ID, pureNumber, sequence, precursor_seq, structure, chr, strand, start)
values(1,'mir156', 'ACAGAAGAUAGAGAGCACAG(ACAGAAGAUAGAGAGCACAG)', 'tgttgAcagaagatagagagcacaGataatgaaggcatgaaaattatctgtacctcactcctttGTGCTCTTTATTCTTCTGTCAtcatcctcaactcccttcatgtag', '((.(((((((((((((((((((((((...(((..(((.((......)))))...)))....))))))))))))).)))))))))).))......................', 'contig01', '+', 3);



	@Id
	@GeneratedValue
	private Integer id;
	
	//camel case the column name for findby method in repository
	//ok: "La360 findByPureNumber(Integer pureNumber);"  
	//error:  "La360 findByPure_number(Integer pure_number);"  
	@Column(name="pure_number")
	private Integer pureNumber;

	private String sequence;
	
	private String precursor_seq;
	
	private String structure;
	
	private String chr;
	
	private String strand;
	
	private String start;
miR_ID	miR	number	pure_number	new_pure_number	number	sequence(seq + mature from miRbase)	chr	strand	start	precursor_seq	Total_#mature_reads(include extra)	Total_#star_reads	Total_LAB_#mature_reads	Total_LAB_#star_reads	Total_QLD_#mature_reads	Total_QLD_#star_reads	LAB FLOWER(mature)	LAB FLOWER(star)	QLD FLOWER(mature)	QLD FLOWER(star)	LAB ROOT(mature)	LAB ROOT(star)	LAB SEED(mature)	LAB SEED(star)	QLD SEED(mature)	QLD SEED(star)	#read_same_direction	#read_both_direction	genomic_location(0:generetic,1:intron,2:exon)	pri_left_range	pri_right_range	structure
[gma-miR156g]	miR156g	156g	#NAME?	156	1	ACAGAAGAUAGAGAGCACAG(ACAGAAGAUAGAGAGCACAG)	NbQld183C02	+	81188970	tgttgAcagaagatagagagcacaGataatgaaggcatgaaaattatctgtacctcactcctttGTGCTCTTTATTCTTCTGTCAtcatcctcaactcccttcatgtaga	4	0	4	0	0	0	0	0	0	0	0	0	4	0	0	0	0	0	0	None	None	((.(((((((((((((((((((((((...(((..(((.((......)))))...)))....))))))))))))).)))))))))).))......................
[gma-miR156g]	miR156g	156g	#NAME?	156	1	ACAGAAGAUAGAGAGCACAG(ACAGAAGAUAGAGAGCACAG)	NbQld183C02	+	81185638	tgttgAcagaagatagagagcacaGataatgaagtgcatgaaaattgtctgcacctcactcctttGTGCTCTTTATTCTTCTGTCAtcatcctcagctccctttttttta	4	0	4	0	0	0	0	0	0	0	0	0	4	0	0	0	0	0	0	None	None	((.(((((((((((((((((((((((...(((.(((((.((......))))))).)))....))))))))))))).)))))))))).)).....................