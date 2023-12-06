package com.tom.musicarchives.web;

import com.tom.musicarchives.model.Band;
import com.tom.musicarchives.model.BandDAO;
import com.tom.musicarchives.model.Song;
import com.tom.musicarchives.model.SongDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SongController {
    private SongDAO dao;

    @Autowired
    public SongController(SongDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/song/new/{album_id}")
    public String newSong(Model model, @PathVariable int album_id) {
        model.addAttribute("song", new Song());
        model.addAttribute("album_id", album_id);
        return "song/song_new";
    }

    @GetMapping("/song/process_new/{album_id}")
    public RedirectView addSong(@Valid @ModelAttribute("song") Song song, @PathVariable int album_id) {
        dao.saveSong(song, album_id);

        return new RedirectView("/album/detail/" + album_id);
    }
}
