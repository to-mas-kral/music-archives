package com.tom.musicarchives.web;

import com.tom.musicarchives.model.Band;
import com.tom.musicarchives.model.BandDAO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BandController {
    private BandDAO dao;

    @Autowired
    public BandController(BandDAO dao) {
        this.dao = dao;
    }

    /*@GetMapping("/user/{id}")
    @ResponseBody
    public String userDetail(@PathVariable int id){
        return dao.getUserById(id).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String userDelete(@PathVariable int id){
        try{
            dao.deleteUser(id);
            return "User deleted.";
        }catch (Exception e){
            return "User does not exist.";
        }
    }*/

    /*@GetMapping("/bands")
    @ResponseBody
    public String bands(){
        Random r = new Random();

        Band u = dao.getBandById(1);

        Band post = new Band();
        post.setText("Test " + String.valueOf(r.nextInt()));

        post.setUser(u);
        u.addPost(post);

        dao.saveUser(u);

        List<Post> posts = dao.getPostsByUser(1);

        String out = "";

        for(Post p : posts){
            out += p.getText() + "<br>";
        }

        return out;
    }*/

    @GetMapping("/bands_view")
    public String allBands(Model model) {
        var bands = dao.getAllBands();
        model.addAttribute("bands", bands);

        return "bands_view";
    }

    @GetMapping("/new")
    public String newBand(Model model) {
        model.addAttribute("band", new Band());
        return "new";
    }

    @GetMapping("/process_new")
    public RedirectView processForm(@Valid @ModelAttribute("band") Band band, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return new RedirectView("/new");
        }

        if (band.getId() == 0) {
            dao.saveBand(band);
        } else {
            dao.updateBand(band);
        }

        return new RedirectView("/bands_view");
    }

    /*@GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable int id){
        try {
            User user = dao.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "new";
        }catch (Exception e){
            return "error";
        }
    }*/


    /*@InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }*/
}
