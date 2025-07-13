package models.domainmodels

import constants.ShipmentStatus

data class ShippingUpdateDomainModel(
    val previousStatus: ShipmentStatus,
    val newStatus: ShipmentStatus,
    val timestamp: Long
)