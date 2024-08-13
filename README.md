# Spring Boot ile CRUD Rest API

Bu proje, Spring Boot, Maven ve Thymeleaf kullanılarak oluşturulmuş basit bir CRUD (Create, Read, Update, Delete) uygulamasıdır. Kullanıcıların ürünleri yönetmesine olanak tanır; ürün ekleme, düzenleme ve silme gibi özellikler içerir.

## Kullanılan Teknolojiler

- Java
- Spring Boot
- Maven
- Thymeleaf
- Bootstrap
- Jakarta Validation
- Spring Data JPA

## Başlarken

### Gereksinimler

- Java 17 veya üstü
- Maven 3.6.0 veya üstü
- MySQL veri tabanı

### Kurulum

1. Depoyu klonlayın:
    ```sh
    git clone https://github.com/bersankilic/spring-boot-crud.git
    cd spring-boot-crud.git
    ```

2. `src/main/resources/application.properties` dosyasındaki veritabanı yapılandırmasını düzenleyin:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/veritabaniadi
    spring.datasource.username=kullaniciadi
    spring.datasource.password=sifre
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Projeyi derleyin:
    ```sh
    mvn clean install
    ```

4. Uygulamayı çalıştırın:
    ```sh
    mvn spring-boot:run
    ```

### Kullanım

- Uygulamaya `http://localhost:8080/products` adresinden erişin.
- Ürün ekleme, düzenleme ve silme işlemlerini arayüz üzerinden gerçekleştirin.

### Proje Yapısı

- `src/main/java/com/crudrest/restCrud/controllers`: Kontrolör sınıflarını içerir.
- `src/main/java/com/crudrest/restCrud/models`: Model sınıflarını içerir.
- `src/main/java/com/crudrest/restCrud/services`: Servis sınıflarını içerir.
- `src/main/resources/templates`: Thymeleaf şablonlarını içerir.
- `src/main/resources/static`: CSS ve JavaScript dosyaları gibi statik kaynakları içerir.

### Endpointler

- `GET /products`: Tüm ürünleri listele.
- `GET /products/create`: Ürün oluşturma formunu göster.
- `POST /products/create`: Yeni bir ürün oluştur.
- `GET /products/edit?id={id}`: Ürün düzenleme formunu göster.
- `POST /products/edit?id={id}`: Mevcut bir ürünü düzenle.
- `GET /products/delete?id={id}`: Bir ürünü sil.

### Doğrulama

- `ProductDto` sınıfı, veri bütünlüğünü sağlamak için Jakarta Validation anotasyonlarını kullanır.
- Doğrulama başarısız olursa hata mesajları formda görüntülenir.

### Dosya Yükleme

- Uygulama, ürünler için görsel dosya yüklemeyi destekler.
- Yüklenen görseller `public/images/` dizininde saklanır.
