package com.ecdtms.model.network;

public enum DeviceStatus {
    // ليه enum يمنع القيم العشوائية ويضمن أن حالة الجهاز تكون واحدة من قيم محددة فقط، وده يقلل الأخطاء ويحسن الـ maintainability
    // Enumeration
    ACTIVE,
    OFFLINE,
    COMPROMISED
}