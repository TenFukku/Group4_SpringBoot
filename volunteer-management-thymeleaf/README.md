# Hệ thống Quản lý Tình nguyện viên - Demo Thymeleaf

## 📋 Mô tả

Hệ thống quản lý tình nguyện viên được xây dựng bằng Spring Boot và Thymeleaf, hỗ trợ quản lý tình nguyện viên, chiến dịch, kỹ năng và theo dõi sự tham gia. **Ứng dụng này cũng là một demo hoàn chỉnh về các tính năng Thymeleaf.**

## 🚀 Tính năng chính

### 👥 Quản lý Tình nguyện viên

- Thêm, sửa, xóa tình nguyện viên
- Quản lý thông tin cá nhân (họ tên, email, số điện thoại)
- Theo dõi ngày tham gia
- Gán kỹ năng cho tình nguyện viên
- **Tìm kiếm và lọc** theo tên, email, trạng thái

### 📅 Quản lý Chiến dịch

- Tạo và quản lý các chiến dịch tình nguyện
- Theo dõi thời gian diễn ra (ngày bắt đầu, kết thúc)
- Quản lý địa điểm tổ chức
- Theo dõi sự tham gia của tình nguyện viên
- **Tìm kiếm và lọc** theo tên, địa điểm, trạng thái

### 🛠️ Quản lý Kỹ năng

- Thêm, sửa, xóa các kỹ năng
- Phân loại mức độ kỹ năng (BEGINNER, INTERMEDIATE, ADVANCED)
- Gán kỹ năng cho tình nguyện viên
- **Tìm kiếm và lọc** theo tên, số lượng tình nguyện viên

### 📊 Thống kê và Báo cáo

- Dashboard tổng quan với thống kê
- Theo dõi số lượng tình nguyện viên, chiến dịch, kỹ năng
- Hiển thị chiến dịch đang diễn ra

## 🎯 Demo Thymeleaf Features

### 1. Cú pháp cơ bản

- ✅ **Namespace declaration**: `xmlns:th="http://www.thymeleaf.org"`
- ✅ **Variable expressions**: `${...}` - Hiển thị dữ liệu từ Model
- ✅ **Text display**: `th:text` - Hiển thị văn bản an toàn (HTML escaping)
- ✅ **Unsafe HTML**: `th:utext` - Hiển thị HTML không an toàn (cảnh báo XSS)

### 2. Thuộc tính HTML động

- ✅ **General attributes**: `th:attr` - Gán nhiều thuộc tính động
- ✅ **Specific attributes**: `th:value`, `th:class`, `th:classappend`
- ✅ **Form attributes**: `th:disabled`, `th:title`, `th:onclick`, `th:placeholder`
- ✅ **Validation attributes**: `th:required`, `th:pattern`, `th:maxlength`

### 3. Link expressions

- ✅ **Basic links**: `@{/path}` - Tự động thêm context path
- ✅ **Path variables**: `@{/path/{id}(id=${value})}` - Truyền biến đường dẫn
- ✅ **Request parameters**: `@{/path?param=${value}}` - Truyền tham số

### 4. Hiển thị có điều kiện

- ✅ **th:if**: Hiển thị khi điều kiện đúng
- ✅ **th:unless**: Hiển thị khi điều kiện sai
- ✅ **Conditional logic**: Kết hợp với validation, trạng thái

### 5. Vòng lặp và danh sách

- ✅ **th:each**: Lặp qua collections (List, Map, Array)
- ✅ **Iteration status**: `iterStat` với các thuộc tính (index, count, size, first, last, even, odd)
- ✅ **Dynamic styling**: CSS classes dựa trên vị trí trong vòng lặp

### 6. Form Backing Bean (DTO)

- ✅ **th:object**: Liên kết form với object
- ✅ **th:field**: Binding với properties của object
- ✅ **Validation**: Bean Validation với custom messages
- ✅ **Error handling**: Hiển thị lỗi validation

### 7. Request Parameters

- ✅ **Search forms**: Tìm kiếm với GET parameters
- ✅ **Filtering**: Lọc dữ liệu theo nhiều tiêu chí
- ✅ **URL building**: Tạo URL với parameters động
- ✅ **Parameter retention**: Giữ lại giá trị đã nhập

### 8. Fragments (DRY Principle)

- ✅ **th:fragment**: Định nghĩa reusable components
- ✅ **th:replace**: Thay thế hoàn toàn element
- ✅ **th:insert**: Chèn fragment vào bên trong element
- ✅ **Fragment parameters**: Truyền dữ liệu vào fragments
- ✅ **Layout fragments**: Navigation, footer, scripts, buttons

## 🛠️ Công nghệ sử dụng

- **Backend**: Spring Boot 3.5.4
- **Database**: H2 Database (in-memory)
- **ORM**: Spring Data JPA
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5, Font Awesome
- **Validation**: Bean Validation (Jakarta Validation)
- **Build Tool**: Maven

## 📦 Cài đặt và Chạy

### Yêu cầu hệ thống

- Java 21
- Maven 3.6+

### Các bước cài đặt

1. **Clone repository**

```bash
git clone <repository-url>
cd volunteer-management-thymeleaf
```

2. **Chạy ứng dụng**

```bash
mvn spring-boot:run
```

3. **Truy cập ứng dụng**

- URL chính: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:volunteerdb`
  - Username: `sa`
  - Password: (để trống)

## 📁 Cấu trúc dự án

```
src/
├── main/
│   ├── java/org/example/
│   │   ├── controller/          # Controllers
│   │   ├── entity/             # JPA Entities
│   │   ├── repository/         # Data Access Layer
│   │   ├── service/            # Business Logic
│   │   ├── dto/               # Data Transfer Objects
│   │   └── config/            # Configuration
│   └── resources/
│       ├── templates/          # Thymeleaf templates
│       │   ├── fragments/      # Reusable fragments
│       │   ├── volunteer/      # Volunteer templates
│       │   ├── campaign/       # Campaign templates
│       │   └── skill/          # Skill templates
│       ├── static/            # Static resources (CSS, JS)
│       └── application.properties
└── test/                      # Unit tests
```

## 🗄️ Cơ sở dữ liệu

### Các bảng chính:

- `volunteers`: Thông tin tình nguyện viên
- `campaigns`: Thông tin chiến dịch
- `skills`: Danh sách kỹ năng
- `skill_levels`: Mức độ kỹ năng của tình nguyện viên
- `participation_logs`: Lịch sử tham gia chiến dịch

### Dữ liệu mẫu:

Ứng dụng tự động tạo dữ liệu mẫu khi khởi động:

- 12 tình nguyện viên
- 14 kỹ năng khác nhau
- 3 chiến dịch mẫu
- Gán kỹ năng ngẫu nhiên cho tình nguyện viên

## 🎨 Giao diện

### Trang chủ

- Dashboard với thống kê tổng quan
- Cards hiển thị số liệu với dynamic styling
- Quick actions để thêm mới
- **Demo conditional rendering** với th:if và th:unless

### Quản lý Tình nguyện viên

- Danh sách tình nguyện viên với **th:each** và **iterStat**
- Form thêm/sửa thông tin với **th:object** và **th:field**
- **Validation** với custom error messages
- **Search form** với request parameters

### Quản lý Chiến dịch

- Danh sách chiến dịch với **dynamic styling**
- Form tạo/sửa chiến dịch với **validation**
- Theo dõi thời gian với **conditional rendering**
- **Search và filter** functionality

### Quản lý Kỹ năng

- Danh sách kỹ năng với **iterStat**
- Form thêm/sửa kỹ năng
- Thống kê số tình nguyện viên có kỹ năng
- **Search và filter** theo tên và số lượng

## 🔧 Cấu hình

### application.properties

```properties
# Database
spring.datasource.url=jdbc:h2:mem:volunteerdb
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 🧪 Testing

Chạy unit tests:

```bash
mvn test
```

## 📝 API Endpoints

### Tình nguyện viên

- `GET /volunteers` - Danh sách tình nguyện viên (với search parameters)
- `GET /volunteers/add` - Form thêm mới
- `POST /volunteers/add` - Thêm tình nguyện viên (với validation)
- `GET /volunteers/edit/{id}` - Form sửa
- `POST /volunteers/edit/{id}` - Cập nhật (với validation)
- `GET /volunteers/delete/{id}` - Xóa

### Chiến dịch

- `GET /campaigns` - Danh sách chiến dịch (với search parameters)
- `GET /campaigns/add` - Form thêm mới
- `POST /campaigns/add` - Thêm chiến dịch (với validation)
- `GET /campaigns/edit/{id}` - Form sửa
- `POST /campaigns/edit/{id}` - Cập nhật (với validation)
- `GET /campaigns/delete/{id}` - Xóa

### Kỹ năng

- `GET /skills` - Danh sách kỹ năng (với search parameters)
- `GET /skills/add` - Form thêm mới
- `POST /skills/add` - Thêm kỹ năng (với validation)
- `GET /skills/edit/{id}` - Form sửa
- `POST /skills/edit/{id}` - Cập nhật (với validation)
- `GET /skills/delete/{id}` - Xóa

## 🎯 Demo URLs

### Thymeleaf Features Demo:

- **Homepage**: http://localhost:8080 - Demo conditional rendering
- **Volunteers**: http://localhost:8080/volunteers - Demo th:each, iterStat, search
- **Campaigns**: http://localhost:8080/campaigns - Demo dynamic styling, validation
- **Skills**: http://localhost:8080/skills - Demo fragments, form binding

### Search Examples:

- **Volunteer Search**: http://localhost:8080/volunteers?name=Nguyễn&status=active
- **Campaign Search**: http://localhost:8080/campaigns?location=Hà Nội&status=upcoming
- **Skill Search**: http://localhost:8080/skills?name=Lên&minVolunteers=3

## 🤝 Đóng góp

1. Fork dự án
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📄 License

Dự án này được phát triển cho mục đích học tập và demo Thymeleaf features.

## 👨‍💻 Tác giả

Được phát triển với ❤️ bằng Spring Boot và Thymeleaf.

---

**Lưu ý**:

- Đây là ứng dụng demo, dữ liệu sẽ bị mất khi restart server do sử dụng H2 in-memory database.
- Ứng dụng này cũng là một **demo hoàn chỉnh** về các tính năng Thymeleaf từ cơ bản đến nâng cao.
