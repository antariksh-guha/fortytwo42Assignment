package com.fortytwo42.student.rest.api.assignment.utils;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * A set of helper method for fixture files.
 */
public class FixtureHelpers {
    private FixtureHelpers() { /* singleton */ }
    public static String fixture(String filename) {
        return fixture(filename, StandardCharsets.UTF_8);
    }
    private static String fixture(String filename, Charset charset) {
        final URL resource = Resources.getResource(filename);
        try {
            return Resources.toString(resource, charset).trim();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

