# Backend-for-Netflix-platorm
![image](https://user-images.githubusercontent.com/73692534/220122735-12a1c62c-b28e-412b-a646-5cdb3d3224e7.png)
Obiective
implementarea unui backend simplu al unei platforme specifice vizualizării de filme și seriale precum Netflix sau HBO MAX;
dezvoltarea unor abilități de organizare și design orientat-obiect;
scrierea unui cod cât mai generic, ce va permite ulterior adăugarea de noi funcționalități (cele specifice etapei a doua);
folosirea unor design patterns potrivite pentru implementarea diferitelor task-uri;
respectarea unui stil standard în momentul scrierii liniilor de cod și comentariilor;
Scenariu
În ziua de astăzi, platformele de vizualizare de filme sau seriale precum Netflix sau HBO MAX au luat o amploare extrem de mare atât în România, dar mai ales în întreaga lume.

În acest sens, pentru că știm că ți-ai dat silința de-a lungul întregului semestru pentru a vizualiza cele mai recente filme sau episoade din serialele favorite, vrem să îți oferim posibilitatea de a implementa o platformă similară cu scopul de a înțelege și funcționalitățile acesteia.

Te invităm să ajuți cât mai curând atât întreaga planetă pentru ca aceasta să poată vizualiza în continuare serialele și filmele preferate, cât și pe tine pentru că ne dorim să te bucuri alături de familie de sărbători și nu lucrând la proiectul de POO.

Context
Ne dorim implementarea unei platfome pentru vizualizarea de filme și seriale. Inițial ne gândim la câteva funcționalități standard, primele care ne vin în minte, pe care le vom propune noi după cum urmează: register, login, logout, search, view movie, rating, etc.

Rolul vostru este să implementați funcționalitățile acestei pagini din perspectiva unui utilizator și să îi transmiteți acestuia ce anume are voie și nu are voie să realizeze la un anumit moment de timp, pe platforma voastră.

Ulterior, în etapa a doua a proiectului ne vom da seama că platforma noastră a devenit foarte populară, iar cerințele utilizatorilor sunt mult mai mari decât posibilitățile acesteia. Astfel, vom adăuga noi funcționalități, drept continuare a celor deja existente.
Se încarcă datele citite din fișierul de test (ce este în format JSON), în obiecte;
Pentru prima etapă a proiectului se vor oferi inițial: lista cu userii deja înregistrați pe platformă (aceasta va putea fi modificată doar prin operația de register explicată ulterior) și lista filmelor existente pe platformă. Bineînțeles, fiecare dintre acestea vor fi identificate după caracteristicile proprii;
Se primesc secvențial acțiuni („change page” sau „on page” pentru prima etapă) și se execută pe măsură ce sunt primite, rezultatul lor având efect asupra datelor din platformă la un anumit moment de timp;
După executarea unor acțiuni (vom detalia ulterior care dintre acțiuni), se afișează rezultatul ei în fișierul JSON de ieșire;
La terminarea tuturor acțiunilor se termina și execuția programului și se trece la următorul set de teste.
Structura site-ului POO TV
În cadrul primei etape, ați avut de implementat structura platformei noastre de streaming video, care a constat în definirea paginilor care pot fi accesate de un utilizator și flow-ul de accesare al acestora.

![image](https://user-images.githubusercontent.com/73692534/220123424-4d1020e2-6490-44d8-8875-d8f6e3b89098.png)


Am introdus schema precedentă pentru a avea o imagine și mai clară asupra paginilor existente pe site-ul nostru, modul în care acestea pot fi accesate (în urma acțiunilor de tipul change page) precum și acțiunile care pot fi realizate în cadrul fiecărei pagini. (acțiunile on page).
