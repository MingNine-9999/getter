package com.payload.getter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.payload.getter.service.ArrangeService.arrangeString;

@Controller
public class GetController {

    @PostMapping("/getter/{title}")
    public ResponseEntity<Void> getting(@PathVariable String title, @RequestBody String body) {
        try (FileOutputStream stream = new FileOutputStream("./" + title + ".json")) {
            stream.write(arrangeString(body).getBytes(StandardCharsets.UTF_8));

            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }


}