<h1 align="center">🛍️ E-Ticaret Uygulaması</h1>


## 🚀 Proje Özeti

Bu uygulama, **Kotlin** dili kullanılarak geliştirilen, **Room Database** destekli bir e-ticaret platformudur.  
Uygulamada kullanıcılar ürünleri görüntüleyebilir, favorilere ekleyebilir, sepet oluşturabilir ve ödeme işlemini tamamlayabilir.  
Tasarımda **MVVM mimarisi** ve **Fragment-tabanlı yapı** kullanılmıştır.

---

## 🧠 Teknik Özellikler

| Bileşen | Açıklama |
|----------|-----------|
| **Kotlin (Android)** | Uygulama dili |
| **Room Database** | Ürün, sepet ve favori verilerini yerel olarak saklar |
| **MVVM Mimari Deseni** | ViewModel ve Repository katmanlarıyla modüler yapı |
| **RecyclerView + Adapter** | Ürün ve kategori listelerinin yönetimi |
| **Navigation Component** | Fragment geçişleri ve alt menü yönetimi |
| **LiveData / ViewModel** | UI ve veri senkronizasyonu |
| **CardView & ConstraintLayout** | Modern ve responsive UI tasarımı |

---

## ⚠️ Bilgilendirme

Bu proje örnek görsellerle oluşturulmuştur.  
Şu anda yalnızca **Kadın kategorisine** ait görseller yüklüdür.  
Diğer kategorilerdeki ürünler placeholder (boş) görsellerle temsil edilmektedir.

---

## 📁 Proje Yapısı

```bash
Ecommerce-App/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/eticaretuyg/
│   │   │   │   ├── data/               # DAO, Entity ve Database sınıfları
│   │   │   │   ├── model/              # Veri modelleri (Product, Category, CartItem vb.)
│   │   │   │   ├── repository/         # Veritabanı işlemleri (CRUD) yöneticisi
│   │   │   │   ├── ui/
│   │   │   │   │   ├── fragments/      # Category, Product, Cart, Profile ekranları
│   │   │   │   │   ├── adapters/       # RecyclerView adapter sınıfları
│   │   │   │   │   └── viewmodel/      # MVVM ViewModel sınıfları
│   │   │   │   └── utils/              # Yardımcı fonksiyonlar
│   │   │   ├── res/
│   │   │   │   ├── layout/             # XML tasarım dosyaları
│   │   │   │   ├── drawable/           # Görseller ve ikonlar
│   │   │   │   ├── menu/               # Menü XML dosyaları
│   │   │   │   ├── navigation/         # NavGraph yönlendirmeleri
│   │   │   │   ├── values/             # colors.xml, strings.xml, styles.xml
│   │   │   │   └── xml/                # Uygulama ayar dosyaları (ör. network_security_config)
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/                # Instrumented test dosyaları
│   │   └── test/                       # Unit test dosyaları
│   └── build.gradle.kts                # Modül düzeyindeki Gradle yapılandırması
│
├── gradle/                             # Gradle wrapper dosyaları
├── build.gradle.kts                    # Proje düzeyindeki Gradle yapılandırması
├── gradle.properties                   # Gradle sistem özellikleri
├── settings.gradle.kts                 # Modül tanımlamaları
├── gradlew / gradlew.bat               # Gradle çalıştırıcı dosyaları
├── .gitignore                          # Gereksiz dosyaları hariç tutar
└── README.md                           # Proje dokümantasyonu

> Bu yapı MVVM mimarisine göre düzenlenmiştir. Her katman bağımsız geliştirilip test edilebilir yapıdadır.
