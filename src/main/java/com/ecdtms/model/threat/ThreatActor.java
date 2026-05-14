package com.ecdtms.model.threat;

public class ThreatActor {

    private String alias;
    private SophisticationLevel sophisticationLevel;

    public ThreatActor(String alias, SophisticationLevel sophisticationLevel) {
        this.alias = alias;
        this.sophisticationLevel = sophisticationLevel;
    }

    @Override
    public String toString() {
        return "Threat Actor: " + alias +
                " (Level: " + sophisticationLevel + ")";
    }
}