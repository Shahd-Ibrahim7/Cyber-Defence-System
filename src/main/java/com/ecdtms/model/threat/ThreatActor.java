package com.ecdtms.model.threat;

public class ThreatActor {

    private String alias; // A pseudonym or name used to identify the threat actor, such as "Fancy Bear" or "Lazarus Group".
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