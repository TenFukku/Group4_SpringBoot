-- Tạo 2 vai trò cơ bản
INSERT INTO roles (name) VALUES ('ROLE_MEMBER'), ('ROLE_LIBRARIAN');

-- Tạo 2 người dùng mẫu với mật khẩu là "123" (đã được mã hóa bằng BCrypt)
INSERT INTO users (username, password, enabled) VALUES
('member', '$2a$10$22n.Wc283A.pB.152f2NlOP8p6s2yVd619D.jK9C03O5oc1j0jWPa', true),
('librarian', '$2a$10$22n.Wc283A.pB.152f2NlOP8p6s2yVd619D.jK9C03O5oc1j0jWPa', true);

-- Gán vai trò cho người dùng
-- user 'member' có id=1, role 'MEMBER' có id=1
-- user 'librarian' có id=2, role 'LIBRARIAN' có id=2
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (2, 2);

-- Thêm sách mẫu
INSERT INTO books (title, author, quantity) VALUES
('Lập trình Spring Boot Toàn tập', 'John Doe', 5),
('Clean Code', 'Robert C. Martin', 3),
('Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma', 0);