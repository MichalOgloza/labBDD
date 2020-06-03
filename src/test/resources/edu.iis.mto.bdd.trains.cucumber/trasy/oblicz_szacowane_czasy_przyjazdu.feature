# language: pl
Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
	W celu bardziej efektywnego planowania podróży
	Jako podróżny
	Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

Scenariusz: Szacowanie czasu przyjazdu
    Zakładając chcę się dostać z Epping do Central
    I następny pociąg odjeżdża o <time> na linii <line>
        | 8:03 | Northern |
        | 8:07 | Newcastle |
        | 8:07 | Newcastle |
        | 8:13 | Epping    |
    Gdy zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu:
        | 8:48 |
        | 8:37 |
        | 8:37 |
        | 8:51 |
