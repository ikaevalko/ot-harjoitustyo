# Ohjelmistotekniikka-kurssin harjoitustyö

Harjoitustyön aiheena on avaruuslentelypeli, jossa tarkoituksena on selvitä mahdollisimman monesta vihollisaallosta.

Valittavissa on kaksi tapaa ohjata avaruusalusta:
- WASD + Hiiri
- WASD + JKL

## Dokumentaatio

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)

## Komennot

Huom. komennot on suoritettava kansiossa ot-harjoitustyo/SpaceShooter.

### Ohjelman kääntäminen ja suorittaminen

Ohjelman koodi voidaan kääntää IDE:n avulla tai ajamalla komento

```
mvn compile exec:java -Dexec.mainClass=spaceshooter.Main
```

Suoritettava jar-tiedosto generoidaan komennolla

```
mvn package
```

kansioon _target_ nimellä _SpaceShooter-1.0-SNAPSHOT.jar

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan tiedostoon _target/site/jacoco/index.html_ komennolla

```
mvn test jacoco:report
```

### Checkstyle

Tiedostossa [checkstyle.xml](SpaceShooter/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle-tarkistuksen tulokset löytyvät tiedostosta _target/site/checkstyle.html_
