# TODO (Incident/Threat navigation + FXML runtime errors)

- [x] Fix Incident.fxml runtime NPE: ComboBox fx:id mismatch (controller expects comboDevice).
- [x] Fix Threats.fxml runtime NPE: TableColumn fx:id mismatch (controller expects colThreatId/colThreatDescription/colThreatSeverity).

- [x] Ensure incidents/threats FXML tables are correctly wired to controller @FXML fields.

- [ ] Keep FXML controller fields consistent with model types and cell factories.
- [ ] Rebuild and run app; click Dashboard -> Incidents/Threats to verify no FXMLLoader exceptions.

