# Ohjelmistotekniikka-kurssin harjoitustyö

Harjoitustyön aiheena on avaruuslentelypeli, jossa tarkoituksena on selvitä mahdollisimman monesta vihollisaallosta.

Kontrollityypin "Mouse + Keyboard" painikkeet:
- liiku = WASD
- tähtää = hiiri
- ammu = hiiren vasen nappi
- väistä = hiiren oikea nappi

Kontrollityypin "Keyboard Only" painikkeet:
- liiku = WASD
- tähtää = J, L
- ammu = K
- väistä = välilyönti

Aseen tyyppiä voi vaihtaa numeronäppäimistä. Nykyisessä versiossa on kolme asetta:
- Bullet (1)
- ScatterShot (2)
- MachineGun (3)

## Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

[Testaus](dokumentaatio/testaus.md)

[Työaikakirjanpito](dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Loppupalautus](https://github.com/ikaevalko/ot-harjoitustyo/releases/tag/loppupalautus)

[Viikko6](https://github.com/ikaevalko/ot-harjoitustyo/releases/tag/viikko6)

[Viikko5](https://github.com/ikaevalko/ot-harjoitustyo/releases/tag/viikko5)

## Komennot

Huom. komennot on suoritettava kansiossa _ot-harjoitustyo/SpaceShooter_

### Ohjelman kääntäminen ja suorittaminen

Ohjelman koodi voidaan kääntää IDE:n avulla tai ajamalla komento

```
mvn compile exec:java -Dexec.mainClass=spaceshooter.Main
```

Suoritettava jar-tiedosto generoidaan komennolla

```
mvn package
```

kansioon _target_ nimellä _SpaceShooter-1.0-SNAPSHOT.jar_

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

### Javadoc

Javadoc luodaan kansioon _target/site/apidocs/_ komennolla

```
mvn javadoc:javadoc
```
