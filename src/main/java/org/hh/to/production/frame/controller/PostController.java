package org.hh.to.production.frame.controller;

import org.jsoup.Jsoup;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class PostController implements HttpSessionListener {

    private List<String> postFilesList;

    @RequestMapping("/poster/new")
    public String createNewPost() throws IOException {
        if (postFilesList == null || postFilesList.isEmpty()) {
            createDir();
        } else deleteFolder(new File("image/" + postFilesList.get(0)));
        return "create_post";
    }

    @RequestMapping(value = "/poster/save", method = RequestMethod.POST)
    public String savePost(@RequestParam("header") String header, @RequestParam("content") String content) {
        //todo check if header has already exist
        var document = Jsoup.parse(content);
        var images = document.getElementsByTag("img");
        if (!images.isEmpty()) {
            images.forEach(i -> {
                try {
                    Files.createDirectories(Paths.get("image/" + header));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                var attr = i.attributes().get("src");
                final var res = attr.substring(attr.lastIndexOf((int) '/')+1);
                //move images to another dir
                postFilesList.stream().filter(s -> s.equals(res)).findFirst().ifPresent(s -> {
                    try {
                        var l = Files.move(Paths.get(postFilesList.get(0) + "/" + s), Paths.get("image/" + header + "/" + s), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println(l);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
        deleteFolder(new File(postFilesList.get(0)));
        return "redirect://localhost:8080/index";
    }

    @RequestMapping(value = "/poster/saveImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveImage(@RequestParam("upload") MultipartFile image, HttpServletRequest request) throws IOException {
        try (var outputStream = new FileOutputStream(postFilesList.get(0) + "/" + image.getOriginalFilename())) {
            postFilesList.add(image.getOriginalFilename());
            outputStream.write(image.getBytes());
        }
        return "{\"uploaded\": 1,\"url\": \"" + "/" + postFilesList.get(0) + "/" + image.getOriginalFilename() + "\"}";
    }

    @RequestMapping(value = "/image/{header}/{image}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getImage(@PathVariable String header, @PathVariable String image, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (var inputStream = new FileInputStream(request.getRequestURI().substring(1))) {
            return inputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            response.sendError(404, ":no such image");
            return null;
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        if (postFilesList != null && !postFilesList.isEmpty())
            deleteFolder(new File("image/" + postFilesList.get(0)));
    }

    private void createDir() throws IOException {
        String folderName = "image/" + UUID.randomUUID().toString();
        Files.createDirectories(Paths.get(folderName));
        postFilesList = new ArrayList<>(Collections.singleton(folderName));
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
        postFilesList.clear();
        postFilesList = null;
    }
}
