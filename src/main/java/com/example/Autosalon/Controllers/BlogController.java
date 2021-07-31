package com.example.Autosalon.Controllers;

import com.example.Autosalon.bean.Cars;
import com.example.Autosalon.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private CarsRepository carsRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Cars> cars = carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam(value = "Id", required = false) Integer id, @RequestParam String brand, @RequestParam String modell, @RequestParam String color, @RequestParam Integer yearMake,
                              @RequestParam String fuelType, @RequestParam Double engine, @RequestParam Integer maxSpeed, Model model){
        Cars cars = new Cars(id, brand, modell, color, yearMake, fuelType, engine, maxSpeed);
        carsRepository.save(cars);
        return "redirect:/blog";
    }

    @GetMapping("/blog{id}")
    public String blogDetail(@PathVariable(value = "id") Integer id, Model model){
        if (!carsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Cars> car = carsRepository.findById(id);
        ArrayList<Cars> result = new ArrayList<>();
        car.ifPresent(result::add);
        model.addAttribute("car", result);
        return "blog-detail";
    }

    @GetMapping("/blog{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Integer id, Model model){
        if (!carsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Cars> car = carsRepository.findById(id);
        ArrayList<Cars> result = new ArrayList<>();
        car.ifPresent(result::add);
        model.addAttribute("car", result);
        return "blog-edit";
    }

    @PostMapping("/blog{id}/edit")
    public String blogPostAddEdit(@PathVariable(value = "id") @RequestParam(value = "id", required = false) Integer id, @RequestParam String brand, @RequestParam String modell, @RequestParam String color, @RequestParam Integer yearMake,
                              @RequestParam String fuelType, @RequestParam Double engine, @RequestParam Integer maxSpeed, Model model){
        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setId(id); cars.setBrand(brand); cars.setModell(modell); cars.setColor(color); cars.setYearMake(yearMake);
        cars.setFuelType(fuelType); cars.setEngine(engine); cars.setMaxSpeed(maxSpeed);
        carsRepository.save(cars);
        return "redirect:/blog";
    }
}
