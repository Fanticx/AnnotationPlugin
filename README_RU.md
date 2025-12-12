# AnnotationPlugin - Автоматическая генерация plugin.yml

[![JitPack](https://jitpack.io/v/Fanticx/AnnotationPlugin.svg)](https://jitpack.io/#Fanticx/AnnotationPlugin)

**AnnotationPlugin** - это современная, библиотека с annotation-процессором для разработки плагинов на Spigot/Bukkit.

Она позволяет полностью отказаться от ручного создания и поддержки файла `plugin.yml`. Всё, что нужно - добавить аннотации в главный класс вашего плагина. При компиляции процессор автоматически сгенерирует чистый и корректный `plugin.yml` в JAR-файле.


##  Установка

Добавьте зависимость в ваш проект. Последнюю версию можно найти по [ссылке](https://jitpack.io/#Fanticx/AnnotationPlugin).

<details>
<summary><b>Groovy (build.gradle)</b></summary>

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.Fanticx:AnnotationPlugin:1.1.0'
    annotationProcessor 'com.github.Fanticx:AnnotationPlugin:1.1.0'
}
```
</details>

<details>
<summary><b>Kotlin (build.gradle.kts)</b></summary>

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.Fanticx:AnnotationPlugin:1.1.0")
    annotationProcessor("com.github.Fanticx:AnnotationPlugin:1.1.0")
}
```
</details>

---

##  Поддерживаемые аннотации

| Аннотация      | Обязательна? | Описание                                              | Пример                                                 |
|----------------|--------------|-------------------------------------------------------|--------------------------------------------------------|
| `@Name`        | **Да**       | Название плагина.                                     | `@Name("MyPlugin")`                                    |
| `@Version`     | Нет          | Версия плагина. По умолчанию: `1.0`.                  | `@Version("2.3.1")`                                    |
| `@Description` | Нет          | Описание плагина.                                     | `@Description("Крутой плагин")`                        |
| `@Authors`     | Нет          | Один или несколько авторов.                           | `@Authors("Fanticx", "Друг")`                          |
| `@Website`     | Нет          | Сайт плагина.                                         | `@Website("https://example.com")`                      |
| `@Prefix`      | Нет          | Префикс для логов.                                    | `@Prefix("MyPlugin")`                                  |
| `@ApiVersion`  | Нет          | Версия API. По умолчанию: `1.21`.                     | `@ApiVersion(v1_21)`                                   |
| `@Load`        | Нет          | Порядок загрузки (STARTUP или POSTWORLD).             | `@Load(STARTUP)`                                       |
| `@Depend`      | Нет          | Зависимости от других плагинов.                       | `@Depend("Vault")`                                     |
| `@SoftDepend`  | Нет          | Зависимости, но запустится без них.                   | `@SoftDepend("Essentials")`                            |
| `@Library`     | Нет          | Внешние библиотеки для загрузки (shaded).             | `@Library("com.squareup.okhttp3:okhttp:4.12.0")`       |
| `@Command`     | Нет          | Регистрация команды. Можно использовать несколько.    | `@Command(name = "hello", description = "Привет")`     |
| `@Permission`  | Нет          | Регистрация разрешения. Можно использовать несколько. | `@Permission(name = "myplugin.use")`                   |

---

## Пример использования

```java
package com.example.myplugin;

import com.annotations.qwins.annotations.core.*;
import com.annotations.qwins.annotations.core.repeatable.Command;
import com.annotations.qwins.annotations.core.repeatable.Depend;
import com.annotations.qwins.annotations.core.repeatable.Permission;
import com.annotations.qwins.annotations.core.repeatable.SoftDepend;
import com.annotations.qwins.annotations.enums.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

import static com.annotations.qwins.annotations.core.ApiVersion.Target.v1_21;
import static com.annotations.qwins.annotations.enums.LoadOrder.STARTUP;

@Name("MyPlugin")
@Version("1.2.11")
@Description("Экономика рулит")
@Authors("Fanticx")
@Website("https://github.com/Fanticx/AnnotationPlugin")
@Prefix("Awesome")
@ApiVersion(v1_21)
@Load(STARTUP)
@Depend("Vault")
@SoftDepend("WorldEdit")
@Command(
        name = "hello",
        description = "Команда приветствия",
        aliases = {"hi", "privet"},
        usage = "/hello [игрок]",
        permission = "awesome.hello"
)
@Permission(
        name = "awesome.hello",
        description = "Разрешает использовать /hello",
        defaultValue = PermissionDefault.OP
)
public class MyAwesomePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Ваш код плагина
    }
}
```

### Сгенерированный `plugin.yml`

Процессор сгенерирует следующий файл

```yaml
name: MyPlugin
version: '1.2.11'
main: com.example.myplugin.MyAwesomePlugin
description: Экономика рулит
authors: [Fanticx]
website: https://github.com/Fanticx/AnnotationPlugin
prefix: Awesome
api-version: '1.21'
load: STARTUP
depend:
  - Vault
softdepend:
  - WorldEdit
commands:
  hello:
    description: Команда приветствия
    aliases:
      - hi
      - privet
    usage: /hello [игрок]
    permission: awesome.hello
permissions:
  awesome.hello:
    description: Разрешает использовать /hello
    default: op
```

---

## Для поддержки этого проекта поставьте звезды на этот репозиторий


**Удачного work work work**
