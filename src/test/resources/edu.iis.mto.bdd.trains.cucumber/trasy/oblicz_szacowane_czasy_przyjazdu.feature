# language: pl
Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
	W celu bardziej efektywnego planowania podróży
	Jako podróżny
	Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

Szablon scenariusza: Szacowanie czasu przyjazdu
	Zakładając chcę się dostać z "<Start>" do "<End>"
    I następny pociąg odjeżdża o <LeavingTime> na linii "<Line>"
	Gdy zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: <ArrivingTime>

    Przykłady:
        |   Start       |   End         |   LeavingTime   |   Line   |   ArrivingTime   |
        |   Parramatta  |   Town Hall   |   8:02          |  Western |   8:29           |