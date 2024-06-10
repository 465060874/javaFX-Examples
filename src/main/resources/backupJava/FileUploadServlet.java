package com.demo.javafxexample.apps.richeditor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fileName = UUID.randomUUID().toString();
        File file = new File(uploadDir, fileName);

        try (InputStream input = req.getInputStream(); FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, bytesRead, bytesRead);
            }
        }

        String fileUrl = "http://localhost:8080/" + UPLOAD_DIR + "/" + fileName;
        String jsonResponse = "{ \"location\": \"" + fileUrl + "\" }";

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

