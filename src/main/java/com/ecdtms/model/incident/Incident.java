package com.ecdtms.model.incident;

import com.ecdtms.model.network.Device;
import com.ecdtms.model.threat.Threat;
import com.ecdtms.model.threat.Vulnerability;
import com.ecdtms.model.personnel.SecurityAnalyst;
import java.time.LocalDateTime;

public class Incident {

    private int incidentId;
    private String title;
    private Device affectedDevice;
    private Threat detectedThreat;
    private SecurityAnalyst assignedAnalyst;
    private IncidentStatus status;
    private LocalDateTime timestamp;

    public Incident(int incidentId, String title, Device device, Threat threat) {
        this.incidentId = incidentId;
        this.title = title;
        this.affectedDevice = device;
        this.detectedThreat = threat;
        this.status = IncidentStatus.OPEN;
        this.timestamp = LocalDateTime.now();
    }

    public void assignAnalyst(SecurityAnalyst analyst) { // يتاكد من أن المحلل الأمني غير فارغ قبل تعيينه للحادث
        if (analyst != null) {
            this.assignedAnalyst = analyst; // عند تعيين محلل أمني للحادث، يتم تحديث حالة الحادث إلى "قيد التنفيذ"
            this.status = IncidentStatus.IN_PROGRESS;
            System.out.println("Incident [" + title + "] assigned to: " + analyst.getName());
        }
    }

    public void updateStatus(IncidentStatus newStatus) {
        if (newStatus != null) {
            this.status = newStatus;
        }
    }

    public int getIncidentId() {
        return incidentId;
    }

    public String getTitle() {
        return title;
    }

    public Device getAffectedDevice() {
        return affectedDevice;
    }

    public Threat getDetectedThreat() {
        return detectedThreat;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Incident #" + incidentId +
            ": " + title +
            " | Status: " + status +
            " | Device: " + (affectedDevice != null ? 
            affectedDevice.getDeviceId() : "N/A") + 
            " | Threat: " + (detectedThreat != null ? 
            detectedThreat.getThreatId() : "N/A"); 
    }
// Threat attacks Device
//          ↓
//Vulnerability exploited
//        ↓
//Incident created
//       ↓
//Analyst assigned
//       ↓
//Status updated
//     ↓
//Resolved

}