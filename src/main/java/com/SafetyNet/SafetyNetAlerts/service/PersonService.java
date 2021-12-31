package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.dto.*;
import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//service fait appelle à repository
//dans la classe service on retrouve les methodes metier

@Service
public class PersonService {

    @Autowired
    DataService dataService;

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService(DataService dataService) {
        this.dataService = dataService;
    }

    //ajouter une nouvelle personne
    public void savePerson(Person person) {
        logger.info("inside class : PersonService | method : savePerson" + person);
        Person newPerson = new Person();
        newPerson.setFirstName(person.firstName);
        newPerson.setLastName(person.lastName);
        newPerson.setAddress(person.address);
        newPerson.setCity(person.city);
        newPerson.setZip(person.zip);
        newPerson.setPhone(person.phone);
        newPerson.setEmail(person.email);
        List<Person> personList = dataService.getPersons();
        try {
            personList.add(newPerson);
            logger.info("person saved !");
        } catch (Exception exception) {
            logger.error("error ! person not saved " + exception.getMessage());
        }
    }

    public List<Person> getPersonList() {
        logger.info("inside class : PersonService | method : getPersonList");
        return dataService.getPersons();
    }

    //mettre à jour une personne existante
    public void updatePerson(String firstName, String lastName, Person personUpdated) {
        logger.info("inside class : PersonService | method : updatePerson");
        List<Person> personList = dataService.getPersons();
        boolean found = false;
        try {
            for (Person person : personList) {
                if (person.firstName.equals(firstName) && person.lastName.equals(lastName)) {
                    found = true;
                    person.setFirstName(personUpdated.firstName);
                    person.setLastName(personUpdated.lastName);
                    person.setAddress(personUpdated.address);
                    person.setCity(personUpdated.city);
                    person.setEmail(personUpdated.email);
                    person.setPhone(personUpdated.phone);
                    person.setZip(personUpdated.zip);
                    personList.set(personList.indexOf(person), person);
                    logger.info("person updated");
                    break;
                }
            }
        }
        catch (Exception exception) {
            logger.error("error ! person not updated " + exception.getMessage());
        }
        if(found == false){
            logger.info("person not updated, it not exist !");
        }
    }

    //supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur
    public void deletePerson(String firstname, String lastname) {
        logger.info("inside class : PersonService | method : deletePerson");
        List<Person> personList = dataService.getPersons();
        try {
            personList.removeIf(person -> person.getFirstName().equals(firstname) && person.getLastName().equals(lastname));
            logger.info("person deleted");
        } catch (Exception e) {
            logger.error("error ! person not deleted" + e.getMessage());
        }
    }


    /*Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
      Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
      doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
      elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
      moins) dans la zone desservie*/
    public CalculateNumberOfAdultAndChildren getPersonByStation(String station) {
        logger.info("inside class : PersonService | method : getPersonByStation " + "with station: " + station);
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        CalculateNumberOfAdultAndChildren calculateNumberOfAdultAndChildren = new CalculateNumberOfAdultAndChildren();
        int adult = 0;
        int children = 0;
        try {
            List<Person> personByStation = new ArrayList<>();
            for (Firestation firestation : firestationList) {
                if (firestation.getStation().equals(station)) {
                    for (Person person : personList) {
                        if (person.getAddress().equals(firestation.getAddress())) {
                            personByStation.add(person);
                            for (Medicalrecord medicalrecord : medicalrecordList) {
                                if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                                    String birthDate = medicalrecord.getBirthdate();
                                    LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                                    LocalDate currentDate = LocalDate.now();
                                    int age = Period.between(birthdayDate, currentDate).getYears();
                                    if (age <= 18) {
                                        children = children + 1;
                                        calculateNumberOfAdultAndChildren.setChildren(children);
                                    } else {
                                        adult = adult + 1;
                                        calculateNumberOfAdultAndChildren.setAdult(adult);
                                    }
                                    break;
                                }

                            }
                        }
                    }
                }
            }
            calculateNumberOfAdultAndChildren.setPerson(personByStation);
            logger.info("check result in : http://localhost:8080/firestation?stationNumber=" + station);
        } catch (Exception exception) {
            logger.error("error happened ! " + exception.getMessage());
        }
        return calculateNumberOfAdultAndChildren;
    }

    /*Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
     membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide. */

    public List<ChildrenDTO> getChildrenList(String address) {
        logger.info("inside class : PersonService | method : getChildrenList " + "with address : " + address);
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<ChildrenDTO> childrenDTOList = new ArrayList<>();
        List<Person> foyer = new ArrayList<>();
        try {
            for (Person person : personList) {
                if (address.equals(person.address)) {
                    for (Medicalrecord medicalrecord : medicalrecordList) {
                        if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                            String birthDate = medicalrecord.getBirthdate();
                            LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                            LocalDate currentDate = LocalDate.now();
                            int age = Period.between(birthdayDate, currentDate).getYears();
                            if (age <= 18) {
                                ChildrenDTO childrenDTO = new ChildrenDTO();
                                childrenDTO.setFirstName(person.firstName);
                                childrenDTO.setLastName(person.lastName);
                                childrenDTO.setAge(age);
                                childrenDTO.setFoyer(foyer);
                                childrenDTOList.add(childrenDTO);
                            } else {
                                foyer.add(person);
                            }
                            break;
                        }
                    }

                }
            }
            logger.info("check result in :  http://localhost:8080/childAlert?address=" + address.replaceAll(" ", "+"));
        } catch (Exception exception) {
            logger.error("error happened ! " + exception.getMessage());
        }
        return childrenDTOList;
    }

    /*Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques. */
    public List<String> getPhoneNumber(String stationNumber) {
        logger.info("inside class : PersonService | method : getPhoneNumber with station : " + stationNumber);
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<String> phoneNumber = new ArrayList<>();
        try {
            for (Firestation firestation : firestationList) {
                if (firestation.getStation().equals(stationNumber)) {
                    for (Person person : personList) {
                        if (firestation.getAddress().equals(person.getAddress())) {
                            if (phoneNumber.contains(person.getPhone())) {
                                break;
                            } else {
                                phoneNumber.add(person.getPhone());
                            }
                        }
                    }
                }
            }
            logger.info("check result in : http://localhost:8080/phoneAlert?firestation=" + stationNumber);
        } catch (Exception exception) {
            logger.error("error ! " + exception.getMessage());
        }
        return phoneNumber;
    }

    /*Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/
    public List<FireDTO> getInhabitantByAddress(String address) {
        logger.info("inside class : PersonService | method : getInhabitantByAddress with address : " + address);
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<Firestation> firestationList = dataService.getFirestations();
        List<FireDTO> inhabitants = new ArrayList<>();
        try {
            for (Person person : personList) {
                if (person.getAddress().equals(address)) {
                    FireDTO fireDTO = new FireDTO();
                    fireDTO.setLastName(person.getLastName());
                    fireDTO.setPhoneNumber(person.getPhone());
                    for (Medicalrecord medicalrecord : medicalrecordList) {
                        if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                            String birthDate = medicalrecord.getBirthdate();
                            LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                            LocalDate currentDate = LocalDate.now();
                            int age = Period.between(birthdayDate, currentDate).getYears();
                            fireDTO.setAge(age);
                            fireDTO.setMedications(medicalrecord.getMedications());
                            fireDTO.setAllergies(medicalrecord.getAllergies());
                            for (Firestation firestation : firestationList) {
                                if (firestation.getAddress().equals(address)) {
                                    fireDTO.setStationNumber(firestation.getStation());
                                    inhabitants.add(fireDTO);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            logger.info("check result in : http://localhost:8080/fire?address=" + address.replaceAll(" ", "+"));
        } catch (Exception exception) {
            logger.error("error !" + exception.getMessage());
        }

        return inhabitants;
    }

    /*Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
     */
    public List<FloodDTO> getFoyerByStationList(List<String> stations) {
        logger.info("inside class : PersonService | method : getFoyerByStationList " + "with stations list : " + stations);
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<FloodDTO> floodDTOList = new ArrayList<>();
        try {
            for (Firestation firestation : firestationList) {
                if (stations.contains(firestation.getStation())) {
                    List<Foyer> foyerList = new ArrayList<>();
                    FloodDTO floodDTO = new FloodDTO();
                    floodDTO.setAddress(firestation.address);
                    for (Person person : personList) {
                        if (person.getAddress().equals(firestation.getAddress())) {
                            Foyer foyer = new Foyer();
                            foyer.setName(person.lastName);
                            foyer.setPhone(person.phone);
                            for (Medicalrecord medicalrecord : medicalrecordList) {
                                if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                                    String birthDate = medicalrecord.getBirthdate();
                                    LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                                    LocalDate currentDate = LocalDate.now();
                                    int age = Period.between(birthdayDate, currentDate).getYears();
                                    foyer.setMedications(medicalrecord.getMedications());
                                    foyer.setAllergies(medicalrecord.getAllergies());
                                    foyer.setAge(age);
                                    foyerList.add(foyer);
                                    break;
                                }
                            }
                        }
                    }
                    floodDTO.setFoyer(foyerList);
                    floodDTOList.add(floodDTO);
                }
            }
            logger.info("check result in : " + "http://localhost:8080/flood/stations?stations=" + stations);
        } catch (Exception exception) {
            logger.error("error ! " + exception.getMessage());
        }
        return floodDTOList;
    }

    /*Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    toutes apparaître*/
    public List<PersonInfoDTO> getPersonByFirstNameAndLastName(String firstName, String lastName) {
        logger.info("inside class : PersonService | method : getPersonByFirstNameAndLastName with firstname : " + firstName + " / lastName : " + lastName);
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();
        try {
            for (Person person : personList) {
                if (person.lastName.equals(lastName)) {
                    PersonInfoDTO personInfoDTO = new PersonInfoDTO();
                    personInfoDTO.setFirstName(person.firstName);
                    personInfoDTO.setLastName(person.lastName);
                    personInfoDTO.setAddress(person.address);
                    personInfoDTO.setAddressMail(person.email);
                    for (Medicalrecord medicalrecord : medicalrecordList) {
                        if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                            String birthDate = medicalrecord.getBirthdate();
                            LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                            LocalDate currentDate = LocalDate.now();
                            int age = Period.between(birthdayDate, currentDate).getYears();
                            personInfoDTO.setAge(age);
                            personInfoDTO.setMedications(medicalrecord.medications);
                            personInfoDTO.setAllergies(medicalrecord.allergies);
                            personInfoDTOS.add(personInfoDTO);
                            break;
                        }
                    }
                }
            }
            logger.info("check result in : http://localhost:8080/personInfo?firstName=" + firstName + "&lastName=" + lastName);
        } catch (Exception e) {
            logger.error("error ! " + e.getMessage());
        }
        return personInfoDTOS;
    }

    // Cette url doit retourner les adresses mail de tous les habitants de la ville.
    public List<String> getAddressMailByCity(String city) {
        logger.info("inside class : PersonService | method : getAddressMailByCity with city : " + city);
        List<String> adressMail = new ArrayList<>();
        List<Person> personList = dataService.getPersons();
        try {
            for (Person person : personList) {
                if (person.city.equals(city)) {
                    adressMail.add(person.email);
                }
            }
            logger.info("check result in : http://localhost:8080/communityEmail?city=" + city);
        } catch (Exception exception) {
            logger.error("error ! " + exception.getMessage());
        }
        return adressMail;
    }
}
