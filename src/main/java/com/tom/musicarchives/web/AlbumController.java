package com.tom.musicarchives.web;

import com.tom.musicarchives.model.Album;
import com.tom.musicarchives.model.AlbumDAO;
import com.tom.musicarchives.model.Band;
import com.tom.musicarchives.model.BandDAO;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

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

        return "album/album_detail_view";
    }

    @GetMapping("/album/new/{band_id}")
    public String addAlbum(Model model, @PathVariable int band_id) {
        model.addAttribute("album", new Album());
        model.addAttribute("band_id", band_id);

        return "album/album_new";
    }

    @GetMapping("/album/process_new/{band_id}")
    public RedirectView processForm(@Valid @ModelAttribute("album") Album album, BindingResult br, Model model, @PathVariable int band_id) {
        if (br.hasErrors()) {
            return new RedirectView("/bands");
        }

        if (album.getId() == 0) {
            dao.saveAlbum(album, band_id);
        } else {
            dao.updateAlbum(album);
        }

        return new RedirectView("/album/detail/" + album.getId());
    }
}
