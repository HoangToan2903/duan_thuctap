CREATE DATABASE duanthuctap1;


CREATE TABLE Category (
    categoryID INT AUTO_INCREMENT PRIMARY KEY,
    categoryName NVARCHAR(50) NOT NULL,
    deleted TINYINT(1) DEFAULT 0
);

CREATE TABLE ImportBill (
    importID INT,
    productID INT,
    quantity INT NOT NULL,
    expiry DATE,
    importPrice DOUBLE,
    unit VARCHAR(255) NOT NULL,
    totalAmount DOUBLE,
    PRIMARY KEY (importID, productID),
    CONSTRAINT FK_ImportBill_Product FOREIGN KEY (productID) REFERENCES Product (productID),
    CONSTRAINT FK_ImportBill_ImportProduct FOREIGN KEY (importID) REFERENCES ImportProduct (importID)
);

CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    shipper_id INT,
    orderDay DATETIME,
    deliveryDay DATETIME,
    receiveDay DATETIME,
    address VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(100),
    totalPrice BIGINT,
    orderStatus VARCHAR(50),
    note TEXT,
    imgDelivery VARCHAR(255),
    isPaid TINYINT(1),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (shipper_id) REFERENCES User(id)
);
ALTER TABLE Orders DROP FOREIGN KEY orders_ibfk_3;

CREATE TABLE Product (
    productID INT AUTO_INCREMENT PRIMARY KEY,
    categoryID INT,
    productName NVARCHAR(100) NOT NULL,
    img VARCHAR(255),
    price INT NOT NULL,
    unit VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    description NVARCHAR(300),
    deleted TINYINT(1) DEFAULT 0,
    FOREIGN KEY (categoryID) REFERENCES Category(categoryID)
);
CREATE TABLE PurchaseOrder (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    order_id INT,
    FOREIGN KEY (product_id) REFERENCES Product(productID),
    FOREIGN KEY (order_id) REFERENCES Orders(id)
);
ALTER TABLE reviews DROP FOREIGN KEY users_roles_ibfk_2;
ALTER TABLE users_roles DROP FOREIGN KEY users_roles_ibfk_2;
ALTER TABLE users_roles DROP FOREIGN KEY users_roles_ibfk_1;
CREATE TABLE Reviews (
    reviewID INT AUTO_INCREMENT PRIMARY KEY,
    productID INT NOT NULL,
    userID INT NOT NULL,
    content NVARCHAR(200) NOT NULL,
    postDate DATETIME NOT NULL,
    star INT,
    order_id INT,
    replyID INT,
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (userID) REFERENCES user(id),
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (replyID) REFERENCES Reviews(reviewID)
);
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name NVARCHAR(50),
    last_name NVARCHAR(50),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    address NVARCHAR(200),
    phone VARCHAR(50),
    deleted TINYINT DEFAULT 0
);
CREATE TABLE Role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(255)
);
CREATE TABLE Supplier (
    supplierID INT AUTO_INCREMENT PRIMARY KEY,
    supplierName NVARCHAR(200) NOT NULL,
    address NVARCHAR(200),
    phone VARCHAR(255) UNIQUE,
    deleted TINYINT(1) DEFAULT 0
);
CREATE TABLE ImportProduct (
    importID INT AUTO_INCREMENT PRIMARY KEY,
    supplierID INT,
    importDate DATE,
    FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID)
);

CREATE TABLE users_roles (
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id) REFERENCES `user`(id),
    FOREIGN KEY (role_id) REFERENCES Role(id)
);