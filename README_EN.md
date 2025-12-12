# AnnotationPlugin - Automatic plugin.yml generation

[![JitPack](https://jitpack.io/v/Fanticx/AnnotationPlugin.svg)](https://jitpack.io/#Fanticx/AnnotationPlugin)

**AnnotationPlugin** is a modern annotation processor library for Spigot/Bukkit plugin development.

It allows you to completely abandon the manual creation and maintenance of the `plugin.yml` file. All you need to do is add annotations to the main class of your plugin. When compiling, the processor will automatically generate a clean and correct `plugin.yml` in the JAR file.


##  Installation

Add the dependency to your project. The latest version can be found at this [link](https://jitpack.io/#Fanticx/AnnotationPlugin).

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

## Supported annotations

| Annotation | Required? | Description | Example |
|---|---|---|---|
| `@Name` | **Yes** | Plugin name. | `@Name("MyPlugin")` |
| `@Version` | No | Plugin version. Default: `1.0`. | `@Version("2.3.1")` |
| `@Description` | No | Plugin description. | `@Description("Cool plugin")` |
| `@Authors` | No | One or more authors. | `@Authors("Fanticx", "Friend")` |
| `@Website` | No | Plugin website. | `@Website("https://example.com")` |
| `@Prefix` | No | Prefix for logs. | `@Prefix("MyPlugin")` |
| `@ApiVersion` | No | API version. Default: `1.21`. | `@ApiVersion(v1_21)` |
| `@Load` | No | Load order (STARTUP or POSTWORLD). | `@Load(STARTUP)` |
| `@Depend` | No | Dependencies on other plugins. | `@Depend("Vault")` |
| `@SoftDepend` | No | Dependencies, but will start without them. | `@SoftDepend("Essentials")` |
| `@Library` | No | External libraries to load (shaded). | `@Library("com.squareup.okhttp3:okhttp:4.12.0")` |
| `@Command` | No | Command registration. Can be used multiple times. | `@Command(name = "hello", description = "Hello")` |
| `@Permission` | No | Permission registration. Can be used multiple times. | `@Permission(name = "myplugin.use")` |

---

## Example of use

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
@Description("Economy rules")
@Authors("Fanticx")
@Website("https://github.com/Fanticx/AnnotationPlugin")
@Prefix("Awesome")
@ApiVersion(v1_21)
@Load(STARTUP)
@Depend("Vault")
@SoftDepend("WorldEdit")
@Command(
        name = "hello",
        description = "Greeting command",
        aliases = {"hi", "privet"},
        usage = "/hello [player]",
        permission = "awesome.hello"
)
@Permission(
        name = "awesome.hello",
        description = "Allows using /hello",
        defaultValue = PermissionDefault.OP
)
public class MyAwesomePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Your plugin code
    }
}
```

### Generated `plugin.yml`

The processor will generate the following file

```yaml
name: MyPlugin
version: '1.2.11'
main: com.example.myplugin.MyAwesomePlugin
description: Economy rules
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
    description: Greeting command
    aliases:
      - hi
      - privet
    usage: /hello [player]
    permission: awesome.hello
permissions:
  awesome.hello:
    description: Allows using /hello
    default: op
```

---

## To support this project, put a star on this repository


**Good luck work work work**
