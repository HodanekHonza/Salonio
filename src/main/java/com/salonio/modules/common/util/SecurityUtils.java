package com.salonio.modules.common.util;

import org.springframework.web.util.HtmlUtils;

public final class SecurityUtils {

    private SecurityUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Prevent XSS attacks: https://owasp.org/www-community/attacks/xss/
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }

        return HtmlUtils.htmlEscape(input);
    }

}
