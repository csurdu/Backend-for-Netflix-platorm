Avand in vedere ca datele de intrare sunt primite din fisiere JSON, am creat pachetul fileio, care contine clase care mapeaza datele din fisierele 
JSON in obiecte Java. Clasa principala este cea de Input, care contine toate datele dintr-un fisier de intrare, adica o lista cu utilizatori, 
o lista cu filme si o lista cu actiunile ce trebuie executate.
Pentru a crea un obiect de tip Input dintr-un fisier JSON se foloseste metoda statica readInputFromFile a clasei DataInputReader. Aici se foloseste
libraria de jackson pentru conversie.

Obiectul de tip Input este pasat ca argument constructorului ActionsSolver, care mai primeste si numele fisierului de iesire.
Clasa de ActionsSolver are si metoda executeActions, care itereaza peste toate actiunile, le executa si salveaza output-ul. La finalul programului, 
pe baza output-ului primit in urma tuturor actiunilor, se apeleaza metoda writeToOutputFile(), care scrie rezultatele in fisierul JSON.

Pentru reprezentarea paginilor, s-a ales o abordare bazata pe mostenire: exista o clasa abstracta Page ce defineste doua metode: una care indica
care pagini sunt accesibile de pe pagina curenta si una care indica care actiuni pot fi executate pe pagina curenta.
Ulterior, s-au creat clase pentru fiecare pagina si s-au suprascris metodele cu abordarile specifice pe fiecare pagina.
Exista si clasa PageFactory care utilizeaza design pattern-ul Factory si aceasta construieste o pagina in functie de campul page (String) primit ca input

Tot bazat pe mostenire, s-a creat o clasa GenericAction care are 2 clase copil: OnPageAction si ChangePageAction si de asemenea, utilizand DP Factory,
avem clasa ActionFactory care, in functie de o actiune primita ca input (din JSON), creeaza un obiect OnPageAction sau ChangePageAction in functie de caz.


Pachetul data contine logica pentru a executa actiunile. Avem clasele PlatformMovie si PlatformUser care contin datele pentru filme si utilizatori,
clasa StreamingPlatform pentru platforma si PlatformManager care este puntea de legatura intre ActionSolver si StreamingPlatform

Pachetul mai pune la dispozitie clase ce implementeaza interfata Comparator pentru PlatformMovie, pentru executare filtrelor.
Acestea sunt in functie de sortare si rating (crescator si descrescator).