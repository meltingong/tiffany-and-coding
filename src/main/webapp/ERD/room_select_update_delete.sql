select msgcontent,msgsendtime,msgread from chatMsg where userid='test1';
select count(*) from chatMsg where room_no=2 and msgread=0 and userid='test1';
select msgcontent,msgsendtime,msgread from chatMsg where room_no=2 and msgread=0 and userid='test1';
select msgcontent,msgsendtime,msgread from chatMsg where room_no=1;
insert into room(room_no,room_name,from_id,to_id)
values(room_room_no_SEQ.nextval,'테스트3번방','test3','test1');
delete from room where room_name ='테스트3번방';
select r.room_no, r.room_name,c.msgcontent from room r join chatMsg c on c.room_no = r.room_no where c.userid='test1';
select * from room where room_name='테스트3번방';
select * from room where room.from_id='test1' and room.to_id='test3';

update chatMsg set msgread=1 where msgread=0 and room_no=1 and userId = 'test1';

select count(*) from chatMsg where room_no=1 and msgread=0 and userid='test1';

