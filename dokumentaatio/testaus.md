# Testaus

Sovellukselle on määritelty automaattisia yksikkötestejä. Käyttöliittymää ja ohjelman kokonaisvaltaista toimintaa on testattu manuaalisesti.

Testit suoritetaan komennolla

```
mvn test
```

Automaattiset JUnit-testit testaavat sovelluslogiikkaa ja tietokantaa. Käyttöliittymäluokka SpaceShooterUi on poistettu testauksen piiristä. Pakkauksen spaceshooter.input luokille ei ole määritelty testejä, koska ne käsittelevät vain näppäinpainalluksia, eivätkä täten sisällä oleellista sovelluslogiikkaa.
