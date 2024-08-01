package com.example.AuthorService.configs;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * A utility class for translating messages based on the current locale.
 */
@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    /**
     * Constructs a Translator with the specified ResourceBundleMessageSource.
     *
     * @param messageSource the message source to use for translations
     */
    private Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    /**
     * Translates the given message code into the current locale, using the provided arguments.
     *
     * @param msgCode the message code to translate
     * @param args    the arguments to include in the translated message
     * @return the translated message
     */
    public static String toLocale(String msgCode, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }
}
