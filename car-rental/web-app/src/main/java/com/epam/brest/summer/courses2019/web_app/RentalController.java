package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Rental;
import com.epam.brest.summer.courses2019.service.RentalService;
import com.epam.brest.summer.courses2019.web_app.validators.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Rental controller.
 */
@Controller
public class RentalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    CarValidator carValidator;

    /**
     * Goto rentals list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/rentals")
    public final String rentals(Model model) {

        LOGGER.debug("findAll({})", model);
        model.addAttribute("rentals", rentalService.findAll());
        return "rentals";
    }

    /**
     * Goto edit rental page.
     *
     * @return view name
     */
    @GetMapping(value = "/rental/{id}")
    public final String gotoEditCarPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditRentalPage({},{})", id, model);
        Rental rental = rentalService.findById(id);
        model.addAttribute("rental", rental);
        return "rental";
    }

    /**
     * Update rental into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/car/{id}")
    public String updateRental(@Valid Rental rental, BindingResult result) {

        LOGGER.debug("updateRental({},{})", rental, result);
        carValidator.validate(rental, result);
        if(result.hasErrors()) {
            return "rental";
        } else {
            this.rentalService.update(rental);
            return "redirect:/rentals";
        }
    }

    /**
     * Goto add rental page.
     *
     * @return view name
     */
    @GetMapping(value = "/rental")
    public final String gotoAddRentalPage(Model model) {

        LOGGER.debug("gotoAddRentalPage({},{})", model);
        Rental rental = new Rental();
        model.addAttribute("isNew", true);
        model.addAttribute("rental", rental);
        return "rental";
    }

    /**
     * Persist new rental into persistence storage.
     *
     * @param rental new rental with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/rental")
    public String addRental(@Valid Rental rental, BindingResult result) {

        LOGGER.debug("addRental({},{})", rental, result);
        carValidator.validate(rental, result);
        if(result.hasErrors()) {
            return "rental";
        } else {
            this.rentalService.add(rental);
            return "redirect:/rentals";
        }
    }

    /**
     * Delete car.
     *
     * @return view name
     */
    @GetMapping(value = "/rental/{id}/delete")
    public final String deleteRentalById(@PathVariable Integer id, Model model) {

        LOGGER.debug("delete({},{})", id, model);
        rentalService.delete(id);
        return "redirect:/rentals";
    }

}
