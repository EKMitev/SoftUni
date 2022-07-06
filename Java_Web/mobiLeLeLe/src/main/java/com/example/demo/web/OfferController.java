package com.example.demo.web;

import com.example.demo.models.dto.AddOfferDTO;
import com.example.demo.models.dto.OfferDTO;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import com.example.demo.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public String allOffers(Model model) {
        List<OfferDTO> allOffers = this.offerService.getAllOffers();
        model.addAttribute("offers", allOffers);

        return "offers";
    }


    @ModelAttribute("addOfferModel")
    public AddOfferDTO initOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("brands", this.brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String submitOffer(@Valid AddOfferDTO addOfferModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }


        this.offerService.addOffer(addOfferModel, principal);
        return "redirect:/offers/all";
    }
}
