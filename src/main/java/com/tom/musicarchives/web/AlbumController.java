package com.tom.musicarchives.web;

import com.tom.musicarchives.model.AlbumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AlbumController {
    private AlbumDAO dao;

    @Autowired
    public AlbumController(AlbumDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/album/detail/{id}")
    public String albumDetail(Model model, @PathVariable int id) {
        var album = dao.getAlbumById(id);

        model.addAttribute("album", album);

        return "album_detail_view";
    }
}
