# lang-tools

    create table parts_of_speech ( word string , parts array<string> ) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\001' COLLECTION ITEMS TERMINATED BY '\002';

    load data local inpath '/home/ecapriolo/out.txt' into table parts_of_speech;

Usage
----

    hive> select * from parts_of_speech limit 10;
    OK
    3-D	["A","N"]
    4-F	["N"]
    4-H	["A"]

    hive> select * from parts_of_speech where array_contains(parts, 'N') limit 10;
    OK
    3-D	["A","N"]
    4-F	["N"]
    4-H'er	["N"]
