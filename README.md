## Ohjelmistotekniikka-kurssin harjoitustyö

Harjoitustyön aiheena on avaruuslentelypeli, jossa tarkoituksena on selvitä mahdollisimman monesta vihollisaallosta.

Valittavissa on kaksi tapaa ohjata avaruusalusta:
	* WASD + Hiiri
	* WASD + JKL

### Dokumentaatio

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)

### Komennot

Ohjelman koodi voidaan suorittaa IDE:n avulla, tai navigoimalla kansioon "SpaceShooter" ja ajamalla komento

```
mvn compile exec:java -Dexec.mainClass=spaceshooter.Main
```

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan tiedostoon _target/site/jacoco/index.html_ komennolla

```
mvn test jacoco:report
```
