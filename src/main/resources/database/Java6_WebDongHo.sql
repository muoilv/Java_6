create database [Java6_WebDongHo]
go
use [Java6_WebDongHo]
go



----- Bảng status -----
if object_id('StatusObject') is not null
drop table StatusObject
go
create table StatusObject(
id_status varchar (15) not null primary key,
name_status nvarchar (100) not null,
description_status nvarchar (500) null,
)
go

insert into StatusObject  values ('ST01',N'Tồn tại','')
insert into StatusObject  values ('ST02',N'Đã xóa','')
insert into StatusObject  values ('ST03',N'Đã thanh toán','')
insert into StatusObject  values ('ST04',N'Chờ xác nhận','')
insert into StatusObject  values ('ST05',N'Hết hàng','')

select * from StatusObject

----- Bảng gender -----
if object_id('Gender') is not null
drop table Gender
go
create table Gender(
id_gender varchar (15) not null primary key,
name_gender nvarchar (100) not null,
note_gender nvarchar (500) null,
status_gender bit default 1,
)
go

insert into Gender  values ('GD01',N'Nam','','1')
insert into Gender  values ('GD02',N'Nữ','','1')

select * from Gender

DELETE FROM Gender WHERE id_gender='CT04';

----- Bảng category -----
if object_id('Category') is not null
drop table Category
go
create table Category(
id_category int not null primary key identity,
name_category nvarchar (100) not null,
note_category nvarchar (500) null,
status_category bit default 1,
)
go

insert into Category (name_category,note_category,status_category) values (N'KASHMIR',N'Trẻ trung','1')
insert into Category (name_category,note_category,status_category) values (N'WEIMAR',N'Cổ điển','1')
insert into Category (name_category,note_category,status_category) values (N'COLOSSEUM',N'Mạnh mẽ','1')
insert into Category (name_category,note_category,status_category) values (N'MYKONOS',N'Khác biệt','1')

select * from Category 

UPDATE Category
SET status_category = 1
WHERE id_category='1';

DELETE FROM Category WHERE id_category='1';


----- Bảng role -----
if object_id('RoleUser') is not null
drop table RoleUser
go
create table RoleUser(
id_role int not null primary key identity,
name_role nvarchar (100) not null,
)
go

select * from RoleUser

----- Bảng product -----
if object_id('Product') is not null
drop table Product
go
create table Product(
id_product int not null  primary key identity ,
gender_id  varchar (15) not null ,
category_id  int not null,
status_id varchar (15) not null,
name_product nvarchar (200) not null,
price_product float not null,
image_product varchar (3000) not null,
strap_product nvarchar (200) not null,
case_diameter float not null,
type_machine nvarchar (200) not null,
color_product nvarchar (100) not null,
face_glasses nvarchar (100) not null,
description_product nvarchar (500) null,
constraint fk_Gender_Product foreign key (gender_id) references Gender,
constraint fk_Category_Product foreign key (category_id) references Category ,
constraint fk_StatusObject_Product foreign key (status_id) references StatusObject
)
go

insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )  
	values ('GD01',1,'ST01',N'KASHMIR - 40MM SWANK','1000000','abc.jpg',N'Dây kim loại',40,N'MIYOTA QUARTZ',N'Silver',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )  
	values ('GD01',2,'ST01',N'KASHMIR - 40MM ROBUST','1100000','abc.jpg',N'Dây kim loại',40,N'MIYOTA QUARTZ',N'Black',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )
	values ('GD02',3,'ST01',N'COLOSSEUM - 42MM MORTAR','1200000','abc.jpg',N'Dây da GENUINE',42,N'MIYOTA QUARTZ',N'Black',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product ) 
	values ('GD02',4,'ST02',N'MYKONOS - 32MM XANDER','1300000','abc.jpg',N'Dây thép không gỉ',40,N'MIYOTA QUARTZ',N'Gold',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )  
	values ('GD01',1,'ST02',N'KASHMIR - 40MM SWANK','1400000','abc.jpg',N'Dây kim loại',40,N'MIYOTA QUARTZ',N'Silver',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )  
	values ('GD02',2,'ST01',N'KASHMIR - 40MM ROBUST','1500000','abc.jpg',N'Dây kim loại',40,N'MIYOTA QUARTZ',N'Black',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product )
	values ('GD01',3,'ST02',N'COLOSSEUM - 42MM MORTAR','1600000','abc.jpg',N'Dây da GENUINE',42,N'MIYOTA QUARTZ',N'Black',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')
insert into Product (gender_id,category_id,status_id,name_product,price_product,image_product,strap_product,case_diameter,type_machine,color_product,face_glasses,description_product ) 
	values ('GD02',4,'ST01',N'MYKONOS - 32MM XANDER','1700000','abc.jpg',N'Dây thép không gỉ',40,N'MIYOTA QUARTZ',N'Gold',N'SAPPHIRE',N'Đồng hồ thời trang nam Kashmir Swank .Là người trẻ, bạn nghiêm túc trong công việc và năng động trong những hoạt động với bạn bè? Đồng hồ Kashmir Swank chính là sự lựa chọn hoàn hảo nhất. Thiết kế mặt đồng hồ màu xanh ấn tượng, kết hợp cùng dây kim loại bạc tạo nên một tổng thể trẻ trung, khác biệt, nhưng vẫn cực kì lịch lãm khi đi làm.')

select * from Product
 
----- Bảng user -----
if object_id('Users') is not null
drop table Users
go
create table Users(
id_user int not null primary key identity,
gender_id  varchar (15) not null ,
name_user nvarchar (100) not null,
email_user varchar (100) not null ,
password_user varchar (100) not null ,
phone_user varchar(15) null ,
avatar_user varchar (3000) null,
address_user nvarchar (1000) not null,
note_user nvarchar (500) null,
status_user bit default 1,
constraint fk_Gender_Users foreign key (gender_id) references Gender ,
)
go
insert into Users (gender_id,name_user,email_user,password_user,phone_user,avatar_user,address_user,note_user)
	values ('GD01',N'Lê Văn Mười','muoilvph11464@fpt.edu.vn','12345678','096161403','abc.jpg',N'Hà Nội','')
insert into Users (gender_id,name_user,email_user,password_user,phone_user,avatar_user,address_user,note_user)
	values ('GD02',N'Hoàng Quốc Nam','namhqph11964@fpt.edu.vn','12345678','0987654321','abc.jpg',N'Vĩnh Phúc','')
insert into Users (gender_id,name_user,email_user,password_user,phone_user,avatar_user,address_user,note_user)
	values ('GD02',N'Nguyễn Trọng Cường','cuongntph14964@fpt.edu.vn','12345678','0987895321','abc.jpg',N'Vĩnh Phúc','')
select * from Users

DELETE FROM Users WHERE id_user='1';

----- Bảng Authorities -----
if object_id('Authorities') is not null
drop table Authorities
go
create table Authorities(
id_user int not null,
id_role int not null,
unique(id_user,id_role),
primary key (id_user,id_role),
constraint fk_Users_Authorities foreign key (id_user) references Users ,
constraint fk_RoleUser_Authorities foreign key (id_role) references RoleUser ,
)

insert into Authorities(id_user, id_role)  values (1,1)
insert into Authorities(id_user, id_role)  values (2,2)
insert into Authorities(id_user, id_role)  values (3,3)
select * from Authorities

DELETE FROM Authorities WHERE id_user='2';
----- Bảng cart -----
if object_id('Cart') is not null
drop table Cart
go
create table Cart(
id_cart int  not null primary key identity,
id_user int not null ,
time_add date not null,
id_status varchar(15) not null,
constraint fk_Users_Cart foreign key (id_user) references Users ,
constraint fk_StatusObject_Cart foreign key (id_status) references StatusObject ,
)
go

insert into Cart (id_user,time_add,id_status) values (2,'7/27/2021','ST01')
insert into Cart (id_user,time_add,id_status) values (1,'8/20/2021','ST01')

select * from Cart
DELETE FROM Cart WHERE id_cart='1';
----- Bảng cart detail -----
if object_id('CartDetail') is not null
drop table CartDetail
go
create table CartDetail(
id int not null identity primary key,
id_cart int not null ,
id_product int not null ,
name_product nvarchar (100) not null,
price_product float not null,
quantity int not null,
into_money float not null,
status_cart bit default 1,
constraint fk_Cart_CartDetail foreign key (id_cart) references Cart ,
constraint fk_Product_CartDetail foreign key (id_product) references Product ,
)
go

insert into CartDetail (id_cart,id_product,name_product,price_product,quantity,into_money,status_cart) values (1,1,'aa',99,2,198,1)
insert into CartDetail (id_cart,id_product,name_product,price_product,quantity,into_money,status_cart) values (1,2,'product2',188,3,564,1)
insert into CartDetail (id_cart,id_product,name_product,price_product,quantity,into_money,status_cart) values (2,3,'product3',28,2,56,1)
insert into CartDetail (id_cart,id_product,name_product,price_product,quantity,into_money,status_cart) values (2,4,'product4',28,2,56,1)
select * from CartDetail
----- Bảng bill -----
if object_id('Bill') is not null
drop table Bill
go
create table Bill(
id_bill int not null primary key identity ,
id_user int not null ,
total_money float not null,
address_bill nvarchar (15) not null ,
time_add date not null,
id_status varchar(15) not null,
constraint fk_Users_Bill foreign key (id_user) references Users ,
constraint fk_StatusObject_Bill foreign key (id_status) references StatusObject 
)
go

insert into Bill (id_user,total_money,address_bill,time_add,id_status) values (2,198,N'hanoi','7/27/2021','ST01')
insert into Bill (id_user,total_money,address_bill,time_add,id_status) values (1,50,N'HCM','5/20/2021','ST01')
select * from Bill 


DELETE FROM Bill WHERE id_user='1';

----- Bảng bill detail -----
if object_id('BillDetail') is not null
drop table BillDetail

create table BillDetail(
id int not null identity  primary key,
id_bill int not null ,
id_product int not null ,
name_product nvarchar (100) not null,
price_product float not null,
quantity int not null,
into_money float not null,
status_bill bit default 1,
constraint fk_Bill_BillDetail foreign key (id_bill) references Bill ,
constraint fk_Product_BillDetail foreign key (id_product) references Product ,
)

insert into BillDetail (id_bill,id_product,name_product,price_product,quantity,into_money,status_bill) values (1,1,'ee',99,2,198,1)
insert into BillDetail (id_bill,id_product,name_product,price_product,quantity,into_money,status_bill) values (2,2,'2222',99,2,198,1)
select * from BillDetail

DELETE FROM BillDetail WHERE id_product=8;
