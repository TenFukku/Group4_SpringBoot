-- Bảng lưu trữ vai trò
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

-- Bảng lưu trữ người dùng
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    UNIQUE (username)
);

-- Bảng liên kết nhiều-nhiều giữa người dùng và vai trò
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Bảng lưu trữ sách
CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id)
);

-- Bảng lưu trữ lịch sử mượn sách
CREATE TABLE book_loans (
    id BIGINT NOT NULL AUTO_INCREMENT,
    loan_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(50) NOT NULL,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);