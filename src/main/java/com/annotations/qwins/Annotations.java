package com.annotations.qwins;

import com.annotations.qwins.annotations.core.*;
import com.annotations.qwins.annotations.core.repeatable.Command;
import com.annotations.qwins.annotations.core.repeatable.Depend;
import com.annotations.qwins.annotations.core.repeatable.Permission;

import static com.annotations.qwins.annotations.core.ApiVersion.Target.v1_16;

@Name("MyPlugin")
@Version("1.0.0")
@Description("Этот плагин крут бро")
@Authors("qWins")
@Website("https://github.com/Fanticx/AnnotationPlugin")
@Prefix("AwesomePlugin")
@ApiVersion(v1_16)
@Depend("Vault")
@Command(name = "hello", description = "Говорит привет", usageMessage = "/hello", permission = "awesome.hello")
@Permission(name = "awesome.hello", description = "Разрешает использовать /hello")
public class Annotations {
    // Просто пример класса для обработки аннотаций.
}