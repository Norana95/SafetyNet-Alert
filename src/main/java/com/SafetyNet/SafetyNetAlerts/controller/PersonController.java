package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.dto.*;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//la classe controller fait appelle à la classe service
@RestController
@RequestMapping
public class PersonController {

    @Autowired
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //ajouter une nouvelle personne ;
    @PostMapping("/create")
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("persons/list")
    public List<Person> getPersonList() {
        return personService.getPersonList();
    }


    /*@GetMapping("/childAlert")
    public List<ArrayList<Person>> getListChildren(@RequestParam String address){
        return personService.getListChildren(address);
    }*/

    //mettre à jour une personne existante
    @PatchMapping("person/update")
    public void personUpdate(@RequestParam String firstName, String lastName, @RequestBody Person person) {
        personService.updatePerson(firstName, lastName, person);
    }

    //supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique
    @DeleteMapping("person/delete")
    public void deletePerson(@RequestParam String firstname, String lastname) {
        personService.deletePerson(firstname, lastname);
    }

    /*Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
    Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
    doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
    moins) dans la zone desservie.
    */
    @GetMapping("/firestation")
    public CalculateNumberOfAdultAndChildren getPersonByStation(@RequestParam String stationNumber) {
        return personService.getPersonByStation(stationNumber);
    }

    /* Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.*/
    @GetMapping("/childAlert")
    public List<ChildrenDTO> getChildrenByAddress(@RequestParam String address) {
        return personService.getChildrenList(address);
    }

    /* Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.*/
    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumber(@RequestParam(name = "firestation") String stationNumber) {
        return personService.getPhoneNumber(stationNumber);
    }

    /*Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
     de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
     médicaux (médicaments, posologie et allergies) de chaque personne. */
    @GetMapping("/fire")
    public List<FireDTO> getInhabitantByAddress(@RequestParam String address) {
        return personService.getInhabitantByAddress(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodDTO> getFoyerByStationList(@RequestParam(name = "stations") List<String> stations) {
        return personService.getFoyerByStationList(stations);
    }
  /*Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    toutes apparaître*/
    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersonByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return personService.getPersonByFirstNameAndLastName(firstName, lastName);
    }
    // Cette url doit retourner les adresses mail de tous les habitants de la ville.
    @GetMapping("/communityEmail")
    public List<String> getAddressMailByCity(@RequestParam String city) {
        return personService.getAddressMailByCity(city);
    }


}
