package com.annotations.qwins.builder;

import com.annotations.qwins.annotations.core.repeatable.Command;
import com.annotations.qwins.annotations.core.repeatable.Permission;
import com.annotations.qwins.annotations.enums.LoadOrder;
import com.annotations.qwins.model.PluginMetadata;
import com.annotations.qwins.util.StringUtils;

import java.util.List;

public class PluginYmlBuilder {
    private final PluginMetadata data;

    public PluginYmlBuilder(PluginMetadata data) {
        this.data = data;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        sb.append("name: ").append(data.name()).append("\n");
        sb.append("version: '").append(data.version()).append("'\n");
        sb.append("main: ").append(data.mainClass()).append("\n");

        append(sb, "description", data.description());
        appendAuthors(sb);
        append(sb, "website", data.website());
        append(sb, "prefix", data.prefix());
        append(sb, "api-version", data.apiVersion().getValue());
        if (data.loadOrder() != LoadOrder.POSTWORLD) {
            sb.append("load: ").append(data.loadOrder().name()).append("\n");
        }

        appendList(sb, "libraries", data.libraries());
        appendList(sb, "depend", data.depend());
        appendList(sb, "softdepend", data.softDepend());

        appendCommands(sb);
        appendPermissions(sb);

        return sb.toString();
    }

    private void append(StringBuilder sb, String key, String value) {
        if (!StringUtils.isBlank(value)) {
            sb.append(key).append(": ").append(value).append("\n");
        }
    }

    private void appendAuthors(StringBuilder sb) {
        List<String> authors = data.authors();
        if (authors.isEmpty()) return;
        if (authors.size() == 1) {
            sb.append("author: ").append(authors.get(0)).append("\n");
        } else {
            sb.append("authors: [")
                    .append(String.join(", ", authors))
                    .append("]\n");
        }
    }

    private void appendList(StringBuilder sb, String key, List<String> items) {
        if (items.isEmpty()) return;
        sb.append(key).append(":\n");
        for (String item : items) {
            sb.append("  - ").append(item).append("\n");
        }
    }

    private void appendCommands(StringBuilder sb) {
        if (data.commands().isEmpty()) return;
        sb.append("commands:\n");
        for (Command cmd : data.commands()) {
            sb.append("  ").append(cmd.name()).append(":\n");
            append(sb, "    description", cmd.description());
            if (cmd.aliases().length > 0) {
                sb.append("    aliases: [")
                        .append(String.join(", ", cmd.aliases()))
                        .append("]\n");
            }
            append(sb, "    usage", cmd.usageMessage());
            append(sb, "    permission", cmd.permission());
        }
    }

    private void appendPermissions(StringBuilder sb) {
        if (data.permissions().isEmpty()) return;
        sb.append("permissions:\n");
        for (Permission perm : data.permissions()) {
            sb.append("  ").append(perm.name()).append(":\n");
            append(sb, "    description", perm.description());
            sb.append("    default: ").append(perm.defaultValue().getYamlValue()).append("\n");
        }
    }
}