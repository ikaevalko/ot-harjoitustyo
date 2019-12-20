# Käyttöohje

Lataa sovelluksen uusin julkaistu versio [täältä](https://github.com/ikaevalko/ot-harjoitustyo/releases).

## Sovelluksen käynnistäminen

Sovellus käynnistetään jar-tiedostosta komennolla

```
java -jar spaceshooter.jar
```

Lähdekoodi voidaan kääntää ja suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=spaceshooter.Main
```

## Kontrollien tyypit

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

Pelin nykyisessä versiossa on mahdollista vaihtaa asetta numeronäppäimillä 1, 2 ja 3.

## Uuden pelin aloittaminen

1. Klikkaa "New Game"
2. Valitse kontrollien tyyppi
3. Klikkaa "Start"

## Pelissä eteneminen

Pelissä edetään tuhoamalla vihollisaluksia, jotka ilmestyvät pelialueelle aalto kerrallaan. Pelaaja voi vahingoittaa ja hidastaa vihollisaluksia ampumalla. Tuhotut vihollisalukset kasvattavat pelaajan pistemäärää. Pelaaja voi myös tehdä väistöliikkeitä, joiden aikana ei voi ottaa osumaa. Peli päättyy pelaajan voittoon, jos tämä onnistuu selviämään kaikkien vihollisaaltojen läpi. Jos pelaajan avaruusalus tuhoutuu, peli päättyy pelaajan häviöön.








