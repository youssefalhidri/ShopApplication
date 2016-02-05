/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.utility;

import java.io.IOException;

import javax.servlet.ServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;

/**
 *
 * @author hidri_000
 */
public class Utility {

    public static String encrypt(String value) throws EncryptionException {

        CipherText ciphertext = ESAPI.encryptor().encrypt(new PlainText(value));
        return   ESAPI.encoder().encodeForBase64(
                ciphertext.asPortableSerializedByteArray(), false);

    }

    public static String decrypt(String value) throws EncryptionException,
            IOException {

        byte[] b = ESAPI.encoder().decodeFromBase64(value);
        PlainText recoveredPlaintext = ESAPI.encryptor().decrypt(
                CipherText.fromPortableSerializedBytes(b));

        return recoveredPlaintext.toString();

    }

    public static String getSafeHTMLParameter(ServletRequest request,
            String parameter) {
        String safe = ESAPI.encoder().encodeForHTML(
                request.getParameter(parameter));
        if (safe == null || safe.isEmpty()) {
            return null;
        } else {
            return safe;
        }

    }

    public static String getHTMLParameter(ServletRequest request,
            String parameter) {
        String value = request.getParameter(parameter);
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            return value;
        }

    }
}
