Upload 2 jar files..
1. commons-fileupload-1.4.jar
2. commons-io-2.6.jar



====JAVA=8====
1. PermGen Space Replaced with metaspace
	*permGen was space of JVM's heap.		 ( -XX:MaxPermSize)
	*metaspace is part of native OS memory.  ( -XX:MaxMetaspaceSize)
	-Xms, -Xmx for Heap Memory
	-Xss for stack Memory
2. Interface Changes (Default and Static Methods)
3. Lambda Expressions
	Functional Interfaces
	Functional Arguments
	Lambda Predicate
	Examples
		Callables
		Runnable
		Comparable and Comparator.
4. forEach
5. Pipelines and Streams

6. Date and Time (java.time package)
7. Type Annotations.
8. Nashron JavaScript engine.
9. Concurrent Accumulators
10. Parallell Operations

11. TLS SNI
12. Method references

13. Optional
14. String Joiner
15. Collectors
16. Stream Filter
17. Base64 Encoding
18. Java Parallell array sorting


====SQL====
select sid,
 CASE when sid<3 THEN sid else null END as small,  
 CASE when sid>=3 THEN sid else null END as big, sname from sam; 
 
select * from sam sa 
 where 2=(select count(distinct balance) from sam where sa.balance<=balance);
 
SELECT sid, balance, 
dense_rank() over (ORDER BY balance DESC) AS Ranking
FROM sam;
 
==PL-SQL==(1)
create function inc(val integer) returns integer as $$
begin
	return val + 1;
end; $$
language plpgsql;
select inc(5)
drop function inc(val integer)

==PL-SQL==(2)
create or replace function inc(val integer) returns table(si int, sn varchar) as $$

declare
	v_1 int;
	v_2 int;
begin
return query select sid as si, sname as sn from sam;
end; $$
language plpgsql;

select si, sn from inc(5);


====SQL====
List<String> 
		 numList3 =  numList.stream()
							.filter(each -> (each!=5))
							.limit(7)
							.skip(2)
							.map(eachs -> eachs.toString())
							.collect(Collectors.toList());
