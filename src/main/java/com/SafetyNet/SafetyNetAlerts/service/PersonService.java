package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.dto.*;
import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.model.Person;
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

    public PersonService(DataService dataService) {
        this.dataService = dataService;
    }

    //ajouter une nouvelle personne
    public Person savePerson(Person person) {
        List<Person> personList = dataService.getPersons();
        personList.add(person);
        return personList.get(personList.indexOf(person));
    }

    //*************************************************faut l'effacer celle la, c'est juste un test
    public List<Person> getPersonList() {
        return dataService.getPersons();
    }
    //****************************************************************


    //mettre à jour une personne existante
    public Person updatePerson(Person person) {
        if (dataService.getPersons().contains(person)) {
            Person personUpdated = dataService.getPersons().get(dataService.getPersons().indexOf(person));
            personUpdated.setAddress(new String());
            personUpdated.setCity(new String());
            personUpdated.setEmail(new String());
            personUpdated.setPhone(new String());
            personUpdated.setZip(new String());
            System.out.println("person updated !");
        } else {
            System.out.println("this person don't exists !");
        }
        return person;
    }

    //supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur
    public void deletePerson(String firstname, String lastname) {
        List<Person> personList = dataService.getPersons();
        Person personDeleted;
        for (Person person : personList) {
            if (person.getFirstName().equals(firstname) && person.getLastName().equals(lastname)) {
                if (personList.remove(person)) {
                    personDeleted = person;
                    System.out.println("la personne a été supprimé");
                }
            } else {
                System.out.println("il n'ya pas de person avec le nom et le prenom que vous avez saisi");
            }
        }
    }


    /*Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
      Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
      doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
      elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
      moins) dans la zone desservie*/
    public CalculateNumberOfAdultAndChildren getPersonByStation(String station) {
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        CalculateNumberOfAdultAndChildren calculateNumberOfAdultAndChildren = new CalculateNumberOfAdultAndChildren();
        int adult = 0;
        int children = 0;
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
        return calculateNumberOfAdultAndChildren;
    }

    /*Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
     membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide. */

    public List<ChildrenDTO> getChildrenList(String address) {
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<ChildrenDTO> childrenDTOList = new ArrayList<>();
        List<Person> foyer = new ArrayList<>();

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
        return childrenDTOList;
    }

    /*Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques. */
    public List<String> getPhoneNumber(String stationNumber) {
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<String> phoneNumber = new ArrayList<>();
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
        return phoneNumber;
    }

    /*Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.*/
    public List<FireDTO> getInhabitantByAddress(String address) {
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<Firestation> firestationList = dataService.getFirestations();
        List<FireDTO> inhabitants = new ArrayList<>();
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
        return inhabitants;
    }

    /*Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
     */
    public List<FloodDTO> getFoyerByStationList(List<String> station) {
        List<Firestation> firestationList = dataService.getFirestations();
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<FloodDTO> floodDTOList = new ArrayList<>();
        for (Firestation firestation : firestationList) {
            List<FireDTO> residents = new ArrayList<>();
            if (station.contains(firestation.getStation())) {
                FloodDTO floodDTO = new FloodDTO();
                floodDTO.setAddress(firestation.address);
                for (Person person : personList) {
                    if (person.getAddress().equals(firestation.getAddress())) {
                        FireDTO fireDTO = new FireDTO();
                        fireDTO.setLastName(person.lastName);
                        for (Medicalrecord medicalrecord : medicalrecordList) {
                            if (person.firstName.equals(medicalrecord.firstName) && person.lastName.equals(medicalrecord.lastName)) {
                                String birthDate = medicalrecord.getBirthdate();
                                LocalDate birthdayDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                                LocalDate currentDate = LocalDate.now();
                                int age = Period.between(birthdayDate, currentDate).getYears();
                                fireDTO.setMedications(medicalrecord.getMedications());
                                fireDTO.setAllergies(medicalrecord.getAllergies());
                                fireDTO.setPhoneNumber(person.phone);
                                fireDTO.setAge(age);
                                residents.add(fireDTO);
                                break;
                            }
                        }
                    }
                }
                floodDTO.setResidents(residents);
                floodDTOList.add(floodDTO);
            }
        }
        return floodDTOList;
    }
    /*Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    toutes apparaître*/
    public List<PersonInfoDTO> getPersonByFirstNameAndLastName(String firstName, String lastName) {
        List<Person> personList = dataService.getPersons();
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();
        for (Person person : personList) {
            if (person.firstName.equals(firstName) && person.lastName.equals(lastName)) {
                PersonInfoDTO personInfoDTO = new PersonInfoDTO();
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
        return personInfoDTOS;
    }

    // Cette url doit retourner les adresses mail de tous les habitants de la ville.
    public List<String> getAddressMailByCity(String city) {
        List<String> adressMail = new ArrayList<>();
        List<Person> personList = dataService.getPersons();
        for (Person person : personList) {
            if (person.city.equals(city)) {
                adressMail.add(person.email);
            }
        }
        return adressMail;
    }
}
