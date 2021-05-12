CREATE TABLE Store(
storeID INT AUTO_INCREMENT,
storeAddress VARCHAR(255) NOT NULL,
Phone_No VARCHAR(12) NOT NULL,
PRIMARY KEY (storeID)
);

INSERT INTO Store VALUES (1, 'Morrisville', 9193456789);
INSERT INTO Store VALUES (2, 'Cary', 9198246738);
INSERT INTO Store VALUES (3, 'Durham', 9193873902);
INSERT INTO Store VALUES (4, 'Cary', 9195163784);
INSERT INTO Store VALUES (5, 'Apex', 9197489034);
INSERT INTO Store VALUES (6, 'Raleigh', 9197320774);

CREATE TABLE Staff (
	staffID INT AUTO_INCREMENT, 
	storeID INT, 
name VARCHAR(20) NOT NULL, 
	Age INT NOT NULL, 
Job_Title VARCHAR(50) NOT NULL,
Address VARCHAR(50) NOT NULL,
	Email VARCHAR(20) NOT NULL, 
Phone_No VARCHAR(15) NOT NULL, 
	TimeofEmployment DATE,
	
	PRIMARY KEY (staffID),
FOREIGN KEY (storeID) REFERENCES Store(storeID)  ON DELETE SET NULL
);

INSERT INTO Staff VALUES (006, 1, 'Yin', 64, 'Admin','Raleigh' ,'yin@gmail.com', 9196749303, '2019-2-28');
INSERT INTO Staff VALUES (007, 2, 'Chloe', 40, 'Manager','Raleigh' , 'chloe@gmail.com', 9196746729, '2019-2-28');
INSERT INTO Staff VALUES (014, 4, 'Kamal', 28, 'Manager','Raleigh' , 'kamal@gmail.com', 9196342123, '2019-2-28');
INSERT INTO Staff VALUES (012, 3, 'Carey', 22, 'Manager','Raleigh' , 'carey@gmail.com', 9191236564, '2019-2-28');
INSERT INTO Staff VALUES (001, 1, 'Lee', 23, 'Manager','Raleigh' , 'lee@gmail.com', 9196742649, '2019-2-28');
INSERT INTO Staff VALUES (002, 1, 'Emily', 30, 'Cashier', 'Raleigh' ,'emily@gmail.com', 919748399, '2019-2-28');
INSERT INTO Staff VALUES (008, 2, 'Anthony', 34, 'Cashier','Raleigh' , 'anthony@gmail.com', 9196758493, '2019-2-28');
INSERT INTO Staff VALUES (015, 4, 'Victor', 45, 'Cashier','Raleigh' , 'victor@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (013, 3, 'Alex', 26, 'Warehouse Operator','Raleigh' , 'alex@gmail.com', 9196451283, '2019-2-28');
INSERT INTO Staff VALUES (009, 2, 'Amanda', 39, 'Warehouse Operator','Raleigh' , 'amandas@gmail.com', 9196746349, '2019-2-28');
INSERT INTO Staff VALUES (018, 5, 'Ali', 49, 'Warehouse Operator','Raleigh' , 'ali@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (003, 1, 'Derrick', 22, 'Warehouse Operator', 'Raleigh' ,'derrick@gmail.com', 91967424637, '2019-2-28');
INSERT INTO Staff VALUES (020, 6, 'Karen', 61, 'Billing Staff', 'Raleigh' ,'karen@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (016, 4, 'Hailey', 37, 'Billing Staff','Raleigh' , 'hailey@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (010, 2, 'Xavier', 44, 'Billing Staff', 'Raleigh' ,'xavier@gmail.com', 9196563729,'2019-2-28');
INSERT INTO Staff VALUES (011, 3, 'Genevieve', 32, 'Billing Staff', 'Raleigh' ,'genevieve@gmail.com', 9191236729, '2019-2-28');
INSERT INTO Staff VALUES (005, 1, 'Sally', 35, 'Billing Staff','Raleigh' , 'sally@gmail.com', 9195432645, '2019-2-28');
INSERT INTO Staff VALUES (017, 5, 'Abel', 51, 'Registration Operator', 'Raleigh' ,'abel@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (019, 6, 'Sarah', 30, 'Registration Operator', 'Raleigh' ,'sarah@gmail.com', 9196457123, '2019-2-28');
INSERT INTO Staff VALUES (004, 1, 'Chris', 34, 'Registration Operator','Raleigh' , 'chris@gmail.com', 9196746729, '2019-2-28');
 
CREATE TABLE ClubMember(
customerID INT AUTO_INCREMENT,
firstName VARCHAR (25) NOT NULL,
lastName VARCHAR (25) NOT NULL, 
PhoneNumber VARCHAR(12),
Address VARCHAR(255) NOT NULL,
Email VARCHAR(30),
ActiveStatus BOOLEAN DEFAULT true, 
membershipLevel VARCHAR(10) NOT NULL DEFAULT 'Gold',
PRIMARY KEY (customerID)
);


INSERT INTO ClubMember VALUES (121, 'Lisa', 'Smith', 9197123456, 'Greensboro', 'lisa@gmail.com', true, 'Gold');
INSERT INTO ClubMember VALUES (122, 'Heather', 'White', 9199033456, 'Apex', 'heather@gmail.com', true, 'Platinum');
INSERT INTO ClubMember VALUES (123, 'Serene', 'Tabitha', 9199038916, 'Apex', 'serene@gmail.com', false, 'Platinum');
INSERT INTO ClubMember VALUES (124, 'Walter', 'William', 9199390256, 'Cary', 'walter@gmail.com', true, 'Gold');
INSERT INTO ClubMember VALUES (125, 'Lucy', 'Acoste', 9196734456, 'Raleigh', 'lucy@gmail.com', false, 'Gold');
INSERT INTO ClubMember VALUES (126, 'Percy', 'Faulkner', 9194125533, 'Morrisville', 'percy@gmail.com', false, 'Platinum');
INSERT INTO ClubMember VALUES (127, 'Albert', 'Smith', 9196134566, 'Asheboro', 'albert@gmail.com', true, 'Gold');
INSERT INTO ClubMember VALUES (128, 'Robert', 'Keaton', 9197893456, 'Cary', 'robert@gmail.com', false, 'Gold');
INSERT INTO ClubMember VALUES (129, 'Harry', 'Styles', 9198033456, 'Raleigh', 'harry@gmail.com', true, 'Platinum');
INSERT INTO ClubMember VALUES (130, 'Zayn', 'Malik', 9191234556, 'Durham', 'zayn@gmail.com', false, 'Gold');



CREATE TABLE SignsUp (
customerID INT, 
staffID INT, 
signUpDate DATE, 
storeID INT,
PRIMARY KEY (customerID),
FOREIGN KEY (customerID) REFERENCES ClubMember(customerID),
FOREIGN KEY (staffID) REFERENCES Staff(staffID)  ON DELETE SET NULL,
FOREIGN KEY (storeID) REFERENCES Store(storeID)  ON DELETE SET NULL
);

INSERT INTO SignsUp VALUES (121, 001, '2009-3-2', 1);
INSERT INTO SignsUp VALUES (122, 002, '2010-4-10', 1);
INSERT INTO SignsUp VALUES (123, 003, '2020-9-18', 1);
INSERT INTO SignsUp VALUES (124, 004, '2014-10-10', 1);
INSERT INTO SignsUp VALUES (125, 005, '2017-11-8', 1);
INSERT INTO SignsUp VALUES (126, 006, '2012-12-22', 1);
INSERT INTO SignsUp VALUES (127, 007, '2006-1-3', 2);
INSERT INTO SignsUp VALUES (128, 008, '2003-5-9', 2);
INSERT INTO SignsUp VALUES (129, 009, '2008-9-10', 2);
INSERT INTO SignsUp VALUES (130, 010, '2004-10-12', 2);


CREATE TABLE Transactions (
transactionID INT AUTO_INCREMENT, 
storeID INT,
customerID INT NOT NULL, 
cashierID INT,
PurchaseDate DATE, 
TotalPrice INT NOT NULL,
PRIMARY KEY (transactionID),
FOREIGN KEY (customerID) REFERENCES ClubMember(customerID),
FOREIGN KEY (cashierID) REFERENCES Staff(staffID)  ON DELETE SET NULL,
FOREIGN KEY (storeID) REFERENCES Store(storeID)  ON DELETE SET NULL
);

INSERT INTO Transactions VALUES (131,1 ,121, 002, '2010-1-2', 50);
INSERT INTO Transactions VALUES (132,2, 122, 008, '2011-7-6', 45);
INSERT INTO Transactions VALUES (133,3, 123, 015, '2009-5-10', 90);
INSERT INTO Transactions VALUES (134,4, 124, 002, '2012-3-9', 67);
INSERT INTO Transactions VALUES (135,5, 125, 008, '2007-10-28', 89);
INSERT INTO Transactions VALUES (136,6, 126, 015, '2005-6-18', 27);
INSERT INTO Transactions VALUES (137,1, 127, 002, '2011-8-15', 15);

CREATE TABLE productTable (
productID INT AUTO_INCREMENT, 
productName VARCHAR(50) NOT NULL, 
buyPrice INT NOT NULL,  
marketPrice INT NOT NULL,

PRIMARY KEY (productID)
);

INSERT INTO productTable VALUES (141, 'Bread', 20,  10);
INSERT INTO productTable VALUES (142, 'Cheese', 60, 15);
INSERT INTO productTable VALUES (143, 'Soaps', 50,  4);
INSERT INTO productTable VALUES (144, 'Grapes', 70,  5);
INSERT INTO productTable VALUES (145, 'Tomatoes', 100,  6);
INSERT INTO productTable VALUES (146, 'Onions', 20, 10);
INSERT INTO productTable VALUES (147, 'Lotion', 40, 15);
INSERT INTO productTable VALUES (148, 'Medicines', 10, 12);
INSERT INTO productTable VALUES (149, 'Chocolate', 40, 16);
INSERT INTO productTable VALUES (150, 'Hand Sanitiser', 20, 6);
INSERT INTO productTable VALUES (151, 'Paint', 34,  20);
INSERT INTO productTable VALUES (152, 'Milk', 60, 10);
INSERT INTO productTable VALUES (153, 'Sausage', 80,  11);
INSERT INTO productTable VALUES (154, 'Potatoes', 100,  6);
INSERT INTO productTable VALUES (155, 'Chicken', 20, 19);
INSERT INTO productTable VALUES (156, 'Beer', 30,  20);


CREATE TABLE InventoryInfo (
productID INT, 
storeID INT, 
productionDate DATE NOT NULL,
expirationDate DATE NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (productID, storeID),
FOREIGN KEY(storeID) REFERENCES Store(storeID)  ON DELETE CASCADE,
FOREIGN KEY(productID) REFERENCES productTable(productID)  ON DELETE CASCADE
);


INSERT INTO InventoryInfo VALUES (141, 1,'2020-08-15', '2020-08-29', 20);
INSERT INTO InventoryInfo VALUES (142, 1,'2020-08-19', '2020-8-30', 60);
INSERT INTO InventoryInfo VALUES (143, 1,'2020-08-11', '2023-8-29',50);
INSERT INTO InventoryInfo VALUES (144, 2,'2020-08-15','2020-8-18', 70);
INSERT INTO InventoryInfo VALUES (145, 2,'2020-08-15','2020-8-17', 100);
INSERT INTO InventoryInfo VALUES (146, 2,'2020-08-15','2020-08-29', 70);
INSERT INTO InventoryInfo VALUES (147, 3,'2020-08-15','2022-08-29', 40);
INSERT INTO InventoryInfo VALUES (148, 3,'2020-08-15','2024-08-29',10);
INSERT INTO InventoryInfo VALUES (149, 3,'2020-08-15', '2021-08-29', 40);
INSERT INTO InventoryInfo VALUES (150, 3, '2020-08-15', '2022-08-29', 20);
INSERT INTO InventoryInfo VALUES (151, 3, '2020-08-15', '2023-08-7',34);
INSERT INTO InventoryInfo VALUES (152, 4, '2020-08-15', '2020-08-22', 60);
INSERT INTO InventoryInfo VALUES (153, 4, '2020-08-15', '2020-08-29',80);
INSERT INTO InventoryInfo VALUES (154, 5, '2020-08-15', '2020-08-19',100);
INSERT INTO InventoryInfo VALUES (155, 6,  '2020-08-15', '2020-08-29',20);
INSERT INTO InventoryInfo VALUES (156, 6, '2020-08-15', '2020-10-29',30);


CREATE TABLE ProductList (
transactionID INT,
productID INT, 
quantity INT NOT NULL, 
discountApplied BOOLEAN DEFAULT false,
finalPrice INT NOT NULL,
PRIMARY KEY (transactionID, productID),
FOREIGN KEY (transactionID) REFERENCES Transactions(transactionID)  ON DELETE CASCADE,
FOREIGN KEY (productID) REFERENCES productTable(productID)
);

INSERT INTO ProductList VALUES (131, 141, 20, false, 100);
INSERT INTO ProductList VALUES (132, 142, 60, false, 100);
INSERT INTO ProductList VALUES (133, 143, 50, false, 100);
INSERT INTO ProductList VALUES (134, 144, 70, false, 100);
INSERT INTO ProductList VALUES (135, 145, 100, false, 100);
INSERT INTO ProductList VALUES (136, 146, 70, false, 100);
INSERT INTO ProductList VALUES (137, 147, 40, false, 100);


CREATE TABLE Discount(
discountID INT AUTO_INCREMENT,
productID INT,
storeID INT,
DiscountPercentage INT NOT NULL DEFAULT 0,
startDate DATE,
endDate DATE NOT NULL,
PRIMARY KEY(discountID),
FOREIGN KEY (productID) REFERENCES productTable(productID)  ON DELETE CASCADE,
FOREIGN KEY(storeID) REFERENCES Store(storeID)  ON DELETE CASCADE
);


INSERT INTO Discount VALUES (1, 141, 1, 40, '2020-2-3', '2020-2-10');
INSERT INTO Discount VALUES (2, 142, 1, 20, '2020-2-3', '2020-2-10');
INSERT INTO Discount VALUES (3, 144, 2, 15, '2020-2-3', '2020-2-10');
INSERT INTO Discount VALUES (4, 145, 2, 80, '2020-2-3', '2020-2-10');
INSERT INTO Discount VALUES (5, 147, 3, 10, '2020-2-3', '2020-2-10');

CREATE TABLE Supplier(
supplierID INT AUTO_INCREMENT,
supplierName VARCHAR(50) NOT NULL,
PhoneNumber VARCHAR(15) NOT NULL,
Email VARCHAR(50) NOT NULL,
Location VARCHAR(50) NOT NULL,
PRIMARY KEY (supplierID)
);

INSERT INTO Supplier VALUES (161, 'Wholesaler1', 9192345678, 'w1@gmail.com', 'Apex');
INSERT INTO Supplier VALUES (162, 'Wholesaler2', 9192345610, 'w2@gmail.com', 'Cary');
INSERT INTO Supplier VALUES (163, 'Wholesaler3', 9192345634, 'w3@gmail.com', 'Raleigh');
INSERT INTO Supplier VALUES (164, 'Wholesaler4', 9192345645, 'w4@gmail.com', 'Durham');
INSERT INTO Supplier VALUES (165, 'Wholesaler5', 9192345682, 'w5@gmail.com', 'Wake');
INSERT INTO Supplier VALUES (166, 'Wholesaler6', 9192345662, 'w6@gmail.com', 'Charlotte');

CREATE TABLE ProductInfo (
productID INT,
supplierID INT,
PRIMARY KEY (productID),
FOREIGN KEY (productID) REFERENCES productTable(productID)  ON DELETE CASCADE,
FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID)  ON DELETE SET NULL
);

INSERT INTO ProductInfo VALUES (141, 161);
INSERT INTO ProductInfo VALUES (142, 162);
INSERT INTO ProductInfo VALUES (143, 163);
INSERT INTO ProductInfo VALUES (144, 164);
INSERT INTO ProductInfo VALUES (145, 165);
INSERT INTO ProductInfo VALUES (146, 166);

CREATE TABLE SuppliesInfo (
storeID INT DEFAULT 0,
productID INT,
billingDate DATE NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY(storeID, productID, billingDate),
FOREIGN KEY (storeID) REFERENCES Store(storeID) ON DELETE CASCADE,
FOREIGN KEY (productID) REFERENCES productTable(productID)  ON DELETE CASCADE
);

INSERT INTO SuppliesInfo VALUES (1, 141, '2020-8-15', 20);
INSERT INTO SuppliesInfo VALUES (1, 142, '2020-8-10', 60);
INSERT INTO SuppliesInfo VALUES (1, 143, '2020-9-15', 50);
INSERT INTO SuppliesInfo VALUES (2, 144, '2020-10-15', 70);
INSERT INTO SuppliesInfo VALUES (2, 145, '2020-11-15', 100);
INSERT INTO SuppliesInfo VALUES (2, 146, '2020-11-20', 70);


CREATE TABLE ordersInfo(
orderID INT AUTO_INCREMENT,
supplierID INT,
staffID INT,
PRIMARY KEY (orderID),
FOREIGN KEY(supplierID) REFERENCES Supplier(supplierID)  ON DELETE SET NULL,
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE SET NULL
);

INSERT INTO ordersInfo VALUES (201, 161, 001);
INSERT INTO ordersInfo VALUES (202, 162, 002);
INSERT INTO ordersInfo VALUES (203, 163, 003);
INSERT INTO ordersInfo VALUES (204, 164, 007);
INSERT INTO ordersInfo VALUES (205, 165, 008);
INSERT INTO ordersInfo VALUES (206, 166, 009);
INSERT INTO ordersInfo VALUES (210, 161, 007);

CREATE TABLE orderProductInfo (
orderID INT, 
productID INT,
quantity INT NOT NULL,
PRIMARY KEY (orderID, productID),
FOREIGN KEY (orderID) REFERENCES ordersInfo(orderID)  ON DELETE CASCADE ,
FOREIGN KEY (productID) REFERENCES productTable(productID)  ON DELETE NO ACTION
);
INSERT INTO orderProductInfo VALUES (201, 141, 20);
INSERT INTO orderProductInfo VALUES (202, 142, 60);
INSERT INTO orderProductInfo VALUES (203, 143, 50);
INSERT INTO orderProductInfo VALUES (204, 144, 70);
INSERT INTO orderProductInfo VALUES (205, 145, 100);
INSERT INTO orderProductInfo VALUES (206, 146, 70);


CREATE TABLE Bills(
 staffID INT,
 supplierID INT,
 orderID INT,
 billingDate DATE NOT NULL,
 billingPrice INT NOT NULL,
 PRIMARY KEY(orderID),
 FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE SET NULL,
FOREIGN KEY(supplierID) REFERENCES Supplier(supplierID)  ON DELETE SET NULL,
FOREIGN KEY(orderID) REFERENCES ordersInfo(orderID) ON DELETE CASCADE
);

INSERT INTO Bills VALUES (001, 161, 201, '2020-08-15', 100);
INSERT INTO Bills VALUES (002, 162, 202, '2020-08-19', 80);
INSERT INTO Bills VALUES (003, 163, 203, '2020-08-11', 50);
INSERT INTO Bills VALUES (007, 164, 204, '2020-08-15', 120);
INSERT INTO Bills VALUES (008, 165, 205, '2020-08-15', 60);
INSERT INTO Bills VALUES (009, 166, 206, '2020-08-17', 70);
INSERT INTO Bills VALUES (007, 161, 210, '2021-08-15', 100);

CREATE TABLE Manager(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);

CREATE TABLE Cashier(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);



CREATE TABLE WarehouseOperator(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);

CREATE TABLE RegistrationOperator(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);

CREATE TABLE BillingStaff(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);

CREATE TABLE Admin(
staffID INT,
PRIMARY KEY(staffID),
FOREIGN KEY(staffID) REFERENCES Staff(staffID)  ON DELETE CASCADE
);

INSERT INTO Admin VALUES (006);
INSERT INTO Manager VALUES (007);
INSERT INTO Manager VALUES (014);
INSERT INTO Manager VALUES (012);
INSERT INTO Manager VALUES (001);
INSERT INTO Cashier VALUES (002);
INSERT INTO Cashier VALUES (008);
INSERT INTO Cashier VALUES (015);
INSERT INTO WarehouseOperator VALUES (012);
INSERT INTO WarehouseOperator VALUES (009);
INSERT INTO WarehouseOperator VALUES (018);
INSERT INTO WarehouseOperator VALUES (003);
INSERT INTO BillingStaff VALUES (020);
INSERT INTO BillingStaff VALUES (016);
INSERT INTO BillingStaff VALUES (010);
INSERT INTO BillingStaff VALUES (011);
INSERT INTO BillingStaff VALUES (005);
INSERT INTO RegistrationOperator VALUES (017);
INSERT INTO RegistrationOperator VALUES (019);
INSERT INTO RegistrationOperator VALUES (004);


########Demo Data Begins#####

INSERT INTO Store VALUES (2001,'2221, B Street, NC', 9192222123);
INSERT INTO Store VALUES (2002,'2222, C Street, NC', 9192222456);

INSERT INTO Staff VALUES (1001, 2001, 'John', 32, 'Manager','1101, S Street, NC' ,'john01@gmail.com', 9191111123, '2018-10-10');
INSERT INTO Staff VALUES (1002, 2002, 'Alex', 42, 'Manager','1102, T Street, NC' ,'alex12@gmail.com', 9191111456, '2015-07-19');
INSERT INTO Staff VALUES (1003, 2001, 'Mary', 28, 'Cashier','1103, U Street, NC' ,'mary34@gmail.com', 9191111789, '2019-07-19');

INSERT INTO ClubMember VALUES (5001, 'James', 'Smith', 9195555123, '5500, E Street, NC', 'James5001@gmail.com', true, 'Gold');
INSERT INTO ClubMember VALUES (5002, 'David', 'Smith', 9195555456, '5501 F Street, NC', 'David5002@gmail.com', true, 'Platinum');


INSERT INTO Supplier VALUES (4001, 'A Food Wholesale', 9194444123, 'afood@gmail.com', '4401, A Street, NC');
INSERT INTO Supplier VALUES (4002, 'US Foods', 9194444456, 'usfoods@gmail.com', '4402, G Street, NC');


INSERT INTO productTable VALUES (3001, 'AAA Paper Towels', 10,  20);
INSERT INTO productTable VALUES (3002, 'BBB Hand Soap', 5,  10);
INSERT INTO productTable VALUES (3003, 'CCC Red Wine', 15,  30);

INSERT INTO InventoryInfo VALUES (3001, 2001,'2020-01-01', '2025-01-01', 100);
INSERT INTO InventoryInfo VALUES (3002, 2001,'2020-01-01', '2022-01-01', 200);
INSERT INTO InventoryInfo VALUES (3001, 2002,'2020-01-01', '2025-01-01', 150);
INSERT INTO InventoryInfo VALUES (3002, 2002,'2020-01-01', '2022-01-01', 0);
INSERT INTO InventoryInfo VALUES (3003, 2001,'2021-01-01', '2022-01-01', 100);

INSERT INTO ProductInfo VALUES (3001, 4001);
INSERT INTO ProductInfo VALUES (3002, 4002);
INSERT INTO ProductInfo VALUES (3003, 4002);




INSERT INTO Transactions VALUES (6001,2001 ,5002, 1003, '2020-05-01', 100);
INSERT INTO Transactions VALUES (6002,2001 ,5002, 1003, '2020-06-01', 100);
INSERT INTO Transactions VALUES (6003,2001 ,5001, 1003, '2020-07-01', 160);

INSERT INTO ProductList VALUES (6001, 3001, 5, true, 80);
INSERT INTO ProductList VALUES (6001, 3002, 2, false, 20);

INSERT INTO ProductList VALUES (6002, 3002, 10, false, 100);

INSERT INTO ProductList VALUES (6003, 3001, 10, true, 160);

INSERT INTO Discount VALUES (7001, 3001, 2001, 20, '2020-01-01', '2021-05-01');
INSERT INTO Discount VALUES (7002, 3003, 2001, 20, '2021-01-01', '2021-05-01');

INSERT INTO SignsUp VALUES (5001, 1003, '2019-08-02', 2001);
INSERT INTO SignsUp VALUES (5002, 1003, '2009-01-01', 2001);

INSERT INTO ordersInfo VALUES(1001, 4001, 003);
INSERT INTO ordersInfo VALUES(1002, 4001, 009);
INSERT INTO ordersInfo VALUES(1003, 4001, 013);
INSERT INTO ordersInfo VALUES(1004, 4002, 018);
INSERT INTO ordersInfo VALUES(1005, 4002, 003);

INSERT INTO orderProductInfo VALUES(1001, 3001, 20);
INSERT INTO orderProductInfo VALUES(1001, 3002, 30);
INSERT INTO orderProductInfo VALUES(1001, 3003, 30);
INSERT INTO orderProductInfo VALUES(1002, 3001, 20);
INSERT INTO orderProductInfo VALUES(1002, 3002, 30);
INSERT INTO orderProductInfo VALUES(1003, 3003, 40);
INSERT INTO orderProductInfo VALUES(1003, 3001, 30);
INSERT INTO orderProductInfo VALUES(1004, 3002, 30);
INSERT INTO orderProductInfo VALUES(1004, 3003, 40);
INSERT INTO orderProductInfo VALUES(1005, 3001, 40);
INSERT INTO orderProductInfo VALUES(1005, 3002, 30);



