/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.internal;

import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.neoforge.common.I18nExtension;

public class TextComponentMessageFormatHandler {
    public static int handle(final TranslatableContents parent, final Consumer<FormattedText> addChild, final Object[] formatArgs, final String format) {
        try {
            final String formattedString = I18nExtension.parseFormat(format, formatArgs);

            // See MinecraftForge/MinecraftForge#7396
            if (format.indexOf('\'') != -1) {
                final boolean onlyMissingQuotes = format.chars()
                        .filter(ch -> formattedString.indexOf((char) ch) == -1)
                        .allMatch(ch -> ch == '\'');
                if (onlyMissingQuotes) {
                    return 0;
                }
            }

            MutableComponent component = Component.literal(formattedString);
            addChild.accept(component);
            return format.length();
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }
}
