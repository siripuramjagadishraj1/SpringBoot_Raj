Upload 2 jar files..
--------------------	
	1. commons-fileupload-1.4.jar
	2. commons-io-2.6.jar



SQL:
----
	select sid,
	 CASE when sid<3 THEN sid else null END as small,  
	 CASE when sid>=3 THEN sid else null END as big, sname from sam; 
	 
	select * from sam sa 
	 where 2=(select count(distinct balance) from sam where sa.balance<=balance);
	 
	SELECT sid, balance, 
	dense_rank() over (ORDER BY balance DESC) AS Ranking
	FROM sam;
 
PL-SQL:
-------
	create function inc(val integer) returns integer as $$
	begin
		return val + 1;
	end; $$
	language plpgsql;
	select inc(5)
	drop function inc(val integer)

	create or replace function inc(val integer) returns table(si int, sn varchar) as $$
	
	declare
		v_1 int;
		v_2 int;
	begin
	return query select sid as si, sname as sn from sam;
	end; $$
	language plpgsql;


	select si, sn from inc(5);


NORMALIZATION:
--------------
	1st NF: No CSV
	2nd NF: No Partial Dependencies
	3rd NF: No Transitive Dependencies
	BCNF
	4th NF:
	5th NF:
	6th NF:

TRIGGER:
-------
	CREATE TRIGGER last_name_changes
		BEFORE|AFTER UPDATE
		ON employees
		FOR EACH ROW
	EXECUTE PROCEDURE log_last_name_changes(); 
	
	
	CREATE OR REPLACE FUNCTION log_last_name_changes()
	  RETURNS trigger AS
	$BODY$
	BEGIN
	   IF NEW.last_name <> OLD.last_name THEN
	       INSERT INTO employee_audits(employee_id,last_name,changed_on)
	       VALUES(OLD.id,OLD.last_name,now());
	   END IF;
	 
	   RETURN NEW;
	END;
	$BODY$
	
SQL QUERRIES:
-------------
	Nth Highest:
	* select * from sam sa where 2=(select count(distinct balance) from sam where sa.balance<=balance);
	* select * from(select sid,rownum rn from sam) where rn=2;
	
TRIGGER:
--------
	CREATE TABLE COMPANY(ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL );
		insert into company values(1, 'EPAM');
		insert into company values(2, 'Terrasoft');
		insert into company values(3, 'IBM');
	 
	CREATE OR REPLACE FUNCTION auditlogfunc() RETURNS TRIGGER AS $test_xyz_example$  
	    BEGIN  
	        --INSERT INTO AUDIT(EMP_ID, ENTRY_DATE) VALUES (new.ID, current_timestamp);  
			update company set NAME='JAGA' where ID =new.ID;
			raise notice 'ID : %', new;
	        RETURN NEW;   
	    END;
	$test_xyz_example$ LANGUAGE plpgsql;  
	
	CREATE TRIGGER example_trigger AFTER INSERT ON COMPANY  
	    FOR EACH ROW EXECUTE PROCEDURE auditlogfunc();
	
	Syntax:
	CREATE TRIGGER 
		example_trigger[TRIGGER_NAME] 
		AFTER [AFTER/BEFORE/INSTEAD OF]
		INSERT [INSERT/UPDATE/DELETE/TRUNCATE]
		ON COMPANY [table]
		FOR EACH ROW EXECUTE PROCEDURE auditlogfunc();
	
STORED PROCEDURE:
-----------------
	DO $$ 
	BEGIN 
	  RAISE DEBUG 'debug message %', now();
	  RAISE LOG 'LOG message %', now();
	  RAISE NOTICE 'NOTICE message %', now() ;
	  RAISE INFO 'INFO message %', now();
	  RAISE WARNING 'WARNING message %', now();
	  RAISE EXCEPTION 'EXCEPTION message %', now();
	END $$;

Input, Output, Declare, IF, While, for, Cursor:
-----------------------------------------------
	select * from get_table('One')
	
	CREATE OR REPLACE FUNCTION get_table (p_pattern VARCHAR) 
	   RETURNS TABLE ( ids integer, names text ) 
	AS $$
	DECLARE
		var_r varchar := 'JAGA';
		queryStr varchar := 'SELECT id, name from company where name = || var_r || ';
	BEGIN
		RAISE NOTICE '%',var_r;
		
	   RETURN QUERY queryStr ;
	  
	END; $$ 
	LANGUAGE 'plpgsql';

INDEXES:
--------
	CREATE INDEX c_index ON company USING hash (column_name);  -- B-tree, Hash, GiST and GIN

Views:
------
	* View are of two types Updatabe and Read-Only, default is Updatabe view.

	
