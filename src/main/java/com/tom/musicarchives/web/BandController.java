package com.tom.musicarchives.web;

import com.tom.musicarchives.model.Band;
import com.tom.musicarchives.model.BandDAO;

import com.tom.musicarchives.utils.BandSearchQuery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BandController {
    private BandDAO dao;

    @Autowired
    public BandController(BandDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/bands")
    public String allBands(Model model) {
        var bands = dao.getAllBands();
        var band_search_query = new BandSearchQuery();
        model.addAttribute("bands", bands);
        model.addAttribute("band_search_query", band_search_query);

        return "band/bands_view";
    }

    @GetMapping("/band/detail/{id}")
    public String bandDetail(Model model, @PathVariable int id) {
        var band = dao.getBandById(id);
        var albums = dao.getBandAlbums(id);
        var members = dao.getBandMembers(id);

        model.addAttribute("band", band);
        model.addAttribute("albums", albums);
        model.addAttribute("members", members);

        return "band/band_detail_view";
    }

    @GetMapping("/band/new")
    public String newBand(Model model) {
        model.addAttribute("band", new Band());
        return "band/band_new";
    }

    @GetMapping("/band/edit/{id}")
    public String editBand(Model model, @PathVariable int id) {
        var band = dao.getBandById(id);
        model.addAttribute("band", band);

        return "band/band_new";
    }

    @GetMapping("/band/delete/{id}")
    public RedirectView deleteBand(Model model, @PathVariable int id) {
        dao.deleteBand(id);

        return new RedirectView("/bands");
    }

    @GetMapping("/band/process_new")
    public String processForm(@Valid @ModelAttribute("band") Band band, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "/band/band_new";
        }

        if (band.getId() == 0) {
            dao.saveBand(band);
        } else {
            dao.updateBand(band);
        }

        return "redirect:/bands";
    }

    @GetMapping("/band/search")
    public String bandSearch(@Valid @ModelAttribute("band") BandSearchQuery bandSearchQuery, Model model) {
        var filtered_bands = dao.searchBands(bandSearchQuery);
        model.addAttribute("bands", filtered_bands);

        return "band/bands_search_view";
    }

    @GetMapping("/band/random")
    public String bandSearch(Model model) {
        var band = dao.getRandomBand();

        return "redirect:/band/detail/" + band.getId();
    }
}
