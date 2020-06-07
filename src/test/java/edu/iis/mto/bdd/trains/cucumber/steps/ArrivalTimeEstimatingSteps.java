package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładającże;

public class ArrivalTimeEstimatingSteps {

    @Zakładającże("^chcę się dostać z (.*) do (.*)$")
    public void givenStartAndDestination(String from, String to) {
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (\\d+):(\\d+) na linii Western$")
    public void następnyPociągOdjeżdżaONaLiniiWestern(int arg0, int arg1) {
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void zapytamOGodzinęPrzyjazdu() {
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (\\d+):(\\d+)$")
    public void powinienemUzyskaćNastępującySzacowanyCzasPrzyjazdu(int arg0, int arg1) {
    }
}
